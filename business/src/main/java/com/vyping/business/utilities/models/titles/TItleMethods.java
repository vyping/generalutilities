package com.vyping.business.utilities.models.titles;

import com.vyping.business.utilities.models.categories.Category;
import com.vyping.business.utilities.models.categories.CategoryMethods;

public class TItleMethods extends CategoryMethods {

    private final Category category;


    // ----- SetUp ----- //

    public TItleMethods(Category category) {

        super(category);

        this.category = category;
    }

    public String getName()
    {
        return category.Id;
    }
}
