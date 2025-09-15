package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.ItinerarioService;
import br.edu.ifba.saj.fwads.service.RotaService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadItinerarioController {

    @FXML
    private TextField txNome;

    @FXML
    private TextField txHoraPartida;

    @FXML
    private ChoiceBox<Rota> slRota;

    @FXML
    private Button btnSalvar;

    private final ItinerarioService serviceItinerario = new ItinerarioService();

    private final RotaService serviceRota = new RotaService();

    private final StringConverter<Rota> rotaConverter = new StringConverter<>() {
        @Override
        public String toString(Rota r) {
            return r != null ? r.getNome() : "";
        }

        @Override
        public Rota fromString(String text) {
            return serviceRota.buscarTodosRotas().stream()
                .filter(rt -> rt.getNome().equals(text))
                .findFirst()
                .orElse(null);
        }
    };

    @FXML
    private void initialize() {
        ObservableList<Rota> obsRotas = FXCollections.observableArrayList(serviceRota.buscarTodosRotas());
        slRota.setItems(obsRotas);
        slRota.setConverter(rotaConverter);

        btnSalvar.disableProperty().bind(
            txNome.textProperty().isEmpty()
            .or(txHoraPartida.textProperty().isEmpty())
            .or(slRota.valueProperty().isNull())
        );
    }

    @FXML
    void salvarItinerario(ActionEvent event) {
        try {
            serviceItinerario.validarDuplicidade(txNome.getText());

            Itinerario novoItinerario = new Itinerario();
            novoItinerario.setNome(txNome.getText());
            novoItinerario.setHoraPartida(txHoraPartida.getText());
            novoItinerario.setRota(slRota.getValue());

            serviceItinerario.create(novoItinerario);

            new Alert(AlertType.INFORMATION,
                "Itinerário cadastrado com sucesso: " + novoItinerario.getNome()).showAndWait();

            limparTela();

        } catch (EvitarDuplicidadeException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(AlertType.ERROR,
                "Erro inesperado, favor entrar em contato com a equipe de desenvolvimento").showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        App.setRoot("controller/Menu.fxml");
    }

    @FXML
    private void limparTela() {
        txNome.clear();
        txHoraPartida.clear();
        slRota.setValue(null);
    }
}


//package br.edu.ifba.saj.fwads.controller;
//
//
//import br.edu.ifba.saj.fwads.Dados;
//import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
//import br.edu.ifba.saj.fwads.model.Itinerario;
//import br.edu.ifba.saj.fwads.model.Onibus;
//import br.edu.ifba.saj.fwads.model.Rota;
//import br.edu.ifba.saj.fwads.service.ItinerarioService;
//import br.edu.ifba.saj.fwads.service.OnibusService;
//import javafx.beans.binding.Bindings;
//import javafx.beans.binding.BooleanBinding;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import javafx.util.StringConverter;
//
//public class CadItinerarioController {
//
//    @FXML
//    private TextField txNome;
//
//    @FXML
//    private TextField txHoraPartida;
//
//    @FXML
//    private ChoiceBox<Rota> slRota;
//
//    @FXML
//    private Button btnSalvar;
//
//    private ItinerarioService serviceItinerario = new ItinerarioService();
//
//    @FXML
//    void salvarItinerario(ActionEvent event) {
//
//        try {
//            serviceItinerario.validarDuplicidade(txNome.getText());
//
//            Itinerario novoItinerario = new Itinerario(txNome.getText(), txHoraPartida.getText(), slRota.getValue());
//            serviceItinerario.create(novoItinerario);
//            new Alert(AlertType.INFORMATION,
//                    "Itinerário cadastrado com sucesso: " + novoItinerario.getNome()).showAndWait();
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
//    @FXML 
//    private void initialize() {
//
//        BooleanBinding camposInvalidos = Bindings.createBooleanBinding(
//            () -> txNome.getText() == null
//                   || txNome.getText().trim().isEmpty()
//                   || txHoraPartida.getText() == null
//                   || txHoraPartida.getText().trim().isEmpty()
//                   || slRota.getValue() == null,
//            txNome.textProperty(),
//            txHoraPartida.textProperty(),
//            slRota.valueProperty()
//        );
//
//        btnSalvar.disableProperty().bind(camposInvalidos);
//    
//
//        slRota.setConverter(new StringConverter<Rota>() {
//            @Override
//            public String toString(Rota obj) {
//                if (obj != null) {
//                    return obj.getNome();
//                }
//                return "";
//            }
//
//            @Override
//            public Rota fromString(String stringRota) {
//                return Dados.listaRota
//                    .stream()
//                    .filter(rota -> stringRota.equals(rota.getNome()))
//                    .findAny()
//                    .orElse(null);                
//            }
//        });
//
//        carregarListaRotas();
//    }
//
//
//    @FXML
//    private void limparTela() {
//        txNome.clear();
//        txHoraPartida.clear();
//        slRota.getSelectionModel().clearSelection();
//    }
//
//    private void carregarListaRotas() {
//        slRota.setItems(Dados.listaRota);
//    }
//}
//