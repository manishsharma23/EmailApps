package com.emailer;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class MainA
{
    public static void main(String[] args) throws Exception {
        String[] to = { "sharmam8250@gmail.com" };
        String[] cc = { "sharmam8250@gmail.com" };
        String[] bcc ={ "sharmam8250@gmail.com" };
        // This is for google
        MainA.sendMail("sharmam8250@gmail.com", "*********", "smtp.gmail.com", "465", "true", "true",
                true, "javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc,
                "hi baba don’t send virus mails..",
                "This is my style…of reply..If u send virus mails..");
    }
 
    public synchronized static boolean sendMail(String userName,
            String passWord, String host, String port, String starttls,
            String auth, boolean debug, String socketFactoryClass,
            String fallback, String[] to, String[] cc, String[] bcc,
            String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);
        props.put("port", port);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.auth", auth);
        if (debug) {
            props.put("mail.smtp.debug", "true");
        } else {
            props.put("mail.smtp.debug", "false");
        }
        try {
            Session smtpSession = Session.getDefaultInstance(props, null);
            smtpSession.setDebug(true);
            MimeMessage msg = new MimeMessage(smtpSession);
            msg.setText(text);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress("sharmam8250@gmail.com"));
            for (int i = 0; i < to.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                        to[i]));
            }
            for (int i = 0; i < cc.length; i++) {
                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
                        cc[i]));
            }
            for (int i = 0; i < bcc.length; i++) {
                msg.addRecipient(Message.RecipientType.BCC,
                        new InternetAddress(bcc[i]));
            }
            msg.saveChanges();
            Transport transport = smtpSession.getTransport("smtp");
            transport.connect(host, userName, passWord);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            System.out.println(mex.getLocalizedMessage());
            return false;
        }
    }
}