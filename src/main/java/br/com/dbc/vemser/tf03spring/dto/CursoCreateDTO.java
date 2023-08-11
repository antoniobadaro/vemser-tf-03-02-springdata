package br.com.dbc.vemser.tf03spring.dto;

import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoCreateDTO {

    @NotNull
    @NotBlank
    @Schema(description = "Nome do curso", required = true, example = "Arquitetura de computadores")
    private String nome;
    @NotNull
    @Schema(description = "Carga horária do curso", required = true, example = "60")
    private Integer cargaHoraria;
    @NotNull
    @Schema(description = "Período do curso", required = true, example = "MANHA")
    private TipoPeriodo periodo;

}
