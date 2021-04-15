/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import static classes.Home.income;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author wayomi
 */
public class Outgoing extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Double ppaidMore = 0.00;
     public static Double expaidMore = 0.00;
    public static Double shouldpay = 0.00;
    public static Double exshouldpay = 0.00;
    public static Double value1 = 0.00;
    public static Double value2 = 0.00;
    public static Double value = 0.00;
       
    public Outgoing() {
        initComponents();
        this.setLocationRelativeTo(this);
        jTable1.setAutoCreateRowSorter(true);
        jTable2.setAutoCreateRowSorter(true);
        con = DBConnect.connect();
        update_table_expense();
        update_table_purchase();       
        Fill_Combo_Payment();
        Fill_Combo();    
       
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        pack();
        jLabel_paidmore.setText("");
        jLabel_paidmore1.setText("");
        jLabel_balance.setText("");
        jLabel_balance1.setText("");
        
    }
    
    public void Fill_Combo_Payment()
    {
        try
        {
            String sql = "Select Name from payment ORDER BY Name ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                String name = rs.getString("Name");
                jComboBox_name1.addItem(name);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void Fill_Combo()
    {
        try
        {
            String sql = "Select Name from supplier ORDER BY Name ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                String name = rs.getString("Name");
                jComboBox_name.addItem(name);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void update_table_expense()
    {
        /* SELECT *
FROM purchase
WHERE MONTH(date) = MONTH(CURDATE() - INTERVAL 1 MONTH)*/
            
        try {
            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice Number', Name,amount AS 'Amount (£)',vat AS 'VAT (£)',Total AS 'Total (£)',"
                    + "Method,payment AS 'Payment', paidDate AS '1st Installment',(Total - insAmount) As '1st Payment', insDate AS '2nd Installment', "
                    + "insAmount AS '2nd Payment',discount AS 'Discount', advance AS 'Continuation'  from expences"
                    + " ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            JTableHeader head = jTable1.getTableHeader();
            head.setForeground(Color.blue);            
            jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.setShowGrid(true);              
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null, e);
        }
    }
    public void update_table_purchase()
    {
        
        try {
            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice No' , Name,amount AS 'Amount (£)', vat AS 'VAT (£)',Total AS 'Total (£)',"
                    + " Method,payment AS 'Payment', paidDate AS '1st Installment',(Total - insAmount) As '1st Payment', insDate AS '2nd Installment', "
                    + "insAmount AS '2nd Payment', discount AS 'Discount', advance AS 'Continuation' from purchase"
                    + "";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            JTableHeader head = jTable2.getTableHeader();
            head.setForeground(Color.blue);            
            jTable2.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            jTable2.setShowGrid(true);          
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null, e);
        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        heading = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        slide = new javax.swing.JPanel();
        jLabel_dashboard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_vendor1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        invoiceDate = new javax.swing.JLabel();
        InvoiceNo = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        vat = new javax.swing.JLabel();
        jTextField_Number = new javax.swing.JTextField();
        jComboBox_name = new javax.swing.JComboBox<>();
        jTextField_Amount = new javax.swing.JTextField();
        jTextField_VAT = new javax.swing.JTextField();
        jTextField_total = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_cheque_no = new javax.swing.JLabel();
        jComboBox_method = new javax.swing.JComboBox<>();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton8 = new javax.swing.JButton();
        jXDatePicker5 = new org.jdesktop.swingx.JXDatePicker();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField_paid = new javax.swing.JTextField();
        Discount = new javax.swing.JRadioButton();
        Pay_later = new javax.swing.JRadioButton();
        jLabel_balance = new javax.swing.JLabel();
        jLabel_date = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        credit = new javax.swing.JRadioButton();
        jLabel_paidmore = new javax.swing.JLabel();
        jLabel_shouldpay = new javax.swing.JLabel();
        testlabek = new javax.swing.JLabel();
        jTextField_search = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        invoiceDate1 = new javax.swing.JLabel();
        jXDatePicker3 = new org.jdesktop.swingx.JXDatePicker();
        InvoiceNo1 = new javax.swing.JLabel();
        jTextField_Number1 = new javax.swing.JTextField();
        name1 = new javax.swing.JLabel();
        jComboBox_name1 = new javax.swing.JComboBox<>();
        jTextField_Amount1 = new javax.swing.JTextField();
        jTextField_VAT1 = new javax.swing.JTextField();
        jTextField_total1 = new javax.swing.JTextField();
        vat1 = new javax.swing.JLabel();
        amount1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jXDatePicker4 = new org.jdesktop.swingx.JXDatePicker();
        jComboBox_method1 = new javax.swing.JComboBox<>();
        jLabel_ex_cheque_no = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField_paid1 = new javax.swing.JTextField();
        jLabel_balance1 = new javax.swing.JLabel();
        Discount1 = new javax.swing.JRadioButton();
        Pay_later1 = new javax.swing.JRadioButton();
        jLabel_date1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        credit1 = new javax.swing.JRadioButton();
        jLabel_shouldpay1 = new javax.swing.JLabel();
        jLabel_paidmore1 = new javax.swing.JLabel();
        jTextField_search1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1172, 745));
        setPreferredSize(new java.awt.Dimension(1172, 745));
        setResizable(false);

        heading.setBackground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IOM - Income~Outgoing Manager - Outgoing");

        javax.swing.GroupLayout headingLayout = new javax.swing.GroupLayout(heading);
        heading.setLayout(headingLayout);
        headingLayout.setHorizontalGroup(
            headingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headingLayout.setVerticalGroup(
            headingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        slide.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_dashboard.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_dashboard.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_dashboard.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_dashboard.setText("Dashboard");
        jLabel_dashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dashboardMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Income");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Outgoing");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reports");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Staff");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("New Vendor");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel_vendor1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_vendor1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_vendor1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_vendor1.setText("Credit");
        jLabel_vendor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_vendor1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_vendor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slideLayout.createSequentialGroup()
                .addComponent(jLabel_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_vendor1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(92, 109, 114));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        invoiceDate.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        invoiceDate.setText("DATE");
        jPanel3.add(invoiceDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 119, 29));

        InvoiceNo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        InvoiceNo.setText("INVOICE NO");
        jPanel3.add(InvoiceNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 128, -1));

        name.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        name.setText("NAME");
        jPanel3.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 128, -1));

        amount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        amount.setText("AMOUNT");
        jPanel3.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 83, -1));

        vat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        vat.setText("VAT");
        jPanel3.add(vat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 67, -1));
        jPanel3.add(jTextField_Number, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 40, 180, -1));

        jComboBox_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT NAME" }));
        jComboBox_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_nameActionPerformed(evt);
            }
        });
        jComboBox_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_nameKeyReleased(evt);
            }
        });
        jPanel3.add(jComboBox_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 71, 180, -1));
        jPanel3.add(jTextField_Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 102, 180, -1));

        jTextField_VAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_VATKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_VATKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_VAT, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 140, 180, -1));
        jPanel3.add(jTextField_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 184, 180, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("DATE (PAYMENT)");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 8, 121, 22));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("PAYMENT TYPE");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 49, 115, 25));
        jPanel3.add(jLabel_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 140, 20));

        jComboBox_method.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT METHOD", "CASH", "BANK", "CHEQUE", "NOT PAID", "OTHER" }));
        jComboBox_method.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_methodActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox_method, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 52, 170, -1));
        jPanel3.add(jXDatePicker2, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 9, 170, -1));

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-20.png"))); // NOI18N
        jButton8.setText("SAVE");
        jButton8.setToolTipText("Add a new record to the table");
        jButton8.setIconTextGap(16);
        jButton8.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 130, 30));
        jPanel3.add(jXDatePicker5, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 9, 180, -1));

        jButton11.setBackground(new java.awt.Color(204, 204, 204));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-edit-20.png"))); // NOI18N
        jButton11.setText("EDIT");
        jButton11.setToolTipText("Update a record which is already exsists");
        jButton11.setIconTextGap(16);
        jButton11.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 130, 30));

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-20.png"))); // NOI18N
        jButton12.setText("DELETE");
        jButton12.setToolTipText("Delete a record from the table");
        jButton12.setIconTextGap(16);
        jButton12.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 130, 30));

        jButton15.setBackground(new java.awt.Color(204, 204, 204));
        jButton15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-reset-20.png"))); // NOI18N
        jButton15.setText("CLEAR");
        jButton15.setToolTipText("Clear Fields");
        jButton15.setIconTextGap(16);
        jButton15.setPreferredSize(new java.awt.Dimension(80, 30));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 130, 30));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("PAYMENT");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 92, -1));

        jTextField_paid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_paidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_paidKeyTyped(evt);
            }
        });
        jPanel3.add(jTextField_paid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 85, -1));

        buttonGroup1.add(Discount);
        Discount.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Discount.setText("Discount");
        Discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiscountActionPerformed(evt);
            }
        });
        jPanel3.add(Discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, -1));

        buttonGroup1.add(Pay_later);
        Pay_later.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Pay_later.setText("Pay Later");
        Pay_later.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pay_laterActionPerformed(evt);
            }
        });
        jPanel3.add(Pay_later, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 94, -1));
        jPanel3.add(jLabel_balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 73, 20));
        jPanel3.add(jLabel_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 81, 23));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("TOTAL");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        buttonGroup1.add(credit);
        credit.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        credit.setText("Exception");
        jPanel3.add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 100, -1));

        jLabel_paidmore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel3.add(jLabel_paidmore, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 160, 20));

        jLabel_shouldpay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel3.add(jLabel_shouldpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 184, 210, 20));
        jPanel3.add(testlabek, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 220, 20));

        jTextField_search.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_search.setText("Search..");
        jTextField_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_searchMouseClicked(evt);
            }
        });
        jTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchActionPerformed(evt);
            }
        });
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });
        jPanel3.add(jTextField_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 130, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Invoice Number", " Name", "Amout (£)", "VAT (£)", "Date (Payment)", "Method"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Purchase", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        invoiceDate1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        invoiceDate1.setText("DATE");
        jPanel4.add(invoiceDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 119, 29));
        jPanel4.add(jXDatePicker3, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 15, 180, -1));

        InvoiceNo1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        InvoiceNo1.setText("INVOICE NO");
        jPanel4.add(InvoiceNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 128, -1));
        jPanel4.add(jTextField_Number1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 51, 180, -1));

        name1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        name1.setText("NAME");
        jPanel4.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 128, -1));

        jComboBox_name1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT NAME", "SALARY" }));
        jComboBox_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_name1ActionPerformed(evt);
            }
        });
        jComboBox_name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_name1KeyReleased(evt);
            }
        });
        jPanel4.add(jComboBox_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 89, 180, -1));
        jPanel4.add(jTextField_Amount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 180, -1));

        jTextField_VAT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_VAT1KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField_VAT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 169, 180, -1));
        jPanel4.add(jTextField_total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 214, 180, -1));

        vat1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        vat1.setText("VAT");
        jPanel4.add(vat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 67, -1));

        amount1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        amount1.setText("AMOUNT");
        jPanel4.add(amount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 132, 83, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("DATE (PAYMENT)");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));
        jPanel4.add(jXDatePicker4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 170, -1));

        jComboBox_method1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT METHOD", "CASH", "BANK", "CHEQUE", "NOT PAID", "OTHER " }));
        jComboBox_method1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_method1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox_method1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 170, -1));
        jPanel4.add(jLabel_ex_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 130, 20));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("PAYMENT TYPE");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 120, -1));

        jButton10.setBackground(new java.awt.Color(204, 204, 204));
        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-save-20.png"))); // NOI18N
        jButton10.setText("SAVE");
        jButton10.setIconTextGap(16);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, 130, -1));

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-edit-20.png"))); // NOI18N
        jButton13.setText("EDIT");
        jButton13.setIconTextGap(16);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 130, -1));

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-delete-20.png"))); // NOI18N
        jButton14.setText("DELETE");
        jButton14.setIconTextGap(16);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 130, 30));

        jButton16.setBackground(new java.awt.Color(204, 204, 204));
        jButton16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-reset-20.png"))); // NOI18N
        jButton16.setText("CLEAR");
        jButton16.setIconTextGap(16);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 130, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("PAYMENT");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 247, 92, 20));

        jTextField_paid1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_paid1KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField_paid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 248, 90, -1));
        jPanel4.add(jLabel_balance1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 73, 20));

        buttonGroup2.add(Discount1);
        Discount1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Discount1.setText("Discount");
        Discount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Discount1ActionPerformed(evt);
            }
        });
        jPanel4.add(Discount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, -1, -1));

        buttonGroup2.add(Pay_later1);
        Pay_later1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Pay_later1.setText("Pay Later");
        Pay_later1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pay_later1ActionPerformed(evt);
            }
        });
        jPanel4.add(Pay_later1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));
        jPanel4.add(jLabel_date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 81, 23));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("TOTAL");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        buttonGroup2.add(credit1);
        credit1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        credit1.setText("Exception");
        jPanel4.add(credit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 100, -1));

        jLabel_shouldpay1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel4.add(jLabel_shouldpay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 210, 20));

        jLabel_paidmore1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel4.add(jLabel_paidmore1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 160, 20));

        jTextField_search1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_search1.setText("Search..");
        jTextField_search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_search1MouseClicked(evt);
            }
        });
        jTextField_search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_search1ActionPerformed(evt);
            }
        });
        jTextField_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_search1KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField_search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 140, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Invoice Number", " Name", "Amout (£)", "VAT (£)", "Date (Payment)", "Method"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Expense", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
                .addGap(4, 4, 4))
            .addComponent(heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dashboardMouseClicked
        Home h = new Home();
        h.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_dashboardMouseClicked

    private void jComboBox_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_nameActionPerformed
       try {            
            String sql = "select SUM(advance) AS 'more' FROM purchase WHERE Name = ?";
            pst = con.prepareStatement(sql);
            String name = jComboBox_name.getSelectedItem().toString();
            pst.setString(1, name);            
            ResultSet rs = pst.executeQuery();
            
             if(rs.next())
            {
                String tt = rs.getString("more");
                ppaidMore = Double.parseDouble(tt);  
                String purpaidMore = String.format("%.2f", ppaidMore);
                jLabel_paidmore.setText("Paid £ "+purpaidMore + " More");
                
            }  
             else
             {
                ppaidMore = 0.00;
             }
        } 
        catch (Exception e) {
             //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox_nameActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        value1 = Double.parseDouble((jLabel_balance.getText())); 
        try {            
            String sql ="UPDATE purchase SET advance =0.0 WHERE Name =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            String name2 = jComboBox_name.getSelectedItem().toString();
            pst.setString(1, name2);
            pst.executeUpdate();            
            update_table_purchase();            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
        if (Discount.isSelected()) {
            
            try {

                String sql = "insert into purchase (date, InvoiceNo, Name, Amount, vat, Total, paidDate, Method,"
                        + " cheque_No,discount, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number.getText());
                String name2;
                name2 = jComboBox_name.getSelectedItem().toString();
                pst.setString(3, name2);
                pst.setString(4, jTextField_Amount.getText());
                pst.setString(5, jTextField_VAT.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total.getText());
                pst.setString(9,jLabel_cheque_no.getText());
                pst.setString(10, jLabel_balance.getText());
                pst.setString(11,jTextField_paid.getText());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number.setText("");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker2.setDate(null);
                jXDatePicker5.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);               
                credit.setSelected(false);
                buttonGroup1.clearSelection();
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                
                update_table_purchase();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if(Pay_later.isSelected())
        {
            
            try {

                String sql = "insert into purchase (date, InvoiceNo, Name, Amount, vat, Total, "
                        + "paidDate, Method, cheque_No,insAmount, insDate, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number.getText());
                String name2;
                name2 = jComboBox_name.getSelectedItem().toString();
                pst.setString(3, name2);
                pst.setString(4, jTextField_Amount.getText());
                pst.setString(5, jTextField_VAT.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total.getText());
                pst.setString(9,jLabel_cheque_no.getText());
                pst.setString(10, jLabel_balance.getText());
                pst.setString(11,jLabel_date.getText());
                pst.setString(12,jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number.setText("");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker2.setDate(null);
                jXDatePicker5.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                buttonGroup1.clearSelection();
                
                update_table_purchase();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if (credit.isSelected()) {
                       
            try {

                String sql = "insert into purchase (date, InvoiceNo, Name, Amount, vat, Total, paidDate, Method,"
                        + " cheque_No,exception, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number.getText());
                String name2;
                name2 = jComboBox_name.getSelectedItem().toString();
                pst.setString(3, name2);
                pst.setString(4, jTextField_Amount.getText());
                pst.setString(5, jTextField_VAT.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total.getText());
                pst.setString(9,jLabel_cheque_no.getText());
                pst.setString(10, jLabel_balance.getText());
                pst.setString(11,jTextField_paid.getText());
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number.setText("");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker2.setDate(null);
                jXDatePicker5.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                credit.setSelected(false);
                buttonGroup1.clearSelection();
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                
                update_table_purchase();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
         
        if (value1 < 0) {
            
            try {

                String sql = "insert into purchase (date, InvoiceNo, Name, Amount, vat, Total, paidDate, Method,"
                        + " cheque_No,advance, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number.getText());
                String name2;
                name2 = jComboBox_name.getSelectedItem().toString();
                pst.setString(3, name2);
                pst.setString(4, jTextField_Amount.getText());
                pst.setString(5, jTextField_VAT.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total.getText());
                pst.setString(9,jLabel_cheque_no.getText());
                Double adv = Double.parseDouble(jLabel_balance.getText()); 
                Double pos = adv*(-1);
                pst.setString(10,pos.toString());
                pst.setString(11,jTextField_paid.getText());
                
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number.setText("");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker2.setDate(null);
                jXDatePicker5.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jLabel_balance.setText("");
                buttonGroup1.clearSelection();
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                
                update_table_purchase();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else
        {           
            try {

                String sql = "insert into purchase (date, InvoiceNo, Name, Amount, vat, Total, paidDate,"
                        + " Method, cheque_No, payment) values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number.getText());
                String name2;
                name2 = jComboBox_name.getSelectedItem().toString();
                pst.setString(3, name2);
                pst.setString(4, jTextField_Amount.getText());
                pst.setString(5, jTextField_VAT.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total.getText());
                pst.setString(9,jLabel_cheque_no.getText());
                pst.setString(10,jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number.setText("");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker2.setDate(null);
                jXDatePicker5.setDate(null);
                jTextField_total.setText("");
                buttonGroup1.clearSelection();
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                
                update_table_purchase();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        value1 = Double.parseDouble(jLabel_balance.getText());
      
        if(Discount.isSelected())
        {
            try {
                int row = jTable2.getSelectedRow();
                String id = jTable2.getModel().getValueAt(row, 1).toString();
                String sql = "UPDATE purchase SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, discount= ?, payment =?  WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount.getText());
                pst.setString(4, jTextField_VAT.getText());
                pst.setString(5, jTextField_total.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(11, jTextField_Number.getText());
                pst.setString(9, jLabel_balance.getText()); 
                pst.setString(10, jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");

                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker5.setDate(null);
                jXDatePicker2.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                buttonGroup1.clearSelection();
                jLabel_shouldpay.setText("");
                
                update_table_purchase();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
        else if(Pay_later.isSelected())
        {
            try {
                int row = jTable2.getSelectedRow();
                String id = jTable2.getModel().getValueAt(row, 1).toString();
                String sql = "UPDATE purchase SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, insAmount= ?, insDate=?, payment =?  WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount.getText());
                pst.setString(4, jTextField_VAT.getText());
                pst.setString(5, jTextField_total.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(7, method); 
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(12, jTextField_Number.getText());
                pst.setString(9, jLabel_balance.getText());
                pst.setString(10,jLabel_date.getText());
                pst.setString(11, jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");

                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker5.setDate(null);
                jXDatePicker2.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                buttonGroup1.clearSelection();
                update_table_purchase();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        else if(credit.isSelected())
        {
            try {
                int row = jTable2.getSelectedRow();
                String id = jTable2.getModel().getValueAt(row, 0).toString();
                String sql = "UPDATE purchase SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, exception= ?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount.getText());
                pst.setString(4, jTextField_VAT.getText());
                pst.setString(5, jTextField_total.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(11, jTextField_Number.getText());
                pst.setString(9, jLabel_balance.getText());
                pst.setString(10, jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");

                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker5.setDate(null);
                jXDatePicker2.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                credit.setSelected(false);
                jLabel_paidmore.setText("");
                buttonGroup1.clearSelection();
                jLabel_shouldpay.setText("");
                
                update_table_purchase();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        else if(value1 < 0)
        {
            try {
                int row = jTable2.getSelectedRow();
                String id = jTable2.getModel().getValueAt(row, 1).toString();
                String sql = "UPDATE purchase SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, advance= ?, payment =? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount.getText());
                pst.setString(4, jTextField_VAT.getText());
                pst.setString(5, jTextField_total.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(11, jTextField_Number.getText());
                Double adv = Double.parseDouble(jLabel_balance.getText()); 
                Double pos = adv*(-1);
                pst.setString(9, pos.toString());
                pst.setString(10, jTextField_paid.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");

                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker5.setDate(null);
                jXDatePicker2.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                Discount.setSelected(false);
                buttonGroup1.clearSelection();
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                
                update_table_purchase();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            try {
                int row = jTable2.getSelectedRow();
                String id = jTable2.getModel().getValueAt(row, 1).toString();
                String sql = "UPDATE purchase SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker5.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount.getText());
                pst.setString(4, jTextField_VAT.getText());
                pst.setString(5, jTextField_total.getText());
                String date1 = sdf.format(jXDatePicker2.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method.getSelectedItem().toString();
                pst.setString(7, method); 
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(10, jTextField_Number.getText());
                pst.setString(9, jTextField_paid.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount.setText("");
                jTextField_VAT.setText("");
                jXDatePicker5.setDate(null);
                jXDatePicker2.setDate(null);
                jTextField_total.setText("");
                jComboBox_method.setSelectedIndex(0);
                jComboBox_name.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number.setText("");
                jLabel_balance.setText("");
                jTextField_paid.setText("");
                jLabel_date.setText("");
                Discount.setSelected(false);
                Pay_later.setSelected(false);
                jLabel_paidmore.setText("");
                jLabel_shouldpay.setText("");
                buttonGroup1.clearSelection();
                
                update_table_purchase();
            }
            catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
        }      
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            //DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            //int row = jTable2.getSelectedRow();
            String id = jTextField_Number.getText();
            String sql = "DELETE FROM purchase where InvoiceNo = '" + id+ "'";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted Succesfully..!!");

            jTextField_Amount.setText("");
            jTextField_VAT.setText("");
            jXDatePicker5.setDate(null);
            jXDatePicker2.setDate(null);
            jTextField_total.setText("");
            jComboBox_method.setSelectedIndex(0);
            jComboBox_name.setSelectedIndex(0);
            credit.setSelected(false);
            buttonGroup1.clearSelection();
            jLabel_cheque_no.setText("");
            jTextField_Number.setText("");
            jLabel_paidmore.setText("");
            jLabel_shouldpay.setText("");

            update_table_purchase();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        jTextField_Amount.setText("");
        jTextField_VAT.setText("");
        jXDatePicker5.setDate(null);
        jXDatePicker2.setDate(null);
        jTextField_total.setText("");
        jComboBox_method.setSelectedIndex(0);
        jComboBox_name.setSelectedIndex(0);
        jLabel_cheque_no.setText("");
        jTextField_Number.setText("");
        jLabel_balance.setText("");
        jTextField_paid.setText("");
        jLabel_date.setText("");
        buttonGroup1.clearSelection();
        Discount.setSelected(false);
        jLabel_paidmore.setText("");
        Pay_later.setSelected(false);           
        credit.setSelected(false);
        jLabel_shouldpay.setText("");
        jTextField_search.setText("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField_paidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_paidKeyReleased
        try {
            //double amt = Float.parseFloat(jTextField_total.getText());
            double paid = 0.00;
            paid = Float.parseFloat(jTextField_paid.getText());
            
            double balance = shouldpay - paid;
            String v1 = String.format("%.2f", balance);
            jLabel_balance.setText(v1);
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTextField_paidKeyReleased

    private void DiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiscountActionPerformed
        if(Discount.isSelected())
        {
            Pay_later.setSelected(false);
        }
    }//GEN-LAST:event_DiscountActionPerformed

    private void Pay_laterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pay_laterActionPerformed
        if(Pay_later.isSelected())
        {
            Discount.setSelected(false);
            payment_pop_up p = new payment_pop_up();
            p.setVisible(true);
            p.pack();
            p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }//GEN-LAST:event_Pay_laterActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jLabel_paidmore.setText("");
        jLabel_shouldpay.setText("");
        jLabel_balance.setText("0.00");
        jTextField_paid.setText("");
        try {               
            int i = jTable2.getSelectedRow();
            Date date =(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable2.getValueAt(i,0));
            jXDatePicker5.setDate(date);      
             Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable2.getValueAt(i,8));            
            jXDatePicker2.setDate(date1);
            jTextField_Number.setText(jTable2.getValueAt(i, 1).toString());       
            jComboBox_name.getModel().setSelectedItem(jTable2.getValueAt(i,2));
            jTextField_Amount.setText(jTable2.getValueAt(i, 3).toString());            
            jTextField_VAT.setText(jTable2.getValueAt(i, 4).toString());
            jTextField_total.setText(jTable2.getValueAt(i, 5).toString());
            jComboBox_method.getModel().setSelectedItem(jTable2.getValueAt(i,6));
            jTextField_paid.setText(jTable2.getValueAt(i, 7).toString());        
            
        }
        catch (ParseException ex) {
            Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        ppaidMore= 0.00;
        
        float amt = Float.parseFloat(jTextField_Amount.getText());
        float vat = Float.parseFloat(jTextField_VAT.getText());

        float total = amt + vat;
        String v1 = String.format("%.2f", total);
        jTextField_total.setText(v1);
        
        shouldpay = (total - ppaidMore) ;
        String v2 = String.format("%.2f", shouldpay);
        jLabel_shouldpay.setText("You Should Pay £ " + v2);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void jComboBox_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_name1ActionPerformed
        String name = jComboBox_name1.getSelectedItem().toString();
        if( name == "SALARY")
        {
            salary ch = new salary();
            ch.setVisible(true);
            ch.pack();
            ch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        
        try {            
            String sql = "select SUM(advance) AS 'more' FROM expences WHERE Name = ?";
            pst = con.prepareStatement(sql);
            String name2 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(1, name2);            
            ResultSet rs = pst.executeQuery();
            
             if(rs.next())
            {
                String tt = rs.getString("more");
                expaidMore = Double.parseDouble(tt);  
                String purpaidMore = String.format("%.2f", expaidMore);
                jLabel_paidmore1.setText("Paid £ "+purpaidMore + " More");
                
            }  
             else
             {
                expaidMore = 0.00;
             }
        } 
        catch (Exception e) {
             //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox_name1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        value = Double.parseDouble((jLabel_balance1.getText()));
       try {            
            String sql ="UPDATE expences SET advance =0.0 WHERE Name =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            String name2 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(1, name2);
            pst.executeUpdate();            
            update_table_expense();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
        if(Discount1.isSelected())
        {
            try {
                String sql = "insert into expences (date, InvoiceNo, Name, Amount, vat, Total, "
                        + "paidDate, Method, cheque_No, discount, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number1.getText());
                String name3;
                name3 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(3, name3);
                pst.setString(4, jTextField_Amount1.getText());
                pst.setString(5, jTextField_VAT1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total1.getText());
                pst.setString(9,jLabel_ex_cheque_no.getText());
                pst.setString(10, jLabel_balance1.getText());
                pst.setString(11, jTextField_paid1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number1.setText("");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker3.setDate(null);
                jXDatePicker4.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_ex_cheque_no.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_paidmore1.setText("");
                jLabel_shouldpay1.setText("");

                update_table_expense();

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        
        if(Pay_later1.isSelected())
        {
            try {
                String sql = "insert into expences (date, InvoiceNo, Name, Amount, vat, Total, "
                        + "paidDate, Method, cheque_No, insAmount, insDate, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number1.getText());
                String name3;
                name3 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(3, name3);
                pst.setString(4, jTextField_Amount1.getText());
                pst.setString(5, jTextField_VAT1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total1.getText());
                pst.setString(9,jLabel_ex_cheque_no.getText());
                pst.setString(10, jLabel_balance1.getText());
                pst.setString(11, jLabel_date1.getText());
                pst.setString(12, jTextField_paid1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number1.setText("");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker3.setDate(null);
                jXDatePicker4.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_ex_cheque_no.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_paidmore1.setText("");
                jLabel_shouldpay1.setText("");

                update_table_expense();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
       if(credit1.isSelected())
        {
            try {
                String sql = "insert into expences (date, InvoiceNo, Name, Amount, vat, Total, "
                        + "paidDate, Method, cheque_No, exception, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number1.getText());
                String name3;
                name3 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(3, name3);
                pst.setString(4, jTextField_Amount1.getText());
                pst.setString(5, jTextField_VAT1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total1.getText());
                pst.setString(9,jLabel_ex_cheque_no.getText());
                pst.setString(10, jLabel_balance1.getText());
                pst.setString(11, jTextField_paid1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number1.setText("");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker3.setDate(null);
                jXDatePicker4.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_ex_cheque_no.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                credit1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_paidmore1.setText("");
                jLabel_shouldpay1.setText("");

                update_table_expense();

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
       
       // if(advance_Radio1.isSelected())
       if(value < 0)
        {
            try {
                String sql = "insert into expences (date, InvoiceNo, Name, Amount, vat, Total, "
                        + "paidDate, Method, cheque_No, advance, payment) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number1.getText());
                String name3;
                name3 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(3, name3);
                pst.setString(4, jTextField_Amount1.getText());
                pst.setString(5, jTextField_VAT1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total1.getText());
                pst.setString(9,jLabel_ex_cheque_no.getText());
                Double adv = Double.parseDouble(jLabel_balance1.getText()); 
                Double pos = adv*(-1);
                pst.setString(10,pos.toString());
                pst.setString(11, jTextField_paid1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number1.setText("");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker3.setDate(null);
                jXDatePicker4.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_ex_cheque_no.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                credit1.setSelected(false);                
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_paidmore1.setText("");
                jLabel_shouldpay1.setText("");

                update_table_expense();

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        else
        {
            try {
                String sql = "insert into expences (date, InvoiceNo, Name, Amount, vat, Total,"
                        + " paidDate, Method, cheque_No, payment) values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                pst.setString(2, jTextField_Number1.getText());
                String name3;
                name3 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(3, name3);
                pst.setString(4, jTextField_Amount1.getText());
                pst.setString(5, jTextField_VAT1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(7,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(8, method);
                pst.setString(6, jTextField_total1.getText());
                pst.setString(9,jLabel_ex_cheque_no.getText());
                pst.setString(10, jTextField_paid1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Inserted Successfully..!!");

                jTextField_Number1.setText("");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker3.setDate(null);
                jXDatePicker4.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_ex_cheque_no.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_paidmore1.setText("");
                jLabel_shouldpay1.setText("");

                update_table_expense();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        value = Double.parseDouble(jLabel_balance1.getText());
       /*try {            
            String sql ="UPDATE expences SET advance =0.0 WHERE Name =? ";
            PreparedStatement pst = con.prepareStatement(sql);
            String name2 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(1, name2);
            pst.executeUpdate();            
            update_table_expense();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }*/ 
        if (Discount1.isSelected()) {
            try {                
                String sql = "UPDATE expences SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=? , discount = ?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount1.getText());
                pst.setString(4, jTextField_VAT1.getText());
                pst.setString(5, jTextField_total1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(9, jLabel_balance1.getText());
                pst.setString(11, jTextField_Number1.getText());
                pst.setString(10, jTextField_paid1.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker4.setDate(null);
                jXDatePicker3.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number1.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_shouldpay1.setText("");
                jLabel_paidmore1.setText("");
                jTextField_paid1.setText("");
                jLabel_balance1.setText("");
                
                update_table_expense();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        if (Pay_later1.isSelected()) {
            try {
                
                String sql = "UPDATE expences SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=? , insAmount = ?, insDate=?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount1.getText());
                pst.setString(4, jTextField_VAT1.getText());
                pst.setString(5, jTextField_total1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(9, jLabel_balance1.getText());
                pst.setString(10, jLabel_date1.getText());
                pst.setString(12, jTextField_Number1.getText());
                pst.setString(11, jTextField_paid1.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker4.setDate(null);
                jXDatePicker3.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number1.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_shouldpay1.setText("");
                jLabel_paidmore1.setText("");
                jTextField_paid1.setText("");
            jLabel_balance1.setText("");
                
                update_table_expense();
            }
            catch (Exception e) {
            }

        }
        if (credit1.isSelected()) {
            try {
                
                String sql = "UPDATE expences SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=? , exception = ?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount1.getText());
                pst.setString(4, jTextField_VAT1.getText());
                pst.setString(5, jTextField_total1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(9, jLabel_balance1.getText());
                pst.setString(11, jTextField_Number1.getText());
                pst.setString(10, jTextField_paid1.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker4.setDate(null);
                jXDatePicker3.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number1.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                credit1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_shouldpay1.setText("");
                jLabel_paidmore1.setText("");
                jTextField_paid1.setText("");
            jLabel_balance1.setText("");
                
                update_table_expense();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        
        if (value < 0) {
            try {
                
                String sql = "UPDATE expences SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=? , advance = ?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount1.getText());
                pst.setString(4, jTextField_VAT1.getText());
                pst.setString(5, jTextField_total1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                Double adv = Double.parseDouble(jLabel_balance1.getText()); 
                Double pos = adv*(-1);
                pst.setString(9, pos.toString());
                pst.setString(11, jTextField_Number1.getText());
                pst.setString(10, jTextField_paid1.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker4.setDate(null);
                jXDatePicker3.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number1.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                credit1.setSelected(false);                
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_shouldpay1.setText("");
                jLabel_paidmore1.setText("");
                jTextField_paid1.setText("");
            jLabel_balance1.setText("");
                
                update_table_expense();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        else
        {
            try {
                
                String sql = "UPDATE expences SET date=?,"
                + "Name=?,Amount=?,vat=?,Total=?,"
                + "paidDate=?,Method=?,cheque_No=?, payment = ? WHERE InvoiceNo = ?";
                pst = con.prepareStatement(sql);
                String date = sdf.format(jXDatePicker3.getDate());
                pst.setString(1,date);
                String name1;
                name1 = jComboBox_name1.getSelectedItem().toString();
                pst.setString(2, name1);
                pst.setString(3, jTextField_Amount1.getText());
                pst.setString(4, jTextField_VAT1.getText());
                pst.setString(5, jTextField_total1.getText());
                String date1 = sdf.format(jXDatePicker4.getDate());
                pst.setString(6,date1);
                String method = jComboBox_method1.getSelectedItem().toString();
                pst.setString(7, method);
                pst.setString(8,jLabel_cheque_no.getText());
                pst.setString(10, jTextField_Number1.getText());
                pst.setString(9, jTextField_paid1.getText());

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Succesfully..!!");
                jTextField_Amount1.setText("");
                jTextField_VAT1.setText("");
                jXDatePicker4.setDate(null);
                jXDatePicker3.setDate(null);
                jTextField_total1.setText("");
                jComboBox_method1.setSelectedIndex(0);
                jComboBox_name1.setSelectedIndex(0);
                jLabel_cheque_no.setText("");
                jTextField_Number1.setText("");
                jLabel_balance1.setText("");
                jLabel_date1.setText("");
                Discount1.setSelected(false);
                Pay_later1.setSelected(false);
                jTextField_paid1.setText("");
                buttonGroup2.clearSelection();
                jLabel_shouldpay1.setText("");
                jLabel_paidmore1.setText("");
                jTextField_paid1.setText("");
                jLabel_balance1.setText("");
                
                update_table_expense();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {
            
            String id = jTextField_Number1.getText();
            String sql = "DELETE FROM expences where InvoiceNo = '" + id + "'";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted Succesfully..!!");
            update_table_expense();

            jTextField_Number1.setText("");
            jTextField_Amount1.setText("");
            jTextField_VAT1.setText("");
            jXDatePicker3.setDate(null);
            jXDatePicker4.setDate(null);
            jTextField_total1.setText("");
            jComboBox_method1.setSelectedIndex(0);
            jComboBox_name1.setSelectedIndex(0);
            jLabel_ex_cheque_no.setText("");
            jTextField_Number1.setText("");
            credit1.setSelected(false);
            buttonGroup2.clearSelection();
            jLabel_shouldpay1.setText("");
            jLabel_paidmore1.setText("");
            jTextField_paid1.setText("");
            jLabel_balance1.setText("");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jTextField_Amount1.setText("");
        jTextField_VAT1.setText("");
        jXDatePicker4.setDate(null);
        jXDatePicker3.setDate(null);
        jTextField_total1.setText("");
        jComboBox_method1.setSelectedIndex(0);
        jComboBox_name1.setSelectedIndex(0);
        jLabel_cheque_no.setText("");
        jTextField_Number1.setText("");
        jLabel_balance1.setText("");
        jLabel_date1.setText("");
        Discount1.setSelected(false);
        credit1.setSelected(false);
        Pay_later1.setSelected(false);
        jTextField_paid1.setText("");
        buttonGroup2.clearSelection();
        jLabel_shouldpay1.setText("");
        jLabel_paidmore1.setText("");
        jTextField_search1.setText("");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField_paid1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_paid1KeyReleased
        try {
            //double amt = Float.parseFloat(jTextField_total1.getText());
            double paid = 0.00;
            paid = Float.parseFloat(jTextField_paid1.getText());

            double balance = exshouldpay - paid;
            String v1 = String.format("%.2f", balance);
            jLabel_balance1.setText(v1);
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTextField_paid1KeyReleased

    private void Discount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Discount1ActionPerformed
        if(Discount1.isSelected())
        {
            Pay_later1.setSelected(false);
        }
    }//GEN-LAST:event_Discount1ActionPerformed

    private void Pay_later1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pay_later1ActionPerformed
        if(Pay_later1.isSelected())
        {
            Discount1.setSelected(false);
            payment_pop_up2 p = new payment_pop_up2();
            p.setVisible(true);
            p.pack();
            p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
    }//GEN-LAST:event_Pay_later1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jLabel_paidmore1.setText("");
        jLabel_shouldpay1.setText("");
        jLabel_balance1.setText("0.00");
        try {

            int i = jTable1.getSelectedRow();
            Date date =(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable1.getValueAt(i,0));
            jXDatePicker3.setDate(date);
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable1.getValueAt(i,8));            
            jXDatePicker4.setDate(date1);
            jTextField_Number1.setText(jTable1.getValueAt(i, 1).toString());
            jComboBox_name1.getModel().setSelectedItem(jTable1.getValueAt(i,2));
            jTextField_Amount1.setText(jTable1.getValueAt(i, 3).toString());
            jTextField_VAT1.setText(jTable1.getValueAt(i, 4).toString());
            jTextField_total1.setText(jTable1.getValueAt(i, 5).toString());
            jComboBox_method1.getModel().setSelectedItem(jTable1.getValueAt(i,6));
            jTextField_paid1.setText(jTable1.getValueAt(i, 7).toString());           
            
        }
        catch (ParseException ex) {
            //Logger.getLogger(Income.class.getName()).log(Level.SEVERE, null, ex);
        }
        expaidMore = 0.00;
        double amt1 = Float.parseFloat(jTextField_Amount1.getText());
        double vat1 = Float.parseFloat(jTextField_VAT1.getText());

        double total = amt1 + vat1;
        String v1 = String.format("%.2f", total);
        jTextField_total1.setText(v1);
        
        exshouldpay = (total - expaidMore) ;
        String v2 = String.format("%.2f", exshouldpay);
        jLabel_shouldpay1.setText("You Should Pay £ " + v2);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox_method1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_method1ActionPerformed
        // TODO add your handling code here:
        String method = jComboBox_method1.getSelectedItem().toString();
        if( method == "CHEQUE")
        {
            pop_up2 ch = new pop_up2();
            ch.setVisible(true);
            ch.pack();
            ch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }//GEN-LAST:event_jComboBox_method1ActionPerformed

    private void jComboBox_methodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_methodActionPerformed
        // TODO add your handling code here:
        String method = jComboBox_method.getSelectedItem().toString();
        if( method == "CHEQUE")
        {
            pop_up ch = new pop_up();
            ch.setVisible(true);
            ch.pack();
            ch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //setVisible(false);
        }
    }//GEN-LAST:event_jComboBox_methodActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Income i = new Income();
        i.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Outgoing o = new Outgoing();
        o.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Reports r = new Reports();
        r.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        newVendor v = new newVendor();
        v.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
            vendor v = new vendor();
            v.setVisible(true);
            setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel_vendor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_vendor1MouseClicked
        credit c = new credit();
        c.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_vendor1MouseClicked

    private void jTextField_VATKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_VATKeyReleased
        
        float amt = Float.parseFloat(jTextField_Amount.getText());
        float vat = Float.parseFloat(jTextField_VAT.getText());

        float total = amt + vat;
        String v1 = String.format("%.2f", total);
        jTextField_total.setText(v1);
        
        shouldpay = (total - ppaidMore) ;
        String v2 = String.format("%.2f", shouldpay);
        jLabel_shouldpay.setText("You Should Pay £ " + v2);
    }//GEN-LAST:event_jTextField_VATKeyReleased

    private void jTextField_VAT1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_VAT1KeyReleased
        double amt = Float.parseFloat(jTextField_Amount1.getText());
        double vat = Float.parseFloat(jTextField_VAT1.getText());

        double total = amt + vat;
        String v1 = String.format("%.2f", total);
        jTextField_total1.setText(v1);
        
        exshouldpay = (total - expaidMore) ;
        String v2 = String.format("%.2f", exshouldpay);
        jLabel_shouldpay1.setText("You Should Pay £ " + v2);
    }//GEN-LAST:event_jTextField_VAT1KeyReleased

    private void jComboBox_name1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_name1KeyReleased
String name = jComboBox_name1.getSelectedItem().toString();
        if( name == "SALARY")
        {
            salary ch = new salary();
            ch.setVisible(true);
            ch.pack();
            ch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }        
    }//GEN-LAST:event_jComboBox_name1KeyReleased

    private void jComboBox_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_nameKeyReleased
       
    }//GEN-LAST:event_jComboBox_nameKeyReleased

    private void jTextField_VATKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_VATKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_VATKeyTyped

    private void jTextField_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_searchMouseClicked
        jTextField_search.setText("");
    }//GEN-LAST:event_jTextField_searchMouseClicked

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
       
        DefaultTableModel table = (DefaultTableModel) jTable2.getModel();
        String search = jTextField_search.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable2.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jTextField_search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_search1MouseClicked
       jTextField_search1.setText("");
    }//GEN-LAST:event_jTextField_search1MouseClicked

    private void jTextField_search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_search1ActionPerformed

    private void jTextField_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_search1KeyReleased
       DefaultTableModel table = (DefaultTableModel) jTable1.getModel();
        String search = jTextField_search1.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        jTable1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_jTextField_search1KeyReleased

    private void jTextField_paidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_paidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_paidKeyTyped

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Outgoing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Outgoing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Outgoing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Outgoing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Outgoing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Discount;
    private javax.swing.JRadioButton Discount1;
    private javax.swing.JLabel InvoiceNo;
    private javax.swing.JLabel InvoiceNo1;
    private javax.swing.JRadioButton Pay_later;
    private javax.swing.JRadioButton Pay_later1;
    private javax.swing.JLabel amount;
    private javax.swing.JLabel amount1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JRadioButton credit;
    private javax.swing.JRadioButton credit1;
    private javax.swing.JPanel heading;
    private javax.swing.JLabel invoiceDate;
    private javax.swing.JLabel invoiceDate1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox_method;
    private javax.swing.JComboBox<String> jComboBox_method1;
    private javax.swing.JComboBox<String> jComboBox_name;
    private javax.swing.JComboBox<String> jComboBox_name1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JLabel jLabel_balance;
    public static javax.swing.JLabel jLabel_balance1;
    public static javax.swing.JLabel jLabel_cheque_no;
    private javax.swing.JLabel jLabel_dashboard;
    public static javax.swing.JLabel jLabel_date;
    public static javax.swing.JLabel jLabel_date1;
    public static javax.swing.JLabel jLabel_ex_cheque_no;
    private javax.swing.JLabel jLabel_paidmore;
    private javax.swing.JLabel jLabel_paidmore1;
    private javax.swing.JLabel jLabel_shouldpay;
    private javax.swing.JLabel jLabel_shouldpay1;
    private javax.swing.JLabel jLabel_vendor1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField_Amount;
    public static javax.swing.JTextField jTextField_Amount1;
    public static javax.swing.JTextField jTextField_Number;
    public static javax.swing.JTextField jTextField_Number1;
    private javax.swing.JTextField jTextField_VAT;
    private javax.swing.JTextField jTextField_VAT1;
    private javax.swing.JTextField jTextField_paid;
    private javax.swing.JTextField jTextField_paid1;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JTextField jTextField_search1;
    private javax.swing.JTextField jTextField_total;
    private javax.swing.JTextField jTextField_total1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker5;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JPanel slide;
    private javax.swing.JLabel testlabek;
    private javax.swing.JLabel vat;
    private javax.swing.JLabel vat1;
    // End of variables declaration//GEN-END:variables
}
