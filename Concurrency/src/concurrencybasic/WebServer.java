package concurrencybasic;


import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import concurrencybasic.*;
public class WebServer{
    
    static ThreadPool<HttpHandler> threadPool = new DefaultThreadPool<HttpHandler>(1);
    static String  basePath;
    static ServerSocket serverSocket;
    static int port = 8080;
    public static void setPort(int port){
        if(port > 0 ){
            WebServer.port = port;
        }
    }
    public static void setBasePath(String basePath){
        if(basePath != null && new File(basepath).exists() && new File(basePath).isDirectory()){
            WebServer.basePath = basePath;
        }
    }
    public static void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null ;
        while((socket = serverSocket.accept())!= null){
            threadPool.excute(new HttpHandler(socket));
        }
        serverSocket.close();
    }
    static class HttpHandler implements Runnable{
        private Socket  socket;
        public HttpHandler(Socket socket){
            this.socket = socket ;
        }
        @Override
        public void run(){
            String line = null ;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null ;
            InputStream in = null;
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if(filePath.endsWith(".jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream bais = new ByteArrayOutputStream();
                    int i =0;
                    while((i = in.read()) != -1){
                        bais.write(i);
                    }
                    byte[] array = bais.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: "+ array.length);
                    out.println("");
                    socket.getOutputStream().write(array,0,array.length);
                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while((line = br.read()) != null){
                        out.println(line);
                    }
                }
                out.flush();
            }catch(Exception e){
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally{
                close(br,in,reader,out,socket);
            }
        }
        public static void close(Closeable...closeables){
            if(closeables != null ){
                for(Closeable closeable : closeables){
                    try{
                        closeable.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}