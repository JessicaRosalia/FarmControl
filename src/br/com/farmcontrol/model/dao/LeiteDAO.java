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
* Classe respons�vel pela manipula��o dos dados a respeito da produ��o de Leite vindos do Banco de Dados, como cadastro, leitura, atualiza��o e exclus�o.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplica��o
*/
public class LeiteDAO {
	

	/**
	* M�todo create, respons�vel por inserir no Banco de Dados uma inst�ncia de Leite(uma produ��o, e n�o uma unidade). 
	* N�o retorna nada.
	* @author equipe
	* @param leite - inst�ncia de Leite
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
	* M�todo read, respons�vel por capturar, se houver, todas as ocorr�ncias da inst�ncia de produ��o de Leite
	* existentes no Banco de Dados inserindo em um List, para depois retorn�-lo.
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
	* M�todo read que recebe um id como param�tro, e � respons�vel por capturar, se houver, a ocorr�ncia de produ��o Leite
	* existente no Banco de Dados que corresponde ao id passado como param�tro. Se encontrado, Leite ser� retornado.
	* @author equipe
	* @param id - id da produ��o de leite buscada
	* @return Leite - A produ��o de Leite encontrada no Banco de Dados que corresponde ao id passado.
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
	* M�todo read que recebe uma data como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de inst�ncias de Leite com aquela data passada como param�tro. 
	* Se encontrado, uma lista dos Leites ser� retornada.
	* @author equipe
	* @param d - data a ser buscada nas produ��es leites
	* @return List<Leite> - Lista de produ��es de Leite encontradas no Banco de Dados que correspondem � busca.
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
	* M�todo read que recebe um Mam�fero como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de inst�ncias de Leite no Banco de Dados com aquele mam�fero associado.
	* Se encontrado, uma lista com os Leites ser� retornada.
	* @author equipe
	* @param m - Mam�fero a ter suas produ��es de Leite buscadas.
	* @return List<Leite> - Lista de produ��es de Leites encontradas no Banco de Dados que correspondem � busca.
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
	* M�todo update que � respons�vel por atualizar a produ��o de leite recebido como param�tro,
	* com as novas informa��es vindas com ele. A atualiza��o � refletida no Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param l - Produ��o de Leite a ser atualizada.
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
	* M�todo delete que � respons�vel por deletar uma produ��o leite recebida como param�tro do Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param r - Produ��o de Leite a ser deletada.
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
