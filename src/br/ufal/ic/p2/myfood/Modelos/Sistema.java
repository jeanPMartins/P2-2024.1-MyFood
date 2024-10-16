package br.ufal.ic.p2.myfood.Modelos;

import br.ufal.ic.p2.myfood.Modelos.Empresa.Empresa;
import br.ufal.ic.p2.myfood.Modelos.Empresa.Farmacia;
import br.ufal.ic.p2.myfood.Modelos.Empresa.Mercado;
import br.ufal.ic.p2.myfood.Modelos.Empresa.Restaurante;
import br.ufal.ic.p2.myfood.Modelos.Exception.*;
import br.ufal.ic.p2.myfood.Modelos.Pedido.Pedido;
import br.ufal.ic.p2.myfood.Modelos.Produto.Produto;
import br.ufal.ic.p2.myfood.Modelos.Usuario.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static br.ufal.ic.p2.myfood.Modelos.Empresa.Empresa.*;
import static br.ufal.ic.p2.myfood.Modelos.Produto.Produto.produtoPorEmpresa;
import static br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario.empresasPorEntregador;
import static br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario.usuariosPorEmail;

public class Sistema {

    private int usuarioID = 0;
    private int empresaID = 0;
    private int produtoID = 0;
    private int pedidosID = 0;
    private Map<Integer, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Empresa> empresas = new HashMap<>();
    private Map<Integer, Produto> produtos = new HashMap<>();
    private Map<Integer, Pedido> pedidos = new HashMap<>();

    public Sistema() {
        carregarDados("data.xml");
    }
    public void zerarSistema() {
        usuarios.clear();
        usuariosPorEmail.clear();
        empresas.clear();
        empresasPorDono.clear();
        empresasPorEndereco.clear();
        produtos.clear();
        produtoPorEmpresa.clear();
        pedidos.clear();
        entregadoresPorEmpresa.clear();
        empresasPorEntregador.clear();
    }
    public void encerrarSistema() {
        salvarDados("data.xml");
        System.out.println("Sistema Encerrado");
    }

