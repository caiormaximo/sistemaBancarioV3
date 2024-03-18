package caixa;

import java.util.*;
public class TipoConta {

    private String senha;
    private String nome;
    private double saldo = 0;
    private ArrayList<Transacoes> listaT = new ArrayList<>();
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public ArrayList<Transacoes> getListaT() {
        return listaT;
    }
    public void setListaT(Transacoes transacao) {

        this.listaT.add(transacao);
    }
    public TipoConta(String senha, String nome) {
        this.senha = senha;
        this.nome = nome;
    }
}


