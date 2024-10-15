package br.ufal.ic.p2.myfood.Modelos.Exception;

public class ProdutoNaoCadastrado extends RuntimeException {
    public ProdutoNaoCadastrado() {
        super("Produto nao cadastrado");
    }
}
