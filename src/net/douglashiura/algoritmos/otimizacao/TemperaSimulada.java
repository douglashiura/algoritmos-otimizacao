package net.douglashiura.algoritmos.otimizacao;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class TemperaSimulada extends Otimizador {
	private float temperatura;
	private float resfriamento;

	public TemperaSimulada(Aeroporto destino, Map<Voo, List<Trecho>> voos, Map<Pessoa, Aeroporto> pessoas) {
		super(destino, voos, pessoas);
		temperatura = 10000.0f;
		resfriamento = 0.95f;
	}

	@Override
	public List<Agenda> executar() {
		List<Agenda> solucao = getSolucao();

		while (temperatura > 0.1) {
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

			Integer custoSolucao = custo(solucao);
			Integer custoSolucaoTemporaria = custo(solucaoTemporaria);
			double probabilidade = Math.pow(Math.E, (-custoSolucaoTemporaria - custoSolucao) / temperatura);

			if (custoSolucaoTemporaria < custoSolucao || new Random().nextDouble() < probabilidade) {
				solucao = solucaoTemporaria;
			}
			temperatura = temperatura * resfriamento;
		}
		return solucao;

	}

}
