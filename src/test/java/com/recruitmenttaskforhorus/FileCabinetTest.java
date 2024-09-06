package com.recruitmenttaskforhorus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class FileCabinetTest {
    private FileCabinet fileCabinet;

    @BeforeEach
    void setUp() {
        Folder kadry = new InMemoryFolder("kadry", "LARGE");
        Folder place = new InMemoryFolder("płace", "MEDIUM");
        Folder wydatki = new InMemoryFolder("wydatki", "SMALL");
        Folder klienci = new InMemoryFolder("klienci", "MEDIUM");
        Folder katalog = new InMemoryFolder("katalog", "SMALL");
        Folder marketing = new InMemoryFolder("marketing", "MEDIUM");
        Folder kontakty = new InMemoryFolder("kontakty", "MEDIUM");

        MultiFolder multiFolder1 = new InMemoryMultiFolder("multi1", "LARGE", Arrays.asList(kadry, place));
        MultiFolder multiFolder2 = new InMemoryMultiFolder("multi2", "MEDIUM", Arrays.asList(wydatki, klienci));

        fileCabinet = new FileCabinet(Arrays.asList(multiFolder1, multiFolder2, katalog, marketing, kontakty));
    }

    @Test
    @DisplayName("should find folder by name")
    void should_find_folder_by_name() {
        //given
        String folderName = "kadry";

        //when
        Optional<Folder> folder = fileCabinet.findFolderByName(folderName);

        //then
        assertThat(folder).isPresent();
        assertThat(folder.get().getName()).isEqualTo("kadry");
    }

    @Test
    @DisplayName("should not find folder by wrong name")
    void should_not_find_folder_by_wrong_name() {
        //given
        String nonExistentFolderName = "brak";

        //when
        Optional<Folder> nonExistentFolder = fileCabinet.findFolderByName(nonExistentFolderName);

        //then
        assertThat(nonExistentFolder).isNotPresent();
    }

    @Test
    @DisplayName("should find folders by large size")
    void should_find_folders_by_large_size() {
        //given
        String largeSize = "LARGE";

        //when
        List<Folder> largeFolders = fileCabinet.findFoldersBySize(largeSize);

        //then
        assertThat(largeFolders).hasSize(2);
        assertThat(largeFolders.get(0).getName()).isEqualTo("multi1");
        assertThat(largeFolders.get(1).getName()).isEqualTo("kadry");
    }

    @Test
    @DisplayName("should find folders by size medium")
    void should_find_folders_by_size_medium() {

        //given
        String mediumSize = "MEDIUM";

        //when
        List<Folder> mediumFolders = fileCabinet.findFoldersBySize(mediumSize);

        //then
        assertThat(mediumFolders).hasSize(5);
        assertThat(mediumFolders).extracting(Folder::getName)
                .containsExactlyInAnyOrder("płace", "klienci", "marketing", "kontakty", "multi2");
    }

    @Test
    @DisplayName("should count all folders")
    void should_count_count_all_folders() {
        //when
        int folderCount = fileCabinet.count();

        //then
        assertThat(folderCount).isEqualTo(9);
    }
}