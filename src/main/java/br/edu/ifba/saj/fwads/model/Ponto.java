package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Ponto  extends AbstractEntity{
    @Column
    @NotBlank
    @Size(min = 5)
    private String endereco;

    public Ponto(@NotBlank @Size(min = 5) String endereco) {
        this.endereco = endereco;
    }

    public Ponto() {
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Ponto [endereco=" + endereco + "]";
    }

}