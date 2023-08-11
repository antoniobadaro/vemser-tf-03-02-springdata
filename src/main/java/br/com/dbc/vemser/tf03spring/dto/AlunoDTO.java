package br.com.dbc.vemser.tf03spring.dto;

import br.com.dbc.vemser.tf03spring.model.Aluno;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AlunoDTO extends AlunoCreateDTO {

    @Schema(description = "ID do aluno")
    private Integer idAluno;
    @Schema(description = "Nome do aluno")
    private String nome;
    @Schema(description = "Nome do aluno")
    private String idade;
    @Schema(description = "CPF do aluno")
    private String cpf;
    @Schema(description = "Numero de matrícula do aluno")
    private String numeroDeMatricula;

    public AlunoDTO(Aluno aluno) {
        this.idAluno = aluno.getIdAluno();
        this.nome = aluno.getNome();
        this.idade = aluno.getIdade();
        this.cpf = aluno.getCpf();
        this.numeroDeMatricula = aluno.getNumeroDeMatricula();
    }

    public AlunoDTO(AlunoCreateDTO alunoCreateDTO) {
        this.nome = alunoCreateDTO.getNome();
        this.idade = alunoCreateDTO.getIdade();
        this.cpf = alunoCreateDTO.getCpf();
        this.numeroDeMatricula = alunoCreateDTO.getNumeroDeMatricula();
    }

}
