package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.PerfilAcesso;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "loginBacking")
@SessionScoped
public class LoginBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	public boolean user;
	public boolean adm;
	public boolean user2;
	
	public boolean dev;

	public static Funcionario funcionarioLogado;

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isUser2() {
		return user2;
	}

	public void setUser2(boolean user2) {
		this.user2 = user2;
	}

	public boolean isDev() {
		return dev;
	}

	public void setDev(boolean dev) {
		this.dev = dev;
	}

	public void efetuarLogin() {

		if (validar()) {

			try {
				ArrayList<Funcionario> funcionarios = new FuncionarioController().buscarPorLogin(login);
				// verifica e logo após abre a tela
				if (validaDados(funcionarios)) {

					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			} catch (Exception e) {
				JSFUtil.addErrorMessage("LOGIN", "Credenciais Inválidas");
			}

		}

	}

	private boolean validar() {
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage("LOGIN", "Favor insira uma Login");
			return false;
		}

		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage("SENHA", "Favor insira uma senha");
			return false;
		}

		return true;
	}

	private boolean validaDados(ArrayList<Funcionario> func) {
		for (Funcionario funcionario : func) {
			if (funcionario.getSenha().equals(senha)) {
				funcionarioLogado = funcionario;
				if (funcionario.getPerfilAcesso().equals(PerfilAcesso.USER.getDescricao())) {
					user = true;
					user2 = true;
					adm = false;
					dev = false;
				} else if (funcionario.getPerfilAcesso().equals(PerfilAcesso.DEV.getDescricao())) {
					user = true;
					user2 = false;
					adm = true;
					dev = true;
					
				} else {
					user = false;
					user2 = false;
					adm = true;
					dev = false;
					
				}
				return true;
			}
		}

		return true;
	}
}
