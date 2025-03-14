import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class Absence extends JFrame implements ActionListener {
    JTable table;
    JButton addButton, updateButton, submitButton, cancelButton, cancelButton1, searchButton;
    JPanel panel;
    CardLayout cardLayout;
    Statement stmt;
    JPanel displayPanel; // Define displayPanel

    JLabel idLabel, moduleLabel, heuresLabel, dateLabel, justifyLabel;
    JTextField idField, heuresField, justifyField;
    JComboBox<String> moduleComboBox,justifyComboBox;
    JDateChooser dateChooser;

    public Absence() {
        super("Gestion des Absences des Étudiants");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        this.setPreferredSize(new Dimension(900, 700));

        displayPanel = new JPanel(new BorderLayout()); // Initialize displayPanel
        JLabel titleLabel = new JLabel("Les Absences des Étudiants", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        displayPanel.add(titleLabel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Ajouter Absence");
        updateButton = new JButton("Mettre à jour Absence");
        searchButton = new JButton("Recherche Absence");

        // Apply button styles
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.setBackground(new Color(0, 123, 255));
        updateButton.setBackground(new Color(255, 193, 7)); // Yellow color
        searchButton.setBackground(new Color(23, 162, 184)); // Blue color

        addButton.setForeground(Color.WHITE);
        updateButton.setForeground(Color.WHITE);
        searchButton.setForeground(Color.WHITE);

        addButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        searchButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);
        displayPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(displayPanel, "display");
        this.add(panel);

        idLabel = new JLabel("Etudiant ID:");
        moduleLabel = new JLabel("Module:");
        heuresLabel = new JLabel("Nombre d'Heures:");
        dateLabel = new JLabel("Date d'Absence:");
        justifyLabel = new JLabel("Justifier:");

        idField = new JTextField(10);
        moduleComboBox = new JComboBox<>(new String[]{"Java", "XML", "UML","NodeJs"});
        heuresField = new JTextField(10);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        justifyComboBox = new JComboBox<>(new String[]{"Oui", "Non"});

        submitButton = new JButton("Ajouter");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> cardLayout.show(panel, "display"));

        cancelButton1 = new JButton("Retour à l'Accueil");
        cancelButton1.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton1.setBackground(new Color(220, 53, 69));
        cancelButton1.setForeground(Color.WHITE);
        cancelButton1.setFocusPainted(false);
        cancelButton1.addActionListener(this);

        buttonPanel.add(cancelButton1);

        displayAbsences();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addAbsence();
        } else if (e.getSource() == updateButton) {
            updateAbsence();
        } else if (e.getSource() == cancelButton1) {
            cancelButton1();
        } else if (e.getSource() == searchButton) {
            searchAbsences();
        }
    }
    public void displayAbsences() {
        dbConnect db = new dbConnect();
        Connection conn;
        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM etudiant_absence";
            ResultSet rs = stmt.executeQuery(sql);
            Table tb = new Table();
            DefaultTableModel model = tb.buildTableModel(rs);
            table.setModel(model);
            table = tb.createStyledTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            displayPanel.add(scrollPane, BorderLayout.CENTER);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void addAbsence() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formTitleLabel = new JLabel("Ajouter une nouvelle absence", SwingConstants.CENTER);
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(formTitleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(moduleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(moduleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(heuresLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(heuresField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(justifyLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(justifyComboBox, gbc);

        submitButton.setText("Ajouter");
        submitButton.addActionListener(e -> {
            String etudiantId = idField.getText();
            String module = moduleComboBox.getSelectedItem().toString();
            String heures = heuresField.getText();
            java.util.Date date = dateChooser.getDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            String justifier = justifyComboBox.getSelectedItem().toString();

            try {
                // Fetch Etudiant_nom and Etudiant_prenom based on Etudiant_ID
                String fetchSql = "SELECT prenom, nom FROM edata WHERE Etudiant_ID = '" + etudiantId + "'";
                ResultSet rs = stmt.executeQuery(fetchSql);
                if (rs.next()) {
                    String etudiantPrenom = rs.getString("prenom");
                    String etudiantNom = rs.getString("nom");

                    // Insert into etudiant_absence
                    String insertSql = "INSERT INTO etudiant_absence (Etudiant_ID, Etudiant_prenom, Etudiant_nom, Module, Nombre_Heure, Date_absence, Justifier) VALUES ('"
                            + etudiantId + "', '" + etudiantPrenom + "', '" + etudiantNom + "', '" + module + "', '" + heures + "', '"
                            + sqlDate + "', '" + justifier + "')";
                    stmt.executeUpdate(insertSql);
                    JOptionPane.showMessageDialog(null, "Absence ajoutée avec succès.");
                    cardLayout.show(panel, "display");
                    displayAbsences(); // Refresh absences display
                } else {
                    JOptionPane.showMessageDialog(null, "Etudiant ID non trouvé.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        formPanel.add(cancelButton, gbc);

        panel.add(formPanel, "form");
        cardLayout.show(panel, "form");
    }

    public void updateAbsence() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "SELECT * FROM etudiant_absence WHERE Etudiant_ID = '" + studentId + "'";

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String[] options = {"Module", "Nombre d'Heures", "Date d'Absence", "Justifier"};
                int choice = JOptionPane.showOptionDialog(
                        null, "Sélectionnez le champ à modifier:", "Modifier",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
                );

                String column = switch (choice) {
                    case 0 -> "Module";
                    case 1 -> "nombre_Heure";
                    case 2 -> "Date_absence";
                    case 3 -> "Justifier";
                    default -> null;
                };

                if (column != null) {
                    String newValue = JOptionPane.showInputDialog("Entrez une nouvelle valeur :");
                    sql = "UPDATE etudiant_absence SET " + column + " = '" + newValue + "' WHERE Etudiant_ID = '" + studentId + "'";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Absence mise à jour avec succès.");
                    displayAbsences(); // Refresh absences display
                }
            } else {
                JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchAbsences() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "SELECT * FROM etudiant_absence WHERE Etudiant_ID = '" + studentId + "'";

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

    public void cancelButton1() {
        setVisible(false);
        Home hm = new Home();
        hm.pack();
        hm.setVisible(true);
        hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dispose();
    }


}
