package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import br.com.foursys.locadora.dao.CidadeDAO;
import br.com.foursys.locadora.dao.EstadoDAO;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.PerfilAcesso;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe respons�vel por controlar os componentes do front-end Funcionario
 * 
 * @author Renato Sampaio
 * @since 27/04/2021
 * @version 1.0
 */
@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// atributos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private String dataNascimento;
	private String sexo;
	private String logradouro;
	private String enderecoNome;
	private String numero;
	private String complemento;
	private String bairro;
	private int estado;
	private int cidade;
	private String cep;
	private String telefone;
	private String celular;
	private String email;
	private String idade;
	private String login;
	private String senha;
	private String perfilAcesso;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Funcionario funcionarioSelecionado;
	private boolean comboCidade = true;
	
	// atributos auxiliares
	private Funcionario funcionario;
	private Endereco enderecoFinal;
	private Contato contato;
	
	
	private boolean alterar = false;
	
	private String pesquisaNome = "";

	private ArrayList<Funcionario> listaFuncionarios;
	private ArrayList<String> listaLogradouro;
	private ArrayList<String> listaPerfil;
	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;

	public FuncionarioBacking() {
		carregarPerfil();
		carregarEstado();
		carregarLogradouro();
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

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEnderecoNome() {
		return enderecoNome;
	}

	public void setEnderecoNome(String enderecoNome) {
		this.enderecoNome = enderecoNome;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
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
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public ArrayList<String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(ArrayList<String> listaPerfil) {
		this.listaPerfil = listaPerfil;
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
	

	public ArrayList<String> getListaLogradouro() {
		return listaLogradouro;
	}

	public void setListaLogradouro(ArrayList<String> listaLogradouro) {
		this.listaLogradouro = listaLogradouro;
	}

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void cadastrar() {

		if (validar()) {
			funcionario = new Funcionario();
			enderecoFinal = new Endereco();
			contato = new Contato();
			
			enderecoFinal = getEndereco();
			funcionario = getFuncionario();
			contato = getContato();
			
			new EnderecoController().salvar(enderecoFinal);
			new ContatoController().salvar(contato);
			
			try {
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage("", "Funcionário gravado com sucesso!");
			} catch (Exception e) {
				JSFUtil.addErrorMessage("", "Erro ao cadastrar o Funcionário!");
			}

		}

	}
	
	public ArrayList<Funcionario> preencherTabela() {
		return new FuncionarioController().buscarTodos();
	}
	
	public String pesquisar() {

		try {
			listaFuncionarios = new FuncionarioController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}

		return "";

	}

	public ArrayList<Funcionario> pesquisarFuncionario() {
		return new FuncionarioController().buscarPorNome(pesquisaNome);
	}

		private boolean validar() {
			
			if (Valida.isEmptyOrNull(nome)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
				return false;
			}
			
			if (Valida.isEmptyOrNull(sexo)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
				return false;
			}

			if (cpf.length() < 9) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
				return false;
			}

			if (rg.length() < 9) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
				return false;
			} else if (rg.length() < 9) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, "Favor usar apenas Numeros");
				return false;
			}
			
			if (dataNascimento.length() < 4) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
				return false;
			}

			if (!Valida.isInteger(idade)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
				return false;
			} else if (Valida.isIntZero(Integer.parseInt(idade))) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, "Favor usar apenas Numeros");
				return false;
			}

			if (Valida.isEmptyOrNull(logradouro)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
				return false;
			}

			if (Valida.isEmptyOrNull(enderecoNome)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
				return false;
			}
			
			if (!Valida.isInteger(numero)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_LOGRADOURO_VAZIO);
				return false;
			} else if (Valida.isIntZero(Integer.parseInt(numero))) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_LOGRADOURO_VAZIO);
				return false;
			}

			if (Valida.isEmptyOrNull(bairro)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.BAIRRO_VAZIO);
				return false;
			}
			
			if (Valida.isComboInvalida(estado)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ESTADO_VAZIO);
				return false;
			}
			
			if (Valida.isComboInvalida(cidade)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_VAZIO);
				return false;
			}

			if (cep.length() < 2) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
				return false;
			} else if (cep.length() < 2) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
				return false;
			}

			if (celular.length() < 2) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO,"Informe um celular");
				return false;
			} else if (celular.length() < 2) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO,"Informe um celular");
				return false;
			}
			
			if (Valida.isEmptyOrNull(email)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, "Informe um e-mail");
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
	/*
	 * m�todo que captura a a��o do bot�o CANCELAR na tela cad-filme.jsp
	 */
	public void cancelar() {
		limparCampos();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cad-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();
		dataNascimento = funcionarioSelecionado.getDataNascimento();
		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstado();
		carregarCidadesAlterar();
		logradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoNome = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estado = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void alterarFuncionario() {

			if (validar()) {
				try {
					getFuncionarioAlterar();
					new EnderecoController().salvar(enderecoFinal);
					new ContatoController().salvar(contato);
					limparCampos();
					JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
				} catch (Exception e) {
					JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
				}
			}

	}
	
	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		funcionario.setEnderecoIdEndereco(enderecoFinal);
		funcionario.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		enderecoFinal = funcionario.getEnderecoIdEndereco();
		enderecoFinal.setTipoLogradouro(logradouro);
		enderecoFinal.setEndereco(enderecoNome);
		enderecoFinal.setNumero(Integer.parseInt(numero));
		enderecoFinal.setComplemento(complemento);
		enderecoFinal.setBairro(bairro);
		enderecoFinal.setCep(cep);
		enderecoFinal.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = funcionario.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	public String excluir() {
		new FuncionarioController().excluir(funcionarioSelecionado);
		new EnderecoController().excluir(funcionarioSelecionado.getEnderecoIdEndereco());
		new ContatoController().excluir(funcionarioSelecionado.getContatoIdContato());
		
		listaFuncionarios = new FuncionarioController().buscarTodos();
		for (Funcionario funcionarios : listaFuncionarios) {
			if (funcionarios.getIdFuncionario().equals(funcionarioSelecionado.getIdFuncionario())) {
				JSFUtil.addInfoMessage("Erro Excluir", "");
				return "";
			} 
		}
		
		JSFUtil.addInfoMessage("Excluido com Sucesso", "");
		pesquisar();
		return "";
	}
	
	/*
	 * m�todo que captura a a��o do bot�o SAIR na tela cad-filme.jsp
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para retornar um objeto Filme
	 */
	private Funcionario getFuncionario() {

		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		
		
	return funcionario;
		
	}
	
	private Contato getContato() {
		contato = new Contato();
		
		contato.setEmail(email);
		contato.setCelular(celular);
		contato.setTelefone(telefone);
		
		funcionario.setContatoIdContato(contato);
		
		return contato;
		
	}
	
	private Endereco getEndereco() {
		
		enderecoFinal.setTipoLogradouro(logradouro);
		enderecoFinal.setEndereco(enderecoNome);
		enderecoFinal.setComplemento(complemento);
		enderecoFinal.setBairro(bairro);
		enderecoFinal.setCep(cep);
		enderecoFinal.setNumero(Integer.parseInt(numero));
		enderecoFinal.setCidadeIdCidade(new Cidade(cidade));
		
		funcionario.setEnderecoIdEndereco(enderecoFinal);
		
		return enderecoFinal;
		
	}

	/*
	 * m�todo para abrir a tela de cadastro
	 */
	public String cadastroFuncionario() {
		return "";
	}

	/*
	 * M�todo para limpar campos
	 */
	private void limparCampos() {
		setNome(null);
		setSexo(null);
		setCpf(null);
		setRg(null);
		setIdade(null);
		setDataNascimento(null);
		setLogradouro(null);
		setEnderecoNome(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setNumero(null);
		setEmail(null);
		setTelefone(null);
		setCelular(null);
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
		setCidade(0);
		setEstado(0);
	}

	private void carregarEstado() {
		try {
			listaEstados = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarCidade() {
		try {
			listaCidades = new CidadeDAO().buscarPorEstado(new Estado(estado));
		} catch (Exception e) {
		}
	}

	/*
	 * Método para carregar a lista de gênero
	 */
	public void carregarPerfil() {

		listaPerfil = new ArrayList<String>();

		for (PerfilAcesso p : PerfilAcesso.values()) {
			listaPerfil.add(p.getDescricao());
		}
	}
	/*
	 * Método para carregar a lista de gênero
	 */
	public void carregarLogradouro() {

		listaLogradouro = new ArrayList<String>();

		for (Logradouro l : Logradouro.values()) {
			listaLogradouro.add(l.getDescricao());
		}
	}
	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, "Erro pesquisar cidade");
		}
	}
	

}