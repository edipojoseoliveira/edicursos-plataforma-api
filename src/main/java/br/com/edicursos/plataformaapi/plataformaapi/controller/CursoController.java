package br.com.edicursos.plataformaapi.plataformaapi.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edicursos.plataformaapi.plataformaapi.dto.CursoDTO;
import br.com.edicursos.plataformaapi.plataformaapi.service.CursoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<CursoDTO>> findAll() {
        var cursosDTO = this.service.findAll();
        return ResponseEntity.ok(cursosDTO);
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<CursoDTO>> findAll(
    		@PageableDefault(size = 10, page = 0, sort = "nome") Pageable pageable
    		) {
    	Page<CursoDTO> cursosDTO = this.service.findAll(pageable);
    	return ResponseEntity.ok(cursosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable UUID id) {
        var cursoDTO = this.service.findById(id);
        return ResponseEntity.ok(cursoDTO);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> save(@Valid @RequestBody CursoDTO cursoDTO) {
        cursoDTO = this.service.save(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> update(@PathVariable UUID id, @RequestBody CursoDTO cursoDTO) {
        cursoDTO = this.service.update(id, cursoDTO);
        return ResponseEntity.ok(cursoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
