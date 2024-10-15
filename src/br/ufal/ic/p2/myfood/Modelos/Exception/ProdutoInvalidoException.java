package br.ufal.ic.p2.myfood.Modelos.Exception;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException() {
        super("Produto invalido");
    }
}
