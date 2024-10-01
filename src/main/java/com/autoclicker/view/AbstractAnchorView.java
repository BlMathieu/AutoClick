package com.autoclicker.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mb
 */
public abstract class AbstractAnchorView extends AnchorPane {

    public AbstractAnchorView(String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(filename));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }
}
