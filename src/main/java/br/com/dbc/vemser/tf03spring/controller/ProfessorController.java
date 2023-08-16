package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.documentation.ProfessorControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/professor")
public class ProfessorController implements ProfessorControllerDoc {

    private final ProfessorService professorService;
    private final ObjectMapper objectMapper;
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        log.info("Professor: listar todos");
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/professores-paginados")
    public Page<ProfessorDTO> findAllPaginados(Integer numeroDePaginas, Integer quantidadeDeRegistros) {
        Sort ordenacao = Sort.by("nome");

        Pageable pageable = PageRequest.of(numeroDePaginas, quantidadeDeRegistros, ordenacao);

        return professorService.findAll(pageable);
    }

    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable ("idProfessor") @Positive Integer idProfessor) throws RegraDeNegocioException {
        log.info("Professor: listar por Id do professor");
        return new ResponseEntity<>(professorService.findById(idProfessor), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody @Valid ProfessorCreateDTO professorCreateDTO) {
        log.info("Professor: inserir novo");
        return new ResponseEntity<>(professorService.create(professorCreateDTO), HttpStatus.OK);
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable("idProfessor") @Positive Integer idProfessor, @RequestBody @Valid ProfessorCreateDTO professorDTO) throws RegraDeNegocioException {
        log.info("Professor: editar");
        ProfessorDTO professorAtualizar = professorService.update(idProfessor, professorDTO);
        return new ResponseEntity<>(professorAtualizar, HttpStatus.OK);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") @Positive Integer idProfessor) throws RegraDeNegocioException {
        log.info("Professor: deletar por id");
        professorService.delete(idProfessor);
        return ResponseEntity.ok().build();
    }
}