    //Persistencia de dados usando XML
    public void salvarDados(String caminhoArquivo) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(caminhoArquivo))) {
            // Cria um mapa para armazenar todos os dados do sistema
            Map<String, Object> dadosDoSistema = new HashMap<>();
            dadosDoSistema.put("usuarios", usuarios);
            dadosDoSistema.put("empresas", empresas);
            dadosDoSistema.put("produtos", produtos);
            dadosDoSistema.put("pedidos", pedidos);

            // Escreve o mapa no arquivo
            encoder.writeObject(dadosDoSistema);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
                // Carrega produtos
                if (dadosCarregados.containsKey("produtos")) {
                    Map<Integer, Produto> produtosCarregados = (Map<Integer, Produto>) dadosCarregados.get("produtos");
                    produtos.clear();
                    produtos.putAll(produtosCarregados);
                }
                // Carrega pedidos
                if (dadosCarregados.containsKey("pedidos")) {
                    Map<Integer, Pedido> pedidosCarregados = (Map<Integer, Pedido>) dadosCarregados.get("pedidos");
                    pedidos.clear();
                    pedidos.putAll(pedidosCarregados);
                }

                // Atualiza os mapas adicionais
                atualizarExtras();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void atualizarExtras() {
        // Limpa e atualiza usuariosPorEmail
        usuariosPorEmail.clear();
        for (Usuario usuario : usuarios.values()) {
            usuariosPorEmail.put(usuario.getEmail(), usuario);
        }

        // Limpa e atualiza empresasPorEndereco
        empresasPorEndereco.clear();
        for (Empresa empresa : empresas.values()) {
            empresasPorEndereco.put(empresa.getEndereco(), empresa);
        }

        // Limpa e atualiza empresasPorDono
        empresasPorDono.clear();
        for (Empresa empresa : empresas.values()) {
            empresasPorDono.computeIfAbsent(empresa.getDono(), k -> new ArrayList<>()).add(empresa);
        }

        // Limpa e atualiza produtoPorEmpresa
        produtoPorEmpresa.clear();
        for (Produto produto : produtos.values()) {
            produtoPorEmpresa.computeIfAbsent(produto.getEmpresa(), k -> new ArrayList<>()).add(produto);
        }
    }

    //Usuarios = US1 + US7
    public void criarUsuario(String nome, String email, String senha, String endereco) throws NomeInvalidoException, EmailJaExisteException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        //criaçao de clientes
        Cliente cliente = Usuario.criarUsuario(usuarioID, nome, email, senha, endereco);
        usuarios.put(cliente.getId(), cliente);
        usuarioID++;
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, CpfInvalidoException, EmailJaExisteException {
        Dono dono = Usuario.criarUsuario(usuarioID, nome, email, senha, endereco, cpf);
        usuarios.put(dono.getId(), dono);
        usuarioID++;
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws EmailJaExisteException, NomeInvalidoException, EmailInvalidoException, EnderecoInvalidoException, SenhaInvalidaException {
        Entregador entregador = Usuario.criarUsuario(usuarioID, nome, email, senha, endereco, veiculo, placa);
        usuarios.put(entregador.getId(), entregador);
        usuarioID++;
    }
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
                    return ((Dono) usuario).getCpf();
                case "veiculo":
                    return ((Entregador) usuario).getVeiculo();
                case "placa":
                    return ((Entregador) usuario).getPlaca();
            }
        }
        return null;
    }
    public void cadastrarEntregador(int empresa, int entregador) throws EntregadorJaCadastradoException, EmpresaNaoEncontradaException, NaoEntregadorException {
        Empresa emp = empresas.get(empresa);
        if (emp == null) {
            throw new EmpresaNaoEncontradaException();
        }

        Usuario usuario = usuarios.get(entregador);
        if (usuario == null || usuario.getTipo() != TipoUsuario.ENTREGADOR) {
            throw new NaoEntregadorException();
        }

        List<Integer> entregadoresDaEmpresa = entregadoresPorEmpresa.get(empresa);
        if (entregadoresDaEmpresa == null) {
            entregadoresDaEmpresa = new ArrayList<>();
            entregadoresPorEmpresa.put(empresa, entregadoresDaEmpresa);
        }

        if (entregadoresDaEmpresa.contains(entregador)) {
            throw new EntregadorJaCadastradoException();
        }

        entregadoresDaEmpresa.add(entregador);

        List<Integer> empresasDoEntregador = empresasPorEntregador.get(entregador);
        if (empresasDoEntregador == null) {
            empresasDoEntregador = new ArrayList<>();
            empresasPorEntregador.put(entregador, empresasDoEntregador);
        }

        empresasDoEntregador.add(empresa);
    }
    public String getEntregadores(int empresa) throws EmpresaNaoEncontradaException {
        List<Integer> entregadoresDaEmpresa = entregadoresPorEmpresa.get(empresa);

        if (entregadoresDaEmpresa == null || entregadoresDaEmpresa.isEmpty()) {
            return "{[]}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{["); // Começa com {[

        for (int i = 0; i < entregadoresDaEmpresa.size(); i++) {
            Usuario entregador = usuarios.get(entregadoresDaEmpresa.get(i)); // Supondo que os emails estão no mapa `usuarios`
            sb.append(entregador.getEmail());

            if (i < entregadoresDaEmpresa.size() - 1) {
                sb.append(", "); // Adiciona a vírgula entre os emails
            }
        }

        sb.append("]}"); // Fecha com ]}

        return sb.toString();
    }
    public String getEmpresas(int entregadorId) throws NaoEntregadorException {
        Usuario usuario = usuarios.get(entregadorId);
        if (usuario == null || !(usuario instanceof Entregador)) {
            throw new NaoEntregadorException();
        }

        List<Integer> empresasDoEntregador = empresasPorEntregador.get(entregadorId);
        if (empresasDoEntregador == null || empresasDoEntregador.isEmpty()) {
            return "{[]}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{[");
        for (int i = 0; i < empresasDoEntregador.size(); i++) {
            Empresa empresa = empresas.get(empresasDoEntregador.get(i));
            sb.append("[");
            sb.append(empresa.getNome()).append(", ").append(empresa.getEndereco());
            sb.append("]");

            if (i < empresasDoEntregador.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]}");

        return sb.toString();
    }
    //Empresas = US2 + US5 + US6
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, NomeInvalidoException, UsuarioNaoPodeCriarException, EnderecoInvalidoEmpresaException {
        Usuario usuario = usuarios.get(dono);
        if (usuario == null) {
            return 0;
        }
        if (usuario.getTipo() != TipoUsuario.DONO) {
            throw new UsuarioNaoPodeCriarException();
        }

        Restaurante restaurante = Empresa.criarEmpresa(tipoEmpresa, empresaID, dono, nome, endereco, tipoCozinha);
        empresas.put(restaurante.getId(), restaurante);
        return empresaID++;
    }
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) throws EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, NomeInvalidoException, UsuarioNaoPodeCriarException, EnderecoInvalidoEmpresaException {
        Usuario usuario = usuarios.get(dono);
        if (usuario == null) {
            return 0;
        }
        if (usuario.getTipo() != TipoUsuario.DONO) {
            throw new UsuarioNaoPodeCriarException();
        }
        Mercado mercado = Empresa.criarEmpresa(tipoEmpresa, empresaID, dono, nome, endereco, abre, fecha, tipoMercado);
        empresas.put(mercado.getId(), mercado);
        return empresaID++;
    }
    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, boolean abre24h, int numFuncionario) throws UsuarioNaoPodeCriarException, NomeJaExisteException, NomeInvalidoException, EnderecoInvalidoException, EnderecoInvalidoEmpresaException, EnderecoJaExisteException {
        Usuario usuario = usuarios.get(dono);
        if (usuario == null) {
            return 0;
        }
        if (usuario.getTipo() != TipoUsuario.DONO) {
            throw new UsuarioNaoPodeCriarException();
        }
        Farmacia farmacia = Empresa.criarEmpresa(tipoEmpresa, dono, empresaID, nome, endereco, abre24h, numFuncionario);
        empresas.put(farmacia.getId(), farmacia);
        return empresaID++;
    }
    public void alterarFuncionamento(int mercado, String abre, String fecha) {
        if (!empresas.containsKey(mercado)) {
            throw new MercadoInvalidoException();
        }

        Empresa empresa = empresas.get(mercado);

        if (!((empresa.getTipoEmpresa().equals("mercado") ))) {
            throw new MercadoInvalidoException();
        }

        // Validação dos horários
        if (abre == null || fecha == null) {
            throw new HorarioInvalidoException();
        }
        if (abre.isEmpty() || fecha.isEmpty()) {
            throw new FormatoDataHoraInvalidoException();
        }
        if (!validarFormatoHora(abre) || !validarFormatoHora(fecha)) {
            throw new FormatoDataHoraInvalidoException();
        }
        if (!validarHorario(abre) || !validarHorario(fecha)) {
            throw new HorarioInvalidoException();
        }

        Mercado merc = (Mercado) empresa;
        merc.setAbre(abre);
        merc.setFecha(fecha);
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
                return ((Restaurante) empresa).getTipoCozinha();
            case "dono":
                Usuario dono = usuarios.get(empresa.getDono());
                if (dono != null) {
                    return dono.getNome();
                }
            case "abre":
                return ((Mercado)empresa).getAbre();
            case "fecha":
                return ((Mercado)empresa).getFecha();
            case "tipoMercado":
                return ((Mercado)empresa).getTipoMercado();
            case "aberto24Horas":
                if (((Farmacia)empresa).isAbre24h()) return "true";
                else return "false";
            case "numeroFuncionarios":
                return String.valueOf(((Farmacia)empresa).getNumFuncionario());
            default:
                throw new AtributoInvalidoException();
        }
    }
    public int getIdEmpresa(int dono, String nome, int indice) throws NomeInvalidoException, EmpresaNaoExisteException  {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }

        List<Empresa> empresasDoDono = empresasPorDono.get(dono);

        if (indice < 0) {
            throw new IndiceInvalidoException();
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
            throw new EmpresaNaoExisteException();
        }

        throw new IndiceMaiorException();
    }
    //Produtos = US3
    public int criarProduto(int empresa, String nome, float valor, String categoria) throws NomeInvalidoException {
        validarProdutos(empresa, nome, valor, categoria);
        Produto produto = new Produto(produtoID, nome, valor, categoria, empresa);
        if (!produtoPorEmpresa.containsKey(empresa)) {
            produtoPorEmpresa.put(empresa, new ArrayList<>());
        }
//      System.out.println("Produto " + produto.getNome() + " numero " + produto.getId() + " criado" + " para a empresa numero " + produto.getEmpresa() + " ");
        produtoPorEmpresa.get(empresa).add(produto);
        produtos.put(produto.getId(), produto);
        produtoID++;
        return produto.getId();
    }
    public void validarProdutos(int empresa, String nome, float valor, String categoria) throws NomeInvalidoException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (valor < 0) {
            throw new ValorInvalidoException();
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new CategoriaInvalidaException();
        }
        if (produtoPorEmpresa.containsKey(empresa)) {
            for (Produto p : produtoPorEmpresa.get(empresa)) {
                if (p.getNome().equals(nome)) {
                    throw new ProdutoJaExiste();
                }
            }
        }
    }
    public void editarProduto(int produto, String nome, float valor, String categoria) throws NomeInvalidoException, ValorInvalidoException, CategoriaInvalidaException, ProdutoNaoEncontradoException {
        if (!produtos.containsKey(produto)) {
            throw new ProdutoNaoCadastrado();
        }
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (valor < 0) {
            throw new ValorInvalidoException();
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new CategoriaInvalidaException();
        }
        Produto p = produtos.get(produto);
        p.setNome(nome);
        p.setValor(valor);
        p.setCategoria(categoria);
    }
    public String getProduto(String nome, int empresa, String atributo) throws AtributoInvalidoException {
        if (!produtoPorEmpresa.containsKey(empresa)) {
            throw new ProdutoNaoEncontradoException();
        }

        List<Produto> produtosDaEmpresa = produtoPorEmpresa.get(empresa);
        Produto produtoEncontrado = null;
        for (Produto p : produtosDaEmpresa) {
            if (p.getNome().equals(nome)) {
                produtoEncontrado = p;
                break;
            }
        }

        if (produtoEncontrado == null) {
            throw new ProdutoNaoEncontradoException();
        }

        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException();
        }

        switch (atributo) {
            case "nome":
                return produtoEncontrado.getNome();
            case "categoria":
                return produtoEncontrado.getCategoria();
            case "valor":
                return String.format(Locale.US, "%.2f", produtoEncontrado.getValor());
            case "empresa":
                return empresas.get(produtoEncontrado.getEmpresa()).getNome();
            default:
                throw new AtributoNaoExisteException();
        }
    }
    public String listarProdutos(int empresa) {
        if (!empresas.containsKey(empresa)) {
            throw new EmpresaNaoEncontradaException();
        }

        List<Produto> produtosDaEmpresa = produtoPorEmpresa.get(empresa);

        if (produtosDaEmpresa != null && !produtosDaEmpresa.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("{[");
            for (Produto produto : produtosDaEmpresa) {
                sb.append(produto.getNome()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("]}");
            return sb.toString();
        } else {
            return "{[]}";
        }
    }
    //Pedidos = US4
    public int criarPedido(int cliente, int empresa) throws NaoPodePedirException, PedidoJaExisteException {
        if (usuarios.get(cliente).getTipo() == TipoUsuario.DONO) {
            throw new NaoPodePedirException();
        }

        for (Pedido pedido : pedidos.values()) {
            if (pedido.getCliente().equals(usuarios.get(cliente).getNome()) &&
                    pedido.getEmpresa().equals(empresas.get(empresa).getNome()) &&
                    pedido.getEstado().equals("aberto")) {
                throw new PedidoJaExisteException();
            }
        }

        Pedido pedido = new Pedido(pedidosID, usuarios.get(cliente).getNome(), empresas.get(empresa).getNome(), "aberto", 0);
        pedidos.put(pedidosID, pedido);
        return pedidosID++;
    }
    public int getNumeroPedido(int cliente, int empresa, int indice) {
        List<Pedido> pedidosDoClienteParaEmpresa = new ArrayList<>();

        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            Pedido pedido = entry.getValue();
            if (pedido.getCliente().equals(usuarios.get(cliente).getNome()) &&
                    pedido.getEmpresa().equals(empresas.get(empresa).getNome())) {

                pedidosDoClienteParaEmpresa.add(pedido);
            }
        }
        // Retorna o número do pedido correspondente ao índice
        return pedidosDoClienteParaEmpresa.get(indice).getNumPedido();
    }
    public void adicionarProduto(int numero, int produto) throws NaoExistePedidoAbertoException, ProdutoNaoPertenceException, PedidoJaFechouException {
        if (!pedidos.containsKey(numero)) {
            throw new NaoExistePedidoAbertoException();
        }

        Pedido pedido = pedidos.get(numero);

        if (!pedido.getEstado().equals("aberto")) {
            throw new PedidoJaFechouException("Nao e possivel adcionar produtos a um pedido fechado");
        }

        List<Produto> produtosDaEmpresa = produtoPorEmpresa.get(produtos.get(produto).getEmpresa());

        if (!produtosDaEmpresa.contains(produtos.get(produto))) {
            throw new ProdutoNaoPertenceException();
        }
        Empresa teste = null;
        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equalsIgnoreCase(pedidos.get(numero).getEmpresa())) {
                teste = empresa;
                break;
            }
        }
        if(teste.getId() != produtos.get(produto).getEmpresa()) {
            throw new ProdutoNaoPertenceException();
        }

        pedido.addProduto(produtos.get(produto));
    }
    public String getPedidos(int numero, String atributo) throws AtributoInvalidoException, NaoExistePedidoAbertoException, AtributoNaoExisteException {
        if (!pedidos.containsKey(numero)) {
            throw new NaoExistePedidoAbertoException();
        }

        Pedido pedido = pedidos.get(numero);

        if (atributo == null || atributo.isEmpty()) {
            throw new AtributoInvalidoException();
        }

        switch (atributo) {
            case "cliente":
                return pedido.getCliente();
            case "empresa":
                return pedido.getEmpresa();
            case "estado":
                return pedido.getEstado();
            case "valor":
                return String.format(Locale.US, "%.2f", pedido.getTotal());
            case "produtos":
                StringBuilder sb = new StringBuilder();
                sb.append("{[");

                List<Produto> produtosAdicionados = pedido.getProdutos();
                for (Produto produto : produtosAdicionados) {
                    sb.append(produto.getNome()).append(", ");
                }
                if (produtosAdicionados.size() > 0) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append("]}");
                return sb.toString();
            default:
                throw new AtributoNaoExisteException();
        }
    }
    public void fecharPedido(int numero){
        if(!pedidos.containsKey(numero)){
            throw new PedidoNaoEncontradoException();
        }
        pedidos.get(numero).setEstado("preparando");
    }
    public void removerProduto(int numero, String nomeProduto) throws ProdutoNaoEncontradoException, ProdutoInvalidoException, PedidoJaFechouException {
        if (nomeProduto == null || nomeProduto.isEmpty()) {
            throw new ProdutoInvalidoException();
        }

        Pedido pedido = pedidos.get(numero);

        if (pedido == null) {
            throw new NaoExistePedidoAbertoException();
        }
        if (pedido.getEstado().equals("preparando")) {
            throw new PedidoJaFechouException("Nao e possivel remover produtos de um pedido fechado");
        }
        Produto produtoARemover = null;
        for (Produto produto : pedido.getProdutos()) {
            if (produto.getNome().equals(nomeProduto)) {
                produtoARemover = produto;
                break;
            }
        }
        if (produtoARemover == null) {
            throw new ProdutoNaoEncontradoException();
        }

        pedido.removeProduto(produtoARemover);
    }
}