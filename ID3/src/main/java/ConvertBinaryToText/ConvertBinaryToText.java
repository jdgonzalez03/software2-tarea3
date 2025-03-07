package ConvertBinaryToText;

import shared.FilterInterface;

import java.io.*;

public class ConvertBinaryToText implements FilterInterface {

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

            File outputFile = new ConvertBinaryToText().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException {
        File outputFile = new File("output_BinaryToText_" + file.getName());

        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                FileWriter writer = new FileWriter(outputFile);
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder textLine = new StringBuilder();

                String[] binaryChars = line.split(" ");

                for (String binary : binaryChars) {
                    if (binary.length() == 8) {
                        char character = (char) Integer.parseInt(binary, 2);
                        textLine.append(character);
                    }
                }
                writer.write(textLine.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al convertir el archivo binario a texto");
        }

        return outputFile;
    }
}
