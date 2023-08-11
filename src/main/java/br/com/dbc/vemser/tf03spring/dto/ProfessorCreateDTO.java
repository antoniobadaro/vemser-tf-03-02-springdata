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

    @NotNull
    @Schema(description = "Nome do professor")
    private String nome;

    @NotNull
    @Schema(description = "CPF do professor")
    private String cpf;

    @NotNull
    @Schema(description = "Especialidade do professor")
    private String especialidade;

    @NotNull
    @Schema(description = "Salário do professor")
    private Double salario;


}