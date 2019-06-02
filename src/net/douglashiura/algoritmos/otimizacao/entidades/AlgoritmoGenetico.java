package net.douglashiura.algoritmos.otimizacao.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.douglashiura.algoritmos.otimizacao.Otimizador;

public class AlgoritmoGenetico extends Otimizador {

	private int tamanhoPopulacao;
	private float probabilidadeMutacao;
	private float elitismo;
	private int numeroGeracoes;

	public AlgoritmoGenetico(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		super(destino, voos, pessoas);
		tamanhoPopulacao = 100;

		probabilidadeMutacao = 0.2f;
		elitismo = 0.2f;
		numeroGeracoes = 500;
	}

	@Override
	public List<Agenda> executar() {

		List<List<Agenda>> populacao = new ArrayList<List<Agenda>>();
		for (int i = 0; i < tamanhoPopulacao; i++) {
			populacao.add(criaSolucao());
		}
		int numeroElitismo = (int) (elitismo * tamanhoPopulacao);

		for (int i = 0; i < numeroGeracoes; i++) {
			populacao.sort((a, b) -> {
				return Integer.compare(custo(a), custo(b));
			});
			populacao = populacao.subList(0, numeroElitismo);
			while (populacao.size() < tamanhoPopulacao) {
				if (new Random().nextFloat() < probabilidadeMutacao) {
					int m = new Random().nextInt(numeroElitismo);
					populacao.add(mutacao(populacao.get(m)));
				} else {
					int c1 = new Random().nextInt(numeroElitismo);
					int c2 = new Random().nextInt(numeroElitismo);
					populacao.add(cruzamento(populacao.get(c1), populacao.get(c2)));
				}
			}
		}
		populacao.sort((a, b) -> {
			return Integer.compare(custo(a), custo(b));
		});
		return populacao.get(0);
	}

	private List<Agenda> mutacao(List<Agenda> solucao) {
		List<Agenda> mutante = copiar(solucao);
		int pessoa = new Random().nextInt(solucao.size());
		List<Agenda> solucaoTemporaria = copiar(solucao);

		if (new Random().nextBoolean()) {
			if (new Random().nextBoolean()) {
				trechoVizinhoIdaDireita(solucaoTemporaria.get(pessoa));
			} else {
				trechoVizinhoIdaEsquerda(solucaoTemporaria.get(pessoa));
			}
		} else {
			if (new Random().nextBoolean()) {
				trechoVizinhoVoltaDireita(solucaoTemporaria.get(pessoa));
			} else {
				trechoVizinhoVoltaEsquerda(solucaoTemporaria.get(pessoa));
			}
		}

		return mutante;
	}

	private List<Agenda> cruzamento(List<Agenda> solucao1, List<Agenda> solucao2) {
		int corte = new Random().nextInt(solucao1.size());
		List<Agenda> cruzada = new ArrayList<Agenda>(solucao1.size());
		cruzada.addAll(solucao1.subList(0, corte));
		cruzada.addAll(solucao2.subList(corte, solucao2.size()));
		return cruzada;
	}

}
