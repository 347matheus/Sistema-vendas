package vendas;

import java.util.ArrayList;
import java.util.Scanner;

/*Crie um Sistema de Controle de Vendas usando Arrays e ArrayList:
🛍️ ArrayList<String> produtos (nome dos produtos)
💰 ArrayList<Double> precos (preço de cada produto)
📦 ArrayList<Integer> estoque (quantidade disponível)
🧾 ArrayList<String> vendas (histórico de vendas)
📊 Calcular: produto mais caro, total em estoque, faturamento
🔍 Buscar produto por nome e verificar disponibilidade */

public class ArraylistVendas {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			ArrayList<String> produtos = new ArrayList<>();
			ArrayList<Double> precos = new ArrayList<>();
			ArrayList<Integer> estoque = new ArrayList<Integer>();
			ArrayList<Integer> histven = new ArrayList<Integer>();
			
			double faturamento = 0;

			while (true) {
				System.out.println("\n====Controle de vendas====");
				System.out.println("\n digite as opção desejada!\n");
				System.out.println("1. Adicionar produto");
				System.out.println("2. Verificar produto");
				System.out.println("3. Vender produto");
				System.out.println("4. Relatório (histórico de vendas e itens disponíveis)");
				System.out.println("5. Sair");
				System.out.printf("Escolha: ");
				int option = sc.nextInt();
				sc.nextLine();

				if (option == 1) {
					adicionarItem(produtos, precos, estoque, sc);
				}

				else if (option == 2) {
					consultarItem(produtos, precos, estoque, histven);
				}

				else if (option == 3) {
					venderItem(produtos, precos, estoque, histven, sc);
				}



				else if (option == 4) {
					mostrarRelatorio(produtos, precos, histven, faturamento);
				}

				else if (option == 5) {
					System.out.println("\nencerrando...\n");
					break;
				}
				
				else {
					System.out.println("Opção incorreta!\n");
				}

			}
		}

	}
	
	public static void adicionarItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque, Scanner sc) {
		System.out.print("\nDigite o nome do produto: ");
		String product = sc.nextLine();

		System.out.print("Insira o valor do item: ");
		double price = sc.nextDouble();
		sc.nextLine();

		System.out.print("Insira a quantidade do item em estoque: ");
		int manage = sc.nextInt();
		sc.nextLine();

		produtos.add(product);
		precos.add(price);
		estoque.add(manage);
		System.out.println("\nProduto cadastrado!");
	}
	
	public static void consultarItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque, ArrayList<Integer> histven) {
		System.out.println("\nProdutos disponíveis:\n");
		for (int i = 0; i < produtos.size(); i++) {
			System.out.print("Item: " + i + ". " + produtos.get(i));
			System.out.printf("\nR$%.2f", precos.get(i));
			System.out.print("\nQuantidade disponível: " + estoque.get(i));
			System.out.println("\n");
		}

	}
	
	public static void venderItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque, ArrayList<Integer> histven, Scanner sc) {
		System.out.println("\nProdutos disponíveis:\n");
		for (int i = 0; i < produtos.size(); i++) {
			System.out.print("Item: " + i + ". " + produtos.get(i));
			System.out.printf("\nR$%.2f", precos.get(i));
			System.out.print("\nQuantidade disponível: " + estoque.get(i));
			System.out.println("\n");
		}
	}
	
	public static void mostrarRelatorio(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> histven, double faturamento) {
		System.out.println("Histórico de vendas:\n");
		System.out.println("Produtos vendidos: " + produtos);
		System.out.print("Unidades vendidas: " + histven);
		System.out.println("\nvalor unitário " + precos);
		System.out.printf("Faturamento total: R$%.2f\n", faturamento);
	}
}
