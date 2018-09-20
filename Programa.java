import java.io.*;

public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			int linhas = 0, colunas = 0;
			boolean achouSaida = false;
			char labirinto[][];
			Pilha<Coordenada> caminho;
			Pilha<Fila<Coordenada>> possibilidades;
			Coordenada atual = null;
			Fila<Coordenada> fila;

			BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));

			String arq;

			System.out.println("Digite o nome do arquivo (com extensão): ");
			arq = teclado.readLine();


			BufferedReader arquivo = new BufferedReader (new FileReader (arq));


			linhas = Integer.parseInt(arquivo.readLine());
			colunas = Integer.parseInt(arquivo.readLine());

     		labirinto = new char[linhas][colunas];

     		String linha;
     		String[] caracteres;

     		for (int i=0; i<linhas; i++)
     		{
				linha = arquivo.readLine();
				caracteres = linha.split("");

				for (int j=0; j<colunas; j++)
				{
					labirinto[i][j] = caracteres[j].charAt(0);
					if (caracteres[j].equals("E"))
						atual = new Coordenada (i,j);
				}

			}

			/*
			for (int i = 0; i < linhas; i++)
			{
				for (int j = 0; j < colunas; j++)
				{
					System.out.print(labirinto[i][j] + "");
				}
				System.out.println("");
			}
			*/

			caminho = new Pilha<Coordenada> (linhas*colunas);
			possibilidades = new Pilha<Fila<Coordenada>> (linhas*colunas);

			while(!achouSaida)
			{

				fila = new Fila<Coordenada> (3);

				if (atual.getX() < colunas - 1)
					if (labirinto[atual.getX()+1][atual.getY()] == ' ' || labirinto[atual.getX()+1][atual.getY()] == 'S')
					{
						fila.guarde (new Coordenada (atual.getX()+1, atual.getY()));
						labirinto[atual.getX()+1][atual.getY()] = '*';
					}

				if (atual.getX() > 0)
					if (labirinto[atual.getX()-1][atual.getY()] == ' ' || labirinto[atual.getX()-1][atual.getY()] == 'S')
					{
						fila.guarde (new Coordenada (atual.getX()-1, atual.getY()));
						labirinto[atual.getX()-1][atual.getY()] = '*';
					}

				if (atual.getY() < linhas - 1)
					if (labirinto[atual.getX()][atual.getY()+1] == ' ' || labirinto[atual.getX()][atual.getY()+1] == 'S')
					{
						fila.guarde (new Coordenada (atual.getX(), atual.getY()+1));
						labirinto[atual.getX()][atual.getY()+1] = '*';
					}

				if (atual.getY() > 0)
					if (labirinto[atual.getX()][atual.getY()-1] == ' ' || labirinto[atual.getX()][atual.getY()-1] == 'S')
					{
						fila.guarde (new Coordenada(atual.getX(), atual.getY()-1));
						labirinto[atual.getX()][atual.getY()-1] = '*';
					}

				if (labirinto[atual.getX()][atual.getY()] == 'S')
					achouSaida = true;


				// colo0cra loop
				//7
				atual = fila.getUmItem();
				fila.jogueForaUmItem();
				//8

				//9
				caminho.guarde(new Coordenada(atual.getX(),atual.getY()));
				//10
				possibilidades.guarde(fila);
			}

		}
		catch (Exception erro)
		{
			erro.printStackTrace();
			System.err.println (erro);
		}
	}
}