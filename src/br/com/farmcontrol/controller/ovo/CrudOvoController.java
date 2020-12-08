/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.ovo;

import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.OvoDao;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Ovo;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author viniciuslopes
 */
public class CRUDOvoController implements Initializable {
    
    @FXML
    private Pane paneAnimais;

    @FXML
    private ListView<LoteAves> listAnimais;
    
    private List<LoteAves> animais = new ArrayList<>();
    
    private ObservableList<LoteAves> obsAnimal;

    @FXML
    private ListView<Ovo> listOvos;
    
    private List<Ovo> ovos = new ArrayList<>();
            
    private ObservableList<Ovo> obsOvos;
    
    @FXML
    private TextField idovo;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField valor_unidade;

    @FXML
    private TextField data;

    @FXML
    private Button atualizar;

    @FXML
    private Button deletar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarAnimais();
    }
    
    public void deletarOvo(){
        Ovo r = listOvos.getSelectionModel().getSelectedItem();
        OvoDao.delete(r);
        carregarOvos();
    }
    
    public void atualizarOvo(){
        Ovo r = new Ovo();
        LoteAves a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setLote(a);
        r.setData_producao(dataPadrao(data.getText()));
        r.setId_ovo(Integer.parseInt(idovo.getText()));
        r.setQtd_ovos(Integer.parseInt(quantidade.getText()));
        r.setValor_unidade(Float.parseFloat(valor_unidade.getText()));
        OvoDao.update(r);
        carregarOvos();
    }
    
    public void pegarDadosOvo(){
        Ovo r = listOvos.getSelectionModel().getSelectedItem();
        idovo.setText(String.valueOf(r.getId_ovo()));
        quantidade.setText(String.valueOf(r.getQtd_ovos()));
        valor_unidade.setText(String.valueOf(r.getValor_unidade()));
        data.setText(String.valueOf(r.getData_producao()));
    }
    
    public void carregarOvos(){
        LoteAves m = listAnimais.getSelectionModel().getSelectedItem();
        ovos = OvoDao.read(m);
        obsOvos = FXCollections.observableArrayList(ovos);
        listOvos.setItems(obsOvos);
        idanimal.setText(String.valueOf(m.getId_animal()));
        limparCampos();
    }
    
    public void carregarAnimais(){
        /*animais = LoteAvesDAO.read();      
        obsAnimal = FXCollections.observableArrayList(animais);
        listAnimais.setItems(obsAnimal);    */ 
    }
    
    public void limparCampos(){
        idovo.setText(null);
        quantidade.setText(null);
        valor_unidade.setText(null);
        data.setText(null);
    }
    
    public Date dataPadrao(String s){
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
    
}
