package ConvertTextToBinary;

import shared.FilterInterface;

import java.io.*;

public class ConvertTextToBinary implements FilterInterface {

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

            File outputFile = new ConvertTextToBinary().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException {
        File outputFile = new File("output_textToBinary_" + file.getName());
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                FileOutputStream writer = new FileOutputStream(outputFile);
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char character : line.toCharArray()) {
                    String binaryString = String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
                    writer.write(binaryString.getBytes());
                    writer.write(' ');
                }
                writer.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al convertir el archivo de texto a binario");
        }

        return outputFile;
    }
}
