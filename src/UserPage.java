import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
public class UserPage implements GetPanel{
    private JButton viewPetListButton;
    private JButton submitAnApplicationForButton;
    private JButton returnToLogInButton;
    private JButton viewMyApplicationButton;
    private JButton viewMyAccountInfoButton;
    private JPanel mp;
    private JLabel welcomeText;
    private final int currentUserAccount;
    private String sql;
    private String userName;


    public UserPage(int userAccount) throws SQLException {
        currentUserAccount = userAccount;
        sql = "SELECT user_name FROM userinfo WHERE user_no = " + currentUserAccount;
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        userName = result.getObject("user_name").toString();
        welcomeText.setText("Welcome, " + userName + "!");
        submitAnApplicationForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sql = "SELECT user_state FROM userinfo WHERE user_no = " + currentUserAccount;
                try {
                    ResultSet result = Main.statement.executeQuery(sql);
                    result.next();
                    if(Objects.equals(result.getObject("user_state").toString(), "Frozen")){
                        Main.switchErrorBox("Sorry, your account is frozen and you cannot submit an application");
                    }
                    else{
                        Main.switchApplicationForm(currentUserAccount);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        viewMyApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchCheckMyApplication(currentUserAccount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        viewMyAccountInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchViewMyAccountInfo(currentUserAccount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        returnToLogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchLogIn();
            }
        });
        viewPetListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.switchViewPets(currentUserAccount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public int getCurrentUserAccount(){
        return currentUserAccount;
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }
}
