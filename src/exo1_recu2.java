public class exo1_recu2 {


    public static void main(String[] args) {
        exo1_recu2 test = new exo1_recu2();
        Cplx[] complexe ;
        complexe = test.transformation();
        //test.afficheTableau(complexe);
        test.FFT(complexe);

    }

    public Cplx[] transformation() {
        int tabModule[] = {0,1,2,3,4,5,6,7};
        int tabArgument[] = {0,0,0,0,0,0,0,0};

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
        Cplx complexeP_I[][] = new Cplx[][] { complexeP, complexeI };
        return  complexeP_I;
    }



    public void afficheTableau(Cplx[] complexe){
        System.out.println("fct afficheTableau");
        for(int i=0;i<complexe.length;i++){
            //System.out.println("complexe["+i+"]:"+complexe[i].toString());
            System.out.println("module: "+complexe[i].getModule());
        }
        System.out.println("fin fct afficheTableau");
    }



    public Cplx[] FFT(Cplx[] S){

        if (S.length<=1){
            return S;
        }
        else{
            Cplx[] P0=new Cplx[S.length/2];
            Cplx[] P1=new Cplx[S.length/2];
            Cplx M=new CplxPol(0,0);
            Cplx complexeP_I[][]=diviseTableau(S);
            P0=FFT(complexeP_I[0]);//pair
            P1=FFT(complexeP_I[1]);//impaire
            for(int k=0;k<=(S.length/2)-1;k++){
                M=omega(k,S.length);//Faire Omega
                System.out.println("M:"+M);
                Cplx temp1=P1[k];
                temp1.multiplication(M);
                temp1.ajoute(P0[k]);
                S[k]=temp1;
                //S[k]=P0[k]+P1[k]*M;

                /*Cplx temp2=P1[k];
                temp2.multiplication(M);
                temp2.soustrait(P0[k]);*/

                Cplx temp2=P0[k];
                Cplx temp3=P1[k];
                temp3.multiplication(M);
                temp2.soustrait(temp3);

                S[k+(S.length)/2]=temp2;
                //S[k+S.length/2]=P0[k]-P1[k]*M;
            }
            afficheTableau(S);
            return S;
        }


    }

    public Cplx omega(int k,int L){
        Cplx complexe;
        complexe = new CplxPol(1, -2*Math.PI*k/L);
        System.out.println("k"+k);
        return complexe;
    }

}

