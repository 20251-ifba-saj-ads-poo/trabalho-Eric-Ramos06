package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Itinerario extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;
    @Column
    @NotBlank
    @Size(min = 5)
    private String horaPartida;

    @ManyToOne
    @NotNull
    private Rota rota;

    public Itinerario(@NotBlank @Size(min = 5) String nome, @NotBlank @Size(min = 5) String horaPartida, Rota rota) {
        this.nome = nome;
        this.horaPartida = horaPartida;
        this.rota = rota;
    }

    public Itinerario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public String getHoraPartida() {
        return horaPartida;
    }
    

    @Override
    public String toString() {
        return "Itinerario [nome=" + nome + ", horaPartida=" + horaPartida + ", rota=" + rota + "]";
    }

}