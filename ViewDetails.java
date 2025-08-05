import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ViewDetails {
    public static void main(String[] args) {
        
        JFrame mFrame = new JFrame("View Details Sheet");
        mFrame.setSize(800, 600);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setLayout(null);

        JLabel titleLabel = new JLabel("VACCINATION RECORD CARD", JLabel.CENTER);
        titleLabel.setBounds(200, 10, 400, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED);
        mFrame.add(titleLabel);

        JLabel childIdLabel = new JLabel("NewBorn ID:");
        childIdLabel.setBounds(70, 50, 100, 20);
        childIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mFrame.add(childIdLabel);

         ArrayList<String> idList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/immunization_card", "root", "2001")) {
            String sql = "SELECT newborn_id FROM newborn_details"; 
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                idList.add(rs.getString("newborn_id"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving IDs: " + ex.getMessage());
        }

        String[] ids = idList.toArray(new String[0]);

        JComboBox<String> childIdTextField = new JComboBox<>(ids);
        childIdTextField.setBounds(180, 50, 180, 30);
        mFrame.add(childIdTextField);
        
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBackground(Color.YELLOW);
        headerPanel.setBounds(50, 80, 700, 110);
        headerPanel.setBorder(BorderFactory.createTitledBorder("Child Details"));
         
        JLabel childNameLabel = new JLabel("Name:");
        childNameLabel.setBounds(20,20, 100, 20);
        childNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        childNameLabel.setVisible(false);
        headerPanel.add(childNameLabel);

        JLabel childNameFieldLabel = new JLabel();
        childNameFieldLabel.setBounds(120, 20, 200, 20);
        headerPanel.add(childNameFieldLabel);

        JLabel childDOBLabel = new JLabel("Date of Birth:");
        childDOBLabel.setBounds(20, 50, 100, 20);
        childDOBLabel.setFont(new Font("Arial", Font.BOLD, 14));
        childDOBLabel.setVisible(false);
        headerPanel.add(childDOBLabel);

        JLabel childDOBFieldLabel = new JLabel();
        childDOBFieldLabel.setBounds(120, 50, 200, 20);
        headerPanel.add(childDOBFieldLabel);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 80, 100, 20);
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genderLabel.setVisible(false);
        headerPanel.add(genderLabel);

        JLabel genderFieldLabel = new JLabel();
        genderFieldLabel.setBounds(120, 80, 200, 20);
        headerPanel.add(genderFieldLabel);

        mFrame.add(headerPanel);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 200, 700, 300);
        mFrame.add(scrollPane);

        JButton viewButton = new JButton("View Record");
        viewButton.setBounds(400, 50, 150, 20);
         
        viewButton.addActionListener(e ->{
             String Id = String.valueOf(childIdTextField.getSelectedItem()).replace( " ", "");
             if(Id.isEmpty()){
                 JOptionPane.showMessageDialog(mFrame, "Enter a Newborn Id", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
             }
             try{
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/immunization_card", "root", "2001");
 
                 String childDetailsQuery = "SELECT child_Name,date_Of_Birth,gender FROM newborn_details WHERE newborn_Id = ?";
                 PreparedStatement childsStmt = conn.prepareStatement(childDetailsQuery);
                 childsStmt.setString(1,Id);
                 ResultSet childResultSet = childsStmt.executeQuery();
 
                 if(childResultSet.next()){
                     childNameFieldLabel.setText(childResultSet.getString("child_Name"));
                     childNameLabel.setVisible(true);
                     childNameFieldLabel.setVisible(true);
                     childDOBFieldLabel.setText(childResultSet.getString("date_Of_Birth"));
                     childDOBLabel.setVisible(true);
                     childDOBFieldLabel.setVisible(true);
                     genderFieldLabel.setText(childResultSet.getString("gender"));
                     genderLabel.setVisible(true);
                     genderFieldLabel.setVisible(true);
                 }else{
                     JOptionPane.showMessageDialog(mFrame, "No Records Found for Given ID", "Not Found", JOptionPane.WARNING_MESSAGE);
                 }
 
                 String vacDetailsQuery = "SELECT age, vaccine_date, vaccine, dose, batch_no FROM updatevacc WHERE newborn_id = ?";
                 PreparedStatement vacStatement = conn.prepareStatement(vacDetailsQuery);
                 vacStatement.setString(1,Id);
 
                 ResultSet vacSet = vacStatement.executeQuery();
                 DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Age","Vaccinated Date","Vaccine","Dose","Batch No"},0);
 
                 while (vacSet.next()) {
                     tableModel.addRow(new Object[] {
                         vacSet.getString("age"),vacSet.getString("vaccine_date"),vacSet.getString("vaccine"),vacSet.getString("dose"),vacSet.getString("batch_no")
                     });                    
                 }
                 table.setModel(tableModel);
 
                 vacSet.close();
                 vacStatement.close();
                 childResultSet.close();
                 childsStmt.close();
                 conn.close();
             }catch(Exception ex){
                 JOptionPane.showMessageDialog(mFrame, "Error Occured", "Error", JOptionPane.ERROR_MESSAGE);
             }
 
         });
        mFrame.add(viewButton);

        mFrame.setVisible(true);
    }
}
