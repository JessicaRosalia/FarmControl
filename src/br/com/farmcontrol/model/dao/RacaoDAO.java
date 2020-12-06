package br.com.farmcontrol.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.vo.Racao;

public class RacaoDAO {

	public static void create(Racao racao) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO RACAO(quantidade,descricao, custo, data_racao) VALUES(?,?,?,?)");
		//	stmt.setInt(1, racao.getAnimal().getId_animal());
			stmt.setInt(1, racao.getQtd_racao() );
			stmt.setString(2, racao.getDescricao() );
			stmt.setFloat(3, racao.getCusto() );
			stmt.setDate(4, (Date) racao.getData() );
			
			stmt.executeUpdate();
			
			//JOptionPane.showMessageDialog(null, "salvo com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "erro ao salvar"+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public static List<Racao> read(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List <Racao> racoes = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM racao");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Racao racao = new Racao();
				
				racao.setId_racao(rs.getInt("idracao"));
				racao.setDescricao(rs.getString("descricao"));
				racao.setQtd_racao(rs.getInt("quantidade"));
				racao.setCusto(rs.getFloat("custo"));
				//racao.getAnimal().setId_animal(rs.getInt("idanimal"));
				//	racao.setAnimal(rs.getInt("idanimal"));
				
				racoes.add(racao);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return racoes;

	}
}
