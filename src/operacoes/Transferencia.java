package operacoes;

import entidades.Conta;

public class Transferencia extends Operacao {
    private Conta conta_destino;
    private float valor;
    
    public Transferencia(Conta conta_destino, float valor) {
        super(valor);
        this.conta_destino = conta_destino;
    }

    @Override
    public String toString() {
        return super.toString() + " para " + conta_destino;
    }
}
