package com.bui.projects.exeption;

public class AtlasNotFoundException extends  RuntimeException{

    public AtlasNotFoundException(Integer id) {
        super("Atlas with id: " + id + " not found");
    }
}
