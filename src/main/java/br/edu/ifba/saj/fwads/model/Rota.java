package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Rota extends AbstractEntity {

    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @NotNull
    @ManyToOne
    private Ponto pontoInicial;

    @NotNull
    @ManyToOne
    private Ponto pontoFinal;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "rota_paradas", joinColumns = @JoinColumn(name = "rota_id"), inverseJoinColumns = @JoinColumn(name = "ponto_id"))
    private List<Ponto> paradas;

    public Rota(@NotBlank @Size String nome, @NotNull Ponto pontoInicial, @NotNull Ponto pontoFinal,
            List<Ponto> paradas) {

        this.nome = nome;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
        this.paradas = paradas;
    }

    public Rota() {
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public Ponto getPontoInicial() {
        return pontoInicial;
    }

    public Ponto getPontoFinal() {
        return pontoFinal;
    }

    public List<Ponto> getParadas() {
        return paradas;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPontoInicial(Ponto pontoInicial) {
        this.pontoInicial = pontoInicial;
    }

    public void setPontoFinal(Ponto pontoFinal) {
        this.pontoFinal = pontoFinal;
    }

    public void setParadas(List<Ponto> paradas) {
        this.paradas = paradas;
    }

    @Override
    public String toString() {
        return "Rota{" +
                "nome='" + nome + '\'' +
                ", pontoInicial=" + pontoInicial +
                ", pontoFinal=" + pontoFinal +
                ", paradas=" + paradas +
                '}';
    }

}
