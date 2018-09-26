package Classes;
/**
Esta classe tem a função de armazenar coordenadas.
*/
public class Coordenada implements Cloneable
{
	protected int x, y = 0;

	/**
	Construtor da classe
	@param Y posição no eixo das coordenadas
	@param X posição no eixo das abscissas
	*/
	public Coordenada(int Y, int X) throws Exception
	{
		if (X < 0 || Y < 0)
			throw new Exception("Coordenada invalida!");

		this.x = X;
		this.y = Y;
	}

	/**
	Retorna o valor do atributo x
	*/
	public int getX()
	{
		return this.x;
	}

	/**
	Retorna o valor do atributo y
	*/
	public int getY()
	{
		return this.y;
	}

	/**
	Define o valor do atributo x
	@param x novo valor do atributo x
	*/
	public void setX(int X) throws Exception
    {

		if(X < 0)
		{
			throw new Exception("Coordenada invalida!");
		}

		this.x = X;
	}

	/**
		Define o valor do atributo y
		@param y novo valor do atributo y
	*/
	public void setY(int Y) throws Exception
	{
	     if(Y < 0)
		{
			throw new Exception("Coordenada invalida");
		}
		this.y = Y;
	}

	/**
	Compara dois objetos
	@param co objeto a ser comparado com o objeto que chamou o método
	*/
	public boolean equals(Object co)
	{
		if (this == co)
			return true;

		if (co == null)
			return false;

		if (this.getClass() != co.getClass())
			return false;

		Coordenada c = (Coordenada) co;

		if (this.x != c.x)
			return false;

		if (this.y != c.y)
			return false;

		return true;
	}

	/**
	Retorna uma String que representa o objeto
	*/
	public String toString()
	{
		return "(" + this.y + "," + this.x + ")";
	}

	/**
	Retorna o valor do hash code do objeto
	*/
	public int hashCode()
	{
		int ret = 4;

		ret = ret*7 + new Integer(this.x).hashCode();
		ret = ret*7 + new Integer(this.y).hashCode();

		return ret;
	}

	/**
	Faz uma cópia de todos os atributos da classe passada como parâmetro
	@param modelo classe a ter seus atributos copiados
	*/
	public Coordenada (Coordenada modelo) throws Exception
	{
		if (modelo == null)
			throw new Exception("Modelo ausente!");

		this.x = modelo.x;
		this.y = modelo.y;
	}

	/**
	Cria e retorna uma cópia do objeto
	*/
	public Object clone()
	{
		Coordenada ret = null;
		try
		{
			ret = new Coordenada (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}