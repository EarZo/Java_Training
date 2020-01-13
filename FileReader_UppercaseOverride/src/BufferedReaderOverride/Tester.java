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
        
        ReaderOverride readerOverride = new ReaderOverride(new FileReader("Document.txt"));

        System.out.println();

        String line = null;

        while ((line = readerOverride.readLine()) != null) {
            System.out.println("Output from readLine() method: " + line);
        }
        
        readerOverride = new ReaderOverride(new FileReader("Document.txt"));

        int asciiValue = 0;
        char singleCharacter = 0;

        System.out.print("Output from read() method: ");

        // reads to the end of the text file
        while ((asciiValue = readerOverride.read()) != -1) {

            // converts ASCII int to character
            singleCharacter = (char) asciiValue;

            // prints character
            System.out.print(singleCharacter);
        }
    }
}
