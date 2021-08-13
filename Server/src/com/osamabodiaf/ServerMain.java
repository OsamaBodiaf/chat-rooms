package com.osamabodiaf;

public class ServerMain {

    public static void main(String[] args) {
        new Thread(new Server(4444)).start();
    }
}
