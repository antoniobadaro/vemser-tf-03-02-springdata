package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlunoCreateDTO {

    @NotNull(message = "O campo nome não pode ser nulo")
    @Schema(description = "Nome do aluno", required = true, example = "Rafael Silva")
    @Size(max = 255)
    private String nome;

    @NotNull(message = "O campo idade não pode ser nulo")
    @Schema(description = "Idade do aluno", required = true, example = "16")
    private String idade;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @Schema(description = "CPF do aluno", required = true, example = "02815693518")
    @Size(max = 11)
    private String cpf;

    @Schema(description = "Número de matrícula do aluno", required = true, example = "8220188")
    @Size(max = 10)
    private String numeroDeMatricula;

    @Schema(description = "Insira o email do aluno", required = true, example = "teste@gmail.com")
    private String email;

}
