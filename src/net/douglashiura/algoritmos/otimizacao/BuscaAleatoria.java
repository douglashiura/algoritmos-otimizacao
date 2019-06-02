package net.douglashiura.algoritmos.otimizacao;

import java.util.List;
import java.util.Map;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class BuscaAleatoria extends Otimizador {

	public BuscaAleatoria(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		super(destino, voos, pessoas);
	}

	@Override
	public List<Agenda> executar() {
		List<Agenda> solucao = getSolucao();
		Integer melhorCusto = custo(solucao);
		for (int i = 0; i < 10000; i++) {
			List<Agenda> solucaoCandidata = criaSolucao();
			Integer custoCandidato = custo(solucaoCandidata);
			if (custoCandidato < melhorCusto) {
				solucao = solucaoCandidata;
				melhorCusto = custoCandidato;
			}
		}
		return solucao;
	}

}
