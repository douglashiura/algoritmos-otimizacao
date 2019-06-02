package net.douglashiura.algoritmos.otimizacao.entidades;

public class Voo {

	private Aeroporto origem;
	private Aeroporto destino;

	public Voo(Aeroporto origem, Aeroporto destino) {
		this.origem = origem;
		this.destino = destino;
	}

	public Aeroporto getDestino() {
		return destino;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Voo other = (Voo) obj;
		return origem.equals(other.origem) && destino.equals(other.destino);
	}

}
