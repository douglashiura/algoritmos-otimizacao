package net.douglashiura.algoritmos.otimizacao;

public class Custo {
	int custo = 0;

	int custo(int[][] sudoku) {
		int soma = 0;
		for (int lin = 0; lin < 9; lin++) {
			int custo[] = new int[9];
			for (int col = 0; col < 9; col++) {
				if (custo[sudoku[lin][col] - 1] != 0)
					soma++;
				custo[sudoku[lin][col] - 1] = 1;
			}
		}
		for (int lin = 0; lin < 9; lin++) {
			int custo[] = new int[9];
			for (int col = 0; col < 9; col++) {
				if (custo[sudoku[col][lin] - 1] != 0)
					soma++;
				custo[sudoku[col][lin] - 1] = 1;
			}
		}
		return soma;
	}

}
