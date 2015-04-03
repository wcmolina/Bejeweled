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
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
/*
 This class records the state of the game
 It will be extended to support animation 
 and the various states of the bejeweled game.
 */

public final class Game extends JComponent {

    private StatePanel sPanel;
    private Board gameBoard;
    private Algorithms solver;
    private Animation animation;
    private Tile focus;
    private boolean started;
    private int score;
    private int level;
    private int combo;
    public static ImageLibrary imageLibrary = new ImageLibrary();
    public static SoundLibrary soundLibrary = new SoundLibrary();
    public BufferedImage boardImg;
    public ImageIcon boardIcon;
    public boolean noMoves = false;

    public Game(StatePanel sPanel) {
        try {
            boardImg = ImageIO.read(new File("resources/board4.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.boardIcon = new ImageIcon();
        this.boardIcon.setImage(this.boardImg);
        started = false;
        this.sPanel = sPanel;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));
        this.addMouseListener(new MouseListener(this));
    }

    public void initGame() {
        gameBoard = new Board(this);
        solver = new Algorithms(gameBoard, this);
        animation = new Animation(this, gameBoard, null);
        // initialize game
        gameBoard.initAll();
        while (!solver.isStable()) {
            solver.rmChains();
        }
        //set up state information
        started = true;
        focus = null;
        score = 0;
        combo = 0;
        level = 1;
        sPanel.setScore(score);
        sPanel.setCombo(combo);
        sPanel.setLevel(level);
        sPanel.setRow(-1);
        sPanel.setColumn(-1);
        repaint();
        Game.soundLibrary.playAudio("fall");
    }

    public void updateGame() {
        if (!solver.isStable()) {
            solver.markDeleted();
            solver.calculateDrop();
            animation.setType(Animation.animType.CASCADE);
            animation.animateCascade();
            Game.soundLibrary.playAudio("fall");
        }
    }

    public void cleanBoard() {
        solver.applyDrop();
        solver.fillEmpty();
        solver.endCascade();
    }

    public void addScore(int points) {
        if ((this.score + points) > 1000) {
            this.level++;
            sPanel.setLevel(this.level);
            this.score = 0;
            sPanel.setScore(this.score);
        } else {
            this.score += points;
            sPanel.setScore(score);
        }

    }

    public void setCombo(int combo) {
        if (combo > this.combo) {
            this.combo = combo;
            sPanel.setCombo(combo);
        }
    }

    public void clickPerformed(int click_x, int click_y) {
        System.out.println("x: " + click_x + " y: " + click_y);
        sPanel.setColumn(click_x);
        sPanel.setRow(click_y);

        Tile clicked = gameBoard.getTileAt(click_y, click_x);
        if (focus == null) {
            focus = clicked;
            clicked.inFocus = true;
            Game.soundLibrary.playAudio("select");
        } else {
            if (focus.equals(clicked)) {
                clicked.inFocus = false;
                focus = null;
            } else {
                if (focus.isNeighbor(clicked)) {
                    focus.inFocus = false;
                    swapTiles(focus, clicked);
                    focus = null;
                } else {
                    focus.inFocus = false;
                    focus = clicked;
                    clicked.inFocus = true;
                }
            }
        }
    }

    private void swapTiles(Tile t1, Tile t2) {
        animation.setType(Animation.animType.SWAP);
        animation.animateSwap(t1, t2);
    }

    public void paintComponent(Graphics g) {
        this.boardIcon.paintIcon(null, g, 0, 0);
        if (started) {
            drawGems(g);
        }
    }

    private void drawGems(Graphics g) {
        int row, col;
        for (row = 0; row < 8; row++) {
            for (col = 0; col < 8; col++) {
                Tile tile = gameBoard.getTileAt(row, col);
                tile.draw(g);
            }
        }
    }

    public void auto() {
        //checking columns
        int row, col;
        for (row = 0; row < 8; row++) {
            for (col = 0; col < 7; col++) {
                gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row, col + 1));
                if (solver.isStable()) {
                    gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row, col + 1)); //restore
                } else {
                    int tmp = col + 1;
                    gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row, col + 1)); //restore
                    System.out.println("found move at row: " + row + " switch col: " + col + " with col: " + tmp);
                    clickPerformed(tmp, row);
                    clickPerformed(col, row);
                    return;
                }
            }
        }

        //checking rows
        for (col = 0; col < 8; col++) {
            for (row = 0; row < 7; row++) {
                gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row + 1, col));
                if (solver.isStable()) {
                    gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row + 1, col)); //restore
                } else {
                    int tmp = row + 1;
                    gameBoard.swapTile(gameBoard.getTileAt(row, col), gameBoard.getTileAt(row + 1, col)); //restore
                    System.out.println("found move at col: " + col + " switch row: " + row + " with row: " + tmp);
                    clickPerformed(col, row);
                    clickPerformed(col, tmp);
                    return;
                }
            }
        }
        //si llega hasta aqui es porque.... no more moves baby!
        noMoves = true;
        JOptionPane.showMessageDialog(getRootPane(), "No more moves! Final score was: " + score + ", level: " + level);
    }

}
