package br.edu.ifba.saj.fwads.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ponto  extends AbstractModel<UUID>{
    private String endereco;

    public Ponto(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
