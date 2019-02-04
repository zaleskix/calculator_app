package com.zaleskix.app.util;

import com.zaleskix.app.models.Instruction;
import com.zaleskix.app.models.InstructionsKeywords;
import com.zaleskix.app.util.readers.IReadDataFromFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDataProcessor {

    private IReadDataFromFile readDataFromTextFile;

    public UserDataProcessor(IReadDataFromFile readDataFromTextFile) {
        this.readDataFromTextFile = readDataFromTextFile;
    }

    public List<Instruction> readUserDataFromFileAndReturnListOfInstructionObject(String fileName) throws IOException {

        BufferedReader reader = readDataFromTextFile.readFile(fileName);
        String line;

        List<Instruction> instructions = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            Instruction instruction = new Instruction();
            String[] wordsInLine = line.split(" ");
            instruction.setInstruction(InstructionsKeywords.valueOf(wordsInLine[0].toUpperCase()));
            instruction.setNumber(Double.valueOf(wordsInLine[1]));

            instructions.add(instruction);
        }

        return instructions;
    }


}

