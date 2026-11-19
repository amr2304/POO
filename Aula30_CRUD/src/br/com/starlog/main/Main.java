package br.com.starlog.main;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ProdutoDAO dao=new ProdutoDAOSqlite();
        Produto teclado=new Produto("Teclado Mecânico RGB",259.90,8);
        Produto mousepad=new Produto("Mousepad Speed XL",79.90,20);
        dao.salvar(teclado);dao.salvar(mousepad);
        System.out.println("IDs gerados: teclado="+teclado.getId()+", mousepad="+mousepad.getId());
        System.out.println("ID | PRODUTO | PREÇO | ESTOQUE");dao.listarTodos().forEach(System.out::println);
        teclado.setPreco(289.90);teclado.setEstoque(15);
        boolean atualizado=dao.atualizar(teclado);System.out.println("Atualização: "+atualizado);
        if(atualizado)dao.buscarPorId(teclado.getId()).ifPresent(System.out::println);
        System.out.println("Exclusão do Mousepad: "+dao.excluir(mousepad.getId()));
        System.out.println("Exclusão do ID 888: "+dao.excluir(888L)+" (esperado: false)");
        List<Produto> restantes=dao.listarTodos();System.out.println("Estado final: "+restantes.size()+" produto(s)");restantes.forEach(System.out::println);
    }
}
