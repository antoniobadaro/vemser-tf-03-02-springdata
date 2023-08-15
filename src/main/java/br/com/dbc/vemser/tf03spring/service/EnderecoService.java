package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.model.EnderecoEntity;
import br.com.dbc.vemser.tf03spring.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ObjectMapper objectMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ObjectMapper objectMapper){
        this.objectMapper=objectMapper;
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        EnderecoEntity enderecoCriado = retornarEntidade(enderecoCreateDTO);
        return retornarDTO(enderecoCriado);
    }

    public List<EnderecoDTO> findAll() throws BancoDeDadosException {
        return enderecoRepository.findAll().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO findById(Integer idEndereco) throws BancoDeDadosException {
        EnderecoEntity enderecoEncontrado = enderecoRepository.findById(idEndereco).get();
        return retornarDTO(enderecoEncontrado);
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException {
        EnderecoEntity enderecoAtualizado = enderecoRepository.findById(idEndereco).get();

        enderecoAtualizado.setLogradouro(enderecoCreateDTO.getLogradouro());
        enderecoAtualizado.setEstado(enderecoCreateDTO.getEstado());
        enderecoAtualizado.setComplemento(enderecoCreateDTO.getComplemento());
        enderecoAtualizado.setCidade(enderecoCreateDTO.getCidade());
        enderecoAtualizado.setCep(enderecoCreateDTO.getCep());
        enderecoAtualizado.setBairro(enderecoCreateDTO.getBairro());
        enderecoAtualizado.setNumero(enderecoCreateDTO.getNumero());

        return retornarDTO(enderecoAtualizado);
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
