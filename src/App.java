public class App {

    public static void main(String[] args){
        Cplx c1 = new CplxCart(1.5,2.4);
        Cplx c2 = new CplxPol(Math.PI/2,Math.PI/6);
        System.out.println(c1);
        c1.ajoute(c2);
        System.out.println(c1);
        c2.multiplication(c2);
        System.out.println(c2);

    }
}
