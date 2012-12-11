/**
 * 
 */
package com.sivalabs.core.email;

import java.util.List;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sivalabs.core.logging.Slf4JLogger;

public class EmailService 
{
	@Slf4JLogger
	private Logger logger;
	
	private JavaMailSenderImpl mailSender = null;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(Email email) 
	{
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			// use the true flag to indicate you need a multipart message
			boolean hasAttachments = (email.getAttachments() != null && email
					.getAttachments().size() > 0);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					hasAttachments);
			helper.setTo(email.getTo());
			helper.setFrom(email.getFrom());
			helper.setSubject(email.getSubject());
			helper.setText(email.getText(), true);

			List<Attachment> attachments = email.getAttachments();
			if (attachments != null && attachments.size() > 0) 
			{
				for (Attachment attachment : attachments) 
				{
					String filename = attachment.getFilename();
					DataSource dataSource = new ByteArrayDataSource(attachment.getData(), attachment.getMimeType());
					if (attachment.isInline()) 
					{
						helper.addInline(filename, dataSource);
					} else 
					{
						helper.addAttachment(filename, dataSource);
					}
				}
			}
			mailSender.send(mimeMessage);
		} catch (MailException e) {
			logger.error("Error while sending Email.",e);
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			logger.error("Error while sending Email.",e);
			throw new RuntimeException(e);
		}
	}
}
