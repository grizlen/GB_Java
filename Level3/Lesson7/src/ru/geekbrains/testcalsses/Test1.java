package ru.geekbrains.testcalsses;

import ru.geekbrains.annotations.AfterSuite;
import ru.geekbrains.annotations.BeforeSuite;
import ru.geekbrains.annotations.Test;

public class Test1 {
  @Test
  @BeforeSuite
  private void simpleFirst(){
    System.out.println("simpleFirst");
  }

  @Test
  private void simple3(){
    System.out.println("simple3");
  }

  @Test(priority = 5)
  private void simple2(){
    System.out.println("simple2");
  }

  @Test(priority = 1)
  private void simple1(){
    System.out.println("simple1");
  }

  @Test
  @AfterSuite
  private void simpleLast(){
    System.out.println("simpleLast");
  }
}
