package br.com.edicursos.plataformaapi.plataformaapi.service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.edicursos.plataformaapi.plataformaapi.controller.exception.ControllerNotFoundException;
import br.com.edicursos.plataformaapi.plataformaapi.dto.CursoDTO;
import br.com.edicursos.plataformaapi.plataformaapi.entities.Curso;
import br.com.edicursos.plataformaapi.plataformaapi.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoService {

    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public Collection<CursoDTO> findAll() {
    	var cursos = this.repo.findAll();
        return cursos.stream().map(this::toCursoDTO).collect(Collectors.toList());
    }
    
    public Page<CursoDTO> findAll(Pageable pageable) {
    	Page<Curso> cursos = this.repo.findAll(pageable);
        return cursos.map(this::toCursoDTO);
    }

    public CursoDTO findById(UUID id) {
    	var curso = this.repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Curso não encontrado"));
        return this.toCursoDTO(curso);
    }

    public CursoDTO save(CursoDTO cursoDTO) {
        var curso = this.toCurso(cursoDTO);
        curso = this.repo.save(curso);
        return this.toCursoDTO(curso);
    }

    public CursoDTO update(UUID id, CursoDTO cursoDTO) {
        try {
            var buscaCurso = this.repo.getReferenceById(id);
            buscaCurso.setNome(cursoDTO.nome());
            buscaCurso.setDescricao(cursoDTO.descricao());
            buscaCurso.setUrlDaImagem(cursoDTO.urlDaImagem());
            buscaCurso = this.repo.save(buscaCurso);

            return this.toCursoDTO(buscaCurso);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Curso não encontrado");
        }
    }

    public void delete(UUID id) {
        this.repo.deleteById(id);
    }
    
    private CursoDTO toCursoDTO(Curso curso) {
    	return new CursoDTO(
    			curso.getId(),
				curso.getNome(),
				curso.getDescricao(),
				curso.getUrlDaImagem());
    }
    
    private Curso toCurso(CursoDTO curso) {
    	return new Curso(
    			curso.id(),
				curso.nome(),
				curso.descricao(),
				curso.urlDaImagem());
    }

}
