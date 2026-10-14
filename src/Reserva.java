public class Reserva {

    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;

        setNumeroDias(numeroDias);
        setValorDiaria(valorDiaria);
    }

    public Reserva(String nomeHospede, String tipoQuarto) {
        this(nomeHospede, tipoQuarto, 1, 100.0);
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        if (numeroDias < 1) {
            throw new IllegalArgumentException("O número de dias deve ser no mínimo 1.");
        }
        this.numeroDias = numeroDias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("O valor da diária deve ser maior que zero.");
        }
        this.valorDiaria = valorDiaria;
    }

    public double calcularValorTotal() {
        return numeroDias * valorDiaria;
    }

    @Override
    public String toString() {
        return String.format("Hóspede: %s | Quarto: %s | Dias: %d | Diária: R$ %.2f | Total: R$ %.2f",
                nomeHospede, tipoQuarto, numeroDias, valorDiaria, calcularValorTotal());
    }
}
