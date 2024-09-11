# Boletim-Saude-Itabera-SP

Projeto desenvolvido para a Prefeitura Municipal de Itaberá, com o objetivo de facilitar a criação de relátorios diários e mensais dos atendimentos medicos realizados da cidade.

<br>

Front-end - React: https://github.com/LucasLemeCF/Boletim-Saude-Itabera-SP-app   
Container - Docker Hub: https://hub.docker.com/repository/docker/lucaslemec/boletim-saude/general  
Design - Figma: https://www.figma.com/design/Ena3r3kvJhocQlGNe5DT7i/Untitled?t=oyZD2n1bGHiyJxZm-0  
Arquitetura - Miro: https://miro.com/app/board/uXjVK5NG8zs=/  
API Local - Swagger: http://localhost:8080/api/swagger-ui/index.html  
<br>

## Como Executar (Não finalizado)

### Docker

- Necessário ter o Docker Engine instalado, disponivel em: https://docs.docker.com/engine/install
- Necessário ter o Git instalado, disponivel em: https://git-scm.com/downloads

<br>
Há 3 formas de executar a aplicação com Docker:
<br><br>

#### 1 - Docker Hub:

Forma mais simples, nela será utilizado a ultima versão dos containers disponíveis em: https://hub.docker.com/repository/docker/lucaslemec/boletim-saude/general  

Primeiro é necessário criar um arquivo `docker-compose.yml` com o seguinte conteúdo:
```yaml
name: boletim-saude-infra

services:
  app:
    container_name: boletim-saude-api
    build: .
    environment:
      - APPLICATION_PROFILE=dev
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_URL=jdbc:postgresql://postgres:5432/BoletimSaude
      - API_TOKEN_SECRET=lzl024rRZc
      - USUARIO=admin
      - SENHA=123
    ports:
      - "8080:8080"
    networks:
      - boletim-saude-network
    depends_on:
      - database

  database:
    container_name: postgres
    image: postgres:16
    environment:
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_USER=postgres
      - POSTGRES_DB=BoletimSaude
    ports:
      - "5432:5432"
    volumes:
      - ../docker/data/postgresql:/var/lib/postgresql/data
    networks:
      - boletim-saude-network

  pgadmin:
    container_name: pgadmin
    image: dpage/pgadmin4
    environment:
      - PGADMIN_DEFAULT_EMAIL=test@test.com
      - PGADMIN_DEFAULT_PASSWORD=123
    ports:
        - "5050:80"
    depends_on:
      - database
    networks:
      - boletim-saude-network

  app-frontend:
    container_name: boletim-saude-app
    image: boletim-saude-itabera-sp-app
    environment:
      - NEXTAUTH_URL=http://localhost:3000
      - NEXTAUTH_SECRET="OFsQ6wuWlBCetNwuME5qus2Zocu23bF0W9d3dZo5hGk="
    ports:
      - "3000:3000"
    networks:
      - boletim-saude-network
    depends_on:
        - app
    
networks:
  boletim-saude-network:
    driver: bridge
```
Em seguida, abra um terminal na mesma pasta do arquivo anterior e execute o comando:
```bash
docker-compose -f docker-compose.yml up -d --build
```
<br>

#### 2 - Apenas a API:

Com essa forma será utilizado o dockerfile do próprio codigo fonte, para isso é necessário clonar o repositório:
```bash
git clone 
```
Em seguida, abra um terminal na mesma pasta do projeto e execute o comando:
```bash
docker-compose -f docker-compose-local.yml up -d --build
```
<br>

#### 3 - API + Front-end:
Necessário ter o container docker do projeto front-end já baixado, disponível em: https://github.com/LucasLemeCF/Boletim-Saude-Itabera-SP-app
Em seguida é necessário clonar o repositório:
```bash
git clone 
```
Por fim, abra um terminal na mesma pasta do projeto e execute o comando:
```bash
docker-compose -f docker-compose-prd.yml up -d --build
```
Em seguida é necessário configurar as variveis de ambiente como no seguinte exemplo:
```bash
APPLICATION_PROFILE=dev
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_URL=jdbc:postgresql://postgres:5432/BoletimSaude
API_TOKEN_SECRET=ABC123456789
USUARIO=admin
SENHA=123
```


### Local