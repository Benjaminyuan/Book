package collectiontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToArraySpeedTest {
    private  static final int COUNT = 100*100*100;

    public static void main(String[] arg){
        List<Double> list = new ArrayList<Double>(COUNT);
        for (int i =0; i<COUNT; i++){
            list.add(i*1.0);
        }
        long start = System.nanoTime();
        Double[] notenoughArray = new Double[COUNT-1];

        list.toArray(notenoughArray);

        long middle = System.nanoTime();

        Double[] equalArray  = new Double[COUNT];

        list.toArray(equalArray);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
        long middle_2 = System.nanoTime();
        Double[] doubleArray = new Double[COUNT*2];
        list.toArray(doubleArray);
        long end = System.nanoTime();
        long notEnoughArrayTime = middle-start;
        long equalArrayTime = middle_2-middle;
        long doubleArrayTime = end-middle;
        System.out.println("1:"+notEnoughArrayTime+"ms");
        System.out.println("2:"+equalArrayTime);
        System.out.println("3:"+doubleArrayTime);

    }
}
