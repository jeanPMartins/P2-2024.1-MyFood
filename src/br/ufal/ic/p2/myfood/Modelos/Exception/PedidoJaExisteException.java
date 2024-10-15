package br.ufal.ic.p2.myfood.Modelos.Exception;

public class PedidoJaExisteException extends RuntimeException {
    public PedidoJaExisteException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
