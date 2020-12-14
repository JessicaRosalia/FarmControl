package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.LoteAves;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**Classe para objetos do tipo LoteAves,reponsável pelas manipulações dos lotesAves vindos do Banco de Dados, tais como cadastro, leitura,
 * atualização, exclusão.
 * @author equipe
 * @version 1.1
 * @since Release 1.2 da aplicação
 */
public class LoteAvesDAO {
	
	
	/** Método create, que tem como principal objetivo a inserção de uma instância de LoteAves no Banco de Dados.
	 *  Não retorna nada.
	 * @author equipe
	 * @param l - instância de LoteAves.
	 */
	public static void create (LoteAves l) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO lote_aves(idanimal,tipo,quantidade) VALUES (?,?,?)");
			AnimalDAO.create(l);
			stmt.setInt(1,MamiferoDAO.ultimoID());
			stmt.setString(2, l.getTipo_ave());
			stmt.setInt(3, l.getQuantidade());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, " Erro ao salvar: "+e);
		} finally {
			 ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
	/** Método read, que tem como finalidade capturar todas as ocorrências da instância de LoteAves
	 * existentes no Banco de Dados, os dados são inseridos em uma list<LoteAves> e essa é retornada.
	 * @author equipe
	 * @return List<LoteAves> - uma lista com todas os lotes de Aves do Banco de Dados.
	 */
	public static List<LoteAves> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoteAves> lotes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT *FROM lote_aves as l,animal as a "
                    + "WHERE l.idanimal=a.idanimal ORDER BY l.idanimal");
            rs = stmt.executeQuery();

            while(rs.next()){
            	
            	LoteAves l = new LoteAves();
                l.setId_animal(rs.getInt("idanimal"));
                l.setRaca(rs.getString("raca"));
                l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
                l.setData_venda(rs.getDate("dat_venda"));
                l.setValor_venda(rs.getFloat("valor_venda"));
                l.setTipo_ave(rs.getString("tipo"));
                l.setQuantidade(rs.getInt("quantidade"));
                
                lotes.add(l);
     
            }

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lotes;
        
    }

	/** Método read que recebe um id como parâtro, tendo como finalidade capturar, se houver, a ocorrência 
	 * de uma instância de LoteAves no Banco de Dados, correspondete com o id passado como parâtro. Se encontrado
	 * o LoteAves será retornado. 
	 * @author Equipe
	 * @param id - int
	 * @return LoteAves - a instância de LoteAves encontrada no banco de dados correspondente ao id passado como parâmetro.
	 */
	public static LoteAves read(int id){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LoteAves l = new LoteAves();
		
	    try {
	    	stmt = con.prepareStatement("SELECT *FROM lote_aves as l, animal as a WHERE l.idanimal=a.idanimal and l.idanimal=?");
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	        
	        if(!rs.next()){
	            throw new NullPointerException("Item inexistente!");
	        };
	        
	        
	        l.setId_animal(id);
	        l.setRaca(rs.getString("raca"));
	        l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
	        l.setData_venda(rs.getDate("dat_venda"));
	        l.setValor_venda(rs.getFloat("valor_venda"));
	        l.setTipo_ave(rs.getString("tipo"));
	        l.setQuantidade(rs.getInt("quantidade"));
	    	l.setTipo_ave(rs.getString("tipo"));
	    	l.setQuantidade(rs.getInt("quantidade"));
	    	
	
	    }catch (SQLException e) {
		
	    	Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
	
	    } finally {
		ConnectionFactory.closeConnection(con, stmt, rs);
	    }
	    return l;
	
	}


	/** Método read que recebe uma String correspodente ao tipo do lote, e tem o objetivo de captura no Banco de Dados,
	 * se houver, LoteAves que possuem o tipo correspondente ao passado como parâmetro, caso haja a ocorrência, os LoteAves
	 * serão adicionadas em uma lista de AvesLotes e esta será retornada.
     * @author Equipe
     * @param tipoNome - String
     * @return List<LoteAves> - lista de LoteAves com o tipo correspondente ao passado como parâmetro.
     */
	public static List<LoteAves> read(String tipoNome){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<LoteAves> lotes = new ArrayList<>();
		    
	    try {
		stmt = con.prepareStatement("SELECT *FROM lote_aves as l,animal as a WHERE l.idanimal=a.idanimal and l.tipo=?");
	        stmt.setString(1,tipoNome);
	        rs = stmt.executeQuery();
	        
	        while(rs.next()){
	        	LoteAves l = new LoteAves();
	        	l.setId_animal(rs.getInt("idanimal"));
	        	l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
	        	l.setRaca(rs.getString("raca"));
	        	l.setData_venda(rs.getDate("dat_venda"));
	        	l.setValor_venda(rs.getFloat("valor_venda"));
	        	l.setTipo_ave(tipoNome);
	        	l.setQuantidade(rs.getInt("quantidade"));
	        	
	        	lotes.add(l);
	        
	        }
	        
	    }catch (SQLException e) {
			Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
	} finally {
		ConnectionFactory.closeConnection(con, stmt, rs);
	}
	    
	    return lotes;
	}
	
	
	/** Método update recebe como parâmetro um LoteAVes. Tem como finalidade capturar o LoteAves no Banco de Dados, se houver
	 * um loteAves com o mesmo id da instância de LoteAves passada como parâtro. O método pode atualizar todos os dados da 
	 * instâcia com excessão do id do lote. Não retorna nada.
	 * @author Equipe
	 * @param l - instância de loteAves.
	 */
	public static void update(LoteAves l){
	    
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
	    try {
	    	stmt = con.prepareStatement("UPDATE lote_aves SET tipo=?,quantidade=?"
	                + "WHERE idanimal=?");
	    	AnimalDAO.update(l);
	    	stmt.setString(1, l.getTipo_ave());
	        stmt.setInt(2, l.getQuantidade());
	        stmt.setInt(3, l.getId_animal());
	        stmt.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+e);
	    
	    }finally{
	        ConnectionFactory.closeConnection(con, stmt);
	    }
	
	}
	
	/** Método delete que recebe uma intância de LoteAves, e tem a opção de capturar no Banco de Dados, se houver, um LoteAVes
	 * com o id correspondente ao id do LoteAves passado como parâmetro, caso haja a ocorrência esse LoteAves será deletado do 
	 * Banco de Dados. Não retorna nada.
	 * @author equipe
	 * @param l - instância de Vacina.
	 */
	public static void delete(LoteAves l){
	    
	    Connection con = ConnectionFactory.getConnection();
	    PreparedStatement stmt = null;
	    
	    try {
	    	stmt = con.prepareStatement("DELETE FROM lote_aves WHERE idanimal=?");
	    	stmt.setInt(1,l.getId_animal());
	        stmt.executeUpdate();
	        AnimalDAO.delete(l);
	        JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, " Erro ao deletar: "+e);
	    } finally{
	        ConnectionFactory.closeConnection(con, stmt);
	    }
	}
	
	/**
	* Método reportQuery, não tem lista de parâmetros, e é responsável por capturar, se houver,
	* todas as ocorrências de instâncias de LoteAves associadas a um Animal no Banco de Dados que possuem todas as informações
	* desejadas do usuário para gerar um relatório de LoteAves. 
	* @author equipe
	* @return List<LoteAves> - Lista de Lote Aves encontradas no Banco de Dados que correspondem à busca.
	*/
	public static List<LoteAves> reportQuery() {
		Connection con = ConnectionFactory.getConnection();
	    String sql = "SELECT animal.idanimal, animal.data_nasc_aqui, lote_aves.tipo, lote_aves.quantidade FROM animal INNER JOIN lote_aves on animal.idanimal = lote_aves.idanimal ORDER BY idanimal;";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	
	    List<LoteAves> animais = new ArrayList<>();
	
	    try {
	        stmt = con.prepareStatement(sql);
	        rs = stmt.executeQuery();
	
	        while(rs.next()){
	            LoteAves a = new LoteAves();
	            a.setId_animal(rs.getInt("idanimal"));
	            a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
	            a.setTipo_ave(rs.getString("tipo"));
	            a.setQuantidade(rs.getInt("quantidade"));
	            
	           
	            animais.add(a);
	        }
	
	    } catch (SQLException ex) {
	        Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
	    } finally{
	        ConnectionFactory.closeConnection(con, stmt, rs);
	    }
	
	    return animais;
	}
	


}
