import java.util.ArrayList;
import java.util.List;

public class Estoque {
  private ArrayList<Produto> produtos;

  public Estoque() {
    this.produtos = new ArrayList<Produto>();
  }

  public ArrayList<Produto> getProdutos() {
    return this.produtos;
  }

  public void listarProdutos() {

    TableGenerator tableGenerator = new TableGenerator();
    List<String> headersList = new ArrayList<>();
    headersList.add("ID");
    headersList.add("Nome");
    headersList.add("Preço custo");
    headersList.add("Preço venda");
    headersList.add("Disponível");
    headersList.add("Quantidade");

    List<List<String>> rowsList = new ArrayList<>();

    for (int i = 0; i < produtos.size(); i++) {
      List<String> row = new ArrayList<>();

      row.add(String.valueOf(produtos.get(i).id));
      row.add(produtos.get(i).nome);
      row.add("R$ " + String.valueOf(produtos.get(i).precocusto));
      row.add("R$ " + String.valueOf(produtos.get(i).precovenda));
      row.add(String.valueOf(produtos.get(i).disponivel));
      row.add(String.valueOf(produtos.get(i).quantidade));

      rowsList.add(row);
    }
    System.out.println(tableGenerator.generateTable(headersList, rowsList));
  }

  public Produto getProdutoPorID(int id) {
    for (int i = 0; i < this.produtos.size(); i++) {
      if (this.produtos.get(i).id == id) {
        return this.produtos.get(i);
      }
    }

    return null;
  }

  public boolean temQtdEstoque(int id, int qtd) {
    Produto p = getProdutoPorID(id);
    return qtd <= p.quantidade;
  }

  public void cadastrarProduto(Produto p) {
    this.produtos.add(0, p);
  }

  public void deletarProduto(int id) {
    Produto p = getProdutoPorID(id);
    this.produtos.remove(p);
  }

  public boolean temProdutoNoEstoque() {
    return produtos.size() >= 1;
  }
}
