package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Rota extends AbstractEntity{

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

    @ManyToMany
    private List<Ponto> paradas;



    public Rota(@NotBlank @Size String nome, @NotNull Ponto pontoInicial, @NotNull Ponto pontoFinal){

        this.nome = nome;
        this.pontoInicial = pontoInicial;
        this.pontoFinal = pontoFinal;
        this.paradas = new ArrayList<>();
    }

    public Rota() {
    }

    // Getters
    public String getNome() { return nome; }
    public Ponto getPontoInicial() { return pontoInicial; }
    public Ponto getPontoFinal() { return pontoFinal; }
    public List<Ponto> getParadas() { return paradas; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setPontoInicial(Ponto pontoInicial) { this.pontoInicial = pontoInicial; }
    public void setPontoFinal(Ponto pontoFinal) { this.pontoFinal = pontoFinal; }
    public void setParadas(List<Ponto> paradas) { this.paradas = paradas; }


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










//package br.edu.ifba.saj.fwads.model;
//
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//
//@Entity
//public class Rota extends AbstractEntity {
//    @Column
//    @NotBlank
//    @Size(min = 5)
//    private String nome;
//
//    @Column
//    @NotBlank
//    @Size(min = 5)
//    private Ponto pontoInicial;
//
//    @Column
//    @NotBlank
//    @Size(min = 5)
//    private Ponto pontoFinal;
//
//    @Column
//    @NotBlank
//    @Size(min = 5)
//    private List<Ponto> pontos;
//
//    public Rota(@NotBlank @Size(min = 5) String nome, @NotBlank @Size(min = 5) Ponto pontoInicial,
//            @NotBlank @Size(min = 5) Ponto pontoFinal, List<Ponto> pontos) {
//        this.nome = nome;
//        this.pontoInicial = pontoInicial;
//        this.pontoFinal = pontoFinal;
//        this.pontos = pontos;
//    }
//
//    public Rota() {
//    }
//
//    public List<Ponto> getPontos() {
//        return pontos;
//    }
//
//    public void setPontos(List<Ponto> pontos) {
//        this.pontos = pontos;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public @NotBlank @Size(min = 5) Ponto getPontoInicial() {
//        return pontoInicial;
//    }
//
//    public void setPontoInicial(@NotBlank @Size(min = 5) Ponto pontoInicial) {
//        this.pontoInicial = pontoInicial;
//    }
//
//    public @NotBlank @Size(min = 5) Ponto getPontoFinal() {
//        return pontoFinal;
//    }
//
//    public void setPontoFinal(@NotBlank @Size(min = 5) Ponto pontoFinal) {
//        this.pontoFinal = pontoFinal;
//    }
//
//    @Override
//    public String toString() {
//        return "Rota [nome=" + nome + ", pontoInicial=" + pontoInicial + ", pontoFinal=" + pontoFinal + ", pontos="
//                + pontos + "]";
//    }
//
//}