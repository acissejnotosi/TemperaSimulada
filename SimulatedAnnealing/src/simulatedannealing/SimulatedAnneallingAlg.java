/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

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

    public SimulatedAnneallingAlg(Discipline[] discipl, double[] T) {
        this.discipl = discipl;
        this.T = T;
    
    }

    public Discipline [] theBestArrangement() throws Exception {
      
        boolean[] currentBoolSta = new boolean[discipl.length]; // vetor de booleanos para o estado atual
        currentBoolSta = createRandomState(currentBoolSta); // random para o vetor de booleanos
        FitnessFunction currentFit = new FitnessFunction(discipl); //FitnessFunctionpara o estado atual

        while (true) {
            
            Random r = new Random();
            for (int i = 0; i <= T.length; i++) {
                if (T[i] == 0) {
                    System.out.println("getFitnes:" + currentFit.getFitness(currentBoolSta) );
                    return getState(currentBoolSta);
                }
                boolean[] nextBoolSta = new boolean[discipl.length];
                nextBoolSta = createRandomState(nextBoolSta);
                
               
                
                FitnessFunction nextFit = new FitnessFunction(discipl);
                
                
                long deltaE = nextFit.getFitness(nextBoolSta) - currentFit.getFitness(currentBoolSta);

                if (deltaE > 0) {
                    currentBoolSta = nextBoolSta; 
                   // System.out.println("deltaE é´positivo");
                    
                } else {
                   
                    if( r.nextDouble()< Math.exp(deltaE/T[i])){
                        currentBoolSta = nextBoolSta; //System.out.println("Pega o pior");
                    }else{
                        currentBoolSta = nextBoolSta;
                       // System.out.println("Pega o melhor");
                    }
                    
                     p.add( (float)Math.exp(deltaE/T[i]));
                    
                }
                
               
            }

        }

    }

   public boolean[] createRandomState(boolean stateBool[]){
       Random RNG =  new Random();
       PrimitiveIterator.OfInt intStream = RNG.ints(0,discipl.length).iterator();
       for(int j=0 ; j<discipl.length ; j++){
       int i = intStream.next ();
        stateBool [i]= !stateBool [i];
       }
       /*  for(boolean w: stateBool)
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
