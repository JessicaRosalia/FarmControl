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

/**
* Classe responsável pela manipulação dos dados a respeito da produção de Leite vindos do Banco de Dados, como cadastro, leitura, atualização e exclusão.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplicação
*/
public class LeiteDAO {
	

	/**
	* Método create, responsável por inserir no Banco de Dados uma instância de Leite(uma produção, e não uma unidade). 
	* Não retorna nada.
	* @author equipe
	* @param leite - instância de Leite
	*/
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
	
	/**
	* Método read, responsável por capturar, se houver, todas as ocorrências da instância de produção de Leite
	* existentes no Banco de Dados inserindo em um List, para depois retorná-lo.
	* @author equipe
	* @return List<Leite> - um list com todos os leites do Banco de Dados.
	*/
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
	
	/**
	* Método read que recebe um id como paramêtro, e é responsável por capturar, se houver, a ocorrência de produção Leite
	* existente no Banco de Dados que corresponde ao id passado como paramêtro. Se encontrado, Leite será retornado.
	* @author equipe
	* @param id - id da produção de leite buscada
	* @return Leite - A produção de Leite encontrada no Banco de Dados que corresponde ao id passado.
	*/
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

	/**
	* Método read que recebe uma data como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de instâncias de Leite com aquela data passada como paramêtro. 
	* Se encontrado, uma lista dos Leites será retornada.
	* @author equipe
	* @param d - data a ser buscada nas produções leites
	* @return List<Leite> - Lista de produções de Leite encontradas no Banco de Dados que correspondem à busca.
	*/
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

	
	/**
	* Método read que recebe um Mamífero como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de instâncias de Leite no Banco de Dados com aquele mamífero associado.
	* Se encontrado, uma lista com os Leites será retornada.
	* @author equipe
	* @param m - Mamífero a ter suas produções de Leite buscadas.
	* @return List<Leite> - Lista de produções de Leites encontradas no Banco de Dados que correspondem à busca.
	*/
    public static List<Leite> read(Mamifero m){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM leite WHERE idanimal=? ORDER BY idleite";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Leite> leites = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, m.getId_animal());
            rs = stmt.executeQuery();
        
        while(rs.next()){
            Leite leite = new Leite();
            leite.setAnimal(m);
            leite.setData_producao(rs.getDate("data_leite"));
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

	/**
	* Método update que é responsável por atualizar a produção de leite recebido como paramêtro,
	* com as novas informações vindas com ele. A atualização é refletida no Banco de Dados. Não retorna nada.
	* @author equipe
	* @param l - Produção de Leite a ser atualizada.
	*/
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
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
	
	
	/**
	* Método delete que é responsável por deletar uma produção leite recebida como paramêtro do Banco de Dados. Nâo retorna nada.
	* @author equipe
	* @param r - Produção de Leite a ser deletada.
	*/
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
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
