import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    // Constants for styling
    private static final Color BACKGROUND_COLOR = new Color(34, 40, 49);
    private static final Color PRIMARY_COLOR = new Color(57, 62, 70);
    private static final Color ACCENT_COLOR = new Color(0, 173, 181);
    private static final Color TEXT_COLOR = new Color(238, 238, 238);
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 500;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 50;

    // UI Components
    private final Container contentPane;
    private final JPanel mainPanel;
    private final JLabel titleLabel;
    private final JButton studentManagementBtn;
    private final JButton gradeManagementBtn;
    private final JButton attendanceManagementBtn;
    private final JButton backToLoginBtn;

    public Home() {
        initializeFrame();
        contentPane = getContentPane();
        mainPanel = createMainPanel();
        titleLabel = createTitleLabel();

        // Initialize buttons
        studentManagementBtn = createStyledButton("Gestion des Étudiants");
        gradeManagementBtn = createStyledButton("Gestion des Notes");
        attendanceManagementBtn = createStyledButton("Gestion des Absences");
        backToLoginBtn = createStyledButton("Déconnecter", new Color(220, 53, 69));

        setupLayout();
        addActionListeners();
    }

    private void initializeFrame() {
        setTitle("Accueil - Gestion des Étudiants");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        return panel;
    }

    private JLabel createTitleLabel() {
        JLabel label = new JLabel("Système de Gestion ESTEM", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 26));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // Create rounded borders with hover effect
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);
            }
        });

        return button;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        // Create rounded borders with hover effect
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(backgroundColor.darker(), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
        return button;
    }

    private void setupLayout() {
        contentPane.add(mainPanel);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(studentManagementBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(gradeManagementBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(attendanceManagementBtn);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(backToLoginBtn);
    }

    private void addActionListeners() {
        studentManagementBtn.addActionListener(this);
        gradeManagementBtn.addActionListener(this);
        attendanceManagementBtn.addActionListener(this);
        backToLoginBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame nextFrame = null;

        if (e.getSource() == studentManagementBtn) {
            nextFrame = new Etudiants();
        } else if (e.getSource() == gradeManagementBtn) {
            nextFrame = new Note();
        } else if (e.getSource() == attendanceManagementBtn) {
            nextFrame = new Absence();
        } else if (e.getSource() == backToLoginBtn) {
            nextFrame = new Login();
        }

        if (nextFrame != null) {
            nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            nextFrame.pack();
            nextFrame.setLocationRelativeTo(null);
            nextFrame.setVisible(true);
            dispose();
        }
    }
}