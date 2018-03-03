Grocery store.

1) StoreManager
   * He is responsible for handling the inventory, getting new items to Store and initializing discounts.
   * Store is setup by Store manager.

2) Store
   * Inventory
     * Store has an inventory
     * Inventory has available items with the quantity available.
     * Store checks with inventory whether it has the item requested by custome
     * Inventory can self adjust based on the orders processed.

   * Registers
     * Each register has a flag (free or not) set.
     * Store gets available register to process the order placed by customer
     * Register's job is to generate bill for the order placed.
       Only store can communicate with the registers.
     * Registers are exposed to Store's Discount Engine.
       While generating bill it checks for possible discounts to be applied on the order item
     * Orders
       * Each register has many orders which has been processed by it.
       * Each order has order items, customer who ordered, and the bill amount for the order.


   * DiscountEngine
     * Handle discounts to be applied
     * Has itemDiscounts, categoryDiscounts, Staff or senior citizen discounts
     * Discountable enum has possible discounts other than direct item / category.
       Item and category are not added to Discountables enum because there can be many such item/category.

Assumptions:
* Discounts are applied on the price of each item rather than the total.
* For now did not added transaction date in the processed orders.
  So there is not option to get transactions for a particular day


