package com.zaleskix.app;


import com.zaleskix.app.util.UserDataProcessor;
import com.zaleskix.app.util.readers.IReadDataFromFile;
import com.zaleskix.app.util.readers.ReadDataFromTextFile;
import com.zaleskix.app.util.validators.IValidator;
import com.zaleskix.app.util.validators.ValidatorTextFile;

import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    public static final String FILE_NAME = "/instructions.txt";


    public static void main(String[] args) throws IOException, URISyntaxException {

        IReadDataFromFile readDataFromTextFile = new ReadDataFromTextFile();
        IValidator validator = new ValidatorTextFile(readDataFromTextFile);
        UserDataProcessor userDataProcessor = new UserDataProcessor(readDataFromTextFile);

        Calculator calculator = new Calculator(validator, userDataProcessor);

        double finalOutput = calculator.calculateOutput();
        System.out.println("Output is : " + finalOutput);

    }

}
