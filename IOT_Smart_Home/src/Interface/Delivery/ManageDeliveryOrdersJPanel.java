/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Delivery;

import Business.Customer.Customer;
import Business.Customer.Product;
import Business.Ecosystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organisation.DeliveryOrganisation;
import Business.Organisation.Grocery.GroceryOrganisation;
import Business.Organisation.Organisation;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import Business.Utility.Wallet;
import Business.WorkQueue.DeliverWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ajaygoel
 */
public class ManageDeliveryOrdersJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageDeliveryOrdersJPanel
     */
    JPanel userProcessContainer;
    UserAccount account;
    Enterprise enterprise;
    Ecosystem business;
    Organisation organisation;
    DeliveryOrganisation d;
    Customer c1;
    Date date1 = new Date();
    GroceryOrganisation goo;
    double sales=0.0; 

    ManageDeliveryOrdersJPanel(JPanel userProcessContainer, UserAccount account, Organisation organisation, Enterprise enterprise, Ecosystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.business = business;
        this.enterprise = enterprise;
        this.organisation = organisation;

        for (Network n : business.getNetworkDirectory()) {
            for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organisation o : e.getOrganisationDirectory().getOrganizationlist()) {
                    {
                        if (o instanceof DeliveryOrganisation) {
                            d = (DeliveryOrganisation) o;
                        }
                        if (o instanceof GroceryOrganisation) {
                            goo = (GroceryOrganisation) o;
                        }
                    }
                }
            }
        }

        populate_table();
        populate_combo_box();
        populate_table2();
        Wallet wallet = d.getWalletObj();
                    SalestxtField.setText(String.valueOf(wallet.getWalletAmount()));

    }

    public void populate_table() {
                
       DefaultTableModel model = (DefaultTableModel) orderListJTbl.getModel();
        model.setRowCount(0);
        for (WorkRequest wr : d.getWorkQueue().getReqList()) {
                Object[] row = new Object[6];
                row[0]=wr.getCust();
                row[1]=wr.getStatus();
                row[2]=wr.getProduct();
                row[3]=wr;
                row[4]=wr.getReceiver();
                row[5]=wr.getResolveDate();
                model.addRow(row);
                
//                if(wr.getStatus().equals("Delivered"))
//                {
//                    sales=sales+wr.getProduct().getPrice()*0.1;
//                    
//                }
        }
        
        

    }

    public void populate_combo_box() {
        for (Employee e : d.getEmpList().getEmployeeDirectory()) {
            System.out.println(e);
            System.out.println(e.getRole());
            if (e.getRole().toString().equals(Role.RoleType.DeliveryMan.getValue())) {
                EmployeeComboBox.addItem(e);
            }

        }
    }

    public void populate_table2() {

        DefaultTableModel dtm = (DefaultTableModel) WorkQueueDelJTable.getModel();
        dtm.setRowCount(0);

        for (WorkRequest wr : d.getWorkQueue().getReqList()) {
            
            System.out.println(wr);
            Object Row[] = new Object[5];
            Row[0] = wr.getCust();
            Row[1] = wr.getCust().getHouse_no();
            Row[2] = wr.getStatus();
            Row[3] = wr;
            Row[4] = wr.getReceiver();
            dtm.addRow(Row);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        deliverJBtn = new javax.swing.JButton();
        cancelOrderJBtn = new javax.swing.JButton();
        SalestxtField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EmployeeComboBox = new javax.swing.JComboBox();
        AssigntoBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        WorkQueueDelJTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderListJTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Order Details");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 218, -1, -1));

        deliverJBtn.setText("Deliver Order");
        deliverJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliverJBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deliverJBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 218, -1, -1));

        cancelOrderJBtn.setText("Cancel Order");
        cancelOrderJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOrderJBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelOrderJBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 218, 159, -1));
        jPanel1.add(SalestxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 325, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Total Sales :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Manage Delivery Orders:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 0, -1, -1));

        jPanel1.add(EmployeeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 466, 239, -1));

        AssigntoBtn.setText("Assign to:");
        AssigntoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssigntoBtnActionPerformed(evt);
            }
        });
        jPanel1.add(AssigntoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 465, 304, -1));

        WorkQueueDelJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Detail", "House No", "Status", "Request Date", "Assigned To"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(WorkQueueDelJTable);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, 929, 126));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Work Queue:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 282, -1, -1));

        orderListJTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Detail", "Status", "Product", "Request Date", "Receiver", "Resolve Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderListJTbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 68, 929, 126));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/special1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 10, 1680, 870));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed

    private void deliverJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliverJBtnActionPerformed
        // TODO add your handling code here:

        int row = orderListJTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        
        WorkRequest wr1 = (WorkRequest) orderListJTbl.getValueAt(row,3);
        
               if(wr1.getStatus().equals("Delivered")){
             JOptionPane.showMessageDialog(null, "This request has already been closed", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
               
        wr1.setMessage("Delivered");
        wr1.setReceiver(account);
        Date date = new Date();
        wr1.setResolveDate(date);
        wr1.setStatus("Delivered");
        wr1.getCust().getNotification().addNotification(wr1);
        
        
        
        
          Customer c1 = (Customer) orderListJTbl.getValueAt(row, 0);
        for (Product p1 : c1.getProdList()) {
            if (p1 != null) {
                //if (p1.getName().equals(prod.getName())) {
                if (p1 ==wr1.getProduct()) {
                    if (p1.getOrder_status().equals("Pending to be delivered")||p1.getOrder_status().equals("Automatic sent to Delivery")) {
                        p1.setOrder_status("Delivered");
                        p1.setQuantity_left(p1.getQuantity_left() + p1.getProductQuant());

                       JOptionPane.showMessageDialog(null, "Product Delivered", "Information", JOptionPane.INFORMATION_MESSAGE);
                        populate_table();
                        populate_table2();

                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");
                        Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("aedproject2018@gmail.com", "aedProject2");
                            }
                        }
                        );

                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("aedproject2018@gmail.com"));//ajaygoel1993@gmail.com,yadav.ank@husky.neu.edu,solanki.h@husky.neu.edu"
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c1.getE_mail()));
                            message.setSubject("IOT SMART HOME");
                            message.setText("Your product " + p1.getProdName() + " has been delivered on " + date1);
                            Transport.send(message);

                            JOptionPane.showMessageDialog(null, "message sent");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        
                        double amountTOAdd=0;
                        amountTOAdd = Double.valueOf(wr1.getProduct().getPrice());
                        d.addMoney(amountTOAdd*0.1);
                        
                        Wallet wallet = d.getWalletObj();
                        SalestxtField.setText(String.valueOf(wallet.getWalletAmount()));
                        
                        JOptionPane.showMessageDialog(null, "Product delivered", "Information", JOptionPane.INFORMATION_MESSAGE);
                        populate_table();
                    } else if (p1.getOrder_status().equals("Delivered")) {
                        JOptionPane.showMessageDialog(null, "This product is already delivered", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (p1.getOrder_status().equals("Cancelled")) {
                        JOptionPane.showMessageDialog(null, "This product is cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                }
            }

        }
        
//        
//        
//        
//        
//        Product prod = (Product) orderListJTbl.getValueAt(row, 1);
//        //String first_name = (String) orderListJTbl.getValueAt(row, 0);
//        Customer custom = (Customer) orderListJTbl.getValueAt(row, 0);
//
//        for (Network n : business.getNetworkDirectory()) {
//            for (Customer cus : n.getCustomerDirectory().getCustList()) {
//                if (cus.getUserAcc().getUserName().equals(custom.getUserAcc().getUserName())) {
//                    c1 = cus;
//                    break;
//                }
//            }
//        }
//
//        for (Product p1 : c1.getProdList()) {
//            if (p1 != null) {
//                //if (p1.getName().equals(prod.getName())) {
//                if (p1 == prod) {
//                    if (p1.getOrder_status().equals("Pending to be delivered") || p1.getOrder_status().equals("Automatically generated at delivery ") || p1.getOrder_status().equals("Pending at delivery employee")
//                            || p1.getOrder_status().equals("Automatically generated at delivery ")) {
//
//                        for (WorkRequest wr2 : c1.getWorkList().getReqList()) {
//                                for (WorkRequest wr3 : goo.getWorkQueue().getReqList()) {
//                                    
//                        
//                            ////
//                            
//                                    if ((wr2 == wr3) && (wr3.getStatus() != "Delivered")) {
//                                        for (WorkRequest wr : d.getWorkQueue().getReqList()) {
//                                        ////
//                                        if (wr.getCust() == (c1) && p1 == (wr.getProduct())&&(wr.getStatus() != "Delivered")) {
//                                            wr.setStatus("Delivered");
//                                            wr.setReceiver(account);
//                                            Date date = new Date();
//                                            wr.setResolveDate(date);
//                                            date1 = date;
//                                            p1.setOrder_status("Delivered");
//                                            p1.setAvail(p1.getAvail() + p1.getProductQuant());
//                                            p1.setQuantity_left(p1.getQuantity_left() + p1.getProductQuant());
//
////                                for(WorkRequest wr2:c1.getWorkList().getReqList())
////                                {
////                                    if(wr2==wr)
////                                    {
//                                            //wr2.setStatus("Delivered");
//                                            //wr2.setReceiver(account);
//                                            //wr2.setResolveDate(date);
//                                            
//                                            wr3.setStatus("Delivered");
//                                            wr3.setReceiver(account);
//                                            wr3.setResolveDate(date);
//                                            break;
//                                        }
//                                    }
//
//                                }
//
//                            }
//                        }
//                        JOptionPane.showMessageDialog(null, "Product Delivered", "Information", JOptionPane.INFORMATION_MESSAGE);
//                        populate_table();
//
//                        Properties props = new Properties();
//                        props.put("mail.smtp.host", "smtp.gmail.com");
//                        props.put("mail.smtp.socketFactory.port", "465");
//                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//                        props.put("mail.smtp.auth", "true");
//                        props.put("mail.smtp.port", "465");
//                        Session session = Session.getDefaultInstance(props,
//                                new javax.mail.Authenticator() {
//                            protected PasswordAuthentication getPasswordAuthentication() {
//                                return new PasswordAuthentication("aedproject2018@gmail.com", "aedProject2");
//                            }
//                        }
//                        );
//
//                        try {
//                            Message message = new MimeMessage(session);
//                            message.setFrom(new InternetAddress("aedproject2018@gmail.com"));//ajaygoel1993@gmail.com,yadav.ank@husky.neu.edu,solanki.h@husky.neu.edu"
//                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c1.getE_mail()));
//                            message.setSubject("IOT SMART HOME");
//                            message.setText("Your product " + p1.getProdName() + " has been delivered on " + date1);
//                            Transport.send(message);
//
//                            JOptionPane.showMessageDialog(null, "message sent");
//                        } catch (Exception e) {
//                            JOptionPane.showMessageDialog(null, e);
//                        }
//
//                    }
//                }
////                    } else if (p1.getOrder_status().equals("Cancelled")) {
////                        JOptionPane.showMessageDialog(null, "This product is cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
////                        return;
////                    }
//
//            }
//        }

//        int row = orderListJTbl.getSelectedRow();
//        if (row < 0) {
//            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        Product p1 = (Product) orderListJTbl.getValueAt(row, 1);
//        Customer c1 = (Customer) orderListJTbl.getValueAt(row, 0);
//
//        if (p1.getOrder_status().equals("Pending at Grocery Market")) {
//            p1.setOrder_status("Pending for Delivery");
//            JOptionPane.showMessageDialog(null, "Product pending for delivery", "Information", JOptionPane.INFORMATION_MESSAGE);
//            populate_table();
//        } else if (p1.getOrder_status().equals("Cancelled")) {
//            JOptionPane.showMessageDialog(null, "This product is cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
    }//GEN-LAST:event_deliverJBtnActionPerformed

    private void cancelOrderJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOrderJBtnActionPerformed
        // TODO add your handling code here:

         int row = orderListJTbl.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        
        
        WorkRequest wr1 = (WorkRequest) orderListJTbl.getValueAt(row,3);
        
               if(wr1.getStatus().equals("Delivered")){
             JOptionPane.showMessageDialog(null, "This request has already been closed", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
               
               if(wr1.getStatus().equals("Automatic sent to Delivery"))
               {
                   JOptionPane.showMessageDialog(null, "This request is automatic.It cannot be cancelled", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
                   
               }
               
        wr1.setMessage("Cancelled");
        wr1.setReceiver(account);
        Date date = new Date();
        wr1.setResolveDate(date);
        wr1.setStatus("Cancelled");
        wr1.getCust().getNotification().addNotification(wr1);
        
        for(WorkRequest wr4:goo.getWorkQueue().getReqList())
        {
            if(wr4.equals(wr1))
            {
                double amountTOdel=0;
                        amountTOdel = Double.valueOf(wr1.getProduct().getPrice());
                        goo.addMoney(amountTOdel*0.1);
                        goo.debitMoney(wr1,amountTOdel);
                        d.debitMoney(wr1, amountTOdel*0.1);
            }
        }
        
        
        
          Customer c1 = (Customer) orderListJTbl.getValueAt(row, 0);
        for (Product p1 : c1.getProdList()) {
            if (p1 != null) {
                //if (p1.getName().equals(prod.getName())) {
                if (p1 ==wr1.getProduct()) {
                    if (//p1.getOrder_status().equals("Pending to be delivered")||p1.getOrder_status().equals("Automatically generated at grocery")
                        p1.getOrder_status().equalsIgnoreCase("Pending at delivery employee") || p1.getOrder_status().equals("Automatically generated at delivery ")
                        || p1.getOrder_status().equalsIgnoreCase("Pending to be delivered")||p1.getOrder_status().equals("Automatically generated at grocery")
                                    
                                    ) {
                        p1.setOrder_status("Cancelled");
                        
                        double amountTOdel=0;
                        amountTOdel = Double.valueOf(wr1.getProduct().getPrice());
                        wr1.getCust().addMoney(amountTOdel);
                        
                        

                       JOptionPane.showMessageDialog(null, "Product Cancelled", "Information", JOptionPane.INFORMATION_MESSAGE);
                        populate_table();

                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");
                        Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("aedproject2018@gmail.com", "aedProject2");
                            }
                        }
                        );

                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("aedproject2018@gmail.com"));//ajaygoel1993@gmail.com,yadav.ank@husky.neu.edu,solanki.h@husky.neu.edu"
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c1.getE_mail()));
                            message.setSubject("IOT SMART HOME");
                            message.setText("Your product " + p1.getProdName() + " has been cancelled on " + date1);
                            Transport.send(message);

                            JOptionPane.showMessageDialog(null, "message sent");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                        
                        
                        JOptionPane.showMessageDialog(null, "Product cancelled", "Information", JOptionPane.INFORMATION_MESSAGE);
                        populate_table();
                    } else if (p1.getOrder_status().equals("Delivered")) {
                        JOptionPane.showMessageDialog(null, "This product is already delivered", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (p1.getOrder_status().equals("Cancelled")) {
                        JOptionPane.showMessageDialog(null, "This product is already cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                }
            }

        }

    }//GEN-LAST:event_cancelOrderJBtnActionPerformed

    private void AssigntoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssigntoBtnActionPerformed
        // TODO add your handling code here:
        int row = WorkQueueDelJTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Employee emp = (Employee) EmployeeComboBox.getSelectedItem();
        WorkRequest wr = (WorkRequest) WorkQueueDelJTable.getValueAt(row, 3);

        if (wr.getStatus().equals("Delivered")) {
            JOptionPane.showMessageDialog(null, "The request is already completed", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (wr.getReceiver() == null) {
            emp.getWorkQueue().getReqList().add(wr);
            wr.setReceiver(emp.getEmpUserAcc());
            wr.setSender(account);
            JOptionPane.showMessageDialog(null, "The request has been successfully assigned", "Information", JOptionPane.INFORMATION_MESSAGE);

            populate_table2();

        } else if (wr.getReceiver().equals(emp.getEmpUserAcc())) {
            JOptionPane.showMessageDialog(null, "The request is already assigned to you", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {

            emp.getWorkQueue().getReqList().add(wr);
            System.out.println(emp.getEmpUserAcc().getUserName());
            wr.setReceiver(emp.getEmpUserAcc());
            wr.getCust().getNotification().addNotification(wr);
            wr.setSender(account);
            JOptionPane.showMessageDialog(null, "The request has been successfully assigned", "Information", JOptionPane.INFORMATION_MESSAGE);

            populate_table2();
        }

    }//GEN-LAST:event_AssigntoBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AssigntoBtn;
    private javax.swing.JComboBox EmployeeComboBox;
    private javax.swing.JTextField SalestxtField;
    private javax.swing.JTable WorkQueueDelJTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelOrderJBtn;
    private javax.swing.JButton deliverJBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable orderListJTbl;
    // End of variables declaration//GEN-END:variables
}