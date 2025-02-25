package entidades;

import java.util.ArrayList;

public class Cliente {
    private ArrayList<Conta> contas = new ArrayList<>();
    private String nome;
    private String cpf;
    
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
