package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EmpresaNaoExisteException extends Exception {
    public EmpresaNaoExisteException() {
        super("Nao existe empresa com esse nome");
    }
}
