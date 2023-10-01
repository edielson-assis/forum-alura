<h1 align="center">Fórum Alura</h1> 

<p align="center">
<img src="https://img.shields.io/github/stars/edielson-assis?style=social"/>
<img src="https://img.shields.io/badge/languange-java-java"/>
<img src="https://img.shields.io/badge/license-Mit-mit"/>
</p>

<br>

> Status do Projeto: :heavy_check_mark: (Concluído)

<br>

### Tópicos 

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Layout da Aplicação](#layout-da-aplicação-dash)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como rodar a aplicação](#como-rodar-a-aplicação-arrow_forward)

## Descrição do projeto 

<p align="justify">
Este é o back-end de um fórum de perguntas e respostas de cursos, desenvolvido em Java e Spring Boot. O sistema permite que os usuários criem e respondam tópicos de discussão no fórum. Alguns dos principais recursos do sistema incluem:

- Criação de tópicos: Os usuários podem criar novos tópicos de discussão, fornecendo um título, conteúdo e categoria relevante.

- Respostas a Tópicos: Os usuários podem responder aos tópicos existentes, fornecendo suas opiniões e soluções para as questões apresentadas.
</p>

## Funcionalidades

:heavy_check_mark: **Cadastro de Usuários:** Os usuários podem se cadastrar na plataforma, fornecendo informações básicas como nome, e-mail e senha.

:heavy_check_mark: **Autenticação de Usuários:** Os usuários registrados podem fazer login na plataforma usando suas credenciais. 

:heavy_check_mark: **Postagem de Tópicos:** Os usuários podem criar novos tópicos de discussão.

:heavy_check_mark: **Respostas a Tópicos:** Os usuários podem responder aos tópicos existentes.

:heavy_check_mark: **Categorias de Tópicos:** Os tópicos são organizados em categorias para facilitar a navegação e busca de conteúdo relevante.

:heavy_check_mark: **Classificação de Tópicos:** Os tópicos podem ser classificados por título, data de criação, etc.

:heavy_check_mark: **Controle de Acesso:** O acesso a certas funcionalidades do fórum pode ser restrito com base nas permissões do usuário, como administradores ou usuários regulares.

:heavy_check_mark: **Comentários:** Os usuários podem adicionar comentários a tópicos e respostas, permitindo discussões mais aprofundadas.

:heavy_check_mark: **Pesquisa:** Os usuários podem pesquisar tópicos e respostas usando palavras-chave ou filtros avançados.

:heavy_check_mark: **Atualização de Tópicos:** Apenas os administradores podem atualizar o status de um tópico existente.

:heavy_check_mark: **Remoção de Tópicos:** O usuário só consegue remover o tópico que ele criou, sendo impedido de deletar o tópico de outros usuários.

:heavy_check_mark: **Alteração de Respostas:** O usuário só consegue deletar ou alterar a própria resposta.

## Layout da Aplicação :dash:

![Screenshot 2023-09-28 133549](https://github.com/edielson-assis/forum-alura/assets/105529988/81893148-ff50-4088-8162-f518b01e8655)

## Pré-requisitos

- [x] Conhecer a sintaxe do Java<br>
- [x] Java JDK 17<br>
- [x] IDE para desenvolvimento Java (utilizei o Vs Code)<br>
- [x] Git<br>
- [x] Conta no GitHub<br>
 
## Como rodar a aplicação :arrow_forward:

Faça um fork do projeto, após isso, abra o terminal do git bash, na pasta onde deseja salvar o projeto, e digite o seguinte comando: 

```
git clone git@github.com:edielson-assis/forum-alura.git
``` 
Crie uma base de dados no MySQL com o nome **forum-alura**. Não é necessário criar as tabelas.

Em seguida, abra o projeto na IDE de sua preferência, atualize o arquivo **application.properties** com os dados da base de dados criada e execute-o a partir do método **main**.

Após executar o projeto, abra o seu navegador de internet e na barra de endereço, digite o seguinte comando:

```
http://localhost:8080/swagger-ui/index.html
```
Agora é só testar as funcionalidades do projeto. Não se esqueça de se registrar e fazer login para se autenticar na plataforma. Toda comunicação entre os endpoints é feita via token JWT.

Obs: Apenas usuários com o nível de acesso **admim** podem criar, alterar ou excluir cursos e categorias, etc. Por padrão uma conta é criada com o nível de acesso comum. Caso deseje alterar o nível de acesso de um usuário, essa alteração deverá ser feita diretamente no banco de dados, na coluna **role_id** da tabela user.

## Linguagens, dependencias e libs utilizadas :books:

- [Java](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
- [Maven](https://maven.apache.org/ref/3.9.3/maven-core/index.html)
- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- [MySQL Connector](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa/3.1.4)
- [Jakarta Bean Validation API](https://mvnrepository.com/artifact/jakarta.validation/jakarta.validation-api/3.0.2)
- [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Flyway MySQL](https://mvnrepository.com/artifact/org.flywaydb/flyway-mysql/9.22.2)
- [Flyway Core](https://mvnrepository.com/artifact/org.flywaydb/flyway-core/9.22.2)
- [Spring Boot Starter Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.1.4)
- [Java JWT](https://mvnrepository.com/artifact/com.auth0/java-jwt/4.4.0)
- [SpringDoc OpenAPI Starter WebMVC UI](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui/2.2.0)

## Contribuindo 🤝

<p>
Este é um projeto open source, então contribua com ele.<br>
Se te ajudei de alguma forma, ficarei feliz em saber. E caso você conheça alguém que se identifique com o conteúdo, não deixe de compartilhar.<br>
<br>
Se possível:<br>
⭐️  Star o projeto<br>
🐛 Encontrar e relatar issues<br>
</p>

## Desenvolvedor :octocat:

| [<img src="https://github.com/edielson-assis/conversor/assets/105529988/90c01d9d-ccf5-4b60-b740-c0db10e28b2a" width=115><br><sub>Edielson Assis</sub>](https://github.com/edielson-assis) |
| :---: |

## Licença 

The [MIT License](https://github.com/edielson-assis/forum-alura/blob/main/LICENSE) (MIT)

Copyright :copyright: 2023 - Fórum Alura
