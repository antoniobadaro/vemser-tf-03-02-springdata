package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.AlunoEntity;
import br.com.dbc.vemser.tf03spring.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
@Data
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;
    private static String MENSAGEM_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";
    private static String ALUNO_CRIADO_TEMPLATE = "";
    private static String ALUNO_ATUALIZADO_TEMPLATE = "";
    private static String ALUNO_DELETADO_TEMPLATE = "";


    public AlunoDTO create(AlunoDTO alunoDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        AlunoEntity alunoEntityParaPersistir = converterAlunoDtoParaAluno(alunoDTO);
        AlunoEntity alunoEntityPersistido = alunoRepository.save(alunoEntityParaPersistir);

        if (alunoEntityPersistido == null) {
            throw new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO);
        }

        Map<String, String> dados = new HashMap<>();
        dados.put("nome", alunoDTO.getNome());
        String text = "Bem vindo a DBCURSOS TECH!<br>" +
                "           Seu cadastro foi realizado com sucesso.<br>";
        dados.put("text", text);
        dados.put("email", alunoDTO.getEmail());
        emailService.sendTemplateEmail(dados);

        return converterAlunoParaAlunoDto(alunoEntityPersistido);
    }

    public List<AlunoDTO> findAll() throws RegraDeNegocioException {
        List<AlunoEntity> alunosEncontrados = alunoRepository.findAll();
        List<AlunoDTO> dtos = new ArrayList<>();

        if (alunosEncontrados.isEmpty()) {
            throw new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO);
        }

        for (AlunoEntity alunoEntity : alunosEncontrados) {
            dtos.add(converterAlunoParaAlunoDto(alunoEntity));
        }

        return dtos;
    }

    public Page<AlunoDTO> findAll(Pageable pageable) {
        Page<AlunoEntity> alunoEntityPage = alunoRepository.findAll(pageable);
        List<AlunoDTO> alunosEncontrados = alunoRepository
                .findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();

        return new PageImpl<>(alunosEncontrados, pageable, alunoEntityPage.getTotalElements());
    }

    public AlunoDTO findById(Integer idAluno) throws RegraDeNegocioException {
        AlunoEntity alunoEntityEncontrado = alunoRepository
                .findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        return converterAlunoParaAlunoDto(alunoEntityEncontrado);
    }

    public AlunoDTO update(Integer idAluno, AlunoDTO alunoDTO) throws RegraDeNegocioException {
        AlunoEntity alunoEntityParaAtualizar = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        alunoEntityParaAtualizar.setNome(alunoDTO.getNome());
        alunoEntityParaAtualizar.setIdade(alunoDTO.getIdade());
        alunoEntityParaAtualizar.setCpf(alunoDTO.getCpf());
        alunoEntityParaAtualizar.setEmail(alunoDTO.getEmail());
        alunoEntityParaAtualizar.setNumeroDeMatricula(alunoDTO.getNumeroDeMatricula());

        AlunoEntity alunoEntityAtualizado = alunoRepository.save(alunoEntityParaAtualizar);
        return converterAlunoParaAlunoDto(alunoEntityAtualizado);
    }

    public void delete(Integer idAluno) throws RegraDeNegocioException {
        alunoRepository
                .findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        alunoRepository.deleteById(idAluno);
    }

    private AlunoEntity converterAlunoDtoParaAluno(AlunoDTO alunoDTO) {
        return objectMapper.convertValue(alunoDTO, AlunoEntity.class);
    }

    private AlunoEntity converterAlunoCreateDtoParaAluno(AlunoCreateDTO alunoCreateDTO) {
        return objectMapper.convertValue(alunoCreateDTO, AlunoEntity.class);
    }

    private AlunoDTO converterAlunoParaAlunoDto(AlunoEntity alunoEntity) {
        return objectMapper.convertValue(alunoEntity, AlunoDTO.class);
    }

    private AlunoDTO converterAlunoCreateDtoParaAlunoDto(AlunoCreateDTO alunoCreateDTO) {
        return objectMapper.convertValue(alunoCreateDTO, AlunoDTO.class);
    }

    private AlunoCreateDTO converterAlunoParaAlunoCreateDto(AlunoEntity alunoEntity) {
        return objectMapper.convertValue(alunoEntity, AlunoCreateDTO.class);
    }

    private AlunoCreateDTO converterAlunoDtoParaAlunoCreateDto(AlunoDTO alunoDTO) {
        return objectMapper.convertValue(alunoDTO, AlunoCreateDTO.class);
    }

    private AlunoDTO convertToDTO(AlunoEntity alunoEntity) {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setIdAluno(alunoEntity.getIdAluno());
        alunoDTO.setNome(alunoEntity.getNome());
        alunoDTO.setIdade(alunoEntity.getIdade());
        alunoDTO.setCpf(alunoEntity.getCpf());
        alunoDTO.setEmail(alunoEntity.getEmail());
        alunoDTO.setNumeroDeMatricula(alunoEntity.getNumeroDeMatricula());
        return alunoDTO;
    }

}
