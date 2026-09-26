package br.com.cyberfestival.model;

import java.util.HashMap;
import java.util.Map;

public class Bilheteria {
    private Map<String, Ingresso> ingressosVendidos = new HashMap<>();

    public void venderIngresso(Ingresso ingresso) {
        if (ingressosVendidos.containsKey(ingresso.getCodigoId())) {
            throw new IngressoInvalidoException();
        }
        ingressosVendidos.put(ingresso.getCodigoId(), ingresso);
    }
    public double calcularReceitaVIP() {
        return  ingressosVendidos.values().stream().filter(ingresso -> ingresso.getTipo().equals("VIP")).mapToDouble(Ingresso::getValor).sum(); 
    }
}
