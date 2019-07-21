package io;
import java.io.*;
public class BinaryFile{
    public byte[] read(File file) throws IOException{
        BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
        try{
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        }finally{
            bf.close();
        }
    }
}