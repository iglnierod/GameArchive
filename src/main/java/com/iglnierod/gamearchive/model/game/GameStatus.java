/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.iglnierod.gamearchive.model.game;

/**
 *
 * @author iglnierod
 */
public enum GameStatus {
    WANT_TO_PLAY(0),
    PLAYING(1),
    PLAYED(2);

    private final int value;

    GameStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GameStatus fromValue(int value) {
        for (GameStatus status : GameStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
