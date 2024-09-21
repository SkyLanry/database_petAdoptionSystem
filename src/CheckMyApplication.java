import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;
public class CheckMyApplication implements GetPanel{
    private JButton returnButton;
    private JTable table1;
    private JPanel mp;
    private JScrollPane table;
    public CheckMyApplication(int currentUserAccount) throws SQLException {
        System.out.println("INIT TABLE ADOPT APPLICATION");
        String[] columnName = new String[]{"user_no","p_no","ad_no","use_user_no","app_name","app_sex","app_age","app_address","app_phonenumber","app_email"};
        String sql = "Select COUNT(*) As cnt From adoptionapplication WHERE user_no = " + currentUserAccount;
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        String[][] tableData = new String[resultSize][11];
        sql = "SELECT * FROM adoptionapplication WHERE user_no = " + currentUserAccount;
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<10; j++){
                try {
                    tableData[i][j] = result.getObject(columnName[j]).toString();
                }
                catch (NullPointerException e){
                    tableData[i][j] = "null";
                }
            }
            i++;
        }
        result.close();
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnName) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        Font font = new Font("Droid Sans Mono", Font.PLAIN, 16);
        table1.setFont(font);
        table1.setRowHeight(24);
        table1.setAlignmentX(Component.CENTER_ALIGNMENT);
        table.setFont(font);
        table1.setModel(tableModel);
        table.setViewportView(table1);

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
    }

    @Override
    public JPanel getPanel(){
        return mp;
    }

}