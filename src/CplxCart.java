public class CplxCart implements Cplx  {
	private double Reel;
	private double Im;
	
	public CplxCart() {
		this.Reel = 0;
		this.Im = 0;
	}
	
	public CplxCart(double Reel, double Im) {
		this.Reel = Reel;
		this.Im = Im;
	}
	
	public double getReel() {
		return Reel;
	}
	
	public double getIm() {
		return Im;
	}

	public void setReel(double Reel) {
		this.Reel = Reel;
	}
	
	public void setIm(double Im) {
		this.Im = Im;
	}

	public double getArgument() {
		return  Math.atan(this.Im/this.Reel);
	}

	public double getModule() {
		return Math.sqrt(Math.pow(this.Reel,2)+Math.pow(this.Im,2));
	}
	
	public void ajoute(Cplx complexe) {
		this.Reel += complexe.getReel();
		this.Im += complexe.getIm();
	}

	public void soustrait(Cplx complexe) {
		this.Reel -= complexe.getReel();
		this.Im -= complexe.getIm();
	}
	
	public void multiplication(Cplx complexe) {
		double rtemp = this.getReel() * complexe.getReel() - this.getIm() * complexe.getIm();
		this.Im=this.getReel()*complexe.getIm()+this.getIm()*complexe.getReel();
		this.Reel=rtemp;
	}

	public String toString(){
		return "["+this.getReel()+" ; "+getIm()+"]";
	}

}
