import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ViewMyAccountInfo implements GetPanel{
    private JPanel mp;
    private JButton returnButton;
    private JLabel accountField;
    private JLabel nameField;
    private JLabel passwordField;
    private JLabel statusField;

    public ViewMyAccountInfo(int currentAccount) {
        String sql = "SELECT * FROM userinfo WHERE user_no = " + currentAccount;
        try {
            ResultSet result = Main.statement.executeQuery(sql);
            result.next();
            accountField.setText(result.getObject("user_no").toString());
            nameField.setText(result.getObject("user_name").toString());
            passwordField.setText(result.getObject("user_password").toString());
            statusField.setText(result.getObject("user_state").toString());

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchClientPage(currentAccount);
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
