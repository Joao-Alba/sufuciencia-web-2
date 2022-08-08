# sufuciencia-web-2

Projeto com uma API simples para um restaurante, com registro de comandas e produtos. 
Criado para a prova de suficiência da matéria Web 2 - Furb.

Tecnologias: 
- Java 11
- Maven 
- MySql
- Swagger
- OAuth

Para rodar sua aplicação, você precisa:
- Criar uma servidor MySql em sua máquina, e criar um banco de dados com nome "restaurante".

Para utilizar a autenticação do sistema OAuth, você deve parametrizar seu aplicativo de requisições da seguinte maneira:
- Usar a rota "localhost:8080/oauth/token" e adicionar os seguintes parâmetros
- Adicionar uma autenticação simple (Basic Auth) com usuário "admin" e senha "admin"
![image](https://user-images.githubusercontent.com/76071589/183434022-6af080e4-db64-4bda-be16-fd4c586e0206.png)  
- Esses valores podem ser alterados no arquivo application.properties
- ![image](https://user-images.githubusercontent.com/76071589/183434139-d748d829-95ff-4e5b-a0d7-df4f2820b3df.png)  
  
- Adicionar no corpo da requisição os seguintes valores
![image](https://user-images.githubusercontent.com/76071589/183434326-a06ff954-23c1-43cd-9ca1-8a655abd2880.png)  
- Os valores para "user" e "password" podem ser alterados na classe SecurityConfig.java
- ![image](https://user-images.githubusercontent.com/76071589/183436432-4be37e84-26d3-403f-8474-f22f2ccbdb34.png)  
      
- Copie o valor retornado como "access_token", e para autorização das outras requisições, use esse valor como um Bearer Token. 

Para abrir a documentação Swagger, utilizar a rota "<http://localhost:8080/swagger-ui/index.html>"
