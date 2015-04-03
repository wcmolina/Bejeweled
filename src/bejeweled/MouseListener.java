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
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
/*
 this class monitors user input on the game board
 */

public class MouseListener extends MouseInputAdapter {

    private Game gamePanel;

    public MouseListener(Game gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void mouseClicked(MouseEvent e) {
        int col = (e.getX() - 157) / 65;
        int row = (e.getY() - 40) / 65;
        if (col < 0) {
            col = 0;
        }
        if (col > 7) {
            col = 7;
        }
        if (row < 0) {
            row = 0;
        }
        if (row > 7) {
            row = 7;
        }
        gamePanel.clickPerformed(col, row);
        gamePanel.repaint();
    }
}
