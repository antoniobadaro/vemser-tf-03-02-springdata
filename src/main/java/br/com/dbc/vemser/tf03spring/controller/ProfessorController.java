package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.documentation.ProfessorControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@ControllerAdvice
@RequestMapping("/professor")
public class ProfessorController implements ProfessorControllerDoc {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<ProfessorDTO> todosOsProfessor = professorService.findAll();
        return new ResponseEntity<>(todosOsProfessor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody @Valid ProfessorCreateDTO professorCreateDTO) {
        ProfessorDTO professorParaCriar = new ProfessorDTO(professorCreateDTO);
        ProfessorDTO professorCriado = professorService.create(professorParaCriar);
        return new ResponseEntity<>(professorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable("idProfessor") @Positive Integer idProfessor, @RequestBody @Valid ProfessorCreateDTO professorCreateDTO) {
        ProfessorDTO professorParaAtualizar = new ProfessorDTO(professorCreateDTO);
        ProfessorDTO professorAtualizado = professorService.update(idProfessor, professorParaAtualizar);
        return new ResponseEntity<>(professorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") @Positive Integer idProfessor) {
        professorService.delete(idProfessor);
        return ResponseEntity.ok().build();
    }
}
