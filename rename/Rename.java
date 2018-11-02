package rename;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;

public class Rename extends JFrame implements ActionListener, KeyListener {

    JFileChooser chooser = new JFileChooser();
    String str = "", item[] = {"　1　", "　2　", "　3　", "　4　", "　5　", "　6　", "　7　", "　8　", "　9　"};
    String file[] = new String[50];
    String tempfile[] = new String[50];
    TextArea ta;
    JButton b1, b2, b3, b4, b5;
    JRadioButton top, buttom;
    JRadioButton top2, buttom2;
    JRadioButton low, up;
    JRadioButton create, newdate;
    JRadioButton half, zenkaku;
    JComboBox jc;
    JTabbedPane tab;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    ButtonGroup bg, bg2, bg3, bg4, bg5;
    Panel Main, p2, p3, p1, p4, p5, p6, p7, p8;
    int count = 0;

    public static void main(String[] args) {
        new Rename();
    }

    Rename() {
        super("ファイル名変更");
        setLayout(null);

        chooser.setMultiSelectionEnabled(true);

        jc = new JComboBox(item);
        tab = new JTabbedPane();
        Main = new Panel(null);
        p1 = new Panel(new FlowLayout());
        p2 = new Panel(new FlowLayout());
        p3 = new Panel(new FlowLayout());
        p4 = new Panel(new FlowLayout());
        p5 = new Panel(new FlowLayout());
        p6 = new Panel(new FlowLayout());
        p7 = new Panel(new FlowLayout());
        p8 = new Panel(new FlowLayout());
        tf1 = new JTextField("/");
        tf2 = new JTextField("0");
        tf3 = new JTextField(".");
        tf4 = new JTextField("0");
        tf5 = new JTextField("サンプル : 0");
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField("0");
        tf9 = new JTextField("");
        b1 = new JButton("リネーム(R)");
        b2 = new JButton("リストクリア(C)");
        b3 = new JButton("入れ替え");
        b4 = new JButton("ファイル選択(S)");
        b5 = new JButton("元に戻す(B)");
        top = new JRadioButton("先頭から", true);
        buttom = new JRadioButton("末尾から");
        top2 = new JRadioButton("先頭から", true);
        buttom2 = new JRadioButton("末尾から");
        create = new JRadioButton("作成日時", true);
        newdate = new JRadioButton("更新日時");

        half = new JRadioButton("アルファベットを半角に変換", true);
        zenkaku = new JRadioButton("アルファベットを全角に変換");

        bg = new ButtonGroup();
        bg2 = new ButtonGroup();
        bg3 = new ButtonGroup();
        bg4 = new ButtonGroup();
        bg5 = new ButtonGroup();

        l1 = new JLabel("文字削除");
        l2 = new JLabel("　　　　　　　　　　　　　　　連番を入れたいところに / をいれてください　　　　　　　　　　　　　　　");
        l3 = new JLabel("拡張子を入力してください");
        l4 = new JLabel("桁数");
        l5 = new JLabel("変更名");
        l6 = new JLabel("　　開始番号");
        l7 = new JLabel("例 : 2000 01 01");
        l8 = new JLabel("→");
        l9 = new JLabel("文字数目に");
        l10 = new JLabel("を追加");
        low = new JRadioButton("すべて小文字に変換", true);
        up = new JRadioButton("すべて大文字に変換");
        l2.setForeground(Color.red);
        tf1.setPreferredSize(new Dimension(350, 30));
        tf2.setPreferredSize(new Dimension(100, 30));
        tf3.setPreferredSize(new Dimension(100, 30));
        tf4.setPreferredSize(new Dimension(100, 30));
        tf1.addKeyListener(this);
        tf4.addKeyListener(this);
        tf5.setEditable(false);
        tf5.setPreferredSize(new Dimension(430, 30));
        tf6.setPreferredSize(new Dimension(100, 30));
        tf7.setPreferredSize(new Dimension(100, 30));
        tf8.setPreferredSize(new Dimension(100, 30));
        tf9.setPreferredSize(new Dimension(350, 30));

        half.setBounds(250, 30, 100, 30);
        zenkaku.setBounds(250, 70, 100, 30);

        bg.add(top);
        bg.add(buttom);
        bg2.add(low);
        bg2.add(up);
        bg3.add(create);
        bg3.add(newdate);
        bg4.add(half);
        bg4.add(zenkaku);
        bg5.add(top2);
        bg5.add(buttom2);

        p1.add(low);
        p1.add(up);

        p2.add(l5);
        p2.add(tf1);
        p2.add(l2);
        p2.add(l4);
        p2.add(jc);
        p2.add(l6);
        p2.add(tf4);
        p2.add(tf5);

        p3.add(top);
        p3.add(buttom);
        p3.add(tf2);
        p3.add(l1);

        p4.add(l3);
        p4.add(tf3);

        p5.add(create);
        p5.add(newdate);
        p5.add(l7);

        p6.add(tf6);
        p6.add(l8);
        p6.add(tf7);
        p6.add(b3);

        p7.add(half);
        p7.add(zenkaku);

        p8.add(top2);
        p8.add(buttom2);
        p8.add(tf8);
        p8.add(l9);
        p8.add(tf9);
        p8.add(l10);

        tab.add("連番(F)", p2);
        tab.add("削除(D)", p3);
        tab.add("大文字・小文字(U)", p1);
        tab.add("拡張子(E)", p4);
        tab.add("タイムスタンプ(T)", p5);
        tab.add("置換(P)", p6);
        tab.add("全角・半角(H)", p7);
        tab.add("追加(A)", p8);

        b1.setMnemonic('R');
        b2.setMnemonic('C');
        b4.setMnemonic('S');
        b5.setMnemonic('B');

        Main.setBounds(0, 10, 840, 185);
        tab.setBounds(10, 5, 680, 180);
        b1.setBounds(695, 5, 130, 30);
        b2.setBounds(695, 75, 130, 30);
        b4.setBounds(695, 110, 130, 70);
        b5.setBounds(695, 40, 130, 30);

        Main.add(tab);
        Main.add(b1);
        Main.add(b2);
        Main.add(b4);
        Main.add(b5);
        add(Main);

        jc.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b4.setBackground(Color.yellow);
        b1.setBackground(Color.red);
        b2.setBackground(Color.CYAN);
        b5.setBackground(Color.lightGray);

        ta = new TextArea();
        ta.setEditable(false);
        ta.setFont(new Font("", Font.PLAIN, 18));
        ta.setBounds(10, 200, 820, 360);
        add(ta);
        new DropTarget(this, new Drop());
        setBounds(100, 100, 840, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        ta.setFocusable(false);
        tf5.setFocusable(false);

        tab.setMnemonicAt(0, 'F');
        tab.setMnemonicAt(1, 'D');
        tab.setMnemonicAt(2, 'U');
        tab.setMnemonicAt(3, 'E');
        tab.setMnemonicAt(4, 'T');
        tab.setMnemonicAt(5, 'P');
        tab.setMnemonicAt(6, 'H');
        tab.setMnemonicAt(7, 'A');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "リネーム(R)":
                if (count > 0) {
                    System.arraycopy(file, 0, tempfile, 0, file.length);
                    switch (tab.getSelectedIndex()) {
                        case 0:
                            continuous(file);
                            break;
                        case 1:
                            delete(file);
                            break;
                        case 2:
                            lowUp(file);
                            break;
                        case 3:
                            kakutyoushi(file);
                            break;
                        case 4:
                            day(file);
                            break;
                        case 5:
                            replace(file);
                            break;
                        case 6:
                            half(file);
                            break;
                        case 7:
                            add(file);
                            break;
                    }
                }
                break;
            case "リストクリア(C)":
                for (int k = 0; k < count; k++) {
                    file[k] = null;
                }
                str = "";
                count = 0;
                ta.setText(str);
                break;
            case "入れ替え":
                String temp;
                temp = tf7.getText();
                tf7.setText(tf6.getText());
                tf6.setText(temp);
                break;
            case "ファイル選択(S)":
                open();
                break;
            case "元に戻す(B)":
                back(file);
                break;
            default:
                sample();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        sample();
    }

    void open() {
        String str1;
        int selected = chooser.showOpenDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (int k = 0; k < files.length; k++) {
                file[k] = files[k].getPath();
                str1 = file[count].substring(file[count].lastIndexOf('\\') + 1);
                str += str1 + "\n";
                count++;
                if (count % 50 == 0) {
                    String temp[] = new String[count + 50];
                    System.arraycopy(file, 0, temp, 0, file.length);
                    file = temp;
                }
                ta.setText(str);
            }
        }
    }

