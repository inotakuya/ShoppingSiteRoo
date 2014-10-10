package jp.com.inotaku.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaEntity
@RooSerializable
public class Sale {

    /**
     */
    @NotNull
    @Column(unique = true)
    private long saleId;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateDate;

    /**
     */
    @NotNull
    @Min(0L)
    private int totalAmount;

    /**
     */
    @NotNull
    @ManyToOne
    private Consumer consumer;
}
