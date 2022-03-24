import dao.PagamentoDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import model.Pagamento;
import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int opcaoMenu;
        int opcaoSN;
        int opcaoSubMenu;

        Produto produto = new Produto();
        Venda venda = new Venda();
        Pagamento pagamento = new Pagamento();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        Scanner scan = new Scanner(System.in);
        ArrayList<Produto> listproduto = new ArrayList<>();


        opcaoMenu = 0;
        while (opcaoMenu != 4) {
            System.out.println("Qual menu deseja acessar: ");
            System.out.println("1 - VENDA \n 2 - PRODUTO \n 3 - PAGAMENTO\n 4 - SAIR\n ESCOLHA OPÇÃO: ");
            opcaoMenu=scan.nextInt();
            opcaoSubMenu = 0;

            switch (opcaoMenu){
                case 1-> {
                    while (opcaoSubMenu != 4){
                        System.out.println("Escolha uma opção abaixo");
                        System.out.println("1 - INICIAR VENDA\n 2 - VISUALIZAR VENDAS REALIZADAS\n 3 - EXCLUIR VENDA\n OPÇÃO: ");
                        opcaoSubMenu= scan.nextInt();

                        switch (opcaoSubMenu) {
                            case 1 -> {
                                System.out.println("Selecione o produto que deseja adicionar a venda");
                                listaProduto();
                                System.out.println("ID do produto: ");
                                produto.setId(scan.nextInt());
                                venda.adicionarItemVenda(produtoDAO.selecionarProduto(produto));
                                System.out.println("Deseja adicionar outro Produto? \n1 - SIM\n2 - NÃO");
                                opcaoSN = scan.nextInt();
                                while (opcaoSN == 1){
                                    System.out.println("Selecione o produto que deseja adicionar a venda");
                                    listaProduto();
                                    System.out.println("ID do produto: ");
                                    produto.setId(scan.nextInt());
                                    venda.adicionarItemVenda(produtoDAO.selecionarProduto(produto));
                                    System.out.println("Deseja adicionar outro Produto? \n1 - SIM\n2 - NÃO");
                                    opcaoSN = scan.nextInt();
                                }
                                System.out.println("Este são os produtos que foram adicionados á venda:");
                                venda.visualizarVenda();
                                System.out.println("Escolha uma das opções:\n1 - CONCLUIR VENDA\n2 - CANCELAR VENDA");
                                opcaoSN = scan.nextInt();
                                if (opcaoSN == 1){
                                    System.out.println("Escolha forma de pagamento:\n1 - DINHEIRO\n2 - CARTÃO\n3 - PIX");
                                    pagamento.setTipoPagamento(scan.nextInt());
                                    if (pagamento.selecionarPagamento(pagamento.getTipoPagamento())){
                                        vendaDAO.create(venda);
                                        venda.concluirVenda(pagamento);
                                    }else {
                                        cancelarVenda();
                                    }
                                }else cancelarVenda();

                        }
                        case 2 ->  listaVenda();

                        case 3 -> {
                            listaVenda();
                            System.out.println("Digite o codigo da venda que deseja excluir\nID: ");
                            venda.setId(scan.nextInt());
                            vendaDAO.delete(venda);
                        }
                        case 4 -> System.out.println("");

                            default -> System.out.println("Opção invalida!");

                        }
                    }
                }

                case 2 -> {
                    while (opcaoSubMenu != 5){
                        System.out.println("Escolha uma opção abaixo");
                        System.out.println("1 - Adicionar Produto\n 2 - Visualizar Produto\n 3 - Alterar Produto\n4 - Excluir Produto\n OPÇÃO: ");
                        opcaoSubMenu= scan.nextInt();

                        switch (opcaoSubMenu){
                            case 1 -> {
                                System.out.println("Digite o NOME do produto que deseja adicionar a loja:");
                                produto.setNome(scan.next());
                                System.out.println("Digite o PREÇO  do produto:");
                                produto.setPreco(scan.nextFloat());
                                System.out.println("Digite a QUANTIDADE DE ESTOQUE:");
                                produto.setQuantidadeEstoque(scan.nextInt());
                                produtoDAO.create(produto);
                            }
                            case 2 -> {
                                listaProduto();
                            }

                            case 3 -> {
                                listaProduto();
                                System.out.println("Digite o ID do produto que deseja alterar\nID:");
                                produto.setId(scan.nextInt());
                                System.out.println("Digite o valor para o nome:");
                                produto.setNome(scan.next());
                                System.out.println("Digite o valor para o preço");
                                produto.setPreco(scan.nextFloat());
                                System.out.println("Digite o valor para a quantidade de estoque");
                                produto.setQuantidadeEstoque(scan.nextInt());
                                produtoDAO.update(produto);
                            }

                            case 4 -> {
                                listaProduto();
                                System.out.println("Digite o ID do produto que deseja exlcuir\nID:");
                                produto.setId(scan.nextInt());
                                produtoDAO.delete(produto);
                            }

                            case 5 ->  System.out.println("");

                            default -> System.out.println("Opção inválida!");
                        }
                    }
                }

                case 3->{
                    while (opcaoSubMenu != 5){
                        System.out.println("SELECIONE UMA DAS OPÇÕES ABAIXO\n1 - Adicionar Pagamaneto\n2 - Visualizar Pagamento\n3 - Alterar Pagamento\n4 - Exluir Pagamento\n5 - Voltar\nOPÇÃO: ");
                        opcaoSubMenu = scan.nextInt();

                        switch (opcaoSubMenu){
                            case 1 ->{
                                System.out.println("Digite o código do pagamento:");
                                pagamento.setTipoPagamento(scan.nextInt());
                                pagamentoDAO.create(pagamento);
                            }
                            case 2 -> {
                                listarPagamento();
                            }
                            case 3 -> {
                                listarPagamento();
                                System.out.println("Digite o codigo do pagamento que deseja alterar\nID: ");
                                pagamento.setId(scan.nextInt());
                                System.out.println("Digite o valor para o tipo de pagamento:");
                                pagamento.setTipoPagamento(scan.nextInt());
                                pagamentoDAO.update(pagamento);
                            }
                            case 4 -> {
                                listarPagamento();
                                System.out.println("Digite o codigo do pagamento que deseja EXCLUIR\nID: ");
                                pagamento.setId(scan.nextInt());
                                pagamentoDAO.delete(pagamento);
                            }
                            case 5 -> System.out.println("");

                            default -> System.out.println("Opção inválida!");
                        }

                    }
                }

                case 4 -> System.out.println("Encerrando sistema...");

                default -> System.out.println("Opção invalida!\nEscolha outra opção.");

            }
        }


    }
    public static void listaProduto(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> listaProduto = new ArrayList<>();

        listaProduto = produtoDAO.read();
        System.out.println("Produtos cadastrado");
        for (int cont = 0; cont < listaProduto.size(); cont++){
            System.out.println("ID: "+ listaProduto.get(cont).getId() + " | Produto: " + listaProduto.get(cont).getNome()+ " | Preço: " + listaProduto.get(cont).getPreco() + " | Estoque: "+ listaProduto.get(cont).getQuantidadeEstoque());
        }
        System.out.println("_______________________");
    }

    public static void cancelarVenda(){
        Venda venda = new Venda();
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.clear();
        venda.setVlrTotal(0);
        System.out.println("Venda cancelada!");

    }

    public static void listaVenda(){
        VendaDAO vendaDAO = new VendaDAO();
        ArrayList<Venda> listavenda = new ArrayList<>();

        listavenda = vendaDAO.read();
        System.out.println("Vendas Realizadas:");
        for (int cont = 0 ; cont <listavenda.size();cont++){
            System.out.println("ID: "+listavenda.get(cont).getId()+ "| Valor total: "+listavenda.get(cont).getVlrTotal());
        }
    }

    public static void listarPagamento(){
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        ArrayList<Pagamento> listapagamento = new ArrayList<>();

        listapagamento = pagamentoDAO.read();
        System.out.println("Pagamento cadastrado");
        for (int cont = 0;cont < listapagamento.size();cont++){
            System.out.println("ID: "+listapagamento.get(cont).getId()+" | Tipo Pagamento: "+ listapagamento.get(cont).getTipoPagamento());
        }
        System.out.println("______________________");

    }



}
