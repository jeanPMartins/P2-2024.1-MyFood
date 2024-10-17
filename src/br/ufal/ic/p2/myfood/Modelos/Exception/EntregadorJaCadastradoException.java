package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EntregadorJaCadastradoException extends RuntimeException {
    public EntregadorJaCadastradoException() {
        super("O entregador já está cadastrado nesta empresa.");
    }
}
