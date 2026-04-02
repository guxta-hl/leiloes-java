/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        String sql = "INSERT INTO produtos (id, nome, valor, status) VALUES (default, ?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            
            stmt.executeUpdate();

           return true;
        
        
        }catch (SQLException e) {
        	System.out.println("Erro ao salvar: " + e.getMessage());
        	return false;
    	}
    }
    
    public List<ProdutosDTO> listarProdutos(){
        
        List<ProdutosDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = new conectaDAO().connectDB();
        		PreparedStatement stmt = conn.prepareStatement(sql); 
        		ResultSet rs = stmt.executeQuery()){
        	
            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }
    
    public void venderProduto(int id) {

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (Connection conn = new conectaDAO().connectDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Produto vendido!");

        } catch (SQLException e) {
            System.out.println("Erro ao vender: " + e.getMessage());
        }
    }
    public List<ProdutosDTO> listarProdutosVendidos() {

        List<ProdutosDTO> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try (Connection conn = new conectaDAO().connectDB();
        		PreparedStatement stmt = conn.prepareStatement(sql); 
        		ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendidos: " + e.getMessage());
       }
        return lista;
    }

}

