/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import br.com.infox.connection.ConexaoUtil;
import br.com.infox.dao.DaoFactory;
import br.com.infox.dao.UsuarioDao;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import br.com.infox.telas.TelaLogin;
import java.sql.*; 


public class TelaPrincipal extends javax.swing.JFrame {
    
    public static String perfilLogado = null;
 

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal(String perfil) {
        
        perfilLogado = perfil;
        JOptionPane.showMessageDialog(null, perfilLogado);
        initComponents();
        
  
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        menuCadUsuario = new javax.swing.JMenu();
        menuCadCli = new javax.swing.JMenuItem();
        menuCadOS = new javax.swing.JMenuItem();
        menuCadUsu = new javax.swing.JMenuItem();
        menuRel = new javax.swing.JMenu();
        menuRelCli = new javax.swing.JMenuItem();
        menuRelServ = new javax.swing.JMenuItem();
        menuAjuda = new javax.swing.JMenu();
        menuAjudaSobre = new javax.swing.JMenuItem();
        menuOp = new javax.swing.JMenu();
        menuOpSair = new javax.swing.JMenuItem();
        munuOpLogoff = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("X - Sistema para controle de OS");
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        desktop.setPreferredSize(new java.awt.Dimension(639, 460));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/X.png"))); // NOI18N

        lblData.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblData.setText("Data");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usuário");

        menuCadUsuario.setText("Cadastro");

        menuCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuCadCli.setText("Cliente");
        menuCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadCliActionPerformed(evt);
            }
        });
        menuCadUsuario.add(menuCadCli);

        menuCadOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menuCadOS.setText("OS");
        menuCadOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadOSActionPerformed(evt);
            }
        });
        menuCadUsuario.add(menuCadOS);

        menuCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menuCadUsu.setText("Usuários");
        menuCadUsu.setEnabled(false);
        menuCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadUsuActionPerformed(evt);
            }
        });
        menuCadUsuario.add(menuCadUsu);

        menu.add(menuCadUsuario);

        menuRel.setText("Relatório");
        menuRel.setEnabled(false);

        menuRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        menuRelCli.setText("Clientes");
        menuRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelCliActionPerformed(evt);
            }
        });
        menuRel.add(menuRelCli);

        menuRelServ.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menuRelServ.setText("Serviços");
        menuRelServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelServActionPerformed(evt);
            }
        });
        menuRel.add(menuRelServ);

        menu.add(menuRel);

        menuAjuda.setText("Ajuda");

        menuAjudaSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menuAjudaSobre.setText("Sobre");
        menuAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjudaSobreActionPerformed(evt);
            }
        });
        menuAjuda.add(menuAjudaSobre);

        menu.add(menuAjuda);

        menuOp.setText("Opções");

        menuOpSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuOpSair.setText("Sair");
        menuOpSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpSairActionPerformed(evt);
            }
        });
        menuOp.add(menuOpSair);

        munuOpLogoff.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        munuOpLogoff.setText("trocar usuário (logoff)");
        munuOpLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                munuOpLogoffActionPerformed(evt);
            }
        });
        menuOp.add(munuOpLogoff);

        menu.add(menuOp);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblData)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(lblUsuario)
                .addGap(18, 18, 18)
                .addComponent(lblData)
                .addGap(85, 85, 85)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(915, 517));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadCliActionPerformed
        // TODO add your handling code here:
        TelaCliente cli = new TelaCliente();
        cli.setVisible(true);
        desktop.add(cli);
    }//GEN-LAST:event_menuCadCliActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        //Ao carregar a TelaPrincipal, o lblData recebe a data atual do sistema;
        Date data = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // DateFormat.SHORT -> retorna um int = 3.
        //DateFormat sdf = new SimpleDateFormat("dd/MM/YYYY"); 
        lblData.setText(df.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void menuOpSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpSairActionPerformed
        // TODO add your handling code here:
        int sair = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION){
            Runtime.getRuntime().exit(0);
            //System.exit(0);
            // caso o usuário clique em NO_OPTION por default o OptionPane é fechado e o programa continua 
            // normalmente.
        }
        
    }//GEN-LAST:event_menuOpSairActionPerformed

    private void menuAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjudaSobreActionPerformed
        // chamando a tela Sobre
        TelaSobre ts = new TelaSobre();
        ts.setVisible(true);
        
        
    }//GEN-LAST:event_menuAjudaSobreActionPerformed

    private void munuOpLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_munuOpLogoffActionPerformed
        //Estrutura de decisão para opçao de logoff do sistema;
        int logoff = JOptionPane.showConfirmDialog(null, "Deseja realizar o logoff?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (logoff == JOptionPane.YES_OPTION) {
            this.dispose();
            TelaLogin tLog = new TelaLogin();
            tLog.setVisible(true);
        }
    }//GEN-LAST:event_munuOpLogoffActionPerformed

    private void menuCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadUsuActionPerformed
        // TODO add your handling code here:
        TelaUsuario tuser = new TelaUsuario();
        tuser.setVisible(true);
        desktop.add(tuser); // caso não seja chamado o add, o JDesktopPane não libera o JInternalFrame;
    }//GEN-LAST:event_menuCadUsuActionPerformed

    private void menuCadOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadOSActionPerformed
        // TODO add your handling code here:
        TelaOS os = new TelaOS();
        os.setVisible(true);
        desktop.add(os);
    }//GEN-LAST:event_menuCadOSActionPerformed

    private void menuRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelCliActionPerformed
        // gerando um relatório de clientes
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção", JOptionPane.YES_NO_OPTION );
        
        if(confirma == JOptionPane.YES_OPTION){
            // imprimindo o relatório com o JaperReport
            UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
            usuarioDao.imprimirRelatorioClientes();
             
        }
        
    }//GEN-LAST:event_menuRelCliActionPerformed

    private void menuRelServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelServActionPerformed
        // gerando um relatório de serviços
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório?", "Atenção", JOptionPane.YES_NO_OPTION );
        
        if(confirma == JOptionPane.YES_OPTION){
            // imprimindo o relatório com o JaperReport
            
            UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
            usuarioDao.imprimirRelatorioServicos();
            
            // chamar o imprimirRelatorioServicos();
             
        }
    }//GEN-LAST:event_menuRelServActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal(perfilLogado).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenuItem menuAjudaSobre;
    private javax.swing.JMenuItem menuCadCli;
    private javax.swing.JMenuItem menuCadOS;
    public static javax.swing.JMenuItem menuCadUsu;
    private javax.swing.JMenu menuCadUsuario;
    private javax.swing.JMenu menuOp;
    private javax.swing.JMenuItem menuOpSair;
    public static javax.swing.JMenu menuRel;
    private javax.swing.JMenuItem menuRelCli;
    private javax.swing.JMenuItem menuRelServ;
    private javax.swing.JMenuItem munuOpLogoff;
    // End of variables declaration//GEN-END:variables
}
