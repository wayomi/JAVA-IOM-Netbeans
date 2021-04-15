/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author wayomi
 */
public class Home extends javax.swing.JFrame {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    
    public static Double income;
    public static Double expn;
    public static Double purchase;
    public static Double Rincome;
    public static Double Rexpn;
    public static Double Rpurchase;
    public static Double inCash1;
    
    public static Double crCash = 0.00;
    public static Double crCash2 = 0.00;
    public static Double cashBal = 0.00;
    
    public static Double card1In = 0.00;
    public static Double card2 = 0.00;
    public static Double cardBal = 0.00; 
    
    
    public static Double PurCash = 0.00;
    public static Double PurCard = 0.00;
    public static Double PurTopay = 0.00;
    public static Double PurOther = 0.00;
    
    public static Double ExpCash = 0.00;
    public static Double ExpCard = 0.00;
    public static Double ExpTopay = 0.00;
     public static Double ExpOther = 0.00;
    
    public static Double BalCash = 0.00;
    public static Double BalCard = 0.00;
    public static Double BalTopay = 0.00; 
    
    public static  Double IncCredit = 0.00;
    public static  Double PurCredit = 0.00;
    public static  Double ExpCredit = 0.00;        
    
    ////Balance on View button/////////
    
    public static Double Vcash1 = 0.00;
    public static Double Vcash2 = 0.00;
    public static Double Vcash3 = 0.00;
    
    public static Double Vcard1 = 0.00;
    public static Double Vcard2 = 0.00;
    public static Double Vcard3 = 0.00;
    
    public static Double VPurCash = 0.00;
    public static Double VExpCash = 0.00;
    public static Double VCash = 0.00;
    
    public static Double VPurCard = 0.00;
    public static Double VExpCard = 0.00;
    public static Double VCard = 0.00;
    
     public static Double VPurOther = 0.00;
      public static Double VExpOther = 0.00;
    
    public static Double VPurCredit = 0.00;
    public static Double VExpCredit = 0.00;
    public static Double VCredit = 0.00;
    
     public static  Double VBalCash = 0.00;
     public static  Double VBalCard = 0.00;
     public static  Double VBalCredit = 0.00;
     
    public static Double VInCredit=0.00;
    
    public static Double Incre = 0.0;
     
    public Home() {
        initComponents();
         this.setLocationRelativeTo(this);
         con = DBConnect.connect();
         
         this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
         jLabel_incomeCash.setText("£ 0.00");
         jLabel_incomeBank.setText("£ 0.00");
         jLabel_incomeCredit.setText("£ 0.00");
         jLabel_balace.setText("£ 0.00");
         jLabel_purCash.setText("£ 0.00");
         jLabel_purBank.setText("£ 0.00");
         jLabel_purToPay.setText("£ 0.00");
         jLabel_ExpCash.setText("£ 0.00");
         jLabel_ExpBank.setText("£ 0.00");
         jLabel_ExpToPay.setText("£ 0.00");  
         jLabel_purOther.setText("£ 0.00");
         jLabel_ExpOther.setText("£ 0.00");
         balance();
                
    }
    
