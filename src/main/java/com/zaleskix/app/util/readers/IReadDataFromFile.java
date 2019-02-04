package com.zaleskix.app.util.readers;

import java.io.BufferedReader;
import java.io.IOException;

public interface IReadDataFromFile {

    BufferedReader readFile(String fileName) throws IOException;
}
