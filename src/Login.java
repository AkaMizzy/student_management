import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    Connection conn = null;
    Statement stm = null;
    ResultSet rslt = null;

    // Components
    JLabel logoLabel = new JLabel();
    JLabel titleLabel = new JLabel("Login");
    JLabel emailLabel = new JLabel("Email");
    JTextField emailField = new JTextField(20);

    JLabel passwordLabel = new JLabel("Mot de passe");
    JPasswordField passwordField = new JPasswordField(20);

    JButton loginButton = new JButton("Se connecter");
    JButton forgetPasswordButton = new JButton("Mot de passe oublié");
    JLabel footerLabel = new JLabel("copyright © Estem Tous droits réservés", SwingConstants.CENTER);

    public Login() {
        setTitle("LOGIN");
        setSize(800, 400); // Adjust the window size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main container
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        // Left panel (for the logo)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        logoLabel.setIcon(new ImageIcon(getClass().getResource("EduManager.jpg")));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(logoLabel, BorderLayout.CENTER);

        // Right panel (for the login form)
        JPanel rightPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        rightPanel.setBackground(new Color(230, 230, 230)); // Light gray background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(titleLabel, gbc);

        // Email label and field
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        rightPanel.add(emailLabel, gbc);

        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        rightPanel.add(emailField, gbc);

        // Password label and field
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(passwordLabel, gbc);

        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        rightPanel.add(passwordField, gbc);

        // Login button
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton.addActionListener(this);
        rightPanel.add(loginButton, gbc);

        // Forget Password button
        forgetPasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        forgetPasswordButton.setBackground(new Color(220, 53, 69));
        forgetPasswordButton.setForeground(Color.WHITE);
        forgetPasswordButton.setFocusPainted(false);
        forgetPasswordButton.addActionListener(this);
        gbc.gridy = 4;
        rightPanel.add(forgetPasswordButton, gbc);

        // Footer
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        rightPanel.add(footerLabel, gbc);

        // Vertical line (separator)
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(Color.GRAY);

        // Add panels to the container
        con.add(leftPanel, BorderLayout.WEST);
        con.add(separator, BorderLayout.CENTER);
        con.add(rightPanel, BorderLayout.EAST);

        // Adjust panel sizes
        leftPanel.setPreferredSize(new Dimension(300, getHeight()));
        rightPanel.setPreferredSize(new Dimension(500, getHeight()));

        // Database connection
        try {
            conn = dbConnect.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                stm = conn.createStatement();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                String SQL = "SELECT * FROM user WHERE Email = '" + email + "' AND Password = '" + password + "'";
                rslt = stm.executeQuery(SQL);

                if (rslt.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose();
                    setVisible(false);
                    Home hm =new Home();
                    hm.pack();
                    hm.setVisible(true);
                    hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                } else {
                    JOptionPane.showMessageDialog(this, "Email or Password is incorrect.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL Error: " + ex.getMessage());
            }
        } else if (e.getSource() == forgetPasswordButton) {
            handleForgetPassword();
        }
    }

    private void handleForgetPassword() {
        String email = JOptionPane.showInputDialog("Entrez votre email:");
        if (email != null && !email.isEmpty()) {
            try {
                stm = conn.createStatement();
                String SQL = "SELECT * FROM user WHERE Email = '" + email + "'";
                rslt = stm.executeQuery(SQL);

                if (rslt.next()) {
                    String newPassword = JOptionPane.showInputDialog("Entrez un nouveau mot de passe:");
                    if (newPassword != null && !newPassword.isEmpty()) {
                        SQL = "UPDATE user SET Password = '" + newPassword + "' WHERE Email = '" + email + "'";
                        stm.executeUpdate(SQL);
                        JOptionPane.showMessageDialog(this, "Votre mot de passe a été mis à jour avec succès..");
                    } else {
                        JOptionPane.showMessageDialog(this, "Le mot de passe ne peut pas être vide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email non trouvé.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL Error: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "L'e-mail ne peut pas être vide.");
        }
    }
}
