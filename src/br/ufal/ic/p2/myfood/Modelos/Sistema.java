package br.ufal.ic.p2.myfood.Modelos;

import br.ufal.ic.p2.myfood.Modelos.Exception.NomeInvalidoException;
import br.ufal.ic.p2.myfood.Modelos.Exception.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Cliente;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Sistema {

    int contadorID = 1;

    Map<String, Usuario> usuariosPorID = new HashMap<>();
    Map<String, Usuario> usuariosLogados = new HashMap<>();
    public void zerarSistema(){

    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException {
        validarDados(nome, email, senha, endereco);
        Cliente cliente = new Cliente(contadorID, nome, email, senha, endereco);
        usuariosPorID.put(String.valueOf(contadorID), cliente);
        contadorID++;
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException {
        validarDados(nome, email, senha, endereco);
        Cliente cliente = new Cliente(contadorID, nome, email, senha, endereco);
        usuariosPorID.put(String.valueOf(contadorID), cliente);
        contadorID++;
    }

    public void validarDados(String nome, String email, String senha, String endereco) throws NomeInvalidoException {
        if (nome.isEmpty() || nome == null){
            throw new NomeInvalidoException();
        }


    }


    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {

        if (usuariosPorID.containsKey(id)){
            Usuario usuario = usuariosPorID.get(id);

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
