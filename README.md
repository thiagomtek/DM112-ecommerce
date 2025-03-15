# Sistema de Pedidos - E-commerce

*******************************************************************
DEVS:                                                             *  
Matheus Vinicius e Thiago Maglioni                                * 
*******************************************************************

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos**, implementado em Java 8.  
O sistema permite o cadastro de produtos, criação de pedidos e finalização com diferentes formas de pagamento.

## Funcionalidades

- Cadastro de produtos em memória.
- Criação de pedidos utilizando os produtos cadastrados.
- Seleção da forma de pagamento (Cartão de Crédito ou Pix).
- Exibição do valor total do pedido e geração de fatura.
- Listagem de produtos e pedidos criados.

## Como executar

1. Compile o projeto:
   ```sh
   javac -d bin src/com/br/inatel/pos/ecommerce/main/Main.java
2. Execute o projeto
   java -cp bin com.br.inatel.pos.ecommerce.main.Main