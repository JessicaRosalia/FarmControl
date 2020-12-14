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

/**Classe para objetos do tipo Vacina, reponsável pelas manipulações das Vacinas vindas do Banco de Dados, tais como cadastro, leitura,
 * atualização, exclusão.
 * @author equipe
 * @version 1.1
 * @since Release 1.2 da aplicação
 */
public class VacinaDAO {
	
	/** Método create, que tem como principal objetivo a inserção de uma instância de vacina no Banco de Dados. Não retorna nada.
	 * @author equipe
	 * @param v - instância de Vacina.
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
	
	
	/** Método read, que tem como finalidade capturar todas as ocorrências da instância de Vacina
	 * existentes no Banco de Dados, os dados são inseridos em uma list<Vacina> e essa é retornada.
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
	
	
	/** Método read que recebe uma instância do tipo Animal,e a partir desta o id do animal é obtido.
	 *  Tem por finalidade obter instâncias de Vacina no banco de dados que tem relação com o id do animal passado 
	 *  como parâmetro, caso haja essa ocorrência o dado é colocado em uma instância de Vacina e retornado. 
	 * @author Equipe
	 * @param m - instância de Animal.
	 * @return List<Vacina> - lista com todas as vacinas do Banco de Dados que pertencem ao id do animal passado como parâmetro
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
    
    
    /** Método read que recebe um id como parâtro, tendo como finalidade capturar, se houver, a ocorrência 
     * de uma instância de Vacina no Banco de Dados, correspondete com o id passado como parâtro. Se encontrado
     * Vacina será retornada. 
     * @author Equipe
     * @param id - int
     * @return Vacina - a instância de Vacina encontrada no banco de dados correspondente ao id passado como parâmetro.
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
    
	
	/** Método read que recebe uma data como parâmetro, e tem o objetivo de captura no Banco de Dados, se houver, vacinas 
	 * que possuem a data especificada, caso haja a ocorrência, as vacinas serão adicionadas em uma lista de Vacina e esta será 
	 * retornada.
	 * @author Equipe
	 * @param d - Date
	 * @return List<Vacina> - lista de vacinas com data correspondente a passada como parâmetro.
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
	
	
	/** Método update recebe como parâmetro uma vacina. Tem como finalidade capturar a vacina no Banco de Dados, se houver
	 * uma vacina com o mesmo id da instância de vacina passada como parâtro. O método pode atualizar todos os dados da 
	 * instâcia com excessão do id da vacina. Não retorna nada.
	 * @author Equipe
	 * @param v - instância de Vacina.
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
	
	
	/** Método delete que recebe uma intância de Vacina, e tem a opção de capturar no Banco de Dados, se houver, uma vacina
	 * com o id correspondente ao id da vacina passada como parâmetro, caso haja a ocorrência essa vacina será deletada do 
	 * Banco de Dados. Não retorna nada.
	 * @author equipe
	 * @param v - instância de Vacina.
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
    * Método reportQuery, não tem lista de parâmetros, e é responsável por capturar, se houver,
    * todas as ocorrências de instâncias de Vacinas associadas a Animal no Banco de Dados que possuem todas as informações
    * desejadas do usuário para gerar um relatório de Vacina. 
    * @author equipe
    * @return List<Vacina> - Lista de Vacinas encontradas no Banco de Dados que correspondem à busca.
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
        
