package com.homework.serversystemcheck;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Thread thread;
    

    public Server() {
		super();
	}

    public void start() throws IOException {
        thread = new Thread() {
          public void run() {
             try {
                  ServerSocket server = new ServerSocket(8000);
                  int num=0;
                  while (! isInterrupted()) {
                       Socket client = server.accept();
                       try {
                    	   Runtime r = Runtime.getRuntime();
                    	   StringBuilder sb = new StringBuilder();
                    	   sb.append("Connection Established!!!" + "\r\n");
                    	   sb.append("Total memory: " + r.totalMemory() + "\r\n");
                    	   sb.append("Free memory: " + r.freeMemory() + "\r\n");
                    	   sb.append("System property OS "+System.getProperty("os.name") + "\r\n");
                    	   sb.append("System property OS ver "+System.getProperty("os.version") + "\r\n");
                    	   sb.append("Request number "+ num++ + "\r\n");
                    	   client.getOutputStream().write(sb.toString().getBytes());
                       } finally {
                    	   client.getOutputStream().write(("end").getBytes());
                    	   client.close();
                      }
                  }
                  server.close();
            } catch (Exception e) {
                  e.printStackTrace();
            }
         }
    };
    thread.start();
}

}



