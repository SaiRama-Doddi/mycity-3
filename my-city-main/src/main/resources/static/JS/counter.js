// let counter = 1;

// const counterValue = document.getElementById('counter-value');
// const incrementBtn = document.getElementById('increment-btn');
// const decrementBtn = document.getElementById('decrement-btn');
// const resetBtn = document.querySelector('#reset');

// // To increment the value of counter
// incrementBtn.addEventListener('click', () => {
//     counter++;
//     counterValue.innerHTML = counter;
//     localStorage.setItem("quantity", counter);
// });

// // To decrement the value of counter
// decrementBtn.addEventListener('click', () => {
//     if (counter > 0) {  // Prevents counter from going below 0
//         counter--;
//         counterValue.innerHTML = counter;
//     }
// });

let counter = 1;

const counterValue = document.getElementById('counter-value');
const incrementBtn = document.getElementById('increment-btn');
const decrementBtn = document.getElementById('decrement-btn');
const quantityInput = document.getElementById('quantity-input');

// Ensure the hidden input exists
if (quantityInput) {
    quantityInput.value = counter; // Initialize with the starting value
}

// To increment the value of the counter
incrementBtn.addEventListener('click', () => {
    counter++;
    counterValue.innerHTML = counter;
    localStorage.setItem("quantity", counter);
    if (quantityInput) {
        quantityInput.value = counter; // Update the hidden input dynamically
    }
});

// To decrement the value of the counter
decrementBtn.addEventListener('click', () => {
    if (counter > 0) {
        counter--;
        counterValue.innerHTML = counter;
        localStorage.setItem("quantity", counter);
        if (quantityInput) {
            quantityInput.value = counter; // Update the hidden input dynamically
        }
    }
});
