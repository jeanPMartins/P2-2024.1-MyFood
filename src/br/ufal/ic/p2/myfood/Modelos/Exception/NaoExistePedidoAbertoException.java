package br.ufal.ic.p2.myfood.Modelos.Exception;

public class NaoExistePedidoAbertoException extends RuntimeException {
    public NaoExistePedidoAbertoException() {
        super("Nao existe pedido em aberto");
    }
}
