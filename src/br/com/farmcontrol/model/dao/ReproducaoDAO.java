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


/**
* Classe respons�vel pela manipula��o dos dados a respeito de Reproducao vindos do Banco de Dados, como cadastro, leitura, atualiza��o e exclus�o.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplica��o
*/
public class ReproducaoDAO {
	
	
	/**
	* M�todo create, respons�vel por inserir no Banco de Dados uma inst�ncia de Reproducao. N�o retorna nada.
	* @author equipe
	* @param reprod - inst�ncia de Reproducao
	*/
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
	
	/**
	* M�todo read, respons�vel por capturar, se houver, todas as ocorr�ncias da inst�ncia de Reproducao
	* existentes no Banco de Dados inserindo em um List, para depois retorn�-lo.
	* @author equipe
	* @return List<Reproducao> - um list com todos as Reprodu��es do Banco de Dados
	*/
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
	
	/**
	* M�todo read que recebe um id como param�tro, e � respons�vel por capturar, se houver, a ocorr�ncia de uma inst�ncia de Reproducao
	* existente no Banco de Dados que corresponde ao id passado como param�tro. Se encontrado, a Reproducao ser� retornada.
	* @author equipe
	* @param id - id da Reproducao buscada.
	* @return Reproducao - A inst�ncia de Reproducao encontrada no Banco de Dados que corresponde ao id passado.
	*/
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

	
	/**
	* M�todo read que recebe uma data como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de inst�ncias de Reproducao com aquela data passada como param�tro. 
	* Se encontrado, uma lista das Reproducoes ser� retornada.
	* @author equipe
	* @param d - data a ser buscada na tabela Reproducao.
	* @return List<Reproducao> - Lista de Reproduc�es encontradas no Banco de Dados que correspondem � busca.
	*/
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
      
	
	/**
	* M�todo read que recebe um Animal como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de inst�ncias de Reproducao no Banco de Dados que diz respeito �quele Animal.
	* Se encontrado, uma lista com as Reproducoes ser� retornada.
	* @author equipe
	* @param m - Animal a ter suas Reproducoes buscadas.
	* @return List<Reproducao> - Lista de Reproducoes encontradas no Banco de Dados que correspondem � busca.
	*/
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

    
	/**
	* M�todo read que recebe uma descri��o como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de Reproducao no Banco de Dados que possuem aquela descri��o.
	* Se encontrado, uma lista com as Reproducoes ser� retornada.
	* @author equipe
	* @param descricao - Descri��o a ser buscada na tabela reproducao do Banco de Dados.
	* @return List<Reproducao> - Lista de Reproducoes encontradas no Banco de Dados que correspondem � busca.
	*/
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
	
	
	/**
	* M�todo update que � respons�vel por atualizar a Reproducao recebida como param�tro,
	* com as novas informa��es vindas com ela. A atualiza��o � refletida no Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param rep - Reproducao a ser atualizada.
	*/
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


	/**
	* M�todo delete que � respons�vel por deletar uma Reproducao recebido como param�tro do Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param reprod - Reproducao a ser deletada.
	*/
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
