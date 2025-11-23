BIBLIOTECA CRUD
===============

Projeto da disciplina Banco de Dados I para a graduação em Bacharelado em Sistemas de Informação na Universidade de São Paulo.
Esta é uma aplicação web de CRUD (Create, Read, Update, Delete) para gestão de uma biblioteca, desenvolvida com Spring Boot, utilizando o PostgreSQL como banco de dados e Flyway para migrações de esquema e dados.

---------------------------------------------------

1. TECNOLOGIAS E VERSÕES NECESSÁRIAS (PRÉ-REQUISITOS)
---------------------------------------------------

Para rodar esta aplicação localmente, você precisará dos seguintes softwares instalados:

* **Java Development Kit (JDK):** Versão 21.
* **Maven:** Versão 3.x ou superior (Ferramenta de build do projeto).
* **Docker:** Versão mais recente (Necessário para rodar o banco de dados via Docker Compose).
* **Docker Compose:** Versão mais recente (Orquestrador de containers).

**Versões Chave do Projeto:**
* **Spring Boot Parent:** 3.5.7
* **PostgreSQL (Imagem Docker):** 17.6

**Dependências Principais:**
* Spring Boot Starter Data JPA
* PostgreSQL Driver (org.postgresql)
* Flyway Core e Flyway PostgreSQL
* Spring Boot Starter Thymeleaf
* Lombok
* Spring Cloud Starter OpenFeign

-----------------------------

2. COMO RODAR A APLICAÇÃO
-----------------------------

Para executar a aplicação, o banco de dados PostgreSQL deve estar rodando e acessível. A forma mais fácil de fazer isso é utilizando o Docker Compose fornecido.

### 2.1. Inicializar o Banco de Dados com Docker Compose

O arquivo `compose.yaml` já está configurado para iniciar o banco de dados com as credenciais esperadas pela aplicação.

1.  **Navegue** até o diretório que contém o arquivo `compose.yaml`.
2.  **Execute** o comando para iniciar o container do banco de dados:

    ```bash
    docker compose up -d
    ```

    **Detalhes da Conexão:**
    * **Porta:** 5432
    * **Nome do Banco (POSTGRES_DB):** biblioteca_crud
    * **Usuário (POSTGRES_USER):** postgres
    * **Senha (POSTGRES_PASSWORD):** postgres
    * A URL de conexão configurada no `application.properties` é `jdbc:postgresql://localhost:5432/biblioteca_crud`.

### 2.2. Execução da Aplicação Spring Boot

A aplicação utiliza **Flyway** para gerenciar as migrações do banco de dados (scripts em `src/main/resources/db/migration`), que serão aplicadas automaticamente na inicialização.

#### Opção A: Rodar Diretamente com Maven (Recomendado para Desenvolvimento)

1.  **Navegue** até o diretório raiz do projeto (`biblioteca-crud`).
2.  **Execute** o comando Maven para rodar a aplicação:

    ```bash
    mvn spring-boot:run
    ```

    A aplicação estará acessível em `http://localhost:8080` (porta padrão Spring Boot).

#### Opção B: Compilar e Rodar como JAR

1.  **Compilar o Projeto:**
    **Navegue** até o diretório raiz do projeto (`biblioteca-crud`) e execute:

    ```bash
    mvn clean install
    ```

    Isso irá gerar o arquivo JAR na pasta `target`.

2.  **Executar o JAR:**
    Execute o arquivo JAR gerado:

    ```bash
    java -jar target/biblioteca-crud-0.0.1-SNAPSHOT.jar
    ```

    *Nota: O nome do arquivo JAR pode variar ligeiramente com base na versão e artifactId do seu `pom.xml`.*

------------------------------

3. COMO PARAR O BANCO DE DADOS
------------------------------

Para parar e remover o container PostgreSQL e seus volumes, execute no mesmo diretório do `compose.yaml`:

```bash
docker compose down -v
