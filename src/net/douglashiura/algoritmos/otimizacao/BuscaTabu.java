package net.douglashiura.algoritmos.otimizacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class BuscaTabu extends Otimizador {

	private Integer maximoDeInteracoes;
	private List<List<Agenda>> tabuList;

	public BuscaTabu(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		super(destino, voos, pessoas);
		maximoDeInteracoes = 10;
		tabuList = new LinkedList<>();
	}

	@Override
	public List<Agenda> executar() {
		Solucao melhorSolucao = new Solucao();
		melhorSolucao.solucao = criaSolucao();
		melhorSolucao.custo = custo(melhorSolucao.solucao);
		melhorSolucao.vizinhos = Arrays.asList(criaSolucao(), criaSolucao(), criaSolucao());
		Solucao solucaoAtual = melhorSolucao;

		Integer currentIteration = 0;
		while (!deveParar(++currentIteration)) {
			List<List<Agenda>> solucaoComVizinhosCandidatos = solucaoAtual.vizinhos;
			List<List<Agenda>> solutionsInTabu = new ArrayList<>(tabuList);
			List<Agenda> melhorVizinho = encontreOMenhorVizinho(solucaoComVizinhosCandidatos, solutionsInTabu);
			if (custo(melhorVizinho) < melhorSolucao.custo) {
				melhorSolucao = new Solucao();
				melhorSolucao.solucao = melhorVizinho;
				melhorSolucao.custo = custo(melhorSolucao.solucao);
			}
			tabuList.add(solucaoAtual.solucao);
			solucaoAtual = new Solucao();
			solucaoAtual.solucao = melhorVizinho;
			solucaoAtual.custo = custo(melhorSolucao.solucao);
			solucaoAtual.vizinhos = Arrays.asList(criaSolucao(), criaSolucao(), criaSolucao());
			tabuList.add(melhorSolucao.solucao);
		}
		return melhorSolucao.solucao;
	}

	private List<Agenda> encontreOMenhorVizinho(List<List<Agenda>> solucaoComVizinhosCandidatos,
			List<List<Agenda>> solutionsNoTabu) {
		List<List<Agenda>> candidatos = solucaoComVizinhosCandidatos.stream()
				.filter(solution -> !solutionsNoTabu.contains(solution)).collect(Collectors.toList());
		Collections.sort(candidatos, (a, b) -> custo(a).compareTo(custo(b)));
		return candidatos.get(0);
	}

	private boolean deveParar(Integer interacao) {
		return interacao >= maximoDeInteracoes;
	}

	class Solucao {
		List<Agenda> solucao;
		Integer custo;
		List<List<Agenda>> vizinhos;
	}

}
