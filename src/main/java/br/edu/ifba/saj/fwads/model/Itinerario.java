package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
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
    private String HoraPartida;

    @Column
    @NotBlank
    @Size(min = 5)
    private Rota rota;

    public Itinerario(@NotBlank @Size(min = 5) String nome, @NotBlank @Size(min = 5) String HoraPartida, Rota rota) {
        this.nome = nome;
        this.HoraPartida = HoraPartida;
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

    public void setHoraPartida(String HoraPartida) {
        this.HoraPartida = HoraPartida;
    }

    public String getHoraPartida() {
        return HoraPartida;
    }

    @Override
    public String toString() {
        return "Itinerario [nome=" + nome + ", HoraPartida=" + HoraPartida + ", rota=" + rota + "]";
    }

}