package br.com.farmcontrol.controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LayoutController {

    @FXML
    private AnchorPane wrapper;

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
    void exibirPaginaInicial(ActionEvent event) {

    }

    @FXML
    void exibirPaginaLeite(ActionEvent event) {
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
    void exibirPaginaOvos(ActionEvent event) {
    	AnchorPane crudOvo=null;
		try {
			crudOvo = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/ovo/CrudOvo.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudOvo);
    }

    @FXML
    void exibirPaginaRacao(ActionEvent event) {
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
    void exibirPaginaRelatorios(ActionEvent event) {

    }

    @FXML
    void exibirPaginaReproducao(ActionEvent event) {
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
    void exibirPaginaVacina(ActionEvent event) {
    	AnchorPane crudVacina=null;
		try {
			crudVacina = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/vacina/CrudVacina.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crud.getChildren().clear();
        crud.getChildren().add(crudVacina);
    }

}
