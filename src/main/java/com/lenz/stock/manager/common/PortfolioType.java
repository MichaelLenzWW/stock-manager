package com.lenz.stock.manager.common;

/**
 * 
 * @author Michael Lenz
 *
 */
public enum PortfolioType {

    SHARE("SH", "Share"), OPTION("OPT", "Option");

    private String name;

    private String description;

    private PortfolioType(final String name, final String description) {
	this.name = name;
	this.description = description;
    }

    public String getName() {
	return name;
    }

    public String getDescription() {
	return description;
    }

}