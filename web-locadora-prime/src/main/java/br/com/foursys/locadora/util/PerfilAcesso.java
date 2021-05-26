package br.com.foursys.locadora.util;

/**
 * ENUM responssável por armazenar os perfis de acesso
 *
 * @author Renato Duarte Sampaio
 * @since 29 de abr. de 2021
 * @version 1.0
 * 
 */
public enum PerfilAcesso {

	ADMIN("Administrador"),
	DEV("Desenvolvedor"),
	USER("Usuario");
	
	private String descricao;
	
	PerfilAcesso(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
