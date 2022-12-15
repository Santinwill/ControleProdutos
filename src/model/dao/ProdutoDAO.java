package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Produto;

public class ProdutoDAO {
	public void create(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO produto"
					+ "(descricao, quantidade, preco)"
					+ "VALUES(?,?,?)");
			stmt.setString(1, p.getDescricao());
			stmt.setInt(2, p.getQuantidade());
			stmt.setFloat(3, p.getPreco());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Produto> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM produto");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getFloat("preco"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao ler lista: " + e);
		}		finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return produtos;
	}
	
	public void update(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE produto SET descricao = ?, quantidade = ?, preco = ? WHERE id = ?");
			stmt.setString(1, p.getDescricao());
			stmt.setInt(2, p.getQuantidade());
			stmt.setFloat(3, p.getPreco());
			stmt.setInt(4, p.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Excluido: " + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
