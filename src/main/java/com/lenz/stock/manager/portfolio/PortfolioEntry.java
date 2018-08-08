package com.lenz.stock.manager.portfolio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lenz.stock.manager.common.BaseModel;
import com.lenz.stock.manager.common.PortfolioType;

/**
 * @author Michael Lenz
 *
 */
@Entity
@Table(name = "PORTFOLIO")
public class PortfolioEntry extends BaseModel {

    /** The serial version id. */
    private static final long serialVersionUID = -931556645482159883L;

    @Column(name = "STOCK_ID", nullable = false)
    private Long stockId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private PortfolioType type;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    /**
     * 
     */
    public PortfolioEntry() {
	super();
    }

    /**
     * 
     * @param id
     */
    public PortfolioEntry(final String tenant, final Long id) {
	this();
	this.setTenant(tenant);
	this.setId(id);
    }

    public String toString() {
	return new ToStringBuilder(this)
	    .append("tenant", getTenant())
	    .append("id", getId())
	    .toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {

	if (object == null) {
	    return false;
	}

	if (object == this) {
	    return true;
	}

	if (!object.getClass().equals(getClass())) {
	    return false;
	}

	PortfolioEntry ohter = (PortfolioEntry) object;

	return new EqualsBuilder()
	    .append(getTenant(), ohter.getTenant())
	    .append(getId(), ohter.getId())
	    .isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
	return new HashCodeBuilder(17, 37)
	    .append(getId())
	    .toHashCode();
    }

    public Long getStockId() {
	return stockId;
    }

    public void setStockId(Long stockId) {
	this.stockId = stockId;
    }

    public PortfolioType getType() {
	return type;
    }

    public void setType(PortfolioType type) {
	this.type = type;
    }

    public Integer getQuantity() {
	return quantity;
    }

    public void setQuantity(Integer quantity) {
	this.quantity = quantity;
    }

}
