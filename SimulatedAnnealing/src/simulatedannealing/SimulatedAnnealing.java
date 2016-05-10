package simulatedannealing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SimulatedAnnealing {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        File file;
        JFileChooser fileDialog = new JFileChooser();
        FileFilter textFilter = new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return  pathname.exists() &&
                        pathname.canRead() &&
                        pathname.getName().matches("(?i).+\\.txt") ||
                        pathname.isDirectory();
            }

            @Override
            public String getDescription() {
                return "*.txt";
            }
        };
        
        fileDialog.setMultiSelectionEnabled(false);
        fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileDialog.setFileFilter(textFilter);
        fileDialog.showOpenDialog(null);
        file = fileDialog.getSelectedFile();
        
        if(file == null || !file.exists()){
            System.err.println("Arquivo não encontrado.");
            System.exit(0);
        }
    
        ReadDisciplines rDisc = new ReadDisciplines(file);
        
        Discipline disciplines[] = rDisc.getDisciplines(); //
        
        for(Discipline d: disciplines)
            System.out.println(d);
        
        int j=50000;
        List<Float> p;
        double[] T = new double[j+1];
        for(int i= 0; i<=50000; i++)
        {
            T[i] = j;
            j--;
        }
      
        
        SimulatedAnneallingAlg sAA = new SimulatedAnneallingAlg(disciplines, T);
        Discipline[] bestState = new Discipline[sAA.theBestArrangement().length];
        bestState = sAA.theBestArrangement();
        
            p= sAA.getP();
        
     //   for(double d: p)
         //   System.out.println(d);
    
        for(Discipline w: bestState)
            System.out.println(w);
        
        int fitnessSum=0;
         for(int i = 0; i < bestState.length; i++){
                fitnessSum += bestState[i].getCredits();       
        }
         
         System.out.println(fitnessSum);
         System.out.println();
         
    }
}
