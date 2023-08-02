package com.codecool.trv.mapper;

import com.codecool.trv.dto.journal.JournalResponse;
import com.codecool.trv.dto.journal.NewJournalResponse;
import com.codecool.trv.dto.user.UserResponse;
import com.codecool.trv.model.Journal;

import java.util.Set;

public class JournalMapper {

    public static JournalResponse mapToJournalResponse(Journal journal, Set<UserResponse> contributors) {
        return JournalResponse.builder()
                .id(journal.getId())
                .title(journal.getTitle())
                .createdAt(journal.getCreatedAt())
                .contributors(contributors)
                .build();
    }

    public static NewJournalResponse mapToNewJournalResponse(Journal journal, Set<UserResponse> contributors) {
        return NewJournalResponse.builder()
                .id(journal.getId())
                .title(journal.getTitle())
                .createdAt(journal.getCreatedAt())
                .contributors(contributors)
                .build();
    }

}
