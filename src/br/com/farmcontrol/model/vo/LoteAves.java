/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

/** Classe para objetos do tipo LoteAves, onde ser�o contidos atributos e m�todos, 
 * sendo alguns destes heran�as de Animal. Permite a manipula��o de inst�ncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplica��o
 */
public class LoteAves extends Animal {
	
	/**
     * Utilizado para definir o tipo da ave
     */
    private String tipo_ave;
    
    /** Utilizado para definir a quantidade de ave em um lote
     * 
     */
    private int quantidade;
    

    public String getTipo_ave() {
        return tipo_ave;
    }

    public void setTipo_ave(String tipo_ave) {
        this.tipo_ave = tipo_ave;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "LoteID: "+this.getId_animal()+" - "+this.getRaca();
    }
    
}
