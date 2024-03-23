# Projeto de Gerenciamento de Pessoas e Endereços

Este projeto consiste em um sistema para gerenciamento de pessoas e seus respectivos endereços. Ele oferece funcionalidades básicas como cadastro, edição, exclusão e listagem de pessoas e endereços.

## Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias e frameworks:

- Java
- Jakarta EE (anteriormente Java EE)
- JDBC
- PostgreSQL
- JSF (JavaServer Faces)

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- **`com.controller`**: Pacote contendo os controladores para as entidades Pessoa e Endereço.
- **`com.dao`**: Pacote contendo a classe responsável pela conexão com o banco de dados.
- **`com.model`**: Pacote contendo as classes de modelo para Pessoa e Endereço.
- **`com.repository`**: Pacote contendo as classes responsáveis pela interação com o banco de dados, realizando operações como salvar, listar, editar e excluir registros.

## Configuração do Banco de Dados

O banco de dados utilizado é o PostgreSQL. Para configurar a conexão com o banco de dados, é necessário ajustar as seguintes propriedades na classe `Conexao` dentro do pacote `com.dao`:

- URL: Endereço do banco de dados PostgreSQL.
- USUARIO: Nome de usuário do banco de dados.
- SENHA: Senha do usuário do banco de dados.

## Como Executar o Projeto

Para executar o projeto, siga as seguintes etapas:

1. Certifique-se de ter o PostgreSQL instalado e em execução.
2. Crie um banco de dados com o nome "sinerjia" (ou ajuste o nome conforme necessário).
3. Execute os scripts SQL necessários para criar as tabelas `pessoa` e `endereco`, bem como suas respectivas sequências e relacionamentos.
4. Configure a conexão com o banco de dados conforme descrito na seção anterior.
5. Importe o projeto em um ambiente de desenvolvimento Java compatível (por exemplo, Eclipse, IntelliJ IDEA).
6. Execute o servidor de aplicação (por exemplo, Apache Tomcat, WildFly).
7. Implante o projeto no servidor de aplicação.
8. Acesse o aplicativo por meio do navegador web, utilizando o URL fornecido pelo servidor de aplicação.

## Funcionalidades do Sistema

O sistema oferece as seguintes funcionalidades:

### Gerenciamento de Pessoas

- Cadastro de novas pessoas, informando nome, idade e sexo.
- Listagem de todas as pessoas cadastradas.
- Edição das informações de uma pessoa existente.
- Exclusão de uma pessoa do sistema.

### Gerenciamento de Endereços

- Cadastro de endereços associados a uma pessoa, informando estado, cidade, logradouro, número e CEP.
- Listagem de todos os endereços cadastrados.
- Os endereços são relacionados às pessoas cadastradas no sistema.

## Observações

- Este projeto utiliza o framework JavaServer Faces (JSF) para a camada de visualização. Certifique-se de ter um servidor de aplicação compatível para executar o projeto.
- Recomenda-se o uso de ferramentas de desenvolvimento integrado (IDE) como Eclipse ou IntelliJ IDEA para facilitar o desenvolvimento e execução do projeto.
