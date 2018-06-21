/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lilian.cariou
 */
public class Complexe {
     private double Im;
    private double Re;
    private double module;
    private double arg;
    

    
    public Complexe(double re,double im) {               
                this.Im = im;
                this.Re= re;
                setMod();
                
              
    }
    public Complexe(double arg)
    {
        this.Re = (double) Math.cos(arg);
        this.Im = (double) Math.sin(arg);
        setMod();
    }

    public double getRe() {return this.Re;}
    public double getIm() {return this.Im;}
 
    public double module() {return module;}
    public double arg() {return arg;}

    
    public void setIm(double im) {
        this.Im = im;
    }
    public void setRe(double re) {
        this.Re = re;
    }
    
    public void setMod()
    {
        this.module=Math.sqrt(Math.pow(this.getRe() , 2) + Math.pow(this.getIm() , 2));
    }
    public void setArg(double Arg)
    {
        this.arg = Arg;
    }
        
    
    
    public Complexe ajoute(Complexe complexe2)
	{
		return new Complexe(this.getRe()+complexe2.getRe(),this.getIm()+complexe2.getIm());
        }
    public Complexe soustrait(Complexe complexe2)
	{
		return new Complexe(this.getRe()-complexe2.getRe(),this.getIm()-complexe2.getIm());
	}
    
    
    public Complexe multiplie(Complexe complexe2)
    {
        return new Complexe(this.getRe()*complexe2.getRe()-this.getIm()*complexe2.getIm(),this.getRe()*complexe2.getIm()+complexe2.getRe()*this.getIm());
    }
    
    
    public Complexe conjuguer()
        {
            return new Complexe(this.module,-(this.arg));
        }
    public String toString()
    {
        return "(" + this.getRe() + " , " + this.getIm() + ")";
    }
}

