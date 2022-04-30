package net.douglashiura.algoritmos.otimizacao.entidades;

import java.time.LocalTime;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(chegada, saida, valor, voo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trecho other = (Trecho) obj;
		return Objects.equals(chegada, other.chegada) && Objects.equals(saida, other.saida)
				&& Objects.equals(valor, other.valor) && Objects.equals(voo, other.voo);
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
