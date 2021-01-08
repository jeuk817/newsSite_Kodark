package com.kodark.news.service.impl;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kodark.news.dto.Mail;
import com.kodark.news.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendMail(Mail mail) {

		final MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				helper.setFrom(new InternetAddress(mail.getMailFrom()));
				helper.setTo(new InternetAddress(mail.getMailTo()));
				helper.setSubject(mail.getMailSubject());
				helper.setText(mail.getMailContent(), true);
			}
		};

		mailSender.send(preparator);
	}

}
