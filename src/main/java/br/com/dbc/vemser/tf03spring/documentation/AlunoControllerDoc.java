package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

public interface AlunoControllerDoc {

    @Operation(summary = "Cria um aluno", description = "Cria um aluno e o persiste no banco de dados." +
            " Para isso, as informações do aluno a ser persistido deverão ser informadas no campo da" +
            " requisição. O ID será gerado automaticamente através da sequence do banco de dados." +
            " Caso o aluno não seja criado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria o aluno e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "201", description = "Cria o aluno e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping()
    ResponseEntity<AlunoDTO> create(@RequestBody @Valid AlunoCreateDTO alunoCreateDTO, @Positive @RequestParam Integer idCurso) throws RegraDeNegocioException, MessagingException, TemplateException, IOException;

    @Operation(summary = "Lista todos os alunos", description = "Lista todos os alunos do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os alunos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<AlunoDTO>> findAll() throws RegraDeNegocioException;


    @Operation(summary = "Lista um aluno", description = "Lista um aluno específico do banco de dados." +
            " Para isso, o ID do aluno a ser listado deverá ser informado na URL da requisição.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista um aluno"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idAluno}")
    ResponseEntity<AlunoDTO> findById(@PathVariable("idAluno") @Positive Integer idAluno) throws RegraDeNegocioException;

    @Operation(summary = "Atualiza um aluno", description = "Atualiza um aluno e o persiste no banco de dados." +
            " Para isso, o ID do recurso a ser atualizado deverá ser informado na URL da requisição, e as" +
            " informações do aluno a ser atualizado deverão ser informadas no corpo da requisição." +
            " Caso o recurso não seja atualizado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza um aluno"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idAluno}")
    public ResponseEntity<AlunoDTO> update(@PathVariable("idAluno") @Positive Integer idAluno,
                                           @RequestBody @Valid AlunoCreateDTO alunoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Deleta um aluno", description = "Deleta um aluno do banco de dados" +
            " Para isso, o ID do recurso a ser deletado deverá ser informado na URL da requisição" +
            " Caso o recurso não seja deletado adequadamente, o programa poderá lançar uma exceção")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta um aluno"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idAluno}")
    public ResponseEntity<Void> delete(@PathVariable("idAluno") @Positive(message = "O ID deve ser um número inteiro positivo") Integer idAluno) throws RegraDeNegocioException;

}
