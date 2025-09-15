package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Linha;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.service.LinhaService;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.collections.FXCollections;

import java.util.stream.Collectors;

public class ShowLinhasVisuController {

    @FXML
    private ListView<Linha> listViewLinhas;

    private LinhaService linhaService = new LinhaService();

    @FXML
    private void initialize() {
        listViewLinhas.setItems(FXCollections.observableArrayList(linhaService.buscarTodosLinhas()));

        listViewLinhas.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Linha linha, boolean empty) {
                super.updateItem(linha, empty);

                if (empty || linha == null) {
                    setText(null);
                } else {
                    String nomeLinha = linha.getNome();
                    String nomeMotorista = linha.getMotorista() != null ? linha.getMotorista().getNome() : "N/A";
                    String cpfMotorista = linha.getMotorista() != null ? linha.getMotorista().getCpf() : "N/A";
                    String placaOnibus = linha.getOnibus() != null ? linha.getOnibus().getPlaca() : "N/A";

                    Itinerario iti = linha.getItinerario();
                    String nomeItinerario = iti != null ? iti.getNome() : "N/A";
                    String horaPartida = iti != null ? iti.getHoraPartida() : "N/A";

                    Rota rota = iti != null ? iti.getRota() : null;
                    String nomeRota = rota != null ? rota.getNome() : "N/A";
                    String pontoInicial = rota != null && rota.getPontoInicial() != null ? rota.getPontoInicial().getEndereco() : "N/A";
                    String pontoFinal = rota != null && rota.getPontoFinal() != null ? rota.getPontoFinal().getEndereco() : "N/A";

                    String paradas = rota != null && rota.getParadas() != null
                        ? rota.getParadas().stream()
                            .map(Ponto::getEndereco)
                            .collect(Collectors.joining(", "))
                        : "Nenhuma parada cadastrada";

                    String info = String.format(
                        "Linha: %s%n" +
                        "Motorista: %s (CPF: %s)%n" +
                        "Ônibus: %s%n" +
                        "Itinerário: %s às %s%n" +
                        "Rota: %s (%s → %s)%n" +
                        "Paradas: %s",
                        nomeLinha,
                        nomeMotorista,
                        cpfMotorista,
                        placaOnibus,
                        nomeItinerario,
                        horaPartida,
                        nomeRota,
                        pontoInicial,
                        pontoFinal,
                        paradas
                    );

                    setText(info);
                    setWrapText(true);
                    setPrefHeight(Region.USE_COMPUTED_SIZE);
                }
            }
        });
    }
}
