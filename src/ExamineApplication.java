import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Integer.parseInt;
public class ExamineApplication implements GetPanel{
    private JPanel mp;
    private JTextField textField1;
    private JButton findRecordButton;
    private JButton acceptButton;
    private JButton returnButton;
    private JLabel accountLabel;
    private JLabel nameLabel;
    private JLabel petLabel;
    private JLabel petNameLabel;
    private JLabel breedLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;
    private JLabel cityLabel;
    private JLabel telLabel;
    private JLabel veryLabel;
    private int applicationID = 0;
    private int petID = 0;
    private String sql1, sql2, sql3, sql4, sql5;

    public ExamineApplication() {
        findRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationID = parseInt(textField1.getText());
                sql1 = "SELECT * FROM adoptionapplication WHERE user_no = " + applicationID;
                try {
                    ResultSet result = Main.statement.executeQuery(sql1);
                    if(!result.next()){
                        Main.switchErrorBox("Application whose ID = " + applicationID + " does not exist.");
                    }
                    else {
                        sql2 =  "SELECT userinfo.user_no AS Account," +
                                "adoptionapplication.app_name AS AName," +
                                "adoptionapplication.p_no AS PetID," +
                                "adoptionapplication.app_sex AS Gender," +
                                "adoptionapplication.app_age AS Age," +
                                "adoptionapplication.app_phonenumber AS Tel," +
                                "adoptionapplication.isApproved AS Verification," +
                                "petinfo.p_nickname AS PName," +
                                "petspecies.species_name AS SName," +
                                "adoptionapplication.app_address AS AddressName" +
                                " FROM userinfo, adoptionapplication, petinfo, petspecies" +
                                " WHERE adoptionapplication.user_no = " + applicationID +
                                " And userinfo.user_no = adoptionapplication.user_no" +
                                " And adoptionapplication.p_no = petinfo.p_no" +
                                " And petinfo.p_species = petspecies.p_species";
                    }
                    result = Main.statement.executeQuery(sql2);
                    result.next();
                    petID = parseInt(result.getObject("PetID").toString());
                    accountLabel.setText(result.getObject("Account").toString());
                    nameLabel.setText(result.getObject("AName").toString());
                    petLabel.setText(result.getObject("PetID").toString());
                    petNameLabel.setText(result.getObject("PName").toString());
                    breedLabel.setText(result.getObject("SName").toString());
                    genderLabel.setText(result.getObject("Gender").toString());
                    ageLabel.setText(result.getObject("Age").toString());
                    cityLabel.setText(result.getObject("AddressName").toString());
                    telLabel.setText(result.getObject("Tel").toString());
                    veryLabel.setText(result.getObject("Verification").toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Objects.equals(accountLabel.getText(), "<DEFAULT>")){
                    Main.switchErrorBox("Please select an application before operation.");
                }
                else if(Objects.equals(veryLabel.getText(), "true")) {
                    Main.switchErrorBox("This application has been approved already.");
                }
                else {
                    try {
                        sql3 =  "SELECT COUNT(*) AS cnt FROM adoptinfo";
                        ResultSet result = Main.statement.executeQuery(sql3);
                        result.next();
                        //int adoptID = parseInt(result.getObject("cnt").toString()) + 1;
                        sql4 =  "INSERT INTO adoptinfo VALUES (" + applicationID + ", " + petID + ")";
                        //sql5 =  "UPDATE adoptapplication SET adoptID = " + adoptID +
                        //        " WHERE applicationID = " + applicationID;
                        System.out.println("SQL: " + sql4);
                        //System.out.println("SQL: " + sql5 );
                        Main.statement.execute(sql4);
                        //Main.statement.execute(sql5);
                        sql5 =  "UPDATE adoptionapplication SET isApproved = true" +
                                " WHERE user_no = " + applicationID +
                                " AND p_no = " + petID;

                        //System.out.println("SQL: " + sql5 );
                        Main.statement.execute(sql5);
                        findRecordButton.doClick();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchAdministratorPage();
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return mp;
    }
}
