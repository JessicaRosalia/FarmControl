package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
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

public class AnimalDAO {

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
            JOptionPane.showConfirmDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
        
        public static List<Animal> read(){
            Connection con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM animal";
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
                Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }

            return animais;
        }
        
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
                Logger.getLogger(OvoDao.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }

            return a;
        }
        
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
            JOptionPane.showConfirmDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, " Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        
        public static void delete(Animal a){
        
            Connection con = ConnectionFactory.getConnection();
            String sql = "DELETE FROM animal WHERE idanimal=?";
            PreparedStatement stmt = null;

            try {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, a.getId_animal());
                stmt.executeUpdate();
                JOptionPane.showConfirmDialog(null, "Deletado com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog(null, " Erro ao deletar: "+ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
            
        }
}
