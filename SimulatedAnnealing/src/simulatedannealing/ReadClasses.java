
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author a1562711
 */
public class ReadClasses {
    final private FileInputStream stream; //= new FileInputStream("arquivo.txt");
    final private InputStreamReader reader; //= new InputStreamReader(stream);
    final private BufferedReader br; //= new BufferedReader(reader);

    public ReadClasses(File file) throws FileNotFoundException {
        stream = new FileInputStream(file.getAbsolutePath());
        reader = new InputStreamReader(stream);
        br = new BufferedReader(reader);
        
    }
    
    private void buildClasses() throws IOException{
        int id = 0;
        String newLine;
        List<Class> turmas= new ArrayList<>();
        while((newLine = br.readLine())!=null){
            StringTokenizer strTok = new StringTokenizer(newLine, ",");
            String class_ = strTok.nextToken();
            String credits = strTok.nextToken();
            String classLetters = "";
            String classDigits = "";
            for(char c: class_.trim().toCharArray())
                if(Character.isAlphabetic(c))
                    classLetters += String.valueOf(c);
                else if (Character.isDigit(c))
                    classDigits += String.valueOf(c);
            List<Integer> intervals = new ArrayList<Integer>();
            while(strTok.hasMoreTokens()){
                // Turma = xy (ex.: 301 -> x = 3; y = 01)
                // x-2*24+y+6
                String str = strTok.nextToken();
                String x = str.substring(0,1);
                String y = str.substring(1,3);
                int class_n = Integer.valueOf(x)*24+Integer.valueOf(y)+6;
                intervals.add(class_n);
            }
            Class turma = new Class(id,classLetters.trim(),Integer.valueOf(classDigits).intValue(), Integer.valueOf(credits).intValue(), intervals);
            turmas.add(turma);
            id++;
        }
    }
    
    
    
    
    
}
