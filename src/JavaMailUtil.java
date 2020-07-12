import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMailUtil {

	public static void sendMail(String recepient) throws Exception {
		System.out.println("Preparing to send email");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myAccountEmail = "tomekcm4@gmail.com";
		String password = "x";

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(myAccountEmail, password);
			}
		});

		Message message = prepareMessage(session, myAccountEmail, recepient);

		Transport.send(message);
		System.out.println("Message sent succesfully");

	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
			message.setSubject("my First Email from Java APP");
			message.setText("Hey there, look a email");
			return message;
		} catch (Exception ex) {

			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
