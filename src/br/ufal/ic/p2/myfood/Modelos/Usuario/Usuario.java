package br.ufal.ic.p2.myfood.Modelos.Usuario;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private TipoUsuario tipo;

    public static Map<String, Usuario> usuariosPorEmail = new HashMap<>();
    public static Map<Integer, List<Integer>> empresasPorEntregador = new HashMap<>();

    // Construtor padrão necessário para XMLDecoder
    public Usuario() {
    }
    public Usuario(int id, String nome, String email, String senha, String endereco, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public TipoUsuario getTipo() {
        return tipo;
    }

    // Métodos para criar usuários
    public static Cliente criarUsuario(int id, String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        validarDados(nome, email, senha, endereco);
        Cliente cliente = new Cliente(id, nome, email, senha, endereco);
        usuariosPorEmail.put(email, cliente);
        return cliente;
    }
    public static Dono criarUsuario(int id, String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException, EmailJaExisteException {
        if (cpf == null || cpf.length() != 14) {
            throw new CpfInvalidoException();
        }
        validarDados(nome, email, senha, endereco);

        Dono dono = new Dono(id, nome, email, senha, endereco, cpf);
        usuariosPorEmail.put(email, dono);
        return dono;
    }
    public static Entregador criarUsuario(int id, String nome, String email, String senha, String endereco, String veiculo, String placa) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, EmailJaExisteException {
        if (veiculo == null || veiculo.trim().length() == 0) {
            throw new VeiculoInvalidoException();
        }
        if (placa == null || placa.trim().length() == 0) {
            throw new PlacaInvalidaException();
        }

        validarDados(nome, email, senha, endereco);

        Entregador entregador = new Entregador(id, nome, email, senha, endereco, veiculo, placa);
        usuariosPorEmail.put(email, entregador);
        empresasPorEntregador.put(id, new ArrayList<>());
        return entregador;
    }

    // Validações
    public static void validarDados(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException, EmailJaExisteException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (email == null || email.isEmpty() || !Pattern.matches("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", email)) {
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoException();
        }
        if (usuariosPorEmail.containsKey(email)) {
            throw new EmailJaExisteException();
        }
    }
}
