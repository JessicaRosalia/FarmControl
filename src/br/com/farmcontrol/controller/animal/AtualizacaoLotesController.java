/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.animal;

import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.LoteAves;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author viniciuslopes
 */
public class AtualizacaoLotesController implements Initializable {

    /**
     * Initializes the controller class.
     */
	@FXML
	    private AnchorPane crudLoteAves;
    
    @FXML
    private Button atualizarAnimal;
    
    @FXML
    private ListView<LoteAves> listAnimais;
    
    private ObservableList<LoteAves> obsAnimais;
    
    private List<LoteAves> animais = new ArrayList<>();
    
    @FXML
    private TextField valorVenda;

    @FXML
    private TextField dataVenda;

    @FXML
    private TextField dataAquisicao;

    @FXML
    private TextField raca;

    @FXML
    private TextField id;

    @FXML
    private Pane panelMamifero;

    @FXML
    private TextField sexoMamifero;

    @FXML
    private TextField valorArroba;

    @FXML
    private TextField peso;

    @FXML
    private TextField tipoMamifero;

    @FXML
    private TextField dataAbate;

    @FXML
    private Pane panelLote;

    @FXML
    private TextField tipoLote;

    @FXML
    private TextField quantidadeLote;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        carregarAnimais();
    }
    
    public void pegarInforAnimais(){
        LoteAves m = listAnimais.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(m.getId_animal()));
        raca.setText(m.getRaca());
        dataAquisicao.setText(String.valueOf(m.getData_nasc_aquisicao()));
        dataVenda.setText(String.valueOf(m.getData_venda()));
        valorVenda.setText(String.valueOf(m.getValor_venda()));
        quantidadeLote.setText(String.valueOf(m.getQuantidade()));
        tipoLote.setText(m.getTipo_ave());
    }
    
   public void atualizarAnimal() throws Exception{
        //testeLoad.getChildren().clear();
        
        LoteAves m = new LoteAves();
        
        m.setId_animal(Integer.parseInt(id.getText()));
        m.setRaca(raca.getText());
        m.setData_nasc_aquisicao(dataPadrao(dataAquisicao.getText()));
        m.setData_venda(dataPadrao(dataVenda.getText()));
        m.setValor_venda(Float.parseFloat(valorVenda.getText()));
        m.setQuantidade(Integer.parseInt(quantidadeLote.getText()));
        m.setTipo_ave(tipoLote.getText());
        
        
        //LoteAvesDAO.update(m);
        
        id.setText("");
        raca.setText("");
        dataAquisicao.setText("");
        dataVenda.setText("");
        valorVenda.setText("");
        quantidadeLote.setText("");
        tipoLote.setText("");
        
        carregarAnimais();
      
    }
 
    public Date dataPadrao(String s){
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
    
    public void carregarAnimais(){
        
       // animais =  AnimalDAO.read();
        
        //animais = LoteAvesDAO.read();
        
        obsAnimais = FXCollections.observableArrayList(animais);

        listAnimais.setItems(obsAnimais);
        
    }
    
}
