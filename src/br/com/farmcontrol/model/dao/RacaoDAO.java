package br.com.farmcontrol.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Ovo;
import br.com.farmcontrol.model.vo.Racao;

public class RacaoDAO {

	public static void create(Racao racao) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO RACAO(idanimal,quantidade,descricao, custo, data_racao) VALUES(?,?,?,?,?)");
			stmt.setInt(1, racao.getAnimal().getId_animal());
			stmt.setInt(2, racao.getQtd_racao() );
			stmt.setString(3, racao.getDescricao() );
			stmt.setFloat(4, racao.getCusto() );
			stmt.setDate(5, (Date) racao.getData() );
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "erro ao salvar"+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static List<Racao> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List <Racao> racoes = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM racao");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Racao racao = new Racao();
				Mamifero m = new Mamifero();
				
				racao.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				
				racao.setId_racao(rs.getInt("idracao"));
				racao.setDescricao(rs.getString("descricao"));
				racao.setQtd_racao(rs.getInt("quantidade"));
				racao.setCusto(rs.getFloat("custo"));
				racao.setData(rs.getDate("data_racao"));
				
				racoes.add(racao);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return racoes;
	}
	
	public static Racao read(int id){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM racao WHERE idracao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Racao racao = new Racao();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            Mamifero m = new Mamifero();
            racao.setId_racao(id);
            racao.setAnimal(m);
            m.setId_animal(rs.getInt("idanimal"));
            racao.setCusto(rs.getFloat("custo"));
            racao.setData(rs.getDate("data_racao"));
            racao.setQtd_racao(rs.getInt("quantidade"));
            racao.setDescricao(rs.getString("descricao"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ReproducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return racao;
    }
	
	public static List<Racao> read(String descricao){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM racao WHERE descricao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Racao> racoes = new ArrayList<>();
        
		try {
			stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
			
			while(rs.next()){
				Racao racao = new Racao();
				Mamifero m = new Mamifero();
				
				racao.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				racao.setDescricao(descricao);
				racao.setId_racao(rs.getInt("idracao"));
				racao.setQtd_racao(rs.getInt("quantidade"));
				racao.setCusto(rs.getFloat("custo"));
				racao.setData(rs.getDate("data_racao"));
				
				racoes.add(racao);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return racoes;
	}
	
	public static List<Racao> read(Date d){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM racao WHERE data_racao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Racao> racoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, d);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Racao racao = new Racao();
                Mamifero m = new Mamifero();
                m.setId_animal(rs.getInt("idanimal"));
                racao.setAnimal(m);
                racao.setCusto(rs.getFloat("custo"));
                racao.setData(d);
                racao.setDescricao(rs.getString("descricao"));
                racao.setId_racao(rs.getInt("idracao"));
                racao.setQtd_racao(rs.getInt("quantidade"));

                racoes.add(racao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return racoes;
    }

	
	public static void update(Racao r){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE racao "
                   + "SET custo=?,quantidade=?,data_racao=?,descricao=?"
                   + "WHERE idracao=?";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, r.getCusto());
            stmt.setInt(2, r.getQtd_racao());
            stmt.setDate(3, (Date) r.getData());
            stmt.setString(4, r.getDescricao());
            stmt.setInt(5,r.getId_racao());
            stmt.executeUpdate();
            JOptionPane.showConfirmDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

	
}
