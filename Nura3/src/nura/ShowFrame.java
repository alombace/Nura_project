/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nura;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class ShowFrame extends javax.swing.JFrame
{
    // private int id;

    private ArrayList<Integer> ar;  // список идентификаторов изображений в БД 
    private int flags;
    private boolean k = false;

    public ShowFrame(int flag, String sqls) //конструктор 
    {
        flags = flag;
        //запрос выбора  всех айдишники с таблицы добавленных рецептов
        String sqli = "select add_id from added_recept";
        initComponents();//инициализируем компоненты
        this.setResizable(false); //запрещаем изменение размера окна
        //задаем иконку окна
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("img/иконка.png")));
        ar = Nuranew.getIDS(sqls);// берем айдшники rec_id, так как они расположены по порядку 
        int id = ar.get(flag);
        k = Nuranew.work_favourite(id);
        if (k == true) 
        {
            ButShowLike.setIcon(new ImageIcon(getClass().getResource("/img/удалить_избр.png")));
        } else
        {
            ButShowLike.setIcon(new ImageIcon(getClass().getResource("/img/избранное.png")));
        }

        if (sqls == sqli) //c добавленными рецептами особая работа
        {
            ButShowDel.setEnabled(true);
            String t = Nuranew.get_added_info(id);
            LabelPic.setText(t);
            String text = Nuranew.get_added_info1(id);
            TextAreaRecept.setText(text);
             ButShowLike.setIcon(null);
        } else //со всеми остальными частями Box
        {
            ButShowDel.setEnabled(false);
            BufferedImage bim = Nuranew.getBlob(id);
            LabelPic.setIcon(new ImageIcon(bim));
            String text = Nuranew.gettext(id);
            TextAreaRecept.setText(text);
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

        jPanel4 = new javax.swing.JPanel();
        LabelPic = new javax.swing.JLabel();
        ButShowLike = new javax.swing.JButton();
        ButShowDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ButShowExit = new javax.swing.JButton();
        ButShowBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaRecept = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 530));
        setPreferredSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(249, 246, 222));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LabelPic.setBackground(new java.awt.Color(255, 255, 255));
        LabelPic.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        LabelPic.setForeground(new java.awt.Color(255, 255, 255));
        LabelPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelPic.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(LabelPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 240, 100));

        ButShowLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/избранное.png"))); // NOI18N
        ButShowLike.setBorderPainted(false);
        ButShowLike.setContentAreaFilled(false);
        ButShowLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButShowLikeActionPerformed(evt);
            }
        });
        jPanel4.add(ButShowLike, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        ButShowDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/удалить.png"))); // NOI18N
        ButShowDel.setBorderPainted(false);
        ButShowDel.setContentAreaFilled(false);
        ButShowDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButShowDelActionPerformed(evt);
            }
        });
        jPanel4.add(ButShowDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/показ.gif"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 350, 310));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/начнем.png"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 131));

        ButShowExit.setBackground(new java.awt.Color(229, 216, 163));
        ButShowExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Выход_1.png"))); // NOI18N
        ButShowExit.setBorderPainted(false);
        ButShowExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButShowExit.setOpaque(false);
        ButShowExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButShowExitActionPerformed(evt);
            }
        });
        jPanel4.add(ButShowExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 140, -1));

        ButShowBack.setBackground(new java.awt.Color(229, 216, 163));
        ButShowBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Назад.png"))); // NOI18N
        ButShowBack.setBorderPainted(false);
        ButShowBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ButShowBack.setOpaque(false);
        ButShowBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButShowBackActionPerformed(evt);
            }
        });
        jPanel4.add(ButShowBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, 140, -1));

        TextAreaRecept.setEditable(false);
        TextAreaRecept.setColumns(20);
        TextAreaRecept.setRows(5);
        jScrollPane1.setViewportView(TextAreaRecept);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 300, 260));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/блокнот.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/кухня_размытая.jpg"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButShowExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButShowExitActionPerformed
        new ExitFrame(this).setVisible(true); //при нажатии на "выход" создаем новое окно ExitFrame и делаем его видимым 
        this.setVisible(false);//а данное окно - невидимым
    }//GEN-LAST:event_ButShowExitActionPerformed

    private void ButShowBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButShowBackActionPerformed
        new MenuFrame().setVisible(true);//аналогично функции, описанной выше
        this.setVisible(false);
    }//GEN-LAST:event_ButShowBackActionPerformed
    private void func() //функция обновления данных в поле "Избранное"
    {

        String sql = "select rec_id from recept";
        ar = Nuranew.getIDS(sql);// берем айдшники rec_id, так как они расположены по порядку 
        int id = ar.get(flags);
        System.out.println(id);

        k = Nuranew.work_favourite(id);
        System.out.println(k);
        if (k == false)
        {
            sql = "UPDATE recept SET favourite_n = 'true' WHERE pic_id=?";
            ButShowLike.setIcon(new ImageIcon(getClass().getResource("/img/удалить_избр.png")));
            k = true;
            Nuranew.fav1(id, sql);
        } else
        {
            sql = "UPDATE recept SET favourite_n = 'false' WHERE pic_id=?";
            ButShowLike.setIcon(new ImageIcon(getClass().getResource("/img/избранное.png")));
            k = false;
            Nuranew.fav1(id, sql);
        }

    }

    public void del_func() //функция удаления добавленных рецептов
    {  //p.s. в нашей таблице  всех рецептов пользователь не имеет возможности удалить рецепты,
       // кроме добавленных им же
        String sql = "select add_id from added_recept";
        ar = Nuranew.getIDS(sql);// берем айдшники rec_id, так как они расположены по порядку 
        int id = ar.get(flags);
        System.out.println(id);
        sql = "DELETE from added_recept  where add_id=?";
        Nuranew.fav1(id, sql);
        ButShowDel.setEnabled(false);
    }
    private void ButShowLikeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButShowLikeActionPerformed
    {//GEN-HEADEREND:event_ButShowLikeActionPerformed
        func();


    }//GEN-LAST:event_ButShowLikeActionPerformed

    private void ButShowDelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButShowDelActionPerformed
    {//GEN-HEADEREND:event_ButShowDelActionPerformed
        del_func();
    }//GEN-LAST:event_ButShowDelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ShowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ShowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ShowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ShowFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButShowBack;
    private javax.swing.JButton ButShowDel;
    private javax.swing.JButton ButShowExit;
    private javax.swing.JButton ButShowLike;
    private javax.swing.JLabel LabelPic;
    private javax.swing.JTextArea TextAreaRecept;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
