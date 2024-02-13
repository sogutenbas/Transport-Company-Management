package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseHelper;
import model.Products;

public class ViewProductsFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private DatabaseHelper databaseHelper = new DatabaseHelper();

    /**
     * Frame for the listing of all products in the database.
     */
    public ViewProductsFrame() {
        // Set frame properties
        setTitle("Products");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);

        // Panel using BorderLayout function
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        // Label for page title.
        JLabel lblProduct = new JLabel("Products");
        lblProduct.setFont(new Font("SansSerif", Font.PLAIN, 24));
        lblProduct.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel for title, placed in the middle of the screen, before the table
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.add(lblProduct, BorderLayout.CENTER);

        panel.add(pnlHeader, BorderLayout.NORTH);

        // Table columns
        String[] columnTitles = {
                "Product Code", "Product Name", "Product Line", "Product Scale",
                "Product Vendor", "Product Description", "Quantity In Stock",
                "Buy Price", "MSRP"
        };

        // Fetches data through the database helper
        List<Products> products;
        try {
            products = databaseHelper.getProducts();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error in fetching products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create data array for the table
        Object[][] data = new Object[products.size()][columnTitles.length];

        // Populate the data array using enhanced for loop
        int rowIndex = 0;
        for (Products product : products) {
            data[rowIndex][0] = product.getProductCode();
            data[rowIndex][1] = product.getProductName();
            data[rowIndex][2] = product.getProductLine();
            data[rowIndex][3] = product.getProductScale();
            data[rowIndex][4] = product.getProductVendor();
            data[rowIndex][5] = product.getProductDescription();
            data[rowIndex][6] = product.getQuantityInStock();
            data[rowIndex][7] = product.getBuyPrice();
            data[rowIndex][8] = product.getMSRP();

            rowIndex++;
        }

        // Create the DefaultTableModel with data and column titles
        DefaultTableModel model = new DefaultTableModel(data, columnTitles) {
            private static final long serialVersionUID = 1L;

			@Override   // Makes sure the cells of the tables aren't tamperable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create the JTable using the DefaultTableModel
        JTable productTable = new JTable(model);

        // Automatic resizing
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Make the list scrollable
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    }
}
