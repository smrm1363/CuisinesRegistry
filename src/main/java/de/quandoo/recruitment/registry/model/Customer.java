package de.quandoo.recruitment.registry.model;

import de.quandoo.recruitment.registry.ApplicationException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Customer {
    private final String uuid;
    private Set<Cuisine> cuisineSet = new HashSet<>();

    public Customer(final String uuid) throws ApplicationException {
        if(uuid == null)
            throw new ApplicationException("uuid could not be null");
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }


    public Set<Cuisine> getCuisineSet() {
        return cuisineSet;
    }

    public void setCuisineSet(Set<Cuisine> cuisineSet) {
        this.cuisineSet = cuisineSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return uuid.equals(customer.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
