-printmapping obfuscation/mapping.txt
-printconfiguration proguard-merged-config.txt

-keep class com.vyping.masterlibrary.models.categories.Category
-keepnames class com.vyping.masterlibrary.models.categories.Category
-keepclassmembers class com.vyping.masterlibrary.models.categories.Category {*;}

-keep class com.vyping.masterlibrary.models.ingredients.Ingredient
-keepnames class com.vyping.masterlibrary.models.ingredients.Ingredient
-keepclassmembers class com.vyping.masterlibrary.models.ingredients.Ingredient {*;}

-keep class com.vyping.masterlibrary.models.orders.Order
-keepnames class com.vyping.masterlibrary.models.orders.Order
-keepclassmembers class com.vyping.masterlibrary.models.orders.Order {*;}

-keep class com.vyping.masterlibrary.models.orderproducts.OrderProduct
-keepnames class com.vyping.masterlibrary.models.orderproducts.OrderProduct
-keepclassmembers class com.vyping.masterlibrary.models.orderproducts.OrderProduct {*;}

-keep class com.vyping.masterlibrary.models.products.Product
-keepnames class com.vyping.masterlibrary.models.products.Product
-keepclassmembers class com.vyping.masterlibrary.models.products.Product {*;}

-keep class com.vyping.masterlibrary.models.recipes.Recipe
-keepnames class com.vyping.masterlibrary.models.recipes.Recipe
-keepclassmembers class com.vyping.masterlibrary.models.recipes.Recipe {*;}

-keep class com.vyping.masterlibrary.models.restaurant.Restaurant
-keepnames class com.vyping.masterlibrary.models.restaurant.Restaurant
-keepclassmembers class com.vyping.masterlibrary.models.restaurant.Restaurant {*;}

-keep class com.vyping.masterlibrary.models.subcategories.SubCategory
-keepnames class com.vyping.masterlibrary.models.subcategories.SubCategory
-keepclassmembers class com.vyping.masterlibrary.models.subcategories.SubCategory {*;}

-keep class com.vyping.masterlibrary.models.supplies.Supply
-keepnames class com.vyping.masterlibrary.models.supplies.Supply
-keepclassmembers class com.vyping.masterlibrary.models.supplies.Supply {*;}

-keep class com.vyping.masterlibrary.models.transactions.Transaction
-keepnames class com.vyping.masterlibrary.models.transactions.Transaction
-keepclassmembers class com.vyping.masterlibrary.models.transactions.Transaction {*;}

-keep class com.vyping.masterlibrary.models.images.Image
-keepnames class com.vyping.masterlibrary.models.images.Image
-keepclassmembers class com.vyping.masterlibrary.models.images.Image {*;}

-keep class com.vyping.masterlibrary.models.accounts.Account
-keepnames class com.vyping.masterlibrary.models.accounts.Account
-keepclassmembers class com.vyping.masterlibrary.models.accounts.Account{*;}