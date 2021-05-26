package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;

@ManagedBean(name = "listFuncionarioBacking")
@ViewScoped
public class ListFuncionarioBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomePesquisar;
	public static Funcionario funcionarioSelecionado;

	private ArrayList<Funcionario> listaFuncionarios;

	public ListFuncionarioBacking() {
		listarFuncionarios();
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		ListFuncionarioBacking.funcionarioSelecionado = funcionarioSelecionado;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			listaFuncionarios = new ArrayList<Funcionario>();
			for (Funcionario funcionario : new FuncionarioController().buscarPorNome(nomePesquisar)) {
				if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
					if (LoginBacking.funcionarioLogado.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
						listaFuncionarios.add(funcionario);
					}
				} else {
					listaFuncionarios.add(funcionario);
				}
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
	}

	private void listarFuncionarios() {
		try {
			listaFuncionarios = new ArrayList<Funcionario>();
			for (Funcionario funcionario : new FuncionarioController().buscarTodos()) {
				if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
					if (LoginBacking.funcionarioLogado.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
						listaFuncionarios.add(funcionario);
					}
				} else {
					listaFuncionarios.add(funcionario);
				}
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
	}

	public void excluir() {
		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(funcionarioSelecionado.getEnderecoIdEndereco());
			new ContatoController().excluir(funcionarioSelecionado.getContatoIdContato());
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIDO);
		}
	}

}
