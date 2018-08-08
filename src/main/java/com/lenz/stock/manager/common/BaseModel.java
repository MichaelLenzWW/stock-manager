package com.lenz.stock.manager.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseModel implements Serializable {

    /** The default serial ID. */
    private static final long serialVersionUID = 1L;

    /** The database columns. */
    public final static String COL_ID = "ID";

    public final static String COL_VERSION = "VERSION";

    /** The tenant of the data. */
    @Column(name = "TENANT", nullable = false, updatable = false)
    private String tenant;

    /** The database id of the Item. */
    @Column(name = COL_ID, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The database version of the Item. */
    @Column(name = COL_VERSION, nullable = false)
    private Integer version;

    /** The user who created the object. */
    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    private String createUser;

    /** The date the object was created. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE_TIME", updatable = false, nullable = false)
    private Date createDate;

    /** The user who last updated the object. */
    @Column(name = "UPDATED_BY", nullable = true)
    private String updateUser;

    /** The date the object was last updated. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE_TIME", nullable = true)
    private Date updateDate;

    public BaseModel() {
	version = 0;
	createDate = new Date();
	createUser = "michael";
    }

    /**
     * Getter for the id.
     * 
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Setter for the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
	this.id = id;
    }

    /**
     * Getter for the version.
     * 
     * @return the version
     */
    public final Integer getVersion() {
	return version;
    }

    /**
     * Setter for the version.
     * 
     * @param version
     *            the version to set
     */
    public final void setVersion(final Integer version) {
	this.version = version;
    }

    /**
     * Getter for the createUser.
     * 
     * @return the createUser
     */
    public final String getCreateUser() {
	return createUser;
    }

    /**
     * Setter for the createUser.
     * 
     * @param createUser
     *            the createUser to set
     */
    public final void setCreateUser(final String createUser) {
	this.createUser = createUser;
    }

    /**
     * Getter for the createDate.
     * 
     * @return the createDate
     */
    public final Date getCreateDate() {
	return createDate;
    }

    /**
     * Setter for the createDate.
     * 
     * @param createDate
     *            the createDate to set
     */
    public final void setCreateDate(final Date createDate) {
	this.createDate = createDate;
    }

    /**
     * Getter for the updateUser.
     * 
     * @return the updateUser
     */
    public final String getUpdateUser() {
	return updateUser;
    }

    /**
     * Setter for the udpateUser.
     * 
     * @param updateUser
     *            the updateUser to set
     */
    public final void setUpdateUser(final String updateUser) {
	this.updateUser = updateUser;
    }

    /**
     * Getter for the updateDate.
     * 
     * @return the updateDate
     */
    public final Date getUpdateDate() {
	return updateDate;
    }

    /**
     * Setter for the updateDate.
     * 
     * @param updateDate
     *            the updateDate to set
     */
    public final void setUpdateDate(final Date updateDate) {
	this.updateDate = updateDate;
    }

    /**
     * Getter for the tenant.
     * 
     * @return the tenant
     */

    public String getTenant() {
	return tenant;
    }

    /**
     * Setter for the tenant.
     * 
     * @param tenant
     *            the tenant to set
     */
    public void setTenant(String tenant) {
	this.tenant = tenant;
    }
}
