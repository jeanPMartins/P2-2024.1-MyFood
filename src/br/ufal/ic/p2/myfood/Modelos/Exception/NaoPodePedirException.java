package br.ufal.ic.p2.myfood.Modelos.Exception;

public class NaoPodePedirException extends RuntimeException {
    public NaoPodePedirException() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
