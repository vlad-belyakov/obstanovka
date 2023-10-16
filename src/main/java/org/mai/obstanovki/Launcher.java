package org.mai.obstanovki;

public class Launcher{

    public static void main(String[] args) {
        new Thread(new MainScreen(), "main_screen_thread").start();
    }
}
