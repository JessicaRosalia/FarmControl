package br.com.farmcontrol.model.dao;



import java.sql.Date;

import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;
import br.com.farmcontrol.model.vo.Reproducao;

public class RacaoDAODemo {

	public static void main(String[] args) {
		Mamifero m = new Mamifero();
		m.setId_animal(14);

		/*
		Date d = new Date(2020-1900, 7-1, 22);
		
		
		Racao racao = new Racao();
		
		racao.setQtd_racao(5);
		racao.setDescricao("racao de pinto tipo dddF");
		racao.setCusto(13.0f);
		racao.setData(d);
		racao.setAnimal(m);
	
		
		
		System.out.println(racao.getId_racao());
	    System.out.println(racao.getQtd_racao());
	    System.out.println(racao.getDescricao());
	    System.out.println(racao.getCusto());
	    System.out.println(racao.getData());
	    System.out.println(racao.getAnimal());
	    
	    RacaoDAO.create(racao);
	
	    
	    
	    
	    Date t = new Date(2018-1999,4-1,17);
	    
	 // System.out.println(RacaoDAO.read(1));
	    System.out.println(RacaoDAO.read("3 kg de racao basd"));
	 //   System.out.println(RacaoDAO.read(t));
	    
	    
	    Racao rac = new Racao();
       
               
        rac.setCusto(45.0f);
        rac.setDescricao("racao 2");       
        rac.setAnimal(m);
        rac.setQtd_racao(4);

        
        RacaoDAO.create(rac);
   
        
        rac.setCusto(50.0f);
        rac.setDescricao("racao 2 att");
        rac.setId_racao(78);
        RacaoDAO.update(rac);
        
        
        for(Racao r: RacaoDAO.read()) {
	    	System.out.println(r);
	    }
        
        */
        
        Racao re = new Racao();
		re.setDescricao("racao teste delete 4");
		re.setAnimal(m);
		re.setId_racao(10);
	  
	     
	//    RacaoDAO.create(re); 
	     
 RacaoDAO.delete(re);
	     
	     for(Racao r: RacaoDAO.read()) {
		    	System.out.println(r);
		    }

	  // System.out.println("del:::"+re);
	    
	    
	}
}
