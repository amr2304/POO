package br.com.starlog.model;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int estoque;
    public Produto(Long id, String nome, double preco, int estoque) { this.id=id; setNome(nome); setPreco(preco); setEstoque(estoque); }
    public Produto(String nome, double preco, int estoque) { this(null,nome,preco,estoque); }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String nome){if(nome==null||nome.trim().length()<3)throw new IllegalArgumentException("Nome deve ter ao menos 3 caracteres.");this.nome=nome.trim();}
    public double getPreco(){return preco;} public void setPreco(double preco){if(!Double.isFinite(preco)||preco<=0)throw new IllegalArgumentException("Preço deve ser maior que zero.");this.preco=preco;}
    public int getEstoque(){return estoque;} public void setEstoque(int estoque){if(estoque<0)throw new IllegalArgumentException("Estoque não pode ser negativo.");this.estoque=estoque;}
    @Override public String toString(){return String.format("%-4s | %-30s | R$ %8.2f | %4d",id,nome,preco,estoque);}
}
