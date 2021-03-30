package ru.geekbrains;

import ru.geekbrains.annotations.AfterSuite;
import ru.geekbrains.annotations.BeforeSuite;
import ru.geekbrains.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tester {
  private static final Class
      BEFORE_CLASS = BeforeSuite.class,
      AFTER_CLASS = AfterSuite.class,
      TEST_CLASS = Test.class;

  private static Constructor testedConstructor = null;

  public static void start(Class testClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
    if (testClass == null) { return;}
    testedConstructor = testClass.getConstructor();
    Method[] methods = testClass.getDeclaredMethods();
    List<Method> ml = Arrays.stream(methods).filter(method -> method.getAnnotation(TEST_CLASS) != null).collect(Collectors.toList());
    Method before = null, after = null;
    for (Method m : ml) {
      if (getMethodAnnotation(m, BEFORE_CLASS) != null) {
        if (before != null) {throw new RuntimeException("Annotation @BeforeSuite must be single."); }
        before = m;
      }
    }
    for (Method m : ml) {
      if (m.getAnnotation(AFTER_CLASS) != null) {
        if (after != null) {throw new RuntimeException("Annotation @AfterSuite must be single."); }
        after = m;
      }
    }
    if (before != null){
      System.out.println("BeforeSuite:");
      runMethod(before);
    }
    System.out.println("Run tests:");
    ml.stream()
        .filter(method -> (getMethodAnnotation(method, BEFORE_CLASS) == null) & (getMethodAnnotation(method, AFTER_CLASS) == null))
        .sorted(methodComparator)
//        .sorted(Tester::compareMethod)
        .forEach(Tester::runMethod);
    if (after != null){
      System.out.println("AfterSuite:");
      runMethod(after);
    }
  }

  private static Annotation getMethodAnnotation(Method method, Class annotationClass) {
    return method.getAnnotation(annotationClass);
  }

  private static Comparator<Method> methodComparator =
      (o1, o2) -> o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();

  private static void runMethod(Method method) {
    try {
      int modifiers = method.getModifiers();
      if (Modifier.isAbstract(modifiers)) {
        System.out.println("Can not run abstract method: " + method);
      } else {
        if (!Modifier.isPublic(modifiers)) {
          method.setAccessible(true);
        }
        Object inst = null;
        if (!Modifier.isStatic(modifiers)) {
          inst = testedConstructor.newInstance();
        }
        System.out.println("run: " + method);
        method.invoke(inst, getMethodParameters(method));
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  // можно конечно дальше залесть, но думаю это уже выходит за рамки задания.
  private static Object[] getMethodParameters(Method method){
    return new Object[method.getParameters().length];
  }
}
