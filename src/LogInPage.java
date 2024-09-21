import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
public class LogInPage implements GetPanel{
    private JPanel mp;
    private JButton logInButton;
    private JButton returnButton;
    private JTextField accountField;
    private JTextField passwordField;
    private ResultSet result;
    public LogInPage() {

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFound = false;
                if(Objects.equals(accountField.getText(), "") && Objects.equals(passwordField.getText(), "")){
                    Main.switchErrorBox("Account Or Password Is Empty!");
                }
                else {
                    String sql = "Select * From userinfo";
                    try {
                        result = Main.statement.executeQuery(sql);
                        while(result.next()){
                            if(Objects.equals(result.getObject("user_no").toString(), accountField.getText())){
                                isFound = true;
                                System.out.println("Password: "+result.getObject("user_password"));
                                if(Objects.equals(result.getObject("user_password").toString(), passwordField.getText())){
                                    Main.switchClientPage((Integer) result.getObject("user_no"));
                                }
                                else{
                                    Main.switchErrorBox("Password Error!");
                                }
                            }
                        }
                        if(!isFound){
                            Main.switchErrorBox("Account Not Found");
                        }

                    } catch (SQLException ex) {
                        ;
                    }
                }

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchStartMenu();
                } catch (IOException ex) {
                    ;
                }
            }
        });
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }
}
