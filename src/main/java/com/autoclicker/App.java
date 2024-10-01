package com.autoclicker;

import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.autoclicker.view.AbstractAnchorView;
import com.autoclicker.view.PrimaryView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, NativeHookException {
        GlobalScreen.registerNativeHook();
        AbstractAnchorView controller = new PrimaryView();
        scene = new Scene(controller);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws NativeHookException{
        GlobalScreen.unregisterNativeHook();
        Platform.exit();
    }
}