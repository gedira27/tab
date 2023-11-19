package com.example.menu.components;

import javafx.scene.control.ProgressBar;

import java.util.Random;

public class Hilo extends Thread{

    private ProgressBar pgbCorredor;
    public Hilo(String nombre, ProgressBar pgbCorredor){
        super(nombre);
        this.pgbCorredor = pgbCorredor;
    }
    @Override
    public void run() {
        super.run();
        try {
            double avance=0;
            while(avance <= 1) {
                sleep((long)(Math.random()*1500));
                avance += Math.random()/10;
                pgbCorredor.setProgress(avance);
                //System.out.println("Corredor " +this.getName() + " llegó al km  " + i);

            }
            //System.out.printf("Corredor " +this.getName() + " llegó a la meta");
        } catch (Exception e) {

        }
    }
}
