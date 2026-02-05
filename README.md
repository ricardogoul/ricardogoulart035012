Aplicação feita por Ricardo Menechino Goulart para a vaga de Desenvolvedor Back End Java da SEPLAG.

Para executar basta criar um bucket chamado images no MinIO e rodar o comando docker-compose up dentro da pasta do projeto para subir a aplicação.

O projeto segue uma arquitetura em camadas: Controller, Service, Repository, Database, MinIO. Onde a controller expõe os endpoints REST, service expõe regras de negócio, aepository acessa dados via JPA e o MinIO faz gerenciamento de arquivos (upload, delete, URL temporária).

A aplicação utiliza autenticação baseada em JWT:
  Access Token: curto prazo, usado nas requisições
  Refresh Token: usado para renovar o access token

Fluxo:
  Login gera access + refresh token
  Access token enviado no header Authorization: Bearer <token>
  Refresh token permite manter o usuário logado
  Swagger já está configurado para aceitar autenticação via Bearer Token.

Endpoints
Autenticação
  POST /auth/login
  POST /auth/refresh
Artistas
  POST /artistas
  GET /artistas
  GET /artistas/{id}
  PUT /artistas/{id}
  DELETE /artistas/{id}
Álbuns
  POST /albuns
  GET /albuns
  GET /albuns/{id}
  PUT /albuns/{id}
  DELETE /albuns/{id}
Imagens
  POST /albuns/{id}/imagens
  GET /albuns/{id}/imagens
  DELETE /imagens/{id}

Testes via Swagger
  Faça login (/auth/login), copie o access token, clique em Authorize no Swagger e cole o token.
