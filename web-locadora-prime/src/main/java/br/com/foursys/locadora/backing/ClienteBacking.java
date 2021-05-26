package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.dao.CidadeDAO;
import br.com.foursys.locadora.dao.EstadoDAO;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Programa para
 * 
 * @author Renato Sampaio
 * @since 30/04/2021
 */
@ManagedBean(name = "clienteBacking")
@SessionScoped
public class ClienteBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private int estado;
	private int cidade;
	private Cliente cliente;
	private Endereco endereco;
	private Contato contato;
	private String nomePesquisar;
	private Cliente clienteSelecionado;

	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaPerfil;
	private ArrayList<String> listaLogradouro;
	private ArrayList<Cliente> listaClientes;

	private boolean comboCidade = true;
	private int indexTab;

	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String telefone;
	private String celular;
	private String email;
	private String tipoLogradouro;
	private String enderecoNome;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	public ClienteBacking() {
		carregarEstados();
		carregarLogradouros();
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
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

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getEnderecoNome() {
		return enderecoNome;
	}

	public void setEnderecoNome(String enderecoNome) {
		this.enderecoNome = enderecoNome;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<String> getListaLogradouro() {
		return listaLogradouro;
	}

	public void setListaLogradouro(ArrayList<String> listaLogradouro) {
		this.listaLogradouro = listaLogradouro;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(ArrayList<String> listaPerfil) {
		this.listaPerfil = listaPerfil;
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

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
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

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public ArrayList<Estado> getListaEstado() {
		return listaEstados;
	}

	public void setListaEstado(ArrayList<Estado> listaEstado) {
		this.listaEstados = listaEstado;
	}

	private void carregarEstados() {
		try {
			listaEstados = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregarCidades() {
		try {
			listaCidades = new CidadeDAO().buscarPorEstado(new Estado(estado));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancelar() {
		limparTela();
	}

	public void sair() {
		try {
			limparTela();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void limparTela() {
		// dados basicos
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		// dados de endereco
		setTipoLogradouro(null);
		setEnderecoNome(null);
		setNumero(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setEstado(0);
		setCidade(0);
		setComboCidade(true);
		// dados de contato
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setListaClientes(null);
		setNomePesquisar(null);
		setIndexTab(0);
	}

	public void pesquisar() {
		try {
			listaClientes = new ClienteController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISA);
		}
	}

	public void alterar() throws ParseException {
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(clienteSelecionado.getDataNascimento()).getTime());

		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		carregarEstados();
		carregarCidadesAlterar();
		tipoLogradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoNome = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estado = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void excluir() {
		try {
			new ClienteController().excluir(clienteSelecionado);
			new EnderecoController().excluir(clienteSelecionado.getEnderecoIdEndereco());
			new ContatoController().excluir(clienteSelecionado.getContatoIdContato());
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIDO);
		}
	}

	/*
	 * método para carregar a listas
	 */

	public void carregarLogradouros() {
		listaLogradouro = new ArrayList<String>();
		for (Logradouro l : Logradouro.values()) {
			listaLogradouro.add(l.getDescricao());
		}
	}

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void cadastrar() {
		if (validar()) {

			try {
				getCliente();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				new ClienteController().salvar(cliente);
				limparTela();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}

	private void getCliente() {
		cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setDataNascimento(getDateToString());
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);
		getEndereco();
		getContato();
		cliente.setEnderecoIdEndereco(endereco);
		cliente.setContatoIdContato(contato);

	}

	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderecoNome);
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

	private void getClienteAlterar() {
		cliente = clienteSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		cliente.setEnderecoIdEndereco(endereco);
		cliente.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		endereco = cliente.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderecoNome);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = cliente.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	private boolean validar() {
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CPF_VAZIO);
			return false;
		}
		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}
		if (rg.length() < 9) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.RG_VAZIO);
			return false;
		} else if (rg.length() < 9) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, "Favor usar apenas Numeros");
			return false;
		}
		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE,"Favor usar apenas Numeros");
			return false;
		}
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.SEXO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(enderecoNome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE,Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(bairro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.BAIRRO_VAZIO);
			return false;
		}
		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE,"Favor usar apenas Numeros");
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(numero))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, "Numero Inválido, favor informar apenas Numeros");
			return false;
		}
		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE,"Favor inserir um E-mail");
			return false;
		}
		return true;
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataNascimento);
	}

	public void alterarCliente() {
		if (validar()) {
			try {
				getClienteAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				limparTela();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}
	}

	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, "Erro ao pesquisar cidade");
		}
	}

}
