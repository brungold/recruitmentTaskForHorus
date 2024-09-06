package com.recruitmenttaskforhorus;

public class InMemoryFolder implements Folder {
    private String name;
    private String size;

    public InMemoryFolder(String name, String size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size;
    }
}
