package br.com.farmcontrol.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LayoutController implements Initializable {
	

    @FXML
    private TextField lbqtdAnimais;


    @FXML
    private AnchorPane wrapper_pi;

    @FXML
    private AnchorPane menu;

    @FXML
    private Button pagina_inicial;

    @FXML
    private ImageView img_paginainicial;

    @FXML
    private Button animal;

    @FXML
    private ImageView img_animal;

    @FXML
    private Button vacina;

    @FXML
    private ImageView img_vacina;

    @FXML
    private Button leite;

    @FXML
    private ImageView img_leite;

    @FXML
    private Button ovos;

    @FXML
    private ImageView img_ovo;

    @FXML
    private Button racao;

    @FXML
    private ImageView img_racao;

    @FXML
    private Button reproducao;

    @FXML
    private ImageView img_reproducao;

    @FXML
    private Button relatorio;

    @FXML
    private ImageView img_relatorio;

    @FXML
    private ImageView logo;

    @FXML
    public AnchorPane crud;
    
    @FXML
    private Pane producoes;

    @FXML
    private Pane animais;

    @FXML
    private Pane racoes;
    
  //  @FXML
  //  private TextField lbqtdAnimais = new TextField();
    
 
    
    

    @FXML
    void exibirPaginaAnimais(ActionEvent event) {
    	AnchorPane crudAnimal=null;
		try {
			crudAnimal = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/animal/MenuAnimal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudAnimal);
    }

    @FXML
    void exibirPaginaInicial() {
    	AnchorPane home=null;
		try {
			home = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/pagina-inicial.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(home);
    }

    @FXML
    void exibirPaginaLeite() {
    	AnchorPane crudLeite=null;
		try {
			crudLeite = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/leite/CRUDLeite.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudLeite);
    }

    @FXML
    void exibirPaginaOvos() {
    	AnchorPane crudOvo=null;
		try {
			crudOvo = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/ovo/CRUDOvo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudOvo);
    }

    @FXML
    void exibirPaginaRacao() {
    	AnchorPane crudRacao=null;
		try {
			crudRacao = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/racao/CRUDRacao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudRacao);
    }

    @FXML
    void exibirPaginaRelatorios() {
    	AnchorPane crudRel=null;
		try {
			crudRel = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/relatorios/MenuRelatorios.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudRel);
    }

    @FXML
    void exibirPaginaReproducao() {
    	AnchorPane crudReprod=null;
		try {
			crudReprod = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/reproducao/CRUDReproducao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudReprod);
    }

    @FXML
    void exibirPaginaVacina() {
    	AnchorPane crudVacina=null;
		try {
			crudVacina = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/vacina/CrudVacina.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudVacina);
    }


    @FXML
    void qtdAnimais() {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		qtdAnimais();
	}
}
