package de.quandoo.recruitment.registry.model;

import de.quandoo.recruitment.registry.ApplicationException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cuisine {

    private final String name;
    /**
     * I used Set, duo to it prevents adding redundant data
     */
    private Set<Customer> customerSet = new HashSet<>();
    public Cuisine(final String name) throws ApplicationException {
        if(name==null)
            throw new ApplicationException("Name could not be null");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuisine cuisine = (Cuisine) o;
        return name.equals(cuisine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
