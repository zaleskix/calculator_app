package com.zaleskix.app.util.readers;

import com.zaleskix.app.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class ReadDataFromTextFile implements IReadDataFromFile {


    @Override
    public BufferedReader readFile(String fileName) throws IOException {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name is empty");
        }

        InputStream file = App.class.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(file, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        return reader;

    }

}
