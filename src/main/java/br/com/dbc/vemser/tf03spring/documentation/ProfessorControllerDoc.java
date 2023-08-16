package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.ProfessorCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface ProfessorControllerDoc {
    @Operation(summary = "Lista todos os professores", description = "Lista todos os professores do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os professores"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll();

    @Operation(summary = "Procura Professor", description = "Procura professor pelo id no banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Procura professor pelo id"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable ("idProfessor") @Positive Integer idProfessor) throws RegraDeNegocioException;

    @Operation(summary = "Cria um professor", description = "Cria um professor e o persiste no banco de dados." +
            " Para isso, as informações do professor a ser persistido deverão ser informadas no campo da" +
            " requisição. O ID será gerado automaticamente através da sequence do banco de dados." +
            " Caso o professor não seja criado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria o professor e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "201", description = "Cria o professor e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody @Valid ProfessorCreateDTO professorCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Atualiza um professor", description = "Atualiza um professor e o persiste no banco de dados." +
            " Para isso, o ID do recurso a ser atualizado deverá ser informado na URL da requisição, e as" +
            " informações do professor a ser atualizado deverão ser informadas no corpo da requisição." +
            " Caso o recurso não seja atualizado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza um professor"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable("idProfessor") @Positive Integer idProfessor, @RequestBody @Valid ProfessorCreateDTO professorDTO) throws RegraDeNegocioException;

    @Operation(summary = "Deleta um professor", description = "Deleta um professor do banco de dados" +
            " Para isso, o ID do recurso a ser deletado deverá ser informado na URL da requisição" +
            " Caso o recurso não seja deletado adequadamente, o programa poderá lançar uma exceção")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta um professor"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable("idProfessor") @Positive Integer idProfessor) throws RegraDeNegocioException;

}
