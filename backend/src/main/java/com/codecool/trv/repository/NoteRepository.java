package com.codecool.trv.repository;

import com.codecool.trv.model.Note;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByJournal_Id(Long journalId);

    void deleteAllByJournal_Id(Long journalId);

    @Transactional
    void deleteNotesByCreatedBy_IdOrUpdatedBy_Id(Long userId1, Long userId2);

}
