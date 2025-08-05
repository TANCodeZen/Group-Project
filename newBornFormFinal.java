import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;
import java.text.*;

public class newBornFormFinal{

    private static final String url = "jdbc:mysql://localhost:3306/immunization_card";
    private static final String user = "root";
    private static final String password = "2001";
public static void main(String []args){

        JFrame mFrame = new JFrame("Information Sheet");
        mFrame.setSize(815, 800);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mPanel = new JPanel();
        mPanel.setBackground(Color.decode("#FFFFFF"));
        mFrame.add(mPanel);
        mFrame.setVisible(true);

        mPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Newborn's Details");
        titleLabel.setBounds(275, 0, 600, 80);
        titleLabel.setFont(new Font("MadeFor",Font.ITALIC,30));
        titleLabel.setForeground(Color.darkGray);
        mPanel.add(titleLabel);

        JLabel reqLabel = new JLabel("*REQUIRED");
        reqLabel.setBounds(100, 60, 600, 10);
        reqLabel.setFont(new Font("Times New Roman",Font.BOLD,10));
        reqLabel.setForeground(Color.RED);
        reqLabel.setHorizontalAlignment(SwingConstants.LEFT);
        mPanel.add(reqLabel);

        JLabel childIDLabel = new JLabel("Newborn's ID: ");
        childIDLabel.setBounds(100, 60, 600, 10);
        childIDLabel.setFont(new Font("Times New Roman",Font.BOLD,10));
        childIDLabel.setForeground(Color.darkGray);
        childIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        mPanel.add(childIDLabel);

        JLabel childDOBLabel = new JLabel("Date of Birth:");
        childDOBLabel.setBounds(25, 94, 130, 15);
        childDOBLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        childDOBLabel.setForeground(Color.darkGray);
        mPanel.add(childDOBLabel);

        JSpinner bDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor bDateEditor = new JSpinner.DateEditor(bDateSpinner,"yyyy-MM-dd");
        bDateSpinner.setEditor(bDateEditor);
        bDateSpinner.setBounds(170, 84, 250, 25);
        mPanel.add(bDateSpinner); 

        JLabel bTimeLabel = new JLabel("Time:");
        bTimeLabel.setBounds(440, 94, 70, 15);
        bTimeLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        bTimeLabel.setForeground(Color.darkGray);
        mPanel.add(bTimeLabel);

        JSpinner btimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor btimeEditor = new JSpinner.DateEditor(btimeSpinner,"HH:mm:ss");
        btimeSpinner.setEditor(btimeEditor);
        btimeSpinner.setBounds(520, 85, 250, 25);
        mPanel.add(btimeSpinner);
        
        JLabel childNameLabel = new JLabel("Name:");
        childNameLabel.setBounds(25, 123, 70, 15);
        childNameLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        childNameLabel.setForeground(Color.darkGray);
        mPanel.add(childNameLabel);

        JTextField childNameTextField = new JTextField();
        childNameTextField.setBounds(170, 115, 600, 25);
        childNameTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(childNameTextField);

        JLabel HospitalLabel = new JLabel("*Hospital:");
        HospitalLabel.setBounds(25, 153, 130, 15);
        HospitalLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        HospitalLabel.setForeground(Color.darkGray);
        mPanel.add(HospitalLabel);

        String[] hospitals = {"Select Hospital","Hambantota","Tangalle","Matara","Karapitiya","Kalubowila"};

        JComboBox <String> Hos= new JComboBox<>(hospitals);
        Hos.setBounds(170, 145, 250, 25);
        Hos.setEditable(true);
        mPanel.add(Hos);

        JLabel genderLabel = new JLabel("*Gender:");
        genderLabel.setBounds(440, 153, 70, 15);
        genderLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        genderLabel.setForeground(Color.darkGray);
        mPanel.add(genderLabel);
        
        JRadioButton boyRadioButton = new JRadioButton("Boy ");
        boyRadioButton.setBounds(535, 145, 70, 25);
        boyRadioButton.setActionCommand("Boy");
        mPanel.add(boyRadioButton);
        boyRadioButton.addActionListener(e -> {
            mPanel.setBackground(Color.decode("0xE3F2FD"));
        });

        JRadioButton girlRadioButton = new JRadioButton("Girl ");
        girlRadioButton.setBounds(605, 145, 70, 25);
        girlRadioButton.setActionCommand("Girl");
        mPanel.add(girlRadioButton);
        girlRadioButton.addActionListener(e -> {
            mPanel.setBackground(Color.decode("#ffc2f7"));
        });

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(girlRadioButton);
        genderGroup.add(boyRadioButton);

        JLabel momNameLabel = new JLabel("*Mom's Name:");
        momNameLabel.setBounds(25, 183, 120, 15);
        momNameLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        momNameLabel.setForeground(Color.darkGray);
        mPanel.add(momNameLabel);

        JTextField momNameTextField = new JTextField();
        momNameTextField.setBounds(170, 175, 600, 25);
        momNameTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(momNameTextField);

        JLabel momIDLabel = new JLabel("*Mom's ID no:");
        momIDLabel.setBounds(25, 213, 130, 15);
        momIDLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        momIDLabel.setForeground(Color.darkGray);
        mPanel.add(momIDLabel);

        JTextField momIDTextField = new JTextField();
        momIDTextField.setBounds(170, 205, 250, 25);
        momIDTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(momIDTextField);

        JLabel dadNameLabel = new JLabel("*Dad's Name:");
        dadNameLabel.setBounds(25, 243, 120, 15);
        dadNameLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        dadNameLabel.setForeground(Color.darkGray);
        mPanel.add(dadNameLabel);

        JTextField dadNameTextField = new JTextField();
        dadNameTextField.setBounds(170, 235, 600, 25);
        dadNameTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(dadNameTextField);

        JLabel dadIDLabel = new JLabel("*Dad's ID no:");
        dadIDLabel.setBounds(25, 273, 130, 15);
        dadIDLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        dadIDLabel.setForeground(Color.darkGray);
        mPanel.add(dadIDLabel);

        JTextField dadIDTextField = new JTextField();
        dadIDTextField.setBounds(170, 265, 250, 25);
        dadIDTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(dadIDTextField);

        JLabel addressLabel = new JLabel("*Address:");
        addressLabel.setBounds(25, 303, 120, 15);
        addressLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        addressLabel.setForeground(Color.darkGray);
        mPanel.add(addressLabel);

        JTextField addressTextField = new JTextField();
        addressTextField.setBounds(170, 295, 600, 25);
        addressTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(addressTextField);

        JLabel healthTitleLabel = new JLabel("Medical Information");
        healthTitleLabel.setBounds(275, 320, 600, 80);
        healthTitleLabel.setFont(new Font("MadeFor",Font.ITALIC,30));
        healthTitleLabel.setForeground(Color.darkGray);
        mPanel.add(healthTitleLabel);

        JLabel childWeightLabel = new JLabel("*Weight:");
        childWeightLabel.setBounds(25, 403, 130, 20);
        childWeightLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        childWeightLabel.setForeground(Color.darkGray);
        mPanel.add(childWeightLabel);

        JTextField childWeightTextField = new JTextField("0000");
        childWeightTextField.setBounds(170, 395, 200, 25);
        childWeightTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(childWeightTextField);

        JTextField gramTextField = new JTextField("grams");
        gramTextField.setBounds(370, 395, 50, 25);
        gramTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        gramTextField.setEditable(false);
        mPanel.add(gramTextField);

        JLabel childHeightLabel = new JLabel("*Height:");
        childHeightLabel.setBounds(440, 403, 70, 20);
        childHeightLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        childHeightLabel.setForeground(Color.darkGray);
        mPanel.add(childHeightLabel);

        JTextField childHeightTextField = new JTextField("00");
        childHeightTextField.setBounds(520, 395, 220, 25);
        childHeightTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(childHeightTextField);

        JTextField cmTextField = new JTextField("cm");
        cmTextField.setBounds(740, 395, 30, 25);
        cmTextField.setEditable(false);
        cmTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(cmTextField);

        JLabel childBTLabel = new JLabel("*Blood Type:");
        childBTLabel.setBounds(25, 438, 130, 20);
        childBTLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        childBTLabel.setForeground(Color.darkGray);
        mPanel.add(childBTLabel);
        
        String[] bloodType = {"Blood Type","A+","A-","B+","B-","AB+","AB-","O+","O-"};

        JComboBox <String> BT= new JComboBox<>(bloodType);
        BT.setBounds(170, 430, 250, 25);
        mPanel.add(BT);

        JLabel healthStatusLabel = new JLabel("*Health Status:");
        healthStatusLabel.setBounds(440, 430, 150, 15);
        healthStatusLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        healthStatusLabel.setForeground(Color.darkGray);
        mPanel.add(healthStatusLabel);
        
        JRadioButton goodRadioButton = new JRadioButton("Good ");
        goodRadioButton.setBounds(600, 425, 100, 22);
        goodRadioButton.setActionCommand("Good");
        mPanel.add(goodRadioButton);
        
        JRadioButton generalRadioButton = new JRadioButton("General ");
        generalRadioButton.setBounds(600, 447, 100, 22);
        generalRadioButton.setActionCommand("General");
        mPanel.add(generalRadioButton);
        
        JRadioButton poorRadioButton = new JRadioButton("Poor ");
        poorRadioButton.setBounds(600, 467, 100, 22);
        poorRadioButton.setActionCommand("Poor");
        mPanel.add(poorRadioButton);
       
        ButtonGroup healthStatusGroup = new ButtonGroup();
        healthStatusGroup.add(goodRadioButton);
        healthStatusGroup.add(generalRadioButton);
        healthStatusGroup.add(poorRadioButton);

        JLabel sNotesLabel = new JLabel("Special Notes:");
        sNotesLabel.setBounds(25, 473, 120, 15);
        sNotesLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        sNotesLabel.setForeground(Color.darkGray);
        mPanel.add(sNotesLabel);

        JTextField sNotesTextField = new JTextField();
        sNotesTextField.setBounds(170, 465, 400, 25);
        sNotesTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(sNotesTextField);

        JLabel vacTitleLabel = new JLabel("Vaccination Information(at Birth)");
        vacTitleLabel.setBounds(205, 490, 600, 80);
        vacTitleLabel.setFont(new Font("MadeFor",Font.ITALIC,30));
        vacTitleLabel.setForeground(Color.darkGray);
        mPanel.add(vacTitleLabel);

        JLabel bcgLabel = new JLabel("The BCG ");
        bcgLabel.setBounds(25, 573, 100, 20);
        bcgLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        bcgLabel.setForeground(Color.darkGray);
        mPanel.add(bcgLabel);

        JTextField bcgBatchTextField = new JTextField("*");
        bcgBatchTextField.setBounds(170, 565, 100, 30);
        bcgBatchTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(bcgBatchTextField);

        JLabel vacNameLabel = new JLabel("vaccine was administered on:");
        vacNameLabel.setBounds(285, 573, 250, 20);
        vacNameLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        vacNameLabel.setForeground(Color.darkGray);
        mPanel.add(vacNameLabel);
        
        JSpinner bcgDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor bcgDateEditor = new JSpinner.DateEditor(bcgDateSpinner,"yyyy-MM-dd");
        bcgDateSpinner.setEditor(bcgDateEditor);
        bcgDateSpinner.setBounds(520, 565, 250, 30);
        mPanel.add(bcgDateSpinner);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(25, 653, 130, 20);
        dateLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        dateLabel.setForeground(Color.darkGray);
        mPanel.add(dateLabel);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner,"yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(170, 645, 250, 25);
        mPanel.add(dateSpinner);

        JLabel empIDLabel = new JLabel("*Emp ID:");
        empIDLabel.setBounds(440, 653, 70, 20);
        empIDLabel.setFont(new Font("MadeFor",Font.BOLD,15));
        empIDLabel.setForeground(Color.darkGray);
        mPanel.add(empIDLabel);

        JTextField empIDTextField = new JTextField();
        empIDTextField.setBounds(520, 645, 250, 25);
        empIDTextField.setFont(new Font("MadeFor",Font.BOLD,14));
        mPanel.add(empIDTextField);

        JButton clear = new JButton("Clear");
        clear.setForeground(Color.BLACK);
        clear.setBackground(Color.decode("#dedede"));
        clear.setBounds(520, 700, 100, 30);
        clear.addActionListener(e ->{
            int Confirm = JOptionPane.showConfirmDialog(mFrame,"Do You want To Clear Entered Data?","Confirm",JOptionPane.YES_NO_OPTION);
            if(Confirm == JOptionPane.YES_OPTION){
            childNameTextField.setText("");
            momNameTextField.setText(""); 
            momIDTextField.setText(""); 
            dadNameTextField.setText(""); 
            dadIDTextField.setText(""); 
            addressTextField.setText(""); 
            childWeightTextField.setText(""); 
            childHeightTextField.setText("");
            sNotesTextField.setText("");
            bcgBatchTextField.setText("*");
            empIDTextField.setText("");
            childIDLabel.setText("Newborn's ID:");
            genderGroup.clearSelection();
            healthStatusGroup.clearSelection();
            Hos.setSelectedIndex(0);
            BT.setSelectedIndex(0);
            mPanel.setBackground(Color.decode("#FFFFFF"));
        }});
        mPanel.add(clear);

        JButton submit = new JButton("Submit");
        submit.setForeground(Color.BLACK);
        submit.setBackground(Color.decode("#dedede"));
        submit.setBounds(670, 700, 100, 30);
        submit.addActionListener(f ->{
            if(momNameTextField.getText().trim().equals("") || 
            momIDTextField.getText().trim().equals("") || 
            dadNameTextField.getText().trim().equals("") || 
            dadIDTextField.getText().trim().equals("") || 
            addressTextField.getText().trim().equals("") || 
            childWeightTextField.getText().trim().equals("") || 
            childHeightTextField.getText().trim().equals("") ||
            healthStatusGroup.getSelection() == null ||
            genderGroup.getSelection() == null || 
            bcgBatchTextField.getText().trim().equals("") ||
            empIDTextField.getText().trim().equals("") ||
            String.valueOf(Hos.getSelectedItem()).equals("Select Hospital") ||
            String.valueOf(BT.getSelectedItem()).equals("Blood Type"))
            {
                JOptionPane.showMessageDialog(mFrame,"Please Fill All the Required Fields","Warning",JOptionPane.WARNING_MESSAGE);
            }else if(!(momIDTextField.getText().length() == 10 || momIDTextField.getText().length() == 12)){
                momIDLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(mFrame, "There are some Mismatch in Your Entered Data.Check Again", "Mismatch", JOptionPane.WARNING_MESSAGE);
            
            }else if(!(dadIDTextField.getText().length() == 10 || dadIDTextField.getText().length() == 12)){
                
                if((momIDTextField.getText().length() == 10 || momIDTextField.getText().length() == 12)){
                    momIDLabel.setForeground(Color.BLACK);
                }
                
                dadIDLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(mFrame, "There are some Mismatch in Your Entered Data.Check Again", "Mismatch", JOptionPane.WARNING_MESSAGE);
                
            }else if(!(Integer.parseInt(childWeightTextField.getText()) > 0 && Integer.parseInt(childWeightTextField.getText()) < 10000)){
                if((dadIDTextField.getText().length() == 10 || dadIDTextField.getText().length() == 12)){
                    dadIDLabel.setForeground(Color.BLACK);
                }
                
                childWeightLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(mFrame, "There are some Mismatch in Your Entered Data.Check Again", "Mismatch", JOptionPane.WARNING_MESSAGE);

                
            }else if(!(Integer.parseInt(childHeightTextField.getText()) > 0 && Integer.parseInt(childHeightTextField.getText()) < 100)){
                if(Integer.parseInt(childWeightTextField.getText()) > 0 && Integer.parseInt(childWeightTextField.getText()) < 10000){
                    childWeightLabel.setForeground(Color.BLACK);
                }
                
                childHeightLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(mFrame, "There are some Mismatch in Your Entered Data.Check Again", "Mismatch", JOptionPane.WARNING_MESSAGE);

                
            }else{
                try{
                    String uniqueID = (String.valueOf(Hos.getSelectedItem()).substring(0,3))+(momIDTextField.getText().substring(7, 9))+(btimeSpinner.getValue().toString().substring(11,20).replace(":", ""));
                    uniqueID = uniqueID.replace(" ","");
                    childIDLabel.setText(uniqueID);
                    childIDLabel.setBackground(null);
                    childIDLabel.setForeground(Color.RED);

                    String childName = childNameTextField.getText();
                    String momName = momNameTextField.getText();
                    String momID = momIDTextField.getText();
                    String dadName = dadNameTextField.getText();
                    String dadID = dadIDTextField.getText();
                    String address = addressTextField.getText();
                    int childWeight = Integer.parseInt(childWeightTextField.getText());
                    int childHeight = Integer.parseInt(childHeightTextField.getText());
                    String bType = String.valueOf(BT.getSelectedItem());
                    String gend = genderGroup.getSelection().getActionCommand();
                    String healthStatus = healthStatusGroup.getSelection().getActionCommand();
                    String specialNotes = sNotesTextField.getText();
                    String hospital = String.valueOf(Hos.getSelectedItem());
                    String bcgBatch = bcgBatchTextField.getText();
                    java.util.Date bcgDate = (java.util.Date) bcgDateSpinner.getValue();
                    java.util.Date birthDate = (java.util.Date) bDateSpinner.getValue();
                    java.util.Date birthTime = (java.util.Date) btimeSpinner.getValue();
                    java.util.Date filledDate = (java.util.Date) dateSpinner.getValue();
                    String empID = empIDTextField.getText();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedBirthTime = sdf.format(birthTime);
                    String formattedBirthDate = sdf.format(birthDate);
                    String formattedBcgDate = sdf.format(bcgDate);
                    String formattedFilledDate = sdf.format(filledDate); 
                    
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    Connection conn = DriverManager.getConnection(url, user, password);
                    
                    int confirmation = JOptionPane.showConfirmDialog(mFrame,"\n\nConfirm ??? \n\nNewborn's Information\n\nChild Name:"+childName+"\nDate and Time of Birth:"+birthDate+"\nHospital:"+hospital+"\nMothers:"+momName+"\nNIC:"+momID+"\nFather:"+dadName+"\nNIC:"+dadID+"\nAddress"+address+"\n\nMedical Information\n\nBirth Weight:"+childWeight+"\nBirth Height:"+childHeight+"\nHealth Status:"+healthStatus+"\nBlood Type:"+bType+"\n\nVaccination Information\n\nBCG Batch No:"+bcgBatch+"\nAdministered Date:"+bcgDate+"\nYour ID:"+empID,"Confimation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
                    if(confirmation == JOptionPane.YES_OPTION){
                    String query = "INSERT INTO newborn_details (newborn_Id, date_Of_Birth, time_Of_Birth, child_Name, hospital, gender, mom_Name, mom_Id, dad_Name, dad_Id, address, birth_Weight, birth_Height, blood_Type, health_Status, special_Notes, bcg_Batch_No, bcg_Date, filled_Date, employee_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, uniqueID);
                    pstmt.setString(2, formattedBirthDate);
                    pstmt.setString(3, formattedBirthTime);
                    pstmt.setString(4, childName);
                    pstmt.setString(5, hospital);
                    pstmt.setString(6, gend);
                    pstmt.setString(7, momName);
                    pstmt.setString(8, momID);
                    pstmt.setString(9, dadName);
                    pstmt.setString(10, dadID);
                    pstmt.setString(11, address);
                    pstmt.setInt(12, childWeight);
                    pstmt.setInt(13, childHeight);
                    pstmt.setString(14, bType);
                    pstmt.setString(15, healthStatus);
                    pstmt.setString(16, specialNotes);
                    pstmt.setString(17, bcgBatch);
                    pstmt.setString(18, formattedBcgDate);
                    pstmt.setString(19, formattedFilledDate);
                    pstmt.setString(20, empID);

                    String vacQuery = "INSERT INTO updatevacc (newborn_id, batch_no, vaccine_date,age,vaccine,dose,adverse_effects,adverse_notes) VALUES (?, ?, ?,'24 Hours','BCG', '1','0','null')";
                    PreparedStatement vacstmt = conn.prepareStatement(vacQuery);
                    vacstmt.setString(1, uniqueID);
                    vacstmt.setString(2, bcgBatch);
                    vacstmt.setString(3, formattedBcgDate);
                    vacstmt.executeUpdate();

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                         JOptionPane.showMessageDialog(mFrame, "Data Entered Successfully", "Great!", JOptionPane.DEFAULT_OPTION);
                         JOptionPane.showMessageDialog(mFrame, "NewBorn's Id: "+uniqueID, "Important!", JOptionPane.INFORMATION_MESSAGE);
                     }
                    pstmt.close();
                    conn.close();
                    mFrame.dispose();
                }}catch(Exception c){
                    System.out.println(c.toString());
                    JOptionPane.showMessageDialog(mFrame, "Some Error Occured When Trying to Connect Database"+c.toString(), "Error!!!", JOptionPane.WARNING_MESSAGE);
                }}});
        mPanel.add(submit);
}}
    