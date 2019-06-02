package net.douglashiura.algoritmos.otimizacao.entidades;

public class Aeroporto {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Aeroporto other = (Aeroporto) obj;
		return sigla.equals(other.sigla);
	}

	private String sigla;

	public Aeroporto(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return sigla;
	}

}
