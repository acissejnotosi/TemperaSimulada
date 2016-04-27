
package simulatedannealing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FitnessFunction {
    final Discipline discips[];
    final int maximumCredits;
    Discipline obrigatories[];
    
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
        
        totalIntervals.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        
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
        return errors;
    }
    
    private int countNotIncludedObrigatoryDisciplines(boolean state[]){
        Discipline[] obrigatories = getObrigatories();
        List<String> tokensNotTaken = new ArrayList<>();
        for(Discipline d: obrigatories)
            if(!tokensNotTaken.contains(d))
                tokensNotTaken.add(d.getName().toUpperCase());
        for(int i = 0; i < state.length; i++)
            if(state[i])
                if(tokensNotTaken.contains(discips[i].getName().toUpperCase()))
                    tokensNotTaken.remove(discips[i].getName().toUpperCase());
        return tokensNotTaken.size();
    }

    private int countRepeatedDisciplines(boolean state[]){
        int countRep = 0;
        List<String> repeatedTokens =  new ArrayList<>();
        for(int i = 0; i < state.length; i++){
            if(state[i]){
                if(repeatedTokens.contains(discips[i].getDisciplineName().toUpperCase()))
                    countRep++;
                else
                    repeatedTokens.add(discips[i].getDisciplineName().toUpperCase());
            }
        }
        
        return countRep;
    }
    
    private int countRepeatedIntervals(List<Integer> intervals){
        int countRep = 0;
        List<Integer> repeatedTokens = new ArrayList<>();
        for(Integer i: intervals)
            if(repeatedTokens.contains(i))
                countRep++;
            else
                repeatedTokens.add(i);
        return countRep++;
    }
    
    private int countFiveIntervalsSequence(List<Integer> intervals){
        return 0;
    }
    
    private int countTwelveIntervalNotBreak(List<Integer> intervals){
        return 0;
    }
    
    private Discipline[] getObrigatories(){
        if(obrigatories == null){
            List<Discipline> ob = new ArrayList<>();
            for(Discipline d: discips)
                if(d.isObrigatory())
                    ob.add(d);

            obrigatories = ob.toArray(new Discipline[ob.size()]);
        }
        return obrigatories;
    }
}
