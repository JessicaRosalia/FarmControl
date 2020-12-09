package br.com.farmcontrol.controller.ovo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CrudOvoController {

    @FXML
    private Pane paneAnimais;

    @FXML
    private ListView<?> listOvos;

    @FXML
    private ListView<?> listOvo;

    @FXML
    private TextField idOvos;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField qtd;

    @FXML
    private TextField valor_unid;

    @FXML
    private TextField data_prod;

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
    private TextField cadastrar_qtd;

    @FXML
    private TextField cadastrar_desc;

    @FXML
    private TextField cadastrar_data;

    @FXML
    private Button cadastrar_button;

    @FXML
    private Button cancelar_button;

    @FXML
    void atualizarOvos(ActionEvent event) {

    }

    @FXML
    void cadastrarOvos(ActionEvent event) {

    }

    @FXML
    void cancelarCadastro(ActionEvent event) {

    }

    @FXML
    void carregarReproducoes(MouseEvent event) {

    }

    @FXML
    void deletarOvos(ActionEvent event) {

    }

    @FXML
    void novoOvos(ActionEvent event) {

    }

    @FXML
    void pegarDadosReproducoes(MouseEvent event) {

    }

}
