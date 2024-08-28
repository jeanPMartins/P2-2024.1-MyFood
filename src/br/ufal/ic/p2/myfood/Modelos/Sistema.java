package br.ufal.ic.p2.myfood.Modelos;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Cliente;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Dono;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

    private int contadorID = 1;
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Map<String, Usuario> usuariosPorEmail = new HashMap<>();
    public void zerarSistema(){
    }
    //cria cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        validarDados(nome, email, senha, endereco);
        if(validarEmail(email)){
            throw new EmailJaExisteException();
        }
        Cliente cliente = new Cliente(contadorID, nome, email, senha, endereco);
        usuarios.add(cliente);
        usuariosPorEmail.put(email, cliente);
        contadorID++;
    }
    public boolean validarEmail(String email){
        return usuariosPorEmail.containsKey(email);
    }
    //cria dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        validarDados(nome, email, senha, endereco);
        Dono dono = new Dono(contadorID, nome, email, senha, endereco, cpf);
        usuarios.add(dono);
        usuariosPorEmail.put(email, dono);
        contadorID++;
    }

    public void validarDados(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException {
        if (nome == null || nome.isEmpty()){
            throw new NomeInvalidoException();
        }
        if (email == null || email.isEmpty()){
            throw new EmailInvalidoException();
        }
        if (senha == null || senha.isEmpty()){
            throw new SenhaInvalidaException();
        }
        if (endereco == null || endereco.isEmpty()){
            throw new EnderecoInvalidoException();
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
        if(usuarios.contains(id)){
            Usuario usuario = usuariosPorEmail.get(usuarios.get(id));
            switch (atributo){
                case "nome":
                    return usuario.getNome();
                case "senha":
                    return usuario.getSenha();
                case "email":
                    return usuario.getEmail();
            }
        } else {
            throw new UsuarioNaoCadastradoException();
        }

        return "";
    }
}
