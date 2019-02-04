package com.zaleskix.app;

import com.zaleskix.app.models.Instruction;
import com.zaleskix.app.models.InstructionsKeywords;
import com.zaleskix.app.util.UserDataProcessor;
import com.zaleskix.app.util.readers.IReadDataFromFile;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test for UserDataProcessor class
 */
public class UserDataProcessorTest {

    @Mock
    private BufferedReader reader;

    @Mock
    private IReadDataFromFile readDataFromTextFile;

    private UserDataProcessor userDataProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //Test case: If file is valid, userDataProcessor return the correct list of Instructions
    @Test
    public void shouldReturnListOfInstructionsWithCorrectDataWhenFileIsValid() throws IOException {

        when(readDataFromTextFile.readFile(anyString())).thenReturn(reader);
        when(reader.readLine()).thenReturn("Add 1", "Multiply 1", "Apply 2", null);

        userDataProcessor = new UserDataProcessor(readDataFromTextFile);
        List<Instruction> instructions = userDataProcessor.readUserDataFromFileAndReturnListOfInstructionObject("test");

        assertEquals(3, instructions.size());
        assertEquals(InstructionsKeywords.ADD, instructions.get(0).getInstruction());
        assertEquals(1, instructions.get(0).getNumber(), 0.1);
        assertEquals(InstructionsKeywords.MULTIPLY, instructions.get(1).getInstruction());
        assertEquals(1, instructions.get(1).getNumber(), 0.1);
        assertEquals(InstructionsKeywords.APPLY, instructions.get(2).getInstruction());
        assertEquals(2, instructions.get(2).getNumber(), 0.1);
    }

}
