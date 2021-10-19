package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RepairController {
    @FXML
    public Button btAdd;
    public Button btRepair;
    public Button btDelete;
    public Button btrBack;

    public TextField tfAddE;
    public TextField tfAddV;
    public TextField tfAddType;
    public TextField tfAddPro;

    @FXML
    //Add word by click button
    public void clickAddWord() throws IOException{
        String wordE = tfAddE.getText();
        String wordType = tfAddType.getText();
        String wordPro = tfAddPro.getText();
        String wordV = tfAddV.getText();

        if (wordE.equals("")) {
            //Alert
            Alert alert_del = new Alert(Alert.AlertType.INFORMATION);
            alert_del.setTitle("Thông báo");
            alert_del.setHeaderText(null);
            alert_del.setContentText("Bạn chưa nhập từ để thêm, vui lòng kiểm tra lại!");
            alert_del.showAndWait();
        } else {
            if (DictionaryManagement.dictionaryLookup(wordE) == 0) {
                if (wordType.equals("") || wordPro.equals("") || wordV.equals("")) {
                    //Allert
                    Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
                    alert_search.setTitle("Thông báo");
                    alert_search.setHeaderText(null);
                    alert_search.setContentText("Bạn chưa nhập đủ dữ liệu, vui lòng kiểm tra lại!");
                    alert_search.showAndWait();
                } else {
                    DictionaryManagement.insertWord(wordE, wordType, wordPro, wordV);
                    Word newWord = new Word(wordE, wordType, wordPro, wordV);
                    Dictionary.DictionaryEV.add(newWord);

                    //Allert
                    Alert alert_add = new Alert(Alert.AlertType.INFORMATION);
                    alert_add.setTitle("Thông báo");
                    alert_add.setHeaderText(null);
                    alert_add.setContentText("Đã thêm từ vào từ điển!");
                    alert_add.showAndWait();
                }
            } else {
                //Allert
                Alert alert_noadd = new Alert(Alert.AlertType.INFORMATION);
                alert_noadd.setTitle("Thông báo");
                alert_noadd.setHeaderText(null);
                alert_noadd.setContentText("Không thể thực hiện thao tác này vì từ tiếng Anh bạn nhập đã có trong từ điển!");
                alert_noadd.showAndWait();
            }
        }
    }

    //Repair when click button
    public void clickRepairWord() throws IOException{
        String wordE = tfAddE.getText();
        String wordType = tfAddType.getText();
        String wordPro = tfAddPro.getText();
        String wordV = tfAddV.getText();

        if (wordE.equals("")) {
            //Alert
            Alert alert_del = new Alert(Alert.AlertType.INFORMATION);
            alert_del.setTitle("Thông báo");
            alert_del.setHeaderText(null);
            alert_del.setContentText("Bạn chưa nhập từ để sửa, vui lòng kiểm tra lại!");
            alert_del.showAndWait();
        } else {
            if (DictionaryManagement.dictionaryLookup(wordE) != 0) {
                if (wordType.equals("") || wordPro.equals("") || wordV.equals("")) {
                    //Allert
                    Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
                    alert_search.setTitle("Thông báo");
                    alert_search.setHeaderText(null);
                    alert_search.setContentText("Bạn chưa nhập đủ dữ liệu, vui lòng kiểm tra lại!");
                    alert_search.showAndWait();
                } else {
                    DictionaryManagement.repairWord(wordE, wordE, wordType, wordPro, wordV);
                    Dictionary.DictionaryEV.clear();
                    DictionaryManagement.insertFromFile();

                    //Alert
                    Alert alert_repair = new Alert(Alert.AlertType.INFORMATION);
                    alert_repair.setTitle("Thông báo");
                    alert_repair.setHeaderText(null);
                    alert_repair.setContentText("Đã sửa từ trong từ điển!");
                    alert_repair.showAndWait();
                }
            } else {
                //Alert
                Alert alert_norepair = new Alert(Alert.AlertType.INFORMATION);
                alert_norepair.setTitle("Thông báo");
                alert_norepair.setHeaderText(null);
                alert_norepair.setContentText("Không thể thực hiện thao tác này vì từ tiếng Anh bạn nhập không có trong từ điển!");
                alert_norepair.showAndWait();
            }
        }
    }

    //Delete when click button
    public void clickDeleteWord() throws IOException{
        String wordE = tfAddE.getText();
        if (wordE.equals("")) {
            //Alert
            Alert alert_del = new Alert(Alert.AlertType.INFORMATION);
            alert_del.setTitle("Thông báo");
            alert_del.setHeaderText(null);
            alert_del.setContentText("Bạn chưa nhập từ để xóa, vui lòng kiểm tra lại!");
            alert_del.showAndWait();
        } else {
            if (DictionaryManagement.dictionaryLookup(wordE) != 0) {
                DictionaryManagement.deleteWord(wordE);
                Dictionary.DictionaryEV.clear();
                DictionaryManagement.insertFromFile();

                //Alert
                Alert alert_delete = new Alert(Alert.AlertType.INFORMATION);
                alert_delete.setTitle("Thông báo");
                alert_delete.setHeaderText(null);
                alert_delete.setContentText("Đã xóa từ trong từ điển!");
                alert_delete.showAndWait();
            } else {
                //Allert
                Alert alert_nodelete = new Alert(Alert.AlertType.INFORMATION);
                alert_nodelete.setTitle("Thông báo");
                alert_nodelete.setHeaderText(null);
                alert_nodelete.setContentText("Không thể thực hiện thao tác này vì từ tiếng Anh bạn nhập không có trong từ điển!");
                alert_nodelete.showAndWait();
            }
        }
    }

    public void clickRpBack(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("menu.fxml"));
            Parent mainGUI = loader.load();
            Scene scene = new Scene(mainGUI);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}