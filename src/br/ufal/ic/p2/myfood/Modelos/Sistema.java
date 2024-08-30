package br.ufal.ic.p2.myfood.Modelos;

import br.ufal.ic.p2.myfood.Modelos.Empresa.Empresa;
import br.ufal.ic.p2.myfood.Modelos.Empresa.Restaurante;
import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Cliente;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Dono;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario.usuariosPorEmail;

public class Sistema {

    private int usuarioID = 0;
    private int empresaID = 0;
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Empresa> empresas = new HashMap<>();

    public Sistema(){
        carregarUsuarios("data.xml");
    }
    // Zera o sistema
    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
    }

    // Encerra o sistema
    public void encerrarSistema() {
        salvarUsuarios("data.xml");
        System.out.println("Sistema Encerrado");
    }

    // Cria cliente
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        Cliente cliente = Usuario.criarUsuario(usuarioID, nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuarioID++;
    }

    // Cria dono
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException, EmailJaExisteException {
        Dono dono = Usuario.criarUsuario(usuarioID, nome, email, senha, endereco, cpf);
        usuarios.put(dono.getId(), dono);
        usuarioID++;
    }

    // Login de usuário
    public int login(String email, String senha) throws UsuarioNaoCadastradoException, LoginInvalidoException {
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario == null) {
            throw new LoginInvalidoException();
        } else if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
            return usuario.getId();
        } else {
            throw new LoginInvalidoException();
        }
    }

    // Obtém atributo de um usuário
    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNaoCadastradoException();
        } else {
            Usuario usuario = usuarios.get(id);
            switch (atributo) {
                case "nome":
                    return usuario.getNome();
                case "senha":
                    return usuario.getSenha();
                case "email":
                    return usuario.getEmail();
                case "endereco":
                    return usuario.getEndereco();
                case "cpf":
                    if (usuario instanceof Dono) {
                        return ((Dono) usuario).getCpf();
                    }
            }
        }
        return "";
    }

    // Salva os usuários em um arquivo XML
    public void salvarUsuarios(String caminhoArquivo) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(caminhoArquivo))) {
            encoder.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os usuários a partir de um arquivo XML
    public void carregarUsuarios(String caminhoArquivo) {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(caminhoArquivo))) {
            Object obj = decoder.readObject();
            if (obj instanceof Map<?, ?>) {
                Map<Integer, Usuario> usuariosCarregados = (Map<Integer, Usuario>) obj;
                usuarios.clear();
                usuarios.putAll(usuariosCarregados);
                atualizarUsuariosPorEmail();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Atualiza o mapa de usuários por email com base no mapa de usuários
    private void atualizarUsuariosPorEmail() {
        usuariosPorEmail.clear();
        for (Usuario usuario : usuarios.values()) {
            usuariosPorEmail.put(usuario.getEmail(), usuario);
        }
    }

    public void criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException {
        Restaurante restaurante = Empresa.criarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha);
        empresas.put(restaurante.getId(), restaurante);
        empresaID++;
    }
}
