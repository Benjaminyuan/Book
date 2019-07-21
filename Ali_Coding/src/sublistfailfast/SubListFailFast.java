package sublistfailfast;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SubListFailFast {
    public static void main(String[] arg ) {
        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");
        List branchList = masterList.subList(0,3);
//        masterList.remove(0);
//        masterList.add("ten");
//        masterList.clear();

//
//        branchList.clear();
//        branchList.add("six");
//        branchList.add("seven");
//        branchList.remove(0);
        for(Object t: masterList){
           if (t.equals("four")){
               masterList.remove(t);
           }
        }
        int[][] array = new int[][]{{1,2},{3,4},{5,6}};
        System.out.println(array.length);
//        System.out.println(masterList);
//        List<Long> copy = new CopyOnWriteArrayList<>();
//        // cpu 风扇声音好大。。
//        Long start = System.nanoTime();
//        for(int i=0; i<20*10000; i++){
//            copy.add(System.nanoTime());
//        }
//        Long end = System.nanoTime();
//        System.out.println(end - start );
    }

}
