package br.ufal.ic.p2.myfood.Modelos.Empresa;

public class Mercado extends Empresa{
    private String abre;
    private String fecha;
    private String tipoMercado;

    // Construtor padrão necessário para XMLDecoder
    public Mercado() {}
    public Mercado(String tipoEmpresa, int dono, int id, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        super(tipoEmpresa, dono, id, nome, endereco, EnumEmpresa.MERCADO);
        this.abre = abre;
        this.fecha = fecha;
        this.tipoMercado = tipoMercado;
    }

    public String getAbre() {
        return abre;
    }
    public void setAbre(String abre) {
        this.abre = abre;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getTipoMercado() {
        return tipoMercado;
    }
    public void setTipoMercado(String tipoMercado) {
        this.tipoMercado = tipoMercado;
    }
}