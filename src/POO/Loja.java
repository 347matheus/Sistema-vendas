package POO;

import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			ArrayList<Produto> lista = new ArrayList<>();
			double fat = 0;

			while (true) {
				System.out.println("\n=======Controle de vendas=======");
				System.out.println("\n digite as opção desejada!\n");
				System.out.println("1. Adicionar produto");
				System.out.println("2. Verificar produto");
				System.out.println("3. Vender produto");
				System.out.println("4. Relatório (histórico de vendas e itens disponíveis)");
				System.out.println("5. Sair");
				System.out.printf("Escolha: ");
				int opt = sc.nextInt();
				sc.nextLine();

				if (opt == 1) {
					adicionarItem(lista, sc);

				}

				else if (opt == 2) {
					consultarItem(lista);

				}

				else if (opt == 3) {
					fat = venderItem(lista, sc, fat);

				}

				else if (opt == 4) {
					mostrarRelatorio(lista, fat);

				}

				else if (opt == 5) {
					System.out.println("Encerrando...");
					break;
				} 
				else {
					System.out.println("Opção inválida...");
					return;
				}

			}
		}

	}

	public static void adicionarItem(ArrayList<Produto> Lista, Scanner sc) {
		System.out.print("\nNome: ");
		String nome = sc.nextLine();

		System.out.print("Preço: ");
		double preco = sc.nextDouble();
		sc.nextLine();

		System.out.print("Em estoque: ");
		int estoque = sc.nextInt();
		sc.nextLine();

		Produto prod = new Produto(nome, preco, estoque);
		Lista.add(prod);

		System.out.println("Produto adicionado!");
	}

	public static void consultarItem(ArrayList<Produto> Lista) {
		for (Produto prod : Lista) {
			System.out.println("\nCódigo: " + Lista.indexOf(prod));
			System.out.println(prod);
			System.out.println(" ");
		}
		
		double maior = 0; 
		double menor = Double.MAX_VALUE;
		
		for (Produto prd : Lista) {
			if(prd.getPrice() > maior) {
				maior = prd.getPrice();
			}
			if(prd.getPrice() < menor) {
				menor = prd.getPrice();
			}
			
		}
		
		System.out.println("Produto de menor valor: "+ menor);
		System.out.println("Produto de maior valor: "+ maior);
		
	}

	public static double venderItem(ArrayList<Produto> lista, Scanner sc, double fat) {
		System.out.print("\nInsira o código do produto para vender: ");
		int cod = sc.nextInt();
		sc.nextLine();

		if (cod < 0 || cod >= lista.size()) {
			System.out.println("Código não encontrado");
		}

		else {
			Produto escolhido = lista.get(cod);
			System.out.println("Produto selecionado -> \n");
			System.out.println(escolhido);

			System.out.print("\nUnidades para vender: ");
			int venda = sc.nextInt();

			if (venda > escolhido.getEstoque()) {
				System.out.println("Quantidade excedida");
			}
			else if (venda <= 0) {
				System.out.println("Quantidade inválida");
			} 
			else {
				int novoestoque = escolhido.getEstoque() - venda;
				escolhido.setEstoque(novoestoque);
				System.out.println("\n" + venda + " " + escolhido.getProduct() + " vendidos(as)");
				System.out.println("Estoque atualizado: " + escolhido.getEstoque());

				double valortot = escolhido.getPrice() * venda;
				fat += valortot;
				System.out.printf("Lucro obtido: R$%.2f", valortot);

			}
		}
		return fat;
	}

	public static void mostrarRelatorio(ArrayList<Produto> lista, double fat) {
		System.out.println(" ");
		
		if (lista.isEmpty()) {
			System.out.println("Nenhum produto vendido até o momento.");
		} 
		else {
			System.out.printf("Faturamento total: %.2f ", fat);
		}
		
		for (Produto relatorio : lista) {
		System.out.println("Itens disponíveis: ");
		System.out.println("\nItem: "+relatorio.getProduct() + "\nQuantidade disponível: "+relatorio.getEstoque());
		System.out.println(" ");
		}
	}
}
