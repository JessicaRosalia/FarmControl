package br.com.farmcontrol.controller.animal;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuAnimalController {

    @FXML
    private AnchorPane crudAnimal;

    @FXML
    private Button cadastrar_mamifero;

    @FXML
    private Button cadastrar_lote;

    @FXML
    void cadastrarLote(ActionEvent event) {
    	AnchorPane crudLotesAves=null;
		try {
			crudLotesAves = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/animal/AtualizacaoLotes.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crudAnimal.getChildren().clear();
        crudAnimal.getChildren().add(crudLotesAves);
    }

    @FXML
    void cadastrarMamifero(ActionEvent event) {
    	AnchorPane crudMamifero=null;
		try {
			crudMamifero = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/animal/AtualizarMamifero.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        crudAnimal.getChildren().clear();
        crudAnimal.getChildren().add(crudMamifero);
    }

}
