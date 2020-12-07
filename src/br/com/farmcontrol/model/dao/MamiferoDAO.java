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

public class MamiferoDAO {

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
            JOptionPane.showConfirmDialog(null, " Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public static List<Mamifero> read(){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM mamifero_abate as m,animal as a "
                + "WHERE m.idanimal=a.idanimal";
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
            JOptionPane.showConfirmDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public static void delete(Mamifero a){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM mamifero_abate WHERE idanimal=?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, a.getId_animal());
            stmt.executeUpdate();
            AnimalDAO.delete(a);
            JOptionPane.showConfirmDialog(null, "Deletado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao deletar: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
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
