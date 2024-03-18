package caixa;

public class PJ extends TipoConta{
    private String CNPJ;
    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    public PJ(String CNPJ, String senha, String nome) {
        super(senha, nome);
        this.CNPJ = CNPJ;
    }
}