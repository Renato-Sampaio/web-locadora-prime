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
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;

/**
 * Classe responsavel por controlar os componentes do front-end Devolução
 * 
 * @author Renato Sampaio
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "consultaBacking")
@ViewScoped
public class ConsultaBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos da tela de devolução
	private String nomeCliente;
	private String nomeFuncionario;
	private int idLocacao;
	private String devolvido;
	private String formaPagamento;
	private double valorTotal;
	private String dataLocacao;
	private String dataDevolucaoLocacao;

	// Atributos da tela de consulta
	private ArrayList<Locacao> listaLocacoes;
	private Locacao devolucao;
	private ArrayList<Filme> listaFilmes;
	
	// Atributos auxiliares
	private int locacao;
	private boolean bloqueio;
	private boolean carregado;
	
	// Método construtor da classe
	public ConsultaBacking() {
		carregarLocacoes();
		
	}
	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}



	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}



	public int getIdLocacao() {
		return idLocacao;
	}



	public void setIdLocacao(int idLocacao) {
		this.idLocacao = idLocacao;
	}



	public String getDevolvido() {
		return devolvido;
	}



	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}



	public String getFormaPagamento() {
		return formaPagamento;
	}



	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}



	public double getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}



	public Locacao getDevolucao() {
		return devolucao;
	}



	public void setDevolucao(Locacao devolucao) {
		this.devolucao = devolucao;
	}



	public int getLocacao() {
		return locacao;
	}

	public void setLocacao(int locacao) {
		this.locacao = locacao;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
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

	public String getDataDevolucaoLocacao() {
		return dataDevolucaoLocacao;
	}

	public void setDataDevolucaoLocacao(String dataDevolucaoLocacao) {
		this.dataDevolucaoLocacao = dataDevolucaoLocacao;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public boolean isCarregado() {
		return carregado;
	}

	public void setCarregado(boolean carregado) {
		this.carregado = carregado;
	}

	/*
	 * Método para dar uma mensagem de erro caso o usuario não selecione uma locação para ser devolvida
	 */
	public void carregarLocacao() {
		if (locacao > 0) {
			carregarTela();
		} else {
			JSFUtil.addErrorMessage("Titulo.CADASTRO_DEVOLUCAO", "Mensagem.DEVOLUCAO_NAO_SELECIONADO");
		}
	}


	/*
	 * Método para cancelar (resetando os campos)
	 */
	public void cancelar() {
		setLocacao(0);
		limparCampos();
		setBloqueio(true);
		setCarregado(false);
	}

	/*
	 * Método para sair da tela
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para limpar os campos
	 */
	private void limparCampos() {
		setNomeCliente("Carregar Locação");
		setDataLocacao("Carregar Locação");
		setDataDevolucaoLocacao("Carregar Locação");
		setNomeFuncionario("Carregar Locação");
		setIdLocacao(0);
		setDevolvido("Carregar Locação");
		setFormaPagamento("Carregar Locação");
		setValorTotal(0.0);
		listaFilmes = new ArrayList<Filme>();
	}

	/*
	 * Método para carregar as locações (as que ja foram efetuadas)
	 */
	private void carregarLocacoes() {
		try {
			listaLocacoes = new LocacaoController().buscarTodos();
			setBloqueio(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para carregar a tela
	 */
	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		devolucao = getLocacaoLista();
		setNomeCliente(devolucao.getClienteIdCliente().getNome());
		setDataLocacao(devolucao.getDataLocacao());
		setDataDevolucaoLocacao(devolucao.getDataDevolucao());
		setNomeFuncionario(devolucao.getFuncionarioIdFuncionario().getNome());
		setDevolvido(devolucao.getDevolvido());
		setFormaPagamento(devolucao.getFormaPagamentoIdFormaPagamento().getDescricao());
		setValorTotal(devolucao.getValor());
		setIdLocacao(devolucao.getIdLocacao());
	

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(devolucao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

		setBloqueio(true);
		setCarregado(true);
	}



	/*
	 * Método para pegar o index de Cliente
	 */
	private Locacao getLocacaoLista() {
		int indLocacao = listaLocacoes.indexOf(new Locacao(locacao));
		return listaLocacoes.get(indLocacao);
	}

}