    void back(String strlist[]) {
        str = "";
        for (int k = 0; k < count; k++) {
            File f1 = new File(strlist[k]);
            File f2 = new File(tempfile[k]);
            if (f2.exists()) {
                str += strlist[k].substring(strlist[k].lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            } else {
                f1.renameTo(f2);
                str += tempfile[k].substring(tempfile[k].lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            }

        }
    }

    void add(String strlist[]) {
        str = "";
        int num;
        String str1, str2 = "", str3, newName = "", oldName;
        try {
            num = Integer.valueOf(tf8.getText());
        } catch (NumberFormatException ne) {
            num = 0;
        }
        if (num < 0) {
            num = 0;
        }
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            File f1 = new File(str1);

            str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
            if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                str2 = str1.substring(str1.lastIndexOf('.'));
                oldName = str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.'));
            } else {
                oldName = str1.substring(str1.lastIndexOf('\\') + 1);
            }
            if (oldName.length() < num) {
                num = oldName.length();
            }
            if (top2.isSelected()) {
                newName = oldName.substring(0, num) + tf9.getText() + oldName.substring(num);
            }
            if (buttom2.isSelected()) {
                newName = oldName.substring(0, oldName.length() - num) + tf9.getText() + oldName.substring(oldName.length() - num);
            }
            File f2 = new File(str3 + newName + str2);
            if (f2.exists()) {
                str += str1.substring(str1.lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            } else {
                f1.renameTo(f2);
                file[k] = str3 + newName + str2;
                str += newName + str2 + "\n";
                ta.setText(str);
            }
        }
    }

    void half(String strlist[]) {
        str = "";
        String str1, str2 = "", str3, newName = "", oldName;
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            File f1 = new File(str1);
            str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
            if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                str2 = str1.substring(str1.lastIndexOf('.'));
                oldName = str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.'));
            }else{
                oldName = str1.substring(str1.lastIndexOf('\\') + 1);                
            }
            if (half.isSelected()) {
                for (int i = 0; i < 26; i++) {
                    oldName = oldName.replace((char) ('ａ' + i), (char) ('a' + i));
                    oldName = oldName.replace((char) ('Ａ' + i), (char) ('A' + i));
                }
            }
            if (zenkaku.isSelected()) {
                for (int i = 0; i < 26; i++) {
                    oldName = oldName.replace((char) ('a' + i), (char) ('ａ' + i));
                    oldName = oldName.replace((char) ('A' + i), (char) ('Ａ' + i));
                }
            }
            newName = oldName;
            File f2 = new File(str3 + newName + str2);
            if (f2.exists()) {
                str += str1.substring(str1.lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            } else {
                f1.renameTo(f2);
                file[k] = str3 + newName + str2;
                str += newName + str2 + "\n";
                ta.setText(str);
            }
        }

    }

