package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.AlunoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.service.AlunoService;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

@RestController
@Validated
@ControllerAdvice
@RequestMapping("/aluno")
public class AlunoController implements AlunoControllerDoc {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@RequestBody @Valid AlunoCreateDTO alunoCreateDTO) throws BancoDeDadosException, TemplateException, MessagingException, IOException {
        AlunoDTO alunoParaCriar = new AlunoDTO(alunoCreateDTO);
        AlunoDTO alunoCriado = alunoService.create(alunoParaCriar);

        if (ObjectUtils.isEmpty(alunoCriado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(alunoCriado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() throws BancoDeDadosException {
        List<AlunoDTO> todosOsAlunos = alunoService.findAll();

        if (ObjectUtils.isEmpty(todosOsAlunos.get(0))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(todosOsAlunos, HttpStatus.OK);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable("idAluno") @Positive Integer idAluno) throws BancoDeDadosException, RegraDeNegocioException {
        AlunoDTO alunoEncontrado = alunoService.findById(idAluno);

        if (ObjectUtils.isEmpty(alunoEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(alunoEncontrado, HttpStatus.OK);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> update(@PathVariable("idAluno") @Positive Integer idAluno, @RequestBody @Valid AlunoCreateDTO alunoCreateDTO) throws BancoDeDadosException, RegraDeNegocioException, TemplateException, MessagingException, IOException {
        AlunoDTO alunoParaAtualizar = alunoService.findById(idAluno);

        if (ObjectUtils.isEmpty(alunoParaAtualizar)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        alunoParaAtualizar = new AlunoDTO(alunoCreateDTO);
        AlunoDTO alunoAtualizado = alunoService.update(idAluno, alunoParaAtualizar);

        if (ObjectUtils.isEmpty(alunoAtualizado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idAluno}")
    public ResponseEntity<Void> delete(@PathVariable("idAluno") @Positive Integer idAluno) throws BancoDeDadosException, RegraDeNegocioException, TemplateException, MessagingException, IOException {
        AlunoDTO alunoParaDeletar = alunoService.findById(idAluno);

        if (ObjectUtils.isEmpty(alunoParaDeletar)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        alunoService.delete(idAluno);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
