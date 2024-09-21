import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Integer.parseInt;
//import static jdk.dynalink.beans.StaticClass.forClass;

public class SubmitApplication implements GetPanel{
    private JPanel mp;
    private JTextField petField;
    private JTextField nameField;
    private JTextField ageField;
    private JButton submitButton;
    private JButton returnButton;
    private JTextField cityField;
    private JTextField telField;
    private JTextField mailField;
    private JComboBox genderField;

    public SubmitApplication(int currentUserAccount) {

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchClientPage(currentUserAccount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT COUNT(*) AS cnt FROM adoptionapplication";
                try {
                    ResultSet result = Main.statement.executeQuery(sql);
                    result.next();
                    //int applicationId = parseInt(result.getObject("cnt").toString()) + 1;
                    String adopterName = nameField.getText();
                    int p_no = parseInt(petField.getText());
                    int adopterAge = parseInt(ageField.getText());
                    String adopter_sex;
                    switch (genderField.getSelectedIndex()) {
                        case 1:adopter_sex = "男";break;
                        case 2:adopter_sex = "女";break;
                        default:adopter_sex = "未知";break;
                    };
                    String adopterCity = cityField.getText();
                    String adopterTEL = telField.getText();
                    String adopterMail = mailField.getText();
                    boolean isValid = true;
                    sql = "SELECT p_state FROM petinfo WHERE p_no = " + p_no;
                    result = Main.statement.executeQuery(sql);
                    if(!result.next()){
                        Main.switchErrorBox("Sorry, pet whose ID = " + p_no + "does not exist.");
                        isValid = false;
                    }
                    if(Objects.equals(result.getObject("p_state").toString(), "1")) {
                        Main.switchErrorBox("Sorry, pet whose ID = " + p_no + "has been adopted already.");
                        isValid = false;
                    }
                    if(Objects.equals(telField.getText(), "")){
                        isValid = false;
                    }
                    if(!isValid){
                        Main.switchErrorBox("Information filled is not valid");
                    }
                    else{
                        sql = "INSERT INTO adoptionapplication VALUES (" +
                                currentUserAccount +
                                ", " +
                                p_no +
                                ", " +
                                1 +
                                ", " +
                                currentUserAccount +
                                ", '" +
                                adopterName +
                                "', '" +
                                adopter_sex +
                                "', " +
                                adopterAge +
                                ", '" +
                                adopterTEL +
                                "', '" +
                                adopterMail +
                                "', '" +
                                adopterCity +
                                "', '" +
                                "false" +
                                "')";

                        System.out.println("SQL = " + sql);
                        Main.statement.execute(sql);
                        Main.switchErrorBox("您的申请已提交，请等待管理员审核。");
                    }


                } catch (SQLException | NumberFormatException ex) {
                    ex.printStackTrace();
                    if(ex instanceof NumberFormatException){
                        Main.switchErrorBox("Number Format Error (Or Empty)");
                    }
                }
            }
        });
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }

}
