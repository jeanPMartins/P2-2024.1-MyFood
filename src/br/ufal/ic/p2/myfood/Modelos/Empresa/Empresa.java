package br.ufal.ic.p2.myfood.Modelos.Empresa;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private String tipoEmpresa;
    private int id;
    private int dono;
    private String nome;
    private String endereco;

    public static Map<String, Empresa> empresasPorNome = new HashMap<>();
    public static Map<String, Empresa> empresasPorEndereco = new HashMap<>();
    public static Map<Integer, Empresa> empresasPorDono = new HashMap<>();

    // Construtor padrão necessário para XMLDecoder
    public Empresa() {}
    public Empresa(String tipoEmpresa, int dono, int id, String nome, String endereco) {
        this.dono = dono;
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Empresa(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
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

    public static Restaurante criarEmpresa(String tipoEmpresa, int id, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException {
        validarEmpresa(dono, nome, endereco);
        Restaurante restaurante = new Restaurante(id, nome, endereco, tipoCozinha);
        empresasPorDono.put(dono, restaurante);
        empresasPorNome.put(nome, restaurante);
        empresasPorEndereco.put(endereco, restaurante);
        return restaurante;
    }
    public static void validarEmpresa(int dono, String nome, String endereco) throws EnderecoInvalidoException, NomeInvalidoException, NomeJaExisteException, EnderecoJaExisteException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        if (dono < 1 || dono > 100) {
            throw new EnderecoInvalidoException();
        }
        Empresa teste = null;
        for (Empresa e : empresasPorNome.values()) {
            if (nome.equals(e.getNome())) {
                teste = e;
                break;
            }
        }
        if (teste != null) {
            if (teste.getDono() == dono) {
                throw new NomeJaExisteException();
            } else {
                if (teste.getEndereco().equals(endereco)) {
                    throw new EnderecoJaExisteException();
                }
            }
        }
    }
}