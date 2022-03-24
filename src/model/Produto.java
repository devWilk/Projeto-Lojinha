package model;

public class Produto {

    private int id;
    private String nome;
    private int quantidadeEstoque;
    private double preco;


    public Produto() {
    }

    public Produto(String nome, double preco, int quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;

    }

    public boolean VerificaEstoque(Produto prod) {
        if (quantidadeEstoque > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
