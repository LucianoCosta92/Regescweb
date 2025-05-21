package br.com.luciano.regescweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luciano.regescweb.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
