/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filereader_filewriter;

/**
 *
 * @author Eranda
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileReader_FileWriter {

    /**
     * @param args the command line arguments
     */
    static Scanner scanner = new Scanner(System.in);

    private static void read(String path) throws IOException {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
            }
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

            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    private static String write(String path) throws IOException {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        
        
        System.out.print("Please enter the file name: ");
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.append("\\");
        stringBuilder.append(scanner.nextLine());
        stringBuilder.append(".txt");

        try {
            File file = new File(stringBuilder.toString());
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            System.out.print("Please enter some text: ");
            bufferedWriter.write(scanner.nextLine());
        } catch (IOException ex) {

            System.out.println("Exception thrown: " + ex);
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }
        
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {

        System.out.print("Please enter file directory path to read from: ");
        read(scanner.nextLine());
    }

}
