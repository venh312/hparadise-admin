package com.hparadise.admin.service;

import com.hparadise.admin.domain.program.ProgramRepository;
import com.hparadise.admin.domain.program.QProgram;
import com.hparadise.admin.dto.program.*;
import com.hparadise.admin.util.MemberUtils;
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
public class ProgramService {
    private final ProgramRepository programRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QProgram program = QProgram.program;

    @Transactional(readOnly = true)
    public HashMap<String, Object> findAll(ProgramSearchRequest request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<ProgramInfoResponse> list = jpaQueryFactory
            .select(Projections.constructor(ProgramInfoResponse.class,
                program.id,
                program.title,
                program.place,
                program.status,
                program.useYn,
                program.createdDate
            ))
            .from(program)
            .where(
                ProgramExpression.containsTargetDate(program, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                ProgramExpression.containsTarget(program, request.getTarget(), request.getSearch()),
                ProgramExpression.eqStatus(program, request.getStatus()),
                ProgramExpression.eqUseYn(program, request.getUseYn())
            )
            .offset(page)
            .limit(pageSize)
            .orderBy(program.createdDate.desc())
            .fetch();

        Long totalCnt = (long) jpaQueryFactory.select(program.count()).from(program)
            .where(
                ProgramExpression.containsTargetDate(program, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                ProgramExpression.containsTarget(program, request.getTarget(), request.getSearch()),
                ProgramExpression.eqStatus(program, request.getStatus()),
                ProgramExpression.eqUseYn(program, request.getUseYn())
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
    public ProgramInfoResponse findById(Long id) {
        return new ProgramInfoResponse(programRepository.findById(id).get());
    }

    @Transactional
    public long save(ProgramSaveRequest request) {
        return programRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public long update(ProgramSaveRequest request) {
        return jpaQueryFactory.update(program)
            .set(program.startDate, request.getStartDate())
            .set(program.endDate, request.getEndDate())
            .set(program.title, request.getTitle())
            .set(program.contents, request.getContents())
            .set(program.place, request.getPlace())
            .set(program.status, request.getStatus())
            .set(program.useYn, request.getUseYn())
            .set(program.modifiedId, MemberUtils.getId())
            .set(program.modifiedDate, LocalDateTime.now())
            .where(program.id.eq(request.getId()))
            .execute();
    }

    @Transactional
    public long delete(ProgramDeleteRequest request) {
        return jpaQueryFactory.delete(program)
            .where(program.id.in(request.getIdArr()))
            .execute();
    }
}
