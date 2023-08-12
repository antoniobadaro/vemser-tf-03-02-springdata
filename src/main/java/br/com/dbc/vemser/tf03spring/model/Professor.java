package br.com.dbc.vemser.tf03spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    private Integer idProfessor;
    private String nome;
    private String cpf;
    private String especialidade;
    private double salario;


}