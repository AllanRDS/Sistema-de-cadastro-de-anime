# 📦 API de Animes

Este projeto é uma API RESTful para gerenciamento de animes, permitindo operações de CRUD (Create, Read, Update, Delete).

## 🚀 Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Validation)  
- **Banco de Dados PostgreSQL**  
- **Flyway**  
- **Spring Test** (JUnit 5 e Mockito)  
- **Maven**  
- **Postman** (Testes da API)  

## 📂 Estrutura do projeto

```plaintext
 ├── 📂 src
 │   ├── 📂 main
 │   │   ├── 📂 java/com/exemplo/animes
 │   │   │   ├── 📂 controller   
 │   │   │   ├── 📂 entity       
 │   │   │   ├── 📂 repository   
 │   │   │   ├── 📂 service      
 │   │   │   ├── 📜 AnimeApplication.java  
 │   │   ├── 📂 resources
 │   │   │   ├── 📜 application.properties   
 │   │   │   ├── 📂 db/migration            
 │   │   │   ├── 📂 static / templates       
 │   ├── 📂 test
 │   │   ├── 📂 java/com/exemplo/animes
 │   │   │   ├── 📜 AnimeApplicationTests.java    
 │   │   │   ├── 📜 AnimeServiceTest.java  
 │   ├── 📂 docs
 │   │   │   ├── 📜 documentacao.pdf 
 ├── 📜 application.properties
 ├── 📜 README.md          
 ├── 📜 pom.xml            
 ├── 📜 .gitignore  
