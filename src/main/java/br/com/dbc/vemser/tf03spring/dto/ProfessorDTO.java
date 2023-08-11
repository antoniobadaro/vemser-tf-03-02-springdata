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

}