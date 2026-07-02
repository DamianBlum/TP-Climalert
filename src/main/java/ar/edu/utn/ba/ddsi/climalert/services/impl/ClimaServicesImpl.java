package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.services.ClimaServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaServicesImpl implements ClimaServices {
    public RestTemplate  restTemplate;

    public ClimaServicesImpl( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void obtenerClima() {
        restTemplate.getForObject("http://localhost:8080/ClimaServices", ClimaServices.class);
    }

    @Override
    public void chequeoAlertas() {

    }
}
