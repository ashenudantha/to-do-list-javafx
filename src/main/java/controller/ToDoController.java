package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import model.ToDo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToDoController implements Initializable {
    public ListView listview;
    public TextArea inputfielddescription;
    public TableView table;
    public TableColumn datecol;
    public TableColumn titlecol;
    public TextArea inputfieldtitle;
    public DatePicker date;


    @FXML

    private ObservableList<ToDo> taskList = FXCollections.observableArrayList();

    public void showData() {
        try {

            Connection connection = DbConnection.getInstance().getConnection();
            taskList.clear();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM todolist");

            listview.getItems().clear(); // Clear old UI entries

            while (resultSet.next()) {
                ToDo todo = new ToDo(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("date").toLocalDate()
                );

                CheckBox cb = new CheckBox(todo.getTitle()+" | " +todo.getDescription()+ " (" + todo.getDate() + ")");
                cb.setOnAction(event -> {
                    if (cb.isSelected()) {
                        System.out.println(" Selected: " + cb.getText());

                    }
                });
                listview.getItems().add(cb);



            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addToOnClick(ActionEvent actionEvent) {
        String task = inputfieldtitle.getText();
        String description = inputfielddescription.getText();
        LocalDate value = this.date.getValue();

        String sql = "INSERT INTO todolist VALUES(?,?,?)";

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,task);
            preparedStatement.setObject(2,value);
            preparedStatement.setString(3,description);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        inputfieldtitle.clear();
        inputfielddescription.clear();
        date.setValue(null);
        showData();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listview.setItems(taskList);
        showData();

}}
