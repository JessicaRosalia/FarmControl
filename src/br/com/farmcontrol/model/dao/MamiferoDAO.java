package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
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


/** Classe respons�vel pela manipula��o dos dados a respeito de Mamiferos vindos do Banco de Dados,
 * como cadastro, leitura, atualiza��o e exclus�o.
 * @author equipe
 * @version 1.1 
 * @since Realease 1.2 da aplica��o
 */
public class MamiferoDAO {

	
	/** M�todo create, que tem como principal objetivo a inser��o de uma inst�ncia de Mamifero no banco de dados.
	 *  N�o retorna nada.
	 * @author equipe
	 * @param a - inst�ncia de Mamifero.
	 */
    public static void create(Mamifero a){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO "
                + "mamifero_abate(idanimal,sexo,valor_arroba,peso,tipomamifero,dataabate) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
            
            AnimalDAO.create(a);
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, MamiferoDAO.ultimoID());
            stmt.setString(2, a.getSexo_mamifero());
            stmt.setFloat(3, a.getValor_arroba());
            stmt.setFloat(4, a.getPeso());
            stmt.setString(5, a.getTipo_mamifero());
            stmt.setDate(6, (Date) a.getData_abate());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    /** M�todo read, que tem como finalidade capturar todas as ocorr�ncias da inst�ncia de Mamifero
	 * existentes no Banco de Dados, os dados s�o inseridos em uma list<Mamifero> e essa � retornada.
	 * @author equipe
	 * @return List<Mamifero> - uma lista com todos os Mamiferos do Banco de Dados.
	 */
    public static List<Mamifero> read(){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM mamifero_abate as m,animal as a "
                + "WHERE m.idanimal=a.idanimal ORDER BY m.idanimal";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Mamifero> animais = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                Mamifero a = new Mamifero();
                a.setId_animal(rs.getInt("idanimal"));
                a.setRaca(rs.getString("raca"));
                a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
                a.setData_venda(rs.getDate("dat_venda"));
                a.setValor_venda(rs.getFloat("valor_venda"));
                a.setSexo_mamifero(rs.getString("sexo"));
                a.setValor_arroba(rs.getFloat("valor_arroba"));
                a.setPeso(rs.getFloat("peso"));
                a.setTipo_mamifero(rs.getString("tipomamifero"));
                a.setData_abate(rs.getDate("dataabate"));
                animais.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return animais;
        
    }
    
    
    /** M�todo read que recebe uma String correspodente ao tipo de Mamifero, e tem o objetivo de captura
     *  no Banco de Dados, se houver, Mamifero(s) que possue(m) o tipo correspondente ao passado como par�metro,
     *  caso haja a ocorr�ncia, o(s) Mamifero(s) ser�o adicionadas em uma lista de Mamifero e esta ser� retornada.
     * @author Equipe
     * @param tipoNome - tipo do mam�fero a ser buscado.
     * @return List<Mamifero> - lista de Mamifero com o tipo correspondente ao passado como par�metro.
     */
    public static List<Mamifero> read(String tipo){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM mamifero_abate as m,animal as a "
                + "WHERE m.idanimal=a.idanimal and m.tipomamifero=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Mamifero> animais = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, tipo);
            rs = stmt.executeQuery();

            while(rs.next()){
                Mamifero a = new Mamifero();
                a.setId_animal(rs.getInt("idanimal"));
                a.setRaca(rs.getString("raca"));
                a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
                a.setData_venda(rs.getDate("dat_venda"));
                a.setValor_venda(rs.getFloat("valor_venda"));
                a.setSexo_mamifero(rs.getString("sexo"));
                a.setValor_arroba(rs.getFloat("valor_arroba"));
                a.setPeso(rs.getFloat("peso"));
                a.setTipo_mamifero(rs.getString("tipomamifero"));
                a.setData_abate(rs.getDate("dataabate"));
                animais.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return animais;
    }
    
    
    /** M�todo read que recebe um id como par�tro, tendo como finalidade capturar, se houver, a ocorr�ncia 
     * de uma inst�ncia de Mamifero no Banco de Dados, correspondete com o id passado como par�tro. Se encontrado
     * o Mamifero ser� retornado. 
     * @author Equipe
     * @param id - id do mam�fero a ser buscado.
     * @return Mamifero - a inst�ncia de Mamifero encontrada no Banco de Dados correspondente ao id passado como par�metro.
     */
    public static Mamifero read(int id){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM mamifero_abate as m,animal as a "
                + "WHERE m.idanimal=a.idanimal and m.idanimal=?;";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mamifero a = new Mamifero();
        
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
            a.setSexo_mamifero(rs.getString("sexo"));
            a.setValor_arroba(rs.getFloat("valor_arroba"));
            a.setPeso(rs.getFloat("peso"));
            a.setTipo_mamifero(rs.getString("tipomamifero"));
            a.setData_abate(rs.getDate("dataabate"));

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return a;
    }
    
    
    /** M�todo update recebe como par�metro um Mamifero. Tem como finalidade capturar o Mamifero no Banco de Dados, se houver
	 * um Mamifero com o mesmo id da inst�ncia de LoteAves passada como par�tro. O m�todo pode atualizar todos os dados da 
	 * inst�cia com excess�o do id do Mamifero. N�o retorna nada.
	 * @author Equipe
	 * @param a - inst�ncia de Mamiferos.
	 */
    public static void update(Mamifero a){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE mamifero_abate "
                   + "SET sexo=?,valor_arroba=?,peso=?,tipomamifero=?,dataabate=?"
                   + "WHERE idanimal=?";
        PreparedStatement stmt = null;
        
        try {
            
            AnimalDAO.update(a);
            
            stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getSexo_mamifero());
            stmt.setFloat(2, a.getValor_arroba());
            stmt.setFloat(3, a.getPeso());
            stmt.setString(4, a.getTipo_mamifero());
            stmt.setDate(5, (Date) a.getData_abate());
            stmt.setInt(6, a.getId_animal());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
	/** M�todo delete que recebe uma int�ncia de Mamifero, e tem a op��o de capturar no Banco de Dados, se houver, 
	 * Mamifero com o id correspondente ao id do Mamifero passado como par�metro, caso haja a ocorr�ncia esse Mamifero
	 * ser� deletado do Banco de Dados. N�o retorna nada.
	 * @author equipe
	 * @param a - inst�ncia de Mamifero.
	 */
    public static void delete(Mamifero a){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM mamifero_abate WHERE idanimal=?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, a.getId_animal());
            stmt.executeUpdate();
            AnimalDAO.delete(a);
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    /** M�todo est�tico que tem a finalidade de capturar no Banco de Dados o �ltimo Animal
     * cadastrado no mesmo, podendo este ser uma especializa��o de Animal.
     * @author equipe
     * @return id - int, id do �ltimo animal cadastrado.
     */
    public static int ultimoID(){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT MAX(idanimal) FROM animal";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            id=rs.getInt("max");

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return id;
    }
    
}
