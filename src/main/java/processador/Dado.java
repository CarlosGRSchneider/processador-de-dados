package processador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dado {

    private String nome;
    private List<ValorDiario> valores = new ArrayList<>();

    public Dado(String nome) {
        this.nome = nome;
    }

    public void adicionaValorDiario(LocalDate data, double valorCompra, double valorVenda, int unidadesVendidas, int unidadesPerdidas) {
        ValorDiario valorDiario = new ValorDiario(data, valorCompra, valorVenda, unidadesVendidas, unidadesPerdidas);
        valores.add(valorDiario);
    }

    public String getNome() {
        return nome;
    }

    public List<ValorDiario> getValores() {
        return valores;
    }

    public Map<LocalDate, Double> getDiasComLucroNegativo() {

        List<ValorDiario> valoresNegativos = valores.stream()
                .filter(ValorDiario::isLucroNegativo)
                .toList();

        return valoresNegativos.stream()
                .collect(Collectors.toMap(ValorDiario::getData, ValorDiario::getLucro));
    }

    public Map<LocalDate, Double> getDiaDoMaiorLucro() {
        LocalDate dia = null;
        Double lucro = Double.MIN_VALUE;

        for (ValorDiario valor : valores) {
            if (valor.getLucro() > lucro) {
                lucro = valor.getLucro();
                dia = valor.getData();
            }
        }

        return Map.of(dia, lucro);
    }

    public Map<LocalDate, Double> getDiaDoMaiorValorVenda() {
        LocalDate dia = null;
        Double maiorValorVenda = Double.MIN_VALUE;

        for (ValorDiario valor : valores) {
            if (valor.getValorVenda() > maiorValorVenda) {
                maiorValorVenda = valor.getValorVenda();
                dia = valor.getData();
            }
        }

        return Map.of(dia, maiorValorVenda);
    }

    public double getValorMedioDeVenda() {

        return valores.stream().mapToDouble(ValorDiario::getValorVenda).average().orElse(0.0);
    }

    public Map<LocalDate, Double> getDiaDoMaiorValorCompra() {
        LocalDate dia = null;
        Double maiorValorCompra = Double.MIN_VALUE;

        for (ValorDiario valor : valores) {
            if (valor.getValorCompra() > maiorValorCompra) {
                maiorValorCompra = valor.getValorCompra();
                dia = valor.getData();
            }
        }

        return Map.of(dia, maiorValorCompra);
    }

    public double getValorMedioDeCompra() {

        return valores.stream().mapToDouble(ValorDiario::getValorCompra).average().orElse(0.0);
    }

    public Map<LocalDate, Integer> getDiaDeMaisUnidadesVendidas() {
        LocalDate dia = null;
        Integer maisUnidadesVendidas = Integer.MIN_VALUE;

        for (ValorDiario valor : valores) {
            if (valor.getUnidadesVendidas() > maisUnidadesVendidas) {
                maisUnidadesVendidas = valor.getUnidadesVendidas();
                dia = valor.getData();
            }
        }

        return Map.of(dia, maisUnidadesVendidas);
    }

    public double getQuantidadeMediaVendida() {

        return valores.stream().mapToInt(ValorDiario::getUnidadesVendidas).average().orElse(0.0);
    }

    public Map<LocalDate, Integer> getDiaDeMaisUnidadesPerdidas() {
        LocalDate dia = LocalDate.now();
        Integer maiorValorPerda = 0;

        for (ValorDiario valor : valores) {
            if (valor.getUnidadesPerdidas() > maiorValorPerda) {
                maiorValorPerda = valor.getUnidadesPerdidas();
                dia = valor.getData();
            }
        }

        return Map.of(dia, maiorValorPerda);
    }

    public double getQuantidadeMediaPerdida() {

        return valores.stream().mapToInt(ValorDiario::getUnidadesPerdidas).average().orElse(0.0);
    }

    public double getLucroMedio() {
        return valores.stream().mapToDouble(ValorDiario::getLucro).average().orElse(0.0);
    }

    public double getReceita() {

        return valores.stream()
                .mapToDouble(valor -> (valor.getValorVenda() - valor.getValorCompra()) * valor.getUnidadesVendidas())
                .sum();
    }
}