    void replace(String strlist[]) {
        str = "";
        String str1, str2 = "", str3, newName, oldName;
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            File f1 = new File(str1);
            if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                str2 = str1.substring(str1.lastIndexOf('.'));
                oldName = str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.'));
            }else{
                oldName = str1.substring(str1.lastIndexOf('\\') + 1);                
            }
            str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
            if (tf6.getText() != null && !tf6.getText().equals("")) {
                try {
                    newName = oldName.replaceAll(tf6.getText(), tf7.getText());
                } catch (PatternSyntaxException e) {
                    return;
                }
            } else {
                return;
            }
            File f2 = new File(str3 + newName + str2);
            if (f2.exists()) {
                str += str1.substring(str1.lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            } else {
                f1.renameTo(f2);
                file[k] = str3 + newName + str2;
                str += newName + str2 + "\n";
                ta.setText(str);
            }
        }
    }

    void sample() {

        String temp = "";
        int num;
        int keta = jc.getSelectedIndex() + 1;

        try {
            num = Integer.valueOf(tf4.getText());
        } catch (NumberFormatException ne) {
            num = 0;
        }
        if (num < 0) {
            num = 0;
        }
        int k = 10;
        while (true) {
            if ((int) (num / k) == 0) {
                break;
            }
            keta--;
            k *= 10;
        }
        for (int l = 1; l < keta; l++) {
            temp += "0";
        }
        if (!tf1.getText().contains("/")) {
            tf1.setText(tf1.getText() + "/");
        }
        tf5.setText("サンプル : " + tf1.getText().replace("/", temp + num));

    }

    void continuous(String strlist[]) {
        str = "";
        String str1, str2 = "", str3, newName, temp;
        int num, keta;

        try {
            num = Integer.valueOf(tf4.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "0以上の整数値を入力してください");
            return;
        }
        if (num < 0) {
            JOptionPane.showMessageDialog(null, "0以上の整数値を入力してください");
            return;
        }
        for (int k = 0; k < count; k++) {
            keta = jc.getSelectedIndex() + 1;
            newName = "";
            temp = "";
            str1 = strlist[k];
            File f1 = new File(str1);
            if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                str2 = str1.substring(str1.lastIndexOf('.'));
            }
            str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
            int i = 10;
            while (true) {
                if ((int) (num / i) == 0) {
                    break;
                }
                keta--;
                i *= 10;
            }
            for (int l = 1; l < keta; l++) {
                temp += "0";
            }
            if (!tf1.getText().contains("/")) {
                tf1.setText(tf1.getText() + "/");
            }
            newName = tf1.getText().replaceAll("/", temp + num);

            File f2 = new File(str3 + newName + str2);
            if (f2.exists()) {
                str += str1.substring(str1.lastIndexOf("\\") + 1) + "\n";
                ta.setText(str);
            } else {
                f1.renameTo(f2);
                file[k] = str3 + newName + str2;
                str += newName + str2 + "\n";
                ta.setText(str);
            }
            num++;
        }
    }

    void delete(String strlist[]) {
        String str1, str2 = "", str3, newName = "", oldName;
        str = "";
        int del;
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            try {
                if ((del = Integer.valueOf(tf2.getText())) <= 0) {
                    JOptionPane.showMessageDialog(null, "正の整数値を入力してください");
                    return;
                }
                File f1 = new File(str1);
                if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                    str2 = str1.substring(str1.lastIndexOf('.'));
                    oldName = str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.'));
                } else {
                    oldName = str1.substring(str1.lastIndexOf('\\') + 1);
                }
                str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
                if (del >= oldName.length()) {
                    JOptionPane.showMessageDialog(null, oldName + str2 + "は\n消去する文字数がファイル名より多いので変更できません");
                    str += oldName + str2 + "\n";
                    ta.setText(str);
                    continue;
                }
                if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                    if (top.isSelected()) {
                        newName = str1.substring(str1.lastIndexOf('\\') + 1 + del, str1.lastIndexOf('.'));
                    }
                    if (buttom.isSelected()) {
                        newName = str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.') - del);
                    }
                } else {
                    if (top.isSelected()) {
                        newName = str1.substring(str1.lastIndexOf('\\') + 1 + del);
                    }
                    if (buttom.isSelected()) {
                        newName = str1.substring(str1.lastIndexOf('\\') + 1, str1.length() - del);
                    }
                }
                File f2 = new File(str3 + newName + str2);
                if (f2.exists()) {
                    JOptionPane.showMessageDialog(null, newName + str2 + "と同一名ファイルが存在します\n" + oldName + str2 + "を変更できませんでした");
                    str += oldName + str2 + "\n";
                    ta.setText(str);
                    continue;
                }
                f1.renameTo(f2);
                file[k] = str3 + newName + str2;
                str += newName + str2 + "\n";
                ta.setText(str);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "正の整数値を入力してください");
            }
        }
    }

    void day(String strlist[]) {
        str = "";
        String name[], name1[], newName = "", str1, str2, str3 = "";
        File temp;
        for (int k = 0; k < count; k++) {
            int i = 0;
            try {
                str1 = strlist[k];
                File f1 = new File(str1);
                if (f1.isDirectory()) {
                    JOptionPane.showMessageDialog(null,str1.substring(str1.lastIndexOf('\\') + 1)+ "はディレクトリです");
                    str += str1.substring(str1.lastIndexOf('\\') + 1) + "\n";
                } else {
                    //空白の置き換え
                    if (str1.contains(" ")) {
                        str1 = str1.substring(0, str1.lastIndexOf('\\') + 1) + (str1.substring(str1.lastIndexOf('\\') + 1)).replace(' ', '_');
                        temp = new File(str1);
                        f1.renameTo(temp);
                    } else {
                        temp = f1;
                    }
                    Process child = null;
                    if (create.isSelected()) {
                        child = Runtime.getRuntime().exec("cmd.exe /c chcp 65001 | dir /tc " + str1);
                    }
                    if (newdate.isSelected()) {
                        child = Runtime.getRuntime().exec("cmd.exe /c chcp 65001 | dir /tw " + str1);
                    }
                    InputStream is = child.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.contains(str1.substring(str1.lastIndexOf('\\') + 1))) {
                            name = line.split(" ");
                            name1 = name[0].split("/");
                            newName = name1[0] + " " + name1[1] + " " + name1[2];
                        }
                    }
                    if (!newName.equals("")) {
                        str2 = str1.substring(0, str1.lastIndexOf('\\') + 1) + newName + str1.substring(str1.lastIndexOf('.'));
                        File f2 = new File(str2);
                        if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                            str3 = str1.substring(str1.lastIndexOf('.'));
                        }
                        while (f2.exists()) {
                            str2 = str1.substring(0, str1.lastIndexOf('\\') + 1) + newName + "(" + i + ")" + str3;
                            f2 = new File(str2);
                            i++;
                        }
                        temp.renameTo(f2);
                        file[k] = str2;
                        str += str2.substring(str2.lastIndexOf('\\') + 1) + "\n";
                    } else {
                        JOptionPane.showMessageDialog(null, "エラー");
                        str += str1.substring(str1.lastIndexOf('\\') + 1) + "\n";
                    }
                }
                ta.setText(str);
            } catch (IOException e) {
            }
        }
    }

    void lowUp(String strlist[]) {
        String str1, str2 = "", str3;
        str = "";
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            File f1 = new File(str1);
            str3 = str1.substring(0, str1.lastIndexOf('\\') + 1);
            if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                str2 = str1.substring(str1.lastIndexOf('.'));
                if (up.isSelected()) {
                    str1 = str3 + str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.')).toUpperCase() + str2;
                }
                if (low.isSelected()) {
                    str1 = str3 + str1.substring(str1.lastIndexOf('\\') + 1, str1.lastIndexOf('.')).toLowerCase() + str2;
                }
            } else {
                if (up.isSelected()) {
                    str1 = str3 + str1.substring(str1.lastIndexOf('\\') + 1).toUpperCase() + str2;
                }
                if (low.isSelected()) {
                    str1 = str3 + str1.substring(str1.lastIndexOf('\\') + 1).toLowerCase() + str2;
                }
            }

            file[k] = str1;
            str += str1.substring(str1.lastIndexOf('\\') + 1) + "\n";
            ta.setText(str);
            File f2 = new File(str1);
            f1.renameTo(f2);
        }
    }

    void kakutyoushi(String strlist[]) {
        String str1, str2;
        str = "";
        if (tf3.getText().equals("") || !tf3.getText().startsWith(".")) {
            JOptionPane.showMessageDialog(null, "正しい拡張子を入力してください");
            return;
        }
        for (int k = 0; k < count; k++) {
            str1 = strlist[k];
            File f1 = new File(str1);
            if (f1.isDirectory()) {
                JOptionPane.showMessageDialog(null, str1.substring(str1.lastIndexOf('\\') + 1)+"はディレクトリです");
                str += str1.substring(str1.lastIndexOf('\\') + 1) + "\n";
            } else {
                if ((str1.substring(str1.lastIndexOf('\\'))).contains(".")) {
                    str2 = str1.substring(0, str1.lastIndexOf('.')) + tf3.getText();
                } else {
                    str2 = str1 + tf3.getText();
                }
                File f2 = new File(str2);
                if (f2.exists()) {
                    JOptionPane.showMessageDialog(null, str1.substring(str1.lastIndexOf('\\') + 1) + "は同一ファイルが存在するため\n" + str2.substring(str2.lastIndexOf('\\') + 1) + "に変更できませんでした");
                    str += str1.substring(str1.lastIndexOf('\\') + 1) + "\n";
                } else {
                    f1.renameTo(f2);
                    file[k] = str2;
                    str += str2.substring(str2.lastIndexOf('\\') + 1) + "\n";
                }
            }
            ta.setText(str);
        }
    }

    public class Drop extends DropTargetAdapter {

        String str1;

        Drop() {
        }

        public void drop(DropTargetDropEvent e) {
            try {
                Transferable transfer = e.getTransferable();
                if (transfer.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    java.util.List fileList = (java.util.List) transfer.getTransferData(DataFlavor.javaFileListFlavor);
                    Iterator iterator = fileList.iterator();
                    while (iterator.hasNext()) {
                        file[count] = ((File) iterator.next()).getPath();
                        str1 = file[count].substring(file[count].lastIndexOf('\\') + 1);
                        str += str1 + "\n";
                        count++;
                        if (count % 50 == 0) {
                            String temp[] = new String[count + 50];
                            System.arraycopy(file, 0, temp, 0, file.length);
                            file = temp;

                            String temp2[] = new String[count + 50];
                            System.arraycopy(tempfile, 0, temp2, 0, tempfile.length);
                            tempfile = temp2;
                        }
                    }
                    ta.setText(str);
                }
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }
    }
}
