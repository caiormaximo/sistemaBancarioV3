package caixa;

import java.util.*;
import javax.swing.JOptionPane;

public class Caixa {
    static ArrayList<PF> contasPF = new ArrayList<>();
    static ArrayList<PJ> contasPJ = new ArrayList<>();
    static double valor_saque;
    static double valor_depositado;
    static double valor_troca;
    static int indice_CLIENTE;
    static int indice_pagamento;
    static int Continuar = 0;
    static int atendimentos = 0;
    static String atendimento = "on";

    public static String novaContaPF(){
        String pfNome;
        String pfCPF;
        String pfSenha;
        pfNome = JOptionPane.showInputDialog("Nome: ");
        pfCPF = JOptionPane.showInputDialog("CPF: ");
        pfSenha = JOptionPane.showInputDialog("Senha: ");
        contasPF.add(new PF(pfCPF, pfSenha, pfNome));
        return pfNome;
    }

    public static String novaContaPJ(){
        String pjNome;
        String pjCNPJ;
        String pjSenha;
        pjNome = JOptionPane.showInputDialog("Nome: ");
        pjCNPJ = JOptionPane.showInputDialog("CNPJ: ");
        pjSenha = JOptionPane.showInputDialog("Senha: ");
        contasPJ.add(new PJ(pjCNPJ, pjSenha, pjNome));
        return pjNome;
    }