    private  void balance()
    {
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase";
            PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                purchase = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences";
            PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                expn = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {
           //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(total) AS 'pay' FROM income";
            PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                income = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        double balance = income - (purchase+expn);
        String BalS = String.format("%.2f", balance);
        jLabel_balace.setText(" £ "+BalS);
    }
     private  void balance_range()
    {                       
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase Where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);           
                String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);         
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Rpurchase = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences Where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);           
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Rexpn = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(total) AS 'pay' FROM income Where (date between ? and ?)";
            PreparedStatement pst = con.prepareStatement(sql);           
             
            String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Rincome = Double.parseDouble(tt);               
                
            }  
            
        } 
        catch (Exception e) {//JOptionPane.showMessageDialog(null, e);
        }
        
        double balance = Rincome - Rpurchase- Rexpn;
        String BalS = String.format("%.2f", balance);
        jLabel_balace.setText(" £ "+BalS);
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
        slide = new javax.swing.JPanel();
        jLabel_dashboard = new javax.swing.JLabel();
        jLabel_Income = new javax.swing.JLabel();
        jLabel_outgoing = new javax.swing.JLabel();
        jLabel_reports = new javax.swing.JLabel();
        jLabel_staff = new javax.swing.JLabel();
        jLabel_vendor = new javax.swing.JLabel();
        jLabel_vendor1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel_incomeCash = new javax.swing.JLabel();
        jLabel_incomeBank = new javax.swing.JLabel();
        jLabel_incomeCredit = new javax.swing.JLabel();
        jLabel_purCash = new javax.swing.JLabel();
        jLabel_purBank = new javax.swing.JLabel();
        jLabel_purToPay = new javax.swing.JLabel();
        jLabel_ExpCash = new javax.swing.JLabel();
        jLabel_ExpBank = new javax.swing.JLabel();
        jLabel_ExpToPay = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel_balace = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel_BCash = new javax.swing.JLabel();
        jLabel_BBank = new javax.swing.JLabel();
        jLabel_BToPay = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel_purOther = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel_ExpOther = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1172, 745));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        heading.setBackground(new java.awt.Color(51, 51, 51));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("IOM - Income~Outgoing Manager - Dashboard");

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

        jLabel_dashboard.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_dashboard.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_dashboard.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_dashboard.setText("Dashboard");
        jLabel_dashboard.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_Income.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_Income.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_Income.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_Income.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Income.setText("Income");
        jLabel_Income.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_IncomeMouseClicked(evt);
            }
        });

        jLabel_outgoing.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_outgoing.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_outgoing.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_outgoing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_outgoing.setText("Outgoing");
        jLabel_outgoing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_outgoingMouseClicked(evt);
            }
        });

        jLabel_reports.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_reports.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_reports.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_reports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_reports.setText("Reports");
        jLabel_reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_reportsMouseClicked(evt);
            }
        });

        jLabel_staff.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_staff.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_staff.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_staff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_staff.setText("Staff");
        jLabel_staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_staffMouseClicked(evt);
            }
        });

        jLabel_vendor.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_vendor.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel_vendor.setForeground(new java.awt.Color(0, 51, 51));
        jLabel_vendor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_vendor.setText("New Vendor");
        jLabel_vendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_vendorMouseClicked(evt);
            }
        });

        jLabel_vendor1.setBackground(new java.awt.Color(204, 255, 204));
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
            .addComponent(jLabel_Income, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_outgoing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_reports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_staff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_vendor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_vendor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slideLayout.createSequentialGroup()
                .addComponent(jLabel_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Income, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_outgoing, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_reports, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_staff, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_vendor1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 51));
        jLabel8.setText("INCOME");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 51));
        jLabel9.setText("OUTGOING");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 51));
        jLabel10.setText("BALANCE");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setText("CASH");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 204));
        jLabel12.setText("CARD");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 204));
        jLabel13.setText("CREDIT");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 153));
        jLabel14.setText("Purchase");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 153));
        jLabel15.setText("Expences");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 204, 51));
        jLabel16.setText("CASH");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 204, 51));
        jLabel17.setText("BANK");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 204, 51));
        jLabel18.setText("TO PAY");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 102));
        jLabel19.setText("CASH");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 102));
        jLabel20.setText("BANK");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 102));
        jLabel21.setText("TO PAY");

        jLabel_incomeCash.setBackground(new java.awt.Color(204, 255, 204));
        jLabel_incomeCash.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_incomeCash.setForeground(new java.awt.Color(0, 204, 204));

        jLabel_incomeBank.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_incomeBank.setForeground(new java.awt.Color(0, 204, 204));

        jLabel_incomeCredit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_incomeCredit.setForeground(new java.awt.Color(0, 204, 204));

        jLabel_purCash.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_purCash.setForeground(new java.awt.Color(0, 204, 51));

        jLabel_purBank.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_purBank.setForeground(new java.awt.Color(0, 204, 51));

        jLabel_purToPay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_purToPay.setForeground(new java.awt.Color(0, 204, 51));

        jLabel_ExpCash.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_ExpCash.setForeground(new java.awt.Color(255, 0, 102));

        jLabel_ExpBank.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_ExpBank.setForeground(new java.awt.Color(255, 0, 102));

        jLabel_ExpToPay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_ExpToPay.setForeground(new java.awt.Color(255, 0, 102));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-view-20.png"))); // NOI18N
        jButton1.setText("SUMMARY");
        jButton1.setIconTextGap(16);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel_balace.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_balace.setForeground(new java.awt.Color(0, 0, 255));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 255));
        jLabel24.setText("CASH");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 255));
        jLabel25.setText("BANK");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 255));
        jLabel26.setText("TO PAY");

        jLabel_BCash.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_BCash.setForeground(new java.awt.Color(0, 0, 255));

        jLabel_BBank.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_BBank.setForeground(new java.awt.Color(0, 0, 255));

        jLabel_BToPay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_BToPay.setForeground(new java.awt.Color(0, 0, 255));

        jLabel89.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 204, 51));
        jLabel89.setText("OTHER");

        jLabel_purOther.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_purOther.setForeground(new java.awt.Color(0, 204, 51));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 102));
        jLabel27.setText("OTHER");

        jLabel_ExpOther.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_ExpOther.setForeground(new java.awt.Color(255, 0, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel_incomeCash, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel16)
                                    .addGap(34, 34, 34)
                                    .addComponent(jLabel_purCash, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(69, 69, 69)
                                    .addComponent(jLabel19)
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel_ExpCash, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(72, 72, 72)
                                    .addComponent(jLabel24)
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel_BCash, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(203, 203, 203)
                                            .addComponent(jLabel9))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(187, 187, 187)
                                            .addComponent(jLabel14)
                                            .addGap(164, 164, 164)
                                            .addComponent(jLabel15)))
                                    .addGap(159, 159, 159)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel_balace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(44, 44, 44))
                                        .addComponent(jLabel10))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel_incomeBank, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel17)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel_purBank, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_incomeCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel89)
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel_purOther, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel_purToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(49, 49, 49)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel_ExpOther, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel_ExpToPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel_ExpBank, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel_BBank, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel_BToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_balace, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel_incomeCash, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel_purCash, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel24)))
                        .addComponent(jLabel_BCash, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel_ExpCash, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel_incomeBank, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel20))
                            .addComponent(jLabel_ExpBank, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel25))
                            .addComponent(jLabel_BBank, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel_purBank, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel_incomeCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel_purToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_ExpToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_BToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel26)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(jLabel_purOther, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabel_ExpOther, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Invoice Number", "Name", "Amount", "Date (Payment)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(102, 102, 255));
        jTable1.setMaximumSize(new java.awt.Dimension(100, 64));
        jTable1.setPreferredSize(new java.awt.Dimension(700, 400));
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 480, 260));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Invoice Number", "Name", "Amount", "Date (Payment)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(102, 102, 255));
        jTable2.setMaximumSize(new java.awt.Dimension(100, 64));
        jTable2.setPreferredSize(new java.awt.Dimension(700, 400));
        jTable2.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 490, 260));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 83, 90));
        jLabel22.setText("Purchases Due Today");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 83, 90));
        jLabel23.setText("Expences Due Today ");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_IncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_IncomeMouseClicked
        Income i = new Income();
        i.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_IncomeMouseClicked

    private void jLabel_outgoingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_outgoingMouseClicked
        Outgoing o = new Outgoing();
        o.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_outgoingMouseClicked

    private void jLabel_reportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_reportsMouseClicked
        Reports r = new Reports();
        r.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_reportsMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
          try {
            String sql = "SELECT InvoiceNo AS 'Invoice Number', Name, Total AS 'Total (£)', "
                    + "paidDate AS '1st Installment',(Total - insAmount) As '1st Payment', insDate AS '2nd Installment',insAmount AS '2nd Payment' "
                    + "FROM purchase WHERE (paidDate =? OR insDate = ?) and method ='NOT PAID' ";
            pst = con.prepareStatement(sql);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
           
            pst.setString(1, dtf.format(now)); 
            pst.setString(2, dtf.format(now));    
                          
            rs = pst.executeQuery(); 
            JTableHeader head = jTable2.getTableHeader();
            head.setForeground(Color.blue);            
            jTable2.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        
         try {
            String sql = "SELECT InvoiceNo AS 'Invoice Number', Name, Total AS 'Total (£)', "
                    + "paidDate AS '1st Installment',(Total - insAmount) As '1st Payment', insDate AS '2nd Installment',insAmount AS '2nd Payment'"
                    + "FROM expences WHERE paidDate =? OR insDate = ? and method ='NOT PAID' ";
            pst = con.prepareStatement(sql);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDateTime now = LocalDateTime.now();
           
             pst.setString(1, dtf.format(now)); 
             pst.setString(2, dtf.format(now)); 
                         
            rs = pst.executeQuery();
            JTableHeader head = jTable1.getTableHeader();
            head.setForeground(Color.blue);            
            jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(cash) AS 'pay' FROM income";
             PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                inCash1 = Double.parseDouble(tt);                
            }       
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null,e);
        }
        try {
            String sql = "SELECT SUM(cash) AS 'pay' FROM credit";
                  
             PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                crCash = Double.parseDouble(tt);                
            }       
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null,e);
        }
                
         cashBal= inCash1 + crCash ;
        String v1 = String.format("%.2f", cashBal);
        jLabel_incomeCash.setText(" £ "+v1); 
        ////////////*******************/////////////////
        try {
            String sql = "SELECT SUM(bank) AS 'pay' FROM income";
             PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                card1In = Double.parseDouble(tt);                
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(card) AS 'pay' FROM credit";
             PreparedStatement pst = con.prepareStatement(sql);           
                        
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                card2 = Double.parseDouble(tt);
                
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        
        cardBal = card1In + card2;
        String v2= String.format("%.2f", cardBal);
        jLabel_incomeBank.setText(" £ "+v2); 
        ////////////*******************/////////////////
        try {
            String sql = "SELECT SUM(balance) AS 'pay' FROM credit";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                IncCredit = Double.parseDouble(tt);
                String value2 = String.format("%.2f", IncCredit);
                jLabel_incomeCredit.setText(" £"+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        ////////////*******************/////////////////
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where Method ='CASH'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                PurCash = Double.parseDouble(tt);
                String value2 = String.format("%.2f", PurCash);
                jLabel_purCash.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        ////////////*******************/////////////////
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where Method ='BANK'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                PurCard = Double.parseDouble(tt);
                String value2 = String.format("%.2f", PurCard);
                jLabel_purBank.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }
        ////////////*******************/////////////////
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where Method ='NOT PAID'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                PurTopay = Double.parseDouble(tt);
                String value2 = String.format("%.2f", PurTopay);
                jLabel_purToPay.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where Method ='OTHER'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                PurOther = Double.parseDouble(tt);
                String value2 = String.format("%.2f", PurOther);
                jLabel_purOther.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        ///////////////Expences//////////////////
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where Method ='CASH'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                ExpCash = Double.parseDouble(tt);
                String value2 = String.format("%.2f", ExpCash);
                jLabel_ExpCash.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where Method ='BANK'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                ExpCard = Double.parseDouble(tt);
                String value2 = String.format("%.2f", ExpCard);
                jLabel_ExpBank.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where Method ='NOT PAID'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                ExpTopay = Double.parseDouble(tt);
                String value2 = String.format("%.2f", ExpTopay);
                jLabel_ExpToPay.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where Method ='OTHER'";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                ExpOther = Double.parseDouble(tt);
                String value2 = String.format("%.2f", ExpOther);
                jLabel_ExpOther.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        /////////****** Balance CASH ********///////////
        try {
            String sql = "SELECT SUM(credit) AS 'pay' FROM income";
             PreparedStatement pst = con.prepareStatement(sql);           
                       
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Incre = Double.parseDouble(tt);
                
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        
        BalCash = cashBal - (PurCash + ExpCash) ;
        String val1 = String.format("%.2f", BalCash);
        jLabel_BCash.setText(" £ "+val1); 
        
        BalCard = cardBal - (PurCard + ExpCard) ;
        String val2 = String.format("%.2f", BalCard);
        jLabel_BBank.setText(" £ "+val2); 
        
        BalTopay = Incre - (PurTopay + ExpTopay) ;
        String val3 = String.format("%.2f", BalTopay);
        jLabel_BToPay.setText(" £ "+val3);     
                
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jLabel_ExpBank.setText("");
        jLabel_ExpCash.setText("");
        jLabel_ExpToPay.setText("");
        jLabel_incomeBank.setText("");
        jLabel_incomeCash.setText("");
        jLabel_incomeCredit.setText("");
        jLabel_purBank.setText("");
        jLabel_purCash.setText("");
        jLabel_purToPay.setText("");
        jLabel_balace.setText("");
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null, e);
        }
               
        
        try {
            String sql = "SELECT SUM(cash) AS 'pay' FROM income where (date between ? and ?)";
             PreparedStatement pst = con.prepareStatement(sql);           
            
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Vcash1 = Double.parseDouble(tt);
                //String value2 = String.format("%.2f", value1);
                //jLabel_incomeCash.setText(" £"+value2);  
            }       
        } 
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(cash) AS 'pay' FROM credit where (date between ? and ?)";
                  
             PreparedStatement pst = con.prepareStatement(sql);           
                String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
                     
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Vcash2 = Double.parseDouble(tt);                
            }       
        } 
        catch (Exception e) {
            
            //JOptionPane.showMessageDialog(null,e);
        }
                
         Vcash3= Vcash1 + Vcash2 ;
        String v1 = String.format("%.2f", Vcash3);
        jLabel_incomeCash.setText(" £ "+v1); 
        
        ////////*********** Button CASH VIEW*********//////////////////
         try {
            String sql = "SELECT SUM(bank) AS 'pay' FROM income where (date between ? and ?)";
             PreparedStatement pst = con.prepareStatement(sql);           
            
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Vcard1 = Double.parseDouble(tt);
                //String value2 = String.format("%.2f", value1);
                //jLabel_incomeBank.setText(" £"+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
         try {
            String sql = "SELECT SUM(card) AS 'pay' FROM credit where (date between ? and ?)";
             PreparedStatement pst = con.prepareStatement(sql);           
              String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);           
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                Vcard2 = Double.parseDouble(tt);
                
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        
        Vcard3 = Vcard1 + Vcard2;
        String v2= String.format("%.2f", Vcard3);
        jLabel_incomeBank.setText(" £ "+v2); 
        ///////////******************////////////////////////////////
        try {
            String sql = "SELECT SUM(balance) AS 'pay' FROM credit where (date between ? and ?)";
             PreparedStatement pst = con.prepareStatement(sql);           
              String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to);          
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VInCredit = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VInCredit);
                jLabel_incomeCredit.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
          ////////////////////******************/////////////////////////
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where(date between ? and ?) and Method ='CASH'";
             PreparedStatement pst = con.prepareStatement(sql);           
                   
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VPurCash = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VPurCash);
                jLabel_purCash.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where (date between ? and ?) and Method ='BANK'";
             PreparedStatement pst = con.prepareStatement(sql);           
             
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VPurCard = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VPurCard);
                jLabel_purBank.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where (date between ? and ?) and Method ='NOT PAID'";
             PreparedStatement pst = con.prepareStatement(sql);           
              
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VPurCredit = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VPurCredit);
                jLabel_purToPay.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM purchase where (date between ? and ?) and Method ='OTHER'";
             PreparedStatement pst = con.prepareStatement(sql);           
              
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VPurOther = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VPurOther);
                jLabel_purOther.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where (date between ? and ?) and Method ='CASH'";
             PreparedStatement pst = con.prepareStatement(sql);           
             
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VExpCash = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VExpCash);
                jLabel_ExpCash.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
         //   JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where (date between ? and ?) and Method ='BANK'";
             PreparedStatement pst = con.prepareStatement(sql);           
             
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VExpCard = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VExpCard);
                jLabel_ExpBank.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where (date between ? and ?) and Method ='NOT PAID'";
             PreparedStatement pst = con.prepareStatement(sql);           
              
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VExpCredit = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VExpCredit);
                jLabel_ExpToPay.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
           //JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql = "SELECT SUM(Total) AS 'pay' FROM expences where (date between ? and ?) and Method ='OTHER'";
             PreparedStatement pst = con.prepareStatement(sql);           
             
             String from = sdf.format(jDateChooser1.getDate());
             pst.setString(1,from);

            String to = sdf.format(jDateChooser2.getDate());
            pst.setString(2,to); 
            
             ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String tt = rs.getString("pay");
                VExpOther = Double.parseDouble(tt);
                String value2 = String.format("%.2f", VExpOther);
                jLabel_ExpOther.setText(" £ "+value2);  
            }       
        } 
        catch (Exception e) {
         //   JOptionPane.showMessageDialog(null, e);
        }
        balance_range();
        
        VBalCash = Vcash3 - (VPurCash + VExpCash) ;
        String val1 = String.format("%.2f", VBalCash);
        jLabel_BCash.setText(" £ "+val1);  
        
        VBalCard = Vcard3 - (VPurCard + VExpCard) ;
        String val2 = String.format("%.2f", VBalCard);
        jLabel_BBank.setText(" £ "+val2); 
        
        VBalCredit = VInCredit - (VPurCredit + VExpCredit) ;
        String val3 = String.format("%.2f", VBalCredit);
        jLabel_BToPay.setText(" £ "+val3); 
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel_vendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_vendorMouseClicked
        vendor v = new vendor();
        v.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_vendorMouseClicked

    private void jLabel_staffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_staffMouseClicked
        newVendor v = new newVendor();
        v.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_staffMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       
    }//GEN-LAST:event_formWindowOpened

    private void jLabel_vendor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_vendor1MouseClicked
        credit c = new credit();
        c.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel_vendor1MouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel heading;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_BBank;
    private javax.swing.JLabel jLabel_BCash;
    private javax.swing.JLabel jLabel_BToPay;
    private javax.swing.JLabel jLabel_ExpBank;
    private javax.swing.JLabel jLabel_ExpCash;
    private javax.swing.JLabel jLabel_ExpOther;
    private javax.swing.JLabel jLabel_ExpToPay;
    private javax.swing.JLabel jLabel_Income;
    private javax.swing.JLabel jLabel_balace;
    private javax.swing.JLabel jLabel_dashboard;
    private javax.swing.JLabel jLabel_incomeBank;
    private javax.swing.JLabel jLabel_incomeCash;
    private javax.swing.JLabel jLabel_incomeCredit;
    private javax.swing.JLabel jLabel_outgoing;
    private javax.swing.JLabel jLabel_purBank;
    private javax.swing.JLabel jLabel_purCash;
    private javax.swing.JLabel jLabel_purOther;
    private javax.swing.JLabel jLabel_purToPay;
    private javax.swing.JLabel jLabel_reports;
    private javax.swing.JLabel jLabel_staff;
    private javax.swing.JLabel jLabel_vendor;
    private javax.swing.JLabel jLabel_vendor1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel slide;
    // End of variables declaration//GEN-END:variables
}
