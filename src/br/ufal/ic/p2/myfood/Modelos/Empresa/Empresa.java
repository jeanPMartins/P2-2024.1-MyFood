package br.ufal.ic.p2.myfood.Modelos.Empresa;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empresa {
    private String tipoEmpresa;
    private int id;
    private int dono;
    private String nome;
    private String endereco;

    public static Map<String, Empresa> empresasPorNome = new HashMap<>();
    public static Map<String, Empresa> empresasPorEndereco = new HashMap<>();
    public static Map<Integer, List<Empresa>> empresasPorDono = new HashMap<>();

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

    public Empresa(int dono, int id, String nome, String endereco) {
        this.dono = dono;
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

    public static Restaurante criarEmpresa(String tipoEmpresa, int id, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, EnderecoInvalidoEmpresaException {
        validarEmpresa(tipoEmpresa, dono, nome, endereco);
        Restaurante restaurante = new Restaurante(tipoEmpresa, dono, id, nome, endereco, tipoCozinha);
        if (!empresasPorDono.containsKey(dono)) {
            empresasPorDono.put(dono, new ArrayList<>());
        }
        empresasPorDono.get(dono).add(restaurante);
        empresasPorNome.put(nome, restaurante);
        empresasPorEndereco.put(endereco, restaurante);
        return restaurante;
    }
    public static Mercado criarEmpresa(String tipoEmpresa,int id, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, EnderecoInvalidoEmpresaException {
        validarEmpresa(tipoEmpresa, dono, nome, endereco);
        Mercado mercado = new Mercado(tipoEmpresa, dono, id, nome, endereco, abre, fecha, tipoMercado);
        if (!empresasPorDono.containsKey(dono)) {
            empresasPorDono.put(dono, new ArrayList<>());
        }
        if (tipoMercado == null || tipoMercado.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (abre == null || abre.isEmpty()) {
            throw new HorarioInvalidoException();
        }
        if (fecha == null || fecha.isEmpty()) {
            throw new HorarioInvalidoException();
        }
        if (validarHorario(fecha)) {
            throw new HorarioInvalidoException();
        }
        empresasPorDono.get(dono).add(mercado);
        empresasPorNome.put(nome, mercado);
        empresasPorEndereco.put(endereco, mercado);
        return mercado;
    }
    public static void validarEmpresa(String tipoEmpresa, int dono, String nome, String endereco) throws EnderecoInvalidoException, NomeInvalidoException, NomeJaExisteException, EnderecoJaExisteException, EnderecoInvalidoEmpresaException {
        if (tipoEmpresa == null || tipoEmpresa.isEmpty()){
            throw new TipoEmpresaInvalidoException();
        }
        if (nome == null || nome.isEmpty()) {
            throw new NomeInvalidoException();
        }
        if (endereco == null || endereco.isEmpty()) {
            throw new EnderecoInvalidoEmpresaException();
        }
        if (dono < 0 || dono > 100) {
            throw new EnderecoInvalidoEmpresaException();
        }
        Empresa teste = null;
        for (Empresa e : empresasPorNome.values()) {
            if (nome.equals(e.getNome())) {
                teste = e;
                break;
            }
        }
        if (teste != null) {
            if (teste.getDono() != dono) {
                throw new NomeJaExisteException();
            } else {
                if (teste.getEndereco().equals(endereco)) {
                    throw new EnderecoJaExisteException();
                }
            }
        }
    }
    public static boolean validarHorario(String hora){
        String[] parts = hora.split(":");
        if(parts.length != 2) return true;
        int horas = Integer.parseInt(parts[0]);
        int mins = Integer.parseInt(parts[1]);
        return (horas < 6 || horas > 23) || (mins < 0 || mins > 59);
    }
}