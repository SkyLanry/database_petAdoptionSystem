import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.lang.Integer.parseInt;
public class DatabaseTableView implements GetPanel{
    private JButton returnButton;
    private JTable table1;
    private JPanel mp;
    private JScrollPane table;
    String[] columnName;
    private String[][] tableData;

    public DatabaseTableView(String tableName) throws SQLException {
        switch (tableName){
            case "PetInfo": initPet(); break;
            case "UserInfo": initUserInfo(); break;
            case "AdoptionApplication": initAdoptApplication(); break;
            case "AdoptInfo": initAdoptInfo(); break;
            case "Breed": initSpecies(); break;
            //case "City": initCity(); break;
            //case "Province": initProvince(); break;
            case "RevisitRecord": initAdoptionTracking(); break;
            case "Adopter_Pet": initAdopterPet(); break;
            //case "User_City_Province": initUserCityProvince(); break;
            default:break;
        }

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
                Main.switchDatabaseEntry();
            }
        });
    }

    public void initPet() throws SQLException {
        System.out.println("INIT TABLE PET");
        columnName = new String[]{"p_no", "p_nickname", "p_birthday", "p_age", "p_sex", "p_species", "p_character", "p_state", "ad_no"};
        //columnName = new String[]{"petID", "petName", "birthday", "age", "gender", "breedID", "adoptID", "colour", "petCharacter", "isAdopted", "isApplied"};
        String sql = "Select COUNT(*) As cnt From petinfo";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][11];
        sql = "Select * From petinfo";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()) {
            for(int j=0; j<9; j++) {
                try {
                    tableData[i][j] = result.getObject(columnName[j]).toString();
                } catch (NullPointerException e) {
                    tableData[i][j] = "null";
                }
            }
            i++;
        }
        result.close();
    }

    public void initUserInfo() throws SQLException {
        System.out.println("INIT TABLE USER INFO");
        columnName = new String[]{"user_no", "ad_no", "user_state","user_password","user_name"};
        String sql = "Select COUNT(*) As cnt From userinfo";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][6];
        sql = "Select * From userinfo";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<5; j++){
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
    }

    public void initAdoptApplication() throws SQLException {
        System.out.println("INIT TABLE ADOPT APPLICATION");
        columnName = new String[]{"user_no","p_no","ad_no","use_user_no","app_name","app_sex","app_age","app_address","app_phonenumber","app_email"};
        String sql = "Select COUNT(*) As cnt From adoptionapplication";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][10];
        sql = "Select * From adoptionapplication";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        System.out.println("AAA");
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
    }

    public void initAdoptInfo() throws SQLException {
        System.out.println("INIT TABLE ADOPT INFO");
        columnName = new String[]{"user_no", "p_no"};
        String sql = "Select COUNT(*) As cnt From adoptinfo";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][5];
        sql = "Select * From adoptinfo";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<2; j++){
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
    }

    public void initSpecies() throws SQLException {
        System.out.println("INIT TABLE BREED");
        columnName = new String[]{"p_species","species_name"};
        String sql = "Select COUNT(*) As cnt From petspecies";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][2];
        sql = "Select * From petspecies";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<2; j++){
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
    }

    /*public void initCity() throws SQLException {
        System.out.println("INIT TABLE CITY");
        columnName = new String[]{"cityID", "cityName", "provinceID"};
        String sql = "Select COUNT(*) As cnt From city";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][3];
        sql = "Select * From city";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<3; j++){
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
    }

    public void initProvince() throws SQLException {
        System.out.println("INIT TABLE PROVINCE");
        columnName = new String[]{"provinceID", "provinceName"};
        String sql = "Select COUNT(*) As cnt From province";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][2];
        sql = "Select * From province";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<2; j++){
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
    }*/

    public void initAdoptionTracking() throws SQLException {
        System.out.println("INIT TABLE REVISIT RECORD");
        columnName = new String[]{"result", "user_no", "ad_no", "time", "p_no"};
        String sql = "Select COUNT(*) As cnt From adoptiontracking";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][5];
        sql = "Select * From adoptiontracking";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<5; j++){
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
    }

    public void initAdopterPet() throws SQLException {
        System.out.println("INIT VIEW ADOPTER_PET");
        columnName = new String[]{"user_no", "p_no"};
        String sql = "Select COUNT(*) As cnt From adopter_pet";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][2];
        sql = "Select * From adopter_pet";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<2; j++){
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
    }

    /*public void initUserCityProvince() throws SQLException {
        System.out.println("INIT TABLE USER_CITY_PROVINCE");
        columnName = new String[]{"userName","city","province"};
        String sql = "Select COUNT(*) As cnt From user_city_province";
        ResultSet result = Main.statement.executeQuery(sql);
        result.next();
        int resultSize = parseInt(result.getObject("cnt").toString());
        tableData = new String[resultSize][3];
        sql = "Select * From user_city_province";
        result = Main.statement.executeQuery(sql);
        int i = 0;
        while (result.next()){
            for(int j=0; j<3; j++){
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
    }*/


    @Override
    public JPanel getPanel(){
        return mp;
    }

}
