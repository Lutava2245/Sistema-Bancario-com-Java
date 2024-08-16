package operacoes;

public class Operacao {
    private float valor;
    
    public Operacao(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName() + " no valor de R$" + valor;
    }
}
