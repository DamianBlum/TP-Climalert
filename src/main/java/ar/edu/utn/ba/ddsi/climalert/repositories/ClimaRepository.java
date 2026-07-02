package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;

import java.util.Optional;

public interface ClimaRepository {
    Optional<Clima> conseguirUltimoClima();
    void agregarClima(Clima clima);
}
