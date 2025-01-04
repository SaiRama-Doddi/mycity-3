// function filterProducts() {
//     const maxPrice = parseFloat(document.getElementById('priceRange').value);
//     document.getElementById('priceValue').innerText = maxPrice;

//     const discountFilters = Array.from(document.querySelectorAll('input[data-discount]:checked')).map(el => parseFloat(el.dataset.discount));
//     const ratingFilters = Array.from(document.querySelectorAll('input[data-rating]:checked')).map(el => parseFloat(el.dataset.rating));
//     const displayFilters = Array.from(document.querySelectorAll('input[data-display]:checked')).map(el => el.dataset.display);

//     const productCards = document.querySelectorAll('.product-card');
//     productCards.forEach(card => {
//         const price = parseFloat(card.dataset.price);
//         const discount = parseFloat(card.dataset.discount);
//         const rating = parseFloat(card.dataset.rating);
//         const display = card.dataset.display;

//         const discountApplied = discountFilters.length === 0 || discountFilters.includes(discount);

//         const ratingApplied = ratingFilters.length === 0 || ratingFilters.includes(rating);
//         const displayApplied = displayFilters.length === 0 || displayFilters.includes(display);

//         if (price <= maxPrice && discountApplied && ratingApplied && displayApplied) {
//             card.style.display = 'block';
//         } else {
//             card.style.display = 'none';
//         }
//     });
// }

function filterProducts() {
    // Get selected discounts
    const selectedDiscounts = Array.from(
        document.querySelectorAll('.filter-section input[data-discount]:checked')
    ).map(checkbox => checkbox.getAttribute('data-discount'));
debugger;
    // Get selected ratings
    const selectedRatings = Array.from(
        document.querySelectorAll('.filter-section input[data-rating]:checked')
    ).map(checkbox => checkbox.getAttribute('data-rating'));

    // Get all product cards
    const products = document.querySelectorAll('#productGrid');

    products.forEach(product => {
        const productDiscount = product.getAttribute('data-discount');
        const productRating = product.getAttribute('data-rating');

        // Determine if the product matches selected filters
        const matchesDiscount = selectedDiscounts.length === 0 || selectedDiscounts.includes(productDiscount);
        const matchesRating = selectedRatings.length === 0 || selectedRatings.includes(productRating);

        // Show product if it matches any filter, hide otherwise
        if (matchesDiscount && matchesRating) {
            product.style.display = 'block';
        } else {
            product.style.display = 'none';
        }
    });
}

function toggleList(id) {
    const list = document.getElementById(id);
    list.style.display = list.style.display === 'none' ? 'block' : 'none';
}

// Set initial display state for lists to hidden
document.querySelectorAll('.list ul').forEach(list => list.style.display = 'none');

        // Get all favorite buttons
const favoriteButtons = document.querySelectorAll('.favorite-button');

// Add event listener to each button
favoriteButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Toggle the 'active' class to change color
        button.classList.toggle('active'); 

        // Optional: Add logic to handle adding/removing from favorites
        // You might use localStorage or an API here
    });
});

    const truncateText = (text, maxLength) => {
        if (text.length > maxLength) {
            return text.slice(0, maxLength) + '...';
        }
        return text;
    };

    // Apply truncation to the product name
    const productNameElement = document.getElementById("productName");
    const originalText = productNameElement.textContent; // Get the text set by Thymeleaf
    const truncatedText = truncateText(originalText, 40); // Adjust 40 to your desired length
    productNameElement.textContent = truncatedText; // Update the text  