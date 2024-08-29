package br.ufal.ic.p2.myfood.Modelos;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Cliente;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Dono;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Sistema {

    private int contadorID = 1;

    Map<Integer, Usuario> usuarios = new HashMap<>();
    Map<String, Usuario> usuariosPorEmail = new HashMap<>();

    public void zerarSistema(){
        usuarios.clear();
        usuariosPorEmail.clear();
    }
    public void encerrarSistema(){
        System.out.println("Sistema Encerrado");
    }
    //cria cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        validarDados(nome, email, senha, endereco);
        if(validarEmail(email)){
            throw new EmailJaExisteException();
        }
        Cliente cliente = new Cliente(contadorID, nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuariosPorEmail.put(email, cliente);
        contadorID++;
    }
    public boolean validarEmail(String email){
        return usuariosPorEmail.containsKey(email);
    }
    //cria dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException {
        validarDados(nome, email, senha, endereco);
        validarCpf(cpf);
        Dono dono = new Dono(contadorID, nome, email, senha, endereco, cpf);
        usuarios.put(dono.getId(), dono);
        usuariosPorEmail.put(email, dono);
        contadorID++;
    }

    public void validarDados(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException {
        if (nome == null || nome.isEmpty()){
            throw new NomeInvalidoException();
        }
        if (email == null || email.isEmpty() || !Pattern.matches("^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", email)){
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()){
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()){
            throw new EnderecoInvalidoException();
        }
    }
    public void validarCpf(String cpf) throws CpfInvalidoException {
        if (cpf == null || cpf.length() != 14){
            throw new CpfInvalidoException();
        }
    }
    public int login(String email, String senha) throws UsuarioNaoCadastradoException, LoginInvalidoException{
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario == null){
            throw new LoginInvalidoException();
        } else if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
            return usuario.getId();
        } else {
            throw new LoginInvalidoException();
        }
    }

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNaoCadastradoException();
        } else {
            Usuario usuario = usuarios.get(id);
            switch (atributo){
                case "nome":
                    return usuario.getNome();
                case "senha":
                    return usuario.getSenha();
                case "email":
                    return usuario.getEmail();
                case "endereco":
                    return usuario.getEndereco();
                case "cpf":
                    if(usuario instanceof Dono){
                        return ((Dono) usuario).getCpf();
                    }
            }
        }
        return "";
    }
}
