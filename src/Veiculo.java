import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Veiculo {
    private int placa;
    private String modelo;
    private int tamanho;//1=pequeno,2=médio,3=grande
    private LocalTime checkin;
    private LocalTime checkout;
    private Vaga vaga;

    public Veiculo() {
    }

    public Veiculo(int placa, String modelo, int tamanho, LocalTime checkin, LocalTime checkout, Vaga vaga) {
        this.placa = placa;
        this.modelo = modelo;
        this.tamanho = tamanho;
        this.checkin = null;
        this.checkout = null;
        this.vaga = null;
    }

    public Veiculo(int placa, String modelo, int tamanho) {
        this.placa = placa;
        this.modelo = modelo;
        this.tamanho = tamanho;
        this.checkin = null;
        this.checkout = null;
        this.vaga = null;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public LocalTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalTime checkin) {
        this.checkin = checkin;
    }

    public LocalTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalTime checkout) {
        this.checkout = checkout;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa=" + placa +
                ", modelo='" + modelo + '\'' +
                ", tamanho=" + tamanho +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", vaga=" + vaga +
                '}';
    }
}


