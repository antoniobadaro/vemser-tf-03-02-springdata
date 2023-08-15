package br.com.dbc.vemser.tf03spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "ENDERECO")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "estado")
    private String estado;

//    @Column(name = "aluno")
//    private Set<Object> proprietario;
}
