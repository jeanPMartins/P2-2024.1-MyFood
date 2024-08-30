package br.ufal.ic.p2.myfood.Modelos.Empresa;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private String tipoEmpresa;
    private int id;
    private int dono;
    private String nome;
    private String endereco;

    public static Map<String, Empresa> empresasPorNome = new HashMap<>();

    // Construtor padrão necessário para XMLDecoder
    public Empresa() {}
    public Empresa(String tipoEmpresa, int dono, int id, String nome, String endereco) {
        this.dono = dono;
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Empresa(int id, String nome, String endereco) {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDono() {
        return dono;
    }
    public void setDono(int dono) {
        this.dono = dono;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static Restaurante criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException {
        validarEmpresa(dono, nome, endereco);
        if(validarNome(nome)){
            throw new NomeJaExisteException();
        }
        return new Restaurante(dono, nome, endereco, tipoCozinha);
    }
    public static void validarEmpresa(int dono, String nome, String endereco) throws NomeInvalidoException, EnderecoInvalidoException {
        //fazer verificaçao de dono?
        if (nome == null || nome.isEmpty()){
            throw new NomeInvalidoException();
        }
        if (endereco == null || endereco.isEmpty()){
            throw new EnderecoInvalidoException();
        }
    }
    public static boolean validarNome(String nome){
        return empresasPorNome.containsKey(nome);
    }
}

