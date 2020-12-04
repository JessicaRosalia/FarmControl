package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AnimalDAO {

        public static void create(Animal a){
        
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO animal(raca,data_nasc_aqui,data_venda,valor_venda) "
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
    
}
