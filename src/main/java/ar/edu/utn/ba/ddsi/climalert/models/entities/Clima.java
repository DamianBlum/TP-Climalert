package ar.edu.utn.ba.ddsi.climalert.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Clima {
    private double temperatura;
    private double humedad;
    private LocalDateTime hora;

    public Clima(double temperatura, double humedad) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.hora = LocalDateTime.now();
    }
}
