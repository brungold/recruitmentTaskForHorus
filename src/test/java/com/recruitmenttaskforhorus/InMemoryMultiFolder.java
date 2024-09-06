package com.recruitmenttaskforhorus;

import java.util.List;

public class InMemoryMultiFolder extends InMemoryFolder implements MultiFolder {
    private List<Folder> folders;

    public InMemoryMultiFolder(String name, String size, List<Folder> folders) {
        super(name, size);
        this.folders = folders;
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}
