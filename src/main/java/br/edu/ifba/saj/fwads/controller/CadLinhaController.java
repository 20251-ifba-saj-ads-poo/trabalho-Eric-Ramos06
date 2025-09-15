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
            public String toString(Itinerario it) {
                if (it != null) {
                    String rotaNome = it.getRota() != null ? it.getRota().getNome() : "Rota N/D";
                    String origem = it.getRota() != null && it.getRota().getPontoInicial() != null
                            ? it.getRota().getPontoInicial().getEndereco()
                            : "Origem N/D";
                    String destino = it.getRota() != null && it.getRota().getPontoFinal() != null
                            ? it.getRota().getPontoFinal().getEndereco()
                            : "Destino N/D";

                    return String.format("%s | Partida: %s | %s → %s (%s)",
                            it.getNome(), it.getHoraPartida(), origem, destino, rotaNome);
                }
                return "";
            }

            @Override
            public Itinerario fromString(String text) {
                return serviceItinerario.buscarTodosItinerarios().stream()
                        .filter(it -> text.contains(it.getNome()))
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
        ObservableList<Itinerario> lista = FXCollections
                .observableArrayList(serviceItinerario.buscarTodosItinerarios());
        slItinerario.setItems(lista);
    }
}
