/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tkach
 */
public class TimePanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    public int count = 0;
    
    public TimePanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        timeLabel = new JLabel("Time comes here...");
        
        add(timeLabel);
    }
    
    public void refreshCounter(){
        count = 0;
        refresh();
    }
    
    public void refresh(){
        timeLabel.setText("Genereation: "+count);
    }
    
}
