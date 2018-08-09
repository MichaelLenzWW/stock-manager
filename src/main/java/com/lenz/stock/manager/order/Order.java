package com.lenz.stock.manager.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lenz.stock.manager.common.BaseModel;
import com.lenz.stock.manager.common.OptionType;

/**
 * @author Michael Lenz
 *
 */
@Entity
@Table(name = "ORDERS")
public class Order extends BaseModel {

    /** The serial version id. */
    private static final long serialVersionUID = -931556645482159883L;

    @Column(name = "STOCK_ID", nullable = false)
    private Long stockId;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private OrderType type;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "PURCHASE_PRICE", nullable = false)
    private Double purchasePrice;

    @Column(name = "SELL_PRICE")
    private Double sellPrice;

    @Column(name = "PURCHASE_PROVISION")
    private Double purchaseProvision;

    @Column(name = "SELL_PROVISION")
    private Double sellProvision;

//    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "PURCHASE_DATE", nullable = false)
    private Date purchaseDate;

//    @JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "SELL_DATE")
    private Date sellDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "OPTION_TYPE")
    private OptionType optionType;

    @Column(name = "STRIKE_PRICE")
    private Double strikePrice;

    //@JsonSerialize(using=JsonDateSerializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "STRIKE_DATE")
    private Date strikeDate;

    /**
     * 
     */
    public Order() {
	super();
    }

    /**
     * 
     * @param id
     */
    public Order(final String tenant, final Long id, final Long stockId) {
	this();
	setTenant(tenant);
	setId(id);
	this.stockId = stockId;
    }

    public String toString() {
	return new ToStringBuilder(this)
	    .append("tenant", getTenant())
	    .append("id", getId())
	    .append("stockId", stockId)
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

	Order ohter = (Order) object;

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

    public OrderType getType() {
	return type;
    }

    public void setType(OrderType type) {
	this.type = type;
    }

    public Double getQuantity() {
	return quantity;
    }

    public void setQuantity(Double quantity) {
	this.quantity = quantity;
    }

    public Double getPurchasePrice() {
	return purchasePrice;
    }

    public void setPurchasePrice(Double price) {
	this.purchasePrice = price;
    }

    public Double getSellPrice() {
	return sellPrice;
    }

    public void setSellPrice(Double price) {
	this.sellPrice = price;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    public Double getPurchaseProvision() {
	return purchaseProvision;
    }

    public void setPurchaseProvision(Double provision) {
	this.purchaseProvision = provision;
    }

    public Double getSellProvision() {
	return sellProvision;
    }

    public void setSellProvision(Double provision) {
	this.sellProvision = provision;
    }

    public Date getPurchaseDate() {
	return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
	this.purchaseDate = purchaseDate;
    }

    public Date getSellDate() {
	return sellDate;
    }

    public void setSellDate(Date sellDate) {
	this.sellDate = sellDate;
    }

    public OrderStatus getStatus() {
	return status;
    }

    public void setStatus(OrderStatus status) {
	this.status = status;
    }

    public OptionType getOptionType() {
	return optionType;
    }

    public void setOptionType(OptionType optionType) {
	this.optionType = optionType;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Date getStrikeDate() {
        return strikeDate;
    }

    public void setStrikeDate(Date strikeDate) {
        this.strikeDate = strikeDate;
    }
}
