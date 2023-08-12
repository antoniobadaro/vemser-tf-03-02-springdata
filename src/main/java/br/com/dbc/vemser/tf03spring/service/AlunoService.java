package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.Aluno;
import br.com.dbc.vemser.tf03spring.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    private static String MENSAGEM_ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";
    private static String ALUNO_CRIADO_TEMPLATE = "";
    private static String ALUNO_ATUALIZADO_TEMPLATE = "";
    private static String ALUNO_DELETADO_TEMPLATE = "";

    public AlunoDTO create(AlunoDTO alunoDTO) throws RegraDeNegocioException {
        Aluno alunoParaPersistir = converterAlunoDtoParaAluno(alunoDTO);
        Aluno alunoPersistido = alunoRepository.save(alunoParaPersistir);

        if (alunoPersistido == null) {
            throw new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO);
        }

        return converterAlunoParaAlunoDto(alunoPersistido);
    }

    public List<AlunoDTO> findAll() throws RegraDeNegocioException {
        List<Aluno> alunosEncontrados = alunoRepository.findAll();
        List<AlunoDTO> dtos = new ArrayList<>();

        if (alunosEncontrados.isEmpty()) {
            throw new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO);
        }

        for (Aluno aluno : alunosEncontrados) {
            dtos.add(converterAlunoParaAlunoDto(aluno));
        }

        return dtos;
    }

    public AlunoDTO findById(Integer idAluno) throws RegraDeNegocioException {
        Aluno alunoEncontrado = alunoRepository
                .findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        return converterAlunoParaAlunoDto(alunoEncontrado);
    }

    public AlunoDTO update(Integer idAluno, AlunoDTO alunoDTO) throws RegraDeNegocioException {
        Aluno alunoParaAtualizar = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        alunoParaAtualizar.setNome(alunoDTO.getNome());
        alunoParaAtualizar.setIdade(alunoDTO.getIdade());
        alunoParaAtualizar.setCpf(alunoDTO.getCpf());
        alunoParaAtualizar.setNumeroDeMatricula(alunoDTO.getNumeroDeMatricula());

        Aluno alunoAtualizado = alunoRepository.save(alunoParaAtualizar);
        return converterAlunoParaAlunoDto(alunoAtualizado);
    }

    public void delete(Integer idAluno) throws RegraDeNegocioException {
        alunoRepository
                .findById(idAluno)
                .orElseThrow(() -> new RegraDeNegocioException(MENSAGEM_ALUNO_NAO_ENCONTRADO));

        alunoRepository.deleteById(idAluno);
    }

    private Aluno converterAlunoDtoParaAluno(AlunoDTO alunoDTO) {
        return objectMapper.convertValue(alunoDTO, Aluno.class);
    }

    private Aluno converterAlunoCreateDtoParaAluno(AlunoCreateDTO alunoCreateDTO) {
        return objectMapper.convertValue(alunoCreateDTO, Aluno.class);
    }

    private AlunoDTO converterAlunoParaAlunoDto(Aluno aluno) {
        return objectMapper.convertValue(aluno, AlunoDTO.class);
    }

    private AlunoDTO converterAlunoCreateDtoParaAlunoDto(AlunoCreateDTO alunoCreateDTO) {
        return objectMapper.convertValue(alunoCreateDTO, AlunoDTO.class);
    }

    private AlunoCreateDTO converterAlunoParaAlunoCreateDto(Aluno aluno) {
        return objectMapper.convertValue(aluno, AlunoCreateDTO.class);
    }

    private AlunoCreateDTO converterAlunoDtoParaAlunoCreateDto(AlunoDTO alunoDTO) {
        return objectMapper.convertValue(alunoDTO, AlunoCreateDTO.class);
    }

}
