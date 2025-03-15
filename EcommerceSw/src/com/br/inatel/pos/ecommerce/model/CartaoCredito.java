package com.br.inatel.pos.ecommerce.model;

import com.br.inatel.pos.ecommerce.interfaces.FormaDePagamento;

public class CartaoCredito implements FormaDePagamento {
	// Adicionado supress pois não precisa de get e set
	@SuppressWarnings("unused")
	private String numeroCartao;

	public CartaoCredito(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	@Override
	public void processaPagamento(double valor) {
		System.out.println("Pagamento de R$" + String.format("%.2f", valor) + " realizado com Cartão de Crédito.");
	}
}
