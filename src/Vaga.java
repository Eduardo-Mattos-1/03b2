public class Vaga {
    private int numero;
    private int tamanho; //1=pequeno,2=médio,3=grande
    private boolean ocupada = false;

    public Vaga() {
    }

    public Vaga(int numero, int tamanho, boolean ocupada) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "numero=" + numero +
                ", tamanho=" + tamanho +
                ", ocupada=" + ocupada +
                '}';
    }
}
