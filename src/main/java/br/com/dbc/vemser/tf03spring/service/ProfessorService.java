package br.com.dbc.vemser.tf03spring.service;


import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.model.Professor;
import br.com.dbc.vemser.tf03spring.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;


    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public ProfessorDTO create(ProfessorDTO professorDTO) {
        Professor professorCriado = professorRepository.create(professorDTO);
        return new ProfessorDTO(professorCriado);
    }

    public List<ProfessorDTO> findAll() {
        List<Professor> todosOsProfessores = professorRepository.findAll();
        List<ProfessorDTO> professorDTOS = new ArrayList<>();

        for (Professor professor : todosOsProfessores) {
            professorDTOS.add(new ProfessorDTO(professor));
        }

        return professorDTOS;
    }

    public ProfessorDTO update(Integer idProfessor, ProfessorDTO professorDTO) {
        Professor professorAtualizado = professorRepository.update(idProfessor, professorDTO);

        return new ProfessorDTO(professorAtualizado);
    }

    public void delete(Integer idProfessor) {
        professorRepository.delete(idProfessor);
    }
}
