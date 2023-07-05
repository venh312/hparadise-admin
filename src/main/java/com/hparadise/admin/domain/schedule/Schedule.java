package com.hparadise.admin.domain.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Entity
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String contents;
    private String place;
    private String status;
    private String useYn;
    private Long createdId;
    private Long modifiedId;
}
