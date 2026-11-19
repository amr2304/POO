package br.com.bookstore.dao;

import br.com.bookstore.factory.ConexaoFactory;
import br.com.bookstore.model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAOSqlite implements LivroDAO {
    public LivroDAOSqlite() {
        String sql = "CREATE TABLE IF NOT EXISTS livros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, autor TEXT NOT NULL, preco REAL NOT NULL);";
        try (Connection c = ConexaoFactory.obterConexao(); Statement s = c.createStatement()) { s.execute(sql); }
        catch (SQLException e) { throw new IllegalStateException("Erro ao criar tabela livros.", e); }
    }
    private Livro mapearLinha(ResultSet rs) throws SQLException {
        return new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getDouble("preco"));
    }
    @Override public void salvar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, preco) VALUES (?, ?, ?);";
        try (Connection c = ConexaoFactory.obterConexao(); PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, livro.getTitulo()); s.setString(2, livro.getAutor()); s.setDouble(3, livro.getPreco()); s.executeUpdate();
            try (ResultSet keys = s.getGeneratedKeys()) { if (keys.next()) livro.setId(keys.getLong(1)); }
        } catch (SQLException e) { throw new IllegalStateException("Erro ao salvar livro.", e); }
    }
    @Override public Optional<Livro> buscarPorId(Long id) {
        String sql = "SELECT id, titulo, autor, preco FROM livros WHERE id = ?;";
        try (Connection c = ConexaoFactory.obterConexao(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setLong(1, id);
            try (ResultSet rs = s.executeQuery()) { return rs.next() ? Optional.of(mapearLinha(rs)) : Optional.empty(); }
        } catch (SQLException e) { throw new IllegalStateException("Erro ao consultar livro.", e); }
    }
    @Override public List<Livro> listarTodos() {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, preco FROM livros ORDER BY titulo ASC;";
        try (Connection c = ConexaoFactory.obterConexao(); PreparedStatement s = c.prepareStatement(sql); ResultSet rs = s.executeQuery()) {
            while (rs.next()) lista.add(mapearLinha(rs));
            return lista;
        } catch (SQLException e) { throw new IllegalStateException("Erro ao listar livros.", e); }
    }
}
