package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Visitante extends AbstractEntity {

    @Column
    private String nome;

    @Column
    private String perfil;

    public Visitante() {
        this.nome = "Visitante";
        this.perfil = "VISITANTE";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Visitante [nome=" + nome + ", perfil=" + perfil + "]";
    }
}