/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Delivery;

import Business.Ecosystem;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Organisation.DeliveryOrganisation;
import Business.Organisation.Organisation;
import Business.Role.Role;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ajaygoel
 */
public class ManageAccountsDeliveryJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageAccountsDeliveryJPanel
     */
    
    
    JPanel userProcessContainer;
    UserAccount account;
    Enterprise enterprise;
    Ecosystem business;
    Organisation organisation;
    DeliveryOrganisation deliveryOrganisation;
   
    ManageAccountsDeliveryJPanel(JPanel userProcessContainer, UserAccount account, Organisation organisation, Enterprise enterprise, Ecosystem business) {
        initComponents();
        this.userProcessContainer=userProcessContainer;
        this.account=account;
        this.business=business;
        this.enterprise=enterprise;
        this.organisation= organisation;
        
        populate_table();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StaffJTable = new javax.swing.JTable();
        BackJBtn = new javax.swing.JButton();
        CreateStaff = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Manage Staff Maintenance");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 11, -1, -1));

        StaffJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Role", "UserName", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(StaffJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 67, 748, 81));

        BackJBtn.setText("<< Back");
        BackJBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackJBtnActionPerformed(evt);
            }
        });
        add(BackJBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 181, -1, -1));

        CreateStaff.setText("Create Staff");
        CreateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateStaffActionPerformed(evt);
            }
        });
        add(CreateStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 181, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    public void populate_table()
    {
        DefaultTableModel model = (DefaultTableModel) StaffJTable.getModel();
        model.setRowCount(0);
        for(Organisation oo:enterprise.getOrganisationDirectory().getOrganizationlist())
        {
            if(oo instanceof DeliveryOrganisation)
            {
                deliveryOrganisation=(DeliveryOrganisation) oo;
            }
        }
        
        for(Employee emp: deliveryOrganisation.getEmpList().getEmployeeDirectory())
        {
            if(emp.getRole().toString().equalsIgnoreCase(Role.RoleType.DeliveryMan.getValue()))
            {
                Object [] row = new Object[4];
                row[0]=emp.getName();
                row[1]=emp.getRole();
                row[2]= emp.getEmpUserAcc().getUserName();
                row[3]=emp.getEmpUserAcc().getPassword();
                model.addRow(row);
            }
        }
        
    }
    private void BackJBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackJBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_BackJBtnActionPerformed

    private void CreateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateStaffActionPerformed
        // TODO add your handling code here:
        Interface.Delivery.CreateDeliveryEmployeesJPanel idcdejp = new Interface.Delivery.CreateDeliveryEmployeesJPanel(userProcessContainer,account,deliveryOrganisation, enterprise, business);
        userProcessContainer.add("CreateDeliveryEmployeesJPanel",idcdejp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        
    }//GEN-LAST:event_CreateStaffActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackJBtn;
    private javax.swing.JButton CreateStaff;
    private javax.swing.JTable StaffJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
