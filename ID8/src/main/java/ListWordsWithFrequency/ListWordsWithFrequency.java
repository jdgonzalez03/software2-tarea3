package ListWordsWithFrequency;

import shared.FilterInterface;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListWordsWithFrequency implements FilterInterface {

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

            File outputFile = new ListWordsWithFrequency().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException {
        File outputFile = new File("output_wordFrequency_" + file.getName());

        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[^a-záéíóúüñ0-9 ]", "");
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }

            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCountMap.entrySet());
            sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<String, Integer> entry : sortedList) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al procesar el archivo.");
        }

        return outputFile;
    }
}
