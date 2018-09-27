import Classes.*;
import java.io.*;

public class Programa
{
	public static void main (String[] args)
	{
		try
		{

			Labirinto labirinto = new Labirinto();

			BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));

			String arq;

			System.out.println("Digite o nome do arquivo (com extensão): ");
			arq = teclado.readLine();


			labirinto.lerArquivo(arq);


			labirinto.solucionarLabirinto();


			if (labirinto.getAchouSaida())
			{
				System.out.println("Saida encontrada");
				System.out.println("");

				for (int i = 0; i < labirinto.getLinhas(); i++)
				{
					for (int j = 0; j < labirinto.getColunas(); j++)
					{
						System.out.print(labirinto.getLabirinto(i,j) + "");
					}
					System.out.println("");
				}
				System.out.println("");
			}

			System.out.println(labirinto.inverso());
		}
		catch (Exception erro)
		{
			erro.printStackTrace();
			System.err.println (erro);
		}
	}
}