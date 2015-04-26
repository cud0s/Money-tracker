/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoneyTracker;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author slvai_000
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    private MTUser currUser;
    private final UserManager userManager;
    private final DefaultListModel<String> entryListModel = new DefaultListModel<>();
    private final DefaultListModel<String> loansListModel = new DefaultListModel<>();
    private boolean ignoreListChanges = false;                                  // galima geriau?

    static ExecutorService executor = Executors.newFixedThreadPool(4); ;
    
    public MainJFrame() {
        userManager = UserManagerFactory.getUserManager();
        initComponents();
        pList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loansList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detailsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        pName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pPrice.requestFocusInWindow();
            }
        });
        pPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pAddButtonActionPerformed(e);
            }
        });
    }

    private void clearJTextFields(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    private void login() {
        mainPane.getRootPane().setDefaultButton(pAddButton);
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card6");
        pName.requestFocus();
        clearJTextFields(registration);
        clearJTextFields(login);
        updateStats();
    }

    private void logout() {        
        mainPane.getRootPane().setDefaultButton(null);
        currUser = null;
        entryListModel.removeAllElements();
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card1");
    }

    private void updateStats() {
        statsUsn.setText(currUser.getUsername());
        statsMostCom.setText(currUser.getMost(true));
        statsMostSpent.setText(currUser.getMost(false));          

        loansUsn.setText(currUser.getUsername());

        jLabel12.setText(Integer.toString(currUser.getBudget()));
        listPurchases();
    }

    private void updateStats(int index) {
        updateStats();

        index = (currUser.getTotalEntries() - 1) - index; // because list in GUI is upside-down
        Entry selectedEntry = currUser.getEntry(index);
        pIncome.setSelected(selectedEntry instanceof Income);
        pExpenditure.setSelected(selectedEntry instanceof Expenditure);
        pLoanCheckBox.setSelected(selectedEntry instanceof Loan);

        String entryName = selectedEntry.getName();

        pName.setText(entryName);
        String newPrice = Integer.toString(Math.abs(selectedEntry.getPrice()));
        pPrice.setText(newPrice);
        
        //------------------better solution needed --------------------
        String[] details = selectedEntry.getDetails();
        dLabel1.setText(details[0]);
        dLabel2.setText(details[2]);
        dLabel3.setText(details[4]);
        dLabel4.setText(details[6]);
        
        dLabelRight1.setText(details[1]);
        dLabelRight2.setText(details[3]);
        dLabelRight3.setText(details[5]);
        dLabelRight4.setText(details[7]);
        
        dItemName.setText("<html>"+details[8]+"</html>");
        //-------------------------------------------------------------
    }

    private void listPurchases() {
        ignoreListChanges = true;
        int totalUserEntr = currUser.getTotalEntries();
        int userCountDifference = totalUserEntr - entryListModel.size();

        String dataString; //will save string, containing "cost", "type" and "name" variables of an entry

        switch (userCountDifference) {
            case 1:
                dataString = currUser.getEntryData(totalUserEntr - 1);
                entryListModel.add(0, dataString);
                if (dataString.contains("Loan")) {
                    loansListModel.add(0, dataString);
                }
                break;
            case 0:
                break;
            default:
                entryListModel.removeAllElements();
                for (int a = 0; a < totalUserEntr; a++) {
                    dataString = currUser.getEntryData(a);
                    entryListModel.add(0, dataString);
                    if (dataString.contains("Loan")) {
                        loansListModel.add(0, dataString);
                    }
                }
                break;
        }
        ignoreListChanges = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        firstPanel = new javax.swing.JPanel();
        regButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        registration = new javax.swing.JPanel();
        regUsnField = new javax.swing.JTextField();
        regAgeField = new javax.swing.JTextField();
        regBudgetField = new javax.swing.JTextField();
        regPassField = new javax.swing.JPasswordField();
        regBackButton = new javax.swing.JButton();
        regContinueButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        logUsnField = new javax.swing.JTextField();
        logPasswordField = new javax.swing.JPasswordField();
        logBackButton = new javax.swing.JButton();
        logContinueLogin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mainPane = new javax.swing.JTabbedPane();
        purchases = new javax.swing.JPanel();
        pName = new javax.swing.JTextField();
        pPrice = new javax.swing.JTextField();
        pAddButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pExpenditure = new javax.swing.JRadioButton();
        pExpenditure.setSelected(true);
        pIncome = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pLogoutButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        pList = new javax.swing.JList();
        pLoanCheckBox = new javax.swing.JCheckBox();
        stats = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        statsLogoutButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        statsUsn = new javax.swing.JLabel();
        statsMostCom = new javax.swing.JLabel();
        statsMostSpent = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        statsList = new javax.swing.JList();
        loans = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        loansLogoutButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        loansUsn = new javax.swing.JLabel();
        loansYearlyInterest = new javax.swing.JLabel();
        loansMonthlyPayment = new javax.swing.JLabel();
        loansTotalRemaining = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loansList = new javax.swing.JList();
        loansAddButton = new javax.swing.JButton();
        entryDetails = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        detailsLogoutButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        dItemName = new javax.swing.JLabel();
        dLabel1 = new javax.swing.JLabel();
        dLabel2 = new javax.swing.JLabel();
        dLabel3 = new javax.swing.JLabel();
        dLabel4 = new javax.swing.JLabel();
        dLabelRight1 = new javax.swing.JLabel();
        dLabelRight2 = new javax.swing.JLabel();
        dLabelRight3 = new javax.swing.JLabel();
        dLabelRight4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        detailsList = new javax.swing.JList();
        detailsBackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        regButton.setText("Register");
        regButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regButtonActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout firstPanelLayout = new javax.swing.GroupLayout(firstPanel);
        firstPanel.setLayout(firstPanelLayout);
        firstPanelLayout.setHorizontalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstPanelLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        firstPanelLayout.setVerticalGroup(
            firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstPanelLayout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addGroup(firstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(188, 188, 188))
        );

        getContentPane().add(firstPanel, "card1");

        regBackButton.setText("Back");
        regBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regBackButtonActionPerformed(evt);
            }
        });

        regContinueButton.setText("Register");
        regContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regContinueButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Username:");

        jLabel2.setText("Starting budget:");

        jLabel3.setText("Password:");

        jLabel4.setText("Age:");

        javax.swing.GroupLayout registrationLayout = new javax.swing.GroupLayout(registration);
        registration.setLayout(registrationLayout);
        registrationLayout.setHorizontalGroup(
            registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrationLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registrationLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(regBudgetField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registrationLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(regUsnField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registrationLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(regPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registrationLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(regAgeField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(233, 233, 233))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registrationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(regBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270)
                .addComponent(regContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        registrationLayout.setVerticalGroup(
            registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registrationLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regUsnField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(17, 17, 17)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regAgeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regBudgetField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(registrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        regPassField.getAccessibleContext().setAccessibleName("");

        getContentPane().add(registration, "card2");

        logUsnField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logUsnFieldActionPerformed(evt);
            }
        });

        logBackButton.setText("Back");
        logBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logBackButtonActionPerformed(evt);
            }
        });

        logContinueLogin.setText("Login");
        logContinueLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logContinueLoginActionPerformed(evt);
            }
        });

        jLabel5.setText("Username:");

        jLabel9.setText("Password:");

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(logBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(logContinueLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(167, 167, 167)
                                .addComponent(logPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(163, 163, 163)
                                .addComponent(logUsnField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(139, 139, 139))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logUsnField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(41, 41, 41)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logContinueLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(login, "card3");

        pName.setNextFocusableComponent(pPrice);
        pName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pNameActionPerformed(evt);
            }
        });

        pPrice.setNextFocusableComponent(pAddButton);
        pPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pPriceActionPerformed(evt);
            }
        });

        pAddButton.setText("Add");
        pAddButton.setNextFocusableComponent(pName);
        pAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pAddButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Price:");

        jLabel7.setText("Name:");

        jLabel8.setText("Budget:");

        buttonGroup1.add(pExpenditure);
        pExpenditure.setText("Expenditure");
        pExpenditure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pExpenditureActionPerformed(evt);
            }
        });

        buttonGroup1.add(pIncome);
        pIncome.setText("Income");
        pIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pIncomeActionPerformed(evt);
            }
        });

        jLabel10.setText("Entries:");

        jLabel12.setText("jLabel12");

        pLogoutButton.setText("Log out");
        pLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pLogoutButtonActionPerformed(evt);
            }
        });

        pList.setModel(entryListModel);
        pList.setNextFocusableComponent(pAddButton);
        pList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(pList);

        pLoanCheckBox.setText("Loan");
        pLoanCheckBox.setVisible(false);
        pLoanCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pLoanCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout purchasesLayout = new javax.swing.GroupLayout(purchases);
        purchases.setLayout(purchasesLayout);
        purchasesLayout.setHorizontalGroup(
            purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasesLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pLogoutButton)
                    .addGroup(purchasesLayout.createSequentialGroup()
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(70, 70, 70)
                                .addComponent(pName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(77, 77, 77)
                                .addComponent(pPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addComponent(pExpenditure)
                                .addGap(85, 85, 85)
                                .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pLoanCheckBox)
                                    .addComponent(pIncome))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(66, 66, 66))
        );
        purchasesLayout.setVerticalGroup(
            purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purchasesLayout.createSequentialGroup()
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8))
                        .addGap(35, 35, 35)
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7))
                            .addComponent(pName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addComponent(pPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(purchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pExpenditure)
                            .addComponent(pIncome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pLoanCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(purchasesLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pLogoutButton)
                .addGap(20, 20, 20))
        );

        mainPane.addTab("Add purchase", purchases);

        jLabel11.setText("Entries:");

        statsLogoutButton.setText("Log out");
        statsLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsLogoutButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Most common purchase:");

        jLabel14.setText("Most money spent on:");

        jLabel15.setText("Username:");

        statsUsn.setText("jLabel16");

        statsMostCom.setText("jLabel17");

        statsMostSpent.setText("jLabel18");

        statsList.setModel(entryListModel);
        jScrollPane4.setViewportView(statsList);

        javax.swing.GroupLayout statsLayout = new javax.swing.GroupLayout(stats);
        stats.setLayout(statsLayout);
        statsLayout.setHorizontalGroup(
            statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statsLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(statsUsn))
                    .addGroup(statsLayout.createSequentialGroup()
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(2, 2, 2)
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(statsLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(statsMostCom))
                            .addComponent(statsMostSpent)))
                    .addComponent(statsLogoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );
        statsLayout.setVerticalGroup(
            statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statsLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(statsLayout.createSequentialGroup()
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(statsUsn))
                        .addGap(45, 45, 45)
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(statsLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(45, 45, 45)
                                .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(statsMostSpent)))
                            .addComponent(statsMostCom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                        .addComponent(statsLogoutButton)))
                .addGap(20, 20, 20))
        );

        mainPane.addTab("View statistics", stats);

        jLabel17.setText("Loans:");

        loansLogoutButton.setText("Log out");
        loansLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loansLogoutButtonActionPerformed(evt);
            }
        });

        jLabel18.setText("Total remaining value:");

        jLabel19.setText("Monthly payment:");

        jLabel20.setText("Username:");

        jLabel21.setText("Yearly interest:");

        loansUsn.setText("jLabel16");

        loansYearlyInterest.setText("empty");

        loansMonthlyPayment.setText("empty");

        loansTotalRemaining.setText("empty");

        loansList.setModel(loansListModel);
        jScrollPane2.setViewportView(loansList);

        loansAddButton.setText("Add");
        loansAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loansAddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loansLayout = new javax.swing.GroupLayout(loans);
        loans.setLayout(loansLayout);
        loansLayout.setHorizontalGroup(
            loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(loansUsn))
                    .addComponent(loansAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(loansMonthlyPayment))
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(loansYearlyInterest))
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(loansTotalRemaining))
                    .addComponent(loansLogoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loansLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(226, 226, 226))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loansLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );
        loansLayout.setVerticalGroup(
            loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loansLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(loansUsn))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(loansYearlyInterest))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(loansMonthlyPayment))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(loansTotalRemaining))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(loansAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2)))
                .addGap(18, 18, 18)
                .addComponent(loansLogoutButton)
                .addGap(20, 20, 20))
        );

        mainPane.addTab("Loans", loans);

        jLabel22.setText("Entries:");

        detailsLogoutButton.setText("Log out");
        detailsLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsLogoutButtonActionPerformed(evt);
            }
        });

        jLabel25.setText("Item name:");

        dItemName.setText("jLabel16");
        dItemName.setMaximumSize(new java.awt.Dimension(350, 40));
        dItemName.setName(""); // NOI18N

        dLabel1.setText("Calories:");

        dLabel2.setText("Carbohydrates:");

        dLabel3.setText("Protein:");

        dLabel4.setText("Fat:");

        dLabelRight1.setText("empty");

        dLabelRight2.setText("empty");

        dLabelRight3.setText("empty");

        dLabelRight4.setText("empty");

        detailsList.setModel(entryListModel);
        detailsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                detailsListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(detailsList);

        detailsBackButton.setText("Back");
        detailsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout entryDetailsLayout = new javax.swing.GroupLayout(entryDetails);
        entryDetails.setLayout(entryDetailsLayout);
        entryDetailsLayout.setHorizontalGroup(
            entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryDetailsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(dItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(detailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addComponent(dLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dLabelRight2))
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addComponent(dLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(dLabelRight1))
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addComponent(dLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(dLabelRight3))
                    .addComponent(detailsLogoutButton)
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addComponent(dLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(dLabelRight4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(66, 66, 66))
        );
        entryDetailsLayout.setVerticalGroup(
            entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryDetailsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(dItemName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22))
                .addGap(35, 35, 35)
                .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryDetailsLayout.createSequentialGroup()
                        .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dLabel1)
                            .addComponent(dLabelRight1))
                        .addGap(35, 35, 35)
                        .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dLabel2)
                            .addComponent(dLabelRight2))
                        .addGap(35, 35, 35)
                        .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dLabel3)
                            .addComponent(dLabelRight3))
                        .addGap(35, 35, 35)
                        .addGroup(entryDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dLabel4)
                            .addComponent(dLabelRight4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(detailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5))
                .addGap(18, 18, 18)
                .addComponent(detailsLogoutButton)
                .addGap(20, 20, 20))
        );

        mainPane.addTab("Entry details", entryDetails);

        getContentPane().add(mainPane, "card6");

        getAccessibleContext().setAccessibleName("mainPanel");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card2");        
        mainPane.getRootPane().setDefaultButton(regContinueButton);
        regUsnField.requestFocus();
    }//GEN-LAST:event_regButtonActionPerformed

    private void regContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regContinueButtonActionPerformed
        // TODO add your handling code here:
        try {
            currUser = (MTUser) userManager.registerUser(regUsnField.getText(), regPassField.getPassword(), Integer.parseInt(regAgeField.getText()), Integer.parseInt(regBudgetField.getText()));
            JOptionPane.showMessageDialog(this, "Registration successful");
            clearJTextFields(registration);
            this.login();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Incorrect age or budget, please try again");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_regContinueButtonActionPerformed

    private void regBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regBackButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card1");
    }//GEN-LAST:event_regBackButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card3");
        mainPane.getRootPane().setDefaultButton(logContinueLogin);
        logUsnField.requestFocus();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void logBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logBackButtonActionPerformed
        // TODO add your handling code here:        
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card1");
    }//GEN-LAST:event_logBackButtonActionPerformed

    private void logContinueLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logContinueLoginActionPerformed
        try {
            currUser = (MTUser) userManager.performLogin(logUsnField.getText(), logPasswordField.getPassword());
            this.login();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_logContinueLoginActionPerformed

    private void logUsnFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logUsnFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logUsnFieldActionPerformed

    private void pNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pNameActionPerformed

    private void pPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pPriceActionPerformed

    private void pAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pAddButtonActionPerformed
        try {
            if (pLoanCheckBox.isSelected()) {
                loansAddButtonActionPerformed(evt);
            } else {
                currUser.addEntry(pIncome.isSelected(), pName.getText(), Integer.parseInt(pPrice.getText()));
            }
            updateStats();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Incorrect price, please try again");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Something went wrong, please try again");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e);
        }
        pList.setSelectedIndex(0);
