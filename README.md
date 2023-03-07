# SOLUÇÃO PROPOSTA PARA O DESAFIO FLEX PAG BACKEND

O projeto consiste em uma API de agendamento de pagamento. A API possui um CRUD de pagamentos que atualiza os pagamentos agendados todos os dias às 00:00.

Para inicializar o projeto, foi criado um DataInitializer com 3 pagamentos com status PENDING e 1 com status PAID para testes.

## Intruções para uso da aplicação.

Documentação no Swagger: http://localhost:8080/swagger-ui/index.html#/


### GET - (Retorna todos os pagamentos)

http://localhost:8080/payments/




### GET com Parametro PAID | PENDING - (Retorna todos os elementos filtrando o status)

http://localhost:8080/payments/?status={?}




### GET com variável id - (Retorna apenas o elemento com id selecionado, caso não exista, lança um not found)

http://localhost:8080/payments/{id}




### POST - cria um novo pagamento mandando um json no corpo da requisição com valor e data. (Validando dados, caso não sejam validos, lança uma exceção)

http://localhost:8080/payments/

ex:    {
       "date": "2023-03-21T23:32:32",
       "amount": "323"
       }
       
       
       
      
### PUT - atualiza a data e hora do agendamento passando o ID como variavel. Obs: Só é possivel mudar os dados do agendamento caso ele esteja com status PENDING.

http://localhost:8080/payments/{id}

ex:    {
       "date": "2023-03-21T23:32:32"
       }




### DELETE - deleta um agendamento passando o ID como variavel. Obs: Só e possivel deletar agendamento com status PENDING

http://localhost:8080/payments/{id}





-------------------------------------------------------------------------------------------------------------------------------------------------------

<p align="center">
 <img src="https://github.com/jsantos-examples/flexpag-desafio-backend/blob/main/contents/flexpag.png" width="600" alt="Banner Flexpag">
</p>

# 🚀 Desafio backend

Bem-vindo(a). Este é o desafio Back end!

O objetivo deste desafio é avaliar suas habilidades em programação.
Quando concluir o desafio, basta responder o e-mail onde recebeu o link do repositório.
Em seguida, enviaremos o feedback e as instruções dos próximos passos!

Caso tenha alguma dúvida, nós estamos disponíveis para tirá-las.
Bom desafio!

> ⚠️ **É importante que o seu repo esteja público, caso contrário não iremos conseguir avaliar sua resposta**

---

- [🧠 Contexto](#-contexto)
  - [🚰 Fluxo esperado](#-fluxo-esperado)
- [✔️ Critérios de Avaliação](#️-critérios-de-avaliação)
- [:rocket: Instruções](#rocket-instruções)
  - [:notebook: To-do list](#notebook-to-do-list)

# 🧠 Contexto

A Flexpag é uma empresa de tecnologia especializada em soluções digitais de pagamento. Sabendo disso, montamos um desafio que consiste em implementar um serviço de pagamento agendando.

### 🚰 Fluxo esperado

- Quando um agendamento é enviado deve ser registrado como `pending` e retornado o id;
- O usuário deve conseguir consultar o status do agendamento `pending`|`paid`;
- :warning: **Se o pagamento ainda não foi realizado o usuário pode**;
  - Excluir o agendamento;
  - Atualizar a data:hora do agendamento;
  
## ✔️ Critérios de Avaliação

Além dos requisitos levantados acima, iremos olhar para os seguintes critérios durante a correção do desafio:

- Eficiência e simplicidade;

## :rocket: Instruções

Chegou a hora de colocar a mão na massa!

### Aplicação

A aplicação já está com o pre setup default. 

**dependências:**
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-devtools
- h2
- lombok

| componente | porta |
| --------- | ----------- |
| Aplicação  | `8080` |

aplication.yaml foi configurado para apontar para o banco local h2
```
spring:
  datasource:
    driverClassName: org.h2.Driver
    url: jdbc:h2:mem:payment-scheduler
    username: admin
    password: admin
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        show_sql: true
```

### :notebook: To-do list
- [ ] Fazer o fork do projeto
- [ ] Implementar solução
- [ ] Enviar link do projeto

:information_source: _Sinta-se livre para incluir quaisquer observações que achar necessário_

---

_O desafio acima foi cuidadosamente construído para propósitos de avaliação apenas._
