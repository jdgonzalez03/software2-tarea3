package ConvertTextToSHA;

import shared.FilterInterface;

import java.io.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertTextToSha256 implements FilterInterface {
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

            File outputFile = new ConvertTextToSha256().converFile(file);
            printOut.println(outputFile.getAbsolutePath());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public File converFile(File file) throws IOException{
        File outputFile = new File("output_TextToSHA256_" + file.getName());

        try (
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
                FileOutputStream writer = new FileOutputStream(outputFile);
        ) {
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                sha256Digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = sha256Digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }

            writer.write(hexString.toString().getBytes());
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println("Error al encriptar el archivo con SHA-256");
        }

        return outputFile;

    }
}
