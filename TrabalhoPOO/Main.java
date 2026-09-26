package br.com.cyberfestival.main;

import br.com.cyberfestival.model.Bilheteria;
import br.com.cyberfestival.model.Ingresso;
import br.com.cyberfestival.model.IngressoInvalidoException;
public class Main {
        public static void main(String[] args) {

        Bilheteria bilheteria = new Bilheteria();
        Ingresso ingresso1 = new Ingresso("A-01", "VIP", 500.00);
        Ingresso ingresso2 = new Ingresso("A-02", "PISTA", 200.00);
        Ingresso ingresso3 = new Ingresso("A-01", "PISTA", 200.00);

        bilheteria.venderIngresso(ingresso1);
        bilheteria.venderIngresso(ingresso2);

        try {
            bilheteria.venderIngresso(ingresso3);
        } catch (IngressoInvalidoException e) {
  
            System.out.println(e.getMessage());
        }
        double receitaVIP = bilheteria.calcularReceitaVIP();
        System.out.println("Receita VIP: " + receitaVIP);
    }
}
