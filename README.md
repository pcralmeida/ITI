# Exercício de Candidatura à Bolsa de Iniciação à Investigação BL204 (2022)


Paulo Almeida, 6 de Novembro de 2022.

Consultar o [enunciado](/enunciado.md).

### Programas e dependências necessárias

Antes de correr o código, é necessário que o sistema utilizado contenha Java JDK (recomendado v11.0.16 ou acima) e os respetivos _modules_ e aplicações:

-   **Maven Apache Server** (recomendado v3.8.6 ou acima)
-   **Docker** (recomendado v14.13.0 ou acima)
-  **Node Project Manager (npm)** (recomendado v8.19.2 ou acima)

### Correr Servidor de Desenvolvimento Local

1. O primeiro passo será montar a _database_ utilizando as funcionalidades do **Docker**. Na diretoria `/database`, correr o comando:

> `sudo docker-compose up -d --build`

Com a _database_ a funcionar, passaremos para o segundo passo. No entanto, podemos decidir parar o funcionamento utilizando o comando (ainda na diretoria `/database`):

> `sudo docker-compose down`

2. O segundo passo será montar o _backend_ do projeto. Sendo o _backend_ uma aplicação que utiliza **SpringBoot _framework_**, utilizaremos o **Maven** na diretoria  `/backend` correndo o comando:

> `mvn clean spring-boot:run`

Podemos utilizar Ctrl-C a qualquer momento para terminar a execução.

3. O terceiro passo será montar o _frontend_ do projeto, que consiste numa aplicação que utiliza **Vue _framework_**. Utilizaremos **npm** para tal, correndo o seguinte comando na diretoria  `/frontend`:

> `npm run start`

Este comando irá criar uma nova pasta (`/frontend/node_modules`), que contém dependências necessárias para o funcionamento da aplicação. Para terminar, utilizamos Ctrl-C.

4.  Tendo completado todos os passos anteriores, podemos agora aceder ao endereço [http://127.0.0.1:8081/]() através de qualquer _browser_.  


_Nota: caso seja preferido mudar a port do servidor local, tal pode ser feito dirigindo-se a `/frontend`  e editando no ficheiro `.env` a variável `VUE_APP_ROOT_API` para a port desejada. Será também importante perceber que o projeto não está apto para produção._

---

<details>
<summary>Escolhas de Implementação</summary>

1.  Para o aviso da empresa de _catering_ sobre os pratos que têm de ser fornecidos, foi escolhido uma abordagem explícita por parte do utilizador. O utilizador deverá então dirigir-se à secção de pedidos e inserir os pratos a fornecer, em conjunto com o dia a ser entregue.     

2.  Neste projeto há funcionalidades não implementadas, como é o caso da consulta de uma sessão numa página exclusiva<sup id="a1">[1](#fn1)</sup>.

3.  Com vista a melhorar a UX do utilizador, foram feitas algumas mudanças no que toca a _icons_ (foi tomado a liberdade de passar a usar [material icons](https://materialdesignicons.com/)) e a outros casos, como a mudança do aspeto da _landing page_.

</details>

---

<b id="fn1"><sup>1</sup></b> Devido a fatores de tempo e de familiaridade com a tecnologia.
