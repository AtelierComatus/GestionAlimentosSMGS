/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static View.oferta.isNumeric;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class DB {
    
        public void loadData(){
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:MySQL://localhost:3306/pfrutas";
            String user = "root";
            String pass = "";

            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();

           DefaultTableModel modelo = new DefaultTableModel(new String[]{"Id", "Fruta", "Cantidad"}, 0);

           Tabla.setModel(modelo);
           String sql = "SELECT * FROM pedido";

           ResultSet rs = st.executeQuery(sql);
           String i, F, C;
           while(rs.next()){
             i = rs.getString("id");
             F = rs.getString("Fruta");
             C = rs.getString("Cantidad");

             modelo.addRow(new Object[]{i, F, C});
           }

         }catch(Exception e){
                System.out.println("Error "+ e.getMessage());
         }   
    }  
        
        
        public void Create(){
               String Un, Fr, query, FrutaSelec;
        
        
        //connect to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:MySQL://localhost:3306/pfrutas";
            String user = "root";
            String pass = "";
            
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            
            if("".equals(unidadesF.getText())){
             
                JOptionPane.showMessageDialog(new JFrame(), "Ingrese la cantidad del producto que desee ", "Dialog",
                                     JOptionPane.ERROR_MESSAGE);
            }else if(!isNumeric(unidadesF.getText())){
             
                JOptionPane.showMessageDialog(new JFrame(), "Ingrese un numero", "Dialog",
                                     JOptionPane.ERROR_MESSAGE);
            }else {
                FrutaSelec = (String) Frutas.getSelectedItem();
                Fr = FrutaSelec;
                Un = unidadesF.getText();
                
                query = "INSERT INTO pedido (Fruta, Cantidad) "
                        + "VALUES ('"+Fr+"','"+Un+"')";
               
               st.executeUpdate(query);
               FrutVerd.setText("");
               unidadesF.setText("1");
              
               showMessageDialog(null, "Pedido exitoso");
               loadData();
               con.close();
            }   
        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            
        }
    }
    
}
