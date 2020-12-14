package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Ovo;
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
* Classe respons�vel pela manipula��o dos dados a respeito da produ��o de Ovo vindos do Banco de Dados,
* como cadastro, leitura, atualiza��o e exclus�o.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplica��o
*/
public class OvoDao {
    
	
	/**
	* M�todo create, respons�vel por inserir no Banco de Dados uma inst�ncia de Ovo. N�o retorna nada.
	* @author equipe
	* @param o - inst�ncia de Ovo.
	*/
    public static void create(Ovo o){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO ovos(idanimal,valor_unidade,quantidade,data_ovo) "
                + "VALUES(?,?,?,?)";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, o.getLote().getId_animal());
            stmt.setDouble(2, o.getValor_unidade());
            stmt.setInt(3, o.getQtd_ovos());
            stmt.setDate(4, (Date) o.getData_producao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
	/**
	* M�todo read, respons�vel por capturar, se houver, todas as ocorr�ncias da inst�ncia de Ovo
	* existentes no Banco de Dados inserindo em um List, para depois retorn�-lo.
	* @author equipe
	* @return List<Ovo> - um list com todas as produ��es de Ovos do Banco de Dados.
	*/
    public static List<Ovo> read(){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM ovos";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Ovo> ovos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ovo o = new Ovo();
                LoteAves la = new LoteAves();
                o.setId_ovo(rs.getInt("idovos"));
                o.setLote(la);
                la.setId_animal(rs.getInt("idanimal"));
                o.setValor_unidade(rs.getFloat("valor_unidade"));
                o.setQtd_ovos(rs.getInt("quantidade"));
                o.setData_producao(rs.getDate("data_ovo"));
                ovos.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return ovos;
    }
    
    
	/**
	* M�todo read que recebe uma data como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de produ��o de Ovos com aquela data passada como param�tro. 
	* Se encontrado, uma lista das produ��es de Ovos ser� retornada.
	* @author equipe
	* @param d - data a ser buscada na tabela ovos.
	* @return List<Ovo> - Lista de de produ��es de Ovos encontradas no Banco de Dados que correspondem � busca.
	*/
    public static List<Ovo> read(Date d){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM ovos WHERE data_ovo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Ovo> ovos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, d);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ovo o = new Ovo();
                LoteAves la = new LoteAves();
                o.setId_ovo(rs.getInt("idovos"));
                o.setLote(la);
                la.setId_animal(rs.getInt("idanimal"));
                o.setValor_unidade(rs.getFloat("valor_unidade"));
                o.setQtd_ovos(rs.getInt("quantidade"));
                o.setData_producao(d);
                ovos.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return ovos;
    }
    
    
	/**
	* M�todo read que recebe um Lote de Aves como param�tro, e � respons�vel por capturar, se houver,
	* todas as ocorr�ncias de produ��es de Ovos no Banco de Dados que diz respeito �quele Lote de animais.
	* Se encontrado, uma lista com as produ��es de Ovos ser� retornada.
	* @author equipe
	* @param l - Lote de Aves a ter suas produ��es de Ovos buscadas.
	* @return List<Racao> - Lista de produ��es de ovos encontradas no Banco de Dados que correspondem � busca.
	*/
    public static List<Ovo> read(LoteAves l){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM ovos WHERE idanimal=? ORDER BY idovos";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Ovo> ovos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, l.getId_animal());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Ovo o = new Ovo();
                o.setId_ovo(rs.getInt("idovos"));
                o.setLote(l);
                l.setId_animal(rs.getInt("idanimal"));
                o.setValor_unidade(rs.getFloat("valor_unidade"));
                o.setQtd_ovos(rs.getInt("quantidade"));
                o.setData_producao(rs.getDate("data_ovo"));
                ovos.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return ovos;
    }
    
    
	/**
	* M�todo read que recebe um id como param�tro, e � respons�vel por capturar, se houver,
	* a ocorr�ncia de produ��o de Ovos existente no Banco de Dados que corresponde ao id passado como param�tro.
	* Se encontrado, a produ��o de Ovos ser� retornada.
	* @author equipe
	* @param id - id da produ��o de Ovos buscada.
	* @return Ovo - A produ��o de Ovos encontrada no Banco de Dados que corresponde ao id passado.
	*/
    public static Ovo read(int id){
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM ovos WHERE idovos=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ovo o = new Ovo();
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                //JOptionPane.showMessageDialog(null, "Nenhum item com esse ID!");
                throw new NullPointerException("Item inexistente!");
            };
            LoteAves la = new LoteAves();
            o.setId_ovo(id);
            o.setLote(la);
            la.setId_animal(rs.getInt("idanimal"));
            o.setValor_unidade(rs.getFloat("valor_unidade"));
            o.setQtd_ovos(rs.getInt("quantidade"));
            o.setData_producao(rs.getDate("data_ovo"));
            
        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return o;
    }
    
	/**
	* M�todo update que � respons�vel por atualizar a produ��o de Ovos recebida como param�tro,
	* com as novas informa��es vindas com ela. A atualiza��o � refletida no Banco de Dados. N�o retorna nada.
	* @author equipe
	* @param o - produ��o de Ovos a ser atualizada.
	*/
    public static void update(Ovo o){
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE ovos "
                   + "SET valor_unidade=?,quantidade=?,data_ovo=?"
                   + "WHERE idovos=?";
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, o.getValor_unidade());
            stmt.setInt(2, o.getQtd_ovos());
            stmt.setDate(3, (Date) o.getData_producao());
            stmt.setInt(4,o.getId_ovo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
	/**
	* M�todo delete que � respons�vel por deletar uma produ��o de Ovos recebida como param�tro do Banco de Dados.
	* N�o retorna nada.
	* @author equipe
	* @param o - Produ��o de Ovos a ser deletada.
	*/
    public static void delete(Ovo o){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM ovos WHERE idovos=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, o.getId_ovo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public static List<Ovo> reportQuery() {
    	Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT lote_aves.idanimal, lote_aves.tipo, ovos.quantidade, ovos.valor_unidade, ovos.data_ovo FROM lote_aves INNER JOIN ovos on lote_aves.idanimal = ovos.idanimal ORDER BY tipo";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Ovo> ovos = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                Ovo o = new Ovo();
                LoteAves lote = new LoteAves();
                o.setLote(lote);
                lote.setId_animal(rs.getInt("idanimal"));
                lote.setTipo_ave(rs.getString("tipo"));
                o.setQtd_ovos(rs.getInt("quantidade"));
                o.setData_producao(rs.getDate("data_ovo"));
                o.setValor_unidade(rs.getFloat("valor_unidade"));
               
                ovos.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return ovos;
    }
}
