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

public class OvoDao {
    
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
}
