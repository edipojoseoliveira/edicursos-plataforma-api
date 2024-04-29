package br.com.edicursos.plataformaapi.plataformaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edicursos.plataformaapi.plataformaapi.entities.Curso;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
}
