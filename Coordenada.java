public class Coordenada
{
	private int x = 0, y = 0;

	public Coordenada(int X, int Y)
	{
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

	public boolean equals(Coordenada co)
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
}