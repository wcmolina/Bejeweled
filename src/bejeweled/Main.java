/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled;

/**
 *
 * @author Wilmer Carranza
 */
import java.awt.AWTException;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        windowSetup();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: UIManager did not find Windows Look and Feel" + ex.getMessage());
        }
        // Content Pane Setup
        Container content = getContentPane();
        StatePanel sPanel = new StatePanel();
        Game newGame = new Game(sPanel);
        ControlPanel cPanel = new ControlPanel(newGame);
        content.add(newGame, BorderLayout.CENTER);

        //hidden score for now, planning to move it to control panel.. se ve mejor
        //content.add(sPanel, BorderLayout.WEST);
        content.add(cPanel, BorderLayout.SOUTH);
        content.setBackground(Color.decode("#232830"));

        //set visible
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        //ROBOT
        /*
         try {
         Thread.sleep(600);
         click(cPanel.newGameButton.getLocationOnScreen().x + 10, cPanel.newGameButton.getLocationOnScreen().y + 10);
         while (cPanel.game.noMoves == false) {
         click(cPanel.auto.getLocationOnScreen().x + 10, cPanel.auto.getLocationOnScreen().y + 10);
         }

         } catch (AWTException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InterruptedException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    }

    private void windowSetup() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth, screenHeight);
        setLocationByPlatform(true);
        setTitle("Bejeweled");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void click(int x, int y) throws AWTException {
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.delay(2000);
    }
}
