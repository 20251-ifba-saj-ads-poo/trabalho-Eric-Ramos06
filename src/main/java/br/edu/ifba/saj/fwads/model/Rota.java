package br.edu.ifba.saj.fwads.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Rota extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @ManyToOne
    private Ponto pontoInicial;

    @ManyToOne
    private Ponto pontoFinal;

    @ManyToMany
    private List<Ponto> paradas;

    public Rota(@NotBlank @Size(min = 5) String nome, Ponto pontoInicial, Ponto pontoFinal, List<Ponto> paradas) {
        this.nome = nome;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
        this.paradas = paradas;
    }

    public Rota() {
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Ponto getPontoInicial() {
        return pontoInicial;
    }
    public void setPontoInicial(Ponto pontoInicial) {
        this.pontoInicial = pontoInicial;
    }
    public Ponto getPontoFinal() {
        return pontoFinal;
    }
    public void setPontoFinal(Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
    public List<Ponto> getParadas() {
        return paradas;
    }
    public void setParadas(List<Ponto> paradas) {
        this.paradas = paradas;
    }
    
    @Override
    public String toString() {
        return "Rota [nome=" + nome + ", pontoInicial=" + pontoInicial + ", pontoFinal=" + pontoFinal + ", paradas=" + paradas + "]";
    }
}