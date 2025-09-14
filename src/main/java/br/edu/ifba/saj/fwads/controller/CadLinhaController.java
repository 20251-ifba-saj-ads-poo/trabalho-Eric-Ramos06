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