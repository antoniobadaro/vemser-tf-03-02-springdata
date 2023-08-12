package br.com.dbc.vemser.tf03spring.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final Configuration fmConfiguration;
    private String from = "Diogo Bruno <diogo.neves@dbccompany.com.br>";

    private void sendTemplateEmail(String descricao, String conteudo) throws MessagingException {
        MimeMessage emailTemplate = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(emailTemplate, true);

        try {

            helper.setFrom(from);
            helper.setTo("diogobruno06@outlook.com");
            helper.setSubject(descricao);
            helper.setText(conteudo, true);

            mailSender.send(helper.getMimeMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getContentFromTemplateDelete() throws IOException, TemplateException {
        Map<String, String> dados = new HashMap<>();
        Template template = fmConfiguration.getTemplate("email-template1.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        return html;
    }

    public String getContentFromTemplateCriar() throws IOException, TemplateException {
        Map<String, String> dados = new HashMap<>();
        Template template = fmConfiguration.getTemplate("email-template2.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        return html;
    }

    public String getContentFromTemplateEditar() throws IOException, TemplateException {
        Map<String, String> dados = new HashMap<>();
        Template template = fmConfiguration.getTemplate("email-template3.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        return html;
    }

    public void AlunoCriado() throws TemplateException, IOException, MessagingException {
        String descricao = "Novo aluno cadastrado";
        String conteudo = getContentFromTemplateCriar();
        sendTemplateEmail(descricao,conteudo);
    }

    public void AlunoEditado() throws TemplateException, IOException, MessagingException {
        String descricao = "Aluno editado";
        String conteudo = getContentFromTemplateEditar();
        sendTemplateEmail(descricao,conteudo);
    }

    public void AlunoRemovido() throws TemplateException, IOException, MessagingException {
        String descricao = "Aluno removido";
        String conteudo = getContentFromTemplateDelete();
        sendTemplateEmail(descricao,conteudo);
    }


}