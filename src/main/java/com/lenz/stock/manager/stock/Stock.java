package com.lenz.stock.manager.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lenz.stock.manager.common.BaseModel;

/**
 * @author Michael Lenz
 *
 */
@Entity
@Table(name = "STOCK")
public class Stock extends BaseModel {

	/** The generated serial version id. */
	private static final long serialVersionUID = 8837208266110105474L;

	@Column(name = "SYMBOL", nullable = false, unique = true)
	private String symbol;

	@Column(name = "WKN")
	private String wkn;

	@Column(name = "ISIN")
	private String isin;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "VALUE")
	private Double value;

	/**
	 * 
	 */
	public Stock() {
		super();
		setVersion(0);
	}

	/**
	 * @param tenant
	 * 
	 * @param symbol
	 * 
	 * @param description
	 */
	public Stock(final Long id, final String tenant, final String symbol, final String name) {
		this(tenant, symbol, name);
		this.setId(id);
	}

	/**
	 * @param tenant
	 * 
	 * @param symbol
	 * 
	 * @param description
	 */
	public Stock(final String tenant, final String symbol, final String name) {
		this();
		setTenant(tenant);
		this.symbol = symbol;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("tenant", getTenant()).append("id", getId()).append("symbol", symbol)
				.append("name", name).toString();
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

		Stock ohter = (Stock) object;

		return new EqualsBuilder().append(getTenant(), ohter.getTenant()).append(getId(), ohter.getId()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getTenant()).append(getId()).toHashCode();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) {
		this.wkn = wkn;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
