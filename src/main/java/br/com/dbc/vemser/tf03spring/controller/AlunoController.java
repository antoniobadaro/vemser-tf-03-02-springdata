package br.com.dbc.vemser.tf03spring.controller;

import br.com.dbc.vemser.tf03spring.documentation.AlunoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController implements AlunoControllerDoc {

    private final AlunoService alunoService;
    private final ObjectMapper objectMapper;

    @PostMapping("/{idCurso}")
    public ResponseEntity<AlunoDTO> create(@RequestBody @Valid AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        AlunoDTO alunoParaPersistir = objectMapper.convertValue(alunoCreateDTO, AlunoDTO.class);
        AlunoDTO alunoPersistido = alunoService.create(alunoParaPersistir);

        if (alunoPersistido == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alunoPersistido, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() throws RegraDeNegocioException {
        List<AlunoDTO> alunosEncontrados = alunoService.findAll();

        if (alunosEncontrados.get(0) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alunosEncontrados, HttpStatus.OK);
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable("idAluno") @Positive Integer idAluno) throws RegraDeNegocioException {
        AlunoDTO alunoEncontrado = alunoService.findById(idAluno);

        if (alunoEncontrado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alunoEncontrado, HttpStatus.OK);
    }

    @PutMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> update(@PathVariable("idAluno") @Positive Integer idAluno,
            @RequestBody @Valid AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException {
        AlunoDTO alunoParaAtualizar = objectMapper.convertValue(alunoCreateDTO, AlunoDTO.class);
        AlunoDTO alunoAtualizado = alunoService.update(idAluno, alunoParaAtualizar);

        if (alunoAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idAluno}")
    public ResponseEntity<Void> delete(@PathVariable("idAluno") @Positive(message = "O ID deve ser um número inteiro positivo") Integer idAluno) throws RegraDeNegocioException {
        alunoService.delete(idAluno);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
