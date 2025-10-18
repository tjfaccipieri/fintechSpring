# Fintech Spring API

API de backend para uma aplicação de gestão financeira (fintech), desenvolvida com o framework Spring Boot.

## 📜 Descrição

Este projeto consiste em uma API RESTful para gerenciar usuários, contas bancárias, cartões e transações financeiras. Ele foi estruturado para suportar diferentes tipos de usuários (Pessoa Física e Pessoa Jurídica) e fornecer as operações básicas de um aplicativo financeiro.

## ✨ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot:** Framework principal para o desenvolvimento da aplicação.
- **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
- **Maven:** Gerenciador de dependências e build do projeto.
- **Banco de Dados Oracle:** Sistema de gerenciamento de banco de dados relacional.
- **Hibernate:** Implementação da especificação JPA, com validações (hibernate-validator).

## 📂 Estrutura do Projeto

O projeto segue a arquitetura padrão de aplicações Spring Boot, dividida em camadas:

- `src/main/java/com/tjfaccipieri/fintechspring`
  - `controller/`: Responsável por expor os endpoints da API (camada de entrada).
  - `model/`: Contém as entidades JPA que representam a estrutura de dados do banco.
  - `repository/`: Interfaces que estendem `JpaRepository` para a manipulação de dados.
  - `service/`: Onde reside a lógica de negócio da aplicação.
- `src/main/resources/`
  - `application.properties`: Arquivo de configuração principal da aplicação (ex: conexão com o banco de dados).

## 🗃️ Modelagem de Dados

A API é construída em torno dos seguintes modelos principais:

- **Usuario:** Classe abstrata que define os dados comuns a todos os usuários.
  - **UsuarioPF:** Herda de `Usuario` e representa uma Pessoa Física (com CPF).
  - **UsuarioPJ:** Herda de `Usuario` e representa uma Pessoa Jurídica (com CNPJ).
- **Autenticacao:** Armazena as credenciais de login (email e senha) de um usuário.
- **Conta:** Representa a conta bancária de um usuário, com seu saldo.
- **Cartao:** Representa o cartão (crédito/débito) associado a uma conta.
- **Transacao:** Modela as transações financeiras (entradas e saídas) de uma conta.
- **Categoria:** Permite categorizar as transações (ex: Alimentação, Transporte).
- **TipoTransacao:** Define o tipo da transação (ex: Receita, Despesa).

## 🚀 Como Executar o Projeto

### Pré-requisitos

- JDK 17 ou superior instalado.
- Apache Maven instalado.
- Acesso a um banco de dados Oracle.

### Configuração

1.  Clone o repositório.
2.  Abra o arquivo `src/main/resources/application.properties`.
3.  Configure as seguintes propriedades com as credenciais do seu banco de dados Oracle:
    ```properties
    spring.datasource.url=jdbc:oracle:thin:@SEU_HOST:PORTA:SID
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA
    ```
4.  (Opcional) Configure a propriedade `spring.jpa.hibernate.ddl-auto` para `update` ou `create` durante o desenvolvimento inicial para que o Hibernate gerencie o schema do banco.

### Execução

1.  Navegue até o diretório raiz do projeto.
2.  Execute o seguinte comando no seu terminal:

    ```shell
    ./mvnw spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

## Endpoints da API

Os endpoints da API são expostos sob o prefixo `/api`. Um exemplo de endpoint disponível é:

- `GET /api/usuarios`: Retorna uma lista de todos os usuários.
- `GET /api/usuarios/{id}`: Busca um usuário específico pelo seu ID.

