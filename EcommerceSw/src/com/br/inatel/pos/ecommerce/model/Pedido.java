package com.br.inatel.pos.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import com.br.inatel.pos.ecommerce.interfaces.FormaDePagamento;

public class Pedido {
	private List<Produto> produtos;
	private FormaDePagamento formaPagamento;

	public Pedido(FormaDePagamento formaPagamento) {
		this.produtos = new ArrayList<>();
		this.formaPagamento = formaPagamento;
	}

	public void adicionarProduto(Produto produto) {
		produtos.add(produto);
	}

	public double calculaTotal() {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.getPreco();
		}
		return total;
	}

	public void finalizarPedido() {
		double total = calculaTotal();
		formaPagamento.processaPagamento(total);
		geraFatura(total);
	}

	private void geraFatura(double total) {
		System.out.println("Fatura gerada: Valor Total: R$" + String.format("%.2f", total));
	}
	
	public FormaDePagamento getFormaPagamento() {
		return formaPagamento;
	}
}
