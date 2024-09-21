import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends JPanel{
    public static BufferedImage BACKGROUND;
    public static final Dimension DIMENSION_OF_FRAME = new Dimension(1600, 900);
    public static final CardLayout CARD_LAYOUT = new CardLayout(0, 0);
    public static final JPanel CARD_PANEL = new JPanel(CARD_LAYOUT);
    public static final JFrame MAIN_FRAME = new JFrame();
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static Main GLOBAL_INSTANCE;
    public static Connection connection;
    public static Statement statement;

    public Main() throws ClassNotFoundException, SQLException, IOException {
        super();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/lanrui?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="mysql";
        connection = DriverManager.getConnection(url,username,password);
        statement = connection.createStatement();
        switchStartMenu();
    }

    public static Main getGlobalInstance() throws IOException {
        return GLOBAL_INSTANCE;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        /*
        MAIN_FRAME.setIconImage(ImageIO.read(new File("src/bkg.jpg")));
        BACKGROUND = ImageIO.read(new FileInputStream("src/bkg.jpg"));
        WelcomePage welcomePage = new WelcomePage(){
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(
                        JFrame.class.getResource("src/bkg.jpg"));
                g.drawImage(img, 0, 0, 1600, 900, this);
            }
        };*/
        InitialPage initialPage = new InitialPage();
        MAIN_FRAME.setSize(DIMENSION_OF_FRAME);
        MAIN_FRAME.setResizable(true);
        MAIN_FRAME.setBounds(((int) SCREEN_SIZE.getWidth() - 1600) / 2, ((int) SCREEN_SIZE.getHeight() - 900)/2, 1600, 900);
        MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CARD_PANEL.add(initialPage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setTitle("Pet Adopt System");
        MAIN_FRAME.setVisible(true);
        GLOBAL_INSTANCE = new Main();
    }

    public static void switchStartMenu() throws IOException {
        InitialPage initialPage = new InitialPage();
        MAIN_FRAME.setTitle("Pet Adopt System");
        CARD_PANEL.add(initialPage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchAdministratorPage(){
        AdministratorPage administratorPage = new AdministratorPage();
        CARD_PANEL.add(administratorPage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchDatabaseEntry(){
        DatabaseEntry databaseEntry = new DatabaseEntry();
        CARD_PANEL.add(databaseEntry.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchDatabaseTableView(String tableName) throws SQLException, ClassNotFoundException {
        DatabaseTableView databaseTableView = new DatabaseTableView(tableName);
        CARD_PANEL.add(databaseTableView.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }


    public static void switchUserRegister() {
        UserRegister userRegister = new UserRegister();
        CARD_PANEL.add(userRegister.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchLogIn() {
        LogInPage logInPage = new LogInPage();
        CARD_PANEL.add(logInPage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchClientPage(int currentUserAccount) throws SQLException {
        UserPage userPage = new UserPage(currentUserAccount);
        CARD_PANEL.add(userPage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchFreezeDefreeze() {
        FreezePage freezePage = new FreezePage();
        CARD_PANEL.add(freezePage.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchApplicationForm(int userAccount) {
        SubmitApplication submitApplication = new SubmitApplication(userAccount);
        CARD_PANEL.add(submitApplication.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchCheckMyApplication(int userAccount) throws SQLException {
        CheckMyApplication checkMyApplication = new CheckMyApplication(userAccount);
        CARD_PANEL.add(checkMyApplication.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchViewMyAccountInfo(int userAccount) throws SQLException {
        ViewMyAccountInfo viewMyAccountInfo = new ViewMyAccountInfo(userAccount);
        CARD_PANEL.add(viewMyAccountInfo.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchExamineApplication() throws SQLException {
        ExamineApplication examineApplication = new ExamineApplication();
        CARD_PANEL.add(examineApplication.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchSubmitRevistRecord() throws SQLException {
        SubmitTrackingRecord submitTrackingRecord = new SubmitTrackingRecord();
        CARD_PANEL.add(submitTrackingRecord.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchViewPets(int userAccount) throws SQLException {
        ViewPetInfo viewPetInfo = new ViewPetInfo(userAccount);
        CARD_PANEL.add(viewPetInfo.getPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchErrorBox(String err) {
        ErrorBox errorBox = new ErrorBox(err);
        errorBox.setTitle("Password Error");
        errorBox.setBounds(((int) SCREEN_SIZE.getWidth() - 400) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 400, 270);
        errorBox.setResizable(false);
        errorBox.pack();
        errorBox.setVisible(true);
    }


/**
    public static void switchSearchingBar(String platform){
        SeachingBar seachingBar = new SeachingBar(platform);
        CARD_PANEL.add(seachingBar.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void passwordError(){
        PasswordErrorBox passwordErrorBox = new PasswordErrorBox();
        passwordErrorBox.setTitle("Password Error");
        passwordErrorBox.setBounds(((int) SCREEN_SIZE.getWidth() - 400) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 400, 270);
        passwordErrorBox.setResizable(false);
        passwordErrorBox.pack();
        passwordErrorBox.setVisible(true);
    }

    public static void accountError(){
        AccountErrorBox accountErrorBox = new AccountErrorBox();
        accountErrorBox.setTitle("Account Not Found");
        accountErrorBox.setBounds(((int) SCREEN_SIZE.getWidth() - 400) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 400, 270);
        accountErrorBox.setResizable(false);
        accountErrorBox.pack();
        accountErrorBox.setVisible(true);
    }

    public static void accountNotRegistered(String platform){
        AccountNotRegistered accountNotRegistered = new AccountNotRegistered(platform);
        accountNotRegistered.setTitle("Account Not Registered");
        accountNotRegistered.setBounds(((int) SCREEN_SIZE.getWidth() - 400) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 400, 270);
        accountNotRegistered.setResizable(false);
        accountNotRegistered.pack();
        accountNotRegistered.setVisible(true);
    }

    public static void calculating(String platform){
        Calculating calculating = new Calculating(platform);
        calculating.setTitle("Private Set Intersection Calculating");
        calculating.setPreferredSize(new Dimension(600,200));
        calculating.setBounds(((int) SCREEN_SIZE.getWidth() - 600) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 600, 270);
        calculating.setResizable(false);
        calculating.pack();
        calculating.setVisible(true);
    }

    public static void additionSuccess(String username){
        AdditionSuccess additionSuccess = new AdditionSuccess(username);
        additionSuccess.setTitle("Friend Addition Success");
        additionSuccess.setPreferredSize(new Dimension(600,270));
        additionSuccess.setBounds(((int) SCREEN_SIZE.getWidth() - 600) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 400, 600);
        additionSuccess.setResizable(false);
        additionSuccess.pack();
        additionSuccess.setVisible(true);
    }

    public static void userNotFound(){
        UserNotFoundBox userNotFoundBox = new UserNotFoundBox();
        userNotFoundBox.setTitle("User Not Found");
        userNotFoundBox.setPreferredSize(new Dimension(600,200));
        userNotFoundBox.setBounds(((int) SCREEN_SIZE.getWidth() - 600) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 600, 270);
        userNotFoundBox.setResizable(false);
        userNotFoundBox.pack();
        userNotFoundBox.setVisible(true);
    }

    public static void alreadyInList(String notification){
        AlreadyInList alreadyInList = new AlreadyInList(notification);
        alreadyInList.setTitle("Already In List");
        alreadyInList.setPreferredSize(new Dimension(600,200));
        alreadyInList.setBounds(((int) SCREEN_SIZE.getWidth() - 600) / 2, ((int) SCREEN_SIZE.getHeight() - 270)/2, 600, 270);
        alreadyInList.setResizable(false);
        alreadyInList.pack();
        alreadyInList.setVisible(true);
    }


    public static void switchLoginA(){
        LoginA loginA = new LoginA();
        CARD_PANEL.add(loginA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchPlatformA() throws IOException {
        PlatformAMainPage platformA = new PlatformAMainPage();
        CARD_PANEL.add(platformA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchFriendListA(){
        FriendListA friendListA = new FriendListA();
        CARD_PANEL.add(friendListA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchServerLoginA(){
        ServerLoginA serverLoginA = new ServerLoginA();
        CARD_PANEL.add(serverLoginA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchServerA(){
        ServerA serverA = new ServerA();
        CARD_PANEL.add(serverA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchPsiResA(){
        PsiResA psiResA = new PsiResA();
        CARD_PANEL.add(psiResA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchSearchResA(ClientA clientA){
        SearchResA searchResA = new SearchResA(clientA);
        CARD_PANEL.add(searchResA.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchLoginB(){
        LoginB loginB = new LoginB();
        CARD_PANEL.add(loginB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchPlatformB() throws IOException {
        PlatformBMainPage platformB = new PlatformBMainPage();
        CARD_PANEL.add(platformB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchFriendListB(){
        FriendListB friendListB = new FriendListB();
        CARD_PANEL.add(friendListB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchServerLoginB(){
        ServerLoginB serverLoginB = new ServerLoginB();
        CARD_PANEL.add(serverLoginB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchServerB(){
        ServerB serverB = new ServerB();
        CARD_PANEL.add(serverB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchPsiResB(){
        PsiResB psiResB = new PsiResB();
        CARD_PANEL.add(psiResB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }

    public static void switchSearchResB(ClientB clientB){
        SearchResB searchResB = new SearchResB(clientB);
        CARD_PANEL.add(searchResB.getMainPanel());
        CARD_LAYOUT.next(CARD_PANEL);
        MAIN_FRAME.add(CARD_PANEL);
        MAIN_FRAME.setVisible(true);
    }


    public void loadUserListA(Initializing initializing){
        initializing.systemLabel.setText("A");
        File file =new File("src/PSI/PlatformA/ClientOfA2bit.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            /**ClientA client = new ClientA();
             int i=0;
             while(client != null) {
             i++;
             if(i % 65 == 0){
             initializing.processLabel.setText(String.format("%5.1f",i/655.0)+"%");
             }
             client = (ClientA) objIn.readObject();
             clientAList.add(client);
             }
            clientAList = (List<ClientA>) objIn.readObject();
            objIn.close();
        }
        catch (EOFException e){
            System.out.println("Read Object Success");
        }
        catch (IOException e ) {
            System.out.println("read object failed");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadUserListB(Initializing initializing){
        initializing.systemLabel.setText("B");
        File file =new File("src/PSI/PlatformB/ClientOfB2bit.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            /**ClientB client = new ClientB();
             int i = 0;
             while(client != null) {
             i++;
             if(i % 65 == 0){
             initializing.processLabel.setText(String.format("%5.1f",i/655.0)+"%");
             }
             client = (ClientB) objIn.readObject();
             clientBList.add(client);
             }
            clientBList = (List<ClientB>) objIn.readObject();
            objIn.close();
        }
        catch (EOFException e){
            System.out.println("Read Object Success");
        }
        catch (IOException e ) {
            System.out.println("read object failed");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(Main.BACKGROUND,100,100,null);
        System.out.println("REPAINT");
    }
}