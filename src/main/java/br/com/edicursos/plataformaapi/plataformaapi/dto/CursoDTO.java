package br.com.edicursos.plataformaapi.plataformaapi.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
		UUID id,
		@NotBlank(message = "O nome não pode estar em branco.")
		String nome,
		@NotBlank(message = "A descrição não pode estar em branco.")
		String descricao,
		String urlDaImagem
		) {}
