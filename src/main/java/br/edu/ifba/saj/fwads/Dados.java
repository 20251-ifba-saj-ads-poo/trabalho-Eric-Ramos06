package br.edu.ifba.saj.fwads;

import br.edu.ifba.saj.fwads.model.Itinerario;
import br.edu.ifba.saj.fwads.model.Linha;
import br.edu.ifba.saj.fwads.model.Motorista;
import br.edu.ifba.saj.fwads.model.Onibus;
import br.edu.ifba.saj.fwads.model.Ponto;
import br.edu.ifba.saj.fwads.model.Rota;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dados {
    public static ObservableList<Motorista> listaMotorista = FXCollections.observableArrayList();
    public static ObservableList<Onibus> listaOnibus = FXCollections.observableArrayList();
    public static ObservableList<Ponto> listaPonto = FXCollections.observableArrayList();
    public static ObservableList<Itinerario> listaItinerario = FXCollections.observableArrayList();
    public static ObservableList<Linha> listaLinha = FXCollections.observableArrayList();
    public static ObservableList<Rota> listaRota = FXCollections.observableArrayList();
    public static ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
}
