package br.com.cybercorp.main;

import br.com.cybercorp.model.Credencial;
import br.com.cybercorp.model.Departamento;
import br.com.cybercorp.model.Funcionario;
import br.com.cybercorp.model.SistemaSeguranca;
import br.com.cybercorp.model.Veiculo;

public class MainTeste {
    public static void main(String[] args) {
        Departamento ti = new Departamento("TI", "Tecnologia da Informação", 3);
        Funcionario func = new Funcionario("F-001", "Carlos", ti);
        Veiculo carro = new Veiculo("ABC-1234", "Civic", func);

        SistemaSeguranca sistema = new SistemaSeguranca(2);

        Credencial c1 = new Credencial("FFF-999", true, func);
        Credencial clone = new Credencial("FFF-999", true, func);

        sistema.registrarCatraca(func);
        sistema.registrarCatraca(func);

        sistema.acessarCofre(c1);
        sistema.acessarCofre(clone);

        sistema.estacionarVeiculo(carro, 0);
        sistema.estacionarVeiculo(carro, 5);
    }
}
