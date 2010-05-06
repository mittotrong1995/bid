
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ClientGUI extends javax.swing.JFrame implements ActionListener{

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
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textAreaScrollPane;
    private javax.swing.JButton withdrawButton;

    public ClientGUI(){
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

        textArea.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
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

        connectMenuItem.setText("Connect");
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
            registerAction();
        }
        else if (c.equals("withdraw")){
            withdrawAction();
        }
        else if (c.equals("bid")){
            bidAction();
        }
        else if (c.equals("history")){
            historyAction();
        }
        else if (c.equals("participants")){
            participantsAction();
        }
        else if (c.equals("connect")){
            connectAction();
        }
        else if (c.equals("advertise")){
            advertiseAction();
        }
        else if (c.equals("message")){
            messageAction();
        }
        else if (c.equals("exit")){
            exitAction();
        }
    }

    private void registerAction() {

    }

    private void withdrawAction() {

    }

    private void bidAction() {

    }

    private void historyAction() {

    }

    private void participantsAction() {

    }

    private void connectAction() {

    }

    private void advertiseAction() {
        new AdvertiseAuctionGUI(this, "Advertise an item");
    }

    private void messageAction() {

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
        }
    }
}
