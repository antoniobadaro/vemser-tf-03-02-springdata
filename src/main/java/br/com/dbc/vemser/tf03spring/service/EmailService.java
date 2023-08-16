package br.com.dbc.vemser.tf03spring.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final Configuration fmConfiguration;
    private String from = "Letícia Santos <leticia.goncalves@dbccompany.com.br>";
    @Value("${spring.mail.username}")
    private String username;

        public void sendTemplateEmail(Map<String, String> dados) throws MessagingException, TemplateException, IOException {
            MimeMessage emailTemplate = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(emailTemplate, true);

            helper.setFrom(from);
            helper.setTo(dados.get("email"));
            helper.setSubject("email a partir de template");
            helper.setText(getContentFromTemplate(dados), true);

            mailSender.send(helper.getMimeMessage());

        }

    public String getContentFromTemplate(Map<String, String> dados) throws IOException, TemplateException {
        dados.put("suporte", username);
        Template template = fmConfiguration.getTemplate("email-template1.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        return html;
    }
}
