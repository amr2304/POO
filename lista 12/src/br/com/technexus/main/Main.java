package br.com.technexus.main;

import br.com.technexus.model.Loja;
import br.com.technexus.model.Produto;

public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja();

        loja.cadastrar(new Produto("The Witcher", "GAMES", 150));
        loja.cadastrar(new Produto("FIFA", "GAMES", 200));
        loja.cadastrar(new Produto("Java for Dummies", "LIVROS", 100));
        loja.cadastrar(new Produto("Clean Code", "LIVROS", 80));
        loja.cadastrar(new Produto("Mouse", "HARDWARE", 50));

        System.out.println(loja.buscarPorCategoria("GAMES"));
        System.out.println(loja.calcularPatrimonioTotal());
        System.out.println(loja.calcularTotalPorCategoria("LIVROS"));
    }
}
