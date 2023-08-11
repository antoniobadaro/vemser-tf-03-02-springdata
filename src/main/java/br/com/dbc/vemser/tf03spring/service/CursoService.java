package br.com.dbc.vemser.tf03spring.service;
import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.Curso;
import br.com.dbc.vemser.tf03spring.repository.CursoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class CursoService{

    private final CursoRepository cursoRepository;
    private final ProfessorService professorService;
    private final ObjectMapper objectMapper;



    public List<CursoDTO> listar() throws BancoDeDadosException {
        List<Curso> listaCurso = cursoRepository.listar();

        return listaCurso.stream()
                .map(curso -> objectMapper.convertValue(curso, CursoDTO.class))
                .collect(Collectors.toList());

    }


    public CursoDTO adicionar(CursoCreateDTO curso) throws Exception{
        Curso curso1 = objectMapper.convertValue(curso, Curso.class);
        return objectMapper.convertValue(cursoRepository.adicionar(curso1), CursoDTO.class);

    }

    public CursoDTO editar(CursoCreateDTO curso, Integer idCurso) throws Exception {

        getCurso(idCurso);
        Curso curso1 = objectMapper.convertValue(curso, Curso.class);
        //curso1. setIdCurso(cursoRecuperado.getIdCurso());
        return objectMapper.convertValue(cursoRepository.editar(curso1, idCurso), CursoDTO.class);
    }


    public void remover(Integer idCurso) throws Exception{
        getCurso(idCurso);
        cursoRepository.remover(idCurso);
    }


    private Curso getCurso(Integer idCurso) throws Exception{
        Curso pessoaRecuperada = cursoRepository.listar().stream()
                .filter(curso -> curso.getIdCurso().equals(idCurso))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Curso não encontrado!"));
        return pessoaRecuperada;
    }

}
