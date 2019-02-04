package com.zaleskix.app.util.validators;


import java.io.IOException;

public interface IValidator {

    boolean validateFile(String fileName) throws IOException;
}
