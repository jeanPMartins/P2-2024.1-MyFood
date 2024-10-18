package br.ufal.ic.p2.myfood.Modelos.Pedido;

import br.ufal.ic.p2.myfood.Modelos.Produto.Produto;

import java.util.ArrayList;

public class Pedido {
    private int numPedido;
    private String cliente;
    private String empresa;
    private String estado;
    private float valor;
    private ArrayList<Produto> produtosAdicionados;

    public Pedido() {
        this.produtosAdicionados = new ArrayList<>(); // Inicializa a lista de produtos
    }
    public Pedido(int numPedido, String cliente, String empresa, String estado, float valor) {
        this.numPedido = numPedido;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = estado;
        this.valor = valor;
        this.produtosAdicionados = new ArrayList<>();
    }

    public int getNumPedido() {
        return numPedido;
    }
    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtosAdicionados = produtos;
    }
    public void addProduto(Produto produto) {
        this.produtosAdicionados.add(produto);
    }
    public void removeProduto(Produto produto) {
        this.produtosAdicionados.remove(produto);
    }
    public ArrayList<Produto> getProdutos() {
        return produtosAdicionados;
    }
    public double getTotal() {
        double total = 0;
        for (Produto product : produtosAdicionados) {
            total += product.getValor();
        }
        return total;
    }
}