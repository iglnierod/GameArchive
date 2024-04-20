/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.api.igdb;

/**
 *
 * @author iglnierod
 */
public class Reference {

    public static String getImage(ImageType imageType, String imageId) {
        String imageTypeString = imageType.name().toLowerCase();
        if (imageTypeString.charAt(0) == '_') {
            imageTypeString = imageTypeString.replace("_", "");
        }

        return String.format("https://images.igdb.com/igdb/image/upload/t_%s/%s.jpg", imageTypeString, imageId);
    }
}
