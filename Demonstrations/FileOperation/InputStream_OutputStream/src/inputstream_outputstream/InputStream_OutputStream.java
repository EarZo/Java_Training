/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputstream_outputstream;

/**
 *
 * @author Eranda
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class InputStream_OutputStream {

    /**
     * @param args the command line arguments
     */
    static Scanner scanner = new Scanner(System.in);

    private static void read(String path) throws IOException {

        FileInputStream fileInputstream = null;

        try {
            fileInputstream = new FileInputStream(path);

            int value;

            while ((value = fileInputstream.read()) != -1) {

                System.out.print((char) value);
            }
            
            System.out.println();
        } catch (FileNotFoundException ex) {

            System.out.println("Sorry! That file was not found. Please create a new file.");
            System.out.print("\nPlease enter the directory path to save the file: ");
            String newPath = write(scanner.nextLine());
            System.out.println("File saved!");

            System.out.println("\nReading the saved file!");
            read(newPath);
        } catch (IOException ex) {

            System.out.println("Exception thrown: " + ex);
        } finally {

            if (fileInputstream != null) {
                fileInputstream.close();
            }
        }
    }

    private static String write(String path) throws IOException {

        FileOutputStream fileOutputStream = null;
        
        System.out.print("Please enter the file name: ");
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.append("\\");
        stringBuilder.append(scanner.nextLine());
        stringBuilder.append(".txt");

        try {

            File file = new File(stringBuilder.toString());
            fileOutputStream = new FileOutputStream(file);

            System.out.print("Please enter some text: ");
            fileOutputStream.write(scanner.nextLine().getBytes());
        } catch (IOException ex) {

            System.out.println("Exception thrown: " + ex);
        } finally {

            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {

        System.out.print("Please enter file directory path to read from: ");
        read(scanner.nextLine());
    }

}
