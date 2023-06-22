import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner input;
    static Estoque estoque = new Estoque();
    static double lucro;
    static TableGenerator tableGenerator = new TableGenerator();

    public static void main(String[] args) throws Exception {

        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = formato.format(dataAtual);

        input = new Scanner(System.in);
        limpaConsole();
        System.out.print("===================================");
        System.out.format("\nPadaria do Seu Zé %s\n", dataFormatada);
        System.out.print("===================================\n");

        mostrarMenu();
    }

    public static void mostrarMenu() {
        int valor;
        do {
            System.out.print(
                    "\nOpções: \n1: Cadastrar produto\n2: Excluir produto\n3: Listar produtos\n4: Controle Financeiro\n5: Encerrar programa\n\nDigite: ");
            valor = input.nextInt();

            selecionarOpcaoMenu(valor, estoque);
        } while (valor != 5 && valor != 3);
    }

    public static void selecionarOpcaoMenu(int valor, Estoque e) {
        int id;
        int opcao;

        switch (valor) {
            case 1:
                limpaConsole();
                System.out.print("\n\u001B[1m===================================");
                System.out.print("\nCADASTRAR PRODUTO\n");
                System.out.print("===================================\u001B[0m");
                System.out.print("\nDigite o nome do produto: ");
                input.nextLine();
                String nome = input.nextLine().trim();
                System.out.print("Digite o preço de custo do produto: ");
                double precocusto = input.nextDouble();
                System.out.print("Digite o preço de venda do produto: ");
                double precovenda = input.nextDouble();
                System.out.print("Digite a quantidade do produto: ");
                int quantidade = input.nextInt();

                Produto p = new Produto(nome, precovenda, precocusto, quantidade, true);
                e.cadastrarProduto(p);
                System.out.println("Produto cadastrado!");
                limpaConsole();
                break;
            case 2:
                System.out.print("\n\u001B[31m\u001B[1m===================================");
                System.out.print("\nEXCLUIR PRODUTO\n");
                System.out.print("===================================");
                System.out.print("\n\u001B[0mDigite o ID do produto: ");

                id = input.nextInt();

                System.out.print("\nOpções:\n1: EXCLUIR\n2: CANCELAR\n\nDigite: ");
                opcao = input.nextInt();

                if (opcao == 1) {
                    Produto produto = e.getProdutoPorID(id);
                    if (produto != null) {
                        List<String> headersList = new ArrayList<>();

                        headersList.add("Nome");
                        headersList.add("Quantidade");

                        List<List<String>> rowsList = new ArrayList<>();

                        for (int i = 0; i < 1; i++) {
                            List<String> row = new ArrayList<>();
                            row.add(String.valueOf(produto.nome));
                            row.add(String.valueOf(produto.quantidade));

                            rowsList.add(row);
                        }
                        System.out.println(tableGenerator.generateTable(headersList, rowsList));

                        System.out.print("Digite a quantidade à ser excluída: ");
                        opcao = input.nextInt();

                        if (e.temQtdEstoque(id, opcao)) {
                            if (opcao == produto.quantidade) {
                                e.deletarProduto(id);
                            } else {
                                produto.setQuantidade(produto.quantidade - opcao);
                            }
                        } else {
                            System.out.println("Não há produtos suficientes!");
                        }
                        limpaConsole();
                        System.out.println("Produto deletado!");
                    } else {
                        System.out.println("Não existe um produto com o ID informado.");
                    }
                } else {
                    limpaConsole();
                    System.out.println("Operação cancelada!");
                }

                break;
            case 3:
                limpaConsole();
                if (e.temProdutoNoEstoque()) {
                    System.out.print("\n\u001B[1m===================================");
                    System.out.print("\nListar produtos\n");
                    System.out.print("===================================\u001B[0m");
                    e.listarProdutos();

                    System.out.print("\nDeseja vender um produto?\n\nOpções:\n1: SIM\n2: NÃO\n\nDigite: ");
                    opcao = input.nextInt();

                    if (opcao == 1) {
                        System.out.print("\n\u001B[1m===================================");
                        System.out.print("\nVender produto\n");
                        System.out.print("===================================\u001B[0m\n");
                        System.out.print("Digite o ID do produto: ");
                        id = input.nextInt();

                        Produto produto = e.getProdutoPorID(id);
                        if (produto != null) {

                            List<String> headersList = new ArrayList<>();
                            headersList.add("Nome");
                            headersList.add("Quantidade");

                            List<List<String>> rowsList = new ArrayList<>();

                            for (int i = 0; i < 1; i++) {
                                List<String> row = new ArrayList<>();
                                row.add(String.valueOf(produto.nome));
                                row.add(String.valueOf(produto.quantidade));

                                rowsList.add(row);
                            }
                            System.out.println(tableGenerator.generateTable(headersList, rowsList));

                            System.out.print("Digite a quantidade à ser vendida: ");
                            opcao = input.nextInt(); // quantidade a ser vendida
                            if (e.temQtdEstoque(id, opcao)) {
                                lucro += opcao * (produto.precovenda - produto.precocusto);

                                if (opcao == produto.quantidade) {
                                    produto.setQuantidade(0);
                                    produto.setDisponivel(false);
                                } else {
                                    produto.setQuantidade(produto.quantidade - opcao);
                                }
                                limpaConsole();
                                System.out.print("\u001B[32mProduto vendido com sucesso!\n\u001B[0m");
                            } else {
                                System.out.print("\u001B[31mNão há produtos suficientes!\n\u001B[0m");
                            }
                        } else {
                            System.out.println("Não existe um produto com o ID informado.");

                        }
                    }
                } else {
                    limpaConsole();
                    System.out.println("\u001B[1mO estoque está vazio!\u001B[0m");
                }
                mostrarMenu();
                break;
            case 4:
                limpaConsole();

                List<String> headersList = new ArrayList<>();

                headersList.add("Lucro");

                List<List<String>> rowsList = new ArrayList<>();

                for (int i = 0; i < 1; i++) {
                    List<String> row = new ArrayList<>();

                    row.add("R$ " + String.valueOf(lucro));

                    rowsList.add(row);
                }
                System.out.println(tableGenerator.generateTable(headersList, rowsList));

                break;
            default:
                System.out.println("Encerrando programa...");
                break;
        }
    }

    private static void limpaConsole() {
        System.out.print("\033[H\033[2J"); // limpa console
        System.out.flush(); // limpa console
    }
}
