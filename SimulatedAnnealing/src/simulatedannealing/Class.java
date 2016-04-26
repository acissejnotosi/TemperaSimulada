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
public class Class {
    
    private int id;
    private String class_name;
    private int class_n;
    private int credits;
    private List<Integer> intervals;

    public Class(int id, String class_name, int class_n, int credits, List<Integer> intervals) {
        this.id = id;
        this.class_name = class_name;
        this.class_n = class_n;
        this.credits = credits;
        this.intervals = intervals;
    }



    public String getName() {
        return class_name;
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
        return class_name;
    }

    public int getClass_n() {
        return class_n;
    }
    
    
    
    public boolean isNeeded(){
    return true ;
    }
    
    public boolean getConflicts(){
     return true ;   
    }
            
}
