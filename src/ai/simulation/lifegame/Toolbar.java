/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tkach
 */
public class Toolbar extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    
    private JButton startButton;
    private JButton restartButton;
    
    private ButtonListener buttonListener;
    
    public Toolbar(){
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createEtchedBorder());
        
        startButton = new JButton(Constants.START);
        restartButton = new JButton(Constants.RESTART);
        
        startButton.addActionListener(this);
        restartButton.addActionListener(this);
        
        add(startButton);
        add(restartButton);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if((JButton)event.getSource() == startButton && buttonListener != null){
            buttonListener.startClicked();
        }else if((JButton)event.getSource() == restartButton && buttonListener != null){
            buttonListener.restartClicked();
        }
    }
    
    public void setButtonListener(ButtonListener buttonListener){
        this.buttonListener = buttonListener;
    }
    
    public void setStartButton(boolean bool){
        startButton.setEnabled(bool);
    }
    
    public void setRestartButton(boolean bool){
        restartButton.setEnabled(bool);
    }
    
}
