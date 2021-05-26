package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;

@ManagedBean(name = "detFilmeBacking")
@ViewScoped
public class DetFilmeBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private double valor;
	private String disponivel;
	private String promocao;
	private double valorPromocao;
	private String diretor;
	private String anoLancamento;
	private int faixaEtaria;
	private String genero;

	public DetFilmeBacking() {
		carregarTela(ListFilmeBacking.filmeSelecionado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public double getValorPromocao() {
		return valorPromocao;
	}

	public void setValorPromocao(double valorPromocao) {
		this.valorPromocao = valorPromocao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public int getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(int faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-filme.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarTela(Filme filme) {
		setNome(filme.getNome());
		setValor(filme.getValor());
		setDisponivel(filme.getDisponivel());
		setPromocao(filme.getPromocao());
		setValorPromocao(filme.getValorPromocao());
		setDiretor(filme.getDiretor());
		setAnoLancamento(filme.getAnoLancamento());
		setFaixaEtaria(filme.getFaixaEtaria());
		setGenero(filme.getGenero());
	}

}
