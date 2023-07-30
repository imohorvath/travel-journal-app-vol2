package com.codecool.trv.repository;

import com.codecool.trv.model.Journal;
import com.codecool.trv.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
