/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author tkach
 */
public class Cell extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private int id;
    private Board board;
    private boolean isAlive = false;
    
    public Cell(int id, Board board){
        this.id = id;
        this.board = board;
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                setAlive(true);
                Cell.this.board.refreshBoard(id);
            }
        });
    }
    
    public boolean isAlive(){
        return isAlive;
    }
    
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    
    @Override
    public String toString(){
        return "Cell "+id;
    }
    
}
