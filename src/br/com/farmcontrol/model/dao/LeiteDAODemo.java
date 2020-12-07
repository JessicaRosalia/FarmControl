package br.com.farmcontrol.model.dao;

import java.sql.Date;

import br.com.farmcontrol.model.vo.Leite;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Leite;


public class LeiteDAODemo {
	public static void main(String[] args) {
		Mamifero m = new Mamifero();
		m.setId_animal(12);
	
		/*
		Date d = new Date(2000-1900, 2-1, 12);
		
		
		Leite le = new Leite();
		
		le.setData_producao(d);
		le.setValor_litro(2.5f);
		le.setQtd_leite(2);
		le.setAnimal(m);
	
		
		
		System.out.println(le.getData_producao());
	    System.out.println(le.getValor_litro());
	    System.out.println(le.getQtd_leite());
	    System.out.println(le.getAnimal());
	    
	    LeiteDAO.create(le);
	
	    
	    for(Leite l: LeiteDAO.read()) {
	   // 	System.out.println(l);
	    }
	    
	    Date r = new Date(2018-1999,9-1,10);
	   // System.out.println(LeiteDAO.read(2));
	    System.out.println(LeiteDAO.read(r));
	    
	    
	    
	    Leite lei = new Leite();
       
               
        lei.setAnimal(m);
        lei.setQtd_leite(8);      
        lei.setValor_litro(1.5f);
        lei.setId_leite(6);

        
        LeiteDAO.create(lei);
   
        
  
        lei.setQtd_leite(9);

        LeiteDAO.update(lei);
        
        
        
	    for(Leite l: LeiteDAO.read()) {
	    	System.out.println(l);
	    }
        
        
        */
		
		
        Leite re = new Leite();
		re.setQtd_leite(4);
		re.setAnimal(m);
		re.setId_leite(15);
	  
	     
	 //   LeiteDAO.create(re); 
	     
		LeiteDAO.delete(re);
	     
	     for(Leite r: LeiteDAO.read()) {
		    	System.out.println(r);
		    }
	}
}
