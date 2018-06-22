import com.sun.media.sound.FFT;

public class exo1_recu2 {

    public static void main(String[] args) {
        exo1_recu2 test = new exo1_recu2();
        Cplx[][] matrice = matrice(4);
        test.afficheMatrice(matrice);

        //matrice=test.normaliser2D(test.FFT_2D_inv(test.zero_padding((test.FFT_2D(matrice)))));
        matrice=test.zero_padding((test.FFT_2D(matrice)));
        //test.afficheMatrice(matrice);

        /*Cplx[][] matriceTranspose = test.transpose(matrice);
        test.afficheMatrice(matriceTranspose);*/


        /*Cplx[] complexe = transformation();
        complexe = signal(4);
        //complexe[1] = new CplxCart(1,0);
        test.afficheTableau(complexe);
        //test.afficheTableau(complexe);
        //test.FFT(complexe,false);
        test.normalise(test.FFT_inv(test.FFT(complexe)));

        Cplx [][] complexe2D =matrice(2);*/

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
                //System.out.println("temp1 = "+temp1+", P0 = "+P0[k]);
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
            //afficheTableau(tabsortie);
            return tabsortie;

        }
    }


    public Cplx[] FFT_inv(Cplx[] S){
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


            P0=FFT_inv(P0);//pair
            P1=FFT_inv(P1);//impaire


            for(int k=0;k<(S.length/2);k++){


                    M=omega(-k,S.length);//Faire Omega avec conj


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

            Cplx[] tabnorm=new Cplx[S.length];

            System.arraycopy( tabsortie, 0, tabnorm, 0, tabsortie.length );

            /*for (int i=0;i<tabnorm.length;++i){
                tabnorm[i].multiplication(new CplxCart(1/(double)tabnorm.length,0));
            }*/

            afficheTableau(tabnorm);
            return tabnorm;
        }
    }

    public Cplx[] normalise (Cplx[] tabnorm){
        for (int i=0;i<tabnorm.length;++i){
            tabnorm[i].multiplication(new CplxCart(1/(double)tabnorm.length,0));
        }
        afficheTableau(tabnorm);
        return tabnorm;
    }

    public Cplx[][] normaliser2D(Cplx[][] tab){
        System.out.println("longueurN:"+(tab.length));
        CplxCart var=new CplxCart((1/(double)(tab.length*tab[0].length)),0.0);
        System.out.println("var:"+var);
        for (int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){
                tab[i][j].multiplication(var);

            }
        }
        return tab;

    }



    public Cplx omega(int k,int L){
        Cplx complexe;
        complexe = new CplxPol(1, -2*Math.PI*k/L);
        //System.out.println("k"+k+" = "+new CplxCart(complexe.getReel(), complexe.getIm()));
        return complexe;
    }

    public void afficheMatrice(Cplx[][] complexe){
        System.out.println("fct afficheMatrice");
        for(int i=0;i<complexe.length;i++){
            for(int j=0;j<complexe[i].length;j++){
                //System.out.println("complexe["+i+"]:"+complexe[i].toString());
                System.out.print(new CplxCart((int)complexe[i][j].getReel(), (int)complexe[i][j].getIm()));
                System.out.print("            ");
            }
            System.out.println();
        }
        System.out.println("fin fct afficheMatrice");

    }

    public static Cplx[][] matrice(int n){
        Cplx[][] data = new CplxCart[n][n];
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                data[i][j]=new CplxCart(i+2*j,0);
            }
        }
        return data;
    }


    public Cplx[][] FFT_2D(Cplx S [][]){
        Cplx [][] matricecopie = new Cplx[S.length][S[0].length];
        Cplx [][] matricefinale = new Cplx[S.length][S[0].length];

        System.arraycopy(S, 0, matricecopie, 0, S.length );
        Cplx [][] matricesortie= new Cplx[S.length][S[0].length];
        for(int k=0;k<S.length;++k){
            matricesortie[k]=FFT(matricecopie[k]);
        }
        matricesortie=transpose(matricesortie);
        for(int k=0;k<S.length;++k){
            matricefinale[k]=FFT(matricesortie[k]);
        }
        matricefinale=transpose(matricefinale);
        return matricefinale;
    }

    public Cplx[][] FFT_2D_inv(Cplx S [][]){
        Cplx [][] matricecopie = new Cplx[S.length][S[0].length];
        Cplx [][] matricefinale = new Cplx[S.length][S[0].length];

        System.arraycopy(S, 0, matricecopie, 0, S.length );
        Cplx [][] matricesortie= new Cplx[S.length][S[0].length];
        for(int k=0;k<S.length;++k){
            matricesortie[k]=FFT_inv(matricecopie[k]);
        }
        matricesortie=transpose(matricesortie);
        for(int k=0;k<S.length;++k){
            matricefinale[k]=FFT_inv(matricesortie[k]);
        }
        matricefinale=transpose(matricefinale);
        return matricefinale;
    }


    public static Cplx[][] transpose(Cplx[][] M) {
        Cplx[][] tmp=new CplxCart[M.length][M.length];
        Cplx [][] matricecopie = new Cplx[M.length][M[0].length];
        System.arraycopy(M, 0, matricecopie, 0, M.length );

        for (int i=0; i<M.length; i++){
            for (int j=0; j<M[i].length; j++){
                tmp[i][j]=matricecopie[j][i];
            }
        }
        return tmp;
    }

    public Cplx[][] zero_padding(Cplx[][] M){
        float n = M.length*2;

        Cplx[][] S=new CplxCart[(int)n][(int)n];
        for (int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                S[i][j]=new CplxCart(0,0);
            }
        }
        double decalage = (3.0/4)*S.length;
        int decalageint = (3/4)*S.length;
        System.out.println("deca float: "+decalage+" deca int: "+decalageint);


        for(int i=0;i<M.length;i++){
            for (int j=0;j<M[0].length;j++){
                if ((i<M.length/2) && (j<M[0].length/2)){ //en haut a gauche
                    //S[i][j]=M[i][j];
                    S[i][j]=new CplxCart(800,800);
                }
                else if ((i>M.length/2) && (j<M[0].length/2)){ // en bas a gauche
                    //S[(i-M.length/2)+(3/4)*S.length][j]=M[i][j];
                    S[(int)((i-M.length/2)+decalage)][j]=new CplxCart(900,900);
                }

                else if ((i<M.length/2) && (j>M[0].length/2)){ // en haut a droite
                    //S[i][(j-M[0].length/2)+(3/4)*S[0].length]=M[i][j];
                    S[i][(int)((j-M[0].length/2)+decalage)]=new CplxCart(500,500);
                }

                else if ((i>M.length/2) && (j>M[0].length/2)){ // en bas a droite
                    //S[(i-M.length/2)+(3/4)*S.length][(j-M[0].length/2)+(3/4)*S[0].length]=M[i][j];
                    S[(int)((i-M.length/2)+decalage)][(int)((j-M[0].length/2)+decalage)]=new CplxCart(200,200);
                }
                else {S[i][(int)((j-M[0].length/2)+decalage-1)]=new CplxCart(44,44);};
            }
        }
        afficheMatrice(S);
        return S;
    }

}

