package br.ufal.ic.p2.myfood.Modelos.Exception;

public class NaoEntregadorException extends RuntimeException {
    public NaoEntregadorException() {
        super("Usuario nao e um entregador");
    }
}
