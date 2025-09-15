package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Linha extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @NotNull
    @ManyToOne
    private Onibus onibus;

    @NotNull
    @ManyToOne
    private Motorista motorista;

    @NotNull
    @ManyToOne
    private Itinerario itinerario;

    public Linha(String nome, Onibus onibus, Motorista motorista, Itinerario itinerario) {
        this.nome = nome;
        this.onibus = onibus;
        this.motorista = motorista;
        this.itinerario = itinerario;
    }

    public Linha() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    @Override
    public String toString() {
        return "Linha [nome=" + nome + ", onibus=" + onibus + ", motorista=" + motorista + ", itinerario=" + itinerario
                + "]";
    }

}