package com.codecool.trv.repository;

import com.codecool.trv.model.NoteImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteImageRepository extends JpaRepository<NoteImage, Long> {

    @Transactional
    void deleteAllByNote_Id(Long noteId);
}
