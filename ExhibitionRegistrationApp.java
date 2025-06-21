




import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;
    public class ExhibitionRegistrationApp extends JFrame {
        // Database connection details
        private static final String  CONNECTION_STRING  = "jdbc:sqlite:exhibition.db";
        private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        //  the GUI components
        private JTextField txtRegId, txtName, txtFaculty, txtProjectTitle, txtContact, txtEmail;
        private JLabel lblImage;
        private String selectedImagePath = null;

        public ExhibitionRegistrationApp() {
            initializeDatabase();
            initializeUI();
        }
       // Creating the table
        private void initializeDatabase() {
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
                String sql = "CREATE TABLE IF NOT EXISTS Participants (" +
                        "RegistrationID TEXT PRIMARY KEY," +
                        "StudentName TEXT NOT NULL," +
                        "Faculty TEXT NOT NULL," +
                        "ProjectTitle TEXT NOT NULL," +
                        "ContactNumber TEXT NOT NULL," +
                        "EmailAddress TEXT NOT NULL," +
                        "ProjectImage TEXT" +
                        ")";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database initialization error: " + e.getMessage());
            }
        }

        private void initializeUI() {
            setTitle("Victoria University Exhibition Registration");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Registration ID
            gbc.gridx = 0; gbc.gridy = 0;
            mainPanel.add(new JLabel("Registration ID:"), gbc);
            gbc.gridx = 1;
            txtRegId = new JTextField(20);
            mainPanel.add(txtRegId, gbc);

            // Student Name
            gbc.gridx = 0; gbc.gridy = 1;
            mainPanel.add(new JLabel("Student Name:"), gbc);
            gbc.gridx = 1;
            txtName = new JTextField(20);
            mainPanel.add(txtName, gbc);

            // Faculty
            gbc.gridx = 0; gbc.gridy = 2;
            mainPanel.add(new JLabel("Faculty:"), gbc);
            gbc.gridx = 1;
            txtFaculty = new JTextField(20);
            mainPanel.add(txtFaculty, gbc);

            // Project Title
            gbc.gridx = 0; gbc.gridy = 3;
            mainPanel.add(new JLabel("Project Title:"), gbc);
            gbc.gridx = 1;
            txtProjectTitle = new JTextField(20);
            mainPanel.add(txtProjectTitle, gbc);

            // Contact Number
            gbc.gridx = 0; gbc.gridy = 4;
            mainPanel.add(new JLabel("Contact Number:"), gbc);
            gbc.gridx = 1;
            txtContact = new JTextField(20);
            mainPanel.add(txtContact, gbc);

            // Email Address
            gbc.gridx = 0; gbc.gridy = 5;
            mainPanel.add(new JLabel("Email Address:"), gbc);
            gbc.gridx = 1;
            txtEmail = new JTextField(20);
            mainPanel.add(txtEmail, gbc);

            // Image panel
            gbc.gridx = 0; gbc.gridy = 6;
            gbc.gridwidth = 2;
            JPanel imagePanel = new JPanel();
            lblImage = new JLabel("No image selected");
            imagePanel.add(lblImage);
            mainPanel.add(imagePanel, gbc);

            // Buttons panel
            gbc.gridx = 0; gbc.gridy = 7;
            gbc.gridwidth = 2;
            JPanel buttonPanel = new JPanel();
            JButton btnRegister = new JButton("Register");
            JButton btnSearch = new JButton("Search");
            JButton btnUpdate = new JButton("Update");
            JButton btnDelete = new JButton("Delete");
            JButton btnClear = new JButton("Clear");
            JButton btnExit = new JButton("Exit");
            JButton btnUploadImage = new JButton("Upload Image");

            buttonPanel.add(btnRegister);
            buttonPanel.add(btnSearch);
            buttonPanel.add(btnUpdate);
            buttonPanel.add(btnDelete);
            buttonPanel.add(btnClear);
            buttonPanel.add(btnExit);
            buttonPanel.add(btnUploadImage);
            mainPanel.add(buttonPanel, gbc);

            // Addition of the action listeners
            btnRegister.addActionListener(e -> registerParticipant());
            btnSearch.addActionListener(e -> searchParticipant());
            btnUpdate.addActionListener(e -> updateParticipant());
            btnDelete.addActionListener(e -> deleteParticipant());
            btnClear.addActionListener(e -> clearForm());
            btnExit.addActionListener(e -> System.exit(0));
            btnUploadImage.addActionListener(e -> uploadImage());

            add(mainPanel);
        }

        private boolean validateInput() {
            if (txtRegId.getText().trim().isEmpty() ||
                    txtName.getText().trim().isEmpty() ||
                    txtFaculty.getText().trim().isEmpty() ||
                    txtProjectTitle.getText().trim().isEmpty() ||
                    txtContact.getText().trim().isEmpty() ||
                    txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return true;
            }

            if (!EMAIL_PATTERN.matcher(txtEmail.getText().trim()).matches()) {
                JOptionPane.showMessageDialog(this, "Invalid email format!");
                return true;
            }

            return false;
        }

        private void registerParticipant() {
            if (validateInput()) return;

            String sql = "INSERT INTO Participants (RegistrationID, StudentName, Faculty, ProjectTitle, ContactNumber, EmailAddress, ProjectImage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, txtRegId.getText().trim());
                pstmt.setString(2, txtName.getText().trim());
                pstmt.setString(3, txtFaculty.getText().trim());
                pstmt.setString(4, txtProjectTitle.getText().trim());
                pstmt.setString(5, txtContact.getText().trim());
                pstmt.setString(6, txtEmail.getText().trim());
                pstmt.setString(7, selectedImagePath);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration successful!");
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Registration error: " + e.getMessage());
            }
        }

        private void searchParticipant() {
            String regId = txtRegId.getText().trim();
            if (regId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Registration ID!");
                return;
            }

            String sql = "SELECT * FROM Participants WHERE RegistrationID = ?";
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, regId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    txtName.setText(rs.getString("StudentName"));
                    txtFaculty.setText(rs.getString("Faculty"));
                    txtProjectTitle.setText(rs.getString("ProjectTitle"));
                    txtContact.setText(rs.getString("ContactNumber"));
                    txtEmail.setText(rs.getString("EmailAddress"));
                    selectedImagePath = rs.getString("ProjectImage");
                    if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                        displayImage(selectedImagePath);
                    } else {
                        lblImage.setText("No image selected");
                        lblImage.setIcon(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Participant not found!");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Search error: " + e.getMessage());
            }
        }

        private void updateParticipant() {
            if (validateInput()) return;

            String sql = "UPDATE Participants SET StudentName=?, Faculty=?, ProjectTitle=?, ContactNumber=?, EmailAddress=?, ProjectImage=? WHERE RegistrationID=?";
            try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, txtName.getText().trim());
                pstmt.setString(2, txtFaculty.getText().trim());
                pstmt.setString(3, txtProjectTitle.getText().trim());
                pstmt.setString(4, txtContact.getText().trim());
                pstmt.setString(5, txtEmail.getText().trim());
                pstmt.setString(6, selectedImagePath);
                pstmt.setString(7, txtRegId.getText().trim());
                int updated = pstmt.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "Update successful!");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Participant not found!");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Update error: " + e.getMessage());
            }
        }

        private void deleteParticipant() {
            String regId = txtRegId.getText().trim();
            if (regId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Registration ID!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this participant?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM Participants WHERE RegistrationID = ?";
                try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, regId);
                    int deleted = pstmt.executeUpdate();
                    if (deleted > 0) {
                        JOptionPane.showMessageDialog(this, "Participant deleted successfully!");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(this, "Participant not found!");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Delete error: " + e.getMessage());
                }
            }
        }

        private void clearForm() {
            txtRegId.setText("");
            txtName.setText("");
            txtFaculty.setText("");
            txtProjectTitle.setText("");
            txtContact.setText("");
            txtEmail.setText("");
            selectedImagePath = null;
            lblImage.setText("No image selected");
            lblImage.setIcon(null);
        }

        private void uploadImage() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                displayImage(selectedImagePath);
            }
        }

        private void displayImage(String imagePath) {
            try {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(image));
                lblImage.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new ExhibitionRegistrationApp().setVisible(true);
            });
        }
    }






