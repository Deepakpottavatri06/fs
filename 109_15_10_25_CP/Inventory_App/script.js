'use strict';

/**
 * ============================================================
 *  Inventory Cards — Student Starter (implement TODOs)
 *  NOTE: renderGrid() is fully implemented for you.
 *  Implement: init(), addToCart(), removeOne(), renderCart()
 *  Keep DOM structure/classes as-is (tests rely on them).
 * ============================================================
 */

/* ------------------ DOM Elements ------------------ */
const els = {
    grid: document.getElementById('grid'),
    cartList: document.getElementById('cartList'),
    cartCount: document.getElementById('cartCount'),
};

/* ------------------ App State ------------------ */
/** Cart structure: plain object keyed by id
 * {
 *   [id]: { id: number, name: string, price: number, qty: number }
 * }
 */
let CART = {};

/* ------------------ REQUIRED: init() ------------------ */
async function init() {
    // TODO: fetch data.json, call renderGrid(items) and renderCart()
    const items = await fetch("./data.json").
                        then(data=> data.json())
    // console.log(items);
    renderGrid(items)
    renderCart()

}

/* ------------------ PROVIDED: renderGrid() ------------------ */
/**
 * Already implemented for you. Do not modify.
 * - Renders cards (3 per row via CSS grid)
 * - Adds click handler: clicking a card should add item to cart (+1)
 */
function renderGrid(items) {
    els.grid.innerHTML = items
        .map(
            (item) => `
  <article class="card" data-id="${item.id}" data-name="${item.name}" data-price="${item.price}">
    <div class="row">
      <strong>${item.name}</strong>
      <span class="category">${item.category}</span>
    </div>
    <div class="row" style="margin-top:6px;">
      <span class="price">₹${item.price}</span>
      <span class="muted small">Stock: ${item.stock}</span>
    </div>
    <p class="desc">${item.description}</p>
    <p class="small muted">Click card to add to cart</p>
  </article>`
        )
        .join('');

    // Clicking a card adds that item to the cart
    els.grid.querySelectorAll('.card').forEach((cardEl) => {
        cardEl.addEventListener('click', () => {
            const id = Number(cardEl.dataset.id);
            const name = cardEl.dataset.name;
            const price = Number(cardEl.dataset.price);

            addToCart({
                id,
                name,
                price
            }); // <--- implement below
        //    renderCart() 
        });
    });
    
    
    
}

/* ------------------ REQUIRED: addToCart() ------------------ */
/**
 * TODO: Implement addToCart(item)
 * - If item.id not present in CART → create with qty=1
 * - Else increment existing qty by 1
 * - Then call renderCart()
 */
function addToCart(item) {
    // TODO
    //{ id: number, name: string, price: number, qty: number }
    if(CART[item.id]==null){
        CART[item.id] = {...item,qty : Number(1)}
       
    }
    else{

        CART[item.id].qty +=1
    }
    renderCart()
}

/* ------------------ REQUIRED: removeOne() ------------------ */
/**
 * TODO: Implement removeOne(id)
 * - Decrement qty for that id by 1
 * - If qty reaches 0 → delete CART[id]
 * - Then call renderCart()
 */
function removeOne(id) {
    // TODO:

    CART[id].qty-=1
    if(CART[id].qty==0) delete CART[id]
    console.log(CART);
    
    renderCart()
}

/* ------------------ REQUIRED: renderCart() ------------------ */
/**
 TODO: Implement renderCart()
  - Convert CART object → array of rows
  - Update #cartCount = sum of all item qty
  - If no rows → show "Cart is empty..." message and return
  - Build cart list HTML with per-item total and grand total:
     <div class="cart-row" data-id="...">
       <span class="name">Item Name</span>
       <span class="qty">x N</span>
       <span class="price">₹ITEM_TOTAL</span>
     </div>
     <hr>
     <div class="cart-row" style="cursor:default;">
       <span class="name">Grand Total</span>
       <span class="price">₹GRAND_TOTAL</span>
     </div>
  - Add click handler to each `.cart-row[data-id]` to call removeOne(id)
 */
function renderCart() {

    // TODO:
    let count = 0
    Object.keys(CART).map(key=>{
        count += CART[key].qty
    })
    if(count==0){
        els.cartList.innerText = "Cart is empty..."
        return
    }
    else{
        let GrandTotal = 0
        els.cartCount.innerText = count
        els.cartList.innerHTML = Object.keys(CART).map(key=>{
            GrandTotal += CART[key].qty*CART[key].price
            return `<div class="cart-row" data-id=${CART[key].id}>
       <span class="name">${CART[key].name}</span>
       <span class="qty">x ${CART[key].qty}</span>
       <span class="price">₹${CART[key].qty*CART[key].price} </span>
     </div>
     <hr>
     `
        }).join('')
     els.cartList.innerHTML += `<div class="cart-row" style="cursor:default;">
       <span class="name">Grand Total</span>
       <span class="price">₹${GrandTotal}</span>
     </div>`
    }

    els.cartList.querySelectorAll('.cart-row[data-id]').forEach((rowEl)=>{
        rowEl.addEventListener('click',()=>{
            const id = Number(rowEl.dataset.id)
            removeOne(id)
        })
    })

}

init()