package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.PontoService;
import br.edu.ifba.saj.fwads.service.RotaService;
import br.edu.ifba.saj.fwads.model.Ponto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import br.edu.ifba.saj.fwads.App;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CadRotaController {
   @FXML
    private TextField txNome;
    
    @FXML
    private ChoiceBox<Ponto> slPontoInitial;
    
    @FXML
    private ChoiceBox<Ponto> slPontoFinal;
    
    @FXML
    private ListView<Ponto> lvParadas;
    
    @FXML
    private Button btnSalvar;

    private RotaService serviceRota = new RotaService();


    private final StringConverter<Ponto> pontoConverter = new StringConverter<>() {
   
        @Override
        public String toString(Ponto p) {
            return p != null ? p.getEndereco() : "";
        }
        @Override
        public Ponto fromString(String text) {
            PontoService pontoService = new PontoService();
            return pontoService.buscarTodosPontos().stream()
                .filter(pt -> pt.getEndereco().equals(text))
                .findFirst()
                .orElse(null);
        }
    };

    @FXML
    private void initialize(){
    PontoService pontoService = new PontoService();

    ObservableList<Ponto> obsPontos = 
        pontoService.buscarTodosPontos().stream()
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    slPontoInitial.setItems(obsPontos);
    slPontoFinal.setItems(obsPontos);
    slPontoInitial.setConverter(pontoConverter);
    slPontoFinal.setConverter(pontoConverter);


    lvParadas.setItems(obsPontos);
    lvParadas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    lvParadas.setCellFactory(list -> new ListCell<>() {
        @Override
        protected void updateItem(Ponto item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty || item == null ? "" : item.getEndereco());
        }
    });


    btnSalvar.disableProperty().bind(
        txNome.textProperty().isEmpty()
        .or(slPontoInitial.valueProperty().isNull())
        .or(slPontoFinal.valueProperty().isNull())
        .or(Bindings.size(lvParadas.getSelectionModel().getSelectedItems())
                     .lessThan(1))
    );
    }


    @FXML
    void salvarRota(ActionEvent event) {

        try {
            serviceRota.validarDuplicidade(txNome.getText());

            Rota novaRota = new Rota(txNome.getText(), slPontoInitial.getValue(), slPontoFinal.getValue());
            serviceRota.create(novaRota);
            new Alert(AlertType.INFORMATION,
                    "Rota cadastrada com sucesso: " + novaRota.getNome()).showAndWait();
            limparTela();
            // if (listMotoristaController != null) {
            // listMotoristaController.loadMotoristaList();
            // }

        } catch (EvitarDuplicidadeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erro inesperado, favor entra em contato com a equipe de desenvolvimento").showAndWait();
        }
    }

    @FXML
    void newBook(ActionEvent actionEvent) {

    }

    @FXML
    void cancel(ActionEvent event) {
        App.setRoot("controller/Menu.fxml");
    }

    @FXML
    private void limparTela() {
        txNome.setText("");
        slPontoInitial.setValue(null);
        slPontoFinal.setValue(null);
        lvParadas.getSelectionModel().clearSelection();
    }
}


//package br.edu.ifba.saj.fwads.controller;
//
//import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
//import br.edu.ifba.saj.fwads.model.Rota;
//import br.edu.ifba.saj.fwads.service.PontoService;
//import br.edu.ifba.saj.fwads.service.RotaService;
//import br.edu.ifba.saj.fwads.model.Ponto;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.Alert.AlertType;
//import javafx.util.StringConverter;
//import java.util.stream.Collectors;
//import javafx.beans.binding.Bindings;
//
//public class CadRotaController {
//
//    @FXML
//    private TextField txNome;
//    
//    @FXML
//    private ChoiceBox<Ponto> slPontoInitial;
//    
//    @FXML
//    private ChoiceBox<Ponto> slPontoFinal;
//    
//    @FXML
//    private ListView<Ponto> lvParadas;
//    
//    @FXML
//    private Button btnSalvar;
//
//    private RotaService serviceRota = new RotaService();
//
//    @FXML
//    void salvarRota(ActionEvent event) {
//
//        try {
//            serviceRota.validarDuplicidade(txNome.getText());
//
//            Rota novaRota = new Rota(txNome.getText(), slPontoInitial.getValue(), slPontoFinal.getValue());
//            serviceRota.create(novaRota);
//            new Alert(AlertType.INFORMATION,
//                    "Rota cadastrada com sucesso: " + novaRota.getNome()).showAndWait();
//            limparTela();
//            // if (listMotoristaController != null) {
//            // listMotoristaController.loadMotoristaList();
//            // }
//        } catch (EvitarDuplicidadeException e) {
//            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(AlertType.ERROR, "Erro inesperado, favor entrar em contato com a equipe de desenvolvimento")
//                    .showAndWait();
//        }
//    }   
//        
//        private final StringConverter<Ponto> pontoConverter = new StringConverter<>() {
//        @Override
//        public String toString(Ponto p) {
//            return p != null ? p.getEndereco() : "";
//        }
//        @Override
//        public Ponto fromString(String text) {
//            PontoService pontoService = new PontoService();
//            return pontoService.bucasTodos().stream()
//                .filter(pt -> pt.getEndereco().equals(text))
//                .findFirst()
//                .orElse(null);
//        }
//    };
//
//    @FXML
//public void initialize() {
//    PontoService pontoService = new PontoService();
//
//    ObservableList<Ponto> obsPontos = 
//        pontoService.bucasTodos().stream()
//            .collect(Collectors.toCollection(FXCollections::observableArrayList));
//    slPontoInitial.setItems(obsPontos);
//    slPontoFinal.setItems(obsPontos);
//    slPontoInitial.setConverter(pontoConverter);
//    slPontoFinal.setConverter(pontoConverter);
//
//
//    lvParadas.setItems(obsPontos);
//    lvParadas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//    lvParadas.setCellFactory(list -> new ListCell<>() {
//        @Override
//        protected void updateItem(Ponto item, boolean empty) {
//            super.updateItem(item, empty);
//            setText(empty || item == null ? "" : item.getEndereco());
//        }
//    });
//
//    btnSalvar.disableProperty().bind(
//        txNome.textProperty().isEmpty()
//        .or(slPontoInitial.valueProperty().isNull())
//        .or(slPontoFinal.valueProperty().isNull())
//        .or(Bindings.size(lvParadas.getSelectionModel().getSelectedItems())
//                     .lessThan(1))
//    );
//}
//
//
//    @FXML
//    private void limparTela() {
//        txNome.clear();
//        slPontoInitial.getSelectionModel().clearSelection();
//        slPontoFinal.getSelectionModel().clearSelection();
//        lvParadas.getSelectionModel().clearSelection();
//    }
//
//    
//}