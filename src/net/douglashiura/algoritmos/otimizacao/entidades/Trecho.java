package net.douglashiura.algoritmos.otimizacao.entidades;

import java.time.LocalTime;

public class Trecho {

	private LocalTime saida;
	private LocalTime chegada;
	private Integer valor;
	private Voo voo;

	public Trecho(Voo voo, LocalTime saida, LocalTime chegada, Integer preco) {
		this.voo = voo;
		this.saida = saida;
		this.chegada = chegada;
		valor = preco;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public LocalTime getChegada() {
		return chegada;
	}

	public Integer getValor() {
		return valor;
	}

	public Aeroporto getDestino() {
		return voo.getDestino();
	}

	public Aeroporto getOrigem() {
		return voo.getOrigem();
	}

	public Voo getVoo() {
		return voo;
	}

}
