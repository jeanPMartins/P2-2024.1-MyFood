package br.ufal.ic.p2.myfood.Modelos.Exception;

public class EmpresaNaoCadastradaException extends Exception{
    public EmpresaNaoCadastradaException(){
        super("Empresa nao cadastrada");
    }
}
