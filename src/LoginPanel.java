import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public interface LoginListener { // интерфейс для обратного вызова - уведомления об успешном входе
        void onLoginSuccess();
    }

    private final JTextField usernameField = new JTextField(12);
    private final JPasswordField passwordField = new JPasswordField(12);
    private final JButton loginBtn = new JButton("Login");

    public LoginPanel(LoginListener listener) { // конструктор панели входа
        // используем GridBagLayout для гибкого расположения компонентов
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);

        // тут заголовок формы входа
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, c);

        // тут поле для ввода имени пользователя
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

        // здесь обработчик нажатия кнопки входа
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());

            // проверка на введенные учетные данные
            // в реальном приложении здесь была бы проверка с базой данных
            if ("admin".equals(user) && "1234".equals(pass)) {
                // успешный вход - уведомляем слушателя
                listener.onLoginSuccess();
            } else {
                // неверные данные - показываем сообщение об ошибке
                JOptionPane.showMessageDialog(this,
                        "Invalid credentials",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}