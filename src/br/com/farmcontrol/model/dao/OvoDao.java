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
* Classe responsável pela manipulação dos dados a respeito da produção de Ovo vindos do Banco de Dados,
* como cadastro, leitura, atualização e exclusão.
* @author equipe
* @version 1.1
* @since Release 1.2 da aplicação
*/
public class OvoDao {
    
	
	/**
	* Método create, responsável por inserir no Banco de Dados uma instância de Ovo. Não retorna nada.
	* @author equipe
	* @param o - instância de Ovo.
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
	* Método read, responsável por capturar, se houver, todas as ocorrências da instância de Ovo
	* existentes no Banco de Dados inserindo em um List, para depois retorná-lo.
	* @author equipe
	* @return List<Ovo> - um list com todas as produções de Ovos do Banco de Dados.
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
	* Método read que recebe uma data como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de produção de Ovos com aquela data passada como paramêtro. 
	* Se encontrado, uma lista das produções de Ovos será retornada.
	* @author equipe
	* @param d - data a ser buscada na tabela ovos.
	* @return List<Ovo> - Lista de de produções de Ovos encontradas no Banco de Dados que correspondem à busca.
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
	* Método read que recebe um Lote de Aves como paramêtro, e é responsável por capturar, se houver,
	* todas as ocorrências de produções de Ovos no Banco de Dados que diz respeito àquele Lote de animais.
	* Se encontrado, uma lista com as produções de Ovos será retornada.
	* @author equipe
	* @param l - Lote de Aves a ter suas produções de Ovos buscadas.
	* @return List<Racao> - Lista de produções de ovos encontradas no Banco de Dados que correspondem à busca.
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
	* Método read que recebe um id como paramêtro, e é responsável por capturar, se houver,
	* a ocorrência de produção de Ovos existente no Banco de Dados que corresponde ao id passado como paramêtro.
	* Se encontrado, a produção de Ovos será retornada.
	* @author equipe
	* @param id - id da produção de Ovos buscada.
	* @return Ovo - A produção de Ovos encontrada no Banco de Dados que corresponde ao id passado.
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
	* Método update que é responsável por atualizar a produção de Ovos recebida como paramêtro,
	* com as novas informações vindas com ela. A atualização é refletida no Banco de Dados. Não retorna nada.
	* @author equipe
	* @param o - produção de Ovos a ser atualizada.
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
	* Método delete que é responsável por deletar uma produção de Ovos recebida como paramêtro do Banco de Dados.
	* Nâo retorna nada.
	* @author equipe
	* @param o - Produção de Ovos a ser deletada.
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
