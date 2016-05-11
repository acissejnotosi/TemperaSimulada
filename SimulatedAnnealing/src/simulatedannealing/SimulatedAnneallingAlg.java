/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.PrimitiveIterator;

/**
 *
 * @author JessicaIsoton
 */
public class SimulatedAnneallingAlg {

    private Discipline discipl[]; //recebe a lista de disciplinas
    private double T[]; // função temperatura
    private List<Float> p  = new  ArrayList<Float>(); // probalidade de fazer uma escolha ruim
    
    private final int MAX_IT = 100000;
    private final float CNST_T = 0.0001F; 

    public SimulatedAnneallingAlg(Discipline[] discipl) {
        this.discipl = discipl;
        
    
    }

    public Discipline [] theBestArrangement() throws Exception {
      
        boolean[] currentBoolSta = new boolean[discipl.length]; // vetor de booleanos para o estado atual
        currentBoolSta = createRandomState(currentBoolSta); // random para o vetor de booleanos
        FitnessFunction ff = new FitnessFunction(discipl); //FitnessFunctionpara o estado atual
       // double[] T = new double[MAX_IT] ;
                   
            Random r = new Random();
            for (int i = 1; i <= MAX_IT; i++) {
                //T[i]=MAX_IT-CNST_T*i;
                
                boolean[] nextBoolSta = new boolean[discipl.length];
                nextBoolSta = createRandomState(nextBoolSta);               
                long deltaE =0;
                
                // DeltaE calculado com valores normalizados.
                if(ff.getFitness(nextBoolSta)> ff.getFitness(currentBoolSta)){
                    deltaE = (ff.getFitness(nextBoolSta)+ff.getFitness(nextBoolSta))-
                            (ff.getFitness(currentBoolSta)+ff.getFitness(nextBoolSta));
                    System.out.println("*"+(ff.getFitness(nextBoolSta)+ff.getFitness(nextBoolSta)));
                }else{
                     deltaE = (ff.getFitness(currentBoolSta)+ff.getFitness(nextBoolSta))-
                            (ff.getFitness(currentBoolSta)+ff.getFitness(currentBoolSta));
                    System.out.println("*"+(ff.getFitness(currentBoolSta)+ff.getFitness(nextBoolSta)));
                
                }
                if (deltaE > 0) {
                    currentBoolSta = nextBoolSta; 
                   // System.out.println("deltaE é´positivo");
                    
                } else {
                   
                    if( r.nextDouble()> Math.exp(deltaE/testeT(i)) ){
                       //currentBoolSta = nextBoolSta; 
                      //System.out.println("Pega o pior" + Math.exp(deltaE/testeT(i)));
                       
                    }else{
                        currentBoolSta = nextBoolSta;
                       // System.out.println("Pega o melhor");
                    }
                    
                     p.add( (float)Math.exp(deltaE/testeT(i)));
                    
                }              
            }
        System.out.println(ff.getFitness(currentBoolSta) );
        return getState(currentBoolSta);
    }
    
    public int testeT(int t){
        int x = (int) (int)(CNST_T / MAX_IT);
        return x > 0?x:1;
    }

   public boolean[] createRandomState(boolean stateBool[]){
       Random RNG =  new Random();
       PrimitiveIterator.OfInt intStream = RNG.ints(0,discipl.length).iterator();
       for(int j=0 ; j<10/*RNG.nextInt(discipl.length*/ ; j++){
       int i = intStream.next ();
        stateBool [i]= !stateBool [i];
       }
        /* for(boolean w: stateBool)
                System.out.println(w);*/
       return stateBool;
   }
  

    public List<Float> getP() {
        
        return this.p;
    }

    public Discipline[] getState(boolean stateBool[] ){
        List<Discipline> newState = new ArrayList<Discipline>();
        
        for(int i=0; i<=(stateBool.length-1) ; i++)
            if( stateBool[i] )
                 newState.add(discipl[i]);
        
        return newState.<Discipline>toArray(new Discipline[newState.size()]);
    }
    
    
}
