package ConvertBase64ToBinary;

import shared.FilterInterface;

import java.io.*;
import java.util.Base64;

public class ConvertBase64ToBinary implements FilterInterface {
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

            File outputFile = new ConvertBase64ToBinary().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public File converFile(File file) throws IOException {
        File outputFile = new File("output_binary_" + file.getName());

        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                FileOutputStream writer = new FileOutputStream(outputFile);
        ) {
            String base64String = reader.readLine();
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            writer.write(decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al convertir el archivo Base64 a binario");
        }

        return outputFile;
    }
}
