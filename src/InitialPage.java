import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class InitialPage implements GetPanel{
    private JPanel mp;
    private JLabel imageLabel = new JLabel(new ImageIcon("src/bkg.jpg"));
    private JButton clientLogInButton;
    private JButton administratorLogInButton;
    private JButton registerButton;
    private JLabel welcomeLabel1;

    public InitialPage() throws IOException {
        mp.setOpaque(false);

        administratorLogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchAdministratorPage();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchUserRegister();
            }
        });
        clientLogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.switchLogIn();
            }
        });
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }



}
