package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProfessorCreateDTO {

    @NotNull(message = "O campo nome não pode ser nulo")
    @Schema(description = "Nome do professor")
    private String nome;

    @NotNull(message = "O campo cpf não pode ser nulo")
    @Schema(description = "CPF do professor")
    private String cpf;

    @NotNull(message = "O campo especialidade não pode ser nulo")
    @Schema(description = "Especialidade do professor")
    private String especialidade;

    @NotNull(message = "O campo salario não pode ser nulo")
    @Schema(description = "Salário do professor")
    private Double salario;


}