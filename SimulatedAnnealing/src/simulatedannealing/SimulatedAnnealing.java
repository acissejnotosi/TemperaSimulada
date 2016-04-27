package simulatedannealing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SimulatedAnnealing {

    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        
        Discipline disciplines[] = rDisc.getDisciplines();
        
        for(Discipline d: disciplines)
            System.out.println(d);
        
        
    }
    
}
