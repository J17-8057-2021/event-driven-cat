import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class RegistrationForm extends JFrame implements ActionListener {
    JLabel title, idLabel, nameLabel, genderLabel, addressLabel, contactLabel;
    JTextField idField, nameField, addressField, contactField;
    JButton registerButton, exitButton;
    JRadioButton male, female;
    ButtonGroup bg;
    List<User> list = new ArrayList<>();
    JTable table;
    DefaultTableModel model;
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new RegistrationForm());
}


    public RegistrationForm() {
        setTitle("Registration Form");
        setSize(700, 360);
        setLayout(new BorderLayout());

       
        createComponents();

    
        addComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void createComponents() {
        title = new JLabel("Registration Form");

        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        genderLabel = new JLabel("Gender");
        addressLabel = new JLabel("Address");
        contactLabel = new JLabel("Contact");

        idField = new JTextField();
        nameField = new JTextField();
        addressField = new JTextField();
        contactField = new JTextField();

        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

       
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Gender");
        model.addColumn("Address");
        model.addColumn("Contact");

      
        table = new JTable(model);
    }

    private void addComponents() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(genderLabel);
        formPanel.add(createGenderPanel());
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(contactLabel);
        formPanel.add(contactField);
        formPanel.add(new JLabel()); 
        formPanel.add(createButtonPanel());

        add(title, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(male);
        genderPanel.add(female);
        return genderPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerButton.addActionListener(this);
        exitButton.addActionListener(this);
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exitButton) {
            System.exit(0);
        } else if (ae.getSource() == registerButton) {
            registerUser();
        }
    }

   
  private void registerUser() {
    if (fieldsAreNotEmpty()) {
        String gender = male.isSelected() ? "Male" : "Female";
        list.add(new User(idField.getText(), nameField.getText(), gender, addressField.getText(), contactField.getText()));
        addRows(); 
        clearFields();
        JOptionPane.showMessageDialog(this, "Successfully Registered");
    } else {
        JOptionPane.showMessageDialog(this, "Fields will not be blank");
    }
}

private void addRows() {
    model.addRow(new Object[]{
        idField.getText(),
        nameField.getText(),
        male.isSelected() ? "Male" : "Female",
        addressField.getText(),
        contactField.getText()
    });
}

private void clearFields() {
    idField.setText("");
    nameField.setText("");
    bg.clearSelection();
    addressField.setText("");
    contactField.setText("");
}

    private boolean fieldsAreNotEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

   
    }



