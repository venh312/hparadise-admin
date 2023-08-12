package com.hparadise.admin.domain.inquiry;

import com.hparadise.admin.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Inquiry extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private String email;
    private String contents;
    private String phoneNumber;
    private String answerStatus;
    private String answerTitle;
    private String answerContents;
    private LocalDateTime answerDate;
}
