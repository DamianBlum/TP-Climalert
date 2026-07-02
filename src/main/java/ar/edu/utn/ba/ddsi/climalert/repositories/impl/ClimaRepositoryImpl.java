package ar.edu.utn.ba.ddsi.climalert.repositories.impl;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClimaRepositoryImpl implements ClimaRepository {

    private List<Clima> climas = new ArrayList<>();

    @Override
    public Optional<Clima> conseguirUltimoClima() {
        if (climas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(climas.getLast());
    }

    @Override
    public void agregarClima(Clima clima) {
        climas.add(clima);
    }
}
