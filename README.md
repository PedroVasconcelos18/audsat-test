
# Audsat API Test

Esse projeto foi criado para realização do teste técnico solicitado pela empresa Audsat.

## Tech Stack

Java 17, SpringBoot, JUnit, Mockito

## Instalação

Clone esse projeto

```bash
  git clone https://github.com/PedroVasconcelos18/audsat-test
```

Após isso, abra o terminal e rode o comando abaixo para installar todas as dependências necessárias para rodar o projeto.

```bash
  ./gradlew clean build
```
    
## Rodando localmente

Para iniciar o servidor, execute o comando abaixo ou abra a classe AudsattestApplication e dê um run na classe main()

```bash
   ./gradlew bootRun
```

## Rodando os testes unitários

Esses testes sao para verificar a funcionalidade das services e das classes utilitárias.

```bash
  ./gradlew test
```

## Dicas rápidas

 Para facilitar os testes e o desenvolvimento deixei criado alguns campos já criados dentro do sistema.

Entre eles temos 3 customers com id's sequenciais (1, 2 e 3) e 3 carros na mesma lógica.

Caso queria saber mais sobre o que está pré-criado pode acessar o banco de dados, assim que a aplicação estiver rodando acesse a URL localhost:8080/h2-console/login.jsp 

- JDBC URL: jdbc:h2:mem:audsatDB

- User name: sa

- Pass: 

## Rotas disponíveis ( localhost:8080/ ) 

#### POST /insurance/budget

Tem que ser passado um body, contento os atributos de customerId e cardId, esses valores devem existir

#### GET /insurance/budget/{insuranceId}

Tem que ser passado um id existente na URL (PathVariable) para obtér um retorno positivo. Essa rota irá retornar algumas infomações a respeito do seguro jutamente com o valor da FIPE e o orçamento calculado.

#### PUT /insurance/budget/{insuranceId}

Tem que ser passado um id existente na URL e um body, contento os atributos de customerId, cardId ou isActive, esses valores devem existir porém não são obrigatórios de serem enviados.

#### DELETE /insurance/budget/{insuranceId}

Tem que ser passado um id existente na URL.

# Diagrama da arquitetura

A arquitetura da aplicação está divida entre:

- controller
- domain
- dto
- exceptions
- repository
- service
- utils

### Controller:
No projeto, os controllers são responsáveis por lidar com as solicitações HTTP. Eles mapeiam as solicitações para um serviço adequado. Por exemplo, o InsuranceController é um controller que gerencia as operações relacionadas aos seguros.  

### Domain:
 O domínio é onde os objetos de negócios ou entidades são definidos. Estes são os objetos que representam os conceitos do mundo real que o aplicativo gerencia. Por exemplo, Insurances é um objeto de domínio que representa um seguro.  

### DTO (Data Transfer Object):
DTOs são objetos simples que são usados para transportar dados entre processos. Eles são usados para agrupar vários dados em um único objeto para serem enviados em uma única chamada. Por exemplo, CreateInsuranceDTO, InsuranceDTO, InsuranceUpdatedDTO e UpdateInsuranceDTO são DTOs usados para transferir dados de e para o InsuranceController.  


### Exceptions: 
As exceções são usadas para indicar condições excepcionais que um aplicativo deve capturar. No projeto, podem ser definidas classes de exceção personalizadas para lidar com erros específicos.  

### Repository: 
Os repositórios são interfaces que permitem a manipulação de objetos de domínio. Eles fornecem operações como salvar, buscar, atualizar e deletar. Em uma aplicação Spring Boot, os repositórios geralmente estendem a interface JpaRepository ou CrudRepository.  

### Service: 
Os serviços contêm a lógica de negócios principal do aplicativo. Eles são chamados pelos controllers e podem chamar os repositórios para acessar os dados. Por exemplo, InsuranceService é um serviço que gerencia a lógica de negócios relacionada aos seguros.  

### Utils:
Utils são classes que contêm métodos estáticos que são comumente usados em toda a aplicação. Eles fornecem funcionalidades utilitárias como manipulação de strings, operações matemáticas, etc.
