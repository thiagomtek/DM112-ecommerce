package com.br.inatel.pos.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.br.inatel.pos.ecommerce.interfaces.FormaDePagamento;
import com.br.inatel.pos.ecommerce.model.CartaoCredito;
import com.br.inatel.pos.ecommerce.model.Pedido;
import com.br.inatel.pos.ecommerce.model.Pix;
import com.br.inatel.pos.ecommerce.model.Produto;

public class Ecommerce {
	private static List<Pedido> pedidosCriados = new ArrayList<>();
	private static List<Produto> produtosCadastrados = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Cadastro de produto mantido em memória pois não é requisito manter em bacno
		System.out.println("Cadastro de Produto:");
		while (true) {
			System.out.print("Digite o nome (ou 'fim' para voltar): ");
			String nome = scanner.nextLine();
			if (nome.equalsIgnoreCase("fim")) {
				break;
			}

			System.out.print("Digite o preço: ");
			double preco = Double.parseDouble(scanner.nextLine());

			System.out.print("Digite a categoria: ");
			String categoria = scanner.nextLine();

			Produto produto = new Produto(nome, preco, categoria);
			produtosCadastrados.add(produto);
			System.out.println("Produto cadastrado com sucesso!");
		}

		boolean continuar = true;
		while (continuar) {
			System.out.println("\nEscolha a opção:");
			System.out.println("1. Criar novo pedido");
			System.out.println("2. Listar pedidos");
			System.out.println("3. Listar produtos");
			System.out.println("4. Sair");
			System.out.print("Opção: ");
			int opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao) {
			case 1:
				criarPedido(scanner);
				break;
			case 2:
				listarPedidos();
				break;
			case 3:
				listarProdutos();
				break;
			case 4:
				continuar = false;
				System.out.println("Sistema finalizado.");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}

		scanner.close();
	}

	private static void criarPedido(Scanner scanner) {
		List<Produto> produtosPedido = new ArrayList<>();
		System.out.println("\nCriação de Pedido:");
		while (true) {
			listarProdutos();
			System.out.print("Digite o número do produto para adicionar ao pedido (ou 'fim' para voltar): ");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("fim")) {
				break;
			}

			int produtoIndex = Integer.parseInt(input) - 1;
			if (produtoIndex >= 0 && produtoIndex < produtosCadastrados.size()) {
				produtosPedido.add(produtosCadastrados.get(produtoIndex));
				System.out.println("Produto adicionado ao pedido!");
			} else {
				System.out.println("Produto inválido!");
			}
		}

		// Seleção da forma de pagamento não adicionado dinheiro pois não é requisito
		System.out.println("\nSelecione a forma de pagamento:");
		System.out.println("1. Cartão de Crédito");
		System.out.println("2. Pix");
		int opcaoPagamento = Integer.parseInt(scanner.nextLine());
		FormaDePagamento formaPagamento = null;

		if (opcaoPagamento == 1) {
			System.out.print("Digite o número do cartão de crédito: ");
			String numeroCartao = scanner.nextLine();
			formaPagamento = new CartaoCredito(numeroCartao);
		} else if (opcaoPagamento == 2) {
			System.out.print("Digite a chave Pix: ");
			String chavePix = scanner.nextLine();
			formaPagamento = new Pix(chavePix);
		}

		Pedido pedido = new Pedido(formaPagamento);
		for (Produto p : produtosPedido) {
			pedido.adicionarProduto(p);
		}

		pedido.finalizarPedido();
		pedidosCriados.add(pedido);
	}

	private static void listarPedidos() {
		if (pedidosCriados.isEmpty()) {
			System.out.println("\nNenhum pedido foi criado ainda.");
			return;
		}

		System.out.println("\nLista de pedidos criados:");
		for (int i = 0; i < pedidosCriados.size(); i++) {
			Pedido pedido = pedidosCriados.get(i);
			System.out.println("Pedido " + (i + 1) + " - Total: R$" + String.format("%.2f", pedido.calculaTotal()));
		}
	}

	private static void listarProdutos() {
		if (produtosCadastrados.isEmpty()) {
			System.out.println("\nNenhum produto cadastrado.");
			return;
		}

		System.out.println("\nLista de produtos Padastrados:");
		for (int i = 0; i < produtosCadastrados.size(); i++) {
			Produto p = produtosCadastrados.get(i);
			System.out.println((i + 1) + ". " + p.getNome() + " - R$" + String.format("%.2f", p.getPreco()) + " ("
					+ p.getCategoria() + ")");
		}
	}

}
