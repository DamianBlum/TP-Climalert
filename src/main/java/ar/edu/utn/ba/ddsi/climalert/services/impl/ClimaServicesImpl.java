package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.dto.TiempoApiResponse;
import ar.edu.utn.ba.ddsi.climalert.models.entities.Clima;
import ar.edu.utn.ba.ddsi.climalert.alerts.DisparadorAlerta;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClimaServicesImpl implements ClimaServices {
    private final RestTemplate  restTemplate;
    private final ClimaRepository climaRepository;
    private final List<DisparadorAlerta> alertas; // Spring inyecta todos los comandos automáticamente
    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.location:CABA}")
    private String localidad;

    @Value("${weatherapi.base-url:http://api.weatherapi.com/v1}")
    private String baseUrl;

    public ClimaServicesImpl( RestTemplate restTemplate, ClimaRepository climaRepository,  List<DisparadorAlerta> alertas) {
        this.restTemplate = restTemplate;
        this.climaRepository = climaRepository;
        this.alertas = alertas;
    }


    @Override
    public void obtenerClima() {
        System.out.println("======> [SERVICIO] Entré a obtenerClima(). Evaluando llamada externa...");
        String url = String.format("%s/current.json?key=%s&q=%s", baseUrl, apiKey, localidad);

        try {
            // Hacemos el GET mapeando la respuesta a nuestro DTO
            TiempoApiResponse response = restTemplate.getForObject(url, TiempoApiResponse.class);

            if (response != null && response.getCurrent() != null) {
                double temperaturaActual = response.getCurrent().getTempC();
                double humedadActual = response.getCurrent().getHumidity();

                Clima climaActual = new Clima(temperaturaActual, humedadActual);

                System.out.println("Clima obtenido - Temperatura: " + climaActual.getTemperatura() + "°C, Humedad: " + climaActual.getHumedad() + "%");


                climaRepository.agregarClima(climaActual);
            } else {
                System.err.println("======> [ALERTA] La API respondió vacío o con estructura inesperada.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el clima: " + e.getMessage());
        }

    }

    @Override
    public void chequeoAlertas() {
        climaRepository.conseguirUltimoClima().ifPresent(climaActual -> {
            // Esto solo se ejecuta si el Optional tiene un clima adentro


            for (DisparadorAlerta alerta : alertas) {

                alerta.ejecutar(climaActual);
            }
        });
    }
}
