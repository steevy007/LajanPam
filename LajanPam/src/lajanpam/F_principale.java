/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lajanpam;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author Sanon
 */
public class F_principale extends JFrame {
        private BD b;
    private Connection con;
    private ResultSet res;
    private Statement st;
    double ta,tc,tp,te;
 
    String[][] ADM=new String[2][2]; 
    int test=0;
       
            private ActionListener al;
    /**
     * Creates new form F_principale
     */
    public F_principale() {
        initComponents();
        b=new BD();
        pan_con.setVisible(false);
        setLocationRelativeTo(null);
        setResizable(false);
        red.enable();
        green.disable();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Operation p=new Operation();
        btn_mod.setText("Modifier");
       ;
        hour();
        l.disable();
        l.setText("US");
        C_V.disable();
        C_R.disable();
        C_V1.disable();
        C_R1.disable();
        C_V2.disable();
        C_R2.disable();
        C_V3.disable();
        C_R3.disable();
        

          
 
        result.disable();
        init_taux();
        jTextField2.setText("HTG");
        jTextField2.disable();
        v.setVisible(false);
        Ann.setVisible(false);
        result.setText("0");
        Mod();
    }
    
    public void hour(){
        Thread clock=new Thread(){
            public void run(){
                for(;;){
                    Calendar cal=new GregorianCalendar();
                    int sec=cal.get(Calendar.SECOND);
                    int minute=cal.get(Calendar.MINUTE);
                    int hour=cal.get(Calendar.HOUR);
                    
                    hora.setText(hour+":"+minute+":"+sec);
                    
                    try{
                        sleep(1000);
                    }catch(InterruptedException e){
                        //Logger.getLogger(Calendar.class.getName().log(Level.SEVERE,null,e));
                    }
                }
            }
        };
        
        clock.start();
    }
    
 
    public  double AM(){
         double A=Double.parseDouble(entry.getText());
         double result=(A/ta);
         return result;
     }
     
     public  double CAN(){
         double A=Double.parseDouble(entry.getText());
         double result=(A/tc);
         return result;
     }
     
     public  double EU(){
         double A=Double.parseDouble(entry.getText());
         double result=(A/te);
         return result;
     }
     
     public  double PES(){
         double A=Double.parseDouble(entry.getText());
         double result=(A/tp);
         return result;
     }
     
    
    
    public void mod_taux(){
       if(btn_mod.getText().equalsIgnoreCase("Modifier")){
           JOptionPane.showMessageDialog(this,"Veuillez Vous connectez D'abord");
           pan_con.setVisible(true);
          
           test=0;
       } 
    }
    public void init_taux(){
           try{
                double[] tab=new  double[4];
               int i=0;
               st=b.getSt();
               String req="select montant_taux from taux";
               res=st.executeQuery(req);
              
               while(res.next()){
                   String TA=res.getString("montant_taux");
                   double ta=Double.parseDouble(TA);
                   
                   tab[i]=ta;
                    ta=tab[0];
                    tc=tab[1];
                    te=tab[2];
                    tp=tab[3];
                    jt_tauxA.setText(ta+"");
                    jt_tauxC.setText(tc+"");
                    jt_E.setText(te+"");
                    jt_P.setText(tp+"");
                   
                    jt_tauxA.disable();;
                    jt_tauxC.disable();
                    jt_E.disable();
                    jt_P.disable();
                   i++;
               }
              
//               System.out.println(tab[i]);
           }catch(NumberFormatException e){
           
           }catch(SQLException k){
               JOptionPane.showMessageDialog(this,""+k.getMessage());
           }

       }
    
