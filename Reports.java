/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.*;
import java.awt.print.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author wayomi
 */
public class Reports extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public static Double value1 = 0.00;
    public static Double value2 = 0.00;
    public static Double value3 = 0.00;
    public static Double vat = 0.00;
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    DefaultTableModel model = new DefaultTableModel();
    
    public Reports() {
        initComponents();
        this.setLocationRelativeTo(this);
        con = DBConnect.connect();
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        pack();
        jTable2.setAutoCreateRowSorter(true);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable3.setAutoCreateRowSorter(true);
        jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable4.setAutoCreateRowSorter(true);
        jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        Fill_Combo();        
        Fill_Combo1();
        jLabel_discount1.setText("£ 0.00");
        jLabel_discount.setText("£ 0.00");
        supplier_tot1.setText("£ 0.00");
        supplier_tot.setText("£ 0.00");
        amt_display.setText("£ 0.00");
        amt_display1.setText("£ 0.00");
        
    }
    public void CheckGrid2(){
         try {
             DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int rows = model.getRowCount();
        int col = model.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Object ob = model.getValueAt(i, j);
                if (ob == null || ob.toString().isEmpty()) {
                    model.setValueAt("-", i, j);
                }
            }
        }
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
         
     }
     public void CheckGrid(){
         try {
             DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
        int rows = model.getRowCount();
        int col = model.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Object ob = model.getValueAt(i, j);
                if (ob == null || ob.toString().isEmpty()) {
                    model.setValueAt("-", i, j);
                }
            }
        }
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
         
     }
     public void CheckGrid1(){
         try {
             DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        int rows = model.getRowCount();
        int col = model.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Object ob = model.getValueAt(i, j);
                if (ob == null || ob.toString().isEmpty()) {
                    model.setValueAt("-", i, j);
                }
            }
        }
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Check grid"+ e);
         }
         
     }
     public void CheckGrid3(){
         try {
             DefaultTableModel model = (DefaultTableModel)jTable4.getModel();
        int rows = model.getRowCount();
        int col = model.getColumnCount();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                Object ob = model.getValueAt(i, j);
                if (ob == null || ob.toString().isEmpty()) {
                    model.setValueAt("-", i, j);
                }
            }
        }
             
         } catch (Exception e) {
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
    
    private void Fill_Combo1()
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
     public void claim_vat()
    {   
        try {
            String sql = "SELECT SUM(vat) AS 'purchase_vat' FROM purchase WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("purchase_vat");
                value1 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "purc" +e);
        }
        try {
            String sql = "SELECT SUM(vat) AS'expence_vat' FROM expences WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("expence_vat");
                value2 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"expe" +e);
        }
        try {
            String sql = "SELECT SUM(vat) AS 'income_vat' FROM income WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("income_vat");
                value3 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        vat = (value1 + value2) - value3;
        String value2 = String.format("%.2f", vat);
        claimTax.setText(" £"+value2);
    }
     public void claim_vat1()
    {   
        try {
            String sql = "SELECT SUM(vat) AS 'purchase_vat' FROM purchase WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("purchase_vat");
                value1 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Claim vat 1" +e);
        }
        try {
            String sql = "SELECT SUM(vat) AS 'expence_vat' FROM expences WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("expence_vat");
                value2 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "expence vat" +e);
        }
        try {
            String sql = "SELECT SUM(vat) AS 'income_vat' FROM income WHERE (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("income_vat");
                value3 = Double.parseDouble(tt);                                 
            } 
        
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"income vat" + e);
        }
        vat =( value1 + value2 )- value3;
        String value2 = String.format("%.2f", vat);
        claimTax1.setText(" £"+value2);
    }
      private String getCellValue(int x, int y)
    {
        return model.getValueAt(x, y).toString();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        slide3 = new javax.swing.JPanel();
        jLabel_dashboard3 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel_vendor4 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_name = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        vat_display = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        supplier_tot = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        amt_display = new javax.swing.JLabel();
        claim_tax = new javax.swing.JLabel();
        claimTax = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_discount = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jXDatePicker3 = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        jXDatePicker4 = new org.jdesktop.swingx.JXDatePicker();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        vat_display1 = new javax.swing.JLabel();
        amt_display1 = new javax.swing.JLabel();
        supplier_tot1 = new javax.swing.JLabel();
        claim_tax1 = new javax.swing.JLabel();
        claimTax1 = new javax.swing.JLabel();
        jComboBox_name1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel_discount1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jXDatePicker5 = new org.jdesktop.swingx.JXDatePicker();
        jLabel21 = new javax.swing.JLabel();
        jXDatePicker6 = new org.jdesktop.swingx.JXDatePicker();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel_total = new javax.swing.JLabel();
        jLabel_vat = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel_gross = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel_net = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jXDatePicker7 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker8 = new org.jdesktop.swingx.JXDatePicker();
        jLabel31 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel_Bincome = new javax.swing.JLabel();
        jLabel_Bpurchase = new javax.swing.JLabel();
        jLabel_Bexpnec = new javax.swing.JLabel();
        jLabel_Bbalance = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel_purchase3 = new javax.swing.JLabel();
        jLabel_purchase4 = new javax.swing.JLabel();
        jLabel_purchase5 = new javax.swing.JLabel();
        jLabel_purchase6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1172, 745));
        setPreferredSize(new java.awt.Dimension(1172, 745));

        heading.setBackground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IOM - Income~Outgoing Manager - Reports");

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

        slide3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_dashboard3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_dashboard3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_dashboard3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_dashboard3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_dashboard3.setText("Dashboard");
        jLabel_dashboard3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_dashboard3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dashboardMouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 51, 51));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Income");
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Outgoing");
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 51, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Reports");
        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 51, 51));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Staff");
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 51, 51));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("New Vendor");
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel_vendor4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_vendor4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_vendor4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_vendor4.setText("Credit");
        jLabel_vendor4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_vendor1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout slide3Layout = new javax.swing.GroupLayout(slide3);
        slide3.setLayout(slide3Layout);
        slide3Layout.setHorizontalGroup(
            slide3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_dashboard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(jLabel_vendor4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slide3Layout.setVerticalGroup(
            slide3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slide3Layout.createSequentialGroup()
                .addComponent(jLabel_dashboard3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_vendor4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(92, 109, 114));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setColumnSelectionAllowed(true);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("FROM");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("TO");

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton4.setText("VIEW");
        jButton4.setIconTextGap(16);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("VIEW BY NAME");

        jComboBox_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT NAME" }));
        jComboBox_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_nameActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("View By Supplier");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(204, 204, 204));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-pdf-30.png"))); // NOI18N
        jButton11.setText("DOWNLOAD");
        jButton11.setIconTextGap(10);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel11.setText("TOTAL VAT:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel12.setText("TOTAL:");

        vat_display.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel13.setText("BASED ON NAME:");

        supplier_tot.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton10.setBackground(new java.awt.Color(204, 204, 204));
        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-print-30.png"))); // NOI18N
        jButton10.setText("PRINT");
        jButton10.setIconTextGap(20);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        amt_display.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        claim_tax.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        claim_tax.setText("CLAIM VAT:");

        claimTax.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setText("TOTAL DISCOUNT:");

        jLabel_discount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-excel-30.png"))); // NOI18N
        jButton3.setText("EXCEL FILE");
        jButton3.setIconTextGap(10);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vat_display, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amt_display, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supplier_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)
                                .addComponent(claim_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(claimTax, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_discount, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vat_display, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(amt_display, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplier_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton3))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claim_tax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(claimTax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_discount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Purchase", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("FROM");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("TO");

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton9.setText("VIEW");
        jButton9.setIconTextGap(16);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-pdf-30.png"))); // NOI18N
        jButton12.setText("DOWNLOAD");
        jButton12.setIconTextGap(10);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-print-30.png"))); // NOI18N
        jButton13.setText("PRINT");
        jButton13.setIconTextGap(20);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 0, 0));
        jButton14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("View By Supplier");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("VIEW BY NAME");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel17.setText("TOTAL VAT:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel18.setText("TOTAL:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel19.setText("BASED ON NAME:");

        vat_display1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        amt_display1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        supplier_tot1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        claim_tax1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        claim_tax1.setText("CLAIM VAT:");

        claimTax1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jComboBox_name1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECT NAME", "ABERDEEN  CITY COUNCIL", "BLUE WAVE", "BT", "ELIS", "WFG SERVICE STATION", "FOOD", "FUEL", "GARY SMITH", "HOT FLAME", "INVOICE", "JAMES COOPER & SON", "JEWSON", "M & H LOGISTICS LTD", "MARK SHAW", "NELLA", "POST OFFICE", "RISHIS", "SALARY", "SAINSBURYS", "SECURASTORE", "SMYTHS", "TAYLORS", "TRAVELSTOCK", "SPICE OF ASIA RENT", "SPICE OF ASIA EQUIMENT", "SPICE OF ASIS ENERGY CHAR", "OTHER" }));
        jComboBox_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_name1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("TOTAL DISCOUNT:");

        jLabel_discount1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-excel-30.png"))); // NOI18N
        jButton5.setText("EXCEL FILE");
        jButton5.setIconTextGap(10);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vat_display1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jXDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplier_tot1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(claim_tax1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claimTax1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_discount1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amt_display1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14)
                            .addComponent(jComboBox_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vat_display1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton13)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(amt_display1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claim_tax1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(supplier_tot1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_discount1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addComponent(claimTax1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTable3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Expense", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("FROM");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setText("TO");

        jButton15.setBackground(new java.awt.Color(204, 204, 204));
        jButton15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton15.setText("VIEW");
        jButton15.setIconTextGap(16);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(204, 204, 204));
        jButton16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-pdf-30.png"))); // NOI18N
        jButton16.setText("DOWNLOAD");
        jButton16.setIconTextGap(10);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(204, 204, 204));
        jButton17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-print-30.png"))); // NOI18N
        jButton17.setText("PRINT");
        jButton17.setIconTextGap(20);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel22.setText("TOTAL INCOME:");

        jLabel23.setText("TOTAL VAT:");

        jLabel_total.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel_vat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel24.setText("TOTAL GROSS:");

        jLabel_gross.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel25.setText("TOTAL NET SALES:");

        jLabel_net.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-excel-30.png"))); // NOI18N
        jButton6.setText("EXCEL FILE");
        jButton6.setIconTextGap(10);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_total, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_vat, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_gross, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_net, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jXDatePicker5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jXDatePicker6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jXDatePicker5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16))
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22)
                                .addComponent(jLabel_gross, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24)))
                        .addComponent(jLabel_total, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton17))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel23))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel_vat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel_net, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)))
                .addGap(28, 28, 28))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "DATE", "SALES", "PAY POINT", "TYPE", "LOTTERY", "PHONE CARDS", "OTHER"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Income", jPanel6);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("FROM");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("TO");

        jButton18.setBackground(new java.awt.Color(204, 204, 204));
        jButton18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton18.setText("VIEW");
        jButton18.setIconTextGap(20);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(204, 204, 204));
        jButton19.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-pdf-30.png"))); // NOI18N
        jButton19.setText("DOWNLOAD");
        jButton19.setIconTextGap(10);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(204, 204, 204));
        jButton20.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-print-30.png"))); // NOI18N
        jButton20.setText("PRINT");
        jButton20.setIconTextGap(20);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-export-excel-30.png"))); // NOI18N
        jButton7.setText("EXCEL FILE");
        jButton7.setIconTextGap(10);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jXDatePicker7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jXDatePicker8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jXDatePicker7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addComponent(jButton20)
                .addGap(22, 22, 22)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Credit", jPanel11);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 51));
        jLabel26.setText("TOTAL INCOME:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 51));
        jLabel27.setText("TOTAL PURCHASES:");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 51));
        jLabel28.setText("TOTAL EXPENCES:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 51, 51));
        jLabel29.setText("BALANCE:");

        jLabel_Bincome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel_Bpurchase.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel_Bexpnec.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel_Bbalance.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton1.setText("VIEW");
        jButton1.setIconTextGap(16);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel34.setText("TO");

        jLabel35.setText("FROM");

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("RESET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel_purchase3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_purchase3.setText("£");

        jLabel_purchase4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_purchase4.setText("£");

        jLabel_purchase5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_purchase5.setText("£");

        jLabel_purchase6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_purchase6.setText("£");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_purchase4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_purchase3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_Bincome, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel_Bpurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_purchase5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Bexpnec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(163, 163, 163)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_purchase6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Bbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(284, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel34)
                .addGap(28, 28, 28)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Bincome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(jLabel_purchase3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel_purchase4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_Bpurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel_purchase6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Bbalance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Bexpnec, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel_purchase5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 417, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Balance", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slide3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slide3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {

            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice No', Name, Total AS 'Total (£)',vat AS 'VAT (£)',"
                    + "paidDate AS '1st Installment',payment As '1st Payment', insDate AS '2nd Installment',"
                    + "insAmount AS '2nd Payment', Method, advance AS 'Continuation', discount AS 'Discount' "
                    + " from purchase where date between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            JTableHeader head = jTable2.getTableHeader();
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
            head.setForeground(Color.blue);            
            jTable2.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            jTable2.setShowGrid(true);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Main"+ e);
        }

        try{

            String sql = "select SUM(Total) from purchase where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            amt_display.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Total" +e);
        }
        try{

            String sql = "select SUM(vat) from purchase where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            vat_display.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "vat" + e);
        }
        try{

            String sql = "select SUM(discount) from purchase where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_discount.setText(" £"+value2);
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, "Discount" + e);
        }
        claim_vat();
        CheckGrid1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_nameActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {

            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice No', Name, Total AS 'Total (£)',vat AS 'VAT (£)',"
                    + "paidDate AS '1st Installment',payment As '1st Payment', insDate AS '2nd Installment',"
                    + "insAmount AS '2nd Payment', Method, advance AS 'Continuation', discount AS 'Discount' "
            + "from purchase where (date between ? and ? )and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            JTableHeader head = jTable2.getTableHeader();
            head.setForeground(Color.blue);            
            jTable2.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            jTable2.setShowGrid(true);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        try{

            String sql = "select SUM(Total) from purchase where (date between ? and ?) and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);

            supplier_tot.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        try{

            String sql = "select SUM(discount) from purchase where (date between ? and ?) and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker2.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);

            jLabel_discount.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        claim_vat();
        CheckGrid1();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker1.getDate());
        String to = sdf.format(jXDatePicker2.getDate());
        String name1;
        name1 = jComboBox_name.getSelectedItem().toString();
        try{
            Document doc = new Document();
            String filename = "Purchase Report ("+from+" - "+to+ ").pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path+filename));
            doc.open();
            
            doc.add(new Paragraph("IOM - Purchase Report File \n", FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD)));
            doc.add(new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.TIMES,10)));

            doc.add(new Paragraph("From :"+from+"  To : "+to+ "\n",FontFactory.getFont(FontFactory.TIMES,10)));

            Font fontH1 = new Font(Font.TIMES_ROMAN,8, Font.BOLD);
            Font fontH = new Font(Font.TIMES_ROMAN,7);

            //table.AddCell(new PdfPCell(new Phrase(yourDatabaseValue,fontH1)));
            
            PdfPTable p1 = new PdfPTable(12);
            
            p1.setTotalWidth(5000);
            p1.setWidths(new int[]{350, 350, 600, 300,200,350,200,350,300,400,300,300});
            
            p1.addCell(new PdfPCell(new Phrase("Date",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Invoice Number",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Name",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Total Amount (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("VAT (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("1st Installment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("1st Payment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("2nd Installment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("2nd Payment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Payment Method",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Advanced Amount",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Discount",fontH1)));

            for(int i=0; i<jTable2.getRowCount(); i++)
            {
                String date = jTable2.getValueAt(i, 0).toString();
                String no = jTable2.getValueAt(i, 1).toString();
                String name = jTable2.getValueAt(i, 2).toString();
                String amount = jTable2.getValueAt(i, 3).toString();
                String vat = jTable2.getValueAt(i, 4).toString();
                String pDate1 = jTable2.getValueAt(i, 5).toString();
                String pPay1 = jTable2.getValueAt(i, 6).toString();
                String pDate2 = jTable2.getValueAt(i, 7).toString();
                String pPay2 = jTable2.getValueAt(i, 8).toString();
                String method = jTable2.getValueAt(i, 9).toString();
                String advance = jTable2.getValueAt(i, 10).toString();
                String discount = jTable2.getValueAt(i, 11).toString();

                p1.addCell(new PdfPCell(new Phrase(date,fontH)));
                p1.addCell(new PdfPCell(new Phrase(no,fontH)));
                p1.addCell(new PdfPCell(new Phrase(name,fontH)));
                p1.addCell(new PdfPCell(new Phrase(amount,fontH)));
                p1.addCell(new PdfPCell(new Phrase(vat,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pDate1,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pPay1,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pDate2,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pPay2,fontH)));
                p1.addCell(new PdfPCell(new Phrase(method,fontH)));
                p1.addCell(new PdfPCell(new Phrase(advance,fontH)));
                p1.addCell(new PdfPCell(new Phrase(discount,fontH)));

            }
            doc.add(p1);
            doc.add(new Paragraph("Total Amount Based on Supplier " + name1+" : " + supplier_tot.getText().toString(), FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Total Amount : " + amt_display.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Total VAT : " + vat_display.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Claim VAT : " + claimTax.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            
            doc.close();

            JOptionPane.showMessageDialog(null, "File Downloaded..!!");

            jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            jComboBox_name.setSelectedIndex(0);
            amt_display.setText("");
            supplier_tot.setText("");
            claimTax.setText("");
            vat_display.setText("");
            jLabel_discount.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e + "Error downloading File..!!");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker1.getDate());
        String to = sdf.format(jXDatePicker2.getDate());

        MessageFormat title = new MessageFormat("Purchase Report ("+from+" - "+to+ ")");
        MessageFormat footer = new MessageFormat("Page{0,number, integer}");

        try {
            jTable2.print(JTable.PrintMode.FIT_WIDTH, title, footer);

            jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            vat_display.setText("");
            amt_display.setText("");
            supplier_tot.setText("");
            jComboBox_name.setSelectedIndex(0);

            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
                model.removeRow(i);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot Print File..!!");
        }
         jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            jComboBox_name.setSelectedIndex(0);
            amt_display.setText("");
            supplier_tot.setText("");
            claimTax.setText("");
            vat_display.setText("");
            jLabel_discount.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
        try {

            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice No', Name, Total AS 'Total (£)',vat AS 'VAT (£)',"
                    + "paidDate AS '1st Installment',payment As '1st Payment', insDate AS '2nd Installment',"
                    + "insAmount AS '2nd Payment', Method, advance AS 'Continuation', discount AS 'Discount' from expences where date between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            JTableHeader head = jTable3.getTableHeader();
            head.setForeground(Color.blue);            
            jTable3.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            jTable3.setShowGrid(true);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select the Time range..!!");
        }

        try{

            String sql = "select SUM(Total) from expences where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            amt_display1.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        try{

            String sql = "select SUM(vat) from expences where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            vat_display1.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        try{

            String sql = "select SUM(discount) from expences where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_discount1.setText(" £"+value2);
        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        claim_vat1();
        CheckGrid();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker3.getDate());
        String to = sdf.format(jXDatePicker4.getDate());
        String name1;
        name1 = jComboBox_name1.getSelectedItem().toString();
        try{
            Document doc = new Document();
            String filename = "Expence Report ("+from+" - "+to+ ").pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path+filename));
            doc.open();

            doc.add(new Paragraph("IOM - Expences Report File \n", FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD)));
            doc.add(new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.TIMES,10)));

            doc.add(new Paragraph("From :"+from+"  To : "+to+ "\n",FontFactory.getFont(FontFactory.TIMES,10)));

            Font fontH1 = new Font(Font.TIMES_ROMAN,8, Font.BOLD);
            Font fontH = new Font(Font.TIMES_ROMAN,7);
      
            PdfPTable p1 = new PdfPTable(12);
            p1.addCell(new PdfPCell(new Phrase("Date",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Invoice Number",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Name",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Total Amount (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("VAT (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("1st Installment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("1st Payment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("2nd Installment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("2nd Payment",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Payment Method",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Advanced Amount",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Discount",fontH1)));

            for(int i=0; i<jTable3.getRowCount(); i++)
            {
                String date = jTable3.getValueAt(i, 0).toString();
                String no = jTable3.getValueAt(i, 1).toString();
                String name = jTable3.getValueAt(i, 2).toString();
                String amount = jTable3.getValueAt(i, 3).toString();
                String vat = jTable3.getValueAt(i, 4).toString();
                String pDate1 = jTable3.getValueAt(i, 5).toString();
                String pPay1 = jTable3.getValueAt(i, 6).toString();
                String pDate2 = jTable3.getValueAt(i, 7).toString();
                String pPay2 = jTable3.getValueAt(i, 8).toString();
                String method = jTable3.getValueAt(i, 9).toString();
                String advance = jTable3.getValueAt(i, 10).toString();
                String discount = jTable3.getValueAt(i, 11).toString();

                p1.addCell(new PdfPCell(new Phrase(date,fontH)));
                p1.addCell(new PdfPCell(new Phrase(no,fontH)));
                p1.addCell(new PdfPCell(new Phrase(name,fontH)));
                p1.addCell(new PdfPCell(new Phrase(amount,fontH)));
                p1.addCell(new PdfPCell(new Phrase(vat,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pDate1,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pPay1,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pDate2,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pPay2,fontH)));
                p1.addCell(new PdfPCell(new Phrase(method,fontH)));
                p1.addCell(new PdfPCell(new Phrase(advance,fontH)));
                p1.addCell(new PdfPCell(new Phrase(discount,fontH)));

            }
            doc.add(p1);
            doc.add(new Paragraph("Total Amount Based on Supplier " + name1+" : " + supplier_tot1.getText().toString(), FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Total Amount : " + amt_display1.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Total VAT : " + vat_display1.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Claim VAT : " + claimTax1.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.close();

            JOptionPane.showMessageDialog(null, "File Downloaded..!!");

            jXDatePicker3.setDate(null);
            jXDatePicker4.setDate(null);
            jComboBox_name1.setSelectedIndex(0);
            amt_display1.setText("");
            supplier_tot1.setText("");
            vat_display1.setText("");
            claimTax1.setText("");
            jLabel_discount1.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker3.getDate());
        String to = sdf.format(jXDatePicker4.getDate());
        
        MessageFormat title = new MessageFormat("Purchase Report ("+from+" - "+to+ ")");
        MessageFormat footer = new MessageFormat("Page{0,number, integer}");

        try {
            jTable3.print(JTable.PrintMode.FIT_WIDTH, title, footer);

            jXDatePicker3.setDate(null);
            jXDatePicker4.setDate(null);
            vat_display1.setText("");
            amt_display1.setText("");
            supplier_tot1.setText("");
            jComboBox_name1.setSelectedIndex(0);

            DefaultTableModel model = (DefaultTableModel)jTable3.getModel();
            for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
                model.removeRow(i);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot Print File..!!");
        }
        jXDatePicker3.setDate(null);
            jXDatePicker4.setDate(null);
            jComboBox_name1.setSelectedIndex(0);
            amt_display1.setText("");
            supplier_tot1.setText("");
            vat_display1.setText("");
            claimTax1.setText("");
            jLabel_discount1.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        try {

            String sql = "select date AS 'Date',InvoiceNo AS 'Invoice No', Name, Total AS 'Total (£)',vat AS 'VAT (£)',"
                    + "paidDate AS '1st Installment',payment As '1st Payment', insDate AS '2nd Installment',"
                    + "insAmount AS '2nd Payment', Method, advance AS 'Continuation', discount AS 'Discount' "
            + "from expences where (date between ? and ? )and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            JTableHeader head = jTable3.getTableHeader();
            head.setForeground(Color.blue);            
            jTable3.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            jTable3.setShowGrid(true);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        try{

            String sql = "select SUM(Total) from expences where (date between ? and ?) and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);

            supplier_tot1.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        try{

            String sql = "select SUM(discount) from expences where (date between ? and ?) and (Name = ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker3.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker4.getDate());
            pst.setString(2,to);

            String name1;
            name1 = jComboBox_name1.getSelectedItem().toString();
            pst.setString(3, name1);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);

            jLabel_discount1.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Supplier..!!");
        }
        claim_vat1();
        CheckGrid();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try {          

            String sql = "select date AS 'DATE',sales AS 'SHOP SALES', paypoint AS 'PAY POINT',"
                    + "cards AS 'PHONE CARDS', lottery AS 'LOTTERY', other AS 'OTHER',"
                    + "net AS 'NET VALUE', vat AS 'VAT', total AS 'TOTAL', gross AS 'GROSS VALUE',cash AS 'CASH' ,"
                    + "bank AS 'BANK' , credit AS 'CREDIT'"
                    + "from income where date between ? and ?";
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker5.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker6.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            JTableHeader head = jTable1.getTableHeader();
            head.setForeground(Color.blue);            
            jTable1.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.setShowGrid(true);   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select the Time range..!!");
        }
        try{

            String sql = "select SUM(total) from income where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker5.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker6.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_total.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        try{

            String sql = "select SUM(vat) from income where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker5.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker6.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_vat.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        try{

            String sql = "select SUM(net) from income where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker5.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker6.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_net.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        try{

            String sql = "select SUM(gross) from income where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String from = sdf.format(jXDatePicker5.getDate());
            pst.setString(1,from);

            String to = sdf.format(jXDatePicker6.getDate());
            pst.setString(2,to);

            ResultSet rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_gross.setText(" £"+value2);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Select the Time range properly..!!");
        }
        CheckGrid2();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        
        String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker5.getDate());
        String to = sdf.format(jXDatePicker6.getDate());
       
        try{
            Document doc = new Document();
            String filename = "Income Report ("+from+" - "+to+ ").pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path+filename));
            doc.open();

            doc.add(new Paragraph("IOM - Income Report File \n", FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD)));
            doc.add(new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.TIMES,10)));

            doc.add(new Paragraph("From :"+from+"  To : "+to+ "\n",FontFactory.getFont(FontFactory.TIMES,10)));

            Font fontH1 = new Font(Font.TIMES_ROMAN,8, Font.BOLD);
            Font fontH = new Font(Font.TIMES_ROMAN,7);

            //table.AddCell(new PdfPCell(new Phrase(yourDatabaseValue,fontH1)));
            PdfPTable p1 = new PdfPTable(13);
            p1.addCell(new PdfPCell(new Phrase("Date",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Shop Sales (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Pay Point (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Phone cards (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Lottery (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Other (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Total (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Net (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Vat (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Gross (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("CASH",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("BANK",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("CREDIT",fontH1)));

            for(int i=0; i<jTable1.getRowCount(); i++)
            {
                String date = jTable1.getValueAt(i, 0).toString();
                String sales = jTable1.getValueAt(i, 1).toString();
                String pay = jTable1.getValueAt(i, 2).toString();
                String cards = jTable1.getValueAt(i, 3).toString();
                String lot = jTable1.getValueAt(i, 4).toString();
                String other = jTable1.getValueAt(i, 5).toString();
                String total = jTable1.getValueAt(i, 6).toString();
                String net = jTable1.getValueAt(i, 7).toString();
                String vat = jTable1.getValueAt(i, 8).toString();
                String gross = jTable1.getValueAt(i, 9).toString();
                String cash = jTable1.getValueAt(i, 10).toString();
                String bank = jTable1.getValueAt(i, 11).toString();
                String credit = jTable1.getValueAt(i, 12).toString();

                p1.addCell(new PdfPCell(new Phrase(date,fontH)));
                p1.addCell(new PdfPCell(new Phrase(sales,fontH)));
                p1.addCell(new PdfPCell(new Phrase(pay,fontH)));
                p1.addCell(new PdfPCell(new Phrase(cards,fontH)));
                p1.addCell(new PdfPCell(new Phrase(lot,fontH)));
                p1.addCell(new PdfPCell(new Phrase(other,fontH)));
                p1.addCell(new PdfPCell(new Phrase(total,fontH)));
                p1.addCell(new PdfPCell(new Phrase(net,fontH)));
                p1.addCell(new PdfPCell(new Phrase(vat,fontH)));
                p1.addCell(new PdfPCell(new Phrase(gross,fontH)));
                p1.addCell(new PdfPCell(new Phrase(cash,fontH)));
                p1.addCell(new PdfPCell(new Phrase(bank,fontH)));
                p1.addCell(new PdfPCell(new Phrase(credit,fontH)));

            }
            doc.add(p1);
           
            doc.add(new Paragraph("Total Income : " + jLabel_total.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Total VAT : " + jLabel_vat.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Gross Value : " + jLabel_gross.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.add(new Paragraph("Net Value : " + jLabel_net.getText().toString(),FontFactory.getFont(FontFactory.TIMES,8)));
            doc.close();

            JOptionPane.showMessageDialog(null, "File Downloaded..!!");

            jXDatePicker5.setDate(null);
            jXDatePicker6.setDate(null);           
            jLabel_total.setText("");
            jLabel_net.setText("");
            jLabel_gross.setText("");
            jLabel_vat.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error downloading File..!!");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker5.getDate());
        String to = sdf.format(jXDatePicker6.getDate());

        MessageFormat title = new MessageFormat("Income Report ("+from+" - "+to+ ")");
        MessageFormat footer = new MessageFormat("Page{0,number, integer}");

        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, title, footer);

            jXDatePicker5.setDate(null);
            jXDatePicker6.setDate(null);           
            jLabel_total.setText("");
            jLabel_net.setText("");
            jLabel_gross.setText("");
            jLabel_vat.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
                model.removeRow(i);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot Print File..!!");
        }
        jXDatePicker5.setDate(null);
            jXDatePicker6.setDate(null);           
            jLabel_total.setText("");
            jLabel_net.setText("");
            jLabel_gross.setText("");
            jLabel_vat.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            try {
            String sql = "select SUM(total) from income where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            String from = sdf.format(jDateChooser1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);

            rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_Bincome.setText(value2);
        } 
            catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
        }
            try {
            String sql = "select SUM(Total) from purchase where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            String from = sdf.format(jDateChooser1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);

            rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_Bpurchase.setText(value2);
        } 
            catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
        }
            try {
            String sql = "select SUM(Total) from expences where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            String from = sdf.format(jDateChooser1.getDate());
            pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);

            rs = pst.executeQuery();
            rs.next();

            String value = rs.getString(1);
            Double value1 = Double.parseDouble(value);
            String value2 = String.format("%.2f", value1);
            jLabel_Bexpnec.setText(value2);
        } 
            catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, e);
        }
        
        String in = jLabel_Bincome.getText();
        Double inD = Double.parseDouble(in);
        
        String pr = jLabel_Bpurchase.getText();
        Double prD = Double.parseDouble(pr);
        
        String ex = jLabel_Bexpnec.getText();
        Double exD = Double.parseDouble(ex);
        
        double balance = inD - (prD + exD);
        String Sbal = String.format("%.2f", balance);
        jLabel_Bbalance.setText(Sbal);
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jLabel_Bincome.setText("");
        jLabel_Bpurchase.setText("");
        jLabel_Bbalance.setText("");
        jLabel_Bexpnec.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_name1ActionPerformed
       
    }//GEN-LAST:event_jComboBox_name1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
            try {          

            String sql = "select date AS 'DATE', Name AS 'NAME', amount AS 'AMOUNT',card AS 'CARD',"
                    + "cash AS 'CASH',"
                    + "balance AS 'BALANCE' FROM credit WHERE date between ? and ? "; 
            PreparedStatement pst = con.prepareStatement(sql);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");           
            
            String from = sdf.format(jXDatePicker7.getDate());
            pst.setString(1,from);
            String to = sdf.format(jXDatePicker8.getDate());
            pst.setString(2,to);
            ResultSet rs = pst.executeQuery();
            
            JTableHeader head = jTable4.getTableHeader();
            head.setForeground(Color.blue);            
            jTable4.getTableHeader().setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 11));
            jTable4.setModel(DbUtils.resultSetToTableModel(rs)); 
            jTable4.setShowGrid(true);               
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         CheckGrid3();         
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker7.getDate());
        String to = sdf.format(jXDatePicker8.getDate());
       
        try{
            Document doc = new Document();
            String filename = "Credit Report ("+from+" - "+to+ ").pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(path+filename));
            doc.open();

            doc.add(new Paragraph("IOM - Credit Report File \n", FontFactory.getFont(FontFactory.TIMES,14,Font.BOLD)));
            doc.add(new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.TIMES,10)));

            doc.add(new Paragraph("From :"+from+"  To : "+to+ "\n",FontFactory.getFont(FontFactory.TIMES,10)));

            Font fontH1 = new Font(Font.TIMES_ROMAN,8, Font.BOLD);
            Font fontH = new Font(Font.TIMES_ROMAN,7);

            //table.AddCell(new PdfPCell(new Phrase(yourDatabaseValue,fontH1)));
            PdfPTable p1 = new PdfPTable(6);
            p1.addCell(new PdfPCell(new Phrase("Date",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Name",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Amount (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Card (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Cash (£)",fontH1)));
            p1.addCell(new PdfPCell(new Phrase("Balance (£)",fontH1)));
                    

            for(int i=0; i<jTable4.getRowCount(); i++)
            {
                String date = jTable4.getValueAt(i, 0).toString();
                String name = jTable4.getValueAt(i, 1).toString();
                String amount = jTable4.getValueAt(i, 2).toString();
                String card = jTable4.getValueAt(i, 3).toString();
                String cash = jTable4.getValueAt(i, 4).toString();
                String balance = jTable4.getValueAt(i, 5).toString();
                
                p1.addCell(new PdfPCell(new Phrase(date,fontH)));
                p1.addCell(new PdfPCell(new Phrase(name,fontH)));
                p1.addCell(new PdfPCell(new Phrase(amount,fontH)));
                p1.addCell(new PdfPCell(new Phrase(card,fontH)));
                p1.addCell(new PdfPCell(new Phrase(cash,fontH)));
                p1.addCell(new PdfPCell(new Phrase(balance,fontH)));
                
            }
            doc.add(p1);
           
            doc.close();

            JOptionPane.showMessageDialog(null, "File Downloaded..!!");

            jXDatePicker7.setDate(null);
            jXDatePicker8.setDate(null);           
            
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error downloading File..!!");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker7.getDate());
        String to = sdf.format(jXDatePicker8.getDate());

        MessageFormat title = new MessageFormat("Credit Report ("+from+" - "+to+ ")");
        MessageFormat footer = new MessageFormat("Page{0,number, integer}");

        try {
            jTable4.print(JTable.PrintMode.FIT_WIDTH, title, footer);

            jXDatePicker7.setDate(null);
            jXDatePicker8.setDate(null);           
            
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);
            
            for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
                model.removeRow(i);
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot Print File..!!");
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jLabel_vendor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_vendor1MouseClicked
        credit c = new credit();
        c.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_vendor1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        vendor v = new vendor();
        v.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        newVendor n = new newVendor();
        n.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Reports r = new Reports();
        r.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Outgoing o = new Outgoing();
        o.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Income i = new Income();
        i.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dashboardMouseClicked
        Home h = new Home();
        h.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_dashboardMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker1.getDate());
        String to = sdf.format(jXDatePicker2.getDate());
            
        try {
        HSSFWorkbook fWorkbook = new HSSFWorkbook();
        HSSFSheet fSheet;
        fSheet = fWorkbook.createSheet("new Sheet");
        HSSFFont sheetTitleFont = fWorkbook.createFont();
        File file = new File("Purchase_report("+from+" - "+to+ ").xls");         

        HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
        sheetTitleFont.setBoldweight(HSSFFont.U_DOUBLE);
        //sheetTitleFont.setColor();
        TableModel model = jTable2.getModel();

        TableColumnModel model1 = jTable2.getTableHeader().getColumnModel();
        HSSFRow fRow1 = fSheet.createRow((short) 0);
        for (int i = 0; i < model1.getColumnCount(); i++){
            HSSFCell Hcell = fRow1.createCell((short) i);
            Hcell.setCellValue(model1.getColumn(i).getHeaderValue().toString());           

}
        for (int i = 0; i < model.getRowCount(); i++) {
            HSSFRow fRow = fSheet.createRow((short) i+1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                HSSFCell cell = fRow.createCell((short) j);
                cell.setCellValue(model.getValueAt(i, j).toString());
                cell.setCellStyle(cellStyle);
            }
        }
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path+file);
        try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
            fWorkbook.write(bos);
            JOptionPane.showMessageDialog(null, "Excel File Downloaded..!!");
        }
        fileOutputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
        jXDatePicker1.setDate(null);
            jXDatePicker2.setDate(null);
            jComboBox_name.setSelectedIndex(0);
            amt_display.setText("");
            supplier_tot.setText("");
            claimTax.setText("");
            vat_display.setText("");
            jLabel_discount.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker3.getDate());
        String to = sdf.format(jXDatePicker4.getDate());
       
        try {
        HSSFWorkbook fWorkbook = new HSSFWorkbook();
        HSSFSheet fSheet;
        fSheet = fWorkbook.createSheet("new Sheet");
        HSSFFont sheetTitleFont = fWorkbook.createFont();
        File file = new File("Expence_report("+from+" - "+to+ ").xls");         

        HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
        sheetTitleFont.setBoldweight(HSSFFont.U_DOUBLE);
        //sheetTitleFont.setColor();
        TableModel model = jTable3.getModel();

        TableColumnModel model1 = jTable3.getTableHeader().getColumnModel();
        HSSFRow fRow1 = fSheet.createRow((short) 0);
        for (int i = 0; i < model1.getColumnCount(); i++){
            HSSFCell Hcell = fRow1.createCell((short) i);
            Hcell.setCellValue(model1.getColumn(i).getHeaderValue().toString());           

}
        for (int i = 0; i < model.getRowCount(); i++) {
            HSSFRow fRow = fSheet.createRow((short) i+1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                HSSFCell cell = fRow.createCell((short) j);
                cell.setCellValue(model.getValueAt(i, j).toString());
                cell.setCellStyle(cellStyle);
            }
        }
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path+file);
        try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
            fWorkbook.write(bos);
            JOptionPane.showMessageDialog(null, "Excel File Downloaded..!!");
        }
        fileOutputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
           jXDatePicker3.setDate(null);
            jXDatePicker4.setDate(null);
            jComboBox_name1.setSelectedIndex(0);
            amt_display1.setText("");
            supplier_tot1.setText("");
            vat_display1.setText("");
            claimTax1.setText("");
            jLabel_discount1.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker5.getDate());
        String to = sdf.format(jXDatePicker6.getDate());
               
        try {
        HSSFWorkbook fWorkbook = new HSSFWorkbook();
        HSSFSheet fSheet;
        fSheet = fWorkbook.createSheet("new Sheet");
        HSSFFont sheetTitleFont = fWorkbook.createFont();
        File file = new File("Income_report ("+from+" - "+to+ ").xls");         

        HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
        sheetTitleFont.setBoldweight(HSSFFont.U_DOUBLE);
        //sheetTitleFont.setColor();
        TableModel model = jTable1.getModel();

        TableColumnModel model1 = jTable1.getTableHeader().getColumnModel();
        HSSFRow fRow1 = fSheet.createRow((short) 0);
        for (int i = 0; i < model1.getColumnCount(); i++){
            HSSFCell Hcell = fRow1.createCell((short) i);
            Hcell.setCellValue(model1.getColumn(i).getHeaderValue().toString());           

}
        for (int i = 0; i < model.getRowCount(); i++) {
            HSSFRow fRow = fSheet.createRow((short) i+1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                HSSFCell cell = fRow.createCell((short) j);
                cell.setCellValue(model.getValueAt(i, j).toString());
                cell.setCellStyle(cellStyle);
            }
        }
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path+file);
        try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
            fWorkbook.write(bos);
            JOptionPane.showMessageDialog(null, "Excel File Downloaded..!!");
        }
        fileOutputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
             jXDatePicker5.setDate(null);
            jXDatePicker6.setDate(null);           
            jLabel_total.setText("");
            jLabel_net.setText("");
            jLabel_gross.setText("");
            jLabel_vat.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         String path = "";
        JFileChooser j1 = new JFileChooser();
        j1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j1.showSaveDialog(this);

        if(x==JFileChooser.APPROVE_OPTION)
        {
            path = j1.getSelectedFile().getPath();
        }
         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String from = sdf.format(jXDatePicker7.getDate());
        String to = sdf.format(jXDatePicker8.getDate());
    
        try {
        HSSFWorkbook fWorkbook = new HSSFWorkbook();
        HSSFSheet fSheet;
        fSheet = fWorkbook.createSheet("new Sheet");
        HSSFFont sheetTitleFont = fWorkbook.createFont();
        File file = new File("Credit_report ("+from+" - "+to+ ").xls");         

        HSSFCellStyle cellStyle = fWorkbook.createCellStyle();
        sheetTitleFont.setBoldweight(HSSFFont.U_DOUBLE);
        //sheetTitleFont.setColor();
        TableModel model = jTable4.getModel();

        TableColumnModel model1 = jTable4.getTableHeader().getColumnModel();
        HSSFRow fRow1 = fSheet.createRow((short) 0);
        for (int i = 0; i < model1.getColumnCount(); i++){
            HSSFCell Hcell = fRow1.createCell((short) i);
            Hcell.setCellValue(model1.getColumn(i).getHeaderValue().toString());           

}
        for (int i = 0; i < model.getRowCount(); i++) {
            HSSFRow fRow = fSheet.createRow((short) i+1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                HSSFCell cell = fRow.createCell((short) j);
                cell.setCellValue(model.getValueAt(i, j).toString());
                cell.setCellStyle(cellStyle);
            }
        }
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(path+file);
        try (BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)) {
            fWorkbook.write(bos);
            JOptionPane.showMessageDialog(null, "Excel File Downloaded..!!");
        }
        fileOutputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_jButton7ActionPerformed
   
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
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amt_display;
    private javax.swing.JLabel amt_display1;
    private javax.swing.JLabel claimTax;
    private javax.swing.JLabel claimTax1;
    private javax.swing.JLabel claim_tax;
    private javax.swing.JLabel claim_tax1;
    private javax.swing.JPanel heading;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox_name;
    private javax.swing.JComboBox<String> jComboBox_name1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Bbalance;
    private javax.swing.JLabel jLabel_Bexpnec;
    private javax.swing.JLabel jLabel_Bincome;
    private javax.swing.JLabel jLabel_Bpurchase;
    private javax.swing.JLabel jLabel_dashboard3;
    private javax.swing.JLabel jLabel_discount;
    private javax.swing.JLabel jLabel_discount1;
    private javax.swing.JLabel jLabel_gross;
    private javax.swing.JLabel jLabel_net;
    private javax.swing.JLabel jLabel_purchase3;
    private javax.swing.JLabel jLabel_purchase4;
    private javax.swing.JLabel jLabel_purchase5;
    private javax.swing.JLabel jLabel_purchase6;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JLabel jLabel_vat;
    private javax.swing.JLabel jLabel_vendor4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker5;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker7;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker8;
    private javax.swing.JPanel slide3;
    private javax.swing.JLabel supplier_tot;
    private javax.swing.JLabel supplier_tot1;
    private javax.swing.JLabel vat_display;
    private javax.swing.JLabel vat_display1;
    // End of variables declaration//GEN-END:variables
}
