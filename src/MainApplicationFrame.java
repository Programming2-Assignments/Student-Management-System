import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainApplicationFrame extends JFrame {

    private StudentDatabase manager;
    private final String DATAFILE = "students.txt";

    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel(cardLayout);
    private JPanel navBarPanel;

    private JButton HOMEButton, ADDButton, UPDATEButton, DELETEButton, VIEWButton, LOGOUTButton;

    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JButton LOGINButton;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton ADDSTUDENTButton;
    private JButton CLEARButton;
    private JTable viewTable;
    private JButton REFRESHButton;
    private JTable deleteTable;
    private JButton DELETESELECTEDButton;
    private JTextField enterIDOrNameTextField;
    private JButton SEARCHButton;
    private JTable searchTable;
    private JTextField IDTextField, NAMETextField, DEPARTMENTTextField, GPATextField;
    private JButton UPDATEButton_SearchPanel;

    private final String[] COLUMN_NAMES = {"ID", "Full Name", "Age", "Gender", "Department", "GPA"};

    public MainApplicationFrame() {
        super("Student Management System (SMS)");

        manager = new StudentDatabase(DATAFILE);
        manager.readFromFile();
        initializeComponents();
        navBarPanel.setVisible(false);
        this.setLayout(new BorderLayout());
        this.add(navBarPanel, BorderLayout.WEST);
        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(createLoginPanel(), "Login");
        contentPanel.add(createDashboardPanel(), "Dashboard");
        contentPanel.add(createAddStudentPanel(), "Add");
        contentPanel.add(createViewStudentsPanel(), "View");
        contentPanel.add(createDeletePanel(), "Delete");
        contentPanel.add(createSearchUpdatePanel(), "SearchUpdate");

        attachNavigationListeners();
        attachLoginListener();
        attachAddStudentListeners();
        attachDeleteStudentListener();
        attachViewAndRefreshListeners();
        attachSearchAndUpdateListeners();

        Save();
        cardLayout.show(contentPanel, "Login");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void initializeComponents() {
        navBarPanel = new JPanel(new GridLayout(7, 1));
        navBarPanel.add(new JLabel("NAVIGATION BAR", SwingConstants.CENTER));
        HOMEButton = new JButton("HOME");
        ADDButton = new JButton("ADD");
        UPDATEButton = new JButton("UPDATE");
        DELETEButton = new JButton("DELETE");
        VIEWButton = new JButton("VIEW");
        LOGOUTButton = new JButton("LOGOUT");
        navBarPanel.add(HOMEButton);
        navBarPanel.add(ADDButton);
        navBarPanel.add(UPDATEButton);
        navBarPanel.add(DELETEButton);
        navBarPanel.add(VIEWButton);
        navBarPanel.add(LOGOUTButton);
        usernameTextField = new JTextField();
        passwordPasswordField = new JPasswordField();
        LOGINButton = new JButton("LOGIN");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        comboBox1 = new JComboBox<>(new String[]{"Male", "Female"});
        comboBox2 = new JComboBox<>(new String[]{"CCE", "MCE", "EEC"});
        ADDSTUDENTButton = new JButton("ADD STUDENT");
        CLEARButton = new JButton("CLEAR");

        viewTable = new JTable(new DefaultTableModel(COLUMN_NAMES, 0));
        deleteTable = new JTable(new DefaultTableModel(COLUMN_NAMES, 0));
        REFRESHButton = new JButton("REFRESH");
        DELETESELECTEDButton = new JButton("DELETE SELECTED");
        enterIDOrNameTextField = new JTextField("");
        SEARCHButton = new JButton("SEARCH");
        searchTable = new JTable(new DefaultTableModel(COLUMN_NAMES, 0));
        IDTextField = new JTextField("ID:");
        NAMETextField = new JTextField("NAME:");
        DEPARTMENTTextField = new JTextField("DEPARTMENT:");
        GPATextField = new JTextField("GPA:");
        UPDATEButton_SearchPanel = new JButton("UPDATE");
    }
    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(6, 1));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        loginPanel.add(new JLabel("LOGIN INFORMATION", SwingConstants.CENTER));
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameTextField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordPasswordField);
        loginPanel.add(LOGINButton);
        return loginPanel;
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        JLabel welcome = new JLabel("Welcome to the Student Management System", SwingConstants.CENTER);
        welcome.setFont(new Font("SansSerif", Font.BOLD, 18));

        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(welcome, BorderLayout.NORTH);
        fullPanel.add(dashboardPanel, BorderLayout.CENTER);

        dashboardPanel.add(ADDButton);
        dashboardPanel.add(VIEWButton);
        dashboardPanel.add(UPDATEButton);
        dashboardPanel.add(DELETEButton);

        return fullPanel;
    }

    private JPanel createAddStudentPanel() {
        JPanel addPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        addPanel.add(new JLabel("ADD STUDENT PANEL", SwingConstants.CENTER));
        addPanel.add(new JLabel(""));

        addPanel.add(new JLabel("FULL NAME:"));
        addPanel.add(textField1);
        addPanel.add(new JLabel("GPA:"));
        addPanel.add(textField2);
        addPanel.add(new JLabel("AGE:"));
        addPanel.add(textField3);
        addPanel.add(new JLabel("GENDER:"));
        addPanel.add(comboBox1);
        addPanel.add(new JLabel("DEPARTMENT:"));
        addPanel.add(comboBox2);

        addPanel.add(ADDSTUDENTButton);
        addPanel.add(CLEARButton);

        return addPanel;
    }

    private JPanel createViewStudentsPanel() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.add(new JScrollPane(viewTable), BorderLayout.CENTER);
        viewPanel.add(REFRESHButton, BorderLayout.SOUTH);
        return viewPanel;
    }

    private JPanel createDeletePanel() {
        JPanel deletePanel = new JPanel(new BorderLayout());
        deletePanel.add(DELETESELECTEDButton, BorderLayout.NORTH);
        deletePanel.add(new JScrollPane(deleteTable), BorderLayout.CENTER);
        return deletePanel;
    }

    private JPanel createSearchUpdatePanel() {
        JPanel searchPanel = new JPanel(new GridLayout(7, 3));

        searchPanel.add(new JLabel("SEARCH", SwingConstants.CENTER));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));

        searchPanel.add(enterIDOrNameTextField);
        searchPanel.add(SEARCHButton);
        searchPanel.add(new JLabel(""));

        searchPanel.add(new JScrollPane(searchTable));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));


        searchPanel.add(NAMETextField);
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));

        searchPanel.add(DEPARTMENTTextField);
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));

        searchPanel.add(GPATextField);
        searchPanel.add(UPDATEButton_SearchPanel);
        searchPanel.add(new JLabel(""));

        return searchPanel;
    }

    private void attachNavigationListeners() {
        ADDButton.addActionListener(e -> cardLayout.show(contentPanel, "Add"));
        VIEWButton.addActionListener(e -> {
            updateStudentTable(viewTable);
            cardLayout.show(contentPanel, "View");
        });
        DELETEButton.addActionListener(e -> {
            updateStudentTable(deleteTable);
            cardLayout.show(contentPanel, "Delete");
        });
        HOMEButton.addActionListener(e -> cardLayout.show(contentPanel, "Dashboard"));
        LOGOUTButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Login");
            navBarPanel.setVisible(false);
            JOptionPane.showMessageDialog(this, "Logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
        });
        UPDATEButton.addActionListener(e -> cardLayout.show(contentPanel, "SearchUpdate"));
    }

    private void attachLoginListener() {
        LOGINButton.addActionListener(e -> {
            String user = usernameTextField.getText();
            String pass = new String(passwordPasswordField.getPassword());
            if (user.equals("Admin") && pass.equals("0000")) {
                cardLayout.show(contentPanel, "Dashboard");
                navBarPanel.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void attachAddStudentListeners() {
        ADDSTUDENTButton.addActionListener(e -> {
            try {
                String name = textField1.getText().trim();
                String ageText = textField3.getText().trim();
                String gpaText = textField2.getText().trim();
                String gender = (String)comboBox1.getSelectedItem();
                String dept = (String)comboBox2.getSelectedItem();

                if (name.isEmpty() || ageText.isEmpty() || gpaText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age = Integer.parseInt(ageText);
                float gpa = Float.parseFloat(gpaText);
                if (gpa < 0.00f || gpa > 4.00f) {
                    JOptionPane.showMessageDialog(this, "GPA must be between 0.00 and 4.00.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (age < 15 || age > 40) {
                    JOptionPane.showMessageDialog(this, "Age must be between 15 and 40 years.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int newID = manager.getAllStudents().stream()
                        .mapToInt(Student::getStudentID)
                        .max().orElse(1000) + 1;

                Student newStudent = new Student(newID, name, age, gender, dept, gpa);
                manager.addStudent(newStudent);
                JOptionPane.showMessageDialog(this, "Student Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAddForm();
                updateStudentTable(viewTable);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and GPA must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        CLEARButton.addActionListener(e -> clearAddForm());
    }

    private void attachDeleteStudentListener() {
        DELETESELECTEDButton.addActionListener(e -> {
            int selectedRow = deleteTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) deleteTable.getModel();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student record to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int studentId = (int) model.getValueAt(selectedRow, 0);

            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete student ID: " + studentId + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                manager.deleteStudent(studentId);
                JOptionPane.showMessageDialog(this, "Student record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateStudentTable(deleteTable);
                updateStudentTable(viewTable);
            }
        });
    }

    private void attachViewAndRefreshListeners() {
        REFRESHButton.addActionListener(e -> updateStudentTable(viewTable));
    }
    private void attachSearchAndUpdateListeners() {
        SEARCHButton.addActionListener(e -> {
            String key = enterIDOrNameTextField.getText().trim();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an ID or Name to search.", "Search Query Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Student foundStudent = manager.searchStudent(key);

            if (foundStudent != null) {
                displaySingleStudentInTable(foundStudent, searchTable);

                IDTextField.setText(String.valueOf(foundStudent.getStudentID()));
                NAMETextField.setText(foundStudent.getFullName());
                DEPARTMENTTextField.setText(foundStudent.getDepartment());
                GPATextField.setText(String.valueOf(foundStudent.getGpa()));
            } else {
                displaySingleStudentInTable(null, searchTable);
                JOptionPane.showMessageDialog(this, "Student not found with key: " + key, "Not Found", JOptionPane.WARNING_MESSAGE);
                clearSearchData();
            }
            HOMEButton.addActionListener(x -> clearSearchData());

        });


        UPDATEButton_SearchPanel.addActionListener(e -> {
            try {
                int id = Integer.parseInt(IDTextField.getText().trim());
                String name = NAMETextField.getText().trim();
                String dept = DEPARTMENTTextField.getText().trim();
                float gpa = Float.parseFloat(GPATextField.getText().trim());

                Student original = manager.searchStudent(String.valueOf(id));

                if (original == null) {
                    JOptionPane.showMessageDialog(this, "Error: Student ID not found for update.", "Update Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student updatedStudent = new Student(original.getStudentID(), name, original.getAge(), original.getGender(), dept, gpa);
                manager.updateStudent(updatedStudent, original);

                JOptionPane.showMessageDialog(this, "Student record updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateStudentTable(viewTable);
                displaySingleStudentInTable(updatedStudent, searchTable);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID and GPA must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void updateStudentTable(JTable table) {
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);

        model.setRowCount(0);

        ArrayList<Student> students = manager.getAllStudents();

        for (Student s : students) {
            Object[] rowData = {
                    s.getStudentID(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()
            };
            model.addRow(rowData);
        }
    }

    private void displaySingleStudentInTable(Student s, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        if (s != null) {
            Object[] rowData = {
                    s.getStudentID(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()
            };
            model.addRow(rowData);
        }
    }

    private void clearAddForm() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
    }
    private void clearSearchData() {
        enterIDOrNameTextField.setText("");
        IDTextField.setText("ID:");
        NAMETextField.setText("NAME:");
        DEPARTMENTTextField.setText("DEPARTMENT:");
        GPATextField.setText("GPA:");
        DefaultTableModel model = (DefaultTableModel) searchTable.getModel();
        model.setRowCount(0);
    }

    private void Save() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                manager.saveToFile();
                System.out.println("Data saved");
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplicationFrame::new);
    }
}