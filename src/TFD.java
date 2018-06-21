/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.math.*;

/**
 *
 * @author lilian.cariou
 */
public class TFD {
    
    public Complexe[] Se;
    public Complexe[] Sp;
    public static int compteur=0;
    //reçoit un tableau d'une longueur d'une puissance de 2
    
    public TFD(int L)
    {
       
        Se = new Complexe[L];
        Sp = new Complexe[L];      
    }
    
    
    
    //diviser le tableau jusqu'a ce qu'on est des tableaux de 2 membres
    public Complexe[] Diviser_tab(Complexe[] tab)
    {  
        
       
        if(tab.length==1)
        {
            //on copie le signal dans le tableau           
            Se[0]= tab[0];
            compteur++;           
        }
        else
        {       
            //Si N est une puissance de 2, n´ecessite N log2(N) operations.
            //source  :http://www-syscom.univ-mlv.fr/~loubaton/transpacours5bis.pdf
            //int taille_tab =  (int) (Math.log(tab.length) / Math.log(2))-1;   
            int taille_tab =(int) tab.length/2;
            TFD pairs = new TFD(taille_tab);
            TFD impairs = new TFD(taille_tab);
            Complexe tab_pairs[] = new Complexe[(taille_tab)];
            Complexe tab_impairs[] = new Complexe[(taille_tab)];

            for (int i = 0; i < tab.length ; i++) 
            {
                    if (i % 2 == 0) 
                    {
                        tab_pairs[i/2] = tab[i];               
                    } 
                    else 
                    {
                        tab_impairs[(i - 1) / 2] = tab[i];
                    }              
            }
            
            
            pairs.Diviser_tab(tab_pairs); //on divise le tableau tant que l'on a pas un tableau de 2 elementss
            impairs.Diviser_tab(tab_impairs);
            
            
            //une fois qu'on a affecté toute les valeurs dans les bonnes cases on calcule la fft
            for(int k=0;k<(tab.length/2) ;k++)
            {
                //on crée le complexe M de module unitaire et de complexe 2*pi*k*L
             
                //on affecte les bonnes valeurs de fft multiplier par le bon coeff M
               Complexe M = new Complexe((double) (-2 * Math.PI * k / tab.length));
                //Complexe M = new Complexe(1,0);
                
                
                
                this.Se[k]=(pairs.getValeurN(k)).ajoute(impairs.getValeurN(k).multiplie(M)); 
                System.out.println("Se["+k+"] : " + this.Se[k]);
                 
                this.Se[k+ Se.length/2] = (pairs.getValeurN(k)).soustrait((impairs.getValeurN(k)).multiplie(M));               
                System.out.println("Se["+(k+Se.length/2)+"] : " + this.Se[k+Se.length/2]);
            }
        
        }
        
       return Se;
    }
    
    public Complexe[] getS()
    {
        return this.Se;
    }

    public Complexe getValeurN(int n) {
        return Se[n];
    }
     
    
     public String toString()
     {
         String fft= "";
         for(int i=0;i<this.Se.length;i++)
         {
             fft = fft +" Terme "+ (i+1) + " : "+  this.Se[i];
         }
         return fft;
     }
     
     public Complexe[] inverse(Complexe[] tab)
    {  
        
       
        if(tab.length==1)
        {
            //on copie le signal dans le tableau           
            Se[0]= tab[0];
            compteur++;           
        }
        else
        {       
            //Si N est une puissance de 2, n´ecessite N log2(N) operations.
            //source  :http://www-syscom.univ-mlv.fr/~loubaton/transpacours5bis.pdf
            //int taille_tab =  (int) (Math.log(tab.length) / Math.log(2))-1;   
            int taille_tab =(int) tab.length/2;
            TFD pairs = new TFD(taille_tab);
            TFD impairs = new TFD(taille_tab);
            Complexe tab_pairs[] = new Complexe[(taille_tab)];
            Complexe tab_impairs[] = new Complexe[(taille_tab)];

            for (int i = 0; i < tab.length ; i++) 
            {
                    if (i % 2 == 0) 
                    {
                        tab_pairs[i/2] = tab[i];               
                    } 
                    else 
                    {
                        tab_impairs[(i - 1) / 2] = tab[i];
                    }              
            }
            
            
            pairs.Diviser_tab(tab_pairs); //on divise le tableau tant que l'on a pas un tableau de 2 elementss
            impairs.Diviser_tab(tab_impairs);
            
            
            //une fois qu'on a affecté toute les valeurs dans les bonnes cases on calcule la fft
            for(int k=0;k<(tab.length/2) ;k++)
            {
                //on crée le complexe M de module unitaire et de complexe 2*pi*k*L
             
                //on affecte les bonnes valeurs de fft multiplier par le bon coeff M
               Complexe M = new Complexe((double) (2 * Math.PI * k / tab.length));
                //Complexe M = new Complexe(1,0);
                
                
                
                this.Se[k]=(pairs.getValeurN(k)).ajoute(impairs.getValeurN(k).multiplie(M)); 
                System.out.println("Se["+k+"] : " + this.Se[k]);
                 
                this.Se[k+ Se.length/2] = (pairs.getValeurN(k)).soustrait((impairs.getValeurN(k)).multiplie(M));               
                System.out.println("Se["+(k+Se.length/2)+"] : " + this.Se[k+Se.length/2]);
            }
        
        }
        
        for(int i=0;i<tab.length;i++)
        {
            tab[i]=tab[i].multiplie(new Complexe(1/(double)tab.length,0));
        }
        
       return Se;
    }
}


