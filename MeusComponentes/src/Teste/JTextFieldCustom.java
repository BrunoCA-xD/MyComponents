/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import javax.swing.JOptionPane;

/**
 *
 * @author BrunoCA-xD
 */
public class JTextFieldCustom extends javax.swing.JTextField {

    private int MaxLength;
    private String CharCase;
    private boolean CanBeNull;

    public int getMaxLength() {
        return MaxLength;
    }

    public void setMaxLength(int MaxLength) {
        this.MaxLength = MaxLength;
    }

    public String getCharCase() {
        return CharCase;
    }

    public void setCharCase(String CharCase) {
        this.CharCase = CharCase;
    }

    public boolean isCanBeNull() {
        return CanBeNull;
    }

    public void setCanBeNull(boolean CanBeNull) {
        this.CanBeNull = CanBeNull;
    }

    public void jTextKeyTyped(java.awt.event.KeyEvent evt) {
        if ((getText().length() == getMaxLength()) && (getMaxLength() > 0)) {
            evt.consume();

        }

    }
    public JTextFieldCustom(String Texto){
        
        this();
        setText(Texto);
    }
    public JTextFieldCustom(){
        CharCase = "";
        CanBeNull = true;
        MaxLength  = 0;
        Configura();
    }
    

    public void jTextFocusLost(java.awt.event.FocusEvent evt) {
        if (getCharCase().equals("UPPERCASE")) {
            setText(getText().toUpperCase());
        } else if (getCharCase().equals("LOWERCASE")) {
            setText(getText().toLowerCase());
        }

        if ((getText().trim().equals(""))  && (!isCanBeNull()) ) {
            JOptionPane.showMessageDialog(null, "Este campo não pode ficar vazio");
            requestFocus();
        }
    }

    public void Configura() {
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextKeyTyped(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {

                jTextFocusLost(evt);
            }

        });

    }

}
