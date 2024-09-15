package br.ufal.ic.p2.myfood.Modelos.Empresa;

public class Restaurante extends Empresa{
    private String tipoCozinha;

    // Construtor padrão necessário para XMLDecoder
    public Restaurante() {}
    public Restaurante(int dono, int id, String nome, String endereco, String tipoCozinha) {
        super(dono, id, nome, endereco);
        this.tipoCozinha = tipoCozinha;
    }
    public String getTipoCozinha() {
        return tipoCozinha;
    }
    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }
}