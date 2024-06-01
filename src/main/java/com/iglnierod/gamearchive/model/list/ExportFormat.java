/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.iglnierod.gamearchive.model.list;

/**
 *
 * @author iglnierod
 */
public enum ExportFormat {
    JSON("json", true),
    XML("xml", true),
    HTML("html", false);
    /*,
    CSV("csv", true),
    TXT("txt", true),
    MD("md", true),
    YAML("yaml", true);*/

    private final String extension;
    private final boolean canImport;

    ExportFormat(String extension, boolean canImport) {
        this.extension = extension;
        this.canImport = canImport;
    }

    public String getExtension() {
        return extension;
    }

    public boolean canImport() {
        return canImport;
    }
}
