package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Itinerario;

import java.util.List;

public class ItinerarioService extends Service<Itinerario> {

    public ItinerarioService() {
        super(Itinerario.class);
    }

    public void salvarComValidacao(Itinerario itinerario)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        validar(itinerario);
        create(itinerario);
    }

    private void validar(Itinerario itinerario)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        String nome = itinerario.getNome();
        String hora = itinerario.getHoraPartida();

        if (nome == null || nome.trim().isEmpty()) {
            throw new CampoObrigatorioException("O nome do itinerário é obrigatório.");
        }

        if (nome.trim().length() < 3) {
            throw new CampoObrigatorioException("O nome do itinerário deve ter pelo menos 3 caracteres.");
        }

        if (hora == null || hora.trim().isEmpty()) {
            throw new CampoObrigatorioException("A hora de partida é obrigatória.");
        }

        String padraoHora = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        if (!hora.matches(padraoHora)) {
            throw new FormatoInvalidoException("A hora de partida deve estar no formato HH:mm (ex: 08:30 ou 23:45).");
        }

        if (itinerario.getRota() == null) {
            throw new CampoObrigatorioException("A rota do itinerário é obrigatória.");
        }

        List<Itinerario> itinerarios = findAll();
        boolean duplicado = itinerarios.stream()
            .anyMatch(i -> i.getNome().equalsIgnoreCase(nome.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe um itinerário com esse nome cadastrado.");
        }
    }
}
