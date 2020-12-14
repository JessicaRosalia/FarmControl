/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Ovo, onde serão contidos atributos e métodos para permitir
 * a manipulação de instâncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */

public class Ovo {
	
	/**
	 * Utilizado para definir o id do ovo
	 */
    private int id_ovo;
    
    /** Utilizado para definir o valor de cada unidade de ovo
     * 
     */
    private float valor_unidade;
    
    /**
     * Utilizado para definir a quantidade de ovo
     */
    private int qtd_ovos;
    
    /** 
     * Utilizado para definir a data de produção do ovo
     */
    private Date data_producao;
    
    /**
     * Utilizada para definir o lote 
     */
    private LoteAves lote;


    public int getId_ovo() {
        return id_ovo;
    }

    public void setId_ovo(int id_ovo) {
        this.id_ovo = id_ovo;
    }

    public float getValor_unidade() {
        return valor_unidade;
    }

    public void setValor_unidade(float valor_unidade) {
        this.valor_unidade = valor_unidade;
    }

    public int getQtd_ovos() {
        return qtd_ovos;
    }

    public void setQtd_ovos(int qtd_ovos) {
        this.qtd_ovos = qtd_ovos;
    }

    public Date getData_producao() {
        return data_producao;
    }

    public void setData_producao(Date data_producao) {
        this.data_producao = data_producao;
    }

    public LoteAves getLote() {
        return lote;
    }

    public void setLote(LoteAves lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "ID: "+this.id_ovo+" - "+this.data_producao;
    }
    
}
