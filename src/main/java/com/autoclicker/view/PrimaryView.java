package com.autoclicker.view;

import java.io.IOException;
import java.util.ArrayList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.autoclicker.controller.AutoClickController;
import com.autoclicker.entity.KeyEntity;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;

public class PrimaryView extends AbstractAnchorView implements NativeKeyListener {

    @FXML
    public RadioButton leftRadio;
    @FXML
    public RadioButton rightRadio;
    @FXML
    public CheckBox activeCB;
    @FXML
    public Slider cpsSlider;
    @FXML
    public Label cpsLabel;
    @FXML
    public ComboBox autoClickSelector;
    @FXML
    public ComboBox holdClickSelector;

    private final AutoClickController autoclick = new AutoClickController();
    private final ArrayList<KeyEntity> keys = new ArrayList<>();

    public PrimaryView() throws IOException {
        super("primary.fxml");
        GlobalScreen.addNativeKeyListener(this);
        this.fillKeys();

        this.autoClickSelector.setItems(FXCollections.observableArrayList(keys));
        this.autoClickSelector.setValue(this.autoClickSelector.getItems().get(0));
        this.holdClickSelector.setItems(FXCollections.observableArrayList(keys));
        this.holdClickSelector.setValue(this.holdClickSelector.getItems().get(15));

        this.autoclick.setRight(rightRadio.isSelected());
        this.autoclick.setLeft(leftRadio.isSelected());

        cpsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.autoclick.setupClickSpammer(newValue.doubleValue());
            cpsLabel.setText(newValue.toString());
        });
    }

    private void fillKeys() {
        keys.add(new KeyEntity(16, "A"));
        keys.add(new KeyEntity(17, "Z"));
        keys.add(new KeyEntity(18, "E"));
        keys.add(new KeyEntity(19, "R"));
        keys.add(new KeyEntity(20, "T"));
        keys.add(new KeyEntity(21, "Y"));
        keys.add(new KeyEntity(22, "U"));
        keys.add(new KeyEntity(23, "I"));
        keys.add(new KeyEntity(24, "O"));
        keys.add(new KeyEntity(25, "P"));
        keys.add(new KeyEntity(30, "Q"));
        keys.add(new KeyEntity(31, "S"));
        keys.add(new KeyEntity(32, "D"));
        keys.add(new KeyEntity(33, "F"));
        keys.add(new KeyEntity(34, "G"));
        keys.add(new KeyEntity(35, "H"));
        keys.add(new KeyEntity(36, "J"));
        keys.add(new KeyEntity(37, "K"));
        keys.add(new KeyEntity(38, "L"));
        keys.add(new KeyEntity(39, "M"));
        keys.add(new KeyEntity(34, "Page Down"));
        keys.add(new KeyEntity(44, "W"));
        keys.add(new KeyEntity(45, "X"));
        keys.add(new KeyEntity(46, "C"));
        keys.add(new KeyEntity(47, "V"));
        keys.add(new KeyEntity(48, "B"));
        keys.add(new KeyEntity(49, "N"));
        keys.add(new KeyEntity(29, "CTRL"));
        keys.add(new KeyEntity(56, "ALT"));
        keys.add(new KeyEntity(82, "NumPad-0"));
        keys.add(new KeyEntity(79, "NumPad-1"));
        keys.add(new KeyEntity(80, "NumPad-2"));
        keys.add(new KeyEntity(81, "NumPad-3"));
        keys.add(new KeyEntity(75, "NumPad-4"));
        keys.add(new KeyEntity(76, "NumPad-5"));
        keys.add(new KeyEntity(77, "NumPad-6"));
        keys.add(new KeyEntity(71, "NumPad-7"));
        keys.add(new KeyEntity(72, "NumPad-8"));
        keys.add(new KeyEntity(73, "NumPad-9"));
        keys.add(new KeyEntity(59, "F1"));
        keys.add(new KeyEntity(60, "F2"));
        keys.add(new KeyEntity(61, "F3"));
        keys.add(new KeyEntity(62, "F4"));
        keys.add(new KeyEntity(63, "F5"));
        keys.add(new KeyEntity(64, "F6"));
        keys.add(new KeyEntity(65, "F7"));
        keys.add(new KeyEntity(66, "F8"));
        keys.add(new KeyEntity(67, "F9"));
        keys.add(new KeyEntity(68, "F10"));
        keys.add(new KeyEntity(87, "F11"));
        keys.add(new KeyEntity(88, "F12"));
    }

    @FXML
    public void handleLeft() {
        this.handleEmpty();
        this.autoclick.setLeft(leftRadio.isSelected());
    }

    @FXML
    public void handleRight() {
        this.handleEmpty();
        this.autoclick.setRight(rightRadio.isSelected());
    }

    @FXML
    public void handleEmpty() {
        this.autoclick.setRight(false);
        this.autoclick.setLeft(false);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        if (nke.getKeyCode() == ((KeyEntity) this.autoClickSelector.getValue()).getId()) {
            this.autoclick.setActive(true);
        }

        if (nke.getKeyCode() == ((KeyEntity) this.holdClickSelector.getValue()).getId()) {
            this.autoclick.setPress(!this.autoclick.getIsPress());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        if (nke.getKeyCode() == ((KeyEntity) this.autoClickSelector.getValue()).getId()) {
            this.autoclick.setActive(false);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
    
    }
}
