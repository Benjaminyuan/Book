package jmm;

import java.util.ArrayList;
import java.util.List;

public class MultiAdd{
    public static void main(String[] args){
        final Counter counter = new Counter();
        List<Thread> ta = new ArrayList<Thread>(10);
        for(int i =0;i<ta.size();i++){
            Thread td = new Thread(new Runnable(){
                @Override
                public void run(){
                    for(int i=0;i<100;i++){
                        System.out.println(i);
                        counter.add();
                    }
                }
            });
            td.start();
            ta.add(td);
       }
    //    for(Thread t:ta){
    //        t.start();
    //    }
       for(Thread t : ta){
           try{
              t.join();
           }catch(Exception e){
               e.printStackTrace();
           }
       }
        System.out.println(counter.getCount());
    }
}
class Counter implements Runnable{
    private int count =0;
    public void add(){
        count = count +1;
    }
    @Override
    public void run(){
        for(int i=0;i<200;i++){
            add();    
        }
    }
 
    public int getCount(){
        return count;
    } 
}
