import javax.swing.*;
import java.awt.*;

public class StudentResultManagement extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    public StudentResultManagement() {
        setTitle("Student Result Management System");
        setSize(650, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ResultStorage storage = new ResultStorage("results.txt");
        LoginPanel login = new LoginPanel(() -> cardLayout.show(cards, "main"));
        StudentPanel studentPanel = new StudentPanel(storage);

        cards.add(login, "login");
        cards.add(studentPanel, "main");

        add(cards, BorderLayout.CENTER);

        cardLayout.show(cards, "login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentResultManagement app = new StudentResultManagement();
            app.setVisible(true);
        });
    }
}
