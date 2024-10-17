package br.ufal.ic.p2.myfood.Modelos.Exception;

public class MercadoInvalidoException extends RuntimeException {
    public MercadoInvalidoException() {
        super("Nao e um mercado valido");
    }
}
