package com.codecool.trv.repository;

import com.codecool.trv.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findAllByOwner_IdIs(Long owner_id);

    List<Journal> findAllByContributors_Id(Long userId);
}