    public int connect(){
        try{
               st=b.getSt();
               String req="select * from  administrateur ";
               res=st.executeQuery(req);
              int i=0;
               while(res.next()){
                   String nom=res.getString("nom");
                   String pass=res.getString("password");
                   
                   ADM[0][0]=nom;
                   ADM[0][1]=pass;
                   
                   i++;
               }
               //System.out.println(ADM[0][0]);
               //System.out.println(ADM[0][1]);
               try{
                   
                   
                   if(jt_id.getText().isEmpty() || jt_pass.getText().isEmpty()){
                       JOptionPane.showMessageDialog(this,"Veuillez Rentrer les infos");
                   }
                   
                   else{
                      
                       if(ADM[0][0].equalsIgnoreCase(jt_id.getText()) && ADM[0][1].equalsIgnoreCase(jt_pass.getText())){
                           v.setVisible(true);
                       btn_mod.setVisible(false);
                       conT.setText("Connexion Ouvert");
                       red.disable();
                       user.setText(ADM[0][0]);
                       green.enable();
                           jt_id.setText(null);
                           jt_pass.setText(null);
                           pan_con.setVisible(false);
                           M_A.enable();
                           M_A.setText(null);
                           M_C.enable();
                           M_C.setText(null);
                           M_E.enable();
                           M_E.setText(null);
                           M_P.enable();
                           M_P.setText(null);
                           test=1;
                   }
                        
                   
                   else if(!ADM[0][0].equalsIgnoreCase(jt_id.getText()) || !ADM[0][1].equalsIgnoreCase(jt_pass.getText())){
                       JOptionPane.showMessageDialog(this,"Mot de passe ou identifiant incorrect");
                       jt_pass.setText(null);
                       
                   }
               }
               }catch(Exception e){}
               
//               System.out.println(tab[i]);
           }catch(NumberFormatException e){
           
           }catch(SQLException k){
               JOptionPane.showMessageDialog(this,""+k.getMessage());
           }
        return test;
    }
    
