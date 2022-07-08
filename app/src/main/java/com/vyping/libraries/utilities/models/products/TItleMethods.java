package com.vyping.libraries.utilities.models.products;

public class TItleMethods extends ProductMethods {

    private final Product product;


    // ----- SetUp ----- //

    public TItleMethods(Product product) {

        super(product);

        this.product = product;
    }

    public String getName()
    {
        return product.Name;
    }
}
