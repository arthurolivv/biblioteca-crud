# Biblioteca CRUD - Spring Boot

Projeto de exemplo de sistema de biblioteca desenvolvido em **Spring Boot**, com CRUD de livros, usuários e reservas.

Este documento contém instruções para rodar o projeto de duas formas:

1. **Direto no PC usando H2** (sem Docker)
2. **Usando Docker com PostgreSQL**

---

## 1. Rodando com H2 (sem Docker)

### Pré-requisitos

* Java 21 ou superior
* Git

> **Não é necessário instalar Docker ou PostgreSQL.**

### Clonando o projeto

```bash
git clone https://github.com/arthurolivv/biblioteca-crud.git
cd biblioteca-crud
```

### Rodando o projeto

#### Linux/macOS

```bash
./mvnw clean package
java -jar target/biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

#### Windows

```cmd
./mvnw.cmd clean package
java -jar target\biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

### Acessando a aplicação

* URL da aplicação: `http://localhost:8080`
* Console H2: `http://localhost:8080/h2`

    * JDBC URL: `jdbc:h2:mem:biblioteca;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
    * User: `h2`
    * Password: `h2`

### Scripts de execução rápida

#### Windows (`run.bat`)

```bat
@echo off
./mvnw.cmd clean package
java -jar target\biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
pause
```

#### Linux/macOS (`run.sh`)

```bash
#!/bin/bash
./mvnw clean package
java -jar target/biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2
```

> Permissão de execução no Linux/macOS: `chmod +x run.sh`

---

## 2. Rodando com Docker (PostgreSQL)

### Pré-requisitos

* Java 21 ou superior
* Docker e Docker Compose
* Git

### Clonando o projeto

```bash
git clone https://github.com/arthurolivv/biblioteca-crud.git
cd biblioteca-crud
```

### Rodando o projeto com Docker

1️⃣ Subir o PostgreSQL via Docker Compose:

```bash
docker-compose up -d
```

2️⃣ Gerar e rodar o JAR com perfil PostgreSQL:

#### Linux/macOS

```bash
./mvnw clean package
java -jar target/biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
```

#### Windows

```cmd
./mvnw.cmd clean package
java -jar target\biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
```

### Scripts de execução rápida

#### Windows (`run-docker.bat`)

```bat
@echo off
docker-compose up -d
./mvnw.cmd clean package
java -jar target\biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
pause
```

#### Linux/macOS (`run-docker.sh`)

```bash
#!/bin/bash
docker-compose up -d
./mvnw clean package
java -jar target/biblioteca-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres
```

> Permissão de execução no Linux/macOS: `chmod +x run-docker.sh`

---

## Observações

* **H2:** banco embutido, mantém dados em arquivo `./data/biblioteca` mesmo após fechar a aplicação.
* **Perfil H2:** permite rodar sem instalar PostgreSQL.
* **Perfil PostgreSQL:** ideal para desenvolvimento ou produção, usando Docker.


---

