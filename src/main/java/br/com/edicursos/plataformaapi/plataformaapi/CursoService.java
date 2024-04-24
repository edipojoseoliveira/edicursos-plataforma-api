package br.com.edicursos.plataformaapi.plataformaapi;

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

    public Optional<Curso> findById(UUID id) {
        return this.repo.findById(id);
    }

    public Curso save(Curso curso) {
        curso = this.repo.save(curso);
        return curso;
    }

    public Curso update(UUID id, Curso curso) {
        var buscaCurso = this.repo.getOne(id);
        buscaCurso.setNome(curso.getNome());
        buscaCurso.setDescricao(curso.getDescricao());
        buscaCurso.setUrlDaImagem(curso.getUrlDaImagem());
        buscaCurso = this.repo.save(buscaCurso);

        return buscaCurso;
    }

    public void delete(UUID id) {
        this.repo.deleteById(id);
    }

}
