import java.lang.*;
public class exo1 {


    public static void main(String[] args){
        exo1 test = new exo1();
        test.transformation();
    }

    public void transformation() {
        int tabModule[] = {1,1,1,1};
        int tabArgument[] = {1,2,3,4};
        Cplx resultat[]=new CplxPol[4];

        Cplx[] complexe = new CplxPol[4];

        for (int i = 0; i < complexe.length; i++) {
            complexe[i] = new CplxPol(tabModule[i], tabArgument[i]);
            System.out.println(complexe[i].toString());
        }

        for (int i=0; i < complexe.length; i++) {
            Cplx temp1 = new CplxPol(0,0);
            temp1=omega(i,complexe.length,1);
            System.out.println("temp1: "+temp1);

            //sors en -infinity argu
            temp1.multiplication(somme(complexe,i)[1]);
            System.out.println("temp2: "+temp1);

            temp1.ajoute(somme(complexe,i)[0]);
            System.out.println("temp3: "+temp1);

            System.out.println("\n\n");


            resultat[i]= temp1;
            //resultat[i] = somme(complexe,i)[0]+omega(i,complexe.length,1)*somme(complexe,i)[1];

        }
        for (int i = 0; i < complexe.length; i++) {
            //System.out.println(resultat[i].toString());
        }
    }

    public Cplx omega(int puissance, int facteur,int k){
        Cplx complexe;
        if(puissance==0 || k==0){
            complexe = new CplxPol(1, 0);
        }
        else {
            complexe = new CplxPol(1, -2 * Math.PI / ((float) (puissance * k) / (facteur)));
        }
        /*float div=((puissance*k)/(facteur));
        System.out.println("div= "+div);
        System.out.println((float)(puissance*k)/facteur);

        System.out.println("ok"+(float)((-2*Math.PI)/div));
        System.out.println("fin"+((puissance*k)));
        System.out.println("facteur"+facteur);*/


        return complexe;
    }


    public Cplx[] somme(Cplx[] liste,int k) { //r cest i et k cest k ici
        Cplx sommepaire = new CplxPol(0,0);
        Cplx sommeimpaire = new CplxPol(0,0);
        Cplx tempimpaire;
        Cplx temppaire;
        for(int i = 0; i <= (liste.length/2)-1; i++) {

            //IMPAIRE
            tempimpaire=omega(i,liste.length/2,k);
            tempimpaire.multiplication(liste[2*i]);
            System.out.println("tempimpaire: "+tempimpaire);
            sommeimpaire.ajoute(tempimpaire);

            //Paire
            temppaire=omega(i,liste.length/2,k);
            temppaire.multiplication(liste[2*i+1]);
            sommepaire.ajoute(tempimpaire);
        }
        return new Cplx[]{sommepaire, sommeimpaire};
    }
}