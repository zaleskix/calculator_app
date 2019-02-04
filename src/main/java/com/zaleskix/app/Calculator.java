package com.zaleskix.app;

import com.zaleskix.app.models.Instruction;
import com.zaleskix.app.util.UserDataProcessor;
import com.zaleskix.app.util.validators.IValidator;

import java.io.IOException;
import java.util.List;

public class Calculator {

    private IValidator validator;
    private UserDataProcessor userDataProcessor;
    private double returnedOutput;

    public Calculator(IValidator validator, UserDataProcessor userDataProcessor) {
        this.validator = validator;
        this.userDataProcessor = userDataProcessor;
    }

    public double calculateOutput() throws IOException {

        //Check that file is valid
        if (validator.validateFile(App.FILE_NAME)) {

            //If vile is valid save data from file to list of Instruction
            List<Instruction> instructionList = userDataProcessor.readUserDataFromFileAndReturnListOfInstructionObject(App.FILE_NAME);

            //save value last value
            final double[] tempOutput = {instructionList.get(instructionList.size() - 1).getNumber()};

            //for each instruction in list calculate actual value
            instructionList.iterator().forEachRemaining(instruction -> {
                tempOutput[0] = calculateCurrentlyInstruction(instruction, tempOutput[0]);

            });


            returnedOutput = (double) Math.round(tempOutput[0] * 100) / 100;

            return returnedOutput;
        }

        //If file is not valid somehow throw exception
        throw new IllegalArgumentException("File is not valid");
    }

    private double calculateCurrentlyInstruction(Instruction instruction, double actualOutput) {

        switch (instruction.getInstruction()) {
            case ADD:
                return actualOutput + instruction.getNumber();
            case SUBTRACT:
                return actualOutput - instruction.getNumber();
            case MULTIPLY:
                return actualOutput * instruction.getNumber();
            case DIVIDE:
                if (instruction.getNumber() == Double.valueOf(0)) {
                    throw new IllegalArgumentException("You try divide by 0!");
                }
                return actualOutput / instruction.getNumber();
            case APPLY:
                return actualOutput;
            default:
                throw new IllegalArgumentException();
        }
    }


}
