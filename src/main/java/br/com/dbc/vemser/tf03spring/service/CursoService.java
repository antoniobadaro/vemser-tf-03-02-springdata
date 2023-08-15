package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.CursoEntity;
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
    private final ObjectMapper objectMapper;




    public List<CursoDTO> findAll() throws BancoDeDadosException {
        return cursoRepository.findAll().stream()
                .map(this::returnDTO)
                .collect(Collectors.toList());
    }


    public CursoDTO create(CursoCreateDTO curso) throws Exception{
        CursoEntity cursoEntity = returnEntity(curso);
        return returnDTO(cursoRepository.save(cursoEntity));
    }

    public CursoDTO update(CursoCreateDTO curso, Integer idCurso) throws Exception {

        CursoEntity cursoAtualizado = cursoRepository.findById(idCurso).get();

        cursoAtualizado.setNome(curso.getNome());
        cursoAtualizado.setPeriodo(curso.getPeriodo());
        cursoAtualizado.setCargaHoraria(curso.getCargaHoraria());
        cursoRepository.save(cursoAtualizado);
        return  returnDTO(cursoAtualizado);
    }


    public void delete(Integer idCurso) throws Exception{
        findById(idCurso);
        cursoRepository.deleteById(idCurso);
    }



    public CursoDTO findById(Integer idCurso) throws RegraDeNegocioException {
        CursoEntity cursoEntity = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RegraDeNegocioException("Curso não encontrado"));
        return returnDTO(cursoEntity);
    }

    public CursoEntity returnEntity(CursoCreateDTO curso){
        return objectMapper.convertValue(curso, CursoEntity.class);
    }

    public CursoDTO returnDTO(CursoEntity curso){
        return objectMapper.convertValue(curso, CursoDTO.class);
    }

}
