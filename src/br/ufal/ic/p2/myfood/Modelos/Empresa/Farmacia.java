package br.ufal.ic.p2.myfood.Modelos.Empresa;

public class Farmacia extends Empresa{
    private boolean abre24h;
    private int numFuncionario;

    // Construtor padrão necessário para XMLDecoder
    public Farmacia() {}
    public Farmacia(String tipoEmpresa, int dono, int id, String nome, String endereco, boolean abre24h, int numFuncionario) {
        super(tipoEmpresa, dono, id, nome, endereco, EnumEmpresa.FARMACIA);
        this.abre24h = abre24h;
        this.numFuncionario = numFuncionario;
    }

    public boolean isAbre24h() {
        return abre24h;
    }
    public void setAbre24h(boolean abre24h) {
        this.abre24h = abre24h;
    }
    public int getNumFuncionario() {
        return numFuncionario;
    }
    public void setNumFuncionario(int numFuncionario) {
        this.numFuncionario = numFuncionario;
    }
}
