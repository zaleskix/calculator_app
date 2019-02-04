package com.zaleskix.app;

import com.zaleskix.app.models.Instruction;
import com.zaleskix.app.models.InstructionsKeywords;
import com.zaleskix.app.util.UserDataProcessor;
import com.zaleskix.app.util.validators.IValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for Calculator class
 */
public class CalculatorTest {

    @Mock
    private IValidator validator;

    @Mock
    private UserDataProcessor userDataProcessor;

    private Calculator calculator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //Test case: Calculator should calculate output correctly when file is valid
    @Test
    public void shouldCalculateOutputCorrectlyWhenFileIsValid() throws IOException {

        when(validator.validateFile(anyString())).thenReturn(true);
        when(userDataProcessor.readUserDataFromFileAndReturnListOfInstructionObject(anyString()))
                .thenReturn(getInstructionsList());

        calculator = new Calculator(validator, userDataProcessor);
        boolean thrown = false;
        double calculatedOutput = 0.0;
        String exception = "";

        try {
            calculatedOutput = calculator.calculateOutput();
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertFalse(thrown);

        // Calculate output = ((((7,4 + 2) - 3,1) * 4) / 5,0 = 5.04
        assertEquals(5.04, calculatedOutput, 0.1);

    }

    //Test case: If file is not valid (is empty), calculator should throw exception
    //Expect: Illegal argument exception with message: "File is not valid"
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileIsNotValid() throws IOException {
        when(validator.validateFile(anyString())).thenReturn(false);

        calculator = new Calculator(validator, userDataProcessor);
        boolean thrown = false;
        String exception = "";

        try {
            calculator.calculateOutput();
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals("File is not valid", exception);

    }

    //Test case: If file is valid but file contains instruction DIVIDE with number 0, calculator should throw exception
    //Expect: Illegal argument exception with message: "You try divide by 0!"
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDivideByZero() throws IOException {

        when(validator.validateFile(anyString())).thenReturn(true);
        when(userDataProcessor.readUserDataFromFileAndReturnListOfInstructionObject(anyString()))
                .thenReturn(getInstructionsListWithDivideByZero());

        calculator = new Calculator(validator, userDataProcessor);
        boolean thrown = false;
        double calculatedOutput = 0.0;
        String exception = "";

        try {
            calculatedOutput = calculator.calculateOutput();
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
            thrown = true;
        }

        assertTrue(thrown);
        assertEquals(0.0, calculatedOutput, 0.1);
        assertEquals("You try divide by 0!", exception);

    }

    //======================
    // HELPERS METHOD
    //======================

    private List<Instruction> getInstructionsList() {
        List<Instruction> instructions = new ArrayList<>();
        Instruction instruction1 = new Instruction(InstructionsKeywords.ADD, 2);
        Instruction instruction2 = new Instruction(InstructionsKeywords.SUBTRACT, 3.1);
        Instruction instruction3 = new Instruction(InstructionsKeywords.MULTIPLY, 4);
        Instruction instruction4 = new Instruction(InstructionsKeywords.DIVIDE, 5.0);
        Instruction instruction6 = new Instruction(InstructionsKeywords.APPLY, 7.4);

        instructions.add(instruction1);
        instructions.add(instruction2);
        instructions.add(instruction3);
        instructions.add(instruction4);
        instructions.add(instruction6);

        return instructions;
    }

    private List<Instruction> getInstructionsListWithDivideByZero() {
        List<Instruction> instructions = new ArrayList<>();
        Instruction instruction1 = new Instruction(InstructionsKeywords.ADD, 2);
        Instruction instruction2 = new Instruction(InstructionsKeywords.SUBTRACT, 3.1);
        Instruction instruction3 = new Instruction(InstructionsKeywords.MULTIPLY, 4);
        Instruction instruction4 = new Instruction(InstructionsKeywords.DIVIDE, 0.0);
        Instruction instruction6 = new Instruction(InstructionsKeywords.APPLY, 7.4);

        instructions.add(instruction1);
        instructions.add(instruction2);
        instructions.add(instruction3);
        instructions.add(instruction4);
        instructions.add(instruction6);

        return instructions;
    }
}
