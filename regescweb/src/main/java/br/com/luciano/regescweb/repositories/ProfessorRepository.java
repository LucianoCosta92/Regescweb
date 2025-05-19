package br.com.luciano.regescweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luciano.regescweb.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
