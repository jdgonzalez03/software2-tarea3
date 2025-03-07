package ConvertBinaryToBase64;

import shared.FilterInterface;

import java.io.*;
import java.util.Base64;

public class ConvertBinaryToBase64 implements FilterInterface {

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

            File outputFile = new ConvertBinaryToBase64().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException{
        File outputFile = new File("output_binaryToBase64_" + file.getName());

        try (
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
                FileWriter writer = new FileWriter(outputFile);
        ) {
            byte[] fileBytes = reader.readAllBytes();

            String base64String = Base64.getEncoder().encodeToString(fileBytes);

            writer.write(base64String);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al convertir el archivo binario a Base64");
        }

        return outputFile;
    }
}
