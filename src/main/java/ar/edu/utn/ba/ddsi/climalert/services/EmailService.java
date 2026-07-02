package ar.edu.utn.ba.ddsi.climalert.services;

public interface EmailService {
    void enviarCorreo(String correo, String mensaje, String detalle);
}
