package LoadTextFile;

import com.sun.tools.javac.Main;
import shared.FilterInterface;

import java.io.*;

public class LoadFileText implements FilterInterface {
    public static void main(String[] args){
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                PrintStream printOut = new PrintStream(System.out, true, "UTF-8");
        ) {
            String pathFile = reader.readLine();
            if (pathFile == null || pathFile.isEmpty()){
                System.err.println("No se puede abrir el archivo");
                return;
            }
            File file = new File(pathFile);
            if (!file.exists()){
                System.err.println("Archivo no encontrado");
                return;
            }

            File outputFile = new LoadFileText().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException {
        File outputFile =  new File("output_loadfile_" + file.getName());
        try(
                BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        ){
            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Error al leer el archivo - Load Text File");
        }

        return outputFile;
    }
}
