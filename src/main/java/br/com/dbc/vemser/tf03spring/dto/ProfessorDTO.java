package br.com.dbc.vemser.tf03spring.dto;

import br.com.dbc.vemser.tf03spring.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProfessorDTO extends ProfessorCreateDTO {

    @NotBlank
    @NotNull
    @Schema(description = "ID do professor")
    private Integer idProfessor;
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


    public ProfessorDTO(Professor professor) {
        this.idProfessor = professor.getIdProfessor();
        this.nome = professor.getNome();
        this.cpf = professor.getCpf();
        this.especialidade = professor.getEspecialidade();
        this.salario = professor.getSalario();
    }

    public ProfessorDTO(ProfessorCreateDTO professorCreateDTO){
       this.nome = professorCreateDTO.getNome();
       this.cpf = professorCreateDTO.getCpf();
       this.especialidade = professorCreateDTO.getEspecialidade();
       this.salario = professorCreateDTO.getSalario();
    }
}