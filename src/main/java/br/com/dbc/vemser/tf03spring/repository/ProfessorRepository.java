package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.database.ConexaoBancoDeDados;
import br.com.dbc.vemser.tf03spring.dto.ProfessorDTO;
import br.com.dbc.vemser.tf03spring.model.Professor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {
    private Integer obterProximoIdSequence(String nomeDaSequence) throws SQLException {
        int proximoId = 0;
        Connection connection = null;
        PreparedStatement sequenceStatement = null;
        ResultSet sequenceResultSet = null;

        try {
            connection = ConexaoBancoDeDados.getConnection();
            String sequenceSql = "SELECT " + nomeDaSequence + ".NEXTVAL FROM DUAL";
            sequenceStatement = connection.prepareStatement(sequenceSql);
            sequenceResultSet = sequenceStatement.executeQuery();

            if (sequenceResultSet.next()) {
                proximoId = sequenceResultSet.getInt(1);
            }

        } finally {
            if (sequenceResultSet != null) {
                sequenceResultSet.close();
            }
            if (sequenceStatement != null) {
                sequenceStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return proximoId;
    }

    public List<Professor> findAll() {
        List<Professor> todosOsProfessores = new ArrayList<>();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM PROFESSOR";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(resultSet.getInt("ID_PROFESSOR"));
                professor.setNome(resultSet.getString("NOME"));
                professor.setCpf(resultSet.getString("CPF"));
                professor.setSalario(resultSet.getDouble("SALARIO"));
                professor.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
                todosOsProfessores.add(professor);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao listar os professores.");
        }

        return todosOsProfessores;
    }

    public Professor create(ProfessorDTO professorDTO) {
        Professor professorCriado = null;

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            int proximoId = obterProximoIdSequence("SEQ_PROFESSOR");
            String sql = "INSERT INTO PROFESSOR (ID_PROFESSOR, NOME, CPF, SALARIO, ESPECIALIDADE) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, professorDTO.getNome());
            preparedStatement.setString(3, professorDTO.getCpf());
            preparedStatement.setDouble(4, professorDTO.getSalario());
            preparedStatement.setString(5, professorDTO.getEspecialidade());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM PROFESSOR WHERE ID_PROFESSOR = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, proximoId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                professorCriado = new Professor();
                professorCriado.setIdProfessor(resultSet.getInt("ID_PROFESSOR"));
                professorCriado.setNome(resultSet.getString("NOME"));
                professorCriado.setCpf(resultSet.getString("CPF"));
                professorCriado.setSalario(resultSet.getDouble("SALARIO"));
                professorCriado.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Professor inserido com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao inserir professor.");
        }

        return professorCriado;
    }

    public Professor update(Integer idProfessor, ProfessorDTO professorDTO) {
        Professor professorAtualizado = new Professor();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE PROFESSOR SET NOME = ?, CPF = ?, SALARIO = ?, ESPECIALIDADE = ? WHERE ID_PROFESSOR= ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, professorDTO.getNome());
            preparedStatement.setString(2, professorDTO.getCpf());
            preparedStatement.setDouble(3, professorDTO.getSalario());
            preparedStatement.setString(4, professorDTO.getEspecialidade());
            preparedStatement.setInt(5, idProfessor);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM PROFESSOR WHERE ID_PROFESSOR = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, idProfessor);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                professorAtualizado.setIdProfessor(resultSet.getInt("ID_PROFESSOR"));
                professorAtualizado.setNome(resultSet.getString("NOME"));
                professorAtualizado.setCpf(resultSet.getString("CPF"));
                professorAtualizado.setSalario(resultSet.getDouble("SALARIO"));
                professorAtualizado.setEspecialidade(resultSet.getString("ESPECIALIDADE"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Professor atualizado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao atualizar professor.");
        }

        return professorAtualizado;
    }

    public void delete(Integer idProfessor) {
        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM PROFESSOR WHERE ID_PROFESSOR = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProfessor);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("Professor deletado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
            System.out.println("Erro ao deletar professor.");
        }
    }
}
