package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Onibus extends AbstractModel<UUID>{
    private String placa;

    public Onibus(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
