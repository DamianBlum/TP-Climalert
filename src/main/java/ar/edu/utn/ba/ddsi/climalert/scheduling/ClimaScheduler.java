package ar.edu.utn.ba.ddsi.climalert.scheduling;

import ar.edu.utn.ba.ddsi.climalert.services.ClimaServices;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {
    private final ClimaServices climaServices;

    // Inyeccion de dependencia
    public  ClimaScheduler(ClimaServices climaServices) {
        this.climaServices = climaServices;

    }
    // Cada 5 minutos
    @Scheduled(cron = "0 0/1 * * * *")
    public void obtenerClima() {
        climaServices.obtenerClima();
    }

    // Cada 1 minuto chequeoAlertas
    @Scheduled(cron = "0 * * * * *")
    public void chequeoAlertas() {
        climaServices.chequeoAlertas();
    }
}
