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

//    @Test(expected = RuntimeException.class)
//    public void thisDoesntWorkYet() {
//        cuisinesRegistry.topCuisines(1);
//    }

    @Test
    public void topCuisines() throws ApplicationException {
        shouldWork1();
        cuisinesRegistry.register(new Customer("2"), new Cuisine("french"));
        cuisinesRegistry.register(new Customer("1"), new Cuisine("german"));
        List<Cuisine> cuisineList =cuisinesRegistry.topCuisines(2);
        assertTrue(cuisineList.size()==2);
        assertTrue(cuisineList.contains(new Cuisine("french")));
        assertTrue(cuisineList.contains(new Cuisine("german")));
    }


}