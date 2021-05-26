package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.LocacaoFilmeController;

@ManagedBean(name = "detLocacaoBacking")
@ViewScoped
public class DetLocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locacao locacao;

	private String nomeCliente;
	private String dataLocacao;
	private String dataDevolucao;
	private String valor;
	private String devolvido;

	private ArrayList<Filme> listaFilmes;

	public DetLocacaoBacking() {
		this.locacao = ListLocacaoBacking.locacaoSelecionada;
		carregarTela();
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-locacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		setNomeCliente(locacao.getClienteIdCliente().getNome());
		setDataLocacao(locacao.getDataLocacao());
		setDataDevolucao(locacao.getDataDevolucao());
		setValor(locacao.getValorFormatado());
		setDevolvido(locacao.getDevolvido());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(locacao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

	}

}
