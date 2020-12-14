/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.animal;

import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Mamifero;
import java.net.URL;
import java.sql.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class CadastroAvesController implements Initializable {
    
    @FXML
    private TextField raca;

    @FXML
    private TextField dataNasc;
    
    @FXML
    private TextField quantidade;

    @FXML
    private ComboBox<String> cbTipo;

    
    private ObservableList<String> obsTipo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] itens = new String[2];
        itens[0] = "Tipo Galinha";
        itens[1] = "Tpo Codorna";
        
        
        obsTipo=FXCollections.observableArrayList(itens);
        cbTipo.setItems(obsTipo);
        
    }    
    
    public void cadastrarMamifero(){
        
        LoteAves m = new LoteAves();
        if(raca.getText()==null || dataNasc.getText()==null ||
                cbTipo.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null, "Há campos a serem preecnhidos!");
        }else{
            m.setRaca(raca.getText());
            m.setData_nasc_aquisicao(dataPadrao(dataNasc.getText()));
            m.setTipo_ave(cbTipo.getSelectionModel().getSelectedItem());
            m.setQuantidade(Integer.parseInt(quantidade.getText()));
            LoteAvesDAO.create(m);
            limparCampos();
        }
        
    }
    
    public void limparCampos(){
        raca.setText(null);
        dataNasc.setText(null);
    }
    
    public Date dataPadrao(String s){
        if(s==null || s.equals("null") || s.equals("")){
            return null;
        }
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
    

    @FXML
    void sair() {

    }
    
}
