import javax.swing.*;
import java.awt.*;


public class TicTacToe extends JFrame {

    private static JFrame frame;
    private static JPanel panel;
    private static JButton board[][];
    private static final String emptyBoard = "";
    private static String turn = "X ";

    public TicTacToe() {
        GUI_config();
    }

    public static void main (String[] args){
        new TicTacToe();
    }

    private void GUI_config() {
        frame = new JFrame("Tic Tac Toe");
        panel = new JPanel();
        frame.setSize(300, 390);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        panel.setLayout(new GridLayout(3,3));
        board = new JButton[3][3];
        frame.setVisible(true);
        startGame();
    }

    private void startGame() {
        for(int i = 0; i < board.length; i++ ) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = new JButton(emptyBoard);
                board[i][j].addActionListener(e -> {
                    JButton currentSpace = (JButton) e.getSource();
                    if ((currentSpace.getText().equals(emptyBoard)) && !checkWinner()) {
                        currentSpace.setText(turn);
                        switchPlayers();
                        if (checkWinner()) gameEnded("Game Over");
                    }
                });
                panel.add(board[i][j]);
            }
        }
    }

    private void gameEnded(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private boolean checkWinner() {
        if (verifyWinner(0,0,0,1) && verifyWinner(0,0,0,2) && boardChecker(0,1)) return true;
        else if (verifyWinner(1,0,1,1) && verifyWinner(1,0,1,2) && boardChecker(1,0)) return true;
        else if (verifyWinner(2,0,2,1) && verifyWinner(2,0,2,2) && boardChecker(2,0)) return true;
        else if (verifyWinner(0,0,1,0) && verifyWinner(0,0,2,0) && boardChecker(0,0)) return true;
        else if (verifyWinner(0,1,1,1) && verifyWinner(0,1,2,1) && boardChecker(0,1)) return true;
        else if (verifyWinner(0,2,1,2) && verifyWinner(0,2,2,2) && boardChecker(0,2)) return true;
        else if (verifyWinner(0,0,1,1) && verifyWinner(0,0,2,2) && boardChecker(0,0)) return true;
        else if (verifyWinner(2,0,2,1) && verifyWinner(2,0,0,2) && boardChecker(2,0)) return true;
        else
            return false;
    }

    private boolean boardChecker(int i, int j) {
        return !board[i][j].getText().equals(emptyBoard);
    }

    private boolean verifyWinner(int i, int j, int k, int l) {
        return board[i][j].getText().equals(board[k][l].getText());
    }

    private void switchPlayers() {
        if (turn.equals("X ")) turn = "O "; else turn = "X ";
    }

}

