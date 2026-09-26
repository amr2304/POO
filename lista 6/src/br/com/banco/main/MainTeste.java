package br.com.banco.main;

import br.com.banco.model.Agencia;
import br.com.banco.model.Cliente;
import br.com.banco.model.ContaBancaria;

public class MainTeste {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("111.111.111-11", "Ana", "ana@email.com");
        Cliente cliente2 = new Cliente("111.111.111-11", "Ana Duplicada", "outro@email.com");

        if (cliente1.equals(cliente2)) {
            System.out.println("Os clientes são iguais!");
        } else {
            System.out.println("Os clientes são diferentes!");
        }

        ContaBancaria conta = new ContaBancaria("0001", cliente1, 50.0);

        boolean resultadoSaque = conta.sacar(50.0);
        System.out.println("Resultado do saque: " + resultadoSaque);

        System.out.println("Total de contas abertas: " + Agencia.getTotalContasAbertas());
    }
}