     public void Mod(){
         try{
             double[] tab=new  double[4];
               int i=0;
               st=b.getSt();
               String req="select montant_taux from taux";
               res=st.executeQuery(req);
              
               while(res.next()){
                   String TA=res.getString("montant_taux");
                   double tA=Double.parseDouble(TA);
                   
                   tab[i]=tA;
                    ta=tab[0];
                    tc=tab[1];
                    te=tab[2];
                    tp=tab[3];
                    M_A.setText(ta+"");
                    M_C.setText(tc+"");
                    M_E.setText(te+"");
                    M_P.setText(tp+"");
                    
                    M_A.disable();;
                    M_C.disable();
                    M_E.disable();
                    M_P.disable();
                   i++;
               }
              
//               System.out.println(tab[i]);
           }catch(NumberFormatException e){
           
           }catch(SQLException k){
               JOptionPane.showMessageDialog(this,""+k.getMessage());
           }

     }
    /**
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jt_tauxA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jt_tauxC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jt_E = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jt_P = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        result = new javax.swing.JTextField();
        entry = new javax.swing.JTextField();
        select = new javax.swing.JComboBox<>();
        conv = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        l = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        M_A = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        M_C = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        M_E = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        M_P = new javax.swing.JTextField();
        pan_con = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jt_id = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        conn = new javax.swing.JButton();
        jt_pass = new javax.swing.JPasswordField();
        btn_mod = new javax.swing.JButton();
        Ann = new javax.swing.JButton();
        v = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        conT = new javax.swing.JLabel();
        green = new javax.swing.JLabel();
        red = new javax.swing.JLabel();
        C_V = new javax.swing.JLabel();
        C_R = new javax.swing.JLabel();
        C_R1 = new javax.swing.JLabel();
        C_V1 = new javax.swing.JLabel();
        C_V2 = new javax.swing.JLabel();
        C_R2 = new javax.swing.JLabel();
        C_R3 = new javax.swing.JLabel();
        C_V3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        hora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LajanPam");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dollar US");
        jPanel1.add(jLabel1);

        jt_tauxA.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jt_tauxA.setForeground(new java.awt.Color(255, 51, 51));
        jt_tauxA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jt_tauxA);

        jLabel2.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dollar CAD");
        jPanel1.add(jLabel2);

        jt_tauxC.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jt_tauxC.setForeground(new java.awt.Color(255, 51, 51));
        jt_tauxC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jt_tauxC);

        jLabel3.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Euro");
        jPanel1.add(jLabel3);

        jt_E.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jt_E.setForeground(new java.awt.Color(255, 51, 51));
        jt_E.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jt_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_EActionPerformed(evt);
            }
        });
        jPanel1.add(jt_E);

        jLabel4.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Peso");
        jPanel1.add(jLabel4);

        jt_P.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jt_P.setForeground(new java.awt.Color(255, 51, 51));
        jt_P.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jt_P);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Calligraphy", 3, 16), new java.awt.Color(153, 153, 255))); // NOI18N

        result.setFont(new java.awt.Font("Exotc350 DmBd BT", 1, 24)); // NOI18N
        result.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultActionPerformed(evt);
            }
        });

        entry.setBackground(new java.awt.Color(153, 153, 255));
        entry.setFont(new java.awt.Font("Exotc350 DmBd BT", 1, 24)); // NOI18N
        entry.setForeground(new java.awt.Color(0, 102, 51));
        entry.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        select.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Selectionner la monaie--", "Americain", "Canadien", "Euro", "Peso" }));
        select.setToolTipText("Selectionner La monaie");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        conv.setBackground(new java.awt.Color(153, 153, 255));
        conv.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        conv.setForeground(new java.awt.Color(51, 51, 51));
        conv.setText("Convertir");
        conv.setBorder(null);
        conv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                convMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                convMouseExited(evt);
            }
        });
        conv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convActionPerformed(evt);
            }
        });

        clean.setBackground(new java.awt.Color(153, 153, 255));
        clean.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        clean.setForeground(new java.awt.Color(51, 51, 51));
        clean.setText("Nettoyer");
        clean.setBorder(null);
        clean.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cleanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cleanMouseExited(evt);
            }
        });
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Devise");

        l.setFont(new java.awt.Font("Exotc350 DmBd BT", 1, 24)); // NOI18N
        l.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField2.setFont(new java.awt.Font("Exotc350 DmBd BT", 0, 24)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(conv, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clean, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entry, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(l)))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entry)
                    .addComponent(l))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conv, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18), new java.awt.Color(153, 153, 255))); // NOI18N

        jLabel5.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Nouveau Taux US");

        M_A.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        M_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        M_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M_AMouseClicked(evt);
            }
        });
        M_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_AActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Pristina", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Modification Des Taux");

        jLabel7.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("Nouveau Taux CA");

        M_C.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        M_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Nouveau Taux Euro");

        M_E.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        M_E.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("MingLiU-ExtB", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("Nouveau Taux Peso");

        M_P.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        M_P.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        pan_con.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Connection", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Engravers MT", 1, 18), new java.awt.Color(0, 102, 51))); // NOI18N

        jLabel10.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/Entypo_d83d(1)_32.png"))); // NOI18N

        jt_id.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        jt_id.setForeground(new java.awt.Color(153, 153, 255));
        jt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jt_id.setSelectionColor(new java.awt.Color(255, 0, 0));

        jLabel11.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/Entypo_d83d(0)_32.png"))); // NOI18N

        conn.setBackground(new java.awt.Color(153, 153, 255));
        conn.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        conn.setForeground(new java.awt.Color(51, 51, 51));
        conn.setText("Connecter");
        conn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connMouseExited(evt);
            }
        });
        conn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connActionPerformed(evt);
            }
        });

        jt_pass.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        jt_pass.setForeground(new java.awt.Color(153, 153, 255));
        jt_pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout pan_conLayout = new javax.swing.GroupLayout(pan_con);
        pan_con.setLayout(pan_conLayout);
        pan_conLayout.setHorizontalGroup(
            pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_conLayout.createSequentialGroup()
                .addGroup(pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan_conLayout.createSequentialGroup()
                        .addContainerGap(266, Short.MAX_VALUE)
                        .addComponent(conn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan_conLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jt_id)
                            .addComponent(jt_pass))))
                .addGap(29, 29, 29))
        );
        pan_conLayout.setVerticalGroup(
            pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_conLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_conLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jt_pass)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(conn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_mod.setBackground(new java.awt.Color(153, 153, 255));
        btn_mod.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        btn_mod.setForeground(new java.awt.Color(51, 51, 51));
        btn_mod.setText("Modifier");
        btn_mod.setBorder(null);
        btn_mod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_modMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_modMouseExited(evt);
            }
        });
        btn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modActionPerformed(evt);
            }
        });

        Ann.setBackground(new java.awt.Color(153, 153, 255));
        Ann.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        Ann.setForeground(new java.awt.Color(51, 51, 51));
        Ann.setText("Annuler");
        Ann.setBorder(null);
        Ann.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AnnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AnnMouseExited(evt);
            }
        });
        Ann.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnActionPerformed(evt);
            }
        });

        v.setBackground(new java.awt.Color(153, 153, 255));
        v.setFont(new java.awt.Font("MingLiU-ExtB", 1, 14)); // NOI18N
        v.setForeground(new java.awt.Color(51, 51, 51));
        v.setText("Valider la Modification");
        v.setBorder(null);
        v.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vMouseExited(evt);
            }
        });
        v.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vActionPerformed(evt);
            }
        });

        user.setFont(new java.awt.Font("Pristina", 1, 18)); // NOI18N
        user.setForeground(new java.awt.Color(0, 0, 204));
        user.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        user.setText("Utilisateur");

        conT.setFont(new java.awt.Font("Pristina", 1, 18)); // NOI18N
        conT.setForeground(new java.awt.Color(0, 0, 204));
        conT.setText("Connexion Fermé");

        green.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/Entypo_26ab(0)_32.png"))); // NOI18N

        red.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/red.png"))); // NOI18N

        C_V.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/check.png"))); // NOI18N

        C_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/FontAwesome_f00d(0)_48.png"))); // NOI18N

        C_R1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/FontAwesome_f00d(0)_48.png"))); // NOI18N

        C_V1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/check.png"))); // NOI18N

        C_V2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/check.png"))); // NOI18N

        C_R2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/FontAwesome_f00d(0)_48.png"))); // NOI18N

        C_R3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/FontAwesome_f00d(0)_48.png"))); // NOI18N

        C_V3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lajanpam/icones/check.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(conT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(red, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(green, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(M_C, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(M_A, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(M_P, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(M_E, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(C_V2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C_R2))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(C_V3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(C_R3))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(C_V1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(C_R1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(C_V)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(C_R))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pan_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(594, 594, 594)
                        .addComponent(v, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ann, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(conT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(green, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(red, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(C_V1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(M_A, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(C_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(C_R, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(C_V, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(M_C, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(M_E, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(C_V2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(C_R2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(C_V3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(M_P, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                            .addComponent(C_R3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pan_con, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Ann, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                        .addComponent(v, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Pristina", 1, 34)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Veuillez Saisir le montant en gourdes à convertir");

        jPanel5.setBackground(new java.awt.Color(205, 205, 221));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 102, 255))); // NOI18N

        hora.setBackground(new java.awt.Color(255, 255, 255));
        hora.setFont(new java.awt.Font("Footlight MT Light", 0, 36)); // NOI18N
        hora.setForeground(new java.awt.Color(0, 0, 204));
        hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hora, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_EActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_EActionPerformed

    private void resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resultActionPerformed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        // TODO add your handling code here:
        mod_taux();
        Ann.setVisible(true);
        reset();
      
    }//GEN-LAST:event_btn_modActionPerformed

    private void connActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connActionPerformed
        // TODO add your handling code here:
        connect();
    }//GEN-LAST:event_connActionPerformed

    private void M_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M_AMouseClicked

        
    }//GEN-LAST:event_M_AMouseClicked

    private void AnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnActionPerformed
        // TODO add your handling code here:
        Ann();
        Mod();
    }//GEN-LAST:event_AnnActionPerformed

    private void M_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_AActionPerformed

    private void convActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convActionPerformed
        // TODO add your handling code here:
         String V=select.getSelectedItem()+"";
         
         if(result.getText().equalsIgnoreCase("0") && entry.getText().isEmpty()){
             JOptionPane.showMessageDialog(this,"Veuillez saisir votre montant");
         }

         else if(result.getText().equalsIgnoreCase("0") && !entry.getText().isEmpty() && V.equalsIgnoreCase("--selectionner la monaie--")){
             JOptionPane.showMessageDialog(this,"Selectionner la monaie");
         }
         else{
               if(V.equalsIgnoreCase("Americain")){
            try{
              
            if(!entry.getText().isEmpty()){
                DecimalFormat C=new DecimalFormat();
                C.setMaximumFractionDigits(2);
                 double K=AM();
               String L=C.format(K);
                
                 String B=L+"";
        result.setText(B+" $US");
        
            }
            
            else if(!entry.getText().isEmpty() && select.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(this,"Veuillez Saisir le montant a convertir");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Montant Incorrect");
        }
        
        
        }
          
          if(V.equalsIgnoreCase("Canadien")){
            try{
            if(!entry.getText().isEmpty()){
                DecimalFormat C=new DecimalFormat();
                C.setMaximumFractionDigits(2);
                 double K=CAN();
                String L=C.format(K);
                
        String B=L+"";
        result.setText(B+" $CAD");
            }
            
           else{
                JOptionPane.showMessageDialog(this,"Veuillez Saisir le montant a convertir");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Montant Incorrect");
        }
        }
        
        if(V.equalsIgnoreCase("Euro")){
            try{
                if(!entry.getText().isEmpty()){
                DecimalFormat C=new DecimalFormat();
                C.setMaximumFractionDigits(2);
                 double K=EU();
                String L=C.format(K);
                
        String B=L+"";
        result.setText(B+" EU");
            }
            
           else{
                JOptionPane.showMessageDialog(this,"Veuillez Saisir le montant a convertir");
            }
        }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(this,"Montant Incorrect");
        }
        }
        
         if(V.equalsIgnoreCase("Peso")){
            try{
            if(!entry.getText().isEmpty()){
                DecimalFormat C=new DecimalFormat();
                C.setMaximumFractionDigits(2);
                 double K=PES();
                String L=C.format(K);
                
        String B=L+"";
        result.setText(B+" Pesos");
            }
            
           else{
                JOptionPane.showMessageDialog(this,"Veuillez Saisir le montant a convertir");
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Montant Incorrect");
        }
        }
         }
        
    }//GEN-LAST:event_convActionPerformed
    
    public void reset(){
        C_V1.disable();;
        C_R1.disable();
         C_V.disable();;
        C_R.disable();
         C_V2.disable();;
        C_R2.disable();
         C_V3.disable();;
        C_R3.disable();
    }
    public void modif(){
    
        if(!M_A.getText().isEmpty()){
            
            try{
                double A=Double.parseDouble(M_A.getText());
               st=b.getSt();
               String req="update taux set montant_taux="+A+" where nom_taux='Taux_a'";
               st.executeUpdate(req);
               C_V1.enable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this," "+e.getMessage());
                C_R1.enable();
            }catch(NumberFormatException k){
                JOptionPane.showMessageDialog(this,"Desolé Montant Americain incorrect modification echoué");
                C_R1.enable();
            }
        };
        
         if(!M_E.getText().isEmpty()){
            
            try{
                double A=Double.parseDouble(M_E.getText());
               st=b.getSt();
               String req="update taux set montant_taux="+A+" where nom_taux='Taux_e'";
               st.executeUpdate(req);
               C_V2.enable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this," "+e.getMessage());
               C_R2.enable();
            }catch(NumberFormatException k){
                JOptionPane.showMessageDialog(this,"Desolé Montant Europeen incorrect modification echoué");
                C_R2.enable();
            }
        };
        
         if(!M_C.getText().isEmpty()){
            
            try{
                double A=Double.parseDouble(M_C.getText());
               st=b.getSt();
               String req="update taux set montant_taux="+A+" where nom_taux='Taux_c'";
               st.executeUpdate(req);
               C_V.enable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this," "+e.getMessage());
                C_R.enable();
            }catch(NumberFormatException k){
                JOptionPane.showMessageDialog(this,"Desolé Montant Canadien incorrect modification echoué");
                C_R.enable();
            }
        };
        
         if(!M_P.getText().isEmpty()){
            
            try{
                double A=Double.parseDouble(M_P.getText());
               st=b.getSt();
               String req="update taux set montant_taux="+A+" where nom_taux='Taux_p'";
               st.executeUpdate(req);
               C_V3.enable();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this," "+e.getMessage());
                C_R3.enable();
            }catch(NumberFormatException k){
                JOptionPane.showMessageDialog(this,"Desolé Montant Peso incorrect modification echoué");
                C_R3.enable();
            }
        };
    
    }
    public void clean(){
        result.setText("0");
        select.setSelectedItem("yu");
        entry.setText(null);
        l.setText(null);
    }
    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        // TODO add your handling code here:
        clean();
    }//GEN-LAST:event_cleanActionPerformed

    private void vActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vActionPerformed
        // TODO add your handling code here:
          modif();
          init_taux();
         Mod();
         Ann.hide();
         v.hide();
         btn_mod.show();
         //bar();
        
         
    }//GEN-LAST:event_vActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
         String V=select.getSelectedItem()+"";
       
        if(V.equalsIgnoreCase("Americain")){
            l.setText("US");
        
        }
        
        if(V.equalsIgnoreCase("Euro")){
            l.setText("EU");
         
        }
        
        if(V.equalsIgnoreCase("Canadien")){
            l.setText("CA");
         
        }
        
        if(V.equalsIgnoreCase("Peso")){
            l.setText("PE");
            
        }
    }//GEN-LAST:event_selectActionPerformed

    private void convMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_convMouseEntered
        // TODO add your handling code here:
        conv.setBackground(Color.GRAY);
    }//GEN-LAST:event_convMouseEntered

    private void cleanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cleanMouseEntered
        // TODO add your handling code here:
        clean.setBackground(Color.GRAY);
    }//GEN-LAST:event_cleanMouseEntered

    private void btn_modMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modMouseEntered
        // TODO add your handling code here:
        btn_mod.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_modMouseEntered

    private void AnnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnnMouseEntered
        // TODO add your handling code here:
        Ann.setBackground(Color.GRAY);
    }//GEN-LAST:event_AnnMouseEntered

    private void vMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMouseEntered
        // TODO add your handling code here:
        v.setBackground(Color.GRAY);
    }//GEN-LAST:event_vMouseEntered

    private void convMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_convMouseExited
        // TODO add your handling code here:
        conv.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_convMouseExited

    private void connMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connMouseEntered
        // TODO add your handling code here:
        conn.setBackground(Color.GRAY);
    }//GEN-LAST:event_connMouseEntered

    private void cleanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cleanMouseExited
        // TODO add your handling code here:
        clean.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_cleanMouseExited

    private void connMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connMouseExited
        // TODO add your handling code here:
         conn.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_connMouseExited

    private void vMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vMouseExited
        // TODO add your handling code here:
         v.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_vMouseExited

    private void AnnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnnMouseExited
        // TODO add your handling code here:
         Ann.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_AnnMouseExited

    private void btn_modMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modMouseExited
        // TODO add your handling code here:
         btn_mod.setBackground(new Color(153,153,255));
    }//GEN-LAST:event_btn_modMouseExited

    public void Ann(){
        btn_mod.setText("Modifier");
        pan_con.setVisible(false);
        btn_mod.setVisible(true);
        red.enable();
        green.disable();
        conT.setText("Connexion Fermé");
        user.setText("Utilisateur");
        Ann.setVisible(false);
        v.setVisible(false);
    }
    
    
    public void conv(){
        Ann();
    }
    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ann;
    private javax.swing.JLabel C_R;
    private javax.swing.JLabel C_R1;
    private javax.swing.JLabel C_R2;
    private javax.swing.JLabel C_R3;
    private javax.swing.JLabel C_V;
    private javax.swing.JLabel C_V1;
    private javax.swing.JLabel C_V2;
    private javax.swing.JLabel C_V3;
    private javax.swing.JTextField M_A;
    private javax.swing.JTextField M_C;
    private javax.swing.JTextField M_E;
    private javax.swing.JTextField M_P;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton clean;
    private javax.swing.JLabel conT;
    private javax.swing.JButton conn;
    private javax.swing.JButton conv;
    private javax.swing.JTextField entry;
    private javax.swing.JLabel green;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jt_E;
    private javax.swing.JTextField jt_P;
    private javax.swing.JTextField jt_id;
    private javax.swing.JPasswordField jt_pass;
    private javax.swing.JTextField jt_tauxA;
    private javax.swing.JTextField jt_tauxC;
    private javax.swing.JTextField l;
    private javax.swing.JPanel pan_con;
    private javax.swing.JLabel red;
    private javax.swing.JTextField result;
    private javax.swing.JComboBox<String> select;
    private javax.swing.JLabel user;
    private javax.swing.JButton v;
    // End of variables declaration//GEN-END:variables
}
