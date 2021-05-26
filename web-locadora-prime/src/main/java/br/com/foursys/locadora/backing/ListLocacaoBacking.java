package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Util;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "listLocacaoBacking")
@ViewScoped
public class ListLocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private int tipoPesquisa;

	private Date dataInicial;
	private Date dataFinal;

	private Date maxDate;

	private boolean data;

	private String dataAtual;

	public static Locacao locacaoSelecionada;

	private ArrayList<Locacao> listaLocacoes;

	public ListLocacaoBacking() {
		carregarDatas();
		setTipoPesquisa(Constantes.INT_QUATRO);
		pesquisar();
	}

	public Locacao getLocacaoSelecionada() {
		return locacaoSelecionada;
	}

	public void setLocacaoSelecionada(Locacao locacaoSelecionada) {
		ListLocacaoBacking.locacaoSelecionada = locacaoSelecionada;
	}

	public int getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarDatas() {
		setMaxDate(Util.getDateAtual());
		setDataAtual(Util.getDataAtual());
	}

	public void pesquisar() {
		switch (tipoPesquisa) {
		case 1:
			setData(true);
			listaLocacoes = new ArrayList<Locacao>();
			break;
		case 2:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarDevolvidos();
			break;
		case 3:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarNaoDevolvidos();
			break;
		case 4:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarTodos();
			break;
		}
	}

	public void pesquisarLocacoes() {
		if (validar()) {
			try {
				getLocacoes();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private void buscarDevolvidos() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido(Constantes.SIM);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
	}

	private void buscarNaoDevolvidos() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido(Constantes.NAO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
	}

	private void buscarTodos() {
		try {
			listaLocacoes = new LocacaoController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
	}

	private boolean validar() {
		if (Valida.isDateNull(dataInicial)) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.DATA_INICIAL_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataFinal)) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.DATA_FINAL_VAZIO);
			return false;
		}

		if (dataInicial.after(dataFinal)) {
			JSFUtil.addErrorMessage(Titulo.LIST_LOCACAO, Mensagem.DATA_ERRO);
			return false;
		}

		return true;
	}

	private void getLocacoes() throws ParseException {
		listaLocacoes = new ArrayList<Locacao>();
		for (Locacao locacao : new LocacaoController().buscarTodos()) {
			Date data = Util.getStringToDate(locacao.getDataLocacao());

			if ((data.getDate() == dataInicial.getDate() || data.getDate() == dataFinal.getDate())
					|| (data.after(dataInicial) && data.before(dataFinal))) {
				listaLocacoes.add(locacao);
			}
		}
	}

	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-locacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
