package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.CursoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.CursoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CursoControllerDoc {


    @Operation(summary = "Lista todos os cursos", description = "Lista todos os cursos do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os alunos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    List<CursoDTO> listar() throws BancoDeDadosException;


    @Operation(summary = "Cria um curso", description = "Cria um curso a partir de um body JSON ")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso criado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<CursoDTO> adicionar(@Valid @RequestBody CursoCreateDTO curso) throws Exception;


    @Operation(summary = "Atualiza um curso", description = "Atualiza um curso a partir do idCurso")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idCurso}")
    ResponseEntity<CursoDTO> editar(@Valid @RequestBody CursoCreateDTO curso, @PathVariable("idCurso") Integer idCurso) throws Exception;


    @Operation(summary = "Exclui um curso", description = "Exclui um curso do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Curso excluído com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idCurso}")
    ResponseEntity<Void> remover (@PathVariable("idCurso") Integer idCurso) throws Exception;
}
