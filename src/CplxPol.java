public class CplxPol implements Cplx {
	private double module;
	private double argument;
	
	public CplxPol() {
		this.module = 0;
		this.argument = 0;
	}
	
	public CplxPol(double module, double argument) {
		this.module = module;
		this.argument = argument;
	}
	
	public double getModule() {
		return module;
	}
	
	public double getArgument() {
		return argument;
	}
	
	public void setModule(float module) {
		this.module = module;
	}
	
	public void setArgument(float argument) {
		this.argument = argument;
	}
	
	public double getReel() {
		return module*Math.cos(argument);	
	}
	
	public double getIm() {
		return module*Math.sin(argument);
	}

	public void ajoute(Cplx complexe) {
		Cplx moi = new CplxCart(this.getReel(),this.getIm());
		moi.ajoute(complexe);
		this.module = moi.getModule();
		this.argument = moi.getArgument();
	}

    public void soustrait(Cplx complexe) {
        Cplx moi = new CplxCart(this.getReel(),this.getIm());
        moi.soustrait(complexe);
        this.module = moi.getModule();
        this.argument = moi.getArgument();
    }

	
	public void multiplication(Cplx complexe) {
		this.module*= complexe.getModule();
		this.argument += complexe.getArgument();
	}

	public String toString(){
		return "Complexe polaire : \n"+ "module :" +this.getModule()+"\t partie argument: "+getArgument();
	}
}

