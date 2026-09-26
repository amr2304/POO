package br.com.cyberfestival.model;

public class IngressoInvalidoException extends RuntimeException {
    public IngressoInvalidoException() {
        super("Erro de Segurança: Ingresso já validado ou código duplicado!");
    }
}
