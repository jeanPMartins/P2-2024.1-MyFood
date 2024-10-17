package br.ufal.ic.p2.myfood.Modelos.Usuario;

public class Entregador extends Usuario {
    String veiculo;
    String placa;

    public Entregador() {}
    public Entregador(int id, String nome, String email, String senha, String endereco, String veiculo, String placa) {
        super(id, nome, email, senha, endereco, TipoUsuario.ENTREGADOR);
        this.veiculo = veiculo;
        this.placa = placa;
    }
    public String getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
