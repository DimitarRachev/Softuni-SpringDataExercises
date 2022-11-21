package com.example.car_dealer.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.car_dealer.constant.Path;

public class FileManipulationFactory {

  public static FileReader getFileReader(String path) throws IOException {
    return new FileReader(path);
  }

  public static  void writeToFile(String object, String path) throws IOException {
    FileWriter writer = new FileWriter(path);
    writer.write(object);
    writer.close();
  }
}
