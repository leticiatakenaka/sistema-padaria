public class Produto {
  private static int proximoId = 1;
  int id;
  String nome;
  double precocusto;
  double precovenda;
  int quantidade;
  boolean disponivel;

  Produto(String nome, double precovenda, double precocusto, int quantidade, boolean disponivel) {
    this.id = proximoId;
    proximoId++;
    this.nome = nome;
    this.precovenda = precovenda;
    this.precocusto = precocusto;
    this.quantidade = quantidade;
    this.disponivel = disponivel;
  }

  public static int getProximoId() {
    return proximoId;
  }

  public static void setProximoId(int proximoId) {
    Produto.proximoId = proximoId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getPrecocusto() {
    return precocusto;
  }

  public void setPrecocusto(double precocusto) {
    this.precocusto = precocusto;
  }

  public double getPrecovenda() {
    return precovenda;
  }

  public void setPrecovenda(double precovenda) {
    this.precovenda = precovenda;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public boolean isDisponivel() {
    return disponivel;
  }

  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }

}
