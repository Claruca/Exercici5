
package DefArticles;

/**
 *
 * @author clara
 */
public class Finestra extends javax.swing.JFrame{
    
    private javax.swing.JLabel jEtSaludo;    
    
    public Finestra() {
        setSize(500, 500);
        setTitle("Finestra");
        initComponents();
    }
     private void initComponents(){         
         jEtSaludo = new javax.swing.JLabel();
         getContentPane().setLayout(null);
         
         addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
         jEtSaludo.setText("Gestió d'articles");
         jEtSaludo.setFont(new java.awt.Font("courier", 1, 20));
         jEtSaludo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
         getContentPane().add(jEtSaludo);        
         
         
         
     
     }
     
     private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }
     
}
