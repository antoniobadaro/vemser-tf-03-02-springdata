package br.com.dbc.vemser.tf03spring.controller;


import br.com.dbc.vemser.tf03spring.documentation.EnderecoControllerDoc;
import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.service.EnderecoService;
import org.apache.commons.lang3.ObjectUtils;
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
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoControllerDoc {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        EnderecoDTO enderecoParaCriar = new EnderecoDTO(enderecoCreateDTO);
        EnderecoDTO enderecoCriado = enderecoService.create(enderecoParaCriar);

        if (ObjectUtils.isEmpty(enderecoCriado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() throws BancoDeDadosException {
        List<EnderecoDTO> todosOsEnderecos = enderecoService.findAll();

        if (ObjectUtils.isEmpty(todosOsEnderecos)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(todosOsEnderecos, HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("idEndereco") @Positive Integer idEndereco) throws BancoDeDadosException {
        EnderecoDTO enderecoEncontrado = enderecoService.findById(idEndereco);

        if (ObjectUtils.isEmpty(enderecoEncontrado)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity<>(enderecoEncontrado, HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") @Positive Integer idEndereco, @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        EnderecoDTO enderecoParaAtualizar = new EnderecoDTO(enderecoCreateDTO);
        EnderecoDTO enderecoAtualizado = enderecoService.update(idEndereco, enderecoParaAtualizar);

        if (ObjectUtils.isEmpty(enderecoAtualizado)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") @Positive Integer idEndereco) throws BancoDeDadosException {
        enderecoService.delete(idEndereco);

        EnderecoDTO enderecoParaDeletar = enderecoService.findById(idEndereco);

        if (ObjectUtils.isEmpty(enderecoParaDeletar)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
