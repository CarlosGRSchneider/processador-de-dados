package processador;

import java.time.LocalDate;

public class ValorDiario {

    private LocalDate data;
    private double valorCompra;
    private double valorVenda;
    private int unidadesVendidas;
    private int unidadesPerdidas;

    public ValorDiario(LocalDate data, double valorCompra, double valorVenda, int unidadesVendidas, int unidadesPerdidas) {
        this.data = data;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.unidadesVendidas = unidadesVendidas;
        this.unidadesPerdidas = unidadesPerdidas;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public int getUnidadesPerdidas() {
        return unidadesPerdidas;
    }

    public boolean isLucroNegativo() {
        return valorCompra >= valorVenda;
    }

    public Double getLucro() {
        return ((valorVenda / valorCompra) * 100) - 100;
    }
}
