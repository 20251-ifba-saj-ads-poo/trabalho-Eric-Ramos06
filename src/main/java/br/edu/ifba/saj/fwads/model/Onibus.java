package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Onibus  extends AbstractEntity{
    @Column
    @NotBlank
    @Size(min = 5)
    private String placa;

    public Onibus(@NotBlank @Size(min = 5) String placa) {
        this.placa = placa;
    }

    public Onibus() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Onibus [placa=" + placa + "]";
    }

}