    public static void login_ContaFisica(){
        indice_CLIENTE = -1;
        int login = JOptionPane.showConfirmDialog(null, "Já possui conta?", "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
        if (login == 0){
            String clienteNovoVelhoNome = JOptionPane.showInputDialog("Nome: ");
            String clienteNovoVelhoSenha = JOptionPane.showInputDialog("Senha: ");
            for (int k = 0; k < contasPF.size(); k++){
                if ((contasPF.get(k).getNome().equals(clienteNovoVelhoNome)) && (contasPF.get(k).getSenha().equals(clienteNovoVelhoSenha))){
                    JOptionPane.showMessageDialog(null, "Conta confirmada!");
                    indice_CLIENTE = k;
                    break;
                }
            }
            if (indice_CLIENTE == -1){
                JOptionPane.showMessageDialog(null, "Conta inexistente");
                JOptionPane.showMessageDialog(null, "Seja bem vindo " + novaContaPF() + " !");
                indice_CLIENTE = contasPF.size() - 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seja bem vindo " + novaContaPF() + " !");
            indice_CLIENTE = contasPF.size() - 1;
        }
    }

    public static void login_ContaJuridica(){
        indice_CLIENTE = -1;
        int login = JOptionPane.showConfirmDialog(null, "Já possui conta?", "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
        if (login == 0){
            String clienteNovoVelhoNome = JOptionPane.showInputDialog("Nome: ");
            String clienteNovoVelhoSenha = JOptionPane.showInputDialog("Senha: ");
            for (int k = 0; k < contasPJ.size(); k++){
                if ((contasPJ.get(k).getNome().equals(clienteNovoVelhoNome)) && (contasPJ.get(k).getSenha().equals(clienteNovoVelhoSenha))){
                    JOptionPane.showMessageDialog(null, "Olá " + contasPJ.get(k).getNome() + " !");
                    indice_CLIENTE = k;
                    break;
                }
            }
            if (indice_CLIENTE == -1){
                JOptionPane.showMessageDialog(null, "Conta inexistente");
                JOptionPane.showMessageDialog(null, "Seja bem vindo " + novaContaPJ() + " !");
                indice_CLIENTE = contasPJ.size() - 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seja bem vindo "+ novaContaPJ() + " !");
            indice_CLIENTE = contasPJ.size() - 1;
        }
    }

    public static double Saque(double valor_saldo){
        valor_saque = Double.parseDouble(JOptionPane.showInputDialog("Valor do saque:"));
        if (valor_saldo > valor_saque){
            valor_saldo -= valor_saque;
            JOptionPane.showMessageDialog(null, "Saque realizado.");
            return valor_saldo;
        } else {
            JOptionPane.showMessageDialog(null, "O saque não pode ser realizado. Saldo insuficiente.");
            int Saque_inesperado = JOptionPane.showConfirmDialog(null, "Deseja trocar o valor do saque?", "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
            if (Saque_inesperado == 0){
                valor_saldo = Saque(valor_saldo);
                return valor_saldo;
            } else {
                return valor_saldo;
            }
        }
    }

    public static double Deposito(double valor_saldo){
        valor_depositado = Double.parseDouble(JOptionPane.showInputDialog("Valor do depósito:"));
        valor_saldo += valor_depositado;
        JOptionPane.showMessageDialog(null, "Deposito realizado.");
        return valor_saldo;
    }

    public static void TransferenciaCF_CJ(PF cliente_perde, PJ cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Valor do pagamento: "));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_perde.getSaldo()));
        cliente_ganha.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_ganha.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void TransferenciaCJ_CF(PJ  cliente_perde, PF cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Valor do pagamento: "));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_perde.getSaldo()));
        cliente_ganha.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_ganha.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void TransferenciaCF_CF(PF cliente_perde, PF cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Valor do pagamento: "));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_perde.getSaldo()));
        cliente_ganha.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_ganha.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void TransferenciaCJ_CJ(PJ cliente_perde, PJ cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Valor do pagamento: "));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_perde.getSaldo()));
        cliente_ganha.setListaT(new Transacoes ("Transferência", valor_troca, cliente_perde.getNome(), cliente_ganha.getNome(), cliente_ganha.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void Transferencias_ContaFisica(){
        indice_pagamento = -1;
        Object[] transferencias = {"Transferência para Cliente Físico", "Transferência para Cliente Jurídico"};
        Object selectedTransfers = JOptionPane.showInputDialog(null,
                "Que tipo de transferência deseja realizar?", "transferência", JOptionPane.INFORMATION_MESSAGE, null, transferencias, transferencias[0]);
        if (selectedTransfers == transferencias[0]){
            String clientePagoNome = JOptionPane.showInputDialog("Nome destinatario: ");
            String clientePagoCPF = JOptionPane.showInputDialog("CPF destinatario: ");
            for (int m = 0; m < contasPF.size(); m++){
                if ((contasPF.get(m).getNome().equals(clientePagoNome)) && (contasPF.get(m).getCPF().equals(clientePagoCPF))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCF_CF(contasPF.get(indice_CLIENTE), contasPF.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        } else {
            String clientePagoNome = JOptionPane.showInputDialog("Nome destinatario: ");
            String clientePagoCNPJ = JOptionPane.showInputDialog("CPF destinatario: ");
            for (int m = 0; m < 10; m++){
                if ((contasPJ.get(m).getNome().equals(clientePagoNome)) && (contasPJ.get(m).getCNPJ().equals(clientePagoCNPJ))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCF_CJ(contasPF.get(indice_CLIENTE), contasPJ.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        }
    }
    public static void Transferencias_ContaJuridica(){
        indice_pagamento = -1;
        Object[] transferencias = {"Transferência para Cliente Físico", "Transferência para Cliente Jurídico"};
        Object selectedTransfers = JOptionPane.showInputDialog(null,
                "Que tipo de transferência deseja realizar?", "transferência", JOptionPane.INFORMATION_MESSAGE, null, transferencias, transferencias[0]);
        if (selectedTransfers == transferencias[0]){
            String clientePagoNome = JOptionPane.showInputDialog("Nome destinatario: ");
            String clientePagoCPF = JOptionPane.showInputDialog("CPF destinatario: ");
            for (int m = 0; m < contasPF.size(); m++){
                if ((contasPF.get(m).getNome().equals(clientePagoNome)) && (contasPF.get(m).getCPF().equals(clientePagoCPF))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCJ_CF(contasPJ.get(indice_CLIENTE), contasPF.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        } else {
            String clientePagoNome = JOptionPane.showInputDialog("Nome destinatario: ");
            String clientePagoCNPJ = JOptionPane.showInputDialog("CPF destinatario: ");
            for (int m = 0; m < 10; m++){
                if ((contasPJ.get(m).getNome().equals(clientePagoNome)) && (contasPJ.get(m).getCNPJ().equals(clientePagoCNPJ))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = m;
                    TransferenciaCJ_CJ(contasPJ.get(indice_CLIENTE), contasPJ.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        }
    }

    public static void Extrato_Transacoes(ArrayList<Transacoes>
                                                  Extrato){
        System.out.println(Extrato);
    }

    public static void Operacoes_CF(){
        Object[] operations = {"Saque", "Deposito", "Transferência", "Extrato"};
        Object selectedOperation = JOptionPane.showInputDialog(null, "Que operação deseja realizar?",
                "operação", JOptionPane.INFORMATION_MESSAGE, null, operations, operations[0]);

        double novo_saldo;

        Transacoes transacao;

        if (selectedOperation == operations[0]){
            novo_saldo = Saque(contasPF.get(indice_CLIENTE).getSaldo());
            contasPF.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes ("Saque", valor_saque,
                    contasPF.get(indice_CLIENTE).getNome(),
                    contasPF.get(indice_CLIENTE).getNome(),
                    contasPF.get(indice_CLIENTE).getSaldo());
            contasPF.get(indice_CLIENTE).setListaT(transacao);
        } else if (selectedOperation == operations[1]) {
            novo_saldo = Deposito(contasPF.get(indice_CLIENTE).getSaldo());
            contasPF.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes ("Deposito", valor_depositado,
                    contasPF.get(indice_CLIENTE).getNome(),
                    contasPF.get(indice_CLIENTE).getNome(),
                    contasPF.get(indice_CLIENTE).getSaldo());
            contasPF.get(indice_CLIENTE).setListaT(transacao);
        } else if (selectedOperation == operations[2]){
            Transferencias_ContaFisica();
        } else {
            Extrato_Transacoes(contasPF.get(indice_CLIENTE).getListaT());
        }
    }
    public static void Operacoes_CJ(){
        Object[] operations = {"Saque", "Deposito", "Transferência", "Extrato"};
        Object selectedOperation = JOptionPane.showInputDialog(null, "Que operação deseja realizar?",
                "operação", JOptionPane.INFORMATION_MESSAGE, null, operations, operations[0]);

        double novo_saldo;

        Transacoes transacao;

        if (selectedOperation == operations[0]){
            novo_saldo = Saque(contasPJ.get(indice_CLIENTE).getSaldo());
            contasPJ.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes ("Saque", valor_saque,
                    contasPJ.get(indice_CLIENTE).getNome(),
                    contasPJ.get(indice_CLIENTE).getNome(),
                    contasPJ.get(indice_CLIENTE).getSaldo());
            contasPJ.get(indice_CLIENTE).setListaT(transacao);
        } else if (selectedOperation == operations[1]) {
            novo_saldo = Deposito(contasPJ.get(indice_CLIENTE).getSaldo());
            contasPJ.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes ("Deposito", valor_depositado,
                    contasPJ.get(indice_CLIENTE).getNome(),
                    contasPJ.get(indice_CLIENTE).getNome(),
                    contasPJ.get(indice_CLIENTE).getSaldo());
            contasPJ.get(indice_CLIENTE).setListaT(transacao);
        } else if (selectedOperation == operations[2]){
            Transferencias_ContaJuridica();
        } else {
            Extrato_Transacoes(contasPJ.get(indice_CLIENTE).getListaT());
        }
    }
    public static void Atendimento_Cliente() {
        Object[] pessoas = {"Cliente físico", "Cliente jurídico"};
        Object selectedPerson = JOptionPane.showInputDialog(null,
                "Quem é você?", "Caixa", JOptionPane.INFORMATION_MESSAGE, null, pessoas, pessoas[0]);
        if (selectedPerson == pessoas[0]){
            login_ContaFisica();
            do {
                Operacoes_CF();
                Continuar = JOptionPane.showConfirmDialog(null,
                        "Deseja realizar mais alguma operação?", "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
            } while (Continuar == 0);
            JOptionPane.showMessageDialog(null, "Obrigado, tenha um bom dia " + contasPF.get(indice_CLIENTE).getNome());
        } else {
            login_ContaJuridica();
            do {
                Operacoes_CJ();
                Continuar = JOptionPane.showConfirmDialog(null,
                        "Deseja realizar mais alguma operação?", "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
            } while (Continuar == 0);
            JOptionPane.showMessageDialog(null, "Obrigado, tenha um bom dia " + contasPJ.get(indice_CLIENTE).getNome());
        }
    }
    public static void main(String[] args) {
        do {
            Atendimento_Cliente();
            atendimentos++;
            if (atendimentos == 1){
                atendimento = "off";
            }
        } while (atendimento.equals("on"));
    }
}
