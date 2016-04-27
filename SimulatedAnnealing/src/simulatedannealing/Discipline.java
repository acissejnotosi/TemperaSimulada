/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

import java.util.List;

/**
 *
 * @author a1562711
 */
public class Discipline {
    
    private int id;
    private String disciplineName;
    private int disciplineNum;
    private int credits;
    private List<Integer> intervals;

    public Discipline(int id, String disciplinename, int disciplinen, int credits, List<Integer> intervals) {
        this.id = id;
        this.disciplineName = disciplinename;
        this.disciplineNum = disciplinen;
        this.credits = credits;
        this.intervals = intervals;
    }



    public String getName() {
        return disciplineName;
    }

    public int getCredits() {
        return credits;
    }

    public List<Integer> getIntervals() {
        return intervals;
    }

    public int getId() {
        return id;
    }

    public String getClass_name() {
        return disciplineName;
    }

    public int getClass_n() {
        return disciplineNum;
    }
    
    
    
    public boolean isNeeded(){
    return true ;
    }
    
    public boolean getConflicts(){
     return true ;   
    }
            
    @Override
    public String toString(){
        String str = "["+ id +"]{"+ disciplineName + disciplineNum +", "+ credits +", {";
        for(Integer i: intervals)
        {
            int x = i/24 + 2;
            int y = i%24 - 6;
            str += String.format("%01d%02d, ", x, y);
        }
        str = str.substring(0,str.length()-2);
        str += "}};";
        return str;
    }
}
