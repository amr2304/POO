package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

public class Main {
    public static void main(String[] args) {
        LivroDAO dao = new LivroDAOSqlite();
        Livro[] obras = { new Livro("Clean Code", "Robert C. Martin", 89.90),
                new Livro("Design Patterns", "Erich Gamma et al.", 119.90),
                new Livro("Refactoring", "Martin Fowler", 99.90) };
        for (Livro livro : obras) { dao.salvar(livro); System.out.println("ID gerado: " + livro.getId()); }
        dao.buscarPorId(2L).ifPresentOrElse(l -> System.out.println("ID 2: " + l), () -> System.out.println("ID 2 não encontrado."));
        dao.buscarPorId(999L).ifPresentOrElse(System.out::println, () -> System.out.println("ID 999 não encontrado; Optional vazio."));
        System.out.println("ID | TÍTULO | AUTOR | PREÇO");
        dao.listarTodos().forEach(System.out::println);
    }
}
