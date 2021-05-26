package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.dao.LocacaoDAO;

public class LocacaoController {

	public void salvar(Locacao locacao) {
		try {
			new LocacaoDAO().salvar(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Locacao> buscarDevolvido(String devolvido) {
		try {
			return new LocacaoDAO().buscarDevolvido(devolvido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Locacao> buscarTodos() {
		try {
			return new LocacaoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
