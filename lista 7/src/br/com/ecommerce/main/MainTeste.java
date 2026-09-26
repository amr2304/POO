package br.com.ecommerce.main;

import br.com.ecommerce.exception.TipoFreteInvalidoException;
import br.com.ecommerce.model.CalculadoraFrete;
import br.com.ecommerce.model.FreteMotoboy;
import br.com.ecommerce.model.FretePac;
import br.com.ecommerce.model.FreteSedex;

public class MainTeste {
    public static void main(String[] args) {
        CalculadoraFrete calculadora = new CalculadoraFrete();

        try {
            double pedido = 100.00;

            System.out.println("Frete Sedex: " + calculadora.processarFrete(pedido, new FreteSedex()));
            System.out.println("Frete Pac: " + calculadora.processarFrete(pedido, new FretePac()));
            System.out.println("Frete Motoboy: " + calculadora.processarFrete(pedido, new FreteMotoboy()));

            calculadora.processarFrete(pedido, null);
        } catch (TipoFreteInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
