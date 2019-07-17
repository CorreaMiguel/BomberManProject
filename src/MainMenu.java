/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        leaveButton = new javax.swing.JButton();
        createRoom = new javax.swing.JButton();
        enterRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title.setText("Bomber Man");

        leaveButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        leaveButton.setText("SAIR");
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        createRoom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        createRoom.setText("Criar Sala");
        createRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomActionPerformed(evt);
            }
        });

        enterRoom.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        enterRoom.setText("Entrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(enterRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(99, 99, 99))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(leaveButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(createRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(leaveButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_leaveButtonActionPerformed

    private void createRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRoomActionPerformed
        // TODO add your handling code here:
        new CreateRoom().setVisible(true);
        dispose();
    }//GEN-LAST:event_createRoomActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createRoom;
    private javax.swing.JButton enterRoom;
    private javax.swing.JButton leaveButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
