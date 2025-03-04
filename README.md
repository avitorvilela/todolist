# To-Do List API

Esta é uma API de lista de tarefas (To-Do List) desenvolvida em Spring Boot. A API permite o cadastro de usuários, o gerenciamento de tarefas e a exibição das tarefas cadastradas. Cada usuário só pode acessar e modificar suas próprias tarefas, garantindo a privacidade e segurança dos dados. As senhas dos usuários são criptografadas usando o algoritmo BCrypt para maior segurança.

## Tecnologias Utilizadas

- **Spring Web**: Para criar os endpoints da API.
- **Spring Dev Tools**: Para facilitar o desenvolvimento com recarregamento automático.
- **Spring Data JPA**: Para a persistência de dados e interação com o banco de dados.
- **H2 Database**: Banco de dados em memória utilizado para desenvolvimento e testes.
- **Lombok**: Para reduzir a verbosidade do código com anotações como `@Data`, `@Getter`, `@Setter`, etc.
- **BCrypt**: Para criptografar as senhas dos usuários.

## Funcionalidades

- **Cadastro de Usuários**: Os usuários podem se cadastrar fornecendo um nome de usuário e uma senha. A senha é criptografada antes de ser armazenada no banco de dados.
- **Cadastro de Tarefas**: Os usuários podem criar novas tarefas, fornecendo um título, descrição e status (pendente, em andamento, concluída).
- **Exibição de Tarefas**: Os usuários podem visualizar todas as tarefas que criaram.
- **Atualização de Tarefas**: Os usuários podem atualizar o título, descrição e status de suas tarefas.
- **Autenticação e Autorização**: Cada usuário só pode acessar e modificar suas próprias tarefas.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **Controller**: Contém os endpoints da API.
- **Service**: Contém a lógica de negócio.
- **Repository**: Interface para interação com o banco de dados.
- **Model**: Contém as entidades do banco de dados.
- **DTO**: Contém os objetos de transferência de dados.
- **Security**: Contém a configuração de segurança e criptografia de senhas.

## Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven
- Docker (opcional, para deploy)

### Executando Localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/to-do-list-api.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd to-do-list-api
   ```

3. Execute o projeto com Maven:

   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`.

### Executando com Docker

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/to-do-list-api.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd to-do-list-api
   ```

3. Construa a imagem Docker:

   ```bash
   docker build -t to-do-list-api .
   ```

4. Execute o contêiner:

   ```bash
   docker run -p 8080:8080 to-do-list-api
   ```

5. A API estará disponível em `http://localhost:8080`.

### Deploy no Render

O deploy da API foi realizado no Render utilizando um Dockerfile. URL da API: https://spring-todolist-nrox.onrender.com

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
