package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoteAvesDAO {

	public static void create (LoteAves l) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO lote_aves(idanimal,tipo,quantidade) VALUES (?,?,?)");
			AnimalDAO.create(l);
			stmt.setInt(1,MamiferoDAO.ultimoID());
			stmt.setString(2, l.getTipo_ave());
			stmt.setInt(3, l.getQuantidade());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
			
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, " Erro ao salvar: "+e);
		} finally {
			 ConnectionFactory.closeConnection(con,stmt);
		}
	}
	
public static List<LoteAves> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoteAves> lotes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT *FROM lote_aves as l,animal as a "
                    + "WHERE l.idanimal=a.idanimal ORDER BY l.idanimal");
            rs = stmt.executeQuery();

            while(rs.next()){
            	
            	LoteAves l = new LoteAves();
                l.setId_animal(rs.getInt("idanimal"));
                l.setRaca(rs.getString("raca"));
                l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
                l.setData_venda(rs.getDate("dat_venda"));
                l.setValor_venda(rs.getFloat("valor_venda"));
                l.setTipo_ave(rs.getString("tipo"));
                l.setQuantidade(rs.getInt("quantidade"));
                
                lotes.add(l);
     
            }

        } catch (SQLException ex) {
            Logger.getLogger(MamiferoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lotes;
        
    }

public static LoteAves read(int id){
	
	Connection con = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	LoteAves l = new LoteAves();
	
    try {
    	stmt = con.prepareStatement("SELECT *FROM lote_aves as l, animal as a WHERE l.idanimal=a.idanimal and l.idanimal=?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
        if(!rs.next()){
            throw new NullPointerException("Item inexistente!");
        };
        
        
        l.setId_animal(id);
        l.setRaca(rs.getString("raca"));
        l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
        l.setData_venda(rs.getDate("dat_venda"));
        l.setValor_venda(rs.getFloat("valor_venda"));
        l.setTipo_ave(rs.getString("tipo"));
        l.setQuantidade(rs.getInt("quantidade"));
    	l.setTipo_ave(rs.getString("tipo"));
    	l.setQuantidade(rs.getInt("quantidade"));
    	

    }catch (SQLException e) {
	
    	Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);

    } finally {
	ConnectionFactory.closeConnection(con, stmt, rs);
    }
    return l;

}


public static List<LoteAves> read(String tipoNome){
	
	Connection con = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	List<LoteAves> lotes = new ArrayList<>();
	    
    try {
	stmt = con.prepareStatement("SELECT *FROM lote_aves as l,animal as a WHERE l.idanimal=a.idanimal and l.tipo=?");
        stmt.setString(1,tipoNome);
        rs = stmt.executeQuery();
        
        while(rs.next()){
        	LoteAves l = new LoteAves();
        	l.setId_animal(rs.getInt("idanimal"));
        	l.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
        	l.setRaca(rs.getString("raca"));
        	l.setData_venda(rs.getDate("dat_venda"));
        	l.setValor_venda(rs.getFloat("valor_venda"));
        	l.setTipo_ave(tipoNome);
        	l.setQuantidade(rs.getInt("quantidade"));
        	
        	lotes.add(l);
        
        }
        
    }catch (SQLException e) {
		Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, e);
} finally {
	ConnectionFactory.closeConnection(con, stmt, rs);
}
    
    return lotes;
}

public static void update(LoteAves l){
    
	Connection con = ConnectionFactory.getConnection();
	PreparedStatement stmt = null;
	
    try {
    	stmt = con.prepareStatement("UPDATE lote_aves SET tipo=?,quantidade=?"
                + "WHERE idanimal=?");
    	AnimalDAO.update(l);
    	stmt.setString(1, l.getTipo_ave());
        stmt.setInt(2, l.getQuantidade());
        stmt.setInt(3, l.getId_animal());
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, " Erro ao atualizar: "+e);
    
    }finally{
        ConnectionFactory.closeConnection(con, stmt);
    }

}


public static void delete(LoteAves l){
    
    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt = null;
    
    try {
    	stmt = con.prepareStatement("DELETE FROM lote_aves WHERE idanimal=?");
    	stmt.setInt(1,l.getId_animal());
        stmt.executeUpdate();
        AnimalDAO.delete(l);
        JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, " Erro ao deletar: "+e);
    } finally{
        ConnectionFactory.closeConnection(con, stmt);
    }
}

public static List<LoteAves> reportQuery() {
	Connection con = ConnectionFactory.getConnection();
    String sql = "SELECT animal.idanimal, animal.data_nasc_aqui, lote_aves.tipo, lote_aves.quantidade FROM animal INNER JOIN lote_aves on animal.idanimal = lote_aves.idanimal ORDER BY idanimal;";
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<LoteAves> animais = new ArrayList<>();

    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();

        while(rs.next()){
            LoteAves a = new LoteAves();
            a.setId_animal(rs.getInt("idanimal"));
            a.setData_nasc_aquisicao(rs.getDate("data_nasc_aqui"));
            a.setTipo_ave(rs.getString("tipo"));
            a.setQuantidade(rs.getInt("quantidade"));
            
           
            animais.add(a);
        }

    } catch (SQLException ex) {
        Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
    }

    return animais;
}



}
