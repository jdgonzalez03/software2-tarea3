package SearchWordsInText;

import shared.FilterInterface;

import java.io.*;
import java.io.IOException;

public class SearchWordsInText implements FilterInterface {
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

            File outputFile = new SearchWordsInText().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public File converFile(File file) throws IOException {
        String wordToFind = "hola";
        int count = 0;

        File outputFile = new File("output_search_" + file.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                int occurrences = countOccurrences(line, wordToFind);
                if (occurrences > 0) {
                    count += occurrences;
                    writer.write("Línea " + lineNumber + ": " + line);
                    writer.newLine();
                }
                lineNumber++;
            }

            writer.write("\nLa palabra '" + wordToFind + "' apareció " + count + " veces en el archivo.");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al procesar el archivo.");
        }

        return outputFile;
    }

    private int countOccurrences(String line, String word) {
        int count = 0;
        int index = 0;

        while ((index = line.toLowerCase().indexOf(word.toLowerCase(), index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}
