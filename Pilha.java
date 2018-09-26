package Classes;
import java.lang.reflect.*;

public class Pilha<X> implements Cloneable
{
    private Object[] vetor;
    private int qtd = 0;

    //versao preventiva
    public Pilha (int capacidade) throws Exception
    {
        if (capacidade<0)
            throw new Exception ("Capacidade invalida");

        this.vetor = new Object [capacidade];
    }

    private X meuCloneDeX (X x)
    {
		X ret=null;

		try
		{
			Class<?> classe = x.getClass();
			Class<?>[] tiposDosParametrosFormais = null; // null pq clone tem 0 parametros
			Method metodo=classe.getMethod("clone",tiposDosParametrosFormais);
			Object[] parametrosReais = null; // null pq clone tem 0 parametros
			ret=(X)metodo.invoke(x, parametrosReais);
		}
		catch (NoSuchMethodException erro)
		{}
		catch (IllegalAccessException erro)
		{}
		catch (InvocationTargetException erro)
		{}
		return ret;
	}

    public void guarde (X s) throws Exception
    {
		if (s==null)
				throw new Exception ("Informacao ausente");

		if (this.isCheia())
				throw new Exception ("Pilha cheia");

        if (s instanceof Cloneable)
	        this.vetor[this.qtd] = meuCloneDeX(s);
	    else
	        this.vetor[this.qtd] = s;

        this.qtd++;
    }

    public X getUmItem () throws Exception
    {
        if (this.isVazia())
            throw new Exception ("Nada a recuperar");

        if (this.vetor[this.qtd-1] instanceof Cloneable)
            return meuCloneDeX ((X)this.vetor[this.qtd-1]);

        return (X)this.vetor[this.qtd-1];

    }

    public void jogueForaUmItem () throws Exception
    {
	if (this.isVazia())
	{    Exception problema;
	     problema = new Exception ("Pilha vazia");
	     throw problema;
	}

        this.qtd--;
        this.vetor[this.qtd]=null;
    }

    public boolean isCheia ()
    {
        return this.qtd==this.vetor.length;
    }

    public boolean isVazia ()
    {
        return this.qtd==0;
    }

    public String toString ()
    {
		if (this.qtd==0)
		    return "Vazia";

		return this.qtd+" elementos, sendo o ultimo "+this.vetor[this.qtd-1];
	}

	//compara this e obj
	public boolean equals (Object obj)
	{
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		Pilha<X> pil = (Pilha<X>)obj;

		if (this.qtd!=pil.qtd)
		    return false;

		for (int i=0; i<this.qtd; i++)
		    if (!this.vetor[i].equals(pil.vetor[i]))
		        return false;

		return true;
	}

	public int hashCode ()
	{
		int ret=666; // so nao pode ser zero

		ret = ret*2 + new Integer(this.qtd).hashCode();

        for (int i=0; i<this.qtd; i++)
          //if (this.vetor[i]!=null)
				ret = ret*2 + this.vetor[i].hashCode();

		return ret;
	}

	//construtor de copia
	public Pilha (Pilha modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");

		this.qtd = modelo.qtd;

		this.vetor = new Object [modelo.vetor.length];

		for (int i=0; i<=modelo.qtd; i++)
		    this.vetor[i] = modelo.vetor[i];
	}

	public Object clone ()
	{
		Pilha<X> ret=null;

		try
		{
			ret = new Pilha<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}