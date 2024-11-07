import java.util.ArrayList;

public class Estacionamento {
    private ArrayList<Veiculo> listaDeVeiculo = new ArrayList<>();
    private ArrayList<Vaga> listaDeVaga = new ArrayList<>();

    public Estacionamento() {
    }

    public Estacionamento(ArrayList<Veiculo> listaDeVeiculo, ArrayList<Vaga> listaDeVaga) {
        this.listaDeVeiculo = listaDeVeiculo;
        this.listaDeVaga = listaDeVaga;
    }

    public ArrayList<Veiculo> getListaDeVeiculo() {
        return listaDeVeiculo;
    }

    public void setListaDeVeiculo(ArrayList<Veiculo> listaDeVeiculo) {
        this.listaDeVeiculo = listaDeVeiculo;
    }

    public ArrayList<Vaga> getListaDeVaga() {
        return listaDeVaga;
    }

    public void setListaDeVaga(ArrayList<Vaga> listaDeVaga) {
        this.listaDeVaga = listaDeVaga;
    }

    @Override
    public String toString() {
        return "Estacionamento{" +
                "listaDeVeiculo=" + listaDeVeiculo +
                ", listaDeVaga=" + listaDeVaga +
                '}';
    }
}
