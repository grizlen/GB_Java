import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  public void testGet4Trailing1(){
    Assertions.assertArrayEquals(new int[]{5, 6}, Main.get4Trailing(1, 2, 3, 4, 5, 6));
  }

  @Test
  public void testGet4Trailing2(){
    Assertions.assertArrayEquals(new int[]{5, 6}, Main.get4Trailing(1, 2, 4, 4, 5, 6));
  }

  @Test
  public void testGet4Trailing3(){
    Assertions.assertArrayEquals(new int[]{5, 6}, Main.get4Trailing(4, 2, 3, 4, 5, 6));
  }

  @Test
  public void testGet4Trailing4(){
    Assertions.assertArrayEquals(new int[0], Main.get4Trailing(1, 2, 3, 5, 6));
  }

  @Test
  public void testGet4Trailing5(){
    Assertions.assertArrayEquals(new int[0], Main.get4Trailing(4));
  }

  @Test
  public void testGet4TrailingThrows(){
    Assertions.assertThrows(RuntimeException.class, Main::get4Trailing);
  }

  @Test
  public void testCheckIs1or4_1(){
    Assertions.assertTrue(Main.checkIs1or4(1, 2, 3, 4));
  }

  @Test
  public void testCheckIs1or4_2(){
    Assertions.assertTrue(Main.checkIs1or4(1, 2, 3));
  }

  @Test
  public void testCheckIs1or4_3(){
    Assertions.assertTrue(Main.checkIs1or4(2, 3, 4));
  }

  @Test
  public void testCheckIs1or4_4(){
    Assertions.assertFalse(Main.checkIs1or4(2, 3, 5));
  }

}
