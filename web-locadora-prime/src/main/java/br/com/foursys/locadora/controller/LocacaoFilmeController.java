package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.dao.LocacaoFilmeDAO;

public class LocacaoFilmeController {

	public void salvar(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().salvar(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao){
		try {
			return new LocacaoFilmeDAO().buscarPorLocacao(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
