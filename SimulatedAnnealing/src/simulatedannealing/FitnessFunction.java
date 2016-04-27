
package simulatedannealing;

public class FitnessFunction {
    final Discipline discips[];
    final int maximumCredits;
    
    public FitnessFunction(Discipline[] discips) {
        this.discips = discips;
        int sumCredits = 0;
        for(Discipline d: discips)
            sumCredits += d.getCredits();
        maximumCredits = sumCredits;
    }
    
    public int getFitness(boolean state[]) throws Exception{
        if(state.length != discips.length)
            throw new Exception("Vetor de seleção de disciplinas possui tamanho diferente do vetor de diciplinas");
        
        
        throw new UnsupportedOperationException();
    }
}
