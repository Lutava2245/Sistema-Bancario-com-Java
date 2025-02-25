package operacoes;

public class Operacao {
    private float valor;
    
    public Operacao(float valor) {
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "\n" + this.getClass().getSimpleName() + " no valor de R$" + valor;
    }
}
