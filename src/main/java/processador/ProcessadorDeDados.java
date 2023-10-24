package processador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorDeDados {

    public String processaDados(List<String> arquivoCSV) {

        String cabecalho = arquivoCSV.get(0);
        List<Dado> dadosPorProduto = geraDadosComCabecalho(cabecalho);
        Dado dadosTotais = new Dado("Total");

        for (int i = 1; i < arquivoCSV.size(); i++) {
            carregaValoresDiariosDeProduto(dadosPorProduto, dadosTotais, arquivoCSV.get(i));
        }

        return GeradorDeRelatorio.geraRelatorio(dadosPorProduto, dadosTotais);

    }

    private List<Dado> geraDadosComCabecalho(String cabecalho) {

        List<Dado> result = new ArrayList<>();
        String[] dadosCabecalho = cabecalho.split(";");
        for (int i = 0; i < dadosCabecalho.length; i += 4) {
            String nomeProduto = dadosCabecalho[i].substring(10);
            Dado dado = new Dado(nomeProduto);
            result.add(dado);
        }

        return result;
    }

    private void carregaValoresDiariosDeProduto(List<Dado> dadosPorProduto, Dado dadosTotais, String dadosDeLinha) {

        int ponteiroDado = 0;
        String[] dadosDiarios = dadosDeLinha.split(";");
        LocalDate data = LocalDate.parse(dadosDiarios[0]);

        double valorCompraTotal = 0;
        double valorVendaTotal = 0;
        int unidadesVendidasTotal = 0;
        int unidadesPerdidasTotal = 0;

        for (int i = 1; i < dadosDiarios.length; i += 4) {
            Dado dado = dadosPorProduto.get(ponteiroDado);

            double valorCompra = Double.parseDouble(dadosDiarios[i].replace(",", "."));
            double valorVenda = Double.parseDouble(dadosDiarios[i + 1].replace(",", "."));
            int unidadesVendidas = Integer.parseInt(dadosDiarios[i + 2]);
            int unidadesPerdidas = Integer.parseInt(dadosDiarios[i + 3]);

            dado.adicionaValorDiario(data, valorCompra, valorVenda, unidadesVendidas, unidadesPerdidas);

            valorCompraTotal += valorCompra;
            valorVendaTotal += valorVenda;
            unidadesVendidasTotal += unidadesVendidas;
            unidadesPerdidasTotal += unidadesPerdidas;

            ponteiroDado++;
        }
        dadosTotais.adicionaValorDiario(data, valorCompraTotal, valorVendaTotal, unidadesVendidasTotal, unidadesPerdidasTotal);
    }
}
