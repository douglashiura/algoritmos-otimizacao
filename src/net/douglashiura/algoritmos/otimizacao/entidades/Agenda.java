package net.douglashiura.algoritmos.otimizacao.entidades;

public class Agenda {

	private Pessoa pessoa;
	private Aeroporto origem;
	private Trecho ida;
	private Trecho volta;
	private Aeroporto destino;

	public Agenda(Pessoa pessoa, Aeroporto origem, Aeroporto destino) {
		this.pessoa = pessoa;
		this.origem = origem;
		this.destino = destino;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public Trecho getIda() {
		return ida;
	}

	public Trecho getVolta() {
		return volta;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

	public void setIda(Trecho trecho) {
		this.ida = trecho;
	}

	public void setVolta(Trecho volta) {
		this.volta = volta;
	}

	public Aeroporto getDestino() {
		return destino;
	}

}
