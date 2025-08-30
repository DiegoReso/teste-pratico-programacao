# Desafio Prático - Sistema de Gestão de Funcionários

Este projeto é a minha solução para o desafio prático de programação em Java. O objetivo foi criar uma aplicação de console para gerenciar uma lista de funcionários, aplicando diversos conceitos de programação orientada a objetos e manipulação de coleções.

### Tecnologias Utilizadas

* **Linguagem de Programação:** Java
* **API de Datas:** `java.time` (LocalDate, Period)
* **Classes Numéricas:** `java.math.BigDecimal` e `java.math.RoundingMode`
* **Estruturas de Dados:** `ArrayList` e `HashMap`
* **Programação Funcional:** Streams API

### Requisitos do Desafio

Abaixo, a lista de todos os requisitos do teste e como foram implementados:

1.  **Criação das Classes `Pessoa` e `Funcionario`:**
    * As classes foram modeladas com herança, com `Funcionario` estendendo `Pessoa`.
    * Atributos como nome, data de nascimento, salário e função foram adicionados, com `LocalDate` e `BigDecimal` para garantir precisão e imutabilidade quando necessário.

2.  **Criação da Classe `Main`:**
    * A classe principal contém a lógica para gerenciar a lista de funcionários.

3.  **Manipulação da Lista de Funcionários:**
    * **3.1 – Inserção:** A lista de funcionários é populada com os dados da tabela, de forma ordenada.
    * **3.2 – Remoção:** O funcionário "João" é removido da lista.
    * **3.3 – Impressão:** A lista de funcionários é exibida em um formato de tabela organizado. A formatação de data e salário segue os padrões especificados.
    * **3.4 – Aumento de Salário:** Um aumento de 10% foi aplicado a todos os funcionários, com a precisão de duas casas decimais garantida por `BigDecimal`.
    * **3.5 – Agrupamento por Função:** Os funcionários foram agrupados em um `Map`, usando a função como chave.
    * **3.6 – Impressão:** A lista de funcionários é impressa novamente, mas agrupada por função.
    * **3.8 – Aniversariantes de Outubro e Dezembro:** A funcionalidade de filtrar e imprimir funcionários por mês de aniversário foi implementada.
    * **3.9 – Maior Idade:** O funcionário com a maior idade é identificado e sua idade é exibida.
    * **3.10 – Ordenação Alfabética:** A lista de funcionários é exibida em ordem alfabética.
    * **3.11 – Total de Salários:** A soma total de todos os salários é calculada e exibida, com precisão monetária.
    * **3.12 – Salários Mínimos:** O número de salários mínimos que cada funcionário recebe foi calculado e exibido, com o valor de `R$ 1.212,00` para o salário mínimo.
---


### Boas Práticas e Estratégias de Código
Para garantir a clareza e a manutenibilidade do código, as seguintes estratégias foram aplicadas:

* **Métodos Estáticos:** Todas as funcionalidades foram implementadas em métodos estáticos na classe `Main`. Essa abordagem facilita a execução sequencial das operações, tornando o fluxo de código claro e direto para um aplicativo de console.
* **Reutilização de Código:** O método `imprimirFuncionarios` foi projetado para ser reutilizado por outras funções. Essa estratégia de código, alinhada com o princípio **DRY** (Don't Repeat Yourself), evita a duplicação de lógica e mantém um padrão de saída consistente em toda a aplicação.

---

### Gerenciamento de Código (Gitflow)

O projeto foi desenvolvido utilizando um fluxo de trabalho organizado com Git, incluindo:

* **Branches com prefixos:** As branches foram nomeadas de forma descritiva, como `feature/` e `refactor/` , para indicar o propósito de cada alteração.
* **Commits claros e semânticos:** Cada commit com a mensagem para criar um histórico de projeto limpo e fácil de entender.
---

### Como Executar o Projeto

Para executar a aplicação, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone git@github.com:DiegoReso/teste-pratico-programacao.git
    cd teste-pratico-programacao
    ```
2.  **Compile e execute a classe `Main`:**
    * Usando um IDE como o IntelliJ ou VSCode por exemplo: Basta abrir o projeto e executar a classe `Main`.
    * Usando o terminal:
        ```bash
        # Compile o código
        javac main/java/com/diegoreso/teste/*.java

        # Execute a classe Main
        java main.java.com.diegoreso.teste.Main
        ```

### Autor

* **Diego Reis** - https://www.linkedin.com/in/diego-reso/ - diegoreso@me.com
