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
import br.com.farmcontrol.model.vo.Racao;


/**
* Classe respons�vel pela manipula��o dos dados a respeito de Racao vindos do Banco de Dados, como cadastro, leitura, atualiza��o e exclus�o.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplica��o
*/
public class RacaoDAO {

	/**
	* M�todo create, respons�vel por inserir no Banco de Dados uma inst�ncia de Racao. N�o retorna nada.
	* @author equipe
	* @param racao - inst�ncia de Racao.
	*/
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
	
	
	/**
	* M�todo read, respons�vel por capturar, se houver, todas as ocorr�ncias da inst�ncia de Racao
	* existentes no Banco de Dados inserindo em um List, para depois retorn�-lo.
	* @author equipe
	* @return List<Racao> - um list com todas as Rac�es do Banco de Dados.
	*/
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
	
	
	/**
	* M�todo read que recebe um id como param�tro, e � respons�vel por capturar, se houver, a ocorr�ncia de uma inst�ncia de Racao
	* existente no Banco de Dados que corresponde ao id passado como param�tro. Se encontrado, a Racao ser� retornada.
	* @author equipe
	* @param id - id da Racao buscada.
	* @return Racao - A inst�ncia de Racao encontrada no Banco de Dados que corresponde ao id passado.
	*/
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
       
	
	/**
	* M�todo read que recebe um Animal como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de Racao no Banco de Dados que diz respeito �quele Animal(ao seu consumo).
	* Se encontrado, uma lista com as Ra��es ser� retornada.
	* @author equipe
	* @param m - Animal a ter suas Ra��es buscadas.
	* @return List<Racao> - Lista de Ra��es encontradas no Banco de Dados que correspondem � busca.
	*/
    public static List<Racao> read(Animal m){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM racao WHERE idanimal=? ORDER By idracao";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Racao> racoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, m.getId_animal());
            rs = stmt.executeQuery();
        
        while(rs.next()){
            Racao r = new Racao();
            r.setAnimal(m);
            r.setData(rs.getDate("data_racao"));
            r.setId_racao(rs.getInt("idracao"));
            r.setDescricao(rs.getString("descricao"));
            r.setQtd_racao(rs.getInt("quantidade"));
            r.setCusto(rs.getFloat("custo"));

            racoes.add(r);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return racoes;
    }
	
    
	/**
	* M�todo read que recebe uma descri��o como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de Racao no Banco de Dados que possuem aquela descri��o.
	* Se encontrado, uma lista com as Ra��es ser� retornada.
	* @author equipe
	* @param descricao - Descri��o a ser buscada na tabela racao do Banco de Dados.
	* @return List<Racao> - Lista de Rac�es encontradas no Banco de Dados que correspondem � busca.
	*/
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
	
	
	
	/**
	* M�todo read que recebe uma data como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de Racao com aquela data passada como param�tro. 
	* Se encontrado, uma lista das Rac��es ser� retornada.
	* @author equipe
	* @param d - data a ser buscada na tabela racao.
	* @return List<Racao> - Lista de Ra��es encontradas no Banco de Dados que correspondem � busca.
	*/
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

	
	/**
	* M�todo update que � respons�vel por atualizar a Racao recebida como param�tro,
	* com as novas informa��es vindas com ela. A atualiza��o � refletida no Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param rep - Racao a ser atualizada.
	*/
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
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }


	/**
	* M�todo delete que � respons�vel por deletar uma Racao recebida como param�tro do Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param reprod - Racao a ser deletada.
	*/
	public static void delete(Racao r){
	        
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM racao WHERE idracao=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, r.getId_racao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
