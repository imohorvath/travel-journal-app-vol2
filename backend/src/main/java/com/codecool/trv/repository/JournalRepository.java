package com.codecool.trv.repository;

import com.codecool.trv.model.Journal;
import com.codecool.trv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    List<Journal> findAllByOwner_IdIs(Long owner_id);

}
