package br.ufal.ic.p2.myfood.Modelos.Exception;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("Categoria invalido");
    }
}
