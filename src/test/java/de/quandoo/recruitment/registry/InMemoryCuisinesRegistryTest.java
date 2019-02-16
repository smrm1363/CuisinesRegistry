package de.quandoo.recruitment.registry;

import de.quandoo.recruitment.registry.model.Cuisine;
import de.quandoo.recruitment.registry.model.Customer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InMemoryCuisinesRegistryTest {

    private InMemoryCuisinesRegistry cuisinesRegistry = new InMemoryCuisinesRegistry();

    @Test
    public void shouldWork1() throws ApplicationException {
        cuisinesRegistry.register(new Customer("1"), new Cuisine("french"));
        cuisinesRegistry.register(new Customer("2"), new Cuisine("german"));
        cuisinesRegistry.register(new Customer("3"), new Cuisine("italian"));
        cuisinesRegistry.register(new Customer("2"), new Cuisine("french"));
        cuisinesRegistry.register(new Customer("1"), new Cuisine("german"));

        cuisinesRegistry.cuisineCustomers(new Cuisine("french"));
    }

    @Test
    public void shouldWork2() {
        cuisinesRegistry.cuisineCustomers(null);
    }

    @Test
    public void shouldWork3() {
        cuisinesRegistry.customerCuisines(null);
    }

    @Test
    public void topCuisines() throws ApplicationException {
        shouldWork1();
        List<Cuisine> cuisineList =cuisinesRegistry.topCuisines(2);
        assertTrue(cuisineList.size()==2);
        assertTrue(cuisineList.contains(new Cuisine("french")));
        assertTrue(cuisineList.contains(new Cuisine("german")));
    }
    @Test
    public void cuisineCustomers() throws ApplicationException {
        shouldWork1();
        List<Customer> customerList = cuisinesRegistry.cuisineCustomers(new Cuisine("french"));
        assertTrue(customerList.contains(new Customer("2")));
        assertTrue(customerList.contains(new Customer("2")));
        assertTrue(!customerList.contains(new Customer("3")));
    }

    @Test
    public void customerCuisines() throws ApplicationException {
        shouldWork1();
        List<Cuisine> cuisineList = cuisinesRegistry.customerCuisines(new Customer("1"));
        assertTrue(cuisineList.contains(new Cuisine("french")));
        assertTrue(cuisineList.contains(new Cuisine("german")));
        assertTrue(!cuisineList.contains(new Cuisine("italian")));
    }


}