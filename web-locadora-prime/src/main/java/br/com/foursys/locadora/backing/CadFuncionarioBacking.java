package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Util;
import br.com.foursys.locadora.util.Valida;

@ManagedBean(name = "cadFuncionarioBacking")
@ViewScoped
public class CadFuncionarioBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String login;
	private String senha;
	private String perfilAcesso;

	private String tipoLogradouro;
	private String nomeEndereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private int cidade;
	private int estado;

	private String telefone;
	private String celular;
	private String email;

	private Funcionario funcionario;
	private Endereco endereco;
	private Contato contato;

	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaPerfis;
	private ArrayList<String> listaTipoLogradouros;

	private boolean comboCidade = true;
	private int indexTab;

	public CadFuncionarioBacking() {
		carregarPerfil();
		carregarTipoLogradouro();
		carregarEstados();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public boolean isComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(boolean comboCidade) {
		this.comboCidade = comboCidade;
	}

	public int getIndexTab() {
		return indexTab;
	}

	public void setIndexTab(int indexTab) {
		this.indexTab = indexTab;
	}

	public ArrayList<String> getListaPerfis() {
		return listaPerfis;
	}

	public void setListaPerfis(ArrayList<String> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public ArrayList<String> getListaTipoLogradouros() {
		return listaTipoLogradouros;
	}

	public void setListaTipoLogradouros(ArrayList<String> listaTipoLogradouros) {
		this.listaTipoLogradouros = listaTipoLogradouros;
	}

	public void salvar() {
		if (validar()) {
			try {
				getFuncionario();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}

	private boolean validar() {
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}
		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(nomeEndereco)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(numero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(bairro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.BAIRRO_VAZIO);
			return false;
		}

		if (Valida.isIntZero(estado)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ESTADO_VAZIO);
			return false;
		}

		if (Valida.isIntZero(cidade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.LOGIN_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SENHA_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(perfilAcesso)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.PERFIL_ACESSO_VAZIO);
			return false;
		}

		return true;
	}

	public void cancelar() {
		limparCampos();
	}

	public void sair() {
		try {
			limparCampos();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(Util.getDateToString(dataNascimento));
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		getEndereco();
		getContato();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}

	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(nomeEndereco);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContato() {
		contato = new Contato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	private void limparCampos() {
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
		setTipoLogradouro(null);
		setNomeEndereco(null);
		setNumero(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setEstado(Constantes.INT_ZERO);
		setCidade(Constantes.INT_ZERO);
		setComboCidade(true);
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setIndexTab(Constantes.INT_ZERO);
	}

	private void carregarEstados() {
		listaEstados = new ArrayList<Estado>();
		try {
			listaEstados = new EstadoController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ESTADO_ERRO_PESQUISA);
		}
	}

	private void carregarPerfil() {
		listaPerfis = new ArrayList<String>();
		for (Perfil p : Perfil.values()) {
			listaPerfis.add(p.getDescricao());
		}
	}

	private void carregarTipoLogradouro() {
		listaTipoLogradouros = new ArrayList<String>();
		for (Logradouro l : Logradouro.values()) {
			listaTipoLogradouros.add(l.getDescricao());
		}
	}

	public void carregarCidades() {
		listaCidades = new ArrayList<Cidade>();
		try {
			if (estado > Constantes.INT_ZERO) {
				listaCidades = new CidadeController().buscarPorEstado(new Estado(estado));
				comboCidade = false;
			} else {
				comboCidade = true;
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

}
