/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lajanpam;

/**
 *
 * @author Sanon
 */
public class first {
    
    public static void main(String[]args)throws InterruptedException{
        Splash s=new Splash();
        for(int i=0;i<=100;i++){
            Thread.sleep(50);
            
            s.setVisible(true);
            s.load.setText("Loading......"+i);
            s.bar.setValue(i);
            s.bar.setString(i+"%");
            if(i==100){
                F_principale f=new F_principale();
                f.setVisible(true);
                s.dispose();
            }
        }
    }
}
