import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public interface LoginListener {
        void onLoginSuccess();
    }

    private final JTextField usernameField = new JTextField(12);
    private final JPasswordField passwordField = new JPasswordField(12);
    private final JButton loginBtn = new JButton("Login");

    public LoginPanel(LoginListener listener) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, c);

        c.gridwidth = 1;
        c.gridy++;
        add(new JLabel("Username:"), c);
        c.gridx = 1;
        add(usernameField, c);

        c.gridx = 0; c.gridy++;
        add(new JLabel("Password:"), c);
        c.gridx = 1;
        add(passwordField, c);

        c.gridx = 0; c.gridy++; c.gridwidth = 2;
        add(loginBtn, c);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());
            if ("admin".equals(user) && "1234".equals(pass)) {
                listener.onLoginSuccess();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
