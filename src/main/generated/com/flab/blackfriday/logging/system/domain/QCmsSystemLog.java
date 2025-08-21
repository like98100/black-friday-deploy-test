package com.flab.blackfriday.logging.system.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCmsSystemLog is a Querydsl query type for CmsSystemLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCmsSystemLog extends EntityPathBase<CmsSystemLog> {

    private static final long serialVersionUID = -1771879088L;

    public static final QCmsSystemLog cmsSystemLog = new QCmsSystemLog("cmsSystemLog");

    public final StringPath className = createString("className");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final StringPath detail = createString("detail");

    public final StringPath errorDetail = createString("errorDetail");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath ip = createString("ip");

    public final StringPath methodName = createString("methodName");

    public final StringPath processCode = createString("processCode");

    public final NumberPath<Long> processTime = createNumber("processTime", Long.class);

    public QCmsSystemLog(String variable) {
        super(CmsSystemLog.class, forVariable(variable));
    }

    public QCmsSystemLog(Path<? extends CmsSystemLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCmsSystemLog(PathMetadata metadata) {
        super(CmsSystemLog.class, metadata);
    }

}

