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
import br.com.farmcontrol.model.vo.Leite;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;


public class LeiteDAO {
	
	public static void create(Leite leite) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO LEITE(idanimal, valor_litro, quantidade, data_leite) VALUES(?,?,?,?)");
			stmt.setInt(1, leite.getAnimal().getId_animal());
			stmt.setFloat(2, leite.getValor_litro() );
			stmt.setInt(3, leite.getQtd_leite() );
			stmt.setDate(4,(Date) leite.getData_producao());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "erro ao salvar"+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static List<Leite> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List <Leite> leites = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM leite");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Leite leite = new Leite();
				Mamifero m = new Mamifero();
				
				leite.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				
				leite.setId_leite(rs.getInt("idleite"));
				leite.setValor_litro(rs.getFloat("valor_litro"));
				leite.setQtd_leite(rs.getInt("quantidade"));
				leite.setData_producao(rs.getDate("data_leite"));

				
				leites.add(leite);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return leites;

	}
	
	public static Leite read(int id){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM leite WHERE idleite=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Leite leite = new Leite();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            Mamifero m = new Mamifero();
            leite.setId_leite(id);
            leite.setAnimal(m);
            m.setId_animal(rs.getInt("idanimal"));
            leite.setData_producao(rs.getDate("data_leite"));
            leite.setQtd_leite(rs.getInt("quantidade"));
            leite.setValor_litro(rs.getFloat("valor_litro"));
            
        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return leite;
    }

	public static List<Leite> read(Date d){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM leite WHERE data_leite=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Leite> leites = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, d);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Leite leite = new Leite();
                Mamifero m = new Mamifero();
                m.setId_animal(rs.getInt("idanimal"));
                leite.setAnimal(m);
                leite.setData_producao(d);
                leite.setId_leite(rs.getInt("idleite"));
                leite.setQtd_leite(rs.getInt("quantidade"));
                leite.setValor_litro(rs.getFloat("valor_litro"));
            

                leites.add(leite);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LeiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return leites;
    }


	public static void update(Leite l){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE leite "
                   + "SET valor_litro=?,quantidade=?,data_leite=?"
                   + "WHERE idleite=?";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, l.getValor_litro());
            stmt.setInt(2, l.getQtd_leite());
            stmt.setDate(3, (Date) l.getData_producao());
            stmt.setInt(4,l.getId_leite());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
	
	
	public static void delete(Leite r){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM leite WHERE idleite=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, r.getId_leite());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
