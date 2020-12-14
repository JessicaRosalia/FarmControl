package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
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

/**
* Classe responsável pela manipulação dos dados a respeito de Animal vindos do Banco de Dados,
* como cadastro, leitura, atualização e exclusão.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplicação
*/
public class AnimalDAO {

	
	/**
	* Método create, responsável por inserir no Banco de Dados uma instância de Animal. Não retorna nada.
	* @author equipe
	* @param a - Instância de Animal.
	*/
    public static void create(Animal a){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO animal(raca,data_nasc_aqui,dat_venda,valor_venda) "
                + "VALUES(?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getRaca());
            stmt.setDate(2, (Date) a.getData_nasc_aquisicao());
            stmt.setDate(3, (Date) a.getData_venda());
            stmt.setFloat(4, a.getValor_venda());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
       
    
	/**
	* Método read, responsável por capturar, se houver, todas as ocorrências de Animal
	* existentes no Banco de Dados inserindo em um List, para depois retorná-lo.
	* @author equipe
	* @return List<Animal> - um list com todas os Animais do Banco de Dados.
	*/
    public static List<Animal> read(){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM animal ORDER BY idanimal";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Animal> animais = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                Animal a = new Mamifero();
                a.setId_animal(rs.getInt("idanimal"));
                a.setRaca(rs.getString("raca"));
                a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
                a.setData_venda(rs.getDate("dat_venda"));
                a.setValor_venda(rs.getFloat("valor_venda"));
                animais.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return animais;
    }
    
    
	/**
	* Método read que recebe um id como paramêtro, e é responsável por capturar, se houver,
	* a ocorrência de uma instância de Animal existente no Banco de Dados que corresponde ao id passado como paramêtro.
	* Se encontrado, o Animal será retornado.
	* @author equipe
	* @param id - id do Animal buscado.
	* @return Animal - O Animal encontrado no Banco de Dados que corresponde ao id passado.
	*/
    public static Animal read(int id){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM animal WHERE idanimal=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Animal a = new Mamifero();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            a.setId_animal(id);
            a.setRaca(rs.getString("raca"));
            a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
            a.setData_venda(rs.getDate("dat_venda"));
            a.setValor_venda(rs.getFloat("valor_venda"));

        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return a;
    }
    
	/**
	* Método update que é responsável por atualizar o Animal recebida como paramêtro,
	* com as novas informações vindas com ele. A atualização é refletida no Banco de Dados. Não retorna nada.
	* @author equipe
	* @param a - Animal a ser atualizado.
	*/
    public static void update(Animal a){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE animal "
                   + "SET raca=?,data_nasc_aqui=?,dat_venda=?,valor_venda=?"
                   + "WHERE idanimal=?";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getRaca());
            stmt.setDate(2, (Date) a.getData_nasc_aquisicao());
            stmt.setDate(3, (Date) a.getData_venda());
            stmt.setFloat(4,a.getValor_venda());
            stmt.setInt(5, a.getId_animal());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
       
	/**
	* Método delete que é responsável por deletar um Animal recebido como paramêtro do Banco de Dados. Nâo retorna nada.
	* @author equipe
	* @param a - Animal a ser deletado.
	*/
    public static void delete(Animal a){

        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM animal WHERE idanimal=?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, a.getId_animal());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }


    
}
