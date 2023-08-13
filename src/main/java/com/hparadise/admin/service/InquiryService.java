package com.hparadise.admin.service;

import com.hparadise.admin.domain.inquiry.InquiryRepository;
import com.hparadise.admin.domain.inquiry.QInquiry;
import com.hparadise.admin.dto.inquiry.InquiryAnswerRequest;
import com.hparadise.admin.dto.inquiry.InquirySearchRequest;
import com.hparadise.admin.dto.inquiry.InquiryExpression;
import com.hparadise.admin.dto.inquiry.InquiryInfoResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QInquiry inquiry = QInquiry.inquiry;

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(InquirySearchRequest request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<InquiryInfoResponse> list = jpaQueryFactory
            .select(Projections.constructor(InquiryInfoResponse.class,
                inquiry.id,
                inquiry.name,
                inquiry.email,
                inquiry.answerStatus,
                inquiry.createdDate
            ))
            .from(inquiry)
            .where(
                InquiryExpression.containsTargetDate(inquiry, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                InquiryExpression.containsTarget(inquiry, request.getTarget(), request.getSearch()),
                InquiryExpression.eqAnswerStatus(inquiry, request.getAnswerStatus())
            )
            .offset(page)
            .limit(pageSize)
            .orderBy(inquiry.createdDate.desc())
            .fetch();

        Long totalCnt = (long) jpaQueryFactory.select(inquiry.count()).from(inquiry)
            .where(
                InquiryExpression.containsTargetDate(inquiry, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                InquiryExpression.containsTarget(inquiry, request.getTarget(), request.getSearch()),
                InquiryExpression.eqAnswerStatus(inquiry, request.getAnswerStatus())
            )
            .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / pageSize);
        totalPage = totalPage == 0 ? 1 : totalPage;
        int currentPage = (page / pageSize) + 1;
        int endPage = pageSize > totalPage ? totalPage : pageSize;

        resultMap.put("list", list);
        resultMap.put("request", request);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("currentPage", currentPage);
        resultMap.put("startPage", currentPage > 4 ? currentPage - 4 : 1);
        resultMap.put("endPage", currentPage > 4 ? Math.min(currentPage + 4, totalPage) : endPage);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }

    @Transactional(readOnly = true)
    public InquiryInfoResponse findById(Long id) {
        return new InquiryInfoResponse(inquiryRepository.findById(id).get());
    }

    @Transactional
    public long inquiryAnswerSave(InquiryAnswerRequest request) {
        return jpaQueryFactory.update(inquiry)
            .set(inquiry.answerStatus, request.getAnswerStatus())
            .set(inquiry.answerTitle, request.getAnswerTitle())
            .set(inquiry.answerContents, request.getAnswerContents())
            .set(inquiry.answerDate, LocalDateTime.now())
            .where(inquiry.id.eq(request.getId()))
            .execute();
    }

}
