package com.seplag.ricardogoulart035012.domain.service;

import com.seplag.ricardogoulart035012.domain.model.Album;
import com.seplag.ricardogoulart035012.domain.model.Imagem;
import com.seplag.ricardogoulart035012.dto.response.ImagemResponseDTO;
import com.seplag.ricardogoulart035012.exception.RecursoNaoEncontradoException;
import com.seplag.ricardogoulart035012.infra.repository.AlbumRepository;
import com.seplag.ricardogoulart035012.infra.repository.ImagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImagemService {

    private final AlbumRepository albumRepository;
    private final ImagemRepository imagemRepository;
    private final MinioService minioService;

    public ImagemService(AlbumRepository albumRepository, ImagemRepository imagemRepository, MinioService minioService) {
        this.albumRepository = albumRepository;
        this.imagemRepository = imagemRepository;
        this.minioService = minioService;
    }

    public ImagemResponseDTO salvar(Long albumId, MultipartFile arquivo){

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado"));
        String path = minioService.upload(arquivo,"albuns/" + album.getId());

        Imagem imagem = new Imagem();
        imagem.setNomeArquivo(arquivo.getOriginalFilename());
        imagem.setTipo(arquivo.getContentType());
        imagem.setTamanho(arquivo.getSize());
        imagem.setPath(path);
        imagem.setDataUpload(LocalDateTime.now());
        imagem.setAlbum(album);
        imagem = imagemRepository.save(imagem);
        return mapToResponse(imagem);
    }

    public List<ImagemResponseDTO> listarPorAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado"));
        return imagemRepository.findByAlbumId(album.getId()).stream().map(this::mapToResponse).toList();
    }

    public void deletarImagem(Long imagemId) {

        Imagem imagem = imagemRepository.findById(imagemId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Imagem não encontrada"));
        minioService.remover(imagem.getPath());
        imagemRepository.delete(imagem);
    }

    private ImagemResponseDTO mapToResponse(Imagem imagem){
        String urlTemporaria =
                minioService.gerarUrlTemporaria(imagem.getPath());
        return new ImagemResponseDTO(
                imagem.getId(),
                imagem.getNomeArquivo(),
                imagem.getTipo(),
                imagem.getTamanho(),
                imagem.getPath(),
                imagem.getDataUpload(),
                imagem.getAlbum().getId(),
                imagem.getAlbum().getTitulo(),
                urlTemporaria
        );
    }
}
