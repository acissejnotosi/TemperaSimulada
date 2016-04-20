/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author a1562711
 */
public class ReadClasses {
    FileInputStream stream; //= new FileInputStream("arquivo.txt");
    InputStreamReader reader; //= new InputStreamReader(stream);
    BufferedReader br; //= new BufferedReader(reader);

    public ReadClasses(FileInputStream stream, InputStreamReader reader, BufferedReader br) {
        this.stream = stream;
        this.reader = reader;
        this.br = br;
    }
    
    
    
    
    
}
