package challenge;

public class ConcurrencyTest {
    private static final long count = 1000000l;
    public static void main(String[] args) throws InterruptedException{
        concurrency();
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a++;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b++;
        }

        thread.join();
        long end = System.currentTimeMillis();
        System.out.printf("count:%d,concurrency-time:%d ms\n", count, end - start);
    }

    private static void serial() {
        int a = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            a++;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b++;
        }

        long end = System.currentTimeMillis();
        System.out.printf("count:%d,serial-time:%d ms\n", count, end - start);

    }
}