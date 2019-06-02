package teste.net.douglashiura.algoritmos.otimizacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.douglashiura.algoritmos.otimizacao.BuscaAleatoria;
import net.douglashiura.algoritmos.otimizacao.BuscaSubidaDaEncosta;
import net.douglashiura.algoritmos.otimizacao.Otimizador;
import net.douglashiura.algoritmos.otimizacao.TemperaSimulada;
import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Agenda;
import net.douglashiura.algoritmos.otimizacao.entidades.Pessoa;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class TesteOtimizandoVoos {

	private Map<Pessoa, Aeroporto> pessoas;
	private Aeroporto destino;
	private Map<Voo, List<Trecho>> voos;
	private Pessoa amanda;
	private Pessoa pedro;
	private Pessoa paulo;
	private Pessoa jessica;
	private Pessoa pricila;
	private Pessoa marcos;

	@BeforeEach
	public void setUp() throws IOException {
		amanda = new Pessoa("Amanda");
		pedro = new Pessoa("Pedro");
		marcos = new Pessoa("Marcos");
		pricila = new Pessoa("Pricila");
		jessica = new Pessoa("Jessica");
		paulo = new Pessoa("Paulo");
		pessoas = new HashMap<Pessoa, Aeroporto>();
		pessoas.put(amanda, new Aeroporto("CWB"));
		pessoas.put(pedro, new Aeroporto("GIG"));
		pessoas.put(marcos, new Aeroporto("POA"));
		pessoas.put(pricila, new Aeroporto("FLN"));
		pessoas.put(jessica, new Aeroporto("CNF"));
		pessoas.put(paulo, new Aeroporto("GYN"));
		destino = new Aeroporto("GRU");
		voos = Voos.get();
	}

	@Test
	void voosIguais() {
		assertEquals(new Voo(new Aeroporto("BR"), new Aeroporto("CH")),
				new Voo(new Aeroporto("BR"), new Aeroporto("CH")));
	}

	@Test
	void buscaAleatoria() throws Exception {
		Otimizador buscaAleatoria = new BuscaAleatoria(destino, voos, pessoas);
		List<Agenda> solucao = buscaAleatoria.executar();
		buscaAleatoria.imprimirSolucao();
		Agenda umaSolucao = solucao.get(0);
		Trecho ida = umaSolucao.getIda();
		Trecho volta = umaSolucao.getVolta();

		assertTrue(0 < buscaAleatoria.custo(solucao));
		assertEquals(6, solucao.size());
		assertEquals(destino, ida.getDestino());
		assertEquals(new Aeroporto("CWB"), ida.getOrigem());
		assertNotNull(ida.getSaida());
		assertNotNull(ida.getChegada());
		assertNotNull(ida.getValor());

		assertEquals(destino, volta.getOrigem());
		assertEquals(new Aeroporto("CWB"), volta.getDestino());
		assertNotNull(volta.getSaida());
		assertNotNull(volta.getChegada());
		assertNotNull(volta.getValor());

		assertEquals(amanda, solucao.get(0).getPessoa());
		assertEquals(jessica, solucao.get(1).getPessoa());
		assertEquals(marcos, solucao.get(2).getPessoa());
		assertEquals(paulo, solucao.get(3).getPessoa());
		assertEquals(pedro, solucao.get(4).getPessoa());
		assertEquals(pricila, solucao.get(5).getPessoa());
	}

	@Test
	void subida_encosta() throws Exception {
		Otimizador buscaAleatoria = new BuscaSubidaDaEncosta(destino, voos, pessoas);
		List<Agenda> solucao = buscaAleatoria.executar();
		buscaAleatoria.imprimirSolucao();
		Agenda umaSolucao = solucao.get(0);
		Trecho ida = umaSolucao.getIda();
		Trecho volta = umaSolucao.getVolta();

		assertTrue(0 < buscaAleatoria.custo(solucao));
		assertEquals(6, solucao.size());
		assertEquals(destino, ida.getDestino());
		assertEquals(new Aeroporto("CWB"), ida.getOrigem());
		assertNotNull(ida.getSaida());
		assertNotNull(ida.getChegada());
		assertNotNull(ida.getValor());

		assertEquals(destino, volta.getOrigem());
		assertEquals(new Aeroporto("CWB"), volta.getDestino());
		assertNotNull(volta.getSaida());
		assertNotNull(volta.getChegada());
		assertNotNull(volta.getValor());

		assertEquals(amanda, solucao.get(0).getPessoa());
		assertEquals(jessica, solucao.get(1).getPessoa());
		assertEquals(marcos, solucao.get(2).getPessoa());
		assertEquals(paulo, solucao.get(3).getPessoa());
		assertEquals(pedro, solucao.get(4).getPessoa());
		assertEquals(pricila, solucao.get(5).getPessoa());
	}
	
	
	
	
	@Test
	void temperaSimulada() throws Exception {
		Otimizador buscaAleatoria = new TemperaSimulada(destino, voos, pessoas);
		List<Agenda> solucao = buscaAleatoria.executar();
		buscaAleatoria.imprimirSolucao();
		Agenda umaSolucao = solucao.get(0);
		Trecho ida = umaSolucao.getIda();
		Trecho volta = umaSolucao.getVolta();

		assertTrue(0 < buscaAleatoria.custo(solucao));
		assertEquals(6, solucao.size());
		assertEquals(destino, ida.getDestino());
		assertEquals(new Aeroporto("CWB"), ida.getOrigem());
		assertNotNull(ida.getSaida());
		assertNotNull(ida.getChegada());
		assertNotNull(ida.getValor());

		assertEquals(destino, volta.getOrigem());
		assertEquals(new Aeroporto("CWB"), volta.getDestino());
		assertNotNull(volta.getSaida());
		assertNotNull(volta.getChegada());
		assertNotNull(volta.getValor());

		assertEquals(amanda, solucao.get(0).getPessoa());
		assertEquals(jessica, solucao.get(1).getPessoa());
		assertEquals(marcos, solucao.get(2).getPessoa());
		assertEquals(paulo, solucao.get(3).getPessoa());
		assertEquals(pedro, solucao.get(4).getPessoa());
		assertEquals(pricila, solucao.get(5).getPessoa());
	}
	
	
	@Test
	void genetico() throws Exception {
		Otimizador buscaAleatoria = new AlgoritmoGenetico(destino, voos, pessoas);
		List<Agenda> solucao = buscaAleatoria.executar();
		buscaAleatoria.imprimirSolucao();
		Agenda umaSolucao = solucao.get(0);
		Trecho ida = umaSolucao.getIda();
		Trecho volta = umaSolucao.getVolta();

		assertTrue(0 < buscaAleatoria.custo(solucao));
		assertEquals(6, solucao.size());
		assertEquals(destino, ida.getDestino());
		assertEquals(new Aeroporto("CWB"), ida.getOrigem());
		assertNotNull(ida.getSaida());
		assertNotNull(ida.getChegada());
		assertNotNull(ida.getValor());

		assertEquals(destino, volta.getOrigem());
		assertEquals(new Aeroporto("CWB"), volta.getDestino());
		assertNotNull(volta.getSaida());
		assertNotNull(volta.getChegada());
		assertNotNull(volta.getValor());

		assertEquals(amanda, solucao.get(0).getPessoa());
		assertEquals(jessica, solucao.get(1).getPessoa());
		assertEquals(marcos, solucao.get(2).getPessoa());
		assertEquals(paulo, solucao.get(3).getPessoa());
		assertEquals(pedro, solucao.get(4).getPessoa());
		assertEquals(pricila, solucao.get(5).getPessoa());
	}	
	
	
	
	

}