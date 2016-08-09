package sample;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    public String dashTest (){
        String s = "";
        for(int i = 0; i < 60; i++) {
            s += "-";
        }
        s += "|";
        return s;
    }

    public void test(){
        ArrayList<Integer> test1 = new ArrayList<Integer>();
        ArrayList<Integer> test2 = new ArrayList<Integer>();
        ArrayList<Integer> test3 = new ArrayList<Integer>();

    }


    private int staff_max = 10;
    int curMaxStaff = 0;
    int curStaff = 0;

    ArrayList<Integer> backCounter = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> backCounterList = new ArrayList<ArrayList<Integer>>();
    boolean dontMakeNewStaff = false;

    String[] e_arr = new String[staff_max];
    String[] B_arr = new String[staff_max];
    String[] G_arr = new String[staff_max];
    String[] D_arr = new String[staff_max];
    String[] A_arr = new String[staff_max];
    String[] E_arr = new String[staff_max];

    String e_base = "e||--";
    String B_base = "B||--";
    String G_base = "G||--";
    String D_base = "D||--";
    String A_base = "A||--";
    String E_base = "E||--";

    String e = "e||--";
    String B = "B||--";
    String G = "G||--";
    String D = "D||--";
    String A = "A||--";
    String E = "E||--";
    // visuals
    public Label curStaffVis;
    public Label curSpaceVis;

    public Label docMadeVis;
    public Button okButton;

    // Pre-inputs
    public TextField documentInput;
    public TextField artistInput;
    public TextField songInput;
    public TextField capoInput;
    public TextField tuningInput;

    // inputs
    public TextField eInput;
    public TextField BInput;
    public TextField GInput;
    public TextField DInput;
    public TextField AInput;
    public TextField EInput;

    public TextField spacingInput;

    //tab field
    public TextArea tabField;

    // Buttons
    public Button nextButton;
    public Button doneButton;
    public Button endButton;


    public Button newStaffButton;
    public Button prevStaffButton;
    public Button nextStaffButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("View is now loaded!");
        prevStaffButton.setDisable(true);
        nextStaffButton.setDisable(true);
        docMadeVis.setVisible(false);
        okButton.setVisible(false);
    }

    public void documentMade(){
        docMadeVis.setVisible(false);
        okButton.setVisible(false);
    }

    public String addSpacing(){

        int i = Integer.parseInt(spacingInput.getText());
        String spacing = "";

        while(i > 0){
            spacing += "-";
            i--;
        }

        return spacing;

    }

    // return false if more than one number
    public boolean checkSingleDigit(){

        if(eInput.getText().length() > 1) {
            return false;
        }else if(BInput.getText().length() > 1){
            return false;
        }else if(GInput.getText().length() > 1){
            return false;
        }else if(DInput.getText().length() > 1){
            return false;
        }else if(AInput.getText().length() > 1){
            return false;
        }else if(EInput.getText().length() > 1){
            return false;
        }else{
            return true;
        }

    }

    public boolean checkThisSingleDigit(String s){
        if(s.length() > 1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkEmpty(){
        if(eInput.getText().length() > 0) {
            return false;
        }else if(BInput.getText().length() > 0){
            return false;
        }else if(GInput.getText().length() > 0){
            return false;
        }else if(DInput.getText().length() > 0){
            return false;
        }else if(AInput.getText().length() > 0){
            return false;
        }else if(EInput.getText().length() > 0){
            return false;
        }else{
            return true;
        }
    }

    public void updateStaffPage(int i){
        curStaffVis.setText(Integer.toString(i + 1));
    }
    public void updateCurSpaceLeft(int i){
        int curPage = Integer.parseInt(curSpaceVis.getText());
        curSpaceVis.setText(Integer.toString(curPage + i));
    }

    public void saveStaff(){
        e_arr[curStaff] = e;
        B_arr[curStaff] = B;
        G_arr[curStaff] = G;
        D_arr[curStaff] = D;
        A_arr[curStaff] = A;
        E_arr[curStaff] = E;

        backCounterList.add(curStaff, backCounter);
        backCounter = new ArrayList<Integer>();
    }

    public void saveStaff(int i){
        e_arr[curStaff] = e;
        B_arr[curStaff] = B;
        G_arr[curStaff] = G;
        D_arr[curStaff] = D;
        A_arr[curStaff] = A;
        E_arr[curStaff] = E;

        backCounterList.set(i, backCounter);
        backCounter = new ArrayList<Integer>();
    }



    public void loadStaff(){
        e = e_arr[curStaff];
        B = B_arr[curStaff];
        G = G_arr[curStaff];
        D = D_arr[curStaff];
        A = A_arr[curStaff];
        E = E_arr[curStaff];

        backCounter = backCounterList.get(curStaff);
    }

    public void newStaff(){
        e = e_base;
        B = B_base;
        G = G_base;
        D = D_base;
        A = A_base;
        E = E_base;
    }



    public void newStaffButtonClicked(){
        // woo magic numbers @_@...
        if( curStaff > staff_max - 3){ newStaffButton.setDisable(true); }
        if(curStaff == 0) { prevStaffButton.setDisable(false); }
        if(dontMakeNewStaff) {dontMakeNewStaff = false;}

        saveStaff();

        curStaff++;
        curMaxStaff++;

        updateStaffPage(curStaff);
        newStaff();
        // more stuff reset tabs, etc.
        curSpaceVis.setText("2");
        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }

    public void resetStaffButtonClicked(){
        newStaff();
        curSpaceVis.setText("2");
        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }


    public void prevStaffButtonClicked() {
        if(curStaff == curMaxStaff && !dontMakeNewStaff){
            saveStaff();
            dontMakeNewStaff = true;
        }else{
            saveStaff(curStaff);
        }
        curStaff--;

        if(curStaff == 0){ prevStaffButton.setDisable(true); }
        if(curStaff == curMaxStaff - 1){ nextStaffButton.setDisable(false); }

        loadStaff();

        updateStaffPage(curStaff);

        curSpaceVis.setText(Integer.toString(e.length()-3));
        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }

    public void nextStaffButtonClicked() {
        saveStaff(curStaff);

        curStaff++;

        if(curStaff == 1) {prevStaffButton.setDisable(false); }
        if(curStaff == curMaxStaff){ nextStaffButton.setDisable(true); }

        loadStaff();

        updateStaffPage(curStaff);
        curSpaceVis.setText(Integer.toString(e.length()-3));
        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }
    // good
    public void clearButtonClicked(){
        eInput.clear();
        BInput.clear();
        GInput.clear();
        DInput.clear();
        AInput.clear();
        EInput.clear();
    }

    public void endButtonClicked(){
        e += "-|";
        B += "-|";
        G += "-|";
        D += "-|";
        A += "-|";
        E += "-|";

        backCounter.add(2);
        updateCurSpaceLeft(2);

        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }

    // parfect? :D
    public void backButtonClicked(){
        int num;
        if( backCounter.isEmpty()){
            return;
        }else{
            num = backCounter.get(backCounter.size()-1);

            e = e.substring(0, e.length() - num);
            B = B.substring(0, B.length() - num);
            G = G.substring(0, G.length() - num);
            D = D.substring(0, D.length() - num);
            A = A.substring(0, A.length() - num);
            E = E.substring(0, E.length() - num);

            backCounter.remove(backCounter.size()-1);
            updateCurSpaceLeft(-num);
        }

        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }



    public void resetSpacingButtonClicked(){
        spacingInput.setText("2");
    }

    // add other inputs
    public void nextButtonClicked(){

        int prevLength = 0;
        int curLength = 0;

        boolean check = checkSingleDigit();
        boolean empty = checkEmpty();



        //System.out.println(e.length());
        //System.out.println(check);
        //check length here
        prevLength = e.length();

        if(eInput.getText().equals("")){
            if( e.length() == 5){
                e += "-";
                if(!check){
                    e += "-";
                }
            }else{
                e += addSpacing();
                if(!empty) {
                    e += "-";
                    if(!check){
                        e += "-";
                    }
                }
            }
        }else{
            if(e.length() == 5){
                if(checkThisSingleDigit(eInput.getText()) && !check){
                    e += "-";
                }
                e += eInput.getText();

            }else{
                if(checkThisSingleDigit(eInput.getText()) && !check){
                    e += "-";
                }
                e += addSpacing();
                e += eInput.getText();
            }
        }

        // end check length and add to counter
        curLength = e.length();
        backCounter.add(curLength - prevLength);
        updateCurSpaceLeft(curLength - prevLength);


        if(BInput.getText().equals("")){
            if(B.length() == 5){
                B += "-";
                if(!check){
                    B += "-";
                }
            }else{
                B += addSpacing();
                if(!empty) {
                    B += "-";
                    if(!check){
                        B += "-";
                    }
                }
            }
        }else{
            if(B.length() == 5){
                if(checkThisSingleDigit(BInput.getText()) && !check){
                    B += "-";
                }
                B += BInput.getText();
            }else{
                if(checkThisSingleDigit(BInput.getText()) && !check){
                    B += "-";
                }
                B += addSpacing();
                B += BInput.getText();
            }
        }

        if(GInput.getText().equals("")){
            if(G.length() == 5){
                G += "-";
                if(!check){
                    G += "-";
                }
            }else{
                G += addSpacing();
                if(!empty) {
                    G += "-";
                    if(!check){
                        G += "-";
                    }
                }
            }
        }else{
            if(G.length() == 5){
                if(checkThisSingleDigit(GInput.getText()) && !check){
                    G += "-";
                }
                G += GInput.getText();
            }else{
                if(checkThisSingleDigit(GInput.getText()) && !check){
                    G += "-";
                }
                G += addSpacing();
                G += GInput.getText();
            }
        }

        if(DInput.getText().equals("")){
            if(D.length() == 5){
                D += "-";
                if(!check){
                    D += "-";
                }
            }else{
                D += addSpacing();
                if(!empty) {
                    D += "-";
                    if(!check){
                        D += "-";
                    }
                }
            }
        }else{
            if(D.length() == 5){
                if(checkThisSingleDigit(DInput.getText()) && !check){
                    D += "-";
                }
                D += DInput.getText();
            }else{
                if(checkThisSingleDigit(DInput.getText()) && !check){
                    D += "-";
                }
                D += addSpacing();
                D += DInput.getText();
            }
        }

        if(AInput.getText().equals("")){
            if(A.length() == 5){
                A += "-";
                if(!check){
                    A += "-";
                }
            }else{
                A += addSpacing();
                if(!empty) {
                    A += "-";
                    if(!check){
                        A += "-";
                    }
                }
            }
        }else{
            if(A.length() == 5){
                if(checkThisSingleDigit(AInput.getText()) && !check){
                    A += "-";
                }
                A += AInput.getText();
            }else{
                if(checkThisSingleDigit(AInput.getText()) && !check){
                    A += "-";
                }
                A += addSpacing();
                A += AInput.getText();
            }
        }

        if(EInput.getText().equals("")){
            if(E.length() == 5){
                E += "-";
                if(!check){
                    E += "-";
                }
            }else{
                E += addSpacing();
                if(!empty) {
                    E += "-";
                    if(!check){
                        E += "-";
                    }
                }
            }
        }else{
            if(E.length() == 5){
                if(checkThisSingleDigit(EInput.getText()) && !check){
                    E += "-";
                }
                E += EInput.getText();
            }else{
                if(checkThisSingleDigit(EInput.getText()) && !check){
                    E += "-";
                }
                E += addSpacing();
                E += EInput.getText();
            }
        }



        tabField.setText(e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E);
    }

    public void doneButtonClicked(){
        saveStaff();
        //System.out.println(eInput.getText());

        XWPFDocument document = new XWPFDocument();
        XWPFParagraph title = document.createParagraph();
        XWPFRun run = title.createRun();
        run.setFontSize(12);
        run.setFontFamily("Courier New");

        run.setText("Guitar Tab Maker");
        run.addBreak();
        run.setText("Artist: " + artistInput.getText());
        run.addBreak();
        run.setText("Title: " + songInput.getText());
        run.addBreak();

        if(!capoInput.getText().equals("")){
            run.setText("Capo: " + capoInput.getText());
            run.addBreak();
        }

        if(!tuningInput.getText().equals("")){
            run.setText("Tuning: " + tuningInput.getText());
            run.addBreak();
        }else{
            run.setText("Tuning: Standard");
            run.addBreak();
        }

        for(int i = 0; i < curMaxStaff + 1; i++){
            run.addBreak();
            run.setText(e_arr[i]);
            run.addBreak();
            run.setText(B_arr[i]);
            run.addBreak();
            run.setText(G_arr[i]);
            run.addBreak();
            run.setText(D_arr[i]);
            run.addBreak();
            run.setText(A_arr[i]);
            run.addBreak();
            run.setText(E_arr[i]);
            run.addBreak();
        }

        try{
            FileOutputStream output;
            if(documentInput.getText().equals("")){
                 output = new FileOutputStream("test.docx");
            }else{
                String documentName = documentInput.getText() + ".docx";
                output = new FileOutputStream(documentName);
            }

            document.write(output);

            // close everything
            output.close();
            document.close();
            System.out.println("SUCESS: DOCUMENT MADE");
            docMadeVis.setVisible(true);
            okButton.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}