import java.io.*;

public class Programa
{
	public static void main (String[] args)
	{
		try
		{
			int linhas = 0, colunas = 0;
			boolean achouSaida = false;
			boolean progredindo = true;
			char labirinto[][];
			Pilha<Coordenada> caminho;
			Pilha<Fila<Coordenada>> possibilidades;
			Coordenada atual = null;
			Fila<Coordenada> fila = new Fila<Coordenada> (3);

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
				if (progredindo)
				{
					if (atual.getY() + 1 < linhas)
						if (labirinto[atual.getY()+1][atual.getX()] == ' ' || labirinto[atual.getY()+1][atual.getX()] == 'S')
						{
							fila.guarde (new Coordenada (atual.getY()+1, atual.getX()));
						}

					if (atual.getY() - 1 >= 0)
						if (labirinto[atual.getY()-1][atual.getX()] == ' ' || labirinto[atual.getY()-1][atual.getX()] == 'S')
						{
							fila.guarde (new Coordenada (atual.getY()-1, atual.getX()));
						}

					if (atual.getX() + 1 < colunas)
						if (labirinto[atual.getY()][atual.getX()+1] == ' ' || labirinto[atual.getY()][atual.getX()+1] == 'S')
						{
							fila.guarde (new Coordenada (atual.getY(), atual.getX()+1));
						}

					if (atual.getX() - 1 >= 0)
						if (labirinto[atual.getY()][atual.getX()-1] == ' ' || labirinto[atual.getY()][atual.getX()-1] == 'S')
						{
							fila.guarde (new Coordenada(atual.getY(), atual.getX()-1));
						}
				}


				//7
				if (!fila.isVazia())
				{
					progredindo = true;
					atual = fila.getUmItem();
					fila.jogueForaUmItem();
					caminho.guarde(atual);

					if (labirinto[atual.getY()][atual.getX()] == 'S')
						achouSaida = true;

					labirinto[atual.getY()][atual.getX()] = '*';

					possibilidades.guarde(fila);
					fila = new Fila<Coordenada> (3);
				}
				else
				{
					progredindo = false;
					atual = caminho.getUmItem();
					caminho.jogueForaUmItem();
				}

			}

			if (achouSaida)
				System.out.println("Saida encontrada");

		}
		catch (Exception erro)
		{
			erro.printStackTrace();
			System.err.println (erro);
		}
	}
}