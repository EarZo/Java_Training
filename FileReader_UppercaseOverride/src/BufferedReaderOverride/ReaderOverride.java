/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BufferedReaderOverride;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Eranda
 */
public class ReaderOverride extends BufferedReader {

    public ReaderOverride(Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int asciiValue = super.read();

        // calculates ASCII int value for uppercase from ASCII int value for lowercase
        if (asciiValue >= 97 && asciiValue <= 172) {
            return (asciiValue - 32);
        } else {
            return asciiValue;
        }
    }
}
