public class exo1_recu2 {

    public static void main(String[] args) {
        exo1_recu2 test = new exo1_recu2();
        Cplx[] complexe = transformation();
        complexe = signal(8);
        //complexe[1] = new CplxCart(1,0);
        test.afficheTableau(complexe);
        //test.afficheTableau(complexe);
        test.FFT(complexe);
    }

    public static Cplx[] signal(int n)
    {
        Cplx[] data = new CplxCart[n];
        for (int i = 0; i < n; ++i)
            data[i] = new CplxCart(Math.sin(2.*Math.PI*i/n), 0);
        return data;
    }
    public static Cplx[] transformation() {
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
            System.out.println(new CplxCart(complexe[i].getReel(), complexe[i].getIm()));
        }
        System.out.println("fin fct afficheTableau");
    }



    public Cplx[] FFT(Cplx[] S){

        Cplx[] tabsortie=new Cplx[S.length];

        if (S.length<=1){

            tabsortie[0]= new CplxCart(S[0].getReel(), S[0].getIm());
            return tabsortie;
        }
        else{
            Cplx[] P0=new Cplx[S.length/2];
            Cplx[] P1=new Cplx[S.length/2];
            Cplx M;//new CplxPol(0,0);

            for (int i = 0; i < S.length ; i++)
            {
                if (i % 2 == 0)
                {
                    P0[i/2] = S[i];
                }
                else
                {
                    P1[(i - 1) / 2] = S[i];
                }
            }


            P0=FFT(P0);//pair
            P1=FFT(P1);//impaire


            for(int k=0;k<(S.length/2);k++){


                M=omega(k,S.length);//Faire Omega
                //M=omega(0,S.length);
                //System.out.println("M:"+M);


                Cplx temp1= new CplxCart(P1[k].getReel(),P1[k].getIm());
                        //P1[k];
                temp1.multiplication(M);
                System.out.println("temp1 = "+temp1+", P0 = "+P0[k]);
                temp1.ajoute(P0[k]);
                tabsortie[k]= temp1;//new CplxPol(temp1.getModule(),temp1.getArgument());
                //S[k]=P0[k]+P1[k]*M;

                /*Cplx temp2=P1[k];
                temp2.multiplication(M);
                temp2.soustrait(P0[k]);*/

                P1[k].multiplication(M);
                P0[k].soustrait(P1[k]);
                tabsortie[k+(S.length)/2]=P0[k];//new CplxPol(temp2.getModule(),temp2.getArgument());
                //S[k+S.length/2]=P0[k]-P1[k]*M;
            }
            afficheTableau(tabsortie);
            return tabsortie;

        }


    }

    public Cplx omega(int k,int L){
        Cplx complexe;
        complexe = new CplxPol(1, -2*Math.PI*k/L);
        System.out.println("k"+k+" = "+new CplxCart(complexe.getReel(), complexe.getIm()));
        return complexe;
    }

}

