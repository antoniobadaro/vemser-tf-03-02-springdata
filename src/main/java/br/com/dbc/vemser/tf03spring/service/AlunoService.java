package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.Aluno;
import br.com.dbc.vemser.tf03spring.repository.AlunoRepository;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final EmailService emailService;

    public AlunoService(AlunoRepository alunoRepository, EmailService emailService) {
        this.alunoRepository = alunoRepository;
        this.emailService = emailService;
    }

    public AlunoDTO create(AlunoDTO alunoDTO) throws BancoDeDadosException, TemplateException, IOException, MessagingException {
        Aluno alunoCriado = alunoRepository.create(alunoDTO);

        if (ObjectUtils.isEmpty(alunoCriado)) {
            return null;
        }
        emailService.AlunoCriado();
        return new AlunoDTO(alunoCriado);
    }

    public List<AlunoDTO> findAll() throws BancoDeDadosException {
        List<Aluno> todosOsAlunos = alunoRepository.findAll();
        List<AlunoDTO> alunosDTOS = new ArrayList<>();

        if (ObjectUtils.isEmpty(todosOsAlunos)) {
            return null;
        }

        for (Aluno aluno : todosOsAlunos) {
            alunosDTOS.add(new AlunoDTO(aluno));
        }

        return alunosDTOS;
    }

    public AlunoDTO findById(Integer idAluno) throws BancoDeDadosException, RegraDeNegocioException {
        Aluno alunoEncontrado = alunoRepository.findById(idAluno);

        if (ObjectUtils.isEmpty(alunoEncontrado)) {
            return null;
        }

        return new AlunoDTO(alunoEncontrado);
    }

    public AlunoDTO update(Integer idAluno, AlunoDTO alunoDTO) throws BancoDeDadosException, TemplateException, MessagingException, IOException {
        Aluno alunoAtualizado = alunoRepository.update(idAluno, alunoDTO);

        if (ObjectUtils.isEmpty(alunoAtualizado)) {
            return null;
        }
        emailService.AlunoEditado();
        return new AlunoDTO(alunoAtualizado);
    }

    public void delete(Integer idAluno) throws BancoDeDadosException, RegraDeNegocioException, TemplateException, MessagingException, IOException {
        Aluno alunoDTO = alunoRepository.findById(idAluno);

        if (ObjectUtils.isEmpty(alunoDTO)) {
            throw new RegraDeNegocioException("Aluno não encontrado");
        }
        emailService.AlunoRemovido();
        alunoRepository.delete(idAluno);
    }

}