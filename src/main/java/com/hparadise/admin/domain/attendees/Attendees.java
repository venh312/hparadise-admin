package com.hparadise.admin.domain.attendees;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Attendees {
    @Id
    @GeneratedValue
    private Long id;
    private Long programFk;
    private Long custId;
}
