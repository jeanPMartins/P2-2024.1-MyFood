package br.ufal.ic.p2.myfood.Modelos.Exception;

public class IndiceMaiorException extends RuntimeException {
    public IndiceMaiorException() {
        super("Indice maior que o esperado");
    }
}
