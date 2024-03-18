package caixa;

public class PF extends TipoConta{
    private String CPF;
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public PF(String CPF, String senha, String nome) {
        super(senha, nome);
        this.CPF = CPF;
    }
}

