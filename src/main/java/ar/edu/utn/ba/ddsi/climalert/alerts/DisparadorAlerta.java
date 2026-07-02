package ar.edu.utn.ba.ddsi.climalert.alerts;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;

public interface DisparadorAlerta {
    void ejecutar(Clima clima);
}
