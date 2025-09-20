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

			ArrayList<String> produtos = new ArrayList<String>(); // arraylist para o nome dos produtos
			ArrayList<Double> precos = new ArrayList<Double>(); // arraylist para o preço
			ArrayList<Integer> estoque = new ArrayList<Integer>(); // arraylist para a quantidade de itens
			ArrayList<Integer> histven = new ArrayList<Integer>(); // arraylist para o histórico de vendas
			ArrayList<Double> fat = new ArrayList<Double>();

			double faturamento = 0;

			while (true) {
				System.out.println("\n=======Controle de vendas=======");
				System.out.println("\n digite as opção desejada!\n");
				System.out.println("1. Adicionar produto");
				System.out.println("2. Verificar produto");
				System.out.println("3. Vender produto");
				System.out.println("4. Relatório (histórico de vendas e itens disponíveis)");
				System.out.println("5. Sair");
				System.out.printf("Escolha: ");
				int option = sc.nextInt(); // lê a opção
				sc.nextLine(); // sempre depois de usar um Int ou qualquer outro, nextline para limpar o enter

				if (option == 1) {
					adicionarItem(produtos, precos, estoque, sc); // chama o método adicionar item
				}

				else if (option == 2) {
					consultarItem(produtos, precos, estoque, histven, sc); // chama o método consultar item
				}

				else if (option == 3) {
					venderItem(produtos, precos, estoque, histven, fat, sc); // chama o método vender item
				}

				else if (option == 4) {
					mostrarRelatorio(produtos, precos, histven, fat, faturamento); // chama o método mostrar
																					// relatório
				}

				else if (option == 5) {
					System.out.println("\nencerrando...\n");
					break; // encerra o loop
				}

				else {
					System.out.println("Opção incorreta!\n"); // para não quebrar se outro número for digitado
				}

			}
		}

	}

	public static void adicionarItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque,
			Scanner sc) {
		System.out.print("\nDigite o nome do produto: ");
		String product = sc.nextLine(); // lê uma string = nome do produto

		System.out.print("Insira o valor do item: ");
		double price = sc.nextDouble(); // lê um double = preço do item
		sc.nextLine();

		System.out.print("Insira a quantidade do item em estoque: ");
		int manage = sc.nextInt(); // lê um inteiro = quantidade em estoque
		sc.nextLine();

		produtos.add(product); // adicionar os valores em seu respectivo arraylist
		precos.add(price);
		estoque.add(manage);
		System.out.println("\nProduto cadastrado!");

	}

	public static void consultarItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque,
			ArrayList<Integer> histven, Scanner sc) {

		if (produtos.isEmpty()) {
			System.out.println("\nRetornando ao menu, nenhum produto localizado.\n");
			return;
		}

		System.out.println("\nProdutos disponíveis:\n");

		double maior = 0, menor = Integer.MAX_VALUE;
		int prodmaior = 0, prodmenor = 0;

		for (int i = 0; i < produtos.size(); i++) {

			if (precos.get(i) == 0) {
				maior = precos.get(0);
				menor = precos.get(0);
			}

			else {
				if (precos.get(i) > maior) {
					maior = precos.get(i);
					prodmaior = i;

				}
				if (precos.get(i) < menor) {
					menor = precos.get(i);
					prodmenor = i;
				}

			}

			System.out.print("Item: " + i + ". " + produtos.get(i)); // imprime o código do item que é a posição no loop
																		// e o nome do produto conforme loop
			System.out.printf("\nR$%.2f", precos.get(i)); // preço conforme loop
			System.out.print("\nQuantidade disponível: " + estoque.get(i)); // quantidade conforme loop
			System.out.println("\n");
		}

		System.out.println("Produto de maior valor: " + maior + " - " + produtos.get(prodmaior));
		System.out.println("Produto de menor valor: " + menor + " - " + produtos.get(prodmenor));

		System.out.print("\nDigite o índice para excluir (ou qualquer palavra para voltar): ");

		if (!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("Voltando ao menu...");
		}

		else {
			int indice = sc.nextInt();
			sc.nextLine();

			if (indice < 0 || indice >= produtos.size()) {
				System.out.println("Índice não encontrado");
				return;

			} else {
				produtos.remove(indice);
				System.out.println("Produto removido.");
				return;

			}
		}
	}

	public static void venderItem(ArrayList<String> produtos, ArrayList<Double> precos, ArrayList<Integer> estoque,
			ArrayList<Integer> histven, ArrayList<Double> fat, Scanner sc) {
		System.out.print("\nInsira o código do produto que deseja vender\n " + produtos + ": ");

		int indice = sc.nextInt(); // lê o código do item

		// String venda = sc.nextLine();
		// int indice = produtos.indexOf(venda); //esquema para ler o produto pelo nome
		// caso quisesse

		if (indice <= -1 || indice >= produtos.size()) {
			System.out.println("Produto não encontrado!"); // se o número digitado for menor que -1 OU maior igual o
															// máximo do loop (size é loop + 1), cancela a operação

		} else {
			System.out.println("\nItem: " + produtos.get(indice)); // imprime o item
			System.out.println("Quantidade disponível: " + estoque.get(indice)); // quantidade disponível no loop
			System.out.print("Unidades para vender: "); // unidades disponíveis para vender
			int venda1 = sc.nextInt();
			sc.nextLine(); // limpa o enter

			if (venda1 > estoque.get(indice)) {
				System.out.println("\nnão é possível vender mais unidades que o estoque disponível"); // se a quantidade
																										// de venda for
																										// maior que o
																										// estoque
																										// cancela
			} else if (venda1 <= 0) {
				System.out.println("\nnão é possível vender itens iguais ou inferiores a 0"); // se a quantidade for
																								// menor igual a 0
																								// cancela
			} else {
				int novoestoque = estoque.get(indice) - venda1; // novo estoque = o estoque atual - a quantidade vendida
				estoque.set(indice, novoestoque); // gera a subtração do índice(estoque atual) - novoestoque
				System.out.println("\n" + venda1 + " " + produtos.get(indice) + " vendidos(as)");

				double valorvendido = precos.get(indice) * venda1; // lucro obtido de cada venda
				System.out.printf("lucro obtido: R$%.2f\n", valorvendido);

				fat.add(valorvendido); // arraylist fat adiciona o valor vendido
				histven.add(venda1); // arraylist histven adiciona a quantidade de itens vendida
			}

		}
	}

	public static void mostrarRelatorio(ArrayList<String> produtos, ArrayList<Double> precos,
			ArrayList<Integer> histven, ArrayList<Double> fat, double faturamento) {
		System.out.println("Histórico de vendas:\n");
		System.out.println("Produtos disponíveis: " + produtos); // imprime o arraylist dos produtos
		System.out.print("Unidades vendidas: " + histven); // imprime o arraylist do histórico de vendas

		for (Double valorvendido : fat) { // percorre o array FAT, valorvendido recebe os valores de fat até o final do
											// laço
			faturamento += valorvendido; // Isso continua até que todos os elementos de fat tenham sido percorridos.
		}

		System.out.printf("Faturamento total: R$%.2f\n", faturamento); // imprime o faturamento final
	}
}
