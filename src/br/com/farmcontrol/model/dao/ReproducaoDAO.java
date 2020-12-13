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
* Classe responsável pela manipulação dos dados a respeito de Reproducao vindos do Banco de Dados, como cadastro, leitura, atualização e exclusão.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplicação
*/
public class ReproducaoDAO {
	
	
	/**
	* Método create, responsável por inserir no Banco de Dados uma instância de Reproducao. Não retorna nada.
	* @author equipe
	* @param reprod - instância de Reproducao
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
	* Método read, responsável por capturar, se houver, todas as ocorrências da instância de Reproducao
	* existentes no Banco de Dados inserindo em um List, para depois retorná-lo.
	* @author equipe
	* @return List<Reproducao> - um list com todos as Reproduções do Banco de Dados
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
	* Método read que recebe um id como paramêtro, e é responsável por capturar, se houver, a ocorrência de uma instância de Reproducao
	* existente no Banco de Dados que corresponde ao id passado como paramêtro. Se encontrado, a Reproducao será retornada.
	* @author equipe
	* @param id - id da Reproducao buscada.
	* @return Reproducao - A instância de Reproducao encontrada no Banco de Dados que corresponde ao id passado.
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
	* Método read que recebe uma data como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de instâncias de Reproducao com aquela data passada como paramêtro. 
	* Se encontrado, uma lista das Reproducoes será retornada.
	* @author equipe
	* @param d - data a ser buscada na tabela Reproducao.
	* @return List<Reproducao> - Lista de Reproducões encontradas no Banco de Dados que correspondem à busca.
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
	* Método read que recebe um Animal como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de instâncias de Reproducao no Banco de Dados que diz respeito àquele Animal.
	* Se encontrado, uma lista com as Reproducoes será retornada.
	* @author equipe
	* @param m - Animal a ter suas Reproducoes buscadas.
	* @return List<Reproducao> - Lista de Reproducoes encontradas no Banco de Dados que correspondem à busca.
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
	* Método read que recebe uma descrição como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de Reproducao no Banco de Dados que possuem aquela descrição.
	* Se encontrado, uma lista com as Reproducoes será retornada.
	* @author equipe
	* @param descricao - Descrição a ser buscada na tabela reproducao do Banco de Dados.
	* @return List<Reproducao> - Lista de Reproducoes encontradas no Banco de Dados que correspondem à busca.
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
	* Método update que é responsável por atualizar a Reproducao recebida como paramêtro,
	* com as novas informações vindas com ela. A atualização é refletida no Banco de Dados. Não retorna nada.
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
	* Método delete que é responsável por deletar uma Reproducao recebido como paramêtro do Banco de Dados. Nâo retorna nada.
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
