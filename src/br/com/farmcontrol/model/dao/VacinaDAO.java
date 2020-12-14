package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
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

/**Classe para objetos do tipo Vacina, repons�vel pelas manipula��es das Vacinas vindas do Banco de Dados, tais como cadastro, leitura,
 * atualiza��o, exclus�o.
 * @author equipe
 * @version 1.1
 * @since Release 1.2 da aplica��o
 */
public class VacinaDAO {
	
	/** M�todo create, que tem como principal objetivo a inser��o de uma inst�ncia de vacina no Banco de Dados. N�o retorna nada.
	 * @author equipe
	 * @param v - inst�ncia de Vacina.
	 */
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
	
	
	/** M�todo read, que tem como finalidade capturar todas as ocorr�ncias da inst�ncia de Vacina
	 * existentes no Banco de Dados, os dados s�o inseridos em uma list<Vacina> e essa � retornada.
	 * @author equipe
	 * @return List<Vacina> - uma lista com todas as vacinas do Banco de Dados.
	 */
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
	
	
	/** M�todo read que recebe uma inst�ncia do tipo Animal,e a partir desta o id do animal � obtido.
	 *  Tem por finalidade obter inst�ncias de Vacina no banco de dados que tem rela��o com o id do animal passado 
	 *  como par�metro, caso haja essa ocorr�ncia o dado � colocado em uma inst�ncia de Vacina e retornado. 
	 * @author Equipe
	 * @param m - inst�ncia de Animal.
	 * @return List<Vacina> - lista com todas as vacinas do Banco de Dados que pertencem ao id do animal passado como par�metro
	 */
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
    
    
    /** M�todo read que recebe um id como par�tro, tendo como finalidade capturar, se houver, a ocorr�ncia 
     * de uma inst�ncia de Vacina no Banco de Dados, correspondete com o id passado como par�tro. Se encontrado
     * Vacina ser� retornada. 
     * @author Equipe
     * @param id - int
     * @return Vacina - a inst�ncia de Vacina encontrada no banco de dados correspondente ao id passado como par�metro.
     */
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
    
	
	/** M�todo read que recebe uma data como par�metro, e tem o objetivo de captura no Banco de Dados, se houver, vacinas 
	 * que possuem a data especificada, caso haja a ocorr�ncia, as vacinas ser�o adicionadas em uma lista de Vacina e esta ser� 
	 * retornada.
	 * @author Equipe
	 * @param d - Date
	 * @return List<Vacina> - lista de vacinas com data correspondente a passada como par�metro.
	 */
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
	
	
	/** M�todo update recebe como par�metro uma vacina. Tem como finalidade capturar a vacina no Banco de Dados, se houver
	 * uma vacina com o mesmo id da inst�ncia de vacina passada como par�tro. O m�todo pode atualizar todos os dados da 
	 * inst�cia com excess�o do id da vacina. N�o retorna nada.
	 * @author Equipe
	 * @param v - inst�ncia de Vacina.
	 */
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
	
	
	/** M�todo delete que recebe uma int�ncia de Vacina, e tem a op��o de capturar no Banco de Dados, se houver, uma vacina
	 * com o id correspondente ao id da vacina passada como par�metro, caso haja a ocorr�ncia essa vacina ser� deletada do 
	 * Banco de Dados. N�o retorna nada.
	 * @author equipe
	 * @param v - inst�ncia de Vacina.
	 */
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
	
	/**
    * M�todo reportQuery, n�o tem lista de par�metros, e � respons�vel por capturar, se houver,
    * todas as ocorr�ncias de inst�ncias de Vacinas associadas a Animal no Banco de Dados que possuem todas as informa��es
    * desejadas do usu�rio para gerar um relat�rio de Vacina. 
    * @author equipe
    * @return List<Vacina> - Lista de Vacinas encontradas no Banco de Dados que correspondem � busca.
    */
	public static List<Vacina> reportQuery() {
		Connection con = ConnectionFactory.getConnection();
	    String sql = "SELECT animal.idanimal, animal.raca , animal.data_nasc_aqui, vacina.descricao, vacina.data_vacina, vacina.custo FROM animal INNER JOIN vacina on animal.idanimal = vacina.idanimal ORDER BY vacina.data_vacina";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	
	    List<Vacina> vacinas = new ArrayList<>();
	
	    try {
	        stmt = con.prepareStatement(sql);
	        rs = stmt.executeQuery();
	
	        while(rs.next()){
	            Vacina vac = new Vacina();
	            Animal a = new Mamifero();
	            vac.setAnimal(a);
	            a.setId_animal(rs.getInt("idanimal"));
	            a.setRaca(rs.getString("raca"));
	            a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
	            vac.setDescricao(rs.getString("descricao"));
	            vac.setCusto(rs.getFloat("custo"));
	            vac.setData_vacina(rs.getDate("data_vacina"));
	                    
	            vacinas.add(vac);
	        }
	
	    } catch (SQLException ex) {
	        Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, ex);
	    } finally{
	        ConnectionFactory.closeConnection(con, stmt, rs);
	    }
	
	    return vacinas; 
	}  
}
        
