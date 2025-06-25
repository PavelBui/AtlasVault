package com.bui.projects.exeption;

public class ImageNotFoundException extends  RuntimeException{

    public ImageNotFoundException(Integer atlasId, Integer imageId) {
        super("Image with id: " + imageId + " not found for atlas with id: " + atlasId);
    }
}
