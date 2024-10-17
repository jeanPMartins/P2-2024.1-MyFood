package br.ufal.ic.p2.myfood.Modelos.Empresa;

import br.ufal.ic.p2.myfood.Modelos.Exception.*;

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

    public static Map<String, Empresa> empresasPorEndereco = new HashMap<>();
    public static Map<Integer, List<Empresa>> empresasPorDono = new HashMap<>();

    // Construtor padrão necessário para XMLDecoder
    public Empresa() {}

    public Empresa(String tipoEmpresa, int dono, int id, String nome, String endereco) {
        this.tipoEmpresa = tipoEmpresa;
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

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public static Restaurante criarEmpresa(String tipoEmpresa, int id, int dono, String nome, String endereco, String tipoCozinha) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, EnderecoInvalidoEmpresaException {
        validarEmpresa(tipoEmpresa, dono, nome, endereco);
        Restaurante restaurante = new Restaurante(tipoEmpresa, dono, id, nome, endereco, tipoCozinha);
        if (!empresasPorDono.containsKey(dono)) {
            empresasPorDono.put(dono, new ArrayList<>());
        }
        empresasPorDono.get(dono).add(restaurante);
        empresasPorEndereco.put(endereco, restaurante);
        return restaurante;
    }

    public static Mercado criarEmpresa(String tipoEmpresa, int id, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) throws NomeInvalidoException, EnderecoInvalidoException, NomeJaExisteException, EnderecoJaExisteException, EnderecoInvalidoEmpresaException {
        if (tipoMercado == null || tipoMercado.isEmpty()) {
            throw new TipoMercadoInvalidoException();
        }
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

        validarEmpresa(tipoEmpresa, dono, nome, endereco);
        Mercado mercado = new Mercado(tipoEmpresa, dono, id, nome, endereco, abre, fecha, tipoMercado);
        if (!empresasPorDono.containsKey(dono)) {
            empresasPorDono.put(dono, new ArrayList<>());
        }
        empresasPorDono.get(dono).add(mercado);
        empresasPorEndereco.put(endereco, mercado);
        return mercado;
    }

    public static Farmacia criarEmpresa(String tipoEmpresa, int dono, int id, String nome, String endereco, boolean abre24h, int numFuncionario) throws NomeJaExisteException, NomeInvalidoException, EnderecoInvalidoException, EnderecoInvalidoEmpresaException, EnderecoJaExisteException {
        validarEmpresa(tipoEmpresa, dono, nome, endereco);
        Farmacia farmacia = new Farmacia(tipoEmpresa, dono, id, nome, endereco, abre24h, numFuncionario);
        if (!empresasPorDono.containsKey(dono)) {
            empresasPorDono.put(dono, new ArrayList<>());
        }
        empresasPorDono.get(dono).add(farmacia);
        empresasPorEndereco.put(endereco, farmacia);
        return farmacia;
    }

    public static boolean validarFormatoHora(String hora) {
        String regex = "^\\d{2}:\\d{2}$";
        return hora.matches(regex);
    }

    public static boolean validarHorario(String hora) {
        try {
            String[] parts = hora.split(":");
            if (parts.length != 2) return false;

            int horas = Integer.parseInt(parts[0]);
            int minutos = Integer.parseInt(parts[1]);

            return horas >= 4 && horas <= 23 && minutos >= 0 && minutos <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
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

        List<Empresa> empresasDoDono = empresasPorDono.get(dono);
        if (empresasDoDono != null) {
            for (Empresa empresa : empresasDoDono) {
                if (empresa.getNome().equals(nome) && empresa.getEndereco().equals(endereco)) {
                    throw new EnderecoJaExisteException();
                }
            }
        }

        for (List<Empresa> empresas : empresasPorDono.values()) {
            for (Empresa empresa : empresas) {
                if (empresa.getNome().equals(nome) && empresa.getDono() != dono) {
                    throw new NomeJaExisteException();
                }
            }
        }
    }
}
