# 📦 Sistema de cadastro de animes

Este projeto é uma API RESTful para cadastro de animes, permitindo operações de CRUD (Create, Read, Update, Delete).

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
 │   │   ├── 📂 java/br/com/ichibei/anime
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
 │   │   ├── 📂 java/br/com/ichibei/anime
 │   │   │   ├── 📜 AnimeApplicationTests.java    
 │   │   │   ├── 📜 AnimeServiceTest.java  
 │   ├── 📂 docs
 │   │   │   ├── 📜 notion.pdf 
 ├── 📜 application.properties
 ├── 📜 README.md          
 ├── 📜 pom.xml            
 ├── 📜 .gitignore  

# 🛠️ Configuração do Banco de Dados

Este projeto usa o banco de dados **PostgreSQL** e o **Flyway** para migração de banco de dados.  
Para configurar corretamente a conexão com o banco, edite o arquivo `src/main/resources/application.properties` com as seguintes informações:

```properties
# Configurações do banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/animes
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.datasource.driver-class-name=org.postgresql.Driver

# Flyway - Migrações do banco de dados
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# Configurações do JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update


🔄 Endpoints da API

Método
	

Endpoint
	

Descrição

POST
	

/animes
	

Criar um novo anime

GET
	

/animes
	

Listar todos os animes

GET
	

/animes/{id}
	

Buscar um anime pelo ID

PUT
	

/animes/{id}
	

Atualizar um anime

DELETE
	

/animes/{id}
	

Deletar um anime
🧪 Testes

Para rodar os testes unitários, execute o seguinte comando:

properties

mvn test

Os testes unitários são implementados com JUnit 5 e Mockito, e garantem que os serviços e a lógica de negócios do sistema funcionem corretamente.
📚 Documentação

A documentação detalhada sobre a estrutura do projeto está disponível no arquivo PDF abaixo:
