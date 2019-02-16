package de.quandoo.recruitment.registry;

import de.quandoo.recruitment.registry.api.CuisinesRegistry;
import de.quandoo.recruitment.registry.model.Cuisine;
import de.quandoo.recruitment.registry.model.Customer;
import de.quandoo.recruitment.registry.util.PropertyLoader;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCuisinesRegistry implements CuisinesRegistry {

    /**
     * I used Map instead of the limited lists
     */
    private Map<String,Cuisine> cuisineMap = new HashMap<>();
    private Map<String,Customer> customerMap = new HashMap<>();

    /**
     * This Constructor reads cuisines names from property file and puts them in to cuisineMap
     */
    public InMemoryCuisinesRegistry() {
        try {
            Arrays.stream(PropertyLoader.getProperties().getProperty("cuisine.types").split(","))
                    .forEach(s ->
                    {
                        try {
                            cuisineMap.put(s,new Cuisine(s));
                        } catch (ApplicationException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method registers Customer and Cuisine relation. The relation is bidirectional
     * @param userId
     * @param cuisine
     */
    @Override
    public void register(final Customer userId, final Cuisine cuisine) {
        String cuisineName = cuisine.getName();
        Optional<Cuisine> cuisineOptional = Optional.ofNullable(cuisineMap.get(cuisineName));
        Optional<Customer> customerOptional = Optional.ofNullable(customerMap.get(userId.getUuid()));
        Customer foundCustomer = null;
        try {
            foundCustomer = customerOptional.orElse(new Customer(userId.getUuid()));
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        if (cuisineOptional.isPresent()) {
                cuisineOptional.get().getCustomerSet().add(foundCustomer);
                foundCustomer.getCuisineSet().add(cuisineOptional.get());
                customerMap.put(foundCustomer.getUuid(), foundCustomer);
            } else {
                System.err.println("Unknown cuisine, please reach johny@bookthattable.de to update the code");
            }
        }

    /**
     * This method retrieves a list of Cuisine's Customer
     * @param cuisine
     * @return
     */
    @Override
    public List<Customer> cuisineCustomers(final Cuisine cuisine) {
        if(cuisine==null)
            return null;
        return new ArrayList<Customer> (cuisineMap.get(cuisine.getName()).getCustomerSet());
    }

    /**
     * This method retrieves a list of Customer's Cuisine
     * @param customer
     * @return
     */
    @Override
    public List<Cuisine> customerCuisines(final Customer customer) {
        if(customer==null)
            return null;
        return new ArrayList<Cuisine> (customerMap.get(customer.getUuid()).getCuisineSet());
    }

    /**
     * This method retrieves a list of top N Cuisines
     * @param n
     * @return
     */
    @Override
    public List<Cuisine> topCuisines(final int n) {
        return cuisineMap.entrySet().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getValue().getCustomerSet().size(),o1.getValue().getCustomerSet().size()))
                .map(Map.Entry::getValue).limit(n)
                .collect(Collectors.toList());
    }
}
