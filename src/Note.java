import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Note extends JFrame implements ActionListener {
    JTable table;
    JButton addButton, sortButton, updateButton, submitButton, cancelButton, cancelButton1, searchButton;
    JPanel panel;
    CardLayout cardLayout;
    Statement stmt;
    JPanel displayPanel; // Define displayPanel
    JLabel idLabel, javaLabel, xmlLabel, umlLabel, nodejsLabel;
    JTextField idField, javaField, xmlField, umlField, nodejsField;

    public Note() {
        super("Gestion des Notes des Étudiants");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        this.setPreferredSize(new Dimension(900, 700));

        displayPanel = new JPanel(new BorderLayout()); // Initialize displayPanel
        JLabel titleLabel = new JLabel("Les Notes des Étudiants", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        displayPanel.add(titleLabel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Ajouter Note");
        sortButton = new JButton("Trier Notes");
        updateButton = new JButton("Mettre à jour Note");
        searchButton = new JButton("Recherche Note");

        // Apply button styles
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        addButton.setBackground(new Color(0, 123, 255));
        sortButton.setBackground(new Color(40, 167, 69)); // Green color
        updateButton.setBackground(new Color(255, 193, 7)); // Yellow color
        searchButton.setBackground(new Color(23, 162, 184)); // Blue color

        addButton.setForeground(Color.WHITE);
        sortButton.setForeground(Color.WHITE);
        updateButton.setForeground(Color.WHITE);
        searchButton.setForeground(Color.WHITE);

        addButton.setFocusPainted(false);
        sortButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);

        addButton.addActionListener(this);
        sortButton.addActionListener(this);
        updateButton.addActionListener(this);
        searchButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);
        displayPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(displayPanel, "display");
        this.add(panel);

        idLabel = new JLabel("Etudiant ID:");
        javaLabel = new JLabel("Java:");
        xmlLabel = new JLabel("XML:");
        umlLabel = new JLabel("UML:");
        nodejsLabel = new JLabel("NodeJS:");

        idField = new JTextField(10);
        javaField = new JTextField(10);
        xmlField = new JTextField(10);
        umlField = new JTextField(10);
        nodejsField = new JTextField(10);

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

        cancelButton1 = new JButton("Retour to Home");
        cancelButton1.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton1.setBackground(new Color(220, 53, 69));
        cancelButton1.setForeground(Color.WHITE);
        cancelButton1.setFocusPainted(false);
        cancelButton1.addActionListener(this);

        buttonPanel.add(cancelButton1);

        displayNotes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addNote();
        } else if (e.getSource() == sortButton) {
            sortNotes();
        } else if (e.getSource() == updateButton) {
            updateNote();
        } else if (e.getSource() == searchButton) {
            searchNotes();
        } else if (e.getSource() == cancelButton1) {
            cancelButton1();
        }
    }

    public void displayNotes() {
        dbConnect db = new dbConnect();
        Connection conn;
        try {
            conn = db.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM etudiant_note";
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

    public void addNote() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formTitleLabel = new JLabel("Ajouter une nouvelle note", SwingConstants.CENTER);
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
        formPanel.add(javaLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(javaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(xmlLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(xmlField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(umlLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(umlField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(nodejsLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nodejsField, gbc);

        submitButton.setText("Ajouter");
        submitButton.addActionListener(e -> {
            String etudiantId = idField.getText();
            String java = javaField.getText();
            String xml = xmlField.getText();
            String uml = umlField.getText();
            String nodejs = nodejsField.getText();

            try {
                // Fetch Etudiant_nom and Etudiant_prenom based on Etudiant_ID
                String fetchSql = "SELECT prenom, nom FROM edata WHERE Etudiant_ID = '" + etudiantId + "'";
                ResultSet rs = stmt.executeQuery(fetchSql);
                if (rs.next()) {
                    String etudiantPrenom = rs.getString("prenom");
                    String etudiantNom = rs.getString("nom");

                    // Insert into etudiant_note
                    String insertSql = "INSERT INTO etudiant_note (Etudiant_ID, Etudiant_prenom, Etudiant_nom, Java, XML, UML, NodeJS) VALUES ('"
                            + etudiantId + "', '" + etudiantPrenom + "', '" + etudiantNom + "', '" + java + "', '" + xml + "', '"
                            + uml + "', '" + nodejs + "')";
                    stmt.executeUpdate(insertSql);
                    JOptionPane.showMessageDialog(null, "Note ajoutée avec succès.");
                    cardLayout.show(panel, "display");
                    displayNotes(); // Refresh notes display
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

    public void updateNote() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "SELECT * FROM etudiant_note WHERE Etudiant_ID = '" + studentId + "'";

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String[] options = {"Java", "XML", "UML", "NodeJS"};
                int choice = JOptionPane.showOptionDialog(
                        null, "Sélectionnez le champ à modifier:", "Modifier",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
                );

                String column = switch (choice) {
                    case 0 -> "Java";
                    case 1 -> "XML";
                    case 2 -> "UML";
                    case 3 -> "NodeJS";
                    default -> null;
                };

                if (column != null) {
                    String newValue = JOptionPane.showInputDialog("Entrez une nouvelle valeur :");
                    sql = "UPDATE etudiant_note SET " + column + " = '" + newValue + "' WHERE Etudiant_ID = '" + studentId + "'";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Note mise à jour avec succès.");
                    displayNotes(); // Refresh notes display
                }
            } else {
                JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void sortNotes() {
        String[] options = {"Java", "XML", "UML", "NodeJS", "Moyenne_Generale"};
        String[] orderOptions = {"Le plus élevé", "Le plus bas"};
        int choice = JOptionPane.showOptionDialog(
                null, "Trier par:", "Trier les notes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]
        );
        int orderChoice = JOptionPane.showOptionDialog(
                null, "Ordre:", "Ordre de tri",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, orderOptions, orderOptions[0]
        );

        if (choice >= 0 && orderChoice >= 0) {
            String column = options[choice];
            String order = orderChoice == 0 ? "DESC" : "ASC";
            String sql = "SELECT * FROM etudiant_note ORDER BY " + column + " " + order;

            try {
                ResultSet rs = stmt.executeQuery(sql);
                Table tb = new Table();
                DefaultTableModel model = tb.buildTableModel(rs);
                JTable sortTable = tb.createStyledTable(model);
                JScrollPane sortScrollPane = new JScrollPane(sortTable);
                sortScrollPane.setPreferredSize(new Dimension(800, 400));
                JOptionPane.showMessageDialog(null,sortScrollPane, "Notes triées", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void searchNotes() {
        String studentId = JOptionPane.showInputDialog("Enter étudiant ID:");
        String sql = "SELECT * FROM etudiant_note WHERE Etudiant_ID = '" + studentId + "'";

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
