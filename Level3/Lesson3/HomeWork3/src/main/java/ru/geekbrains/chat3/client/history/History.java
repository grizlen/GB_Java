package ru.geekbrains.chat3.client.history;

import ru.geekbrains.chat3.client.GUI.ChatFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class History implements AutoCloseable {
  private final File file;
  private BufferedWriter bufferedWriter;

  public History(String path, ChatFrame chatFrame) throws IOException {
    file = new File(path);
    ArrayList<String> lastLines;
    lastLines = open();
    bufferedWriter = new BufferedWriter(new FileWriter(file));
    if (lastLines != null){
      lastLines.forEach(this::write);
      lastLines.forEach(chatFrame::append);
    }
  }

  public void close(){
    if (bufferedWriter != null) {
      try {
        bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private ArrayList<String> open() {
    ArrayList<String> lines = new ArrayList<>();
    if (file.exists()) {
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
        lines = (ArrayList<String>) bufferedReader.lines().map(String::new).collect(Collectors.toList());
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }
    int start = lines.size() > 10 ? lines.size() - 10 : 0;
    return (ArrayList<String>) lines.stream().skip(start).collect(Collectors.toList());
  }

  public void write(String message) {
    try {
      bufferedWriter.write(message + "\n");
      bufferedWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
