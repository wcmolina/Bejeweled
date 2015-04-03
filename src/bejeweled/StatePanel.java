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
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
/*
 this class keeps track of some interesting game
 information and display them in labels
 */

public class StatePanel extends JPanel {

    private JLabel textScore = new JLabel("Score");
    private JLabel score = new JLabel("0", JLabel.CENTER);
    private JLabel textLevel = new JLabel("Level");
    private JLabel level = new JLabel("1", JLabel.CENTER);
    private JLabel textCombo = new JLabel("Max Combo");
    private JLabel combo = new JLabel("0", JLabel.CENTER);
    private JLabel textRow = new JLabel("row", JLabel.CENTER);
    private JLabel row = new JLabel("-1", JLabel.CENTER);
    private JLabel textCol = new JLabel("column", JLabel.CENTER);
    private JLabel col = new JLabel("-1", JLabel.CENTER);

    public StatePanel() {
        setLayout(new GridLayout(6, 2));
        add(textScore);
        add(score);
        add(textLevel);
        add(level);
        add(textCombo);
        add(combo);
        add(textRow);
        add(row);
        add(textCol);
        add(col);
        for (Component comp : this.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(Color.LIGHT_GRAY);
            }
        }
        setBackground(Color.decode("#232830"));
    }

    public void setScore(int score) {
        this.score.setText(Integer.toString(score));
    }

    public void setLevel(int level) {
        this.level.setText(Integer.toString(level));
    }

    public void setCombo(int combo) {
        this.combo.setText(Integer.toString(combo));
    }

    public void setRow(int row) {
        this.row.setText(Integer.toString(row));
    }

    public void setColumn(int col) {
        this.col.setText(Integer.toString(col));
    }
}
