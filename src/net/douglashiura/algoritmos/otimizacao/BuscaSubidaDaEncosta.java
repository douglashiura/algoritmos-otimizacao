package net.douglashiura.algoritmos.otimizacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class BuscaSubidaDaEncosta extends Otimizador {


	public BuscaSubidaDaEncosta(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		super(destino, voos, pessoas);
	}

	@Override
	public List<Agenda> executar() {
		List<Agenda> solucao = getSolucao();

		while (true) {
			List<List<Agenda>> vizinhos = copiarSolucoes(solucao.size() * 4, solucao);
			for (int i = 0; i < vizinhos.size(); i += 4) {
				trechoVizinhoIdaEsquerda(vizinhos.get(i).get(i % solucao.size()));
				trechoVizinhoIdaDireita(vizinhos.get(i + 1).get(i % solucao.size()));
				trechoVizinhoVoltaEsquerda(vizinhos.get(i + 2).get((i + 1) % solucao.size()));
				trechoVizinhoVoltaDireita(vizinhos.get(i + 3).get((i + 1) % solucao.size()));
			}
			Integer atual = custo(solucao);
			Integer melhor = atual;
			for (List<Agenda> umaSolucaoVizinha : vizinhos) {
				Integer custo = custo(umaSolucaoVizinha);
				if (custo < melhor) {
					melhor = custo;
					solucao = umaSolucaoVizinha;
				}
			}
			if (melhor == atual)
				break;
		}
		return solucao;
	}



	private List<List<Agenda>> copiarSolucoes(int copias, List<Agenda> solucao) {
		List<List<Agenda>> vizinhos = new ArrayList<List<Agenda>>(copias);
		for (int i = 0; i < copias; i++) {
			vizinhos.add(copiar(solucao));
		}
		return vizinhos;
	}

}
