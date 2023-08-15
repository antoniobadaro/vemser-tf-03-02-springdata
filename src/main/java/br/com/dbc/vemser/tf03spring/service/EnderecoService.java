package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.EnderecoEntity;
import br.com.dbc.vemser.tf03spring.model.ProfessorEntity;
import br.com.dbc.vemser.tf03spring.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        EnderecoEntity enderecoCriado = retornarEntidade(enderecoCreateDTO);
        EnderecoEntity enderecoEnviar = enderecoRepository.save(enderecoCriado);
        return retornarDTO(enderecoEnviar);
    }

    public List<EnderecoDTO> findAll() throws BancoDeDadosException {
        List<EnderecoEntity> todosOsEnderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        for (EnderecoEntity endereco : todosOsEnderecos) {
            enderecoDTOS.add(retornarDTO(endereco));
        }
        return enderecoDTOS;
    }

    public EnderecoDTO findById(Integer idEndereco) throws BancoDeDadosException {
        EnderecoEntity enderecoEncontrado = enderecoRepository.findById(idEndereco).get();
        return retornarDTO(enderecoEncontrado);
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoDTO enderecoDTO) throws RegraDeNegocioException {
        EnderecoEntity enderecoAtualizado = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereco não encontrado"));

        enderecoAtualizado.setLogradouro(enderecoDTO.getLogradouro());
        enderecoAtualizado.setEstado(enderecoDTO.getEstado());
        enderecoAtualizado.setComplemento(enderecoDTO.getComplemento());
        enderecoAtualizado.setCidade(enderecoDTO.getCidade());
        enderecoAtualizado.setCep(enderecoDTO.getCep());
        enderecoAtualizado.setBairro(enderecoDTO.getBairro());
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());

        EnderecoEntity enderecoEnviar = enderecoRepository.save(enderecoAtualizado);
        return retornarDTO(enderecoEnviar);
    }

    public void delete(Integer idEndereco) throws BancoDeDadosException {
        enderecoRepository.deleteById(idEndereco);
    }

    public EnderecoEntity retornarEntidade(EnderecoCreateDTO enderecoCreateDTO){
        return objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
    }

    public EnderecoDTO retornarDTO(EnderecoEntity enderecoEntity){
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

}
