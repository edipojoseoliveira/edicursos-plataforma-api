package br.com.edicursos.plataformaapi.plataformaapi;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public Collection<Curso> findAll() {
        return this.repo.findAll();
    }

    public Curso findById(UUID id) {
        return this.repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Curso não encontrado"));
    }

    public Curso save(Curso curso) {
        curso = this.repo.save(curso);
        return curso;
    }

    public Curso update(UUID id, Curso curso) {
        try {
            var buscaCurso = this.repo.getOne(id);
            buscaCurso.setNome(curso.getNome());
            buscaCurso.setDescricao(curso.getDescricao());
            buscaCurso.setUrlDaImagem(curso.getUrlDaImagem());
            buscaCurso = this.repo.save(buscaCurso);

            return buscaCurso;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Curso não encontrado");
        }
    }

    public void delete(UUID id) {
        this.repo.deleteById(id);
    }

}
