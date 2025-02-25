# Sistema Bancário com Java

Este pequeno projeto simula um sistema de transações bancárias como saques, depósitos e visualização de extratos.

## Tecnologias

* Java 21
* Mermaid (Diagrama UML)

## Diagrama de Classes

```mermaid
classDiagram
    class Cliente {
        -List~Conta~ contas
        -String nome
        -String cpf
    }

    class Conta {
        -int numero
        -double saldo = 0
        -List~Transacao~ operacoes
        +registrarOperacao(Operacao operacao) void
        +sacar(float valor) boolean
        +depositar(float valor) void
        +transferir(float valor, Conta conta) boolean
    }

    class ContaCorrente {

    }

    class ContaPoupanca {

    }

    class Operacao {
        -float valor
    }

    class Saque {
    }
    
    class Deposito {
    }

    class Transferencia {
        -Conta conta_destino
    }

    ContaCorrente <|-- Conta
    Cliente "1" *-- "0..2" Conta
    ContaPoupanca <|-- Conta
    Conta "1" -- "*" Operacao
    Operacao --|> Saque
    Operacao --|> Deposito
    Operacao --|> Transferencia
```

## Contato

* Luan T. Felix - [lutavares.bros@gmail.com](mailto:lutavares.bros@gmail.com)
* [LinkedIn](https://www.linkedin.com/in/luan-tavares-felix-24273a289/)