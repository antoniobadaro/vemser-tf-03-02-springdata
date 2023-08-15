package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.CursoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
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
    public ResponseEntity<List<CursoDTO>> findAll() {
        List<CursoDTO> cursosEncontrados = cursoService.findAll();

        return new ResponseEntity<>(cursosEncontrados, HttpStatus.OK);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> findById(@PathVariable ("idCurso") Integer idCurso) throws BancoDeDadosException, RegraDeNegocioException {
        CursoDTO cursoEncontrado = cursoService.findById(idCurso);

        return new ResponseEntity<>(cursoEncontrado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoCreateDTO curso) throws Exception{
        return new ResponseEntity<>(cursoService.create(curso), HttpStatus.OK);
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoCreateDTO curso, @PathVariable ("idCurso") Integer idCurso) throws Exception{
        return new ResponseEntity<>(cursoService.update(curso, idCurso), HttpStatus.OK);
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> delete(@PathVariable("idCurso") Integer idCurso) throws Exception{
        cursoService.delete(idCurso);
        return ResponseEntity.ok().build();
    }

}
