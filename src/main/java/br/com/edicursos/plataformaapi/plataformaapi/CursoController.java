package br.com.edicursos.plataformaapi.plataformaapi;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<Curso>> findAll() {
        var cursos = this.service.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable UUID id) {
        var curso = this.service.findById(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody Curso curso) {
        curso = this.service.save(curso);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable UUID id, @RequestBody Curso curso) {
        curso = this.service.update(id, curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
