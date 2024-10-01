package com.autoclicker.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;
import javafx.util.Duration;

public class AutoClickController {

    private boolean isRight = false;
    private boolean isLeft = false;
    private boolean isPress = false;
    private boolean isActive = false;
    private Timeline clickEngine;
    private Timeline holdClickEngine;
    private final Robot robot = new Robot();

    public AutoClickController() {
        this.setupClickSpammer(1.0);
        this.setupClickHolder();
    }

    public void setRight(boolean bool) {
        this.isRight = bool;
    }

    public void setLeft(boolean bool) {
        this.isLeft = bool;
    }

    public void setPress(boolean bool) {
        this.isPress = bool;
    }

    public void setActive(boolean bool) {
        this.isActive = bool;
    }

    public boolean getIsPress() {
        return this.isPress;
    }

    public void holdClick() {
        if (this.isPress) {
            robot.mousePress(MouseButton.PRIMARY);
        } else {
            robot.mouseRelease(MouseButton.PRIMARY);
        }
    }

    public void setupClickSpammer(Double cps) {
        if (this.clickEngine != null) {
            this.clickEngine.stop();
        }
        Duration duration = new Duration(1000 / cps);
        KeyFrame keyframe = new KeyFrame(duration, (ActionEvent e) -> {
            clickHandler();
        });
        this.clickEngine = new Timeline(keyframe);
        this.clickEngine.setCycleCount(Animation.INDEFINITE);
        this.clickEngine.play();
    }

    public void setupClickHolder() {
        Duration duration = new Duration(250);
        KeyFrame keyFrame = new KeyFrame(duration, (ActionEvent e) -> {
            if (this.isPress) {
                this.robot.mousePress(MouseButton.PRIMARY);
            } else {
                this.robot.mouseRelease(MouseButton.PRIMARY);
            }
        });
        this.holdClickEngine = new Timeline(keyFrame);
        this.holdClickEngine.setCycleCount(Animation.INDEFINITE);
        this.holdClickEngine.play();
    }

    private void clickHandler() {
        if (this.isActive) {
            if (this.isLeft) {
                this.robot.mousePress(MouseButton.PRIMARY);
                this.robot.mouseRelease(MouseButton.PRIMARY);
            } else if (this.isRight) {
                this.robot.mousePress(MouseButton.SECONDARY);
                this.robot.mouseRelease(MouseButton.SECONDARY);
            }
        }
    }
}
