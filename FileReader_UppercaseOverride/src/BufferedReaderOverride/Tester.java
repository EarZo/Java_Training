/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BufferedReaderOverride;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Eranda
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        
        try (FileReader fileReader = new FileReader("Document.txt");
                Reader reader = new ReaderOverride(fileReader)) {

            int asciiValue = 0;
            char singleCharacter = 0;

            // reads to the end of the text file
            while ((asciiValue = reader.read()) != -1) {

                // converts ASCII int to character
                singleCharacter = (char) asciiValue;

                // prints character
                System.out.print(singleCharacter);
            }
            
            System.out.println();
            
        } catch (IOException ex) {
            System.out.println("Exception thrown: " + ex);
        }
    }
}
