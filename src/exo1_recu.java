public class exo1_recu {
        public int a;

        public static void main(String[] args){
            exo1_recu test = new exo1_recu();
            Cplx[] complexe = new CplxPol[4];
            complexe=test.transformation();
            //test.afficheTableau(complexe);
            test.diviseTableauRecursif(complexe);

        }

        public Cplx[] transformation() {
            int tabModule[] = {4,5};
            int tabArgument[] = {0,0};
            Cplx resultat[]=new CplxPol[tabModule.length];

            Cplx[] complexe = new CplxPol[tabModule.length];

            for (int i = 0; i < complexe.length; i++) {
                complexe[i] = new CplxPol(tabModule[i], tabArgument[i]);
                //System.out.println(complexe[i].toString());
            }
            return complexe;
        }

        public Cplx[][] diviseTableau(Cplx[] complexe){

            Cplx[] complexeP = new CplxPol[(complexe.length)/2];
            int j=0;
            Cplx[] complexeI = new CplxPol[(complexe.length)/2];
            int k=0;

            for(int i=0; i<complexe.length;i++){
                if(i%2==0){
                    complexeP[j]=complexe[i];
                    j=j+1;
                }
                else{
                    complexeI[k]=complexe[i];
                    k=k+1;
                }
            }
            Cplx[][] complexeP_I = new Cplx[][] { complexeP, complexeI };
            return  complexeP_I;
        }

        public Cplx[] diviseTableauRecursif(Cplx[] complexe){

            Cplx[] complexeP;
            Cplx[] complexeI;
            Cplx[][] complexeP_I=diviseTableau(complexe);
            complexeP=complexeP_I[0];
            complexeI=complexeP_I[1];

            if(complexe.length>2){
                complexeP = diviseTableauRecursif(complexeP);
                complexeI = diviseTableauRecursif(complexeI);
            }

                //System.out.println("Partie argument: "+complexe[0].getArgument());
                afficheTableau(remonte(complexeP,complexeI));
                return remonte(complexeP,complexeI);
        }

        public Cplx[] remonte(Cplx[] tab1, Cplx[] tab2){

            Cplx tabS[]=new CplxPol[2*tab1.length];
            for(int i=0;i<=tab1.length-1;i++) { //partie +
                Cplx temptab1 = tab2[i];
                System.out.println("tab2: "+tab2[i].getModule());
                temptab1.ajoute(tab1[i]);
                tabS[i] = temptab1;
                System.out.println("!!!!"+tab1[i].getModule()+"oooo"+tab2[i].getModule()+"rrrr"+tabS[i].getModule());
            }
            for(int i=0;i<=tab2.length-1;i++){
                Cplx temptab2 = tab2[i];
                temptab2.soustrait(tab1[i]);
                tabS[i+tab2.length]=temptab2;
            }
            return tabS;

        }

        public void afficheTableau(Cplx[] complexe){
            System.out.println("fct afficheTableau");
            for(int i=0;i<complexe.length;i++){
                //System.out.println("complexe["+i+"]:"+complexe[i].toString());
                System.out.println("module: "+complexe[i].getModule());
            }
            System.out.println("fin fct afficheTableau");
        }


        /*for (int i = 0; i < complexe.length; i++) {
            Cplx temp1 = new CplxPol(0,0);
            temp1=omega(i,complexe.length,1);
            temp1.multiplication(somme(complexe,i)[1]);
            temp1.ajoute(somme(complexe,i)[0]);
            resultat[i]= temp1;
            //resultat[i] = somme(complexe,i)[0]+omega(i,complexe.length,1)*somme(complexe,i)[1];

        }
        for (int i = 0; i < complexe.length; i++) {
            System.out.println(resultat[i].toString());
        }*/

/*
    public Cplx omega(int puissance, int facteur,int k){
        Cplx complexe = new CplxPol(1, -2*Math.PI/(puissance*k/(facteur)));
        return complexe;
    }


    public Cplx[] somme(Cplx[] liste,int k) { //r cest i et k cest k ici
        Cplx sommepaire = new CplxPol(0,0);
        Cplx sommeimpaire = new CplxPol(0,0);
        Cplx tempimpaire = new CplxPol(0,0);
        Cplx temppaire = new CplxPol(0,0);
        for(int i = 0; i <= (liste.length/2)-1; i++) {

            //IMPAIRE
            tempimpaire=omega(i,liste.length,k);
            tempimpaire.multiplication(liste[2*i]);
            sommeimpaire.ajoute(tempimpaire);

            //Paire
            temppaire=omega(i,liste.length,k);
            temppaire.multiplication(liste[2*i+1]);
            sommepaire.ajoute(tempimpaire);
        }
        return new Cplx[]{sommepaire, sommeimpaire};
    }*/
}
