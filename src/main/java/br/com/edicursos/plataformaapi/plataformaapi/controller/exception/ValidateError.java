package br.com.edicursos.plataformaapi.plataformaapi.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {

	private List<ValidateMessage> mensagens = new ArrayList<ValidateMessage>();
	
	public List<ValidateMessage> getMensagens() {
		return mensagens;
	}
	
	public void addMensagem(String campo, String mensagem) {
		this.mensagens.add(new ValidateMessage(campo, mensagem));
	}
	
}
