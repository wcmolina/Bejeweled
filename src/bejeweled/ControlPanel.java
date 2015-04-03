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
import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class ControlPanel extends JPanel implements ActionListener {

    public JButton newGameButton = new JButton("New Game");
    public Game game;
    private JButton exit = new JButton("Exit");
    public JButton auto = new JButton("Auto");

    public ControlPanel(Game game) {
        this.game = game;
        add(newGameButton);
        newGameButton.addActionListener(this);
        add(exit);
        exit.addActionListener(this);
        add(auto);
        auto.addActionListener(this);
        for (Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                comp.setBackground(Color.decode("#1c1f26"));
            }
        }
        setBackground(Color.decode("#1c1f26"));

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGameButton)) {
            game.initGame();
        }
        if (e.getSource().equals(auto)) {
            game.addScore(100);
            game.auto();
            /*
             >>>> De aqui en adelante es el proyecto... buscar la forma que juegue solo, o sea que busque jugadas y las haga.
             update 1: se logro encontrar la forma de decirle al usuario DONDE hay jugadas, pero no las hace (es decir, no se refleja en el tablero).... ver arreglos despues.
             update 2: se agrego metodo findMove() para que busque jugadas en filas o columnas. Una vez encontradas hace la animacion.
             update 3: se quito findMove() porque no funcionaba en ciertos casos.......
             update 4: added auto() method, basically it finds moves and updates the board + does animations as well... Version super mejorada de findMove(). Almost done!
             */
        }
        if (e.getSource().equals(exit)) {
            System.exit(0);
        }
    }
}
