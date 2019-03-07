package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unind.base.domain.admin.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author chen
 */
@Entity
@Table(name = ApprovedItemsMap.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedItemsMap extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_items_map";

    /**
     * 审核项目ID
     */
    @ApiModelProperty(name="bsItemsId",required=true,value="审核项目ID")
    @NotNull
    @Column
    protected Long bsItemsId;

    /**
     * 审核条款ID
     */
    @ApiModelProperty(name="bsTermsId",required=true,value="审核条款ID")
    @NotNull
    @Column
    protected Long bsTermsId;

    @ApiModelProperty(name="approvedItems",hidden=true,value="项目")
    @ManyToOne
    @JoinColumn(name = "bsItemsId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedItems approvedItems;

    @ApiModelProperty(name="termsBy",hidden=true,value="条款")
    @ManyToOne
    @JoinColumn(name = "bsTermsId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedTerms termsBy;

    public Long getBsItemsId() {
        return bsItemsId;
    }

    public void setBsItemsId(Long bsItemsId) {
        this.bsItemsId = bsItemsId;
    }

    public Long getBsTermsId() {
        return bsTermsId;
    }

    public void setBsTermsId(Long bsTermsId) {
        this.bsTermsId = bsTermsId;
    }

    public ApprovedItems getApprovedItems() {
        return approvedItems;
    }

    @JsonBackReference
    public void setApprovedItems(ApprovedItems approvedItems) {
        this.approvedItems = approvedItems;
    }

    public ApprovedTerms getTermsBy() {
        return termsBy;
    }

    public void setTermsBy(ApprovedTerms termsBy) {
        this.termsBy = termsBy;
    }
}
