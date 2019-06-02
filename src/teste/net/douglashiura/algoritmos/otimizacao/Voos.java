package teste.net.douglashiura.algoritmos.otimizacao;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.douglashiura.algoritmos.otimizacao.entidades.Aeroporto;
import net.douglashiura.algoritmos.otimizacao.entidades.Trecho;
import net.douglashiura.algoritmos.otimizacao.entidades.Voo;

public class Voos {

	public static Map<Voo, List<Trecho>> get() throws IOException {
		Map<Voo, List<Trecho>> voos = new HashMap<>();
		InputStream path = TesteOtimizandoVoos.class.getResourceAsStream("voos.txt");
		byte[] conteudo = new byte[path.available()];
		path.read(conteudo);
		String texto = new String(conteudo);
		for (String linha : texto.split("\n")) {
			String[] colunas = linha.split(",");
			String origem = colunas[0];
			String destino = colunas[1];
			Voo voo = new Voo(new Aeroporto(origem), new Aeroporto(destino));
			voos.put(voo, new ArrayList<Trecho>());
		}
		for (String linha : texto.split("\n")) {
			String[] colunas = linha.split(",");
			String origem = colunas[0];
			String destino = colunas[1];
			LocalTime saida = hora(colunas[2]);
			LocalTime chegada = hora(colunas[3]);
			Integer preco = Integer.valueOf(colunas[4].trim());
			Voo voo = new Voo(new Aeroporto(origem), new Aeroporto(destino));
			voos.get(voo).add(new Trecho(voo, saida, chegada, preco));
		}
		path.close();
		return voos;
	}

	private static LocalTime hora(String coluna) {
		return LocalTime.of(Integer.valueOf(coluna.split(":")[0]), Integer.valueOf(coluna.split(":")[1]));
	}
}
