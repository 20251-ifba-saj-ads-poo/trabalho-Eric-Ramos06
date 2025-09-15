package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.model.Visitante;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class MasterController {

    @FXML
    private Button menuItemCadAutor;

    @FXML
    private Button menuItemCadLivro;

    @FXML
    private Button menuItemHome;

    @FXML
    private Button menuItemListAutor;

    @FXML
    private Button menuItemListLivro;

    @FXML
    private BorderPane masterPane;

    @FXML
    private VBox menu;

    @FXML
    private Label userEmail;

    @FXML
    private Circle userPicture;

    @FXML
    private Button btnCadMotorista;
    @FXML
    private Button btnCadOnibus;
    @FXML
    private Button btnCadRota;
    @FXML
    private Button btnCadItinerario;
    @FXML
    private Button btnCadLinha;
    @FXML
    private Button btnExibirLinhas;
    @FXML
    private Button btnCadPonto;
    @FXML
    private Button btnListMotorista;
    @FXML
    private Button btnListOnibus;
    @FXML
    private Button btnListRota;
    @FXML
    private Button btnListItinerario;
    @FXML
    private Button btnListLinha;
    @FXML
    private Button btnListPonto;

    private Object usuarioLogado;

    public void setUsuarioLogado(Object usuario) {
        this.usuarioLogado = usuario;

        if (usuario instanceof Visitante) {
            configurarInterfaceParaVisitante();
        } else {
            configurarInterfaceParaUsuario();
        }
    }

    private void configurarInterfaceParaVisitante() {
        btnCadMotorista.setVisible(false);
        btnCadOnibus.setVisible(false);
        btnCadRota.setVisible(false);
        btnCadItinerario.setVisible(false);
        btnCadLinha.setVisible(false);
        btnCadPonto.setVisible(false);

        btnExibirLinhas.setVisible(true);
    }

    private void configurarInterfaceParaUsuario() {
        btnCadMotorista.setVisible(true);
        btnCadOnibus.setVisible(true);
        btnCadRota.setVisible(true);
        btnCadItinerario.setVisible(true);
        btnCadLinha.setVisible(true);
        btnCadPonto.setVisible(true);

        btnExibirLinhas.setVisible(true);
    }

    @FXML
    void logOff(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente sair??", ButtonType.YES, ButtonType.NO);
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    App.setRoot("controller/Login.fxml");
                });
    }

    @FXML
    void showHome(ActionEvent event) {
        limparBotoes(event.getSource());
        masterPane.setCenter(new Pane());

    }

    @FXML
    void showUsuarios(ActionEvent event) {
        limparBotoes(event.getSource());
        masterPane.setCenter(new Pane());
    }

    private void limparBotoes(Object source) {
        menu.getChildren().forEach((node) -> {
            if (node instanceof Button btn) {
                node.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            }
        }

        );
        if (source instanceof Button btn) {
            btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        }
    }

    @FXML
    void showListAutor(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListAutor.fxml");
    }

    @FXML
    void showListLivro(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListLivro.fxml");
    }

    @FXML
    void showListPonto(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListPonto.fxml");
    }

    @FXML
    void showListRota(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListRota.fxml");
    }

    @FXML
    void showListItinerario(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListItinerario.fxml");
    }

    @FXML
    void showListLinha(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListLinha.fxml");
    }

    @FXML
    void showListMotorista(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListMotorista.fxml");
    }

    @FXML
    void showListOnibus(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ListOnibus.fxml");
    }


    @FXML
    void showCadLivro(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadLivro.fxml");
    }

    @FXML
    void showMotorista(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadMotorista.fxml");
    }

    @FXML
    void showOnibus(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadOnibus.fxml");
    }

    @FXML
    void showPonto(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadPonto.fxml");
    }

    @FXML
    void showRota(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadRota.fxml");
    }

    @FXML
    void showItinerario(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadItinerario.fxml");
    }

    @FXML
    void showLinha(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("CadLinha.fxml");
    }

    @FXML
    void showLinhasVisu(ActionEvent event) {
        limparBotoes(event.getSource());
        showFXMLFile("ShowLinhasVisu.fxml");
    }

    public Object showFXMLFile(String resourceName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));
            Pane fxmlCarregado = loader.load();
            masterPane.setCenter(fxmlCarregado);
            return loader.getController();

        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao carregar o arquivo " + resourceName).showAndWait();
            e.printStackTrace();
        }
        return null;
    }

}
