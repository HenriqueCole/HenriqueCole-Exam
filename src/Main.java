import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o número de produtos a serem cadastrados: ");
        int numProdutos = sc.nextInt();

        String[] nomeProdutos = new String[numProdutos];
        double[] precoProdutos = new double[numProdutos];
        int[][] vendasProdutos = new int[numProdutos][30];
        int[] totalVendasProdutos = new int[numProdutos];
        int[] totalVendasDiarias = new int[30];

        for (int i = 0; i < numProdutos; i++) {
            System.out.println("Digite o nome do produto " + (i + 1) + ": ");
            nomeProdutos[i] = sc.next();
            System.out.println("Digite o preço de venda do produto " + (i + 1) + ": ");
            precoProdutos[i] = sc.nextDouble();
        }
        preencherVendasProdutos(vendasProdutos, nomeProdutos, sc);

        totalVendasProdutos = calcularTotalVendasProdutos(vendasProdutos);
        totalVendasDiarias = calcularTotalVendasDiarias(vendasProdutos);

        exibirRelatorioVendas(nomeProdutos, precoProdutos, vendasProdutos, totalVendasProdutos, totalVendasDiarias);
    }

    private static void preencherVendasProdutos(int[][] vendasProdutos, String[] nomeProdutos, Scanner sc) {
        for (int i = 0; i < vendasProdutos.length; i++) {
            System.out.println("Digite as vendas diárias do produto " + nomeProdutos[i] + ": ");
            for (int j = 0; j < vendasProdutos[i].length; j++) {
                System.out.println("Dia " + (j + 1) + ": ");
                vendasProdutos[i][j] = sc.nextInt();
            }
        }
    }

    private static int[] calcularTotalVendasProdutos(int[][] vendasProdutos) {
        int[] totalVendasProdutos = new int[vendasProdutos.length];
        for (int i = 0; i < vendasProdutos.length; i++) {
            for (int j = 0; j < vendasProdutos[i].length; j++) {
                totalVendasProdutos[i] += vendasProdutos[i][j];
            }
        }
        return totalVendasProdutos;
    }

    private static int[] calcularTotalVendasDiarias(int[][] vendasProdutos) {
        int[] totalVendasDiarias = new int[30];
        for (int i = 0; i < vendasProdutos.length; i++) {
            for (int j = 0; j < vendasProdutos[i].length; j++) {
                totalVendasDiarias[j] += vendasProdutos[i][j];
            }
        }
        return totalVendasDiarias;
    }

    private static String calcularTotalFaturado(int[] totalVendasProdutos, double[] precoProdutos) {
        double totalFaturado = 0;
        for (int i = 0; i < totalVendasProdutos.length; i++) {
            totalFaturado += totalVendasProdutos[i] * precoProdutos[i];
        }
        return String.format("%.2f", totalFaturado);
    }

    private static void exibirRelatorioVendas(String[] nomeProdutos, double[] precoProdutos, int[][] vendasProdutos, int[] totalVendasProdutos, int[] totalVendasDiarias) {
        System.out.println("--- RELATÓRIO DE VENDAS --- ");
        for (int i = 0; i < nomeProdutos.length; i++) {
            System.out.println("-----------------------");
            System.out.println("📋 Produto: " + nomeProdutos[i]);
            System.out.println("💰 Preço: R$" + precoProdutos[i]);
            System.out.println("🛒 Total de vendas: " + totalVendasProdutos[i] + " unidades");
            System.out.println("🔥 Total faturado: R$" + (totalVendasProdutos[i] * precoProdutos[i]));
            System.out.println("-----------------------");

            System.out.println("Vendas diárias do produto " + nomeProdutos[i] + ": ");
            for (int j = 0; j < vendasProdutos[i].length; j++) {
                if (vendasProdutos[i][j] > 1) {
                    System.out.println("Dia " + (j + 1) + ": " + vendasProdutos[i][j] + " unidades" + " - R$" + (vendasProdutos[i][j] * precoProdutos[i]));
                } else {
                    System.out.println("Dia " + (j + 1) + ": " + vendasProdutos[i][j] + " unidade" + " - R$" + (vendasProdutos[i][j] * precoProdutos[i]));
                }
            }
        }

        System.out.println("-----------------------");
        if (nomeProdutos.length > 1) {
            System.out.println("Total de vendas dos " + nomeProdutos.length + " produtos: ");
        } else {
            System.out.println("Total de vendas do produto " + nomeProdutos[0] + ": ");
        }
        for (int i = 0; i < totalVendasDiarias.length; i++) {
            if (totalVendasDiarias[i] > 1) {
                System.out.println("Dia " + (i + 1) + ": " + totalVendasDiarias[i] + " unidades");
            } else {
                System.out.println("Dia " + (i + 1) + ": " + totalVendasDiarias[i] + " unidade");
            }
        }
        System.out.println("-----------------------");
        if (nomeProdutos.length > 1) {
            System.out.println("🔥 Total faturado com os " + nomeProdutos.length + " produtos: R$" + calcularTotalFaturado(totalVendasProdutos, precoProdutos));
        } else {
            System.out.println("🔥 Total faturado com o produto " + nomeProdutos[0] + ": R$" + calcularTotalFaturado(totalVendasProdutos, precoProdutos));
        }
        System.out.println("-----------------------");
        System.out.println("Coded by Henrique Cole. 💻");
    }
}