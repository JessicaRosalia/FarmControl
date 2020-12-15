/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.connection;


public class ConnectionDemo {
    
    public static void main(String[] args){
        
        try {
            ConnectionFactory.getConnection();
            System.out.println("A conexão funcionou!");
        } catch (Exception e) {
            System.err.println("Erro na conexão!");
        }
           
    }
}
