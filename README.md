# Do monólito ao microservice, usando Java no backend

Jornada onde comecei uma aplicação como um monólito, porém não qualquer monólito, um monólito bem feito, bem pensado, para quando começar eu separar ele em microservices, não ter nenhuma dificuldade.

Também tem como objetivo, explorar ferramentas disponíveis e práticas interessantes no Java, Quarkus, Cloud Native e Kubernetes, ferramentas e práticas essas que já é uma realidade no mercado.

## Ferramentas utilizadas

- Java 21
- Quarkus 3.19.1

*para verificar via prompt de comando a versão de uma aplicação Quarkus, rode:*

```bash
mvn quarkus:info
```

*Importante entender que esse comando mostra todas as informações de um projeto Quarkus, versão, extensão etc…*

- Kubernetes

## Sobre o projeto

A ideia principal do projeto é criar uma Agência de Viagens. Existindo um pedido, dentro desse pedido contém voo e hotel.

- pedido
    - voo
    - hotel

Estrutura simples, temos 2 estruturas (voo e hotel) que depende de uma estrutura inicial (pedido). Quando for quebrar tudo isso, teremos 3 microservices, também teremos a comunicação entre eles, dados trocados entre eles, permitindo o uso de mecanismo de garantia de disponibilidade, tolerância a falhas e etc…

## O que é necessário pra rodar essa aplicação localmente?

- Maven
- JDK 21

## Extensões do monólito Travel App

- REST [quarkus-rest]
    
    Uma coisa interessante, o engine do Quarkus roda em cima do engine do Vert.x, sendo o Vert.x uma engine de execução reativa, porém podemos usar o Vert.x tanto reativo quanto imperativo. Inclusive boa parte da perfomance do Quarkus vem da execução que ele faz em cima do Vert.x. Várias APIs do Quarkus vem sendo migrada para o modelo reativo, ainda que seja usado o modelo imperativo. Ou seja, pode usar as APIs ou extensões reativas, ainda que use código imperativo, porque o Quarkus manipula e perfoma melhor.
    
- Hibernate ORM with Panache [quarkus-hibernate-orm-panache]
    
    Sobre Hibernate:
    
    - Hibernate é o maior projeto de persistência
    - Hibernate é complexo para lidar com coisas complexas
    
    Quando o Quarkus foi criado, foi criado também o Panache, que é o Hibernate simplificado. Como o objetivo é simplificar esse projeto, sendo que, não é um projeto para produção, vamos utilizar o Panache, sendo que ele oferece facilidades.
    
- JDBC Driver - H2 [quarkus-jdbc-h2]
    
    No começo foi utilizado o H2, que é um banco de dados em memória, depois foi migrado para o postgre.
    
- REST JSON-B [quarkus-rest-jsonb]
    
    Permite que os objetos Java sejam automaticamente convertidos para JSON e vice-versa quando usados em endpoints REST, sem necessidade de configurações adicionais.

## Comandos úteis

Como desenvolvedores, devemos ter o domínio do terminal, nem que seja comandos básicos, por dois motivos:

- As vezes é a única ferramenta que você tem
- Nem sempre teremos as ferramentas que desejamos

Então aqui está alguns comandos muito úteis:

### Descompactar um zip:

```bash
unzip travel-app2.zip
```

### Remover o .zip:

```bash
 rm -rf travel-app2.zip
```

### Entrar no diretório do projeto Quarkus:

```bash
cd travel-app2
```

### Rodar uma aplicação Quarkus:

```bash
mvn quarkus:dev
```

Esse comando sobe uma aplicação Quarkus. Uma coisa interessante sobre o Quarkus, o Quarkus possui um developer mode, modo desenvolvedor é uma funcionalidade que o Quarkus oferece em tempo de desenvolvimento, que oferece vários recursos que só tem acesso em desenvolvimento, que oferece também muita produtividade.

### Testar se aplicação está no ar:

```bash
curl localhost:8080/hello
```

Obviamente esse comando só vai funcionar se tiver um endpoint, como esse: 

```java
package org.leoalmeida;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("hello")
public class Teste {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Estou funcionando :D";
    }
}
```

### Como fazer uma requisição POST

```bash
curl -d "{}" -H "Content-Type: application/json" http://localhost:8080/travelorder
```

`-d` 

Significa que estamos mandando dados, mais específico para método POST. Inclusive nesse teste não estamos mandando nada `“{}”`.

`-H "Content-Type: application/json"`

Significa que é o header da requisição