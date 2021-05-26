package br.com.foursys.locadora.backing;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.util.JSFUtil;


/**
 * 
 * @author Renato Sampaio
 * @since 09/05/2021
 * @version 1.0
 */

@ManagedBean(name = "relFaturamentoBacking")
@ViewScoped
public class RelFaturamentoBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	// Define um atributo do tipo lineModel onde podemos carregar
	// um gráfico representado por linhas.
	private LineChartModel lineModel;

	private Locacao locacao;
	private ArrayList<Locacao> listaLocacoes;

	private double valorJaneiro;
	private double valorFevereiro;
	private double valorMarco;
	private double valorAbril;
	private double valorMaio;
	private double valorJunho;
	private double valorJulho;
	private double valorAgosto;
	private double valorSetembro;
	private double valorOutubro;
	private double valorNovembro;
	private double valorDezembro;

	 private int anoPesquisa;

	// Define um modelo que irá armazenar as linhas do gráfico
	// e a série de dados (pontos x,y).
	LineChartModel model = new LineChartModel();

	// Define uma série de dados (pontos x,y).
	LineChartSeries serie1 = new LineChartSeries();

	public int getAnoPesquisa() {
		return anoPesquisa;
	}

	public void setAnoPesquisa(int anoPesquisa) {
		this.anoPesquisa = anoPesquisa;
	}

	public double getValorJaneiro() {
		return valorJaneiro;
	}

	public void setValorJaneiro(double valorJaneiro) {
		this.valorJaneiro = valorJaneiro;
	}

	public double getValorFevereiro() {
		return valorFevereiro;
	}

	public void setValorFevereiro(double valorFevereiro) {
		this.valorFevereiro = valorFevereiro;
	}

	public double getValorMarco() {
		return valorMarco;
	}

	public void setValorMarco(double valorMarco) {
		this.valorMarco = valorMarco;
	}

	public double getValorAbril() {
		return valorAbril;
	}

	public void setValorAbril(double valorAbril) {
		this.valorAbril = valorAbril;
	}

	public double getValorMaio() {
		return valorMaio;
	}

	public void setValorMaio(double valorMaio) {
		this.valorMaio = valorMaio;
	}

	public double getValorJunho() {
		return valorJunho;
	}

	public void setValorJunho(double valorJunho) {
		this.valorJunho = valorJunho;
	}

	public double getValorJulho() {
		return valorJulho;
	}

	public void setValorJulho(double valorJulho) {
		this.valorJulho = valorJulho;
	}

	public double getValorAgosto() {
		return valorAgosto;
	}

	public void setValorAgosto(double valorAgosto) {
		this.valorAgosto = valorAgosto;
	}

	public double getValorSetembro() {
		return valorSetembro;
	}

	public void setValorSetembro(double valorSetembro) {
		this.valorSetembro = valorSetembro;
	}

	public double getValorOutubro() {
		return valorOutubro;
	}

	public void setValorOutubro(double valorOutubro) {
		this.valorOutubro = valorOutubro;
	}

	public double getValorNovembro() {
		return valorNovembro;
	}

	public void setValorNovembro(double valorNovembro) {
		this.valorNovembro = valorNovembro;
	}

	public double getValorDezembro() {
		return valorDezembro;
	}

	public void setValorDezembro(double valorDezembro) {
		this.valorDezembro = valorDezembro;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	// Através deste método get, o gráfico será exibido.
	public LineChartModel getLineModel() {
		return lineModel;
	}

	// Executa este método após o Bean ser instanciado.
	public RelFaturamentoBacking() {
		criarLinhasModelo();
		setAnoPesquisa(0);
	}

	// Insere os dados dentro das cordenadas do modelo (a série de dados).
	private LineChartModel iniciarModeloLinear() {

		// Define um rótulo na legenda para a série de dados 1.
		serie1.setLabel(getAnoPesquisa() + "");

		serie1.getData().put(1, valorJaneiro);
		serie1.getData().put(2, valorFevereiro);
		serie1.getData().put(3, valorMarco);
		serie1.getData().put(4, valorAbril);
		serie1.getData().put(5, valorMaio);
		serie1.getData().put(6, valorJunho);
		serie1.getData().put(7, valorJulho);
		serie1.getData().put(8, valorAgosto);
		serie1.getData().put(9, valorSetembro);
		serie1.getData().put(10, valorOutubro);
		serie1.getData().put(11, valorNovembro);
		serie1.getData().put(12, valorDezembro);

		// Insere a série 1 dentro do modelo.
		model.addSeries(serie1);

		// Retorna o modelo com os dados para o atributo lineModel que por sua
		// vez retorna para a tela através de seu método get.
		return model;

	}

	// Método onde se cria as coordenadas do gráfico e carrega o modelo,
	// também configura as cores, eixo x, y, etc.
	public void criarLinhasModelo() {

		double max;

		if ((getValorJaneiro() > getValorFevereiro()) && (getValorJaneiro() > getValorMarco())
				&& (getValorJaneiro() > getValorAbril()) && (getValorJaneiro() > getValorMaio())
				&& (getValorJaneiro() > getValorJunho()) && (getValorJaneiro() > getValorJulho())
				&& (getValorJaneiro() > getValorAgosto()) && (getValorJaneiro() > getValorSetembro())
				&& (getValorJaneiro() > getValorOutubro()) && (getValorJaneiro() > getValorNovembro())
				&& (getValorJaneiro() > getValorDezembro())) {
			max = getValorJaneiro();

		} else if ((getValorFevereiro() > getValorMarco()) && (getValorFevereiro() > getValorAbril())
				&& (getValorFevereiro() > getValorMaio()) && (getValorFevereiro() > getValorJunho())
				&& (getValorFevereiro() > getValorJulho()) && (getValorFevereiro() > getValorAgosto())
				&& (getValorFevereiro() > getValorSetembro()) && (getValorFevereiro() > getValorOutubro())
				&& (getValorFevereiro() > getValorNovembro()) && (getValorFevereiro() > getValorDezembro())) {
			max = getValorFevereiro();

		} else if ((getValorMarco() > getValorAbril()) && (getValorMarco() > getValorMaio())
				&& (getValorMarco() > getValorJunho()) && (getValorMarco() > getValorJulho())
				&& (getValorMarco() > getValorAgosto()) && (getValorMarco() > getValorSetembro())
				&& (getValorMarco() > getValorOutubro()) && (getValorMarco() > getValorNovembro())
				&& (getValorMarco() > getValorDezembro())) {
			max = getValorMarco();

		} else if ((getValorAbril() > getValorMaio()) && (getValorAbril() > getValorJunho())
				&& (getValorAbril() > getValorJulho()) && (getValorAbril() > getValorAgosto())
				&& (getValorAbril() > getValorSetembro()) && (getValorAbril() > getValorOutubro())
				&& (getValorAbril() > getValorNovembro()) && (getValorAbril() > getValorDezembro())) {
			max = getValorAbril();

		} else if ((getValorMaio() > getValorJunho()) && (getValorMaio() > getValorJulho())
				&& (getValorMaio() > getValorAgosto()) && (getValorMaio() > getValorSetembro())
				&& (getValorMaio() > getValorOutubro()) && (getValorMaio() > getValorNovembro())
				&& (getValorMaio() > getValorDezembro())) {
			max = getValorMaio();

		} else if ((getValorJunho() > getValorJulho()) && (getValorJunho() > getValorAgosto())
				&& (getValorJunho() > getValorSetembro()) && (getValorJunho() > getValorOutubro())
				&& (getValorJunho() > getValorNovembro()) && (getValorJunho() > getValorDezembro())) {
			max = getValorJunho();

		} else if ((getValorJulho() > getValorAgosto()) && (getValorJulho() > getValorSetembro())
				&& (getValorJulho() > getValorOutubro()) && (getValorJulho() > getValorNovembro())
				&& (getValorJulho() > getValorDezembro())) {
			max = getValorJulho();

		} else if ((getValorAgosto() > getValorSetembro()) && (getValorAgosto() > getValorOutubro())
				&& (getValorAgosto() > getValorNovembro()) && (getValorAgosto() > getValorDezembro())) {
			max = getValorAgosto();

		} else if ((getValorSetembro() > getValorOutubro()) && (getValorSetembro() > getValorNovembro())
				&& (getValorSetembro() > getValorDezembro())) {
			max = getValorSetembro();

		} else if ((getValorOutubro() > getValorNovembro()) && (getValorOutubro() > getValorDezembro())) {
			max = getValorOutubro();

		} else if ((getValorNovembro() > getValorDezembro())) {
			max = getValorNovembro();
		} else {
			max = getValorDezembro();
		}
		lineModel = iniciarModeloLinear();

		lineModel.setTitle("Faturamento - Anual"); 
		lineModel.setLegendPosition("n");

		Axis yAxis = lineModel.getAxis(AxisType.Y); 
		yAxis.setMin(0); 
		yAxis.setMax(max + 10);
		yAxis.setTickFormat("%d");
		yAxis.setLabel("Reais (R$)"); 

		Axis xAxis = lineModel.getAxis(AxisType.X); 
		xAxis.setMin(0); 
		xAxis.setMax(12);
		xAxis.setTickFormat("%d");
		xAxis.setLabel("Meses"); 

	}

	public void pesquisar() {
		boolean naoExiste = false;
		for (Locacao locacao : new LocacaoController().buscarTodos()) {
			String[] data = locacao.getDataLocacao().split("/");
			int ano = Integer.parseInt(data[2]);
			if (getAnoPesquisa() == ano) {
				limpaGrafico();
				getValorMes();
			} else {
				limpaGrafico();
				naoExiste = true;
			}
		}
		criarLinhasModelo();
		if (naoExiste) {
			JSFUtil.addErrorMessage("Relatório de faturamento", "Ano não encontrado ");
		}

	}

	private void limpaGrafico() {
		setValorJaneiro(0.0);
		setValorFevereiro(0.0);
		setValorMarco(0.0);
		setValorAbril(0.0);
		setValorMaio(0.0);
		setValorJunho(0.0);
		setValorJulho(0.0);
		setValorAgosto(0.0);
		setValorSetembro(0.0);
		setValorOutubro(0.0);
		setValorNovembro(0.0);
		setValorDezembro(0.0);
		model.clear();
	}

	private void getValorMes() {
		ArrayList<Integer> meses = new ArrayList<Integer>();
		for (Locacao locacao : new LocacaoController().buscarTodos()) {

			String[] data = locacao.getDataLocacao().split("/");
			int mes = Integer.parseInt(data[1]);

			meses.add(mes);

			switch (mes) {
			case 1:
				valorJaneiro += locacao.getValor();
				break;
			case 2:
				valorFevereiro += locacao.getValor();
				break;
			case 3:
				valorMarco += locacao.getValor();
				break;
			case 4:
				valorAbril += locacao.getValor();
				break;
			case 5:
				valorMaio += locacao.getValor();
				break;
			case 6:
				valorJunho += locacao.getValor();
				break;
			case 7:
				valorJulho += locacao.getValor();
				break;
			case 8:
				valorAgosto += locacao.getValor();
				break;
			case 9:
				valorSetembro += locacao.getValor();
				break;
			case 10:
				valorOutubro += locacao.getValor();
				break;
			case 11:
				valorNovembro += locacao.getValor();
				break;
			case 12:
				valorDezembro += locacao.getValor();
				break;
			default:
				break;
			}
		}

	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
