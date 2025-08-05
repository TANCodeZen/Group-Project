import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;

public class LoginForm {
    private static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/immunization_card";
            String user = "root";
            String password = "2001";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.getContentPane().setBackground(new Color(173, 216, 230));

        JLabel headingLabel = new JLabel("Welcome to Login Form");
        headingLabel.setBounds(50, 10, 300, 40);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setForeground(Color.BLACK);
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(headingLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 25);
        userLabel.setForeground(Color.BLACK);
        frame.add(userLabel);

        JTextField userTextField = new JTextField();
        userTextField.setBounds(150, 70, 150, 25);
        frame.add(userTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 25);
        passwordLabel.setForeground(Color.BLACK);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 150, 25);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 170, 100, 30);
        frame.add(loginButton);

        JLabel notRegisteredLabel = new JLabel("<HTML><U>Not Registered Yet?</U></HTML>");
        notRegisteredLabel.setBounds(150, 210, 150, 25);
        notRegisteredLabel.setForeground(Color.BLUE);
        notRegisteredLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(notRegisteredLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Username and password cannot be empty!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Connection conn = connect();
                    if (conn != null) {
                        try {
                            String query = "SELECT * FROM employees WHERE Username=? AND Password=?";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, username);
                            pst.setString(2, password);
                            ResultSet rs = pst.executeQuery();

                            if (rs.next()) {
                                JOptionPane.showMessageDialog(frame,
                                        "Login Successful!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                userTextField.setText("");
                                passwordField.setText("");
                                frame.dispose();

                                try {
                                    String classpath = "bin" + File.pathSeparator + "lib/*";
                                    ProcessBuilder pb = new ProcessBuilder("java", "homePage.java"); 
                                    pb.inheritIO();  
                                    pb.start();  
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame,
                                        "Invalid Username or Password",
                                        "Login Error",
                                        JOptionPane.ERROR_MESSAGE);
                                userTextField.setText("");
                                passwordField.setText("");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame,
                                    "Error while processing login!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        notRegisteredLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openEmployeeForm();
            }
        });
    }

    private static void openEmployeeForm() {
        JFrame employeeFrame = new JFrame("Employee Registration Form");
        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeFrame.setSize(400, 500);
        employeeFrame.setLayout(null);
        employeeFrame.setLocationRelativeTo(null);
        employeeFrame.getContentPane().setBackground(new Color(224, 255, 255));

        JLabel headingLabel = new JLabel("Employee Registration");
        headingLabel.setBounds(50, 10, 300, 40);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headingLabel.setForeground(Color.BLACK);
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        employeeFrame.add(headingLabel);

        JLabel empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(50, 70, 100, 25);
        empIdLabel.setForeground(Color.BLACK);
        employeeFrame.add(empIdLabel);

        JComboBox<String> empIdComboBox = new JComboBox<>(new String[]{"E001", "E002", "E003", "E004", "E005"});
        empIdComboBox.setBounds(170, 70, 150, 25);
        employeeFrame.add(empIdComboBox);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(50, 120, 100, 25);
        positionLabel.setForeground(Color.BLACK);
        employeeFrame.add(positionLabel);

        JTextField positionTextField = new JTextField();
        positionTextField.setBounds(170, 120, 150, 25);
        employeeFrame.add(positionTextField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 170, 100, 25);
        usernameLabel.setForeground(Color.BLACK);
        employeeFrame.add(usernameLabel);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(170, 170, 150, 25);
        employeeFrame.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 220, 100, 25);
        passwordLabel.setForeground(Color.BLACK);
        employeeFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(170, 220, 150, 25);
        employeeFrame.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 270, 120, 25);
        confirmPasswordLabel.setForeground(Color.BLACK);
        employeeFrame.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 270, 150, 25);
        employeeFrame.add(confirmPasswordField);

        JButton submitButton = new JButton("Register");
        submitButton.setBounds(170, 350, 100, 30);
        employeeFrame.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empId = (String) empIdComboBox.getSelectedItem();
                String position = positionTextField.getText();
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (position.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeFrame,
                            "All fields are required!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(employeeFrame,
                            "Passwords do not match!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                } else {
                    Connection conn = connect();
                    if (conn != null) {
                        try {
                            String query = "INSERT INTO employees (EmployeeID, Position, Username, Password) VALUES (?, ?, ?, ?)";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, empId);
                            pst.setString(2, position);
                            pst.setString(3, username);
                            pst.setString(4, password);

                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(employeeFrame,
                                    "Employee registered successfully!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);

                            positionTextField.setText("");
                            usernameTextField.setText("");
                            passwordField.setText("");
                            confirmPasswordField.setText("");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employeeFrame,
                                    "Error during registration!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        employeeFrame.setVisible(true);
    }
}
