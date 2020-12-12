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
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Reproducao;

public class ReproducaoDAO {
	
	public static void create(Reproducao reprod) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO REPRODUCAO(idanimal,quantidade,descricao, data_reprod) VALUES(?,?,?,?)");
			stmt.setInt(1, reprod.getAnimal().getId_animal());
			stmt.setInt(2, reprod.getQntd_reproducao() );
			stmt.setString(3, reprod.getDescricao_reprod());
			stmt.setDate(4, (Date) reprod.getData_reproducao() );
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "erro ao salvar"+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	
	public static List<Reproducao> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List <Reproducao> reproducoes = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM reproducao");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Mamifero m = new Mamifero();
				Reproducao reproducao = new Reproducao();
				
				reproducao.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				
				reproducao.setId_repoducao(rs.getInt("idreprod"));
				reproducao.setQntd_reproducao(rs.getInt("quantidade"));
				reproducao.setDescricao_reprod(rs.getString("descricao"));
				reproducao.setData_reproducao(rs.getDate("data_reprod"));
		
				reproducoes.add(reproducao);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return reproducoes;

	}
	
	public static Reproducao read(int id){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM reproducao WHERE idreprod=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Reproducao reproducao = new Reproducao();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            Mamifero m = new Mamifero();
            reproducao.setId_repoducao(id);
            reproducao.setAnimal(m);
            m.setId_animal(rs.getInt("idanimal"));
            reproducao.setDescricao_reprod(rs.getString("descricao"));
            reproducao.setQntd_reproducao(rs.getInt("quantidade"));
            reproducao.setData_reproducao(rs.getDate("data_reprod"));

            
        } catch (SQLException ex) {
            Logger.getLogger(ReproducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return reproducao;
    }

	public static List<Reproducao> read(Date d){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM reproducao WHERE data_reprod=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Reproducao> reproducoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, d);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reproducao reprod = new Reproducao();
                Mamifero m = new Mamifero();
                m.setId_animal(rs.getInt("idanimal"));
                reprod.setAnimal(m);
                reprod.setData_reproducao(d);
                reprod.setDescricao_reprod(rs.getString("descricao"));
                reprod.setId_repoducao(rs.getInt("idreprod"));
                reprod.setQntd_reproducao(rs.getInt("quantidade"));
                

                reproducoes.add(reprod);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReproducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return reproducoes;
    }
        
        public static List<Reproducao> read(Animal m){
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM reproducao WHERE idanimal=? ORDER BY idreprod";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            List<Reproducao> reproducoes = new ArrayList<>();
            
            try {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, m.getId_animal());
                rs = stmt.executeQuery();
            
            while(rs.next()){
                Reproducao reprod = new Reproducao();
                reprod.setAnimal(m);
                reprod.setData_reproducao(rs.getDate("data_reprod"));
                reprod.setDescricao_reprod(rs.getString("descricao"));
                reprod.setId_repoducao(rs.getInt("idreprod"));
                reprod.setQntd_reproducao(rs.getInt("quantidade"));
                

                reproducoes.add(reprod);
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(ReproducaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
            
            
            return reproducoes;
        }

	public static List<Reproducao> read(String descricao){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM reproducao WHERE descricao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Reproducao> reproducoes = new ArrayList<>();
        
		try {
			stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
			
			while(rs.next()){
				Reproducao reproducao = new Reproducao();
				Mamifero m = new Mamifero();
				
				reproducao.setAnimal(m);
				m.setId_animal(rs.getInt("idanimal"));
				reproducao.setDescricao_reprod(descricao);
				reproducao.setData_reproducao(rs.getDate("data_reprod"));
				reproducao.setId_repoducao(rs.getInt("idreprod"));
				reproducao.setQntd_reproducao(rs.getInt("quantidade"));
				
				reproducoes.add(reproducao);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return reproducoes;
	}
	
	public static void update(Reproducao rep){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE reproducao "
                   + "SET descricao=?,quantidade=?,data_reprod=?"
                   + "WHERE idreprod=?";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, rep.getDescricao_reprod());
            stmt.setInt(2, rep.getQntd_reproducao());
            stmt.setDate(3, (Date) rep.getData_reproducao());
            stmt.setInt(4,rep.getId_repoducao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }


	public static void delete(Reproducao reprod){
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM reproducao WHERE idreprod=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, reprod.getId_repoducao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
