public class Labirinto
{
	protected int linhas = 0, colunas = 0;
	protected boolean achouSaida = false, progredindo = true;
	protected char labirinto[][];
	protected Pilha<Coordenada> caminho;
	protected Pilha<Fila<Coordenada>> possibilidades;
	protected Coordenada atual = null;
	protected Fila<Coordenada> fila = new Fila<Coordenada> (3);


	public void inverso()
	{
		Pilha<Coordenada> inverso = new Pilha<Coordenada>(linhas*colunas);
		while (!this.caminho.isVazia())
		{
			inverso.guarde(caminho.getUmItem());
			this.caminho.jogueForaUmItem();
		}

		while(!inverso.isVazia())
		{
			System.out.println(inverso.getUmItem());
			inverso.jogueForaUmItem();
	    }
	}

	public void solucionarLabirinto()
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

	private void localizar()
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


	public int hashCode()
	{
		int ret = 4;

		ret = ret*7 + new Integer(this.linhas).hashCode();
		ret = ret*7 + new Integer(this.colunas).hashCode();
		ret = ret*7 + new Integer(this.labirinto).hashCode();

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
			ret = new Coordenada (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}

	public String toString()
	{
		for (int i = 0; i < linhas; i++)
		{
			for (int j = 0; j < colunas; j++)
			{
				System.out.print(labirinto[i][j] + "");
			}
			System.out.println("");
		}
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