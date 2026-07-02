package ar.edu.utn.ba.ddsi.climalert.alerts;

import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.services.EmailService;
import org.springframework.stereotype.Component;

@Component
public class AlertaTemperaturaHumedadCommand implements DisparadorAlerta {
    private final EmailService emailService;

    public AlertaTemperaturaHumedadCommand(EmailService emailService) {this.emailService = emailService;}

    @Override
    public void ejecutar(Clima clima) {
        // Regla específica de la primera iteración
        if (clima.getTemperatura() > 35 && clima.getHumedad() > 60) {
            String detalle = "¡Alerta Meteorológica! Temp: " + clima.getTemperatura() + "°C, Humedad: " + clima.getHumedad() + "%";

            emailService.enviarCorreo("admin@clima.com", "Alerta Crítica", detalle);
            emailService.enviarCorreo("emergencias@clima.com", "Alerta Crítica", detalle);
            emailService.enviarCorreo("meteorologia@clima.com", "Alerta Crítica", detalle);
        }
    }
}
