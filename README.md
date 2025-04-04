# 📡 Gestão de Cursos API (Backend - Quarkus)

Aplicação backend desenvolvida com **Quarkus**, responsável por gerenciar **alunos**, **cursos** e a relação entre eles. Expõe uma API REST que pode ser consumida por qualquer cliente (ex: Angular frontend).

---

## 🚀 Tecnologias Utilizadas

- [Quarkus](https://quarkus.io/) (Supersonic Subatomic Java)
- [Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache)
- [RESTEasy Reactive](https://quarkus.io/guides/rest)
- [Jackson](https://quarkus.io/guides/rest#json-serialisation)
- [H2 Database (Dev)](https://quarkus.io/guides/datasource)
- [Maven](https://maven.apache.org/)

---

## ☕ Requisitos

- **Java 17** (JDK)
- **Maven 3.8+**

⚠️ Certifique-se de que o Java 21 está instalado e configurado corretamente.  
Para verificar:

```bash
java -version

---

```
---

## 📦 Funcionalidades

- ✅ Cadastro de alunos e cursos
- 🔄 Associação muitos-para-muitos entre alunos e cursos
- 🔍 Listagem de dados
- 🛑 Validações de integridade (ex: impedir remoção de curso vinculado)
- 🌐 API RESTful com CORS habilitado para o frontend Angular (`http://localhost:4200`)

---

## ⚙️ Configurações do `application.properties`

```properties
# Banco de Dados H2 (memória)
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.url=jdbc:h2:mem:devdb;DB_CLOSE_DELAY=-1
quarkus.datasource.username=admin
quarkus.datasource.password=teste

# Geração do schema (dev apenas)
quarkus.hibernate-orm.database.generation=drop-and-create

# CORS para o frontend Angular
quarkus.http.cors=true
quarkus.http.cors.methods=GET,PUT,POST,DELETE
quarkus.http.cors.origins=http://localhost:4200
quarkus.http.cors.headers=accept,authorization,content-type
```

---

## 🛠️ Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/leanford/gestao-cursos-api
cd gestao-cursos-api
```

### 2. Inicie o modo desenvolvimento

```bash
./mvnw quarkus:dev
```

A API estará disponível em:  
[http://localhost:8080/api](http://localhost:8080/api)

A Dev UI estará disponível em:  
[http://localhost:8080/q/dev/](http://localhost:8080/q/dev/)

---

## 📦 Build e Execução

### Empacotar a aplicação

```bash
./mvnw package
```

Executar:

```bash
java -jar target/quarkus-app/quarkus-run.jar
```

### Über-jar

```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar
```

---

## 🧪 Testes e Integração

Você pode testar os endpoints com ferramentas como **Postman**, **Insomnia** ou diretamente pelo frontend Angular (em `http://localhost:4200`).

---

✅ Testes Unitários

Os testes unitários estão localizados em:

gestao-cursos-api/src/test/java

Mantendo a mesma estrutura de pacotes da aplicação. Exemplo:

| Classe                         | Teste correspondente                                                      |
|--------------------------------|---------------------------------------------------------------------------|
| src/main/java/.../AlunoService.java | src/test/java/br/com/unifor/service/AlunoServiceTest.java             |
| src/main/java/.../CursoService.java | src/test/java/br/com/unifor/service/CursoServiceTest.java             |

🧪 Como executar os testes

Com Maven:

    mvn test

📦 Tecnologias utilizadas nos testes

- JUnit 5 – Framework de testes
- Mockito – Mocks e verificações
- MockedStatic – Mock para métodos estáticos (ex: Panache)

## ✍️ Autor

Desenvolvido por Leandro Alves ([github.com/leanford](https://github.com/leanford)) com 💙  
Contribuições, sugestões e melhorias são bem-vindas!
