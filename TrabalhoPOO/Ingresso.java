package br.com.cyberfestival.model;
public class Ingresso {

    private String codigoId; 
    private String tipo;     
    private double valor;   

    public Ingresso(String codigoId, String tipo, double valor) {
        this.codigoId = codigoId;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getCodigoId() {
        return codigoId;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
}
