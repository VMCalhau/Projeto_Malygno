//package Classes;
import Classes.*;
import java.io.*;

public class Labirinto
{
	protected int linhas = 0, colunas = 0;
	protected boolean achouSaida = false, progredindo = true;
	protected char labirinto[][];
	protected Pilha<Coordenada> caminho;
	protected Pilha<Fila<Coordenada>> possibilidades;
	protected Coordenada atual = null;
	protected Fila<Coordenada> fila;


	public char getLabirinto(int linha, int coluna)
	{
		return this.labirinto[linha][coluna];
	}

	public boolean getProgredindo()
	{
		return this.progredindo;
	}

	public boolean getAchouSaida()
	{
		return this.achouSaida;
	}

	public int getLinhas()
	{
		return this.linhas;
	}

	public int getColunas()
	{
		return this.colunas;
	}

	public Labirinto() throws Exception
	{
		try
		{
			fila = new Fila<Coordenada> (3);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public void lerArquivo(String arq) throws Exception
	{
		try
		{
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

			caminho = new Pilha<Coordenada> (linhas*colunas);
			possibilidades = new Pilha<Fila<Coordenada>> (linhas*colunas);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public String inverso() throws Exception
	{
		String ret = "";

		try
		{
			Pilha<Coordenada> inverso = new Pilha<Coordenada>(linhas*colunas);
			while (!this.caminho.isVazia())
			{
				inverso.guarde(caminho.getUmItem());
				this.caminho.jogueForaUmItem();
			}

			while(!inverso.isVazia())
			{
				ret += inverso.getUmItem() + " ";
				inverso.jogueForaUmItem();
			}

			return ret;
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		//return ret;
	}

	public void solucionarLabirinto() throws Exception
	{
		try
		{
			while(!this.achouSaida)
			{
				if (this.progredindo)
					this.localizar();


				if (!this.fila.isVazia())
				{
					this.progredindo = true;
					this.atual = this.fila.getUmItem();
					this.fila.jogueForaUmItem();
					this.caminho.guarde(this.atual);

					if (this.labirinto[this.atual.getY()][this.atual.getX()] == 'S')
						this.achouSaida = true;

					if (this.labirinto[this.atual.getY()][this.atual.getX()] != 'S')
						this.labirinto[this.atual.getY()][this.atual.getX()] = '*';

					this.possibilidades.guarde(this.fila);
					this.fila = new Fila<Coordenada> (3);
				}
				else
				{
					this.progredindo = false;
					this.atual = this.caminho.getUmItem();
					this.caminho.jogueForaUmItem();
					this.labirinto[this.atual.getY()][this.atual.getX()] = ' ';

					this.fila = this.possibilidades.getUmItem();
					this.possibilidades.jogueForaUmItem();
				}

			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	private void localizar() throws Exception
	{
		try
		{
			if (this.atual.getY() + 1 < this.linhas)
				if (this.labirinto[this.atual.getY()+1][this.atual.getX()] == ' ' || this.labirinto[this.atual.getY()+1][this.atual.getX()] == 'S')
					this.fila.guarde (new Coordenada (this.atual.getY()+1, this.atual.getX()));

			if (this.atual.getY() - 1 >= 0)
				if (this.labirinto[this.atual.getY()-1][this.atual.getX()] == ' ' || this.labirinto[this.atual.getY()-1][this.atual.getX()] == 'S')
					this.fila.guarde (new Coordenada (this.atual.getY()-1, this.atual.getX()));

			if (this.atual.getX() + 1 < this.colunas)
				if (this.labirinto[this.atual.getY()][this.atual.getX()+1] == ' ' || this.labirinto[this.atual.getY()][this.atual.getX()+1] == 'S')
					this.fila.guarde (new Coordenada (this.atual.getY(), this.atual.getX()+1));

			if (this.atual.getX() - 1 >= 0)
				if (this.labirinto[this.atual.getY()][this.atual.getX()-1] == ' ' || this.labirinto[this.atual.getY()][this.atual.getX()-1] == 'S')
				    this.fila.guarde (new Coordenada(this.atual.getY(), this.atual.getX()-1));
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

	}


	public int hashCode()
	{
		int ret = 4;

		ret = ret*7 + new Integer(this.linhas).hashCode();
		ret = ret*7 + new Integer(this.colunas).hashCode();
		for (int i = 0; i < linhas; i++)
			for (int j = 0; j < colunas; j++)
				ret = ret*7 + new Integer(this.labirinto[i][j]).hashCode();

		return ret;
	}

	public Labirinto (Labirinto modelo) throws Exception
	{
		if (modelo == null)
			throw new Exception("Modelo ausente!");

		this.linhas = modelo.linhas;
		this.colunas = modelo.colunas;
		this.labirinto = modelo.labirinto;
	}

	public Object clone()
	{
		Labirinto ret = null;
		try
		{
			ret = new Labirinto (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}

	public String toString()
	{
		String ret = "";

		for (int i = 0; i < linhas; i++)
		{
			for (int j = 0; j < colunas; j++)
			{
				ret += labirinto[i][j] + "";
			}

			ret += "\n";
		}

		return ret;
	}

		public boolean equals(Object la)
		{
			if (this == la)
				return true;

			if (la == null)
				return false;

			if (this.getClass() != la.getClass())
				return false;

			Labirinto l = (Labirinto) la;

			if (this.linhas != l.linhas)
				return false;

			if (this.colunas != l.colunas)
				return false;

			if (this.labirinto != l.labirinto)
				return false;

			return true;
	}
}