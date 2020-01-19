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
    Scanner scanner = new Scanner(System.in);

    private void read(String path) throws IOException {

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

            System.out.println("Sorry! That file was not found!");
            System.out.print("\nPlease enter a directory path to read the file (Including the file name with extension): ");
            read(scanner.nextLine());
        } finally {

            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    private void write(String path) throws IOException {

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
        } finally {

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public void optionsToExecute() throws IOException {

        System.out.print("\nEnter 1 to read a file OR 2 to write a file: ");
        int option = 0;

        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            
            System.out.print("Invalid entry! Please try again. ex");
            optionsToExecute();
        } finally {

            switch (option) {
                case 1:
                    System.out.print("Please enter a directory path to read the file (Including the file name with extension): ");
                    read(scanner.nextLine());
                    break;
                    
                case 2:
                    System.out.print("Please enter a directory path to write the file: ");
                    write(scanner.nextLine());
                    break;
                    
                default:
                    System.out.println("Invalid entry! Please try again.");
                    optionsToExecute();

            }
        }
    }

}
