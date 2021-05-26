package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.util.Constantes;
import br.com.foursys.locadora.util.Util;

@ManagedBean(name = "detClienteBacking")
@ViewScoped
public class DetClienteBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
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

	public DetClienteBacking() {
		carregarTela(ListClienteBacking.clienteSelecionado);
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

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregarTela(Cliente cliente) {
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		rg = cliente.getRg();

		try {
			dataNascimento = Util.getStringToDate(cliente.getDataNascimento());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		idade = cliente.getIdade() + Constantes.STRING_VAZIO;
		sexo = cliente.getSexo();
		tipoLogradouro = cliente.getEnderecoIdEndereco().getTipoLogradouro();
		nomeEndereco = cliente.getEnderecoIdEndereco().getEndereco();
		numero = cliente.getEnderecoIdEndereco().getNumero() + Constantes.STRING_VAZIO;
		complemento = cliente.getEnderecoIdEndereco().getComplemento();
		bairro = cliente.getEnderecoIdEndereco().getBairro();
		cep = cliente.getEnderecoIdEndereco().getCep();
		estado = cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = cliente.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = cliente.getContatoIdContato().getTelefone();
		celular = cliente.getContatoIdContato().getCelular();
		email = cliente.getContatoIdContato().getEmail();
	}

}
