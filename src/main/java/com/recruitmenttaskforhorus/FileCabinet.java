package com.recruitmenttaskforhorus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileCabinet implements Cabinet {

    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return getAllFolders().stream()
                .filter(folder -> folder.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return getAllFolders().stream()
                .filter(folder -> folder.getSize().equals(size))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return getAllFolders().size();
    }

    private List<Folder> getAllFolders() {
        return folders.stream()
                .flatMap(folder -> checkIfItIsMultiFolderAndReturnFolder(folder))
                .collect(Collectors.toList());
    }

    private Stream<Folder> checkIfItIsMultiFolderAndReturnFolder(Folder folder) {
        if (folder instanceof MultiFolder) {
            MultiFolder multiFolder = (MultiFolder) folder;
            return Stream.concat(Stream.of(folder), multiFolder.getFolders()
                    .stream()
                    .flatMap(f -> checkIfItIsMultiFolderAndReturnFolder(f)));
        } else {
            return Stream.of(folder);
        }
    }
}

