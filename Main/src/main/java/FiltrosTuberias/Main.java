package FiltrosTuberias;
import Factory.Factory;
import shared.FilterInterface;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo de texto: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);

        Factory factory = new Factory();

        System.out.println("Seleccione una opción:");
        System.out.println("1. LoadFile -> TextToBinary");
        System.out.println("2. Binary -> Text");
        System.out.println("3. LoadFile -> TextToBinary -> BinaryToBase64 -> Base64ToBinary -> BinaryToText");
        System.out.println("4. LoadFile -> TextToSHA");
        System.out.println("5. LoadFile -> Buscar 'hola' en texto");
        System.out.println("6. LoadFile -> Listar Palabras con frecuencia");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                procesarTextoABinario(factory, archivo);
                break;
            case 2:
                procesarBinarioATexto(factory, archivo);
                break;
            case 3:
                procesarTextoCompleto(factory, archivo);
                break;
            case 4:
                procesarTextoASHA(factory, archivo);
                break;
            case 5:
                listarPalabras(factory, archivo);
                break;
                case 6:
                    listarPalabrasConFrecuencia(factory, archivo);
                    break;
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
    }

    private static void procesarTextoABinario(Factory factory, File archivo) throws IOException {
        System.out.println("Convirtiendo Texto a binario");
        FilterInterface textToBinary = factory.convertTextToBinary();
        File binaryFile = textToBinary.converFile(archivo);
        System.out.println("Archivo binario generado en: " + binaryFile.getAbsolutePath());
    }

    private static void procesarBinarioATexto(Factory factory, File archivo) throws IOException {
        System.out.println("Convirtiendo de binario a texto");
        FilterInterface binaryToText = factory.convertBinaryToText();
        File textFile = binaryToText.converFile(archivo);
        System.out.println("Archivo de texto generado en: " + textFile.getAbsolutePath());
    }

    private static void procesarTextoCompleto(Factory factory, File archivo) throws IOException {
        System.out.println("Ejecutando la conversión completa");

        FilterInterface textToBinary = factory.convertTextToBinary();
        File binary = textToBinary.converFile(archivo);
        System.out.println("Archivo binario generado en: " + binary.getAbsolutePath());

        FilterInterface binaryToBase64 = factory.convertBinaryToBase64();
        File base64 = binaryToBase64.converFile(binary);
        System.out.println("Archivo Base64 generado en: " + base64.getAbsolutePath());

        FilterInterface base64ToBinary = factory.convertBase64ToBinary();
        File binaryAgain = base64ToBinary.converFile(base64);
        System.out.println("Archivo binario reconvertido en: " + binaryAgain.getAbsolutePath());

        FilterInterface binaryToText = factory.convertBinaryToText();
        File finalText = binaryToText.converFile(binaryAgain);
        System.out.println("Archivo de texto final generado en: " + finalText.getAbsolutePath());
    }

    private static void procesarTextoASHA(Factory factory, File archivo) throws IOException {
        System.out.println("Encriptando el archivo en SHA256");
        FilterInterface encripter = factory.convertTextToSHA();
        File encryptedFile = encripter.converFile(archivo);
        System.out.println("Archivo encriptado generado en: " + encryptedFile.getAbsolutePath());
    }

    private static void listarPalabras(Factory factory, File archivo) throws IOException {
        System.out.println("Listando palabras en el archivo");
        FilterInterface listWords = factory.searchHelloWord();
        File wordListFile = listWords.converFile(archivo);
        System.out.println("Archivo con palabras listadas generado en: " + wordListFile.getAbsolutePath());
    }

    private static void listarPalabrasConFrecuencia(Factory factory, File archivo) throws IOException {
        System.out.println("Listando palabras con frecuencia en el archivo");
        FilterInterface listWords = factory.listWordsWithFrequency();
        File wordListFile = listWords.converFile(archivo);
        System.out.println("Archivo de palabras con frecuencia listadas generado en: " + wordListFile.getAbsolutePath());
    }
}
