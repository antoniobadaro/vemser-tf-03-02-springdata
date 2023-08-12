package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Aluno {

    private Integer idAluno;
    private String nome;
    private String idade;
    private String cpf;
    private String numeroDeMatricula;

    public Aluno(AlunoDTO alunoDTO) {
        this.idAluno = alunoDTO.getIdAluno();
        this.nome = alunoDTO.getNome();
        this.idade = alunoDTO.getIdade();
        this.cpf = alunoDTO.getCpf();
        this.numeroDeMatricula = alunoDTO.getNumeroDeMatricula();
    }

}
