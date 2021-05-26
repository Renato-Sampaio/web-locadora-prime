package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;

/**
 * Classe responsavel por controlar os componentes do front-end Devolução
 * 
 * @author Renato Sampaio
 * @since 05/05/2021
 * @version 1.0
 */

@ManagedBean(name = "devolucaoBacking")
@ViewScoped
public class DevolucaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos da tela de devolução
	private String nomeCliente;
	private String dataLocacao;
	private String dataDevolucaoLocacao;
	private String dataDevolucaoAtual;

	// Atributos da tela de consulta
	private ArrayList<Locacao> listaLocacoes;
	private Locacao devolucao;
	private ArrayList<Filme> listaFilmes;
	
	// Atributos auxiliares
	private int locacao;
	private boolean bloqueio;
	private boolean carregado;
	
	// Método construtor da classe
	public DevolucaoBacking() {
		carregarLocacoes();
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

	public String getDataDevolucaoAtual() {
		return dataDevolucaoAtual;
	}

	public void setDataDevolucaoAtual(String dataDevolucaoAtual) {
		this.dataDevolucaoAtual = dataDevolucaoAtual;
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
			JSFUtil.addErrorMessage(Titulo.CADASTRO_DEVOLUCAO, "Selecione uma Devolução");
		}
	}

	/*
	 * Método para salvar os dados no banco de dados
	 */
	public void salvar() {

		if (validar()) {

			try {
				getDevolucao();
				new LocacaoController().salvar(devolucao);

				alterarFilmesLocacao();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_SALVO);
				cancelar();
				carregarLocacoes();
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_ERRO_SALVO);
			}
		}
	}

	private boolean validar() {

		return true;
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
		setNomeCliente(null);
		setDataLocacao(null);
		setDataDevolucaoLocacao(null);
		setDataDevolucaoAtual(null);
		listaFilmes = new ArrayList<Filme>();
	}

	/*
	 * Método para carregar as locações (as que ja foram efetuadas)
	 */
	private void carregarLocacoes() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido("Não");
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
		setDataDevolucaoAtual(getDataAtual());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(devolucao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

		setBloqueio(true);
		setCarregado(true);
	}

	/*
	 * Método para salvar a devolução no banco de dados (atribuir a data da devolução e o "Sim" para o Devolvido no Banco de dados)
	 */
	private void getDevolucao() {
		devolucao.setDevolvido("Sim");
		if (!dataDevolucaoLocacao.equals(dataDevolucaoAtual)) {
			devolucao.setDataDevolucao(dataDevolucaoAtual);
		}
	}

	/*
	 * Método para salvar a devolução no banco de dados (atribuir "Sim" para o Disponivel no Banco de dados)
	 */
	private void alterarFilmesLocacao() {
		for (Filme filme : listaFilmes) {
			filme.setDisponivel("Sim");

			try {
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Método para pegar o index de Cliente
	 */
	private Locacao getLocacaoLista() {
		int indLocacao = listaLocacoes.indexOf(new Locacao(locacao));
		return listaLocacoes.get(indLocacao);
	}

	
	/*
	 * Método para desbloquear o botão "Carregar Locação" (id = "btnSelecionar")
	 */
	public void desbloqueioDevolucao() {
		if (locacao > 0) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}

	/*
	 * Método para obter a data da devolução (data atual)
	 */
	private String getDataAtual() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}

}
