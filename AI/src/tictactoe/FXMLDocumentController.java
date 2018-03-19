package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import com.jfoenix.controls.JFXButton;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * MINIMAX ALGORITHM
 * @author JorgeDíaz
 */
public class FXMLDocumentController implements Initializable {

    @FXML private Button buttonPlay;
    @FXML private GridPane grid;
    static Timer timer;

    @FXML
    void buttonPlayOnAction(ActionEvent event) {
        TicTacToe.initialize();
        grid.getChildren().clear();
        buttonPlay.setDisable(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ButtonBoard button = new ButtonBoard(i, j);
                grid.add(button.getButton(), j, i);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonPlay.setCursor(Cursor.HAND);
        timer = new Timer(1000, (e) -> {if (TicTacToe.isTermined()) buttonPlay.setDisable(false);});
        timer.start();
    }
}

class ButtonBoard {

    private final int x, y;
    private JFXButton jfxButton;

    public ButtonBoard(int x, int y) {
        this.jfxButton = new JFXButton();
        this.x = x;
        this.y = y;
        jfxButton.setMinHeight(60);
        jfxButton.setMinWidth(50);
        jfxButton.setFont(Font.font("Verdana", 20));
        TicTacToe.buttons.add(this);

        jfxButton.setOnAction(event -> {
            if (jfxButton.getText().isEmpty() && !TicTacToe.isTermined()) {
                jfxButton.setTextFill(Color.BLACK);
                jfxButton.setText("X");
                TicTacToe.board[x][y] = 1;
                TicTacToe.oponentMovement();
            }
        });
    }

    public JFXButton getButton() {
        return jfxButton;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

class TicTacToe {

    static int numWinner;
    static int[][] board;
    static LinkedList<ButtonBoard> buttons;

    static {
        initialize();
    }

    static void initialize() {
        numWinner = 0;
        board = new int[3][3];
        buttons = new LinkedList<>();
    }

    public static void oponentMovement() {
        if (!isTermined()) {
            int score = Integer.MIN_VALUE, n = 0, m = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2;
                        int currentScore = min();
                        if (currentScore > score) {
                            score = currentScore;
                            n = i;
                            m = j;
                        }
                        board[i][j] = 0;
                    }

                }
            }

            for (ButtonBoard button : buttons) {
                if (button.getX() == n && button.getY() == m) {
                    button.getButton().setTextFill(Color.RED);
                    button.getButton().setText("Y");
                    board[n][m] = 2;
                    break;
                }
            }

        }

        numWinner = getWinner();
        if (isTermined()) showMessage();
        
    }

    private static int min() {
        if (isTermined()) return getWinner() != 0 ? 1 : 0;

        int score = Integer.MAX_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    score = Math.min(max(), score);
                    board[i][j] = 0;
                }
            }
        }
        return score;
    }

    private static int max() {
        if (isTermined()) return getWinner() != 0 ? -1 : 0;

        int score = Integer.MIN_VALUE;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 2;
                    score = Math.max(min(), score);
                    board[i][j] = 0;
                }
            }
        }
        return score;
    }

    private static int getWinner() {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) 
                return board[i][0];
            if (board[0][i] != 0 && board[1][i] == board[0][i] && board[2][i] == board[0][i])
                return board[0][i];
        }
        
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) 
            return board[0][0];
        if (board[2][0] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2]) 
            return board[2][0];

        return 0;
    }

    private static boolean isBoardFull() {
        for (int[] row : board) 
            if (Arrays.stream(row).anyMatch(x -> x == 0)) 
                return false;
        return true;
    }

    static boolean isTermined() {
        return getWinner() != 0 || isBoardFull();
    }

    private static void showMessage() {
        JOptionPane.showMessageDialog(null, numWinner == 2 ? "¡¡ YOU LOST !!" : "¡¡ DRAW !!", "LOSER", JOptionPane.ERROR_MESSAGE);
    }
}
