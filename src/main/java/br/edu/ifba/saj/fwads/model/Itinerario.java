package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Itinerario  extends AbstractModel<UUID>{
    private String Nome;
    private String horaPartida;
    private Rota rota;


    public Itinerario(String nome, String horaPartida, Rota rota) {
        this.Nome = nome;
        this.horaPartida = horaPartida;
        this.rota = rota;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
    public String getHoraPartida() {
        return horaPartida;
    }    
    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }

    public Rota getRota() {
        return rota;
    }
    public void setRota(Rota rota) {
        this.rota = rota;
    }

}
