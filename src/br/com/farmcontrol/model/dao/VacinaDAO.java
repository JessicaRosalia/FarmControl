package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;
import br.com.farmcontrol.model.vo.Vacina;
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

public class VacinaDAO {
	
	public static void create(Vacina v) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO VACINA(idanimal,data_vacina, descricao, custo) VALUES (?,?,?,?)");
			stmt.setInt(1, v.getAnimal().getId_animal());
			stmt.setDate(2,(Date) v.getData_vacina());
			stmt.setString(3, v.getDescricao());
			stmt.setFloat(4, v.getCusto());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, " Erro ao salvar: "+e);
		} finally {
			 ConnectionFactory.closeConnection(con, stmt);
		}
		
	} 
	
	public static List<Vacina> read(){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Vacina> vacinas = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT *FROM vacina");
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				
				Mamifero m = new Mamifero();
				Vacina v = new Vacina();
				v.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				v.setId_vacina(rs.getInt("idvacina"));
				v.setData_vacina(rs.getDate("data_vacina"));
				v.setDescricao(rs.getString("descricao"));
				v.setCusto(rs.getFloat("custo"));
				vacinas.add(v);
				
			}
		}catch (SQLException e) {
				Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return vacinas;
	} 
	
        public static List<Vacina> read(Animal m){
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM vacina WHERE idanimal=? ORDER BY idvacina";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<Vacina> vacinas = new ArrayList<>();
            
            try {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, m.getId_animal());
                rs = stmt.executeQuery();
            
            while(rs.next()){
                Vacina r = new Vacina();
                r.setAnimal(m);
                r.setData_vacina(rs.getDate("data_vacina"));
                r.setId_vacina(rs.getInt("idvacina"));
                r.setDescricao(rs.getString("descricao"));
                r.setCusto(rs.getFloat("custo"));

                vacinas.add(r);
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            
            
            return vacinas;
        }
        
	public static Vacina read(int id){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vacina v = new Vacina();
		
	    try {
	    	stmt = con.prepareStatement("SELECT * FROM vacina WHERE idvacina=?");
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	        
	        if(!rs.next()){
	            //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
	            throw new NullPointerException("Item inexistente!");
	        };

	    
	        Mamifero m = new Mamifero();
			v.setAnimal(m);
			m.setId_animal(id);
			v.setId_vacina(rs.getInt("idvacina"));
			v.setData_vacina(rs.getDate("data_vacina"));
			v.setDescricao(rs.getString("descricao"));
			v.setCusto(rs.getFloat("custo"));

	        
	    }catch (SQLException e) {
			Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
	} finally {
		ConnectionFactory.closeConnection(con, stmt, rs);
	}
	    
	    return v;
	} 
        
	public static List<Vacina> read(Date d){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Vacina> vacinas = new ArrayList<>();
		    
	    try {
	    	stmt = con.prepareStatement("SELECT *FROM vacina WHERE data_vacina=?");
	        stmt.setDate(1, d);
	        rs = stmt.executeQuery();
	        
	        while(rs.next()){
	            Vacina v = new Vacina();
	            Mamifero m = new Mamifero();
				v.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				v.setId_vacina(rs.getInt("idvacina"));
				v.setData_vacina(d);
				v.setDescricao(rs.getString("descricao"));
				v.setCusto(rs.getFloat("custo"));
				vacinas.add(v);
	        }
	        
	    }catch (SQLException e) {
			Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
	} finally {
		ConnectionFactory.closeConnection(con, stmt, rs);
	}
	    
	    return vacinas;
	} 
		
	public static void update(Vacina v){
	    
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
	    try {
	    	stmt = con.prepareStatement("UPDATE vacina SET data_vacina=?, descricao=?, custo=? WHERE idvacina=?");
	        stmt.setDate(1,(Date) v.getData_vacina());
	        stmt.setString(2, v.getDescricao());
	        stmt.setFloat(3, v.getCusto());
                stmt.setInt(4, v.getId_vacina());
	        stmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+e);
	    
	    }finally{
	        ConnectionFactory.closeConnection(con, stmt);
	    }

	}
	
	public static void delete(Vacina v){
	    
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement stmt = null;
	    
	    try {
	    	stmt = con.prepareStatement("DELETE FROM vacina WHERE idvacina=?");
	    	stmt.setInt(1, v.getId_vacina());
	        stmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, " Erro ao deletar: "+e);
	    } finally{
	        ConnectionFactory.closeConnection(con, stmt);
	    }
	}       
}
        
