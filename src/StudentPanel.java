import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StudentPanel extends JPanel {
    private final JTextField nameField = new JTextField(15);
    private final JTextField rollField = new JTextField(8);
    private final JTextField mathField = new JTextField(5);
    private final JTextField scienceField = new JTextField(5);
    private final JTextField englishField = new JTextField(5);

    private final JTextArea resultArea = new JTextArea(6, 40);

    private final ResultStorage storage;

    public StudentPanel(ResultStorage storage) {
        this.storage = storage;
        setLayout(new BorderLayout(8,8));
        add(createFormPanel(), BorderLayout.NORTH);
        add(createButtonsPanel(), BorderLayout.CENTER);

        resultArea.setEditable(false);
        JScrollPane sp = new JScrollPane(resultArea);
        add(sp, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        p.add(new JLabel("Name:"), c);
        c.gridx = 1;
        p.add(nameField, c);

        c.gridx = 2;
        p.add(new JLabel("Roll No:"), c);
        c.gridx = 3;
        p.add(rollField, c);

        c.gridx = 0; c.gridy++;
        p.add(new JLabel("Maths:"), c);
        c.gridx = 1;
        p.add(mathField, c);

        c.gridx = 2;
        p.add(new JLabel("Science:"), c);
        c.gridx = 3;
        p.add(scienceField, c);

        c.gridx = 0; c.gridy++;
        p.add(new JLabel("English:"), c);
        c.gridx = 1;
        p.add(englishField, c);

        return p;
    }

    private JPanel createButtonsPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        JButton calcBtn = new JButton("Calculate Result");
        JButton saveBtn = new JButton("Save Result");
        JButton viewBtn = new JButton("View All Results");
        p.add(calcBtn);
        p.add(saveBtn);
        p.add(viewBtn);

        calcBtn.addActionListener(e -> onCalculate());
        saveBtn.addActionListener(e -> onSave());
        viewBtn.addActionListener(e -> onView());

        return p;
    }

    private void onCalculate() {
        Result r = readForm();
        if (r == null) return;
        ResultCalculator.calculate(r);
        resultArea.setText(r.toString());
    }

    private void onSave() {
        Result r = readForm();
        if (r == null) return;
        ResultCalculator.calculate(r);
        try {
            storage.save(r);
            JOptionPane.showMessageDialog(this, "Saved successfully.");
            clearForm();
            resultArea.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onView() {
        try {
            List<Result> all = storage.loadAll();
            resultArea.setText("");
            if (all.isEmpty()) {
                resultArea.setText("No saved results.");
            } else {
                all.forEach(res -> resultArea.append(res.toString() + "\n"));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Result readForm() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        if (name.isEmpty() || roll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter name and roll no.");
            return null;
        }
        try {
            int math = Integer.parseInt(mathField.getText().trim());
            int science = Integer.parseInt(scienceField.getText().trim());
            int english = Integer.parseInt(englishField.getText().trim());
            return new Result(name, roll, math, science, english);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric marks.");
            return null;
        }
    }

    private void clearForm() {
        nameField.setText("");
        rollField.setText("");
        mathField.setText("");
        scienceField.setText("");
        englishField.setText("");
    }
}
