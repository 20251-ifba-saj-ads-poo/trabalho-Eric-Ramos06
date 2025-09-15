package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.CampoObrigatorioException;
import br.edu.ifba.saj.fwads.exception.FormatoInvalidoException;
import br.edu.ifba.saj.fwads.exception.EvitarDuplicidadeException;
import br.edu.ifba.saj.fwads.model.Onibus;

import java.util.List;

public class OnibusService extends Service<Onibus> {

    public OnibusService() {
        super(Onibus.class);
    }

    public void salvarComValidacao(Onibus onibus)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        validar(onibus);
        create(onibus);
    }

    private void validar(Onibus onibus)
        throws CampoObrigatorioException, FormatoInvalidoException, EvitarDuplicidadeException {

        String placa = onibus.getPlaca();

        if (placa == null || placa.trim().isEmpty()) {
            throw new CampoObrigatorioException("A placa do ônibus é obrigatória.");
        }

        String padraoPlaca = "^[A-Z]{3}\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$";
        if (!placa.trim().toUpperCase().matches(padraoPlaca)) {
            throw new FormatoInvalidoException("A placa deve seguir o padrão brasileiro (ex: ABC1234 ou ABC1D23).");
        }

        List<Onibus> onibusExistentes = findAll();
        boolean duplicado = onibusExistentes.stream()
            .anyMatch(o -> o.getPlaca().equalsIgnoreCase(placa.trim()));

        if (duplicado) {
            throw new EvitarDuplicidadeException("Já existe um ônibus com essa placa cadastrada.");
        }
    }
}
