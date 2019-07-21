package io;
import java.io.*;
public class InData{
    public static void main(String[] args) throws IOException{
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        String s ;
        while((s = bReader.readLine()) != null && s.length()!=0){
            System.out.println(s);
        }
    }
}