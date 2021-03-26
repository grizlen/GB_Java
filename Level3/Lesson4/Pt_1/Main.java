public class Main {
    private static final Object mon = new Object();
    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(() -> print('A', 'B')).start();
        new Thread(() -> print('B', 'C')).start();
        new Thread(() -> print('C', 'A')).start();
    }

    private static void print(char letter, char next){
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != letter) {
                        mon.wait();
                    }
                    System.out.print(letter);
                    currentLetter = next;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
