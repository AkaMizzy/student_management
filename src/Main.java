import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        Login log = new Login();
        log.setVisible(true);
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });
    }
}