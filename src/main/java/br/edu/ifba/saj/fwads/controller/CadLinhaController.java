package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Linha;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.service.ItinerarioService;
import br.edu.ifba.saj.fwads.service.LinhaService;
import br.edu.ifba.saj.fwads.service.MotoristaService;
import br.edu.ifba.saj.fwads.service.OnibusService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadLinhaController {

    @FXML
    private TextField txNomeLinha;

    @FXML
    private ChoiceBox<Onibus> slOnibus;

    @FXML
    private ChoiceBox<Motorista> slMotorista;

    @FXML
    private ChoiceBox<Itinerario> slItinerario;

    private LinhaService serviceLinha = new LinhaService();
    private ItinerarioService serviceItinerario = new ItinerarioService();
    private MotoristaService serviceMotorista = new MotoristaService();
    private OnibusService serviceOnibus = new OnibusService();

    @FXML
    private void initialize() {
        carregarListaOnibus();
        carregarListaMotorista();
        carregarListaItinerario();

        slItinerario.setConverter(new StringConverter<Itinerario>() {
            @Override
            public String toString(Itinerario obj) {
                return obj != null ? obj.getNome() : "";
            }

            @Override
            public Itinerario fromString(String nome) {
                return serviceItinerario.buscarTodosItinerarios().stream()
                    .filter(it -> nome.equals(it.getNome()))
                    .findFirst()
                    .orElse(null);
            }
        });

        slMotorista.setConverter(new StringConverter<Motorista>() {
            @Override
            public String toString(Motorista obj) {
                return obj != null ? obj.getNome() : "";
            }

            @Override
            public Motorista fromString(String nome) {
                return serviceMotorista.buscarTodosMotoristas().stream()
                    .filter(m -> nome.equals(m.getNome()))
                    .findFirst()
                    .orElse(null);
            }
        });

        slOnibus.setConverter(new StringConverter<Onibus>() {
            @Override
            public String toString(Onibus obj) {
                return obj != null ? obj.getPlaca() : "";
            }

            @Override
            public Onibus fromString(String placa) {
                return serviceOnibus.buscarTodosOnibus().stream()
                    .filter(o -> placa.equals(o.getPlaca()))
                    .findFirst()
                    .orElse(null);
            }
        });
    }

    @FXML
    private void salvarLinha(ActionEvent event) {
        try {
            String nomeLinha = txNomeLinha.getText();
            serviceLinha.validarDuplicidade(nomeLinha);

            Linha novaLinha = new Linha();
            novaLinha.setNome(nomeLinha);
            novaLinha.setOnibus(slOnibus.getValue());
            novaLinha.setMotorista(slMotorista.getValue());
            novaLinha.setItinerario(slItinerario.getValue());

            serviceLinha.create(novaLinha);

            new Alert(AlertType.INFORMATION,
                "Linha cadastrada com sucesso: " + novaLinha.getNome()).showAndWait();

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
    private void limparTela() {
        txNomeLinha.clear();
        slOnibus.setValue(null);
        slMotorista.setValue(null);
        slItinerario.setValue(null);
    }

    @FXML
    private void cancel(ActionEvent event) {
        App.setRoot("controller/Menu.fxml");
    }

    private void carregarListaOnibus() {
        ObservableList<Onibus> lista = FXCollections.observableArrayList(serviceOnibus.buscarTodosOnibus());
        slOnibus.setItems(lista);
    }

    private void carregarListaMotorista() {
        ObservableList<Motorista> lista = FXCollections.observableArrayList(serviceMotorista.buscarTodosMotoristas());
        slMotorista.setItems(lista);
    }

    private void carregarListaItinerario() {
        ObservableList<Itinerario> lista = FXCollections.observableArrayList(serviceItinerario.buscarTodosItinerarios());
        slItinerario.setItems(lista);
    }
}

//package br.edu.ifba.saj.fwads.controller;
//
//import br.edu.ifba.saj.fwads.Dados;
//import br.edu.ifba.saj.fwads.model.Itinerario;
//import br.edu.ifba.saj.fwads.model.Linha;
//import br.edu.ifba.saj.fwads.model.Motorista;
//import br.edu.ifba.saj.fwads.model.Onibus;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import javafx.util.StringConverter;
//
//public class CadLinhaController {
//
//    @FXML
//    private TextField txNomeLinha;
//
//    @FXML
//    private ChoiceBox<Onibus> slOnibus;
//
//    @FXML
//    private ChoiceBox<Motorista> slMotorista;
//
//    @FXML
//    private ChoiceBox<Itinerario> slItinerario;
//
//    @FXML
//    private void salvarLinha() {
//        String nomeLinha = txNomeLinha.getText();
//        Onibus onibus = slOnibus.getValue();
//        Motorista motorista = slMotorista.getValue();
//        Itinerario itinerario = slItinerario.getValue();
//
//        Linha novaLinha = new Linha(nomeLinha, onibus, motorista, itinerario);
//
//        new Alert(AlertType.INFORMATION,
//                "Cadastrando Linha:" + novaLinha.getNome()).showAndWait();
//        Dados.listaLinha.add(novaLinha);
//        limparTela();
//
//    }
//   @FXML 
//    private void initialize() {
//        
//        slItinerario.setConverter(new StringConverter<Itinerario>() {
//            @Override
//            public String toString(Itinerario obj) {
//                if (obj != null) {
//                    return obj.getNome();
//                }
//                return "";
//            }
//
//            @Override
//            public Itinerario fromString(String stringItinerario) {
//                return Dados.listaItinerario
//                    .stream()
//                    .filter(itinerario -> stringItinerario.equals(itinerario.getNome()))
//                    .findAny()
//                    .orElse(null);                
//            }
//        });
//
//        carregarListaItinerario();
//        
//        slMotorista.setConverter(new StringConverter<Motorista>() {
//            @Override
//            public String toString(Motorista obj) {
//                if (obj != null) {
//                    return obj.getNome();
//                }
//                return "";
//            }
//
//
//            @Override
//            public Motorista fromString(String stringMotorista) {
//                return Dados.listaMotorista
//                    .stream()
//                    .filter(motorista -> stringMotorista.equals(motorista.getNome()))
//                    .findAny()
//                    .orElse(null);                
//            }
//        });
//
//        carregarListaMotorista();
//
//        slOnibus.setConverter(new StringConverter<Onibus>() {
//            @Override
//            public String toString(Onibus obj) {
//                if (obj != null) {
//                    return obj.getPlaca();
//                }
//                return "";
//            }
//
//            @Override
//            public Onibus fromString(String stringOnibus) {
//                return Dados.listaOnibus
//                    .stream()
//                    .filter(onibus -> stringOnibus.equals(onibus.getPlaca()))
//                    .findAny()
//                    .orElse(null);                
//            }
//        });
//
//        carregarListaOnibus();
//    }
//
//    @FXML
//    private void limparTela() {
//        txNomeLinha.setText("");
//    }
//
//    private void carregarListaOnibus() {
//        slOnibus.setItems(Dados.listaOnibus);
//    }
//
//    private void carregarListaMotorista() {
//        slMotorista.setItems(Dados.listaMotorista);
//    }
//
//    private void carregarListaItinerario() {
//        slItinerario.setItems(Dados.listaItinerario);
//    }
//
//}