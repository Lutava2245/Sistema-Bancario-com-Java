package entidades;

import java.util.ArrayList;

import operacoes.Operacao;

public class Conta {
    private int numero;
    private float saldo = 0;
    private ArrayList<Operacao> operacoes = new ArrayList<>();

    public Conta(int numero) {
        this.numero = numero;
    }
    
    public float getSaldo() {
        return saldo;
    }

    public int getNumero() {
        return numero;
    }
    
    public ArrayList<Operacao> getOperacoes() {
        return operacoes;
    }

    public void registrarOperacao(Operacao operacao) {
        operacoes.add(operacao);
    }
    
    public boolean sacar(float valor) {
        if (saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque de R$"+valor+" realizado!\nSaldo: R$"+this.saldo);
            return true;
        }
        System.out.println("Não foi possível sacar. Saldo insuficiente.\nSaldo: R$"+this.saldo);
        return false;
    }
    
    public void depositar(float valor) {
        this.saldo += valor;
        System.out.println("Deposito de R$"+valor+" realizado!\nSaldo: R$"+this.saldo);
    }
    
    public boolean transferir(float valor, Conta conta) {
        if (this.sacar(valor)) {
            conta.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " - Nº" + numero;
    }
}
