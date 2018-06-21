/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lilian.cariou
 */
public class Projet_java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Complexe comp1 = new Complexe(1,0);
        Complexe comp2 = new Complexe (0,0);
        Complexe comp3 = new Complexe(-1,0);
        Complexe comp4 = new Complexe (0,0);
       
        
        
        
        
        Complexe argu[] = {comp1,comp2,comp3,comp4};
        
        
        //1 on fait la fft sur les membres
      
       TFD tab1= new TFD(4);

       System.out.println(tab1);

       argu = tab1.Diviser_tab(argu);
       
       
       System.out.println(tab1);
       
      argu =  tab1.inverse(argu);
      
       System.out.println(tab1);
       
       
      
        
        
        
       
       
    }
    
}
