package br.com.starlog.dao;

import br.com.starlog.factory.ConexaoFactory;
import br.com.starlog.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAOSqlite implements ProdutoDAO {
    public ProdutoDAOSqlite(){
        String sql="CREATE TABLE IF NOT EXISTS produtos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, preco REAL NOT NULL, estoque INTEGER NOT NULL);";
        try(Connection c=ConexaoFactory.obterConexao();Statement s=c.createStatement()){s.execute(sql);}
        catch(SQLException e){throw new IllegalStateException("Erro ao criar tabela produtos.",e);}
    }
    private Produto mapearLinha(ResultSet rs)throws SQLException{return new Produto(rs.getLong("id"),rs.getString("nome"),rs.getDouble("preco"),rs.getInt("estoque"));}
    @Override public void salvar(Produto p){
        String sql="INSERT INTO produtos (nome, preco, estoque) VALUES (?, ?, ?);";
        try(Connection c=ConexaoFactory.obterConexao();PreparedStatement s=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            s.setString(1,p.getNome());s.setDouble(2,p.getPreco());s.setInt(3,p.getEstoque());s.executeUpdate();
            try(ResultSet keys=s.getGeneratedKeys()){if(keys.next())p.setId(keys.getLong(1));}
        }catch(SQLException e){throw new IllegalStateException("Erro ao salvar produto.",e);}
    }
    @Override public Optional<Produto> buscarPorId(Long id){
        String sql="SELECT id,nome,preco,estoque FROM produtos WHERE id = ?;";
        try(Connection c=ConexaoFactory.obterConexao();PreparedStatement s=c.prepareStatement(sql)){
            s.setLong(1,id);try(ResultSet rs=s.executeQuery()){return rs.next()?Optional.of(mapearLinha(rs)):Optional.empty();}
        }catch(SQLException e){throw new IllegalStateException("Erro ao consultar produto.",e);}
    }
    @Override public List<Produto> listarTodos(){
        List<Produto> lista=new ArrayList<>();String sql="SELECT id,nome,preco,estoque FROM produtos ORDER BY nome ASC;";
        try(Connection c=ConexaoFactory.obterConexao();PreparedStatement s=c.prepareStatement(sql);ResultSet rs=s.executeQuery()){
            while(rs.next())lista.add(mapearLinha(rs));return lista;
        }catch(SQLException e){throw new IllegalStateException("Erro ao listar produtos.",e);}
    }
    @Override public boolean atualizar(Produto p){
        String sql="UPDATE produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?;";
        try(Connection c=ConexaoFactory.obterConexao();PreparedStatement s=c.prepareStatement(sql)){
            s.setString(1,p.getNome());s.setDouble(2,p.getPreco());s.setInt(3,p.getEstoque());s.setLong(4,p.getId());
            int linhasAfetadas=s.executeUpdate();return linhasAfetadas>0;
        }catch(SQLException e){throw new IllegalStateException("Erro ao atualizar produto.",e);}
    }
    @Override public boolean excluir(Long id){
        String sql="DELETE FROM produtos WHERE id = ?;";
        try(Connection c=ConexaoFactory.obterConexao();PreparedStatement s=c.prepareStatement(sql)){
            s.setLong(1,id);int linhasAfetadas=s.executeUpdate();return linhasAfetadas>0;
        }catch(SQLException e){throw new IllegalStateException("Erro ao excluir produto.",e);}
    }
}
