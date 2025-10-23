// ========== Shop-Site Application Logic ==========
// NOTE TO STUDENTS:
// You must implement ALL the functions below.
// The HTML, CSS, and products.js files are already provided.
// DO NOT modify those files — only edit this file (app.js).

// ========== Navigation ==========

/**
 * goToProducts(category)
 * ----------------------
 * Redirect user to products.html with the selected category.
 * Example: products.html?category=Fruits
 */
function goToProducts(category) {
  // TODO: Implement redirection logic
  window.location.href = "products.html?category=" + category;
}

// ========== Rendering Products ==========

/**
 * renderProducts(list)
 * --------------------
 * Display all products in the given list as cards inside #product-list.
 * Each card should contain:
 *  - Product image
 *  - Product name
 *  - Product brand
 *  - Product cost
 *  - "Add to Cart" button (button should call addToCart(index))
 */
function renderProducts(list) {
  // TODO: Implement product rendering

  const productList = document.getElementById('product-list');

  productList.innerHTML = list.map(
    (item,index) =>
      `<article class="item" data-id="${index}"">

    <div class="row">
      <img src="${item.image}">
      <p><strong>${item.name}</strong></p>
      <p>Brand: ${item.brand}</p>
      <p>Cost: ${item.cost}</p>
      <button onClick="addToCart(${index})"> Add to Cart </button>
    </div>
    
  </article>`   
  )

  // productList.querySelectorAll('.item').forEach((item) => {
  //       const id = item.dataset.id
  //       const addToCartButton = item.querySelector(".row > button"); 
  //       addToCartButton.addEventListener('click', () => {
  //           addToCart(id); 
  //       });
  // });
}

// ========== Sorting ==========

/**
 * sortProducts()
 * --------------
 * Sort the current product list based on the selected option:
 *  - Name (A-Z)
 *  - Price (Low to High)
 *  - Price (High to Low)
 * Then re-render the products.
 */
function sortProducts() {
  // TODO: Implement sorting
  const value = document.getElementById('sort-select').value
  const base = [...filteredByCategory]

  if(value==='name'){
    base.sort((a,b)=>a.name.localeCompare(b.name))
  }
  else if(value==="price-asc"){
    base.sort((a,b)=>a.cost-b.cost);
  }
  else{
    base.sort((a,b)=>b.cost-a.cost);
  }
  renderProducts(base)

}

// ========== Filtering ==========

/**
 * filterProducts()
 * ----------------
 * Filter the product list by brand based on the dropdown value (#filter-select).
 * If "all" is selected, show all products for the current category.
 * Otherwise, show only products of the chosen brand.
 */
function filterProducts() {
  // TODO: Implement filtering
  const value = document.getElementById('filter-select').value 
  if(value==='all'){
    renderProducts(filteredByCategory)
    return
  }
  base = [...filteredByCategory]
  base = base.filter(a=>a.brand===value)
  renderProducts(base)
}

// ========== Cart Management ==========

let cart = [];

/**
 * addToCart(index)
 * ----------------
 * Add the product at the given index (from filteredByCategory) to the cart.
 * If the product is already in the cart, increase its quantity.
 * Otherwise, add it with quantity = 1.
 */
function addToCart(index) {
  // TODO: Implement add-to-cart logic
  // HINT: Use cart.find() to check if the item exists
  const existProduct =cart.find(prod=>prod.id===index)
  if(existProduct){
    existProduct.qty+=1
  } 
  else{
    const product = filteredByCategory[index]
    const newObj = {...product,id:index,qty:1}
    cart.push(newObj)
  }
  console.log(cart);
  
  renderCart()

}

/**
 * renderCart()
 * ------------
 * Render the cart table inside #cart-items.
 * Each row should show:
 *  - Product image
 *  - Product name
 *  - Product brand
 *  - Product cost
 *  - Quantity
 *  - Total (cost × quantity)
 */
function renderCart() {
  // TODO: Implement cart rendering
  // <tr>
  //       <th>Image</th>
  //       <th>Name</th>
  //       <th>Brand</th>
  //       <th>Price</th>
  //       <th>Quantity</th>
  //       <th>Total</th>
  //     </tr>
  const cartList = document.getElementById('cart-items')
  cartList.innerHTML = cart.map( product =>
    `
     <tr>
        <td><img src="${product.image}" width="60px"></td>
        <td>${product.name}</td>
        <td>${product.brand}</td>
        <td>${product.cost}</td>
        <td>${product.qty}</td>
        <td>${product.qty*product.cost}</td>
      </tr>
    `
  )
}

// ========== Initialization ==========

const urlParams = new URLSearchParams(window.location.search);
const category = urlParams.get("category");

let filteredByCategory = products.filter(key=> key.category===category)

/**
 * On page load:
 *  - If a category is selected (from URL), set the page title
 *  - Load products of that category into filteredByCategory
 *  - Render them using renderProducts()
 */
if (category) {
  // TODO: Implement initial category setup
  renderProducts(filteredByCategory)
}
