package Teste;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author BrunoCA-xD
 */
public class JCustomTexfield extends javax.swing.JFormattedTextField {

    private int bMaxLength;
    private boolean bCanBeNull;
    private Types Tipo_De_Dados;
    private boolean bValid = false;

    public Types getTipo_De_Dados() {
        return Tipo_De_Dados;
    }

    /**
     *
     * @param Tipo_De_Dados
     */
    @SuppressWarnings("unchecked")
    public void setTipo_De_Dados(Types Tipo_De_Dados) {
        this.setText("");
        this.Tipo_De_Dados = Tipo_De_Dados;
        if (null != Tipo_De_Dados) {
            switch (Tipo_De_Dados) {
                case CNPJ:
                    maskCNPJ(this);
                    break;
                case CPF:
                    maskCPF(this);
                    break;
                case RG:
                    maskRG(this);
                    break;
                case Tel:
                    maskTel(this);
                    break;
                case Cel:
                    maskCel(this);
                    break;
                default:
                    break;
            }
        }
    }

    public int getMaxLength() {
        return bMaxLength;
    }

    public void setMaxLength(int MaxLength) {
        this.bMaxLength = MaxLength;
    }

    public boolean isCanBeNull() {
        return bCanBeNull;
    }

    public void setCanBeNull(boolean CanBeNull) {
        this.bCanBeNull = CanBeNull;
    }

    public void jTextKeyTyped(java.awt.event.KeyEvent evt) {
        if ((getText().length() == getMaxLength()) && (getMaxLength() > 0)) {
            evt.consume();
        }

    }

    public void jTextFocusLost(java.awt.event.FocusEvent evt) {

        Valid();
    }

    public JCustomTexfield(String sText, boolean bNull, int iMaxLenght) {
        setMaxLength(iMaxLenght);
        setCanBeNull(bNull);
        Configura();
        setText(sText);

    }

    public JCustomTexfield(String Texto) {
        Configura();
        setText(Texto);

    }

    public JCustomTexfield() {
        Configura();
    }
// Mascaras //

    public void maskCNPJ(JCustomTexfield jCnpj) {
        MaskFormatter Mask;
        try {
            //Cria uma nova mascara e seta o formato (**.***.***/****-**)
            Mask = new MaskFormatter("**.***.***/****-**");
            Mask.setValidCharacters("0123456789"); // Seta pra só aceitar numeros
            Mask.install(jCnpj);// instala a mascara no textField

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir mask no campo: "
                    + e.getMessage());
        }
    }

    public void maskCPF(JCustomTexfield jcpf) {
        MaskFormatter Mask;
        try {

            Mask = new MaskFormatter("***.***.***-**");
            Mask.setValidCharacters("0123456789");
            Mask.install(jcpf);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir mask no campo: "
                    + e.getMessage());
        }
    }

    public void maskRG(JCustomTexfield jRG) {
        MaskFormatter Mask;
        try {
            Mask = new MaskFormatter("**.***.***-*");
            Mask.setValidCharacters("0123456789");
            Mask.install(jRG);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir mask no campo: "
                    + e.getMessage());
        }
    }

    public void maskTel(JCustomTexfield jTel) {
        MaskFormatter Mask;
        try {
            Mask = new MaskFormatter("(**)****-****");
            Mask.setValidCharacters("0123456789");
            Mask.install(jTel);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir mask no campo: "
                    + e.getMessage());
        }
    }

    public void maskCel(JCustomTexfield jCel) {
        MaskFormatter Mask;
        try {
            Mask = new MaskFormatter("(**)*****-****");
            Mask.setValidCharacters("0123456789");
            Mask.install(jCel);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel inserir mask nos campos letras: "
                    + e.getMessage());
        }
    }

