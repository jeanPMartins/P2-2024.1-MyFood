package br.ufal.ic.p2.myfood.Modelos.Exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto nao encontrado");
    }
}
