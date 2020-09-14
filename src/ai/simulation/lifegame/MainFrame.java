/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.simulation.lifegame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author tkach
 */
public class MainFrame extends JFrame implements ButtonListener{

    private static final long serialVersionUID = 1L;
    
    private Toolbar toolbar;
    private Board board;
    private TimePanel timePanel;
    private ExecutorService  executor;
    
    
    public MainFrame(){
        super(Constants.APP_NAME);
        
        setJMenuBar(createMenuBar());
        
        toolbar = new Toolbar();
        timePanel = new TimePanel();
        board = new Board(timePanel);
        
        toolbar.setButtonListener(this);
        
        add(toolbar, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(timePanel, BorderLayout.SOUTH);
        
        setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        
    }
    
    
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");
        
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);
        
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit?");
                if(action == JOptionPane.OK_OPTION){
                    System.gc();
                    System.exit(0);
                }
            }
        });
        return menuBar;
    }
    
    @Override
    public void startClicked() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                toolbar.setStartButton(false);
                toolbar.setRestartButton(true);
            }
        });
        Controller.startThread();
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Controller(board));
    }

    @Override
    public void restartClicked() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                toolbar.setStartButton(true);
                toolbar.setRestartButton(false);
            }
        });
        executor.shutdown();
        Controller.stopThread();
        board.resetBoard();
        timePanel.refreshCounter();
    }
    
    
    
}
