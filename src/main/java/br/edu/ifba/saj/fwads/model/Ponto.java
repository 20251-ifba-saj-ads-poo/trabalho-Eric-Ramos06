package br.edu.ifba.saj.fwads.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Ponto extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String endereco;

    @ManyToMany(mappedBy = "paradas")
    private List<Rota> rotas;

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

    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }

}