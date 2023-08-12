package br.com.dbc.vemser.tf03spring.documentation;

import br.com.dbc.vemser.tf03spring.dto.AlunoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

public interface EnderecoControllerDoc {

    @Operation(summary = "Cria um Endereco", description = "Cria um endereco e o persiste no banco de dados." +
            " Para isso, as informações do endereco a ser persistido deverão ser informadas no campo da" +
            " requisição. O ID será gerado automaticamente através da sequence do banco de dados." +
            " Caso o aluno não seja criado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria o endereco e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "201", description = "Cria o endereco e o persiste no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @PostMapping()
    ResponseEntity<EnderecoDTO> create(@RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException;

    @Operation(summary = "Lista todos os enderecos", description = "Lista todos os enderecos do banco de dados.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os enderecos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping
    ResponseEntity<List<EnderecoDTO>> findAll() throws BancoDeDadosException;

    @Operation(summary = "Lista todos os enderecos por id", description = "Lista todos os enderecos do banco de dados por id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os enderecos por id "),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("idEndereco") @Positive Integer idEndereco) throws BancoDeDadosException;

    @Operation(summary = "Atualiza um endereco", description = "Atualiza um endereco e o persiste no banco de dados." +
            " Para isso, o ID do recurso a ser atualizado deverá ser informado na URL da requisição, e as" +
            " informações do endereco a ser atualizado deverão ser informadas no corpo da requisição." +
            " Caso o recurso não seja atualizado adequadamente, o programa poderá lançar uma exceção.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza um endereco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )

    @GetMapping
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") @Positive Integer idEndereco, @RequestBody @Valid EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException;

    @Operation(summary = "Deleta um endereco", description = "Deleta um endereco do banco de dados" +
            " Para isso, o ID do recurso a ser deletado deverá ser informado na URL da requisição" +
            " Caso o recurso não seja deletado adequadamente, o programa poderá lançar uma exceção")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta um endereco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idEndereco}")
    ResponseEntity<Void> delete(@PathVariable("idEndereco") @Positive Integer idEndereco)  throws BancoDeDadosException;
}
