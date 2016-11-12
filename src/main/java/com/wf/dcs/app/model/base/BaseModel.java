package com.wf.dcs.app.model.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author ddevera
 */
@MappedSuperclass
public class BaseModel {

    @Transient
    @org.springframework.data.annotation.Transient
    private final transient Logger LOG = LoggerFactory.getLogger(getClass());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false, length = 250)
    private String createdBy;

    @CreatedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", length = 250)
    private String updatedBy;

    @LastModifiedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        BaseModel rhs = (BaseModel) obj;

        if (getId() == null && rhs.getId() == null) {
            return super.equals(rhs);

        } else {
            return new EqualsBuilder()
                    .append(getId(), rhs.getId())
                    .isEquals();
        }
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return new HashCodeBuilder(7, 17)
                    .append(getId())
                    .toHashCode();
        }
    }

    @Override
    public final String toString() {
        if (LOG != null && LOG.isTraceEnabled()) {
            return printTrace();
        } else if (LOG != null && LOG.isDebugEnabled()) {
            return printDebug();
        } else {
            return printInfo();
        }
    }

    /**
     * Override to print info on model.
     *
     * @return model info string
     */
    protected String printInfo() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("id", getId())
                .append("createdBy", getCreatedBy())
                .append("createdDate", getCreatedDate())
                .append("updatedBy", getUpdatedBy())
                .append("updatedDate", getUpdatedDate())
                .toString();
    }

    /**
     * Override to print debug on model.
     *
     * @return model info string
     */
    protected String printDebug() {
        return printInfo();
    }

    /**
     * Override to print trace on model.
     *
     * @return model info string
     */
    protected String printTrace() {
        return printDebug();
    }
}
