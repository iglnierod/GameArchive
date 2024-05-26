/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.utils;

/**
 *
 * @author iglnierod
 */
public class Util {
    /**
     * Escapa los caracteres especiales de HTML en una cadena.
     *
     * @param text La cadena a escapar.
     * @return La cadena con los caracteres especiales de HTML escapados.
     */
    public static String escapeHtml(String text) {
        if (text == null) {
            return null;
        }
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
