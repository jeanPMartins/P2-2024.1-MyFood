package br.ufal.ic.p2.myfood.Modelos.Exception;

public class ProdutoJaExiste extends RuntimeException {
  public ProdutoJaExiste() {
    super("Ja existe um produto com esse nome para essa empresa");
  }
}
