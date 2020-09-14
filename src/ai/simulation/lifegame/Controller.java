/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tkach
 */
public class Controller implements Runnable {

    private Board board;
    private volatile static boolean keepGoing = true;
    
    public Controller(Board board){
        this.board = board;
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted() && keepGoing){
            board.newIteration();
            try {
                Thread.sleep(Constants.TIME_LAG);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                return;
            }
        }
    }
    
    public static void startThread(){
        keepGoing = true;
    }
    
    public static void stopThread(){
        Thread.currentThread().interrupt();
        keepGoing = false;
    }
    
}
