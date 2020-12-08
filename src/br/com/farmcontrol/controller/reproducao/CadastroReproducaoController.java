/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.reproducao;

import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Reproducao;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author viniciuslopes
 */
public class CadastroReproducaoController implements Initializable {

    
    @FXML
    private TextField idAnimal;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField descricao;

    @FXML
    private TextField data;
    
    @FXML
    private ListView<Animal> listAnimais;
    
    private ObservableList<Animal> obsAnimais;
    
    private List<Animal> animais = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarAnimais();
    }
    
    public void cadastrarReproducao(){
        
        Animal a = listAnimais.getSelectionModel().getSelectedItem();
        
        if(idAnimal.getText()==null || idAnimal.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Selecione primeiro um animal!");
        }else if(data.getText()==null||data.getText()==""||
                quantidade.getText()==null||quantidade.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e quantidade "
                    + "são obrigatórios");
        }else{
            Reproducao r = new Reproducao();
            r.setAnimal(a);
            r.setData_reproducao(dataPadrao(data.getText()));
            r.setDescricao_reprod(descricao.getText());
            r.setQntd_reproducao(Integer.parseInt(quantidade.getText()));
            ReproducaoDAO.create(r);
            idAnimal.setText(null);
            data.setText(null);
            descricao.setText(null);
            quantidade.setText(null);
        }
       
    }
    
    public void pegarIdAnimal(){
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        idAnimal.setText(String.valueOf(m.getId_animal()));
    }

    public void carregarAnimais(){
        animais = AnimalDAO.read();
        obsAnimais = FXCollections.observableArrayList(animais);
        listAnimais.setItems(obsAnimais);
    }    
    
    public Date dataPadrao(String s){
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
}
