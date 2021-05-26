package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Util;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar o componentes do front-end Locacao
 * 
 * @author Renato Sampaio
 * @since 05/05/2021
 * @version 1.0
 */
@ManagedBean(name = "locacaoBacking")
@SessionScoped
public class LocacaoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos da tela de cadastro
	private String valor;
	private String dataLocacao;
	private Date dataDevolucao;
	private double valorFinal;
	private boolean bloqueio;

	// Atributos da tela de consulta
	private Locacao locacao;
	private Filme filmeSelecionado;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<Filme> listaFilmesLocacao;
	private ArrayList<FormaPagamento> listaFormaPagamento;

	// Atributos auxiliares
	private int ComboFormaPagamento;
	private int comboCliente;
	private int filme;
	
	private Date minDate;
	private Date maxDate;

	// Método construtor da classe
	public LocacaoBacking() {
		carregarClientes();
		carregarFilmes();
		carregarFormaPagamentos();
		carregarDataAtual();
		carregarDataLimiteLocacao();
		listaFilmesLocacao = new ArrayList<Filme>();
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
		setBloqueio(true);
	}

	public int getClienteCombo() {
		return comboCliente;
	}

	public void setClienteCombo(int comboCliente) {
		this.comboCliente = comboCliente;
	}

	public int getFilme() {
		return filme;
	}

	public void setFilme(int filme) {
		this.filme = filme;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getFormaPagamentoCombo() {
		return ComboFormaPagamento;
	}

	public void setFormaPagamentoCombo(int ComboFormaPagamento) {
		this.ComboFormaPagamento = ComboFormaPagamento;
	}

	public double getValorTotal() {
		return valorFinal;
	}

	public void setValorTotal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}

	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}
	
	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	/*
	 * Método que captura a acao do botao Adicionar na tela cad-locacao.xhtml
	 */
	public void salvar() {

		if (validar()) {
			try {
				getLocacao();
				new LocacaoController().salvar(locacao);

				salvarLocacaoFilmes();

				cancelar();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}

		}

	}

	/*
	 * Método para validar as informações da tela de cad-locacao.xhtml
	 */
	private boolean validar() {

		if (Valida.isIntZero(comboCliente)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesLocacao.size())) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(ComboFormaPagamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}

		return true;
	}
	
	/*
	 * Método para retornar um objeto Locacao
	 */
	private void getLocacao() {
		locacao = new Locacao();

		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(getDateToString());

		locacao.setValor(valorFinal);
		locacao.setDevolvido("Não");

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setFormaPagamentoIdFormaPagamento(getFormaPagamentoLista());
		locacao.setClienteIdCliente(getClienteLista());

	}

	/*
	 * Método que captura a acao do botao CANCELAR na tela cad-locacao.xhtml
	 */
	public void cancelar() {
		setClienteCombo(0);
		setBloqueio(true);
		limparCampos();
	}

	/*
	 * Método que captura a acao do botao SAIR na tela cad-locacao.xhtml
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para limpar as informações da tela cad-locacao.xhtml
	 */
	private void limparCampos() {
		carregarFilmes();
		setFilme(0);
		setFormaPagamentoCombo(0);
		setDataDevolucao(null);
		valorFinal = 0.0;
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
		listaFilmesLocacao = new ArrayList<Filme>();
	}

	/*
	 * Método para carregar a lista de filmes que estão disponiveis
	 */
	private void carregarFilmes() {
		try {
			listaFilmes = new FilmeController().buscarDisponivel("Sim");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para carregar a lista de clientes
	 */
	private void carregarClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para carregar as formas de pagamento
	 */
	private void carregarFormaPagamentos() {
		try {
			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Método para obter a data da locação (data atual)
	 */
	private void carregarDataAtual() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		dataLocacao = formatador.format(data);
	}

	/*
	 * Método para adicionar os filmes na ArrayList de filmes (e remover da ArrayList de filmes disponiveis)
	 */
	public void adicionarFilme() {
		if (filme > 0) {
			int index = listaFilmes.indexOf(new Filme(filme));
			Filme filme = listaFilmes.get(index);

			if (filme.getFaixaEtaria() > getClienteLista().getIdade()) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, "Filme Não permiitido para essa faixa etária");
			} else {
				listaFilmesLocacao.add(filme);
				listaFilmes.remove(filme);

				Collections.sort(listaFilmesLocacao, Filme.FilmeComparator);

				if (filme.getPromocao().equals("Sim")) {
					valorFinal += filme.getValorPromocao();
				} else {
					valorFinal += filme.getValor();
				}
				valor = NumberFormat.getCurrencyInstance().format(valorFinal);
			}

		} else {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO,"Favor selecione um filme");
		}

	}

	/*
	 * Método para salvar os dados na tabela de locacao_filme (no Banco de Dados)
	 */
	private void salvarLocacaoFilmes() {
		for (Filme filme : listaFilmesLocacao) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel("Não");
				new FilmeController().salvar(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Método para bloquear a comboBox de filmes
	 */
	public void desbloqueioFilmes() {
		if (comboCliente > 0) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}

	/*
	 * Método para formatar a data de Devolução em String
	 */
	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataDevolucao);
	}

	/*
	 * Método para pegar o index de forma de pagamento
	 */
	private FormaPagamento getFormaPagamentoLista() {
		int indexFormaPagamento = listaFormaPagamento.indexOf(new FormaPagamento(ComboFormaPagamento));
		return listaFormaPagamento.get(indexFormaPagamento);
	}

	/*
	 * Método para pegar o index de Cliente
	 */
	private Cliente getClienteLista() {
		int indexCliente = listaClientes.indexOf(new Cliente(comboCliente));
		return listaClientes.get(indexCliente);
	}

	/*
	 * Método para excluir o filme da tabela
	 */
	public void excluir() {
		listaFilmesLocacao.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);

		Collections.sort(listaFilmes, Filme.FilmeComparator);

		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valorFinal -= filmeSelecionado.getValorPromocao();
		} else {
			valorFinal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorFinal);

	}
	
	private void carregarDataLimiteLocacao() {
		setMinDate(Util.getDateAtual());
		setMaxDate(Util.getMaxDate(Constantes.INT_TRINTA));
	}

} // Fim da classe