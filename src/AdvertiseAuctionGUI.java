import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

public class AdvertiseAuctionGUI extends javax.swing.JDialog implements ActionListener{
    private javax.swing.JLabel closeTypeLabel;
    private javax.swing.JRadioButton closeTypeOneRadioButton;
    private javax.swing.JRadioButton closeTypeTwoRadioButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel startPriceLabel;
    private javax.swing.JTextField startPriceTextField;
    private javax.swing.JButton submitButton;
    
    public AdvertiseAuctionGUI(javax.swing.JFrame frame, String title)
    {
        super(frame, title, true);
        initComponents(frame);
    }

    private void initComponents(javax.swing.JFrame frame) {

        mainPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        startPriceLabel = new javax.swing.JLabel();
        startPriceTextField = new javax.swing.JTextField();
        closeTypeLabel = new javax.swing.JLabel();
        closeTypeOneRadioButton = new javax.swing.JRadioButton();
        closeTypeTwoRadioButton = new javax.swing.JRadioButton();
        submitButton = new javax.swing.JButton();

        nameLabel.setText("Name:");
        descriptionLabel.setText("Description:");
        startPriceLabel.setText("Starting Price:");
        closeTypeLabel.setText("Closing Type:");
        submitButton.setText("Submit");

        descriptionScrollPane.setViewportView(descriptionTextArea);

        ButtonGroup closeGroup = new ButtonGroup ();
        closeTypeOneRadioButton.setText("1");
        closeTypeTwoRadioButton.setText("2");
        closeGroup.add(closeTypeOneRadioButton);
        closeGroup.add(closeTypeTwoRadioButton);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
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
                    .addComponent(closeTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeTypeTwoRadioButton)
                    .addComponent(closeTypeOneRadioButton))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap())
        );
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
    }

}
