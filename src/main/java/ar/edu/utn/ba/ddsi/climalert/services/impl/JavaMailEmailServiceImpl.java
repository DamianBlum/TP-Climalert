package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class JavaMailEmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public JavaMailEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarCorreo(String destinatario, String asunto,String cuerpo) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            // El 'true' indica que va a ser un mensaje multipart (permite adjuntos o HTML)
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo, false); // 'false' significa texto plano. 'true' si mandás HTML.
            helper.setFrom("no-reply@clima.com");

            mailSender.send(message);
            System.out.println("======> [EMAIL] Correo enviado con éxito a: " + destinatario);

        } catch (Exception e) {
            System.err.println("======> [EMAIL ERROR] No se pudo enviar el correo a " + destinatario + ": " + e.getMessage());
        }
    }
}
