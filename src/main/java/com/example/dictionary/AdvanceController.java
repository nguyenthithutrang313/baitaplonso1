package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdvanceController {
    // Tab API Google Translate
    @FXML
    public Button btTrans;
    public Button btaBack;
    public TextField tfAddAPI;
    public TextArea taAPI;

    //Return meaning of a word by use api translate
    public static String translate(String text) throws IOException {
        // Insert URL(I create by google script and add to project)
        String urlStr = "https://script.google.com/macros/s/AKfycbzTTcn_UHKVfLhZCa6L_eOVwoR16DUqMhKf-xctzg/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + "vi" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    //Click Translate
    public void clickTrans() throws IOException {
        String temp = translate(tfAddAPI.getText());
        taAPI.setText(temp);
    }

    //Back in api
    public void clickABack(ActionEvent event) throws IOException {
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

    //Tab SQLite
    @FXML
    public Button btsqSearch;
    public Button sqBack;
    public TextField tfEnter;
    public TextArea taExplain;

    public Button btsqSearchV;
    public TextField tfEnterV;
    public TextArea taExplainA;

    private static Connection conn;

    //Connect to database
    public void connect() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:dict_hh.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Return Vietnamese when enter english word. If not find, return ""
    public  String getExplain(String target) throws SQLException {
        String query = "SELECT description FROM av " + "WHERE word=" + "'" + target + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            return resultSet.getString("description");
        }
        return "";
    }

    //Search English -Vietnamese
    public void clicksqSearch() throws SQLException {
        connect();
        if(tfEnter.getText().equals("")) {
            //Allert
            Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
            alert_search.setTitle("Thông báo");
            alert_search.setHeaderText(null);
            alert_search.setContentText("Bạn chưa nhập từ!");
            alert_search.showAndWait();
        } else {
            String explain = getExplain(tfEnter.getText().trim());
            if (explain.equals("")) {
                //Allert
                Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
                alert_search.setTitle("Thông báo");
                alert_search.setHeaderText(null);
                alert_search.setContentText("Không thể tìm thấy từ bạn nhập, vui lòng nhập một từ khác!");
                alert_search.showAndWait();
            } else {
                taExplain.setText(explain);
            }
        }
    }

    //Return Vietnamese when enter Vietnamese word. If not find, return ""
    public  String getVietnamese(String target) throws SQLException {
        String query = "SELECT description FROM va " + "WHERE word=" + "'" + target + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            return resultSet.getString("description");
        }
        return "";
    }

    //Search Vietnamese - English
    public void clicksqSearchV() throws SQLException {
        connect();
        if(tfEnterV.getText().equals("")) {
            //Allert
            Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
            alert_search.setTitle("Thông báo");
            alert_search.setHeaderText(null);
            alert_search.setContentText("Bạn chưa nhập từ!");
            alert_search.showAndWait();
        } else {
            String vietnamese = getVietnamese(tfEnterV.getText().trim());
            if (vietnamese.equals("")) {
                //Allert
                Alert alert_search = new Alert(Alert.AlertType.INFORMATION);
                alert_search.setTitle("Thông báo");
                alert_search.setHeaderText(null);
                alert_search.setContentText("Không thể tìm thấy từ bạn nhập, vui lòng nhập một từ khác!");
                alert_search.showAndWait();
            } else {
                taExplainA.setText(vietnamese);
            }
        }
    }

    //Back in SQLite
    public void clicksqBack(ActionEvent event) throws IOException {
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
