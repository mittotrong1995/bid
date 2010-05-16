package client;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClientGUI extends javax.swing.JFrame implements ActionListener,Runnable{

    private TCPClient client;
    private String token;
    private javax.swing.JMenuItem advertiseMenuItem;
    private javax.swing.JButton bidButton;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton historyButton;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPopupMenu.Separator menuSeparator1;
    private javax.swing.JPopupMenu.Separator menuSeparator2;
    private javax.swing.JMenuItem messageMenuItem;
    private javax.swing.JButton participantsButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JMenu systemMenu;
    private javax.swing.JTable table;
    private javax.swing.table.DefaultTableModel tableModel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JButton withdrawButton;
    private boolean connected;
  
    public ClientGUI(){
        token = "#@";
        connected = false;
        initComponents();
                this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showCancelDialog();
            }
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }
    private void initComponents() {

        registerButton = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tableModel = new javax.swing.table.DefaultTableModel();
        historyButton = new javax.swing.JButton();
        participantsButton = new javax.swing.JButton();
        textAreaScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        bidButton = new javax.swing.JButton();
        withdrawButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        systemMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        menuSeparator1 = new javax.swing.JPopupMenu.Separator();
        advertiseMenuItem = new javax.swing.JMenuItem();
        messageMenuItem = new javax.swing.JMenuItem();
        menuSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();


        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Name", "Start Price", "Seller IP", "Registered"
            }
            ) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        table.setToolTipText("List of active auctions");
        table.setColumnSelectionAllowed(true);
        tableScrollPane.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setMinWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setMaxWidth(80);

        tableModel = (DefaultTableModel) table.getModel();

        textArea.setFont(new java.awt.Font("Lucida Sans", 0, 12)); 
        textArea.setToolTipText("Description");
        textArea.setEnabled(false);
        textAreaScrollPane.setViewportView(textArea);

        historyButton.setText("History");
        historyButton.setActionCommand("history");
        historyButton.addActionListener(this);

        registerButton.setText("Register");
        registerButton.setActionCommand("register");
        registerButton.addActionListener(this);

        participantsButton.setText("Participants");
        participantsButton.setActionCommand("participants");
        participantsButton.addActionListener(this);

        bidButton.setText("Place Bid...");
        bidButton.setActionCommand("bid");
        bidButton.addActionListener(this);

        withdrawButton.setText("Withdraw");
        withdrawButton.setActionCommand("withdraw");
        withdrawButton.addActionListener(this);

        connectMenuItem.setText("Connect...");
        connectMenuItem.setActionCommand("connect");
        connectMenuItem.addActionListener(this);

        advertiseMenuItem.setText("Advertise an item...");
        advertiseMenuItem.setActionCommand("advertise");
        advertiseMenuItem.addActionListener(this);

        messageMenuItem.setText("Send message...");
        messageMenuItem.setActionCommand("message");
        messageMenuItem.addActionListener(this);

        exitMenuItem.setText("Exit");
        exitMenuItem.setActionCommand("exit");
        exitMenuItem.addActionListener(this);

        systemMenu.setText("Auction System");
        systemMenu.add(connectMenuItem);
        systemMenu.add(menuSeparator1);
        systemMenu.add(advertiseMenuItem);
        systemMenu.add(messageMenuItem);
        systemMenu.add(menuSeparator2);
        systemMenu.add(exitMenuItem);
        menuBar.add(systemMenu);
        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bidButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(participantsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(withdrawButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bidButton)
                        .addGap(18, 18, 18)
                        .addComponent(historyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(participantsButton))
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle("Auction System - Client");
        this.setMinimumSize(new Dimension(850,600));
        this.setPreferredSize(new Dimension(850, 600));
        this.setLocationRelativeTo(null);
        
        this.pack();
        this.setVisible(true);
    }// </editor-fold>

    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("register")){
            if (connected == true)
                registerAction();

            if (connected == false )
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
        }
        else if (c.equals("withdraw")){
            if (connected == true)
                withdrawAction();

            if (connected == false )
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
        }
        else if (c.equals("bid")){
            if (connected == true)
                bidAction();
            if (connected == false )
             JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
        }
        else if (c.equals("history")){
            if (connected == true)
                historyAction();
            
            if (connected == false )
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
        }
        else if (c.equals("participants")){
            if (connected == false )
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
            
            if (connected == true)
                participantsAction();
        }
        else if (c.equals("connect")){
            connectAction();
            
        }
        else if (c.equals("advertise")){
            if (connected == false)
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
            if (connected == true)
                advertiseAction();
        }
        else if (c.equals("message")){
            if (connected == true)
                messageAction();
            if (connected == false )
                JOptionPane.showMessageDialog(null,"Please connect to the server first","Error Message",2);
        }
        else if (c.equals("exit")){
            exitAction();
        }
        else if (c.equals("disconnectMenu")){
            try {
                client.closeConnection();
                connected = false;
                connectMenuItem.setText("Connect...");
                connectMenuItem.setActionCommand("connect");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void registerAction() {

        if(table.getSelectedRow() != -1){
            String register = "2"+ token + (table.getModel().getValueAt(table.getSelectedRow(),0)).toString();
            (client.getPrintWriter()).println(register);}
        else
          JOptionPane.showMessageDialog(null,"Please select an auction first","Error Message",2);
    }

    private void withdrawAction() {
        if(table.getSelectedRow() != -1){
            String withdraw = "6"+ token + (table.getModel().getValueAt(table.getSelectedRow(),0)).toString();
            (client.getPrintWriter()).println(withdraw);}
        else
          JOptionPane.showMessageDialog(null,"Please select an auction first","Error Message",2);
    }

    private void bidAction() {
        if(table.getSelectedRow() != -1){
        String prize = JOptionPane.showInputDialog(this, "Enter a value for the bid: ", "Place a bid", JOptionPane.QUESTION_MESSAGE);
        String bid = "3" + token +(table.getModel().getValueAt(table.getSelectedRow(),0)).toString() + token + prize;
        (client.getPrintWriter()).println(bid);
        }
        else
          JOptionPane.showMessageDialog(null,"Please select an auction first","Error Message",2);
    }

    private void historyAction() {
        if(table.getSelectedRow() != -1){
            String history = "5"+ token + (table.getModel().getValueAt(table.getSelectedRow(),0)).toString();
            (client.getPrintWriter()).println(history);}
        else
          JOptionPane.showMessageDialog(null,"Please select an auction first","Error Message",2);
    }

    private void participantsAction() {
        if(table.getSelectedRow() != -1){
            String participants = "7"+ token + (table.getModel().getValueAt(table.getSelectedRow(),0)).toString();
            (client.getPrintWriter()).println(participants);
                }
        else
          JOptionPane.showMessageDialog(null,"Please select an auction first","Error Message",2);
    }

    private void connectAction() {
        try {
            showConnectDialog();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void advertiseAction() {
        showAdvertiseDialog();
    }

    private void messageAction() {
        showMessageDialog();
    }

    private void exitAction() {
        showCancelDialog();

    }
    private void showCancelDialog() {
        final JDialog dialog = new JDialog(this, "Exit", true);
        final JOptionPane op = new JOptionPane("Close Window", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(op);
        dialog.setResizable(false);
        op.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent e){
                String prop = e.getPropertyName();
                if (dialog.isVisible() && (e.getSource() == op) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                    dialog.setVisible(false);
                }
            }
        });
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
        int value = ((Integer) op.getValue()).intValue();
        if (value == JOptionPane.NO_OPTION){
            dialog.dispose();
        }
        if (value == JOptionPane.YES_OPTION){
            this.dispose();
            System.exit(0);
        }
    }

    private void showMessageDialog() {
        javax.swing.JLabel messageLabel = new javax.swing.JLabel();
        final javax.swing.JTextArea messageTextArea = new javax.swing.JTextArea();
        javax.swing.JPanel messagesMainPanel = new javax.swing.JPanel();
        javax.swing.JScrollPane msgScrollPane = new javax.swing.JScrollPane();
        javax.swing.JLabel recepientLabel = new javax.swing.JLabel();
        javax.swing.JButton sendButton = new javax.swing.JButton();
        final javax.swing.JTextField iPTextField1 = new javax.swing.JTextField();
        final javax.swing.JTextField iPTextField2 = new javax.swing.JTextField();
        final javax.swing.JTextField iPTextField3 = new javax.swing.JTextField();
        final javax.swing.JTextField iPTextField4 = new javax.swing.JTextField();
        final JDialog messageDialog = new JDialog(this, "Send Message", true);

        iPTextField1.setDocument(new MaxLengthTextDocument(3));
        iPTextField2.setDocument(new MaxLengthTextDocument(3));
        iPTextField3.setDocument(new MaxLengthTextDocument(3));
        iPTextField4.setDocument(new MaxLengthTextDocument(3));

        recepientLabel.setText("Send to IP:");
        messageLabel.setText("Message content:");

        sendButton.setText("Send"); 
        sendButton.setActionCommand("sendMessage");
        sendButton.addActionListener(this);

        javax.swing.GroupLayout messagesMainPanelLayout = new javax.swing.GroupLayout(messagesMainPanel);
        messagesMainPanel.setLayout(messagesMainPanelLayout);
        messagesMainPanelLayout.setHorizontalGroup(
            messagesMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagesMainPanelLayout.createSequentialGroup()
                .addGroup(messagesMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(messagesMainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(recepientLabel))
                    .addGroup(messagesMainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iPTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iPTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iPTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iPTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(messagesMainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(messageLabel))
                    .addGroup(messagesMainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msgScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, messagesMainPanelLayout.createSequentialGroup()
                        .addComponent(sendButton))))
        );
        messagesMainPanelLayout.setVerticalGroup(
            messagesMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messagesMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recepientLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(messagesMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iPTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iPTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iPTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iPTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(sendButton)
                .addContainerGap())
        );

        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer.parseInt(iPTextField1.getText());
                    Integer.parseInt(iPTextField2.getText());
                    Integer.parseInt(iPTextField3.getText());
                    Integer.parseInt(iPTextField4.getText());
                    String message = "8"+ token + iPTextField1.getText() + "." + iPTextField2.getText() + "." + iPTextField3.getText() + "." + iPTextField4.getText() + token+ messageTextArea.getText();
                        client.getPrintWriter().println(message);
                        messageDialog.dispose();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Please enter digits in the IP fields!", "Error Message!",3);
                }
            }
        });
        msgScrollPane.setViewportView(messageTextArea);
        messageDialog.add(messagesMainPanel);
        messageDialog.pack();
        messageDialog.setLocationRelativeTo(this);
        messageDialog.setResizable(false);
        messageDialog.setVisible(true);
    }

    public void showConnectDialog() throws ParseException{
        final javax.swing.JTextField IPTextField1 = new javax.swing.JTextField();
        final javax.swing.JTextField IPTextField2 = new javax.swing.JTextField();
        final javax.swing.JTextField IPTextField3 = new javax.swing.JTextField();
        final javax.swing.JTextField IPTextField4 = new javax.swing.JTextField();
        final javax.swing.JTextField portTextField = new javax.swing.JTextField();
        javax.swing.JButton connectButton = new javax.swing.JButton();
        javax.swing.JLabel portLabel = new javax.swing.JLabel();
        javax.swing.JLabel serverLabel = new javax.swing.JLabel();
        javax.swing.JPanel connectPanel = new javax.swing.JPanel();
        final JDialog connectDialog = new JDialog(this, "Connect to server", true);

        final ActionListener connectAL;
        final ActionListener disconnectAL;

        IPTextField1.setDocument(new MaxLengthTextDocument(3));
        IPTextField2.setDocument(new MaxLengthTextDocument(3));
        IPTextField3.setDocument(new MaxLengthTextDocument(3));
        IPTextField4.setDocument(new MaxLengthTextDocument(3));
        portTextField.setDocument(new MaxLengthTextDocument(5));

        serverLabel.setText("Server IP:");
        portLabel.setText("Port:");
        connectButton.setText("Connect");

        connectAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!IPTextField1.getText().trim().equals("") && !IPTextField2.getText().trim().equals("") && !IPTextField3.getText().trim().equals("") && !IPTextField4.getText().trim().equals("") && !portTextField.getText().trim().equals("")) {
                    try {
                        client = new TCPClient(IPTextField1.getText() + "." + IPTextField2.getText() + "." + IPTextField3.getText() + "." + IPTextField4.getText(), Integer.parseInt(portTextField.getText()));
                        connectDialog.dispose();
                        connectMenuItem.setText("Disconnect");
                        connectMenuItem.setActionCommand("disconnectMenu");
                        action();
                        connected = true;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(connectDialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(connectDialog, "Please enter a correct IP address.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        connectButton.addActionListener(connectAL);
        javax.swing.GroupLayout connectPanelLayout = new javax.swing.GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(connectPanelLayout.createSequentialGroup()
                        .addComponent(serverLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IPTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(portLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                    .addComponent(connectButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        connectPanelLayout.setVerticalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLabel)
                    .addComponent(IPTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IPTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IPTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IPTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portLabel)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(connectButton))
        );

        connectDialog.add(connectPanel);
        connectDialog.pack();
        connectDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        connectDialog.setLocationRelativeTo(this);
        connectDialog.setResizable(false);
        connectDialog.setVisible(true);
    }// </editor-fold>


    private void showAdvertiseDialog(){
        javax.swing.JLabel closeTypeLabel = new javax.swing.JLabel();
        final javax.swing.JRadioButton closeTypeOneRadioButton = new javax.swing.JRadioButton();
        final javax.swing.JRadioButton closeTypeTwoRadioButton = new javax.swing.JRadioButton();
        javax.swing.JLabel descriptionLabel = new javax.swing.JLabel();
        final javax.swing.JTextArea descriptionTextArea = new javax.swing.JTextArea();
        javax.swing.JScrollPane descriptionScrollPane = new javax.swing.JScrollPane();
        javax.swing.JPanel advPanel = new javax.swing.JPanel();
        javax.swing.JLabel nameLabel = new javax.swing.JLabel();
        final javax.swing.JTextField nameTextField = new javax.swing.JTextField();
        javax.swing.JLabel startPriceLabel = new javax.swing.JLabel();
        final javax.swing.JTextField startPriceTextField = new javax.swing.JTextField();
        javax.swing.JLabel quantityLabel = new javax.swing.JLabel();
        final javax.swing.JTextField quantityTextField = new javax.swing.JTextField();
        javax.swing.JButton submitButton = new javax.swing.JButton();
        final javax.swing.JDialog advertiseDialog = new javax.swing.JDialog(this, "Advertise an item", true);

        nameLabel.setText("Name:");
        descriptionLabel.setText("Description:");
        startPriceLabel.setText("Starting Price:");
        closeTypeLabel.setText("Closing Type:");
        submitButton.setText("Submit");
        quantityLabel.setText("Quantity: ");

        final javax.swing.ButtonGroup closeGroup = new javax.swing.ButtonGroup();
        closeTypeOneRadioButton.setText("1");
        closeTypeTwoRadioButton.setText("2");
        closeGroup.add(closeTypeOneRadioButton);
        closeGroup.add(closeTypeTwoRadioButton);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(advPanel);
        advPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(submitButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTextField)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(startPriceTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startPriceLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(quantityTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantityLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(closeTypeLabel)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(closeTypeOneRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(closeTypeTwoRadioButton))))
                            .addComponent(nameLabel)
                            .addComponent(descriptionLabel)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGap(5, 5, 5)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPriceLabel)
                    .addComponent(quantityLabel)
                    .addComponent(closeTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeTypeTwoRadioButton)
                    .addComponent(closeTypeOneRadioButton))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap())
        );

        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String typeSelection = "";
                if (closeTypeOneRadioButton.isSelected())
                    typeSelection = closeTypeOneRadioButton.getText();
                else if (closeTypeTwoRadioButton.isSelected())
                    typeSelection = closeTypeTwoRadioButton.getText();
                try
                {
                    Integer.parseInt(startPriceTextField.getText());
                    try{
                    Integer.parseInt(quantityTextField.getText());
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,"Please enter digits in the quantity field!", "Error Message!",3);
                    }
                    if(typeSelection.equals(""))
                        JOptionPane.showMessageDialog(null,"Please select the closing type!", "Error Message!",3);
                    else if(nameTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please enter the item's name!", "Error Message!",3);
                    else if(descriptionTextArea.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please enter the item's description!", "Error Message!",3);
                    else{
                        String auction = "0"+ token + typeSelection + token + startPriceTextField.getText() + token + quantityTextField.getText() + token+ nameTextField.getText() + token + descriptionTextArea.getText() + token + client.getClient().getIp();
                        client.getPrintWriter().println(auction);
                        advertiseDialog.dispose();                      
                    }
                }
                catch(Exception exc)
                {
                    JOptionPane.showMessageDialog(null,"Please enter digits in the price field!", "Error Message!",3);
                }              
            }
        });

        descriptionScrollPane.setViewportView(descriptionTextArea);
        advertiseDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        advertiseDialog.setResizable(false);
        advertiseDialog.add(advPanel);
        advertiseDialog.pack();
        advertiseDialog.setLocationRelativeTo(this);
        advertiseDialog.setVisible(true);
    }

    public void action()
    {
        
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            String responseLine;
            while ((responseLine = (client.getIn()).readLine()) != null) {
                String [] parts = responseLine.split(token);
                if (responseLine.indexOf("connected") > -1) {
                    JOptionPane.showMessageDialog(null, responseLine, "Connection Accepted", 1);
                }

                else if(responseLine.charAt(0) == '9')
                {
                    System.out.println("ssss" + responseLine);
                    int info = 1;

                    for(int j = 0; j < (parts.length/5); j++){
                        tableModel.insertRow(j, new Object [] {parts[info],parts[info+1],parts[info+2],parts[info+3],parts[info+4]});
                        info = info + 5;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class MaxLengthTextDocument extends javax.swing.text.PlainDocument {
    private int maxChars;

    public MaxLengthTextDocument(int max){
        this.maxChars = max;
    }

    @Override
    public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                    throws javax.swing.text.BadLocationException {
        try{
            if(str == null || (getLength() + str.length() > maxChars)){
                    str = str.substring(0, maxChars-1);
            }
            super.insertString(offs, str, a);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"You cannot enter more than 3 digits in the IP address fields!", "Error Message!",3);
        }
    }
}
