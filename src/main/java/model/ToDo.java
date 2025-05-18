package model;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import lombok.*;

import java.awt.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ToDo {
    private String title;
    private String description;
    private LocalDate date;
    private CheckBox checkBox;

    public ToDo(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }



}
