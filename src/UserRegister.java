import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static java.lang.Integer.parseInt;
public class UserRegister implements GetPanel{
    private JPanel mp;
    private JTextField textField2;
    private JTextField textField3;
    private JButton confirmButton;
    private JButton cancelButton;
    public UserRegister() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "Select Count(*) as cnt From userInfo";
                try {
                    ResultSet result = Main.statement.executeQuery(sql);
                    if(!result.next()){
                        Main.switchErrorBox("NULL!");
                    }else {
                        int resultSize = parseInt(result.getObject("cnt").toString()) + 1;
                        if(!(Objects.equals(textField2.getText(), "")||Objects.equals(textField3.getText(), ""))){
                            sql = "INSERT INTO userinfo VALUES ("
                                    + resultSize + ", "
                                    + 1 + ", '"
                                    + "Normal" + "', '"
                                    + textField3.getText() +"', '"
                                    + textField2.getText() + "')";
                            System.out.println("The Sql is: " + sql);
                            Main.statement.execute(sql);
                            Main.switchErrorBox("注册成功，您的帐号为：" + resultSize);
                        }
                        else {
                            Main.switchErrorBox("Please Fill All The Form.");
                            System.out.println("Please Fill All The Form.");
                        }
                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchStartMenu();
                } catch (IOException ex) {
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
