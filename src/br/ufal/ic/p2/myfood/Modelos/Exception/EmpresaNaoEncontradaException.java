package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException() {
        super("Empresa nao encontrada");
    }
}
