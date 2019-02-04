package com.zaleskix.app;

import com.zaleskix.app.util.readers.IReadDataFromFile;
import com.zaleskix.app.util.validators.IValidator;
import com.zaleskix.app.util.validators.ValidatorTextFile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for ValidatorTextFile class
 */
public class ValidatorTextFileTest {

    @Mock
    private BufferedReader reader;
    @Mock
    private IReadDataFromFile readDataFromTextFile;

    private IValidator validator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    //Test case: If file is valid, validator return true
    //Expect: true
    @Test
    public void shouldReturnTrueWhenValidFileIsGiven() throws IOException {

        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Multiply 1", "Apply 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        boolean returnedValue = false;
        String exception = "";

        try {
            returnedValue = validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertFalse(thrown);
        assertTrue(returnedValue);
        assertTrue(exception.isEmpty());

    }

    //Test case: If file is empty, validator return false
    //Expect: false
    @Test
    public void shouldReturnFalseWhenFileIsEmpty() throws IOException {

        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn(null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        boolean returnedValue = true;
        String exception = "";

        try {
            returnedValue = validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertFalse(thrown);
        assertFalse(returnedValue);
        assertTrue(exception.isEmpty());
    }

    //Test case: If file contains more than one APPLY keywords, validator should throw exception
    //Expect: Illegal argument exception with message: "Apply keyword occurs more than once "
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileContainsManyApplyKeywords() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Apply 1", "Apply 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("Apply keyword occurs more than once ", exception);

    }

    //Test case: If file contains string that is not defined as instruction keyword, validator should throw exception
    //Expect: Illegal argument exception with message: "In file are present words that are not defined instruction"
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileContainsNotDefinedInstructions() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Multiply 1", "Add 1", "Test 2", "Apply 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("In file are present words that are not defined instruction", exception);

    }

    //Test case: If file contains something more than instruction and number, validator should throw exception
    //Expect: Illegal argument exception with message: "In file are present line that contain something more or less than instruction and number"
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileContainsLineWithSomethingMoreThatInstructionAndNumber() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Multiply Test 1", "Apply 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("In file are present line that contain something more or less than instruction and number", exception);

    }

    //Test case: If file contains APPLY keyword not as last instruction, validator should throw exception
    //Expect: Illegal argument exception with message: "Apply keyword is not on last line in file"
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileContainsApplyKeywordNotAsLastInstruction() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Apply 1", "Add 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("Apply keyword is not on last line in file", exception);

    }

    //Test case: If file not contains APPLY keyword, validator should throw exception
    //Expect: Illegal argument exception with message: "File not contains APPLY keyword""
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileNotContainsApplyKeyword() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Multiply 1", "Add 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("File not contains APPLY keyword", exception);

    }

    //Test case: If file contains second argument that is not number, validator should throw exception
    //Expect: Illegal argument exception with message: "In file are present line with second argument that not double"
    @Test
    public void shouldThrowNewIllegalArgumentExceptionWhenFileContainsLineWithSecondArgumentIsNotNumber() throws IOException {
        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Multiply Test", "Apply 2", null);

        validator = new ValidatorTextFile(readDataFromTextFile);

        boolean thrown = false;
        String exception = "";

        try {
            validator.validateFile("test");
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("In file are present line with second argument that not double", exception);

    }
}
