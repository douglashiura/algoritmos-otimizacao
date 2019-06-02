package net.douglashiura.algoritmos.otimizacao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public abstract class Otimizador {

	private Aeroporto destino;
	private Map<Voo, List<Trecho>> voos;
	private List<Agenda> solucoes;
	private Map<Pessoa, Aeroporto> pessoas;

	public Otimizador(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		this.destino = destino;
		this.voos = voos;
		this.pessoas = pessoas;
		this.solucoes = criaSolucao();
	}

	protected List<Agenda> criaSolucao() {
		List<Agenda> solucao = new ArrayList<Agenda>(pessoas.size());
		for (Pessoa pessoa : pessoas.keySet()) {
			solucao.add(new Agenda(pessoa, pessoas.get(pessoa), destino));
		}
		solucao.sort((a, b) -> a.getPessoa().getNome().compareTo(b.getPessoa().getNome()));
		preencheComTrechoAleatorio(solucao);
		return solucao;
	}

	protected List<Agenda> getSolucao() {
		return solucoes;
	}

	public abstract List<Agenda> executar();

	protected void preencheComTrechoAleatorio(List<Agenda> solucao) {
		for (Agenda agenda : solucao) {
			Voo ida = new Voo(agenda.getOrigem(), destino);
			Voo volta = new Voo(destino, agenda.getOrigem());
			agenda.setIda(voos.get(ida).get(new Random().nextInt(voos.get(ida).size())));
			agenda.setVolta(voos.get(volta).get(new Random().nextInt(voos.get(volta).size())));
		}
	}

	public Integer custo(List<Agenda> solucoes) {
		Integer precoTotal = 0;
		Integer ultimaChegada = 0;
		Integer primeiraPartida = 1439;
		for (Agenda agenda : solucoes) {
			precoTotal += agenda.getIda().getValor();
			precoTotal += agenda.getVolta().getValor();
			if (ultimaChegada < getMinutos(agenda.getIda().getChegada())) {
				ultimaChegada = getMinutos(agenda.getIda().getChegada());
			}
			if (primeiraPartida > getMinutos(agenda.getVolta().getSaida())) {
				primeiraPartida = getMinutos(agenda.getVolta().getSaida());
			}
		}

		int totalEspera = 0;
		for (Agenda agenda : solucoes) {
			totalEspera += ultimaChegada - getMinutos(agenda.getIda().getChegada());
			totalEspera += getMinutos(agenda.getVolta().getSaida()) - primeiraPartida;
		}
		if (ultimaChegada > primeiraPartida)
			precoTotal += 50;

		return precoTotal + totalEspera;
	}

	private Integer getMinutos(LocalTime hora) {
		return hora.getHour() * 60 + hora.getMinute();
	}

	public void imprimirSolucao() {
		List<Agenda> solucao = executar();
		System.out.println(custo(solucao));
		for (Agenda agenda : solucao) {
			System.out.printf("%10s%10s %5s-%5s R$%3s %5s-%5s R$%3s\n", agenda.getPessoa(), agenda.getOrigem(),
					agenda.getIda().getSaida(), agenda.getIda().getChegada(), agenda.getIda().getValor(),
					agenda.getVolta().getSaida(), agenda.getVolta().getChegada(), agenda.getVolta().getValor());
		}
	}

	protected List<Agenda> copiar(List<Agenda> solucao) {
		List<Agenda> copias = new ArrayList<>(solucao.size());
		for (Agenda agenda : solucao) {
			Agenda agendaNova = new Agenda(agenda.getPessoa(), agenda.getOrigem(), agenda.getDestino());
			agendaNova.setIda(agenda.getIda());
			agendaNova.setVolta(agenda.getVolta());
			copias.add(agendaNova);
		}
		return copias;
	}

	protected void trechoVizinhoVoltaDireita(Agenda agenda) {
		Voo voo = agenda.getIda().getVoo();
		int index = voos.get(voo).indexOf(agenda.getIda());
		if (index < (voos.get(voo).size() - 2)) {
			agenda.setIda(voos.get(voo).get(index + 1));
		}

	}

	protected void trechoVizinhoIdaDireita(Agenda agenda) {
		Voo voo = agenda.getVolta().getVoo();
		int index = voos.get(voo).indexOf(agenda.getVolta());
		if (index < (voos.get(voo).size() - 2)) {
			agenda.setVolta(voos.get(voo).get(index + 1));
		}
	}

	protected void trechoVizinhoVoltaEsquerda(Agenda agenda) {
		Voo voo = agenda.getVolta().getVoo();
		int index = voos.get(voo).indexOf(agenda.getVolta());
		if (index > 0) {
			agenda.setVolta(voos.get(voo).get(index - 1));
		}
	}

	protected void trechoVizinhoIdaEsquerda(Agenda agenda) {
		Voo voo = agenda.getIda().getVoo();
		int index = voos.get(voo).indexOf(agenda.getIda());
		if (index > 0) {
			agenda.setIda(voos.get(voo).get(index - 1));
		}
	}
	
}
