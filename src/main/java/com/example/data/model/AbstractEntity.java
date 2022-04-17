package com.example.data.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * User super class
 */
@MappedSuperclass
public abstract class AbstractEntity {

    /**
     * Generated Entity's id
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * This method returns Entity's id
     *
     * @return Entity's id
     */
    public int getId() {
        return id;
    }

    /**
     * This method set Entity's id
     *
     * @param id Entity's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method return hashcode of this class
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        if (id == 0) {
            return id;
        }
        return super.hashCode();
    }

    /**
     * This method return is objects equals or not
     *
     * @param obj object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity)) {
            return false; // null or other class
        }
        AbstractEntity other = (AbstractEntity) obj;

        if (id == 0) {
            return id == (other.id);
        }
        return super.equals(other);
    }
}
