package com.flab.blackfriday.logging.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderLog is a Querydsl query type for OrderLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderLog extends EntityPathBase<OrderLog> {

    private static final long serialVersionUID = 919053735L;

    public static final QOrderLog orderLog = new QOrderLog("orderLog");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath orderData = createString("orderData");

    public final EnumPath<com.flab.blackfriday.modules.order.dto.OrderStatusType> orderStatus = createEnum("orderStatus", com.flab.blackfriday.modules.order.dto.OrderStatusType.class);

    public final NumberPath<Long> parmentIdx = createNumber("parmentIdx", Long.class);

    public QOrderLog(String variable) {
        super(OrderLog.class, forVariable(variable));
    }

    public QOrderLog(Path<? extends OrderLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderLog(PathMetadata metadata) {
        super(OrderLog.class, metadata);
    }

}

