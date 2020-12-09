package br.com.farmcontrol.controller.vacina;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CrudVacinaController {

    @FXML
    private Pane paneAnimais;

    @FXML
    private ListView<?> listVacinas;

    @FXML
    private ListView<?> listVacina;

    @FXML
    private TextField idVacina;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField descricao;

    @FXML
    private TextField custo;

    @FXML
    private TextField data;

    @FXML
    private Button atualizar;

    @FXML
    private Button deletar;

    @FXML
    private Button adicionar;

    @FXML
    private TitledPane titledPane;

    @FXML
    private TextField cadastrar_id;

    @FXML
    private TextField cadastrar_desc;

    @FXML
    private TextField cadastrar_custo;

    @FXML
    private TextField cadastrar_data;

    @FXML
    private Button cadastrar_button;

    @FXML
    private Button cancelar_button;

    @FXML
    void atualizarVacina(ActionEvent event) {

    }

    @FXML
    void cadastrarVacina(ActionEvent event) {

    }

    @FXML
    void cancelarCadastro(ActionEvent event) {

    }

    @FXML
    void carregarReproducoes(MouseEvent event) {

    }

    @FXML
    void deletarVacina(ActionEvent event) {

    }

    @FXML
    void novaVacina(ActionEvent event) {

    }

    @FXML
    void pegarDadosReproducoes(MouseEvent event) {

    }

}