// validações //
    private boolean authenticateCnpj(String jCnpj) {
        String sValue = jCnpj;
        Pattern pattern = Pattern.compile("(\\d{2}.\\d{3}.\\d{3}\\/\\d{4}-\\d{2})"); //Validador de padroes 
        //(\d{2}.\d{3}.\d{3}\/\d{4}-\d{2}) -> [\d{2}] = decimal (2casas) obrigatorio [.] ponto obrigatorio [\/] / obrigatoria 
        Matcher matcher = pattern.matcher(sValue); //Combinador que checa se o o validador combina com o texto do txt
        if (matcher.find()) {

            String[] sValueAux = sValue.split("-");
            int iAdd = 0, j = 9, k = 5, iRest;
            sValueAux[0] = sValueAux[0].replaceAll("\\.", "");
            sValueAux[0] = sValueAux[0].replace("/", "");
            for (int i = 0; i < 12; i++) {
                if (i < 4) {
                    iAdd += (Integer.parseInt(sValueAux[0].substring(i, i + 1)) * k);
                    k--;
                } else {
                    iAdd += (Integer.parseInt(sValueAux[0].substring(i, i + 1)) * j);
                    j--;
                }
            }
            iRest = iAdd % 11;
            if (iRest < 2) {
                sValueAux[0] += "0";
            } else {
                iRest = 11 - iRest;
                sValueAux[0] += String.valueOf(iRest);
            }
            k = 6;
            j = 9;
            iAdd = 0;
            for (int i = 0; i < 13; i++) {
                if (i < 5) {
                    iAdd += (Integer.parseInt(sValueAux[0].substring(i, i + 1)) * k);
                    k--;
                } else {
                    iAdd += (Integer.parseInt(sValueAux[0].substring(i, i + 1)) * j);
                    j--;
                }
            }
            iRest = iAdd % 11;
            if (iRest < 2) {
                sValueAux[0] += "0";
            } else {
                iRest = 11 - iRest;
                sValueAux[0] += String.valueOf(iRest);
            }
            try {
                MaskFormatter format;
                format = new MaskFormatter("**.***.***/****-**");
                format.setValueContainsLiteralCharacters(false);
                sValueAux[0] = format.valueToString(sValueAux[0]);
            } catch (ParseException e) {
            }
            return sValueAux[0].equals(sValue);
        }
        return false;

    }

    private boolean ValidaCpf(String sCpf) {
        String sValor = sCpf;
        Pattern pattern = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
        Matcher matcher = pattern.matcher(sValor);
        if (matcher.find()) {
            String[] sValorAux = sValor.split("-");
            int iSoma = 0, j = 10, iResto;
            sValorAux[0] = sValorAux[0].replaceAll("\\.", "");

            for (int i = 0; i < 9; i++) {
                iSoma += (Integer.parseInt(sValorAux[0].substring(i, i + 1)) * j);
                j--;
            }
            iResto = iSoma % 11;
            iResto = 11 - iResto;
            if (iResto > 9) {
                sValorAux[0] += "0";
            } else {
                sValorAux[0] += String.valueOf(iResto);
            }
            j = 11;
            iSoma = 0;
            for (int i = 0; i < 9 + 1; i++) {
                iSoma += (Integer.parseInt(sValorAux[0].substring(i, i + 1)) * j);
                j--;
            }
            iResto = iSoma % 11;
            if (iResto < 2) {
                sValorAux[0] = sValorAux[0] + "0";
            } else {

                iResto = 11 - iResto;
                sValorAux[0] = sValorAux[0] + String.valueOf(iResto);
            }
            try {
                MaskFormatter format;
                format = new MaskFormatter("***.***.***-**");
                format.setValueContainsLiteralCharacters(false);
                sValorAux[0] = format.valueToString(sValorAux[0]);
            } catch (ParseException e) {
            }
            return sValorAux[0].equals(sValor);
        }
        return false;
    }

    private boolean ValidaRG(String sRg) {
        String sValor = sRg;
        Pattern pattern = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}$");
        Matcher matcher = pattern.matcher(sValor);
// incluir validação com calculo de digitos vereficadores
//        if (matcher.find()) {
//            String[] sValorAux = sValor.split("-");
//            int iSoma = 0, j = 10, iResto;
//            sValorAux[0] = sValorAux[0].replaceAll("\\.", "");
//
//            for (int i = 0; i < 9; i++) {
//                iSoma += (Integer.parseInt(sValorAux[0].substring(i, i + 1)) * j);
//                j--;
//            }
//            iResto = iSoma % 11;
//            iResto = 11 - iResto;
//            if (iResto > 9) {
//                sValorAux[0] += "0";
//            } else {
//                sValorAux[0] += String.valueOf(iResto);
//            }
//            j = 11;
//            iSoma = 0;
//            for (int i = 0; i < 9 + 1; i++) {
////                JOptionPane.showMessageDialog(null, sValorAux[0].substring(i, i + 1));
//
//                iSoma += (Integer.parseInt(sValorAux[0].substring(i, i + 1)) * j);
//                j--;
//            }
//            JOptionPane.showMessageDialog(null, "Soma " + iSoma);
//            iResto = iSoma % 11;
//            JOptionPane.showMessageDialog(null, iResto);
//            if (iResto < 2) {
//                sValorAux[0] = sValorAux[0] + "0";
//            } else {
//
//                iResto = 11 - iResto;
//                sValorAux[0] = sValorAux[0] + String.valueOf(iResto);
//            }
//            try {
//                MaskFormatter format;
//                format = new MaskFormatter("***.***.***-**");
//                format.setValueContainsLiteralCharacters(false);
//                sValorAux[0] = format.valueToString(sValorAux[0]);
//            } catch (ParseException e) {
//            }
//            return sValorAux[0].equals(sValor);
//        }
        return matcher.find();
    }

    private boolean ValidaTel(String sTel) {
        String sValor = sTel;
        Pattern pattern = Pattern.compile("^(\\((\\d{2})\\)[9]?)[2-9]\\d{3}[-]?\\d{4}$");
        Matcher matcher = pattern.matcher(sValor);

        return matcher.find();
    }

    private void Valid() {
        if ((!getText().trim().equals("")) && (isCanBeNull())) { // Valida se o txt ta vazio e se nao pode estar vazio
            bValid = true;
        } else if ((getTipo_De_Dados() == Types.CNPJ) && (authenticateCnpj(this.getText()))) { // se o tipo for TAL, ele chama o metodo ValidaTal
            bValid = true;
        } else if ((getTipo_De_Dados() == Types.CPF) && (ValidaCpf(this.getText()))) {
            bValid = true;
        } else if ((getTipo_De_Dados() == Types.RG) && (ValidaRG(this.getText()))) {
            bValid = true;
        } else if ((getTipo_De_Dados() == Types.Tel || getTipo_De_Dados() == Types.Cel) && ValidaTel(this.getText())) {
            bValid = true;
        } else {
            bValid = false;
        }
    }

    public void Configura() {

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                Valid();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {

            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                Valid();
            }

        });
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

    public boolean isbValid() {
        return bValid;
    }

    public void setbValid(boolean bValid) {
        this.bValid = bValid;
    }
}

enum Types {

    CPF, CNPJ, RG, Tel, Cel
}
