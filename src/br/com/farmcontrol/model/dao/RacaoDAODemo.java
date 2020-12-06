package br.com.farmcontrol.model.dao;



import java.sql.Date;
import java.time.LocalDate;

import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;
import javafx.util.converter.LocalDateStringConverter;

public class RacaoDAODemo {

	public static void main(String[] args) {
		Mamifero m = new Mamifero();
		m.setId_animal(10);

		
		Date d = new Date(2000-1900, 2-1, 12);
		
		
		Racao racao = new Racao();
		
		racao.setQtd_racao(5);
		racao.setDescricao("racao de pinto tipo dddF");
		racao.setCusto(123.0f);
		racao.setData(d);
		racao.setAnimal(m);
	
		
		
		System.out.println(racao.getId_racao());
	    System.out.println(racao.getQtd_racao());
	    System.out.println(racao.getDescricao());
	    System.out.println(racao.getCusto());
	    System.out.println(racao.getData());
	    System.out.println(racao.getAnimal());
	    
	    RacaoDAO.create(racao);

	    
	    
	    
	}
}
