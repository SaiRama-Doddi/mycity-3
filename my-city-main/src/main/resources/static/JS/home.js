import { hotels } from "../datas/hotelsDetails.js";



const hotelsList = document.getElementById('hotelsList1');

const hotelsList2 = document.getElementById('hotelsList2');

hotels.forEach((hotel,index) => {
    const hoteldiv = document.createElement('a');
    hoteldiv.className = 'hoteldiv'
    hoteldiv.href = `hotels.html?id=${index}`

    const hotelImage = document.createElement('img');
    hotelImage.className = 'hotelImage';
    hotelImage.src = hotel.image;
    hotelImage.alt = hotel.name;

    const hotelName = document.createElement('h1');
    hotelName.className = 'hotelName';
    hotelName.innerText = hotel.name;

    const hotelLocation = document.createElement('p');
    hotelLocation.innerText = hotel.location;
    hotelLocation.className = 'hotelLocation';


    const hotelReviewBox = document.createElement('div');
    hotelReviewBox.className = 'hotelReviewBox';


    const hotelRatings = document.createElement('div');
    hotelRatings.className = 'hotelRatings';

    const totalStars = hotel.ratings;
    for(let  i=0; i< totalStars; i++ ){
        const hotelStars = document.createElement('img');
        hotelStars.src = '../assets/icons/orangestar.svg';
        hotelStars.alt = 'stars';
        hotelRatings.appendChild(hotelStars);
    }

    const hotelReviews = document.createElement('p');
    hotelReviews.className = 'hotelReviews';
    hotelReviews.innerText = `(${hotel.reviews} Reviews)`;

    hotelReviewBox.appendChild(hotelRatings);
    hotelReviewBox.appendChild(hotelReviews);




    hoteldiv.appendChild(hotelImage);
    hoteldiv.appendChild(hotelName);
    hoteldiv.appendChild(hotelLocation);
    hoteldiv.appendChild(hotelReviewBox);


    hotelsList.appendChild(hoteldiv);
});

hotels.forEach((hotel,index) => {
    const hoteldiv = document.createElement('a');
    hoteldiv.className = 'hoteldiv'
    hoteldiv.href = `hotels.html?id=${index}`

    const hotelImage = document.createElement('img');
    hotelImage.className = 'hotelImage';
    hotelImage.src = hotel.image;
    hotelImage.alt = hotel.name;

    const hotelName = document.createElement('h1');
    hotelName.className = 'hotelName';
    hotelName.innerText = hotel.name;

    const hotelLocation = document.createElement('p');
    hotelLocation.innerText = hotel.location;
    hotelLocation.className = 'hotelLocation';


    const hotelReviewBox = document.createElement('div');
    hotelReviewBox.className = 'hotelReviewBox';


    const hotelRatings = document.createElement('div');
    hotelRatings.className = 'hotelRatings';

    const totalStars = hotel.ratings;
    for(let  i=0; i< totalStars; i++ ){
        const hotelStars = document.createElement('img');
        hotelStars.src = '../assets/icons/orangestar.svg';
        hotelStars.alt = 'stars';
        hotelRatings.appendChild(hotelStars);
    }

    const hotelReviews = document.createElement('p');
    hotelReviews.className = 'hotelReviews';
    hotelReviews.innerText = `(${hotel.reviews} Reviews)`;

    hotelReviewBox.appendChild(hotelRatings);
    hotelReviewBox.appendChild(hotelReviews);




    hoteldiv.appendChild(hotelImage);
    hoteldiv.appendChild(hotelName);
    hoteldiv.appendChild(hotelLocation);
    hoteldiv.appendChild(hotelReviewBox);


    hotelsList2.appendChild(hoteldiv);
})


const dropdowns = document.querySelectorAll('.dropdown');

dropdowns.forEach(dropdown => {
    const filterBox = dropdown.querySelector('.filter');
    const arrowIcon = dropdown.querySelector('.arrowIcon');
    const dropdownContent = dropdown.querySelector('.dropdownContent');
    const selectedText = dropdown.querySelector('.selectedText');
    const checkboxes = dropdown.querySelectorAll('.checkboxItem');
    
    let selectedItems = [];
    

    const toggleDropdown = () => {
        dropdownContent.classList.toggle('active');
        arrowIcon.classList.toggle('active');
    };


    const handleSelection = (e) => {
        const item = e.target.id;
        
        selectedItems = item;

        selectedText.innerText = selectedItems.length <= 0
            ? dropdown.getAttribute('data-type') 
            : selectedItems;
    };
    
    filterBox.addEventListener('click', toggleDropdown);
    checkboxes.forEach(checkbox => checkbox.addEventListener('click', handleSelection));
});



