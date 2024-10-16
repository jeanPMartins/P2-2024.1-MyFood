package br.ufal.ic.p2.myfood.Modelos.Exception;

public class TipoEmpresaInvalidoException extends RuntimeException {
    public TipoEmpresaInvalidoException() {
        super("Tipo de empresa invalido");
    }
}
