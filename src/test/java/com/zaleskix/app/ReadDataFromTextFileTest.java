package com.zaleskix.app;

import com.zaleskix.app.util.readers.IReadDataFromFile;
import com.zaleskix.app.util.readers.ReadDataFromTextFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Test for ReadDataFromTextFile class
 */
public class ReadDataFromTextFileTest {

    private IReadDataFromFile readDataFromFile;

    @Before
    public void setUp() {
        readDataFromFile = new ReadDataFromTextFile();
    }

    //Test case: If file is valid, readDataFromFile read data from file correctly without exceptions
    @Test
    public void shouldReadFileCorrectly() {

        boolean thrown = false;
        String exception = "";

        try {
            readDataFromFile.readFile("/instructions.txt");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        } catch (IOException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertFalse(thrown);
        assertTrue(exception.isEmpty());
    }


    //Test case: If file name is empty, readDataFromFile should throw IllegalArgumentException
    //Expect: Illegal argument exception with message: "File name is empty"
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileNameIsEmpty() {

        boolean thrown = false;
        String exception = "";

        try {
            readDataFromFile.readFile("");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        } catch (IOException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("File name is empty", exception);

    }

    //Test case: If file name is empty, readDataFromFile should throw NullPointerException
    //Expect: Illegal argument exception with message: "File name is empty"
    @Test
    public void shouldThrowNullPointerExceptionWhenFileDoesNotExist() {

        boolean thrown = false;

        try {
            readDataFromFile.readFile("foo.txt");
        } catch (NullPointerException e) {
            thrown = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(thrown);
    }
}
