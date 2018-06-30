/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

   
import Enuns.MeuTipo;
import javax.swing.JTextField;
    
    
public class JDateField extends JTextField{
    
private MeuTipo tipo;
        
     
    
    public MeuTipo getTipo() {
        return tipo;
    }
    public void setTipo(MeuTipo tipo) {
        this.tipo = tipo;
    }   
    public JDateField(String Texto){   
        this();
        setText(Texto);
    }
    
    public JDateField(){

    } 
    
    
} 

