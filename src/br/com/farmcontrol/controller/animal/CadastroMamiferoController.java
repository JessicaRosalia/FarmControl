/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.animal;

import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.vo.Mamifero;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;


public class CadastroMamiferoController implements Initializable {
    
    @FXML
    private TextField raca;

    @FXML
    private TextField dataNasc;

    @FXML
    private ComboBox<String> cbSexo;

    @FXML
    private ComboBox<String> cbTipo;
    
    private ObservableList<String> obsSexo;
    private ObservableList<String> obsTipo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] itens = new String[2];
        itens[0] = "Macho";
        itens[1] = "Femea";
        
        String[] tipos = new String[2];
        tipos[0] = "Bovino";
        tipos[1] = "Suíno";
        
        obsSexo=FXCollections.observableArrayList(itens);
        cbSexo.setItems(obsSexo);
        
        obsTipo=FXCollections.observableArrayList(tipos);
        cbTipo.setItems(obsTipo);
    }    
    
    public void cadastrarMamifero(){
        
        Mamifero m = new Mamifero();
        if(raca.getText()==null || dataNasc.getText()==null ||
                cbSexo.getSelectionModel().getSelectedItem()==null ||
                cbTipo.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null, "Há campos a serem preecnhidos!");
        }else{
            m.setRaca(raca.getText());
            m.setData_nasc_aquisicao(dataPadrao(dataNasc.getText()));
            m.setSexo_mamifero(cbSexo.getSelectionModel().getSelectedItem());
            m.setTipo_mamifero(cbTipo.getSelectionModel().getSelectedItem());
            MamiferoDAO.create(m);
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
    
    public void sair(){
        MenuAnimalController:sair();
    }
    
}
