package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Linha  extends AbstractModel<UUID>{

    private String Nome;
    private Onibus onibus;
    private Motorista motorista;
    private Itinerario itinerarios;

    public Linha (String nome, Onibus onibus, Motorista motorista, Itinerario itinerario) {
        this.Nome = nome;
        this.onibus = onibus;
        this.motorista = motorista;
        this.itinerarios = itinerario;
    }

    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public Onibus getOnibus() {
        return onibus;
    }
    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Itinerario getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(Itinerario itinerarios) {
        this.itinerarios = itinerarios;
    }

}