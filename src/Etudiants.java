import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Etudiants extends JFrame implements ActionListener {
    JLabel etudiantIdLabel, prenomLabel, nomLabel, sexeLabel, filiereLabel, telephoneLabel, dateNaissanceLabel;
    JTextField etudiantIdField, prenomField, nomField, telephoneField, dateNaissanceField;
    JComboBox<String> sexeComboBox, filiereComboBox;
    JButton addButton, sortButton, searchButton, modifyButton, deleteButton, cancelButton, cancelButton1;
    JPanel panel, formPanel, buttonPanel;
    CardLayout cardLayout;
    Statement stmt;
    JTable table;
    JPanel welcomePanel; // Define welcomePanel

    public Etudiants() {
        super("Gestion des Étudinats");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        formPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(900, 700));

        welcomePanel = new JPanel(new BorderLayout()); // Initialize welcomePanel
        JLabel welcomeLabel = new JLabel("Les Étudiants", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        welcomePanel.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Ajouter");
        sortButton = new JButton("Trier");
        searchButton = new JButton("Recherche");
        modifyButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        modifyButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.setBackground(new Color(0, 123, 255));
        sortButton.setBackground(new Color(40, 167, 69));
        searchButton.setBackground(new Color(255, 193, 7));
        modifyButton.setBackground(new Color(23, 162, 184));
        deleteButton.setBackground(new Color(220, 53, 69));

        addButton.setForeground(Color.WHITE);
        sortButton.setForeground(Color.WHITE);
        searchButton.setForeground(Color.WHITE);
        modifyButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);

        addButton.setFocusPainted(false);
        sortButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);
        modifyButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);

        addButton.addActionListener(this);
        sortButton.addActionListener(this);
        searchButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formTitleLabel = new JLabel("Ajouter un nouvel étudiant", SwingConstants.CENTER);
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(formTitleLabel, gbc);

        etudiantIdLabel = new JLabel("Etudiant ID:");
        prenomLabel = new JLabel("Prenom :");
        nomLabel = new JLabel("Nom :");
        sexeLabel = new JLabel("Sexe :");
        filiereLabel = new JLabel("Filiere :");
        telephoneLabel = new JLabel("Telephone :");
        dateNaissanceLabel = new JLabel("date de Naissance (yyyy-mm-dd):");

        etudiantIdField = new JTextField(10);
        prenomField = new JTextField(10);
        nomField = new JTextField(10);
        sexeComboBox = new JComboBox<>(new String[]{"homme", "femme"});
        filiereComboBox = new JComboBox<>(new String[]{"LPGL", "LPGC", "SDDI3"});
        telephoneField = new JTextField(10);
        dateNaissanceField = new JTextField(10);

        etudiantIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        prenomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sexeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        filiereLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        telephoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateNaissanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        etudiantIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        prenomField.setFont(new Font("Arial", Font.PLAIN, 14));
        nomField.setFont(new Font("Arial", Font.PLAIN, 14));
        sexeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        filiereComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        telephoneField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateNaissanceField.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(etudiantIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(etudiantIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(prenomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(prenomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(nomLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(sexeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(sexeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(filiereLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(filiereComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(telephoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(telephoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(dateNaissanceLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dateNaissanceField, gbc);

        JButton submitButton = new JButton("Ajouter");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> addStudent());
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);

        cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> cardLayout.show(panel, "welcome"));

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        formPanel.add(cancelButton, gbc);

        cancelButton1 = new JButton("Retour to Home");
        cancelButton1.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton1.setBackground(new Color(220, 53, 69));
        cancelButton1.setForeground(Color.WHITE);
        cancelButton1.setFocusPainted(false);
        cancelButton1.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 9; // Position après le bouton "Annuler"
        gbc.gridwidth = 2; // Pour l'aligner au centre
        buttonPanel.add(cancelButton1, gbc);

        panel.add(welcomePanel, "welcome");
        panel.add(formPanel, "form");
        this.add(panel);

        displayStudents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            handleAddButton();
        } else if (e.getSource() == sortButton) {
            handleSortButton();
        } else if (e.getSource() == searchButton) {
            handleSearchButton();
        } else if (e.getSource() == modifyButton) {
            handleModifyButton();
        } else if (e.getSource() == deleteButton) {
            deleteStudent();
        } else if (e.getSource() == cancelButton1) {
            cancelButton1();
        }
    }

    public void displayStudents() {
        dbConnect db = new dbConnect();
        Connection conn;
        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM edata";
            ResultSet rs = stmt.executeQuery(sql);
            Table tb = new Table();
            DefaultTableModel model = tb.buildTableModel(rs);
            table.setModel(model);
            table = tb.createStyledTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            welcomePanel.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void addStudent() {
        String sql = "INSERT INTO edata VALUES('" + etudiantIdField.getText() + "', '"
                + prenomField.getText() + "', '" + nomField.getText() + "', '" + sexeComboBox.getSelectedItem().toString() + "', '" + filiereComboBox.getSelectedItem().toString()
                + "', '" + telephoneField.getText() + "', '" + dateNaissanceField.getText() + "')";
        try {
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Étudiant ajouté avec succès.");
            cardLayout.show(panel, "welcome");
            displayStudents(); // Refresh students display
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteStudent() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "DELETE FROM edata WHERE Etudiant_ID = '" + studentId + "'";
        try {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Étudiant supprimé avec succès.");
                displayStudents(); // Refresh students display
            } else {
                JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void handleAddButton() {
        cardLayout.show(panel, "form");
    }

    public void handleSortButton() {
        String[] options = {"Prenom", "Nom", "Sexe", "Filiere"};
        int choice = JOptionPane.showOptionDialog(
                null, "Trier par:", "Trier",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
        );

        String sql = switch (choice) {
            case 0 -> "SELECT * FROM edata ORDER BY Prenom";
            case 1 -> "SELECT * FROM edata ORDER BY Nom";
            case 2 -> "SELECT * FROM edata ORDER BY Sexe";
            case 3 -> "SELECT * FROM edata ORDER BY Filiere";
            default -> null;
        };

        if (sql != null) {
            try {
                ResultSet rs = stmt.executeQuery(sql);
                Table tb = new Table();
                DefaultTableModel model = tb.buildTableModel(rs);
                JTable sortTable = tb.createStyledTable(model);
                JScrollPane sortScrollPane = new JScrollPane(sortTable);
                sortScrollPane.setPreferredSize(new Dimension(800, 400));
                JOptionPane.showMessageDialog(null,sortScrollPane);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void handleSearchButton() {
        String[] options = {"Etudiant ID", "Nom", "Filiere"};
        int choice = JOptionPane.showOptionDialog(
                null, "Rechercher par:", "Recherche",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
        );

        String column = switch (choice) {
            case 0 -> "Etudiant_ID";
            case 1 -> "Nom";
            case 2 -> "Filiere";
            default -> null;
        };

        if (column != null) {
            String searchTerm = JOptionPane.showInputDialog("Entrez le terme de recherche:");
            String sql = "SELECT * FROM edata WHERE " + column + " LIKE '%" + searchTerm + "%'";

            try {
                ResultSet rs = stmt.executeQuery(sql);
                Table tb = new Table();
                DefaultTableModel model = tb.buildTableModel(rs);
                JTable searchTable = tb.createStyledTable(model);
                JScrollPane searchScrollPane = new JScrollPane(searchTable);
                searchScrollPane.setPreferredSize(new Dimension(800, 400)); // Set preferred size for the search result table
                JOptionPane.showMessageDialog(null, searchScrollPane, "Résultats de la recherche", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void handleModifyButton() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "SELECT * FROM edata WHERE Etudiant_ID = '" + studentId + "'";

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String[] options = {"Prenom", "Nom", "Sexe", "Filiere", "Telephone", "dateNaissance"};
                int choice = JOptionPane.showOptionDialog(
                        null, "Sélectionnez le champ à modifier:", "Modifier",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
                );

                String column = switch (choice) {
                    case 0 -> "Prenom";
                    case 1 -> "Nom";
                    case 2 -> "Sexe";
                    case 3 -> "Filiere";
                    case 4 -> "Telephone";
                    case 5 -> "dateNaissance";
                    default -> null;
                };

                if (column != null) {
                    String newValue = JOptionPane.showInputDialog("Entrez une nouvelle valeur :");
                    sql = "UPDATE edata SET " + column + " = '" + newValue + "' WHERE Etudiant_ID = '" + studentId + "'";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Données de l'étudiant modifiées avec succès.");
                    displayStudents(); // Refresh students display
                }
            } else {
                JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void cancelButton1() {
        setVisible(false);
        Home hm = new Home();
        hm.pack();
        hm.setVisible(true);
        hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dispose();
    }
}
