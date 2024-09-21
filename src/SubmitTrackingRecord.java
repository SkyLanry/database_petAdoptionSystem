import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Integer.parseInt;
public class SubmitTrackingRecord implements GetPanel{
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JPanel mp;
    private JButton returnButton;


    public SubmitTrackingRecord() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                if(Objects.equals(text1, "") || Objects.equals(text2, "")){
                    Main.switchErrorBox("There exist empty to be filled.");
                }
                else {
                    String sql = "SELECT * FROM adoptinfo WHERE user_no = " + text1;
                    try {
                        ResultSet result = Main.statement.executeQuery(sql);
                        if(!result.next()){
                            Main.switchErrorBox("Adopt record whoes ID = " + text1 + " does not exist.");
                        }
                        else {
                            int petID = parseInt(result.getObject("p_no").toString());
                            sql = "SELECT COUNT(*) AS cnt FROM adoptiontracking";
                            result = Main.statement.executeQuery(sql);
                            result.next();
                            //int revisitID = parseInt(result.getObject("cnt").toString()) + 1;
                            sql = "INSERT INTO adoptiontracking VALUES (" +
                                    "'" +
                                    text2 +
                                    "', " +
                                    text1 +
                                    ", " +
                                    1 +
                                    ", NOW(), " +
                                    petID + "," +
                                    1 +
                                    ")";
                            System.out.println("SQL: " + sql);
                            Main.statement.execute(sql);
                            Main.switchErrorBox("This revisit form is added to records.");
                        }
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
    public JPanel getPanel(){
        return mp;
    }
}
