import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
public class FreezePage implements GetPanel{
    private JPanel mp;
    private JTextField textField1;
    private JButton checkAccountStatusButton;
    private JLabel statusField;
    private JLabel noticefield;
    private JButton freezeButton;
    private JButton returnButton;
    private JButton defreezeButton;
    private String currentAccount;
    private String currentStatus;


    public FreezePage() {
        checkAccountStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFound = false;
                String enteredText = textField1.getText();
                if(Objects.equals(enteredText, "")){
                    Main.switchErrorBox("Account Is Empty!");
                }
                else{
                    String sql = "SELECT * FROM userinfo";
                    try {
                        ResultSet result = Main.statement.executeQuery(sql);
                        while(result.next()){
                            if(Objects.equals(enteredText, result.getObject("user_no").toString())){
                                currentAccount = enteredText;
                                currentStatus = result.getObject("user_state").toString();
                                isFound = true;
                                break;
                            }
                        }
                        if(!isFound){
                            Main.switchErrorBox("User Account Not Found!");
                        }
                        statusField.setText("User Account: " + currentAccount + ", Status: " + currentStatus);
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
        freezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "UPDATE userinfo SET user_state = 'Frozen' WHERE user_no = " + currentAccount;
                try {
                    Main.statement.execute(sql);
                    Main.switchErrorBox("User (account = " + currentAccount + ") has been frozen.");
                    checkAccountStatusButton.doClick();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        defreezeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "UPDATE userinfo SET user_state = 'Normal' WHERE user_no = " + currentAccount;
                try {
                    Main.statement.execute(sql);
                    Main.switchErrorBox("User (account = " + currentAccount + ") has been unfrozen.");
                    checkAccountStatusButton.doClick();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }
}
