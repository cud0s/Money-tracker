/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import logic.entry.Entry;
import logic.entry.Expenditure;
import logic.entry.Income;
import logic.entry.Loan;
import logic.user.MTUser;
import logic.user.UserManager;
import logic.user.UserManagerFactory;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

    public static ExecutorService executor = Executors.newFixedThreadPool(4);

    public MainJFrame() {
        /**
         * 
         */
        
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

    private void login(){
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
        try{
            userManager.writeDataToDisk();
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        statsUsn.setText(currUser.getUsername());
        statsMostCom.setText(currUser.getMost(true));
        statsMostSpent.setText(currUser.getMost(false));
        statsNumberOfLoans.setText(Integer.toString(currUser.countLoans()));
        statsTotalFood.setText(Integer.toString(currUser.countFoodEntries()));

        jLabel12.setText(Integer.toString(currUser.getBudget()));

        listPurchases();
    }

    private void updateLoansStats(int index) {
        Loan selectedLoan = currUser.getLoan(index);

        loansMonthlyPayment.setText(Double.toString(selectedLoan.getMonthlyPay()));
        loansMonthlyInterest.setText(Double.toString(selectedLoan.getmInterest()));
        loansTotalRemaining.setText(Double.toString(selectedLoan.getTotalRemaining()));
        loansFinalDate.setText(selectedLoan.getReturnDate().toString());
        loansDateAdded.setText(selectedLoan.getInputDate().toString());
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

        dLabelRight5.setText("<html>" + details[8] + "</html>");
        dLabel5.setText(details[9]);
        //-------------------------------------------------------------
    }

    private void listPurchases() {
        ignoreListChanges = true;
        int totalUserEntr = currUser.getTotalEntries();
        int userCountDifference = totalUserEntr - entryListModel.size();

        String dataString; //will save string, containing "cost", "type" and "name" variables of an entry

        switch (userCountDifference) {
            case 1:
                dataString = currUser.getEntryDataString(totalUserEntr - 1);
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
                    dataString = currUser.getEntryDataString(a);
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
        addLoansPanel = new javax.swing.JPanel();
        addLoansInterest = new javax.swing.JTextField();
        addLoansDay = new javax.swing.JTextField();
        addLoansYear = new javax.swing.JTextField();
        addLoansMonth = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        addLoansPeriodRadio = new javax.swing.JRadioButton();
        addLoansDateRadio = new javax.swing.JRadioButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        statsNumberOfLoans = new javax.swing.JLabel();
        statsTotalFood = new javax.swing.JLabel();
        loans = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        loansLogoutButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        loansDateAdded = new javax.swing.JLabel();
        loansMonthlyInterest = new javax.swing.JLabel();
        loansMonthlyPayment = new javax.swing.JLabel();
        loansTotalRemaining = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loansList = new javax.swing.JList();
        loansAddButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        loansFinalDate = new javax.swing.JLabel();
        entryDetails = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        detailsLogoutButton = new javax.swing.JButton();
        dLabel5 = new javax.swing.JLabel();
        dLabelRight5 = new javax.swing.JLabel();
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

        jLabel16.setText("Monthly interest %:");

        jLabel23.setText("Year:");

        jLabel26.setText("Month:");

        jLabel27.setText("Day:");

        buttonGroup2.add(addLoansPeriodRadio);
        addLoansPeriodRadio.setText("Period of time to return");
        addLoansPeriodRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLoansPeriodRadioActionPerformed(evt);
            }
        });

        buttonGroup2.add(addLoansDateRadio);
        addLoansDateRadio.setText("Date of return");
        addLoansDateRadio.setSelected(true);
        addLoansDateRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLoansDateRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addLoansPanelLayout = new javax.swing.GroupLayout(addLoansPanel);
        addLoansPanel.setLayout(addLoansPanelLayout);
        addLoansPanelLayout.setHorizontalGroup(
            addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLoansPanelLayout.createSequentialGroup()
                .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLoansPanelLayout.createSequentialGroup()
                        .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLoansPanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(addLoansYear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addLoansPanelLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(addLoansDay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addLoansPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(addLoansMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)))
                .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addLoansPeriodRadio)
                    .addComponent(addLoansDateRadio)
                    .addGroup(addLoansPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(addLoansInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        addLoansPanelLayout.setVerticalGroup(
            addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLoansPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addLoansPanelLayout.createSequentialGroup()
                        .addComponent(addLoansPeriodRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addLoansDateRadio))
                    .addGroup(addLoansPanelLayout.createSequentialGroup()
                        .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addLoansYear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addLoansInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addGroup(addLoansPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel23)))
                        .addGap(18, 18, 18)
                        .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLoansPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel26))
                            .addComponent(addLoansMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addLoansPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addLoansPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel27))
                            .addComponent(addLoansDay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(104, Short.MAX_VALUE))
        );

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
                .addContainerGap(245, Short.MAX_VALUE)
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
                .addContainerGap(120, Short.MAX_VALUE))
        );

        regPassField.getAccessibleContext().setAccessibleName("");

        getContentPane().add(registration, "card2");

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

        pPrice.setNextFocusableComponent(pAddButton);

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

        jLabel12.setText("0");

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

        statsUsn.setText(" ");

        statsMostCom.setText(" ");

        statsMostSpent.setText(" ");

        statsList.setModel(entryListModel);
        jScrollPane4.setViewportView(statsList);

        jLabel28.setText("Number of loans:");

        jLabel29.setText("Total food entries");

        statsNumberOfLoans.setText(" ");

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
                    .addComponent(statsLogoutButton)
                    .addGroup(statsLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(statsNumberOfLoans))
                    .addGroup(statsLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(statsTotalFood)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
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
                        .addGap(0, 47, Short.MAX_VALUE))
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
                        .addGap(45, 45, 45)
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(statsNumberOfLoans))
                        .addGap(45, 45, 45)
                        .addGroup(statsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(statsTotalFood))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel20.setText("Date added:");

        jLabel21.setText("Monthly interest:");

        loansDateAdded.setText(" ");

        loansMonthlyInterest.setText(" ");

        loansMonthlyPayment.setText(" ");

        loansTotalRemaining.setText(" ");

        loansList.setModel(loansListModel);
        loansList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                loansListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(loansList);

        loansAddButton.setText("Add");
        loansAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loansAddButtonActionPerformed(evt);
            }
        });

        jLabel24.setText("Final payment day:");

        loansFinalDate.setText(" ");

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
                        .addComponent(loansDateAdded))
                    .addComponent(loansAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(loansMonthlyPayment))
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(loansMonthlyInterest))
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(loansTotalRemaining))
                    .addComponent(loansLogoutButton)
                    .addGroup(loansLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(loansFinalDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
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
                            .addComponent(loansDateAdded))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(loansMonthlyInterest))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(loansMonthlyPayment))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(loansTotalRemaining))
                        .addGap(35, 35, 35)
                        .addGroup(loansLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(loansFinalDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
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

        dLabel5.setText("Item name:");

        dLabelRight5.setText("No item selected");
        dLabelRight5.setMaximumSize(new java.awt.Dimension(350, 40));
        dLabelRight5.setName(""); // NOI18N

        dLabel1.setText(" ");

        dLabel2.setText(" ");

        dLabel3.setText(" ");

        dLabel4.setText(" ");

        dLabelRight1.setText(" ");

        dLabelRight2.setText(" ");

        dLabelRight3.setText(" ");

        dLabelRight4.setText(" ");

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
                        .addComponent(dLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(dLabelRight5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
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
                .addGap(61, 61, 61)
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
                    .addComponent(dLabel5)
                    .addComponent(dLabelRight5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card2");
        mainPane.getRootPane().setDefaultButton(regContinueButton);
        regUsnField.requestFocus();
    }//GEN-LAST:event_regButtonActionPerformed

    private void regContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regContinueButtonActionPerformed
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
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card1");
    }//GEN-LAST:event_regBackButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        CardLayout card = (CardLayout) getContentPane().getLayout();
        card.show(getContentPane(), "card3");
        mainPane.getRootPane().setDefaultButton(logContinueLogin);
        logUsnField.requestFocus();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void logBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logBackButtonActionPerformed
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

    private void pAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pAddButtonActionPerformed
        try {
            if (pLoanCheckBox.isSelected()) {
                loansAddButtonActionPerformed(evt);
            } else {
                int price = Integer.parseInt(pPrice.getText());
                String name = pName.getText();
                boolean isIncome = pIncome.isSelected();
                currUser.addEntry(isIncome, name, price);
            }
            updateStats();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Incorrect input data, please try again");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Something went wrong, please try again");
        } catch (java.time.DateTimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        pList.setSelectedIndex(0);
    }//GEN-LAST:event_pAddButtonActionPerformed

    private void pExpenditureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pExpenditureActionPerformed
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
        if (addLoansDateRadio.isSelected()) {
            addLoansYear.setText(Integer.toString(LocalDate.now().getYear()));
            addLoansMonth.setText(Integer.toString(LocalDate.now().getMonthValue()));
            addLoansDay.setText(Integer.toString(LocalDate.now().getDayOfMonth()));
        }

        addLoansInterest.setText("0");
        Object[] options = {"Add", "Cancel"};
        int i = JOptionPane.showOptionDialog(null, addLoansPanel, "Add loan",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
                null, options, options[0]);
        if (i == 0) {
            String text;
            if(addLoansYear.getText().equals("")){
               text = addLoansDateRadio.isSelected() ? "" : "0";
               addLoansYear.setText(text);
            }
            if(addLoansMonth.getText().equals("")){
               text = addLoansDateRadio.isSelected() ? "" : "0";
               addLoansMonth.setText(text);
            }
            if(addLoansDay.getText().equals("")){
               text = addLoansDateRadio.isSelected() ? "" : "0";
               addLoansDay.setText(text);
            }
            
            int year = Integer.parseInt(addLoansYear.getText());
            int month = Integer.parseInt(addLoansMonth.getText());
            int day = Integer.parseInt(addLoansDay.getText());

            double interest = Double.parseDouble(addLoansInterest.getText());
            int price = Integer.parseInt(pPrice.getText());
            String name = pName.getText();

            currUser.addEntry(name, price, interest, addLoansDateRadio.isSelected(), year, month, day);
            this.clearJTextFields(addLoansPanel);
            addLoansDateRadio.setSelected(true);
            loansList.setSelectedIndex(0);
        }
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

    private void pIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pIncomeActionPerformed
        pLoanCheckBox.setVisible(true);
    }//GEN-LAST:event_pIncomeActionPerformed

    private void pListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pListValueChanged
        if (currUser != null && !ignoreListChanges) {
            int index = pList.getSelectedIndex();
            updateStats(index);
        }
    }//GEN-LAST:event_pListValueChanged

    private void detailsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_detailsListValueChanged
        if (currUser != null && !ignoreListChanges) {
            int index = detailsList.getSelectedIndex();
            updateStats(index);
        }
    }//GEN-LAST:event_detailsListValueChanged

    private void addLoansDateRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLoansDateRadioActionPerformed
        if (addLoansDateRadio.isSelected()) {
            if (addLoansYear.getText().equals("0")) {
                if (addLoansMonth.getText().equals("0")) {
                    if (addLoansDay.getText().equals("0")) {
                        addLoansYear.setText(Integer.toString(LocalDate.now().getYear()));
                        addLoansMonth.setText(Integer.toString(LocalDate.now().getMonthValue()));
                        addLoansDay.setText(Integer.toString(LocalDate.now().getDayOfMonth()));
                    }
                }
            }
        }
    }//GEN-LAST:event_addLoansDateRadioActionPerformed

    private void addLoansPeriodRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLoansPeriodRadioActionPerformed
        if (addLoansPeriodRadio.isSelected()) {
            if (addLoansYear.getText().equals(Integer.toString(LocalDate.now().getYear()))) {
                if (addLoansMonth.getText().equals(Integer.toString(LocalDate.now().getMonthValue()))) {
                    if (addLoansDay.getText().equals(Integer.toString(LocalDate.now().getDayOfMonth()))) {
                        addLoansYear.setText("0");
                        addLoansMonth.setText("0");
                        addLoansDay.setText("0");
                    }
                }
            }
        }
    }//GEN-LAST:event_addLoansPeriodRadioActionPerformed

    private void loansListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_loansListValueChanged
        if (currUser != null && !ignoreListChanges) {
            int index = loansList.getSelectedIndex();
            updateLoansStats(index);
        }
    }//GEN-LAST:event_loansListValueChanged

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
    private javax.swing.JRadioButton addLoansDateRadio;
    private javax.swing.JTextField addLoansDay;
    private javax.swing.JTextField addLoansInterest;
    private javax.swing.JTextField addLoansMonth;
    private javax.swing.JPanel addLoansPanel;
    private javax.swing.JRadioButton addLoansPeriodRadio;
    private javax.swing.JTextField addLoansYear;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel dLabel1;
    private javax.swing.JLabel dLabel2;
    private javax.swing.JLabel dLabel3;
    private javax.swing.JLabel dLabel4;
    private javax.swing.JLabel dLabel5;
    private javax.swing.JLabel dLabelRight1;
    private javax.swing.JLabel dLabelRight2;
    private javax.swing.JLabel dLabelRight3;
    private javax.swing.JLabel dLabelRight4;
    private javax.swing.JLabel dLabelRight5;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel loansDateAdded;
    private javax.swing.JLabel loansFinalDate;
    private javax.swing.JList loansList;
    private javax.swing.JButton loansLogoutButton;
    private javax.swing.JLabel loansMonthlyInterest;
    private javax.swing.JLabel loansMonthlyPayment;
    private javax.swing.JLabel loansTotalRemaining;
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
    private javax.swing.JLabel statsNumberOfLoans;
    private javax.swing.JLabel statsTotalFood;
    private javax.swing.JLabel statsUsn;
    // End of variables declaration//GEN-END:variables
}
