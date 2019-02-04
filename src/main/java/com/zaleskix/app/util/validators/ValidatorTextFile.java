package com.zaleskix.app.util.validators;

import com.zaleskix.app.models.InstructionsKeywords;
import com.zaleskix.app.util.readers.IReadDataFromFile;

import java.io.BufferedReader;
import java.io.IOException;


public class ValidatorTextFile implements IValidator {

    private boolean isApplyKeywordPresent = false;
    IReadDataFromFile readDataFromTextFile;

    public ValidatorTextFile(IReadDataFromFile readDataFromTextFile) {
        this.readDataFromTextFile = readDataFromTextFile;
    }

    @Override
    public boolean validateFile(String fileName) throws IOException {
        boolean fileIsEmpty = true;

        BufferedReader reader = readDataFromTextFile.readFile(fileName);
        String line;

        while ((line = reader.readLine()) != null) {
            fileIsEmpty = false;
            validateThatOnlyOneApplyKeywordIsPresentInFileAndIsPresentOnLastLine(line);
            validateThatLineInFileContainOnlyInstructionAndNumber(line);
            validateThatOnlyCorrectKeywordsArePresentInFile(line);
            validateThatSecondArgumentInLineIsNumber(line);
        }

        if (!isApplyKeywordPresent && !fileIsEmpty) {
            throw new IllegalArgumentException("File not contains APPLY keyword");
        }
        return !fileIsEmpty;
    }

    private void validateThatOnlyOneApplyKeywordIsPresentInFileAndIsPresentOnLastLine(String line) {

        String[] wordsInLine = line.split(" ");

        if (InstructionsKeywords.APPLY.name().equals(wordsInLine[0].toUpperCase())) {

            if (this.isApplyKeywordPresent) {
                throw new IllegalArgumentException("Apply keyword occurs more than once ");
            }

            this.isApplyKeywordPresent = true;
        } else if (isApplyKeywordPresent) {
            throw new IllegalArgumentException("Apply keyword is not on last line in file");
        }
    }

    private void validateThatOnlyCorrectKeywordsArePresentInFile(String line) {
        String[] wordsInLine = line.split(" ");
        boolean isKeyword = false;

        for (InstructionsKeywords c : InstructionsKeywords.values()) {
            if (c.name().equals(wordsInLine[0].toUpperCase())) {
                isKeyword = true;
                break;
            }
        }

        if (!isKeyword) {
            throw new IllegalArgumentException("In file are present words that are not defined instruction");

        }
    }

    private void validateThatLineInFileContainOnlyInstructionAndNumber(String line) {

        String[] wordsInLine = line.split(" ");

        if (wordsInLine.length != 2) {
            throw new IllegalArgumentException("In file are present line that contain something more or less than instruction and number");
        }
    }

    private void validateThatSecondArgumentInLineIsNumber(String line) {

        String[] wordsInLine = line.split(" ");

        if (!isDouble(wordsInLine[1])) {
            throw new IllegalArgumentException("In file are present line with second argument that not double");
        }
    }

    private boolean isDouble(String input) {

        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }


}
