/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author Bosko
 */

public class AdvertiseAuctionGUI extends javax.swing.JFrame implements ActionListener{
    private javax.swing.JLabel auctionIDLabel;
    private javax.swing.JLabel auctionIDshowLabel;
    private javax.swing.JLabel auctionInfoLabel;
    private javax.swing.JLabel closeTypeLabel;
    private javax.swing.JRadioButton closeTypeOneRadioButton;
    private javax.swing.JRadioButton closeTypeTwoRadioButton2;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel startPriceLabel;
    private javax.swing.JTextField startPriceTextField;
    private javax.swing.JButton submitButton;
    
    public AdvertiseAuctionGUI()
    {
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showCancelDialog();
            }
        });

    }

    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        startPriceLabel = new javax.swing.JLabel();
        startPriceTextField = new javax.swing.JTextField();
        closeTypeLabel = new javax.swing.JLabel();
        closeTypeOneRadioButton = new javax.swing.JRadioButton();
        closeTypeTwoRadioButton2 = new javax.swing.JRadioButton();
        submitButton = new javax.swing.JButton();
        auctionInfoLabel = new javax.swing.JLabel();
        auctionIDLabel = new javax.swing.JLabel();
        auctionIDshowLabel = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        //org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(desktopapplication1.DesktopApplication1.class).getContext().getResourceMap(DesktopApplication1View.class);
        //nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
        nameLabel.setText("Name:");
        nameLabel.setName("nameLabel"); // NOI18N

        //nameTextField.setText(resourceMap.getString("nameTextField.text")); // NOI18N
        
        nameTextField.setName("nameTextField"); // NOI18N

        //descriptionLabel.setText(resourceMap.getString("descriptionLabel.text")); // NOI18N
        descriptionLabel.setText("Description:");
        descriptionLabel.setName("descriptionLabel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setName("descriptionTextArea"); // NOI18N
        jScrollPane1.setViewportView(descriptionTextArea);

        //startPriceLabel.setText(resourceMap.getString("startPriceLabel.text")); // NOI18N
        startPriceLabel.setText("Starting Price:");
        startPriceLabel.setName("startPriceLabel"); // NOI18N

        //startPriceTextField.setText(resourceMap.getString("startPriceTextField.text")); // NOI18N
        startPriceTextField.setName("startPriceTextField"); // NOI18N

        //closeTypeLabel.setText(resourceMap.getString("closeTypeLabel.text")); // NOI18N
        closeTypeLabel.setText("Closing Type:");
        closeTypeLabel.setName("closeTypeLabel"); // NOI18N

        //closeTypeOneRadioButton.setText(resourceMap.getString("closeTypeOneRadioButton.text")); // NOI18N
        ButtonGroup closeGroup = new ButtonGroup ();
        closeTypeOneRadioButton.setText("1");
        closeTypeOneRadioButton.setName("closeTypeOneRadioButton"); // NOI18N

        //closeTypeTwoRadioButton2.setText(resourceMap.getString("closeTypeTwoRadioButton2.text")); // NOI18N
        closeTypeTwoRadioButton2.setText("2");
        closeTypeTwoRadioButton2.setName("closeTypeTwoRadioButton2"); // NOI18N
        closeGroup.add(closeTypeOneRadioButton);
        closeGroup.add(closeTypeTwoRadioButton2);
        //submitButton.setText(resourceMap.getString("submitButton.text")); // NOI18N
        submitButton.setText("Submit");
        submitButton.setName("submitButton"); // NOI18N

        //auctionInfoLabel.setText(resourceMap.getString("auctionInfoLabel.text")); // NOI18N
        auctionInfoLabel.setText("Auction Specifications");
        auctionInfoLabel.setName("auctionInfoLabel"); // NOI18N

        //auctionIDLabel.setText(resourceMap.getString("auctionIDLabel.text")); // NOI18N
        auctionIDLabel.setText("ID");
        auctionIDLabel.setName("auctionIDLabel"); // NOI18N

        //auctionIDshowLabel.setText(resourceMap.getString("auctionIDshowLabel.text")); // NOI18N
        
        auctionIDshowLabel.setName("auctionIDshowLabel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(auctionIDshowLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(auctionIDLabel)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(auctionInfoLabel))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(submitButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTextField)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(startPriceTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startPriceLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(closeTypeLabel)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(closeTypeOneRadioButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(closeTypeTwoRadioButton2))))
                            .addComponent(nameLabel)
                            .addComponent(descriptionLabel)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(auctionInfoLabel)
                .addGap(5, 5, 5)
                .addComponent(auctionIDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(auctionIDshowLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPriceLabel)
                    .addComponent(closeTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeTypeTwoRadioButton2)
                    .addComponent(closeTypeOneRadioButton))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap())
        );
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle("Advertise an auction");
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

        
    }


    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            new ClientGUI();
        }
    }
}
