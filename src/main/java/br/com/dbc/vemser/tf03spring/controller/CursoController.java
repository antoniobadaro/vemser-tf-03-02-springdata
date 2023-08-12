package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.CursoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/curso")
public class CursoController implements CursoControllerDoc {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> listar() throws BancoDeDadosException {
        return cursoService.listar();
    }

    @PostMapping
    public ResponseEntity<CursoDTO> adicionar(@Valid @RequestBody CursoCreateDTO curso) throws Exception{
        return new ResponseEntity<>(cursoService.adicionar(curso), HttpStatus.OK);
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> editar(@Valid @RequestBody CursoCreateDTO curso, @PathVariable ("idCurso") Integer idCurso) throws Exception{
        return new ResponseEntity<>(cursoService.editar(curso, idCurso), HttpStatus.OK);
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> remover (@PathVariable("idCurso") Integer idCurso) throws Exception{
        cursoService.remover(idCurso);
        return ResponseEntity.ok().build();
    }
}
