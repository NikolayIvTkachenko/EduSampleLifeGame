/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author tkach
 */
public class Board extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private Cell[] cells;
    private Set<Integer> cellsToDie = new HashSet<>();
    private Set<Integer> cellsToBorn = new HashSet<>();
    private int countLiveNeighbours = 0;
    private TimePanel timePanel;
    
    public Board(TimePanel timePanel){
        this.timePanel = timePanel;
        cells = new Cell[Constants.BOARD_COLS*Constants.BOARD_ROWS];
        setLayout(new GridLayout(Constants.BOARD_ROWS,  Constants.BOARD_COLS));
        
        printBoard();
        
    }

    public void refreshBoard(int id){
        cells[id].setAlive(true);
        cells[id].setBackground(Color.decode(Constants.GREEN_COLOR));
    }
    
    public void newIteration(){
        
        for(int i = 0; i<Constants.BOARD_ROWS*Constants.BOARD_COLS; i++){
            
            countLiveNeighbours=0;
            
            if(i <= Constants.BOARD_ROWS-1 
                    || i>((Constants.BOARD_ROWS-1)*Constants.BOARD_COLS-1) 
                    || (i % Constants.BOARD_ROWS) - (Constants.BOARD_ROWS-1)==0 || i % Constants.BOARD_ROWS==0){
                continue;
            }
            
            if(cells[i-1].isAlive())countLiveNeighbours++;
            if(cells[i+1].isAlive())countLiveNeighbours++;
            
            if(cells[i-Constants.BOARD_ROWS+1].isAlive())countLiveNeighbours++;
            if(cells[i-Constants.BOARD_ROWS-1].isAlive())countLiveNeighbours++;
            if(cells[i-Constants.BOARD_ROWS].isAlive())countLiveNeighbours++;
            
            if(cells[i+Constants.BOARD_ROWS+1].isAlive())countLiveNeighbours++;
            if(cells[i+Constants.BOARD_ROWS-1].isAlive())countLiveNeighbours++;
            if(cells[i+Constants.BOARD_ROWS].isAlive())countLiveNeighbours++;
            
            if(countLiveNeighbours == 3 && !cells[i].isAlive())cellsToBorn.add(i);
            if(countLiveNeighbours < 2 || countLiveNeighbours > 3){
                cellsToDie.add(i);
            }
            
            if(countLiveNeighbours == 3 && cells[i].isAlive()) cellsToBorn.add(i);
            
        }
        
        repainBoard();
        timePanel.refresh();
        
    }
    
    
    
    
    public void repainBoard(){
        for(Integer integer:cellsToBorn){
            cells[integer].setBackground(Color.decode(Constants.GREEN_COLOR));
            cells[integer].setAlive(true);
        }
        
        for(Integer integer:cellsToDie){
            cells[integer].setBackground(Color.WHITE);
            cells[integer].setAlive(false);
        }
                
        cellsToBorn.clear();
        cellsToDie.clear();
                
    }
    
    
    private void printBoard() {
        for(int i=0; i<Constants.BOARD_COLS*Constants.BOARD_ROWS;i++){
            cells[i] = new Cell(i, this);
            cells[i].setAlive(false);
            add(cells[i]);
        }
    }
    
    public void resetBoard(){
        for(int i=0; i<Constants.BOARD_COLS*Constants.BOARD_ROWS; i++){
            cells[i].setAlive(false);
            cells[i].setBackground(Color.WHITE);
        }
    }
}
