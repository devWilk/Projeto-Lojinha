package model;

public class Pagamento {

    private int id;
    private int tipoPagamento;

    public Pagamento() {
    }


    public Pagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public boolean selecionarPagamento(int tpag) {
        if (tpag == 1 || tpag == 2 || tpag == 3) {
            return true;
        } else {
            return false;
        }

    }

    public void realizarPagamento(Pagamento pag) {
        if (pag.getTipoPagamento() == 1) {
            System.out.println("Pagamento realizado com dinheiro");
        }
        if (pag.getTipoPagamento() == 2) {
            System.out.println("Pagamento realizado com cartão");
        }
        if (pag.getTipoPagamento() == 3) {
            System.out.println("Pagamanto realizado com pix");
        }
    }


    public int getId() {
        return id;
    }

    public int getTipoPagamento() {
        return tipoPagamento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoPagamento(int tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
