package br.ufal.ic.p2.myfood.Modelos.Exception;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException() {
        super("Placa invalido");
    }
}
