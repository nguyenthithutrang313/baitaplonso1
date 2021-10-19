package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    public Button btSearch;
    public Button US;
    public Button btBack;
    public Button btiBack;
    public TextField tfEnterWord;

    public TextArea taWord;
    public TextArea taType;
    public TextArea taPro;
    public TextArea taMeaning;

    public ObservableList<String> ele = FXCollections.observableArrayList();

    @FXML
    ListView<String> lv = new ListView<>();

    DictionaryCommandLine dc = new DictionaryCommandLine();

    //List view word
    public void search() {
        ele.clear();
        String temp = tfEnterWord.getText();
        ArrayList<String> list = new ArrayList<>();
        list = dc.dictionarySearch(temp);
        for (int i = 0; i < list.size(); i++) {
            ele.add(list.get(i));
        }
        lv.setItems(ele);
    }

    //When click button Search
    public void setBtSearch() throws IOException {
        String temp = tfEnterWord.getText();
        if (temp.equals("")) {
            //Allert
            Alert alert_search = new Alert(AlertType.INFORMATION);
            alert_search.setTitle("Thông báo");
            alert_search.setHeaderText(null);
            alert_search.setContentText("Bạn chưa nhập từ!");
            alert_search.showAndWait();
        } else {
            if (DictionaryManagement.dictionaryLookup(temp) != 0) {
                //Word
                String word = Dictionary.DictionaryEV.get(DictionaryManagement.dictionaryLookup(temp) - 1).getWord_target().trim();
                taWord.setText(word);

                //Type
                String typeWord = Dictionary.DictionaryEV.get(DictionaryManagement.dictionaryLookup(temp) - 1).getWord_type().trim();
                taType.setText(typeWord);

                //Pronunciation
                String proWord = Dictionary.DictionaryEV.get(DictionaryManagement.dictionaryLookup(temp) - 1).getWord_pronun().trim();
                taPro.setText(proWord);

                //Meaning
                String explainWord = Dictionary.DictionaryEV.get(DictionaryManagement.dictionaryLookup(temp) - 1).getWord_explain().trim();
                taMeaning.setText(explainWord);
            } else {
                //Allert
                Alert alert_search = new Alert(AlertType.INFORMATION);
                alert_search.setTitle("Thông báo");
                alert_search.setHeaderText(null);
                alert_search.setContentText("Không thể tìm thấy từ bạn nhập, vui lòng nhập một từ khác!");
                alert_search.showAndWait();
            }
        }
    }

    //Click a word when find by list view
    public void clickSearch() {
        String temp = lv.getSelectionModel().getSelectedItem();
        tfEnterWord.setText(temp);
    }

    //Read word when click voice
    public void clickVoice() {
        String temp = taWord.getText();
        if (temp.equals("")) {
            //Allert
            Alert alert_search = new Alert(AlertType.INFORMATION);
            alert_search.setTitle("Thông báo");
            alert_search.setHeaderText(null);
            alert_search.setContentText("Không thể đọc từ vì bạn chưa tìm kiếm!");
            alert_search.showAndWait();
        }
        if (DictionaryManagement.dictionaryLookup(temp) != 0) {
            textVoice.TTS(temp);
        }
    }

    //Click Back in sample.fxml
    public void clickBack(ActionEvent event) throws IOException {
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

    //Click back in infor.fxml
    public void clickInBack(ActionEvent event) throws IOException {
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