// TODO add your handling code here:
    }//GEN-LAST:event_pAddButtonActionPerformed

    private void pExpenditureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pExpenditureActionPerformed
        // TODO add your handling code here:
        pLoanCheckBox.setSelected(false);
        pLoanCheckBox.setVisible(false);
    }//GEN-LAST:event_pExpenditureActionPerformed

    private void pLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pLogoutButtonActionPerformed
        logout();
    }//GEN-LAST:event_pLogoutButtonActionPerformed

    private void statsLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsLogoutButtonActionPerformed
        logout();
    }//GEN-LAST:event_statsLogoutButtonActionPerformed

    private void loansAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loansAddButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(loans, "Soon");
    }//GEN-LAST:event_loansAddButtonActionPerformed

    private void loansLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loansLogoutButtonActionPerformed
        logout();
    }//GEN-LAST:event_loansLogoutButtonActionPerformed

    private void detailsLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsLogoutButtonActionPerformed
        logout();
    }//GEN-LAST:event_detailsLogoutButtonActionPerformed

    private void detailsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBackButtonActionPerformed
        mainPane.setSelectedIndex(0);
    }//GEN-LAST:event_detailsBackButtonActionPerformed

    private void pLoanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pLoanCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pLoanCheckBoxActionPerformed

    private void pIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pIncomeActionPerformed
        // TODO add your handling code here:
        pLoanCheckBox.setVisible(true);
    }//GEN-LAST:event_pIncomeActionPerformed

    private void pListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pListValueChanged
        // TODO add your handling code here:
        if (currUser != null && !ignoreListChanges) {
            int index = pList.getSelectedIndex();
            updateStats(index);
        }
    }//GEN-LAST:event_pListValueChanged

    private void detailsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_detailsListValueChanged
        // TODO add your handling code here:
        if (currUser != null && !ignoreListChanges) {
            int index = detailsList.getSelectedIndex();
            updateStats(index);
        }
    }//GEN-LAST:event_detailsListValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dItemName;
    private javax.swing.JLabel dLabel1;
    private javax.swing.JLabel dLabel2;
    private javax.swing.JLabel dLabel3;
    private javax.swing.JLabel dLabel4;
    private javax.swing.JLabel dLabelRight1;
    private javax.swing.JLabel dLabelRight2;
    private javax.swing.JLabel dLabelRight3;
    private javax.swing.JLabel dLabelRight4;
    private javax.swing.JButton detailsBackButton;
    private javax.swing.JList detailsList;
    private javax.swing.JButton detailsLogoutButton;
    private javax.swing.JPanel entryDetails;
    private javax.swing.JPanel firstPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel loans;
    private javax.swing.JButton loansAddButton;
    private javax.swing.JList loansList;
    private javax.swing.JButton loansLogoutButton;
    private javax.swing.JLabel loansMonthlyPayment;
    private javax.swing.JLabel loansTotalRemaining;
    private javax.swing.JLabel loansUsn;
    private javax.swing.JLabel loansYearlyInterest;
    private javax.swing.JButton logBackButton;
    private javax.swing.JButton logContinueLogin;
    private javax.swing.JPasswordField logPasswordField;
    private javax.swing.JTextField logUsnField;
    private javax.swing.JPanel login;
    private javax.swing.JButton loginButton;
    private javax.swing.JTabbedPane mainPane;
    private javax.swing.JButton pAddButton;
    private javax.swing.JRadioButton pExpenditure;
    private javax.swing.JRadioButton pIncome;
    private javax.swing.JList pList;
    private javax.swing.JCheckBox pLoanCheckBox;
    private javax.swing.JButton pLogoutButton;
    private javax.swing.JTextField pName;
    private javax.swing.JTextField pPrice;
    private javax.swing.JPanel purchases;
    private javax.swing.JTextField regAgeField;
    private javax.swing.JButton regBackButton;
    private javax.swing.JTextField regBudgetField;
    private javax.swing.JButton regButton;
    private javax.swing.JButton regContinueButton;
    private javax.swing.JPasswordField regPassField;
    private javax.swing.JTextField regUsnField;
    private javax.swing.JPanel registration;
    private javax.swing.JPanel stats;
    private javax.swing.JList statsList;
    private javax.swing.JButton statsLogoutButton;
    private javax.swing.JLabel statsMostCom;
    private javax.swing.JLabel statsMostSpent;
    private javax.swing.JLabel statsUsn;
    // End of variables declaration//GEN-END:variables
}
