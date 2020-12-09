/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

/**
 *
 * @author viniciuslopes
 */
public class LoteAves extends Animal {
    
    private String tipo_ave;
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
        //return "LoteAves{" + "tipo_ave=" + tipo_ave + ", quantidade=" + quantidade + '}';
        //eturn "Lote ID: "+getId_animal()+" - "+getRaca();
        String r= super.toString();
        r+="- Tipo ave: "+this.tipo_ave+"- qtd: "+this.quantidade+"\n";
        return r;
    }
    
}
