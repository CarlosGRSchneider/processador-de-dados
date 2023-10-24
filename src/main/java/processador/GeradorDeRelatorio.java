package processador;

import java.util.List;

public class GeradorDeRelatorio {

    public static String geraRelatorio(List<Dado> dadosPorProduto, Dado dadosTotais) {

        StringBuilder sb = new StringBuilder("Relatorio para os produtos vendidos.");
        sb.append("\n-------------------------------------------------\n");

        sb.append("Balanço por produto:\n");
        dadosPorProduto.forEach(dado -> adicionaDadosNoRelatorio(dado, sb));

        sb.append("Balanço total:\n ");
        adicionaDadosNoRelatorio(dadosTotais, sb);

        return sb.toString();
    }

    private static void adicionaDadosNoRelatorio(Dado dado, StringBuilder sb) {

        sb.append(dado.getNome() + "\n\n");
        sb.append("Receita total: " + dado.getReceita() + "\n");
        sb.append("Lucro medio: " + dado.getLucroMedio() + "\n");
        sb.append("Dia com o maior lucro: " + dado.getDiaDoMaiorLucro() + "\n");
        sb.append("Dias com lucro negativo: " + dado.getDiasComLucroNegativo() + "\n");
        sb.append("Valor medio de venda: " + dado.getValorMedioDeVenda() + "\n");
        sb.append("Dia do maior valor de venda: " + dado.getDiaDoMaiorValorVenda() + "\n");
        sb.append("Valor medio de compra: " + dado.getValorMedioDeCompra() + "\n");
        sb.append("Dia do maior valor de compra: " + dado.getDiaDoMaiorValorCompra() + "\n");
        sb.append("Média de vendas: " + dado.getQuantidadeMediaVendida() + "\n");
        sb.append("Dia com mais vendas: " + dado.getDiaDeMaisUnidadesVendidas() + "\n");
        sb.append("Média de perdas: " + dado.getQuantidadeMediaPerdida() + "\n");
        sb.append("Dia com mais perdas: " + dado.getDiaDeMaisUnidadesPerdidas() + "\n");

        sb.append("\n-------------------------------------------------\n");
    }
}