document.addEventListener("DOMContentLoaded", function () {
    // Select all elements with the class "rating-value"
    const ratingElements = document.querySelectorAll(".rating-value");

    ratingElements.forEach((ratingElement) => {
        // Find the next sibling with the class "stars-container"
        const starsContainer = ratingElement.nextElementSibling;

        if (ratingElement && starsContainer) {
            // Get the rating value and parse it to an integer
            const rating = parseInt(ratingElement.textContent.trim(), 10);

            // Clear any existing stars (if applicable)
            starsContainer.innerHTML = "";

            // Add star icons dynamically based on the rating value
            for (let i = 0; i < rating; i++) {
                const star = document.createElement("i");
                star.classList.add("fa", "fa-star", "text-warning"); // Font Awesome star icon
                starsContainer.appendChild(star);
            }
        }
    });
});












/* home hotels  */



document.addEventListener("DOMContentLoaded", function () {
    const starsContainers = document.querySelectorAll(".stars-container");
    starsContainers.forEach(container => {
        const rating = parseFloat(container.getAttribute("data-rating"));
        const starPercentage = (rating / 5) * 100; // Assuming the rating is out of 5
        container.style.setProperty("--star-percentage", `${starPercentage}%`);
        container.style.width = `${starPercentage}%`; // Dynamic width based on rating
    });
});
/* 
function filterHotels() {
    const searchInput = document.getElementById("hotelSearch").value.toLowerCase();
    const hotelCards = document.querySelectorAll(".hotellists .col-md-4");

    hotelCards.forEach(card => {
        const hotelName = card.querySelector(".card-title").textContent.toLowerCase();
        const hotelAddress = card.querySelector(".card-text").textContent.toLowerCase();

        if (hotelName.includes(searchInput) || hotelAddress.includes(searchInput)) {
            card.style.display = "block"; // Show matching card
        } else {
            card.style.display = "none"; // Hide non-matching card
      }
   });
}
 */  

 function filterHotels() {
    const searchInput = document.getElementById("hotelSearch").value.toLowerCase();
    const hotelCards = document.querySelectorAll(".hotellists .col-md-4");
    let firstMatch = null;

    hotelCards.forEach(card => {
        const hotelName = card.querySelector(".card-title").textContent.toLowerCase();
        const hotelAddress = card.querySelector(".card-text").textContent.toLowerCase();

        if (hotelName.includes(searchInput) || hotelAddress.includes(searchInput)) {
            card.style.display = "block"; // Show matching card
            if (!firstMatch) {
                firstMatch = card; // Store the first matching card
            }
        } else {
            card.style.display = "none"; // Hide non-matching card
        }
    });

    if (firstMatch) {
        // Scroll to the first matching hotel card
        firstMatch.scrollIntoView({ behavior: "smooth", block: "center" });
    }
}

// Existing search functionality
document.getElementById("searchForm").addEventListener("submit", function (e) {
    e.preventDefault(); // Prevent default form submission
    const form = this;

    // Perform the search using the current form action
    const url = form.action + "?" + new URLSearchParams(new FormData(form)).toString();
    fetch(url)
        .then(response => response.text())
        .then(html => {
            // Replace hotel list content with search results
            document.querySelector(".hotellists").innerHTML = new DOMParser()
                .parseFromString(html, "text/html")
                .querySelector(".hotellists").innerHTML;

            // Scroll to hotel list container
            document.getElementById("roomDetailsBox").scrollIntoView({ behavior: "smooth" });
        })
        .catch(error => console.error("Error:", error));
});

// Detect when the search input is cleared or updated (input event)
const searchInput = document.querySelector("#searchForm input[type='text']");
searchInput.addEventListener("input", function () {
    if (!searchInput.value.trim()) {
        // If the search box is empty, fetch and show all hotel cards
        fetchAllHotels();
    }
});

// Function to fetch and display all hotel cards (when search is cleared)
function fetchAllHotels() {
    const url = 'http://localhost:8080/ex';  // URL for fetching all hotels

    fetch(url)
        .then(response => response.text())
        .then(html => {
            // Replace hotel list content with all hotels
            document.querySelector(".hotellists").innerHTML = new DOMParser()
                .parseFromString(html, "text/html")
                .querySelector(".hotellists").innerHTML;

            // Scroll to hotel list container (optional)
            document.getElementById("roomDetailsBox").scrollIntoView({ behavior: "smooth" });
        })
        .catch(error => console.error("Error:", error));
}
    

