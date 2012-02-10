#Aurum, a Gilt Scala API

##About
Aurum is a simple scala client binding for [Gilt's API](http://dev.gilt.com/). It is designed to be simple to use, so that developers don't need to worry about making the correct requests or interpreting the results of those requests, and can instead get right to developing their awesome apps!

##To Use
Each object (Sale, Product, Image, SKU) directly represents the contents of each respective "response" subsection [listed on this page](https://dev.gilt.com/page/gilt-public-apis).

###Initialization
    val client = new GiltClient(apikey) //where apikey is already defined

###Sales
####Get List of All Active Sales
    val list_of_sales = client.active

####Get List of All Active Sales Within A Store
    val list_of_sales = client.active("women") //argument should be a valid store

####Get List of Upcoming Sales
    val list_of_sales = client.upcoming

####Get List of Upcoming Sales Within A Store
    val list_of_sales = client.upcoming("men") //argument should be a valid store

####Get Details of a Sale
    val details = client.detail("kids", sale_key) //where sale_key is valid and previously defined

###Product
####Get Details of a Product Using a Product ID
    val details = client.detail(product_id) //where product_id is valid and previously defined

####Get Details of a Product Using a valid URL
Part of the response from a Sale is a list of URLs of valid API calls for getting product details. These URLs can be used directly to get the details of any Product in a Sale.

    val details = client.detailFromURL(url) //where url is part of a valid response from a Sale

##Contributing to Aurum
* Check out the latest master to make sure the feature hasn’t been implemented or the bug hasn’t been fixed yet
* Check out the issue tracker to make sure someone already hasn’t requested it and/or contributed it
* Fork the project
* Start a feature/bugfix branch
* Commit and push until you are happy with your contribution
* Make sure to add tests for it. This is important so I don’t break it in a future version unintentionally.

##Contributors
This client binding was written by [Moses Nakamura](http://github.com/mnn2104) and [Vivek Bhagwat](http://github.com/vivekbhagwat)
