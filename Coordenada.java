public class Coordenada implements Cloneable
{
	private int x, y = 0;

	public Coordenada(int Y, int X) throws Exception
	{
		if (X < 0 || Y < 0)
			throw new Exception("Coordenada invalida!");

		this.x = X;
		this.y = Y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}



	public void setX(int X) throws Exception
    {

		if(X < 0)
		{
			throw new Exception("Coordenada invalida!");
		}

		this.x = X;
	}

	public void setY(int Y) throws Exception
	{
	     if(Y < 0)
		{
			throw new Exception("Coordenada invalida");
		}
		this.y = Y;
	}


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

	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	public int hashCode()
	{
		int ret = 4;

		ret = ret*7 + new Integer(this.x).hashCode();
		ret = ret*7 + new Integer(this.y).hashCode();

		return ret;
	}

	public Coordenada (Coordenada modelo) throws Exception
	{
		if (modelo == null)
			throw new Exception("Modelo ausente!");

		this.x = modelo.x;
		this.y = modelo.y;
	}

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