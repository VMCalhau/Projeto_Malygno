import java.io.*;

public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			int linhas = 0, colunas = 0;
			char labirinto[][];

			BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));

			String arq;

			System.out.println("Digite o nome do arquivo (sem extensão): ");
			arq = teclado.readLine();

			arq += ".txt";

			/*
			if (arq.substring(arq.length()-4, 4) != ".txt")
			{
				arq += ".txt";
			}
			else {}
			*/

			BufferedReader arquivo = new BufferedReader (new FileReader (arq));

			for (String linha = arquivo.readLine(); linha != null; linha = arquivo.readLine())
			{
				if (linhas == 0)
					linhas = Integer.parseInt(linha);
				else if (colunas == 0)
					colunas = Integer.parseInt(linha);

     		}

     		labirinto = new char[linhas][colunas];

		}
		catch (Exception erro)
		{
			System.err.println (erro.getMessage());
		}
	}
}