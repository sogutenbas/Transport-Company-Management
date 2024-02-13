package view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * GUI and assosciated methods for creating the FileManager GUI and operations.
 * @author eliasronningen
 */
public class FileManagerFrame extends JFrame {
	/**
	 * Frame and assosciated content is intialized.
	 */

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    final String[] selectedFolder = {System.getProperty("user.home")};

    public FileManagerFrame() {
        setResizable(false);
        setTitle("File Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        /**
         * Button for selecting folder for managing saved files.
         */
        JButton btnSelectFolder = new JButton("Select Folder");
        btnSelectFolder.setBounds(316, 189, 117, 29);
        contentPane.add(btnSelectFolder);

        /**
         * Button for saving specific tables directtly from Database.
         */
        JButton btnSaveFromDatabase = new JButton("Save From Database");
        btnSaveFromDatabase.setBounds(6, 189, 200, 29);
        contentPane.add(btnSaveFromDatabase);
        
        /**
         * Button for accessing stored files in a read only envorinment.
         */
        JButton btnReadFiles = new JButton("Read Files");
        btnReadFiles.setBounds(316, 148, 117, 29);
        contentPane.add(btnReadFiles);

        /**
         * Label displaying your current storage folder, standard is users' root folder.
         */
        JLabel lblCurrentFolder = new JLabel("Current Folder: " + selectedFolder[0]);
        lblCurrentFolder.setBounds(6, 250, 479, 16);
        contentPane.add(lblCurrentFolder);
        
        /**
         * Button for executing direct SQL queries to the database.
         */
        JButton btnSQLCommand = new JButton("SQL UPDATES");
        btnSQLCommand.setForeground(Color.RED);
        btnSQLCommand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/**
            	 * Upon pressing the button, user is prompted to enter a SQL statement.
            	 */
                String sqlCommand = JOptionPane.showInputDialog(FileManagerFrame.this, "Enter SQL Command:");

                /**
                 * Checks for empty field.
                 */
                if (sqlCommand != null && !sqlCommand.trim().isEmpty()) {
                    try {
                        DatabaseHelper dbHelper = new DatabaseHelper();
                        
                        // Use the executeSQLCommand method with Consumer<ResultSet>
                        dbHelper.executeSQLCommand(sqlCommand, resultSet -> {
                            // Display the result set using the displayResultSet method
                            dbHelper.displayResultSet(resultSet);
                        });
                        
                        /**
                         * OptionPane for different possible scenarios depending on the statements validation in the executeSQLCommand method.
                         */
                        JOptionPane.showMessageDialog(FileManagerFrame.this, "SQL Command Executed Successfully", "Info", JOptionPane.INFORMATION_MESSAGE) ;
                        
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(FileManagerFrame.this, "Error executing SQL command", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FileManagerFrame.this, "Invalid SQL Command", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
       btnSQLCommand.setBounds(6, 122, 200, 29);
        contentPane.add(btnSQLCommand);

        JLabel lblNewLabel = new JLabel("File Manager and Operations");
        lblNewLabel.setFont(new Font("Serif", Font.BOLD, 24));
        lblNewLabel.setBounds(55, 6, 312, 40);
        contentPane.add(lblNewLabel);

        /**
         * Action listener executes upon pressing the SaveFromDatabase button.
         */
        btnSaveFromDatabase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/**
            	 * If the user hasn't yet changed from default folder, warning will be issued.
            	 */
                if (selectedFolder[0].equals(System.getProperty("user.home"))) {
                    JOptionPane.showMessageDialog(FileManagerFrame.this, "Please select a folder first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DatabaseHelper dbHelper = new DatabaseHelper();

                try {
                    List<String> tableNames = dbHelper.getTableNames();
                    		/**
                    		 * If tables aren't found, issue following error:
                    		 */
                    if (tableNames.isEmpty()) {
                        JOptionPane.showMessageDialog(FileManagerFrame.this, "No tables found in the database", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                    	/**
                    	 * Found tables are added to a pop-up menu for use in selecting what table store.
                    	 */
                        JPopupMenu popupMenu = new JPopupMenu();
                        for (String tableName : tableNames) {
                            JMenuItem menuItem = new JMenuItem(tableName);
                            menuItem.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                	/**
                                	 * Selected menu item will be the parameter for saveFromDatabase method
                                	 */
                                    String selectedTable = ((JMenuItem) e.getSource()).getText();
                                    saveFromDatabase(selectedTable);
                                }
                            });
                            popupMenu.add(menuItem);
                        }

                        popupMenu.show(btnSaveFromDatabase, 0, btnSaveFromDatabase.getHeight());
                    }
                } catch (SQLException ex) {
                	/**
                	 * For any errors that occur while attempting to fetch data from db.
                	 */
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(FileManagerFrame.this, "Error fetching table names from the database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        /**
         * Actionlistener for Select Folder button.
         */
        btnSelectFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/**
            	 * fileChooser opens fileSystemView, allowing user to navigate and pick out / make their desired folder.
            	 */
                JFileChooser fileChooser = new JFileChooser(selectedFolder[0], FileSystemView.getFileSystemView()) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected JDialog createDialog(Component parent) throws HeadlessException {
                        JDialog dialog = super.createDialog(parent);

                        if (dialog.getContentPane().getComponentCount() > 1) {
                            dialog.getContentPane().remove(1);
                        }

                        return dialog;
                    }
                };
                /**
                 * Allows for pick of folders and directories only.
                 */
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.isDirectory();
                    }

                    @Override
                    public String getDescription() {
                        return "Directories only";
                    }
                });

                int result = fileChooser.showSaveDialog(FileManagerFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFolder[0] = fileChooser.getSelectedFile().getPath();
                    lblCurrentFolder.setText("Current Folder: " + selectedFolder[0]);
                }
            }
        });

        btnReadFiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFolder[0].equals(System.getProperty("user.home"))) {
                    JOptionPane.showMessageDialog(FileManagerFrame.this, "Please select a folder first", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JFileChooser fileChooser = new JFileChooser(selectedFolder[0], FileSystemView.getFileSystemView());
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(FileManagerFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    openFileInReadOnlyMode(selectedFile);
                }
            }
        });
    }
    
    /** 
     * 
     * @param tableName will be used as the name for the saved file from database.
     */

    private void saveFromDatabase(String tableName) {
        String selectedFolderPath = selectedFolder[0];
        String filePath = selectedFolderPath + File.separator + tableName + ".txt";

        File existingFile = new File(filePath);
        if (existingFile.exists()) {
        	/**
        	 * If file already exists, user will be asked to confirm overwriting the original file.
        	 */
            int overwriteConfirmation = JOptionPane.showConfirmDialog(
                    FileManagerFrame.this,
                    "The file already exists. Do you want to overwrite it?",
                    "Overwrite Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (overwriteConfirmation != JOptionPane.YES_OPTION) {
                return;
            }
        }

        DatabaseHelper dbHelper = new DatabaseHelper();
        /**
         * File saved confirmation message and location.
         */

        try {
            dbHelper.saveTableToFile(tableName, filePath);
            JOptionPane.showMessageDialog(FileManagerFrame.this, "Table saved to file: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
        	/**
        	 * Error if file saving operation fails.
        	 */
            e.printStackTrace();
            JOptionPane.showMessageDialog(FileManagerFrame.this, "Error saving table to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Read only frame for use in reading stored files. Users may not edit any of the stored info.
     * @param file saved in the user picked folder.
     */
    private void openFileInReadOnlyMode(File file) {
        JFrame readOnlyFrame = new JFrame("Read-only Mode: " + file.getAbsolutePath());
        readOnlyFrame.setSize(1000, 600);

        // Use DefaultTableModel to populate the JTable
        DefaultTableModel tableModel = new DefaultTableModel();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("\t"); // Assuming tab-separated values; adjust as needed

                if (isFirstLine) {
                    // Add column names to the table model
                    for (String columnName : rowData) {
                        tableModel.addColumn(columnName);
                    }
                    isFirstLine = false;
                } else {
                    // Add rows to the table model
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(FileManagerFrame.this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create JTable with the DefaultTableModel
        JTable table = new JTable(tableModel);

        // Add the table to a JScrollPane for scrolling if necessary
        JScrollPane scrollPane = new JScrollPane(table);
        readOnlyFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        readOnlyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        readOnlyFrame.setVisible(true);
    }
    

    
}

