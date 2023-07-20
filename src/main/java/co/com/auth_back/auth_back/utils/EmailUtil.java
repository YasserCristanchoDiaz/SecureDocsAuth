package co.com.auth_back.auth_back.utils;

import co.com.auth_back.auth_back.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class EmailUtil {
    private static String authEmail;
    private static String authPassword;
    private static Properties mProperties;
    private static MimeMessage mMail;

    @Autowired
    private void setCredential(Environment env){
        authEmail = env.getProperty("auth.email");
        authPassword = env.getProperty("auth.password");
    }

    public static void initializeEmailUtil() {
        mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", authEmail);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
    }

    public static void sendEmail() {
        Thread emailThread = new Thread(() -> {
            try {
                Session mSession = Session.getDefaultInstance(mProperties);

                Transport mTransport = mSession.getTransport("smtp");
                mTransport.connect(authEmail, authPassword);
                mMail = new MimeMessage(mSession);
                mMail.setFrom(new InternetAddress(authEmail));

                //mMail.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailTo()));
                //mMail.setSubject(email.getSubject());
                //mMail.setText(email.getContent(), "ISO-8859-1", "html");
                mTransport.sendMessage(mMail, mMail.getRecipients(Message.RecipientType.TO));
                mTransport.close();
            } catch (MessagingException ex) {
                System.out.println(ex);
            }
        });
    }

    /**
     * OBtiene el template a enviar en el correo de registro
     * @param token el token que se necesita en la url
     * @param code el codigo a mostrar al usuario
     * @return un string con el codigo html para enviar al correo
     */
    public static String getUserRegisterTemplate(String token, String code) {
        return "<h3>Completa tu registro en el siguiente <a href=\"http://localhost:3000/pages/auth/passwordChange?token="+token+"\"> enlace</a></h3> <h3>Tu código es: " + code + "</h3>";
    }

    /**
     * OBtiene el template a enviar en el correo de recuperacion
     * @param token el token que se necesita en la url
     * @param code el codigo a mostrar al usuario
     * @return un string con el codigo html para enviar al correo
     */
    public static String getUserRecoveryTemplate(String token, String code) {
        return "<h3>Recupera tu contraseña en el siguiente <a href=\"http://localhost:3000/pages/auth/passwordChange?token="+token+"\"> enlace</a></h3> <h3>Tu código es: " + code + "</h3>";
    }

    /**
     * Envia el correo con el codigo al usuario
     *
     * @param user los datos del usuario
     * @param mail el correo al que se va a enviar el mensaje
     * @param code el codigo para ingresar
     * @return el tokentemporal generado
     */
    public static String sendPasswordChangeMail(User user, String mail, String code) {
        Map<String, String> tokenData = new HashMap<>();
        tokenData.put("id", user.getId());

        String temporalToken = TokenUtil.generateToken(tokenData);

        //Email email = new Email(mail, "Completa tu registro", EmailUtil.getUserRegisterTemplate(temporalToken, code));
        //EmailUtil.sendEmail(email);

        return temporalToken;
    }

    /**
     *
     * @param user los datos del usuario
     * @param mail el correo al que se va a enviar el mensaje
     * @param code el codigo para ingresar
     * @param recovery indica si es un correo de recuperacion
     * @return el tokentemporal generado
     */
    public static String sendPasswordChangeMail(User user, String mail, String code, boolean recovery) {
        Map<String, String> tokenData = new HashMap<>();
        tokenData.put("id", user.getId());

        String temporalToken = TokenUtil.generateToken(tokenData);

        //Email email = new Email(mail, "Recupera tu contraseña: ", EmailUtil.getUserRecoveryTemplate(temporalToken, code));
        //EmailUtil.sendEmail(email);

        return temporalToken;
    }
}
