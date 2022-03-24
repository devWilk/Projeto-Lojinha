package model;

import java.util.ArrayList;

public class Venda {

    private int id;
    private float vlrTotal;
    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    public Venda() {
    }

    public void adicionarItemVenda(Produto prod) {
        if (prod.VerificaEstoque(prod)) {
            listaProdutos.add(new Produto(prod.getNome(), prod.getPreco(), prod.getQuantidadeEstoque()));
            this.vlrTotal += prod.getPreco();
            System.out.println("Produto " + prod.getNome() + "adicionado.");
        } else {
            System.out.println("Não foi possivel adicionar o produto " + prod.getNome() + " pois esta zerado o estoque");
        }
        System.out.println("_______________");
    }

    public void visualizarVenda() {
        System.out.println("___________________");
        System.out.println("ITENS DO CARRINHO");
        for (int cont = 0; cont < listaProdutos.size(); cont++) {
            System.out.println("Produto: " + listaProdutos.get(cont).getNome() + " R$ " + listaProdutos.get(cont).getPreco());
        }
        System.out.println("___________________");
    }

    public void concluirVenda(Pagamento pag) {
        System.out.println("___________________");
        System.out.println("Valor total da venda: " + this.vlrTotal);
        pag.realizarPagamento(pag);
        listaProdutos.clear();
        System.out.println("___________________");
    }

    public int getId() {
        return id;
    }

    public float getVlrTotal() {
        return vlrTotal;
    }

    public ArrayList<Produto> getListadeprodutos() {
        return listaProdutos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVlrTotal(float vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public void setListadeprodutos(ArrayList<Produto> listadeprodutos) {
        this.listaProdutos = listadeprodutos;
    }
}
