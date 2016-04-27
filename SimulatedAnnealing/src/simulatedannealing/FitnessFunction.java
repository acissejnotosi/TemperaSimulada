
package simulatedannealing;

import java.util.ArrayList;
import java.util.List;

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
    
    public long getFitness(boolean state[]) throws Exception{
        if(state.length != discips.length)
            throw new Exception("Vetor de seleção de disciplinas possui tamanho diferente do vetor de diciplinas");
        
        long fitnessSum = 0;
        List<Integer> totalIntervals = new ArrayList<>();
        
        for(int i = 0; i < state.length; i++){
            if(state[i]){
                fitnessSum += discips[i].getCredits();
                totalIntervals.addAll(discips[i].getIntervals());
            }
        }
        
        fitnessSum -= maximumCredits*countErrors(state, totalIntervals);
            
        return fitnessSum;
    }
    
    private int countErrors(boolean state[], List<Integer> intervals){
        int errors = 0;
        errors += countNotIncludedObrigatoryDisciplines(state);
        errors += countRepeatedDisciplines(state);
        errors += countRepeatedIntervals(intervals);
        errors += countFiveIntervalsSequence(intervals);
        errors += countTwelveIntervalNotBreak(intervals);
        return 0;
    }
    
    private int countNotIncludedObrigatoryDisciplines(boolean state[]){
        
        return 0;
    }

    private int countRepeatedDisciplines(boolean state[]){
        return 0;
    }
    
    private int countRepeatedIntervals(List<Integer> intervals){
        return 0;
    }
    
    
    private int countFiveIntervalsSequence(List<Integer> intervals){
        return 0;
    }
    
    private int countTwelveIntervalNotBreak(List<Integer> intervals){
        return 0;
    }
    
//    private Discipline[] getObrigatories(){
//        List<Discipline> obrigatories = new ArrayList<Discipline>();
//        for(Discipline d: discips)
//            if(d.isObrigatorie())
//                obrigatories.add(d);
//        return obrigatories.toArray(new Discipline[obrigatories.size()]);
//    }
}
