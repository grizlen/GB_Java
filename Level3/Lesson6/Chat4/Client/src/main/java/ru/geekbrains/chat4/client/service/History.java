package ru.geekbrains.chat4.client.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class History {
  private static final int MAX_HISTORY_LINES = 10;
  private static File file;
  private BufferedWriter bufferedWriter = null;

  public History(String filename) {
    file = new File(filename);
  }

  public List<String> getLast() {
    ArrayList<String> lines = new ArrayList<>();
    if (file.exists()) {
      try {
        lines = (ArrayList<String>) Files.lines(file.toPath()).collect(Collectors.toList());
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    int start = lines.size() > MAX_HISTORY_LINES ? lines.size() - MAX_HISTORY_LINES : 0;
    return (ArrayList<String>) lines.stream().skip(start).collect(Collectors.toList());
  }

  public boolean openWriter() {
    try {
      bufferedWriter = new BufferedWriter(new FileWriter(file));
      return true;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public void append(String line){
    if (bufferedWriter != null) {
      try {
        bufferedWriter.write(line + "\n");
        bufferedWriter.flush();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void close(){
    try {
      bufferedWriter.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
