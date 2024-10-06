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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.ufal.ic.p2.myfood.Modelos.Empresa.Empresa.*;
import static br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario.usuariosPorEmail;

public class Sistema {

    private int usuarioID = 0;
    private int empresaID = 0;
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Empresa> empresas = new HashMap<>();

    public Sistema() {
        carregarDados("data.xml");
    }

    // Zera o sistema
    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
    }

    // Encerra o sistema
    public void encerrarSistema() {
        salvarDados("data.xml");
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
        return null;
    }

    // Salva os dados em um arquivo XML
    public void salvarDados(String caminhoArquivo) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(caminhoArquivo))) {
            // Cria um mapa para armazenar todos os dados do sistema
            Map<String, Object> dadosDoSistema = new HashMap<>();
            dadosDoSistema.put("usuarios", usuarios);
            dadosDoSistema.put("empresas", empresas);

            // Escreve o mapa no arquivo
            encoder.writeObject(dadosDoSistema);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os dados a partir de um arquivo XML
    public void carregarDados(String caminhoArquivo) {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(caminhoArquivo))) {
            Object obj = decoder.readObject();
            if (obj instanceof Map<?, ?>) {
                Map<String, Object> dadosCarregados = (Map<String, Object>) obj;

                // Carrega usuários
                if (dadosCarregados.containsKey("usuarios")) {
                    Map<Integer, Usuario> usuariosCarregados = (Map<Integer, Usuario>) dadosCarregados.get("usuarios");
                    usuarios.clear();
                    usuarios.putAll(usuariosCarregados);
                }

                // Carrega empresas
                if (dadosCarregados.containsKey("empresas")) {
                    Map<Integer, Empresa> empresasCarregadas = (Map<Integer, Empresa>) dadosCarregados.get("empresas");
                    empresas.clear();
                    empresas.putAll(empresasCarregadas);
                }

                // Carrega empresas por dono
                if (dadosCarregados.containsKey("empresasPorDono")) {
                    Map<Integer, List<Empresa>> empresasPorDonoCarregadas = (Map<Integer, List<Empresa>>) dadosCarregados.get("empresasPorDono");
                    empresasPorDono.clear();
                    empresasPorDono.putAll(empresasPorDonoCarregadas);
                }

                // Atualiza os mapas adicionais
                atualizarExtras();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Atualiza os mapas adicionais usados dentro das classes
    private void atualizarExtras() {
        // Limpa e atualiza usuariosPorEmail
        usuariosPorEmail.clear();
        for (Usuario usuario : usuarios.values()) {
            usuariosPorEmail.put(usuario.getEmail(), usuario);
        }

        // Limpa e atualiza empresasPorNome e empresasPorEndereco
        empresasPorNome.clear();
        empresasPorEndereco.clear();

        for (Empresa empresa : empresas.values()) {
            empresasPorNome.put(empresa.getNome(), empresa);
            empresasPorEndereco.put(empresa.getEndereco(), empresa);
        }

        // Limpa e atualiza empresasPorDono
        empresasPorDono.clear();
        for (Empresa empresa : empresas.values()) {
            empresasPorDono.computeIfAbsent(empresa.getDono(), k -> new ArrayList<>()).add(empresa);
        }
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, NomeInvalidoException, UsuarioNaoPodeCriarException {
        if (!usuarios.containsKey(dono) || usuarios.get(dono) instanceof Cliente) {
            throw new UsuarioNaoPodeCriarException();
        }
        Restaurante restaurante = Empresa.criarEmpresa(tipoEmpresa, empresaID, dono, nome, endereco, tipoCozinha);
        empresas.put(restaurante.getId(), restaurante);
        empresaID++;
        return restaurante.getId();
    }

    public String getEmpresasDoUsuario(int id) throws UsuarioNaoPodeCriarException {
        if (usuarios.get(id) instanceof Dono) {
            if(empresasPorDono.get(id) != null){
                List<Empresa> empresasDoUsuario = empresasPorDono.get(id);
                StringBuilder sb = new StringBuilder();
                sb.append("{[");
                for (Empresa empresa : empresasDoUsuario) {
                    sb.append("[").append(empresa.getNome()).append(", ").append(empresa.getEndereco()).append("], ");
                }
                if (sb.length() > 3) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append("]}");
                return sb.toString();
            }
            else return "{[]}";
        }
        else{
            throw new UsuarioNaoPodeCriarException();
        }
    }

    public String getAtributoEmpresa(int id, String atributo) throws EmpresaNaoCadastradaException, AtributoInvalidoException {
        if (!empresas.containsKey(id)) {
            throw new EmpresaNaoCadastradaException();
        }
        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException();
        }

        Empresa empresa = empresas.get(id);

        switch (atributo) {
            case "nome":
                return empresa.getNome();
            case "endereco":
                return empresa.getEndereco();
            case "tipoCozinha":
                if (empresa instanceof Restaurante) {
                    return ((Restaurante) empresa).getTipoCozinha();
                }
            case "dono":
                Usuario dono = usuarios.get(empresa.getDono());
                if (dono != null) {
                    return dono.getNome();
                }
            default:
                throw new AtributoInvalidoException();
        }
    }

    public int getIdEmpresa(int dono, String nome, int indice) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome invalido");
        }

        List<Empresa> empresasDoDono = empresasPorDono.get(dono);

        if (indice < 0) {
            throw new IndexOutOfBoundsException("Indice invalido");
        }

        int contador = 0;
        boolean encontrouNome = false;

        for (Empresa empresa : empresasDoDono) {
            if (empresa.getNome().equals(nome)) {
                encontrouNome = true;
                if (contador == indice) {
                    return empresa.getId();
                }
                contador++;
            }
        }

        if (!encontrouNome) {
            throw new IllegalArgumentException("Nao existe empresa com esse nome");
        }

        throw new IndexOutOfBoundsException("Indice maior que o esperado");
    }
}