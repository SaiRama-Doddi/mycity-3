
document.addEventListener("DOMContentLoaded", function () {
    // Handle Person Dropdown
    const personFilter = document.querySelector(".filterbox[data-type='Persons']");
    const personDropdown = personFilter.querySelector(".dropdownContent");
    const personSelectedText = personFilter.querySelector(".filter-input .selectedText");

    personFilter.addEventListener("click", function (event) {
        event.stopPropagation(); // Prevents closing dropdown immediately
        personDropdown.classList.toggle("show");
    });

    personDropdown.querySelectorAll(".listItem input").forEach((input) => {
        input.addEventListener("change", function () {
            personSelectedText.textContent = input.labels[0].textContent;
            personDropdown.classList.remove("show");
        });
    });

    // Handle Check-in Date Picker
    const checkinFilter = document.querySelector(".filterbox[data-type='Check-in'] .filter-input");
    const checkinSelectedText = checkinFilter.querySelector(".selectedText");

    checkinFilter.addEventListener("click", function (event) {
        event.stopPropagation(); // Prevent dropdown conflicts

        // Create visible date input
        const checkinInput = document.createElement("input");
        checkinInput.type = "date";
        checkinInput.style.position = "absolute";
        checkinInput.style.opacity = "2"; // Make input visible
        checkinInput.style.zIndex = "1400"; // Ensure it's above other elements
        document.body.appendChild(checkinInput);

        // Position the date picker relative to the checkinFilter
        const rect = checkinFilter.getBoundingClientRect();
        checkinInput.style.left = `${rect.left}px`;
        checkinInput.style.top = `${rect.bottom + window.scrollY}px`;

        checkinInput.focus(); // Open date picker

        // Handle date selection
        checkinInput.addEventListener("change", function () {
            if (checkinInput.value) {
                checkinSelectedText.textContent = checkinInput.value;
            }
            document.body.removeChild(checkinInput); // Clean up
        });

        // Close date picker if clicked outside
        checkinInput.addEventListener("blur", function () {
            document.body.removeChild(checkinInput); // Clean up on blur
        });
    });

    // Handle Check-out Date Picker
    const checkoutFilter = document.querySelector(".filterbox[data-type='Check-out'] .filter-input");
    const checkoutSelectedText = checkoutFilter.querySelector(".selectedText");

    checkoutFilter.addEventListener("click", function (event) {
        event.stopPropagation(); // Prevent dropdown conflicts

        // Create visible date input
        const checkoutInput = document.createElement("input");
        checkoutInput.type = "date";
        checkoutInput.style.position = "absolute";
        checkoutInput.style.opacity = "1"; // Make input visible
        checkoutInput.style.zIndex = "1000"; // Ensure it's above other elements
        document.body.appendChild(checkoutInput);

        // Position the date picker relative to the checkoutFilter
        const rect = checkoutFilter.getBoundingClientRect();
        checkoutInput.style.left = `${rect.left}px`;
        checkoutInput.style.top = `${rect.bottom + window.scrollY}px`;

        checkoutInput.focus(); // Open date picker

        // Handle date selection
        checkoutInput.addEventListener("change", function () {
            if (checkoutInput.value) {
                checkoutSelectedText.textContent = checkoutInput.value;
            }
            document.body.removeChild(checkoutInput); // Clean up
        });

        // Close date picker if clicked outside
        checkoutInput.addEventListener("blur", function () {
            document.body.removeChild(checkoutInput); // Clean up on blur
        });
    });

    // Close dropdown when clicking outside
    document.addEventListener("click", function (event) {
        if (!personFilter.contains(event.target)) {
            personDropdown.classList.remove("show");
        }
    });
});



document.addEventListener('DOMContentLoaded', function() {
    // Initialize date pickers
    const checkInPicker = new Datepicker(document.querySelector('.filterbox[data-type="Check-in"] .filter-input'), {
        format: 'yyyy-mm-dd',
        minDate: new Date(),
        autohide: true,
        todayHighlight: true
    });

    const checkOutPicker = new Datepicker(document.querySelector('.filterbox[data-type="Check-out"] .filter-input'), {
        format: 'yyyy-mm-dd',
        minDate: new Date(),
        autohide: true,
        todayHighlight: true
    });

    // Update selected text when dates are chosen
    checkInPicker.element.addEventListener('changeDate', function(e) {
        const selectedText = e.target.closest('.filterbox').querySelector('.selectedText');
        selectedText.textContent = e.detail.date.toISOString().split('T')[0];
        checkOutPicker.setMinDate(e.detail.date);
    });

    checkOutPicker.element.addEventListener('changeDate', function(e) {
        const selectedText = e.target.closest('.filterbox').querySelector('.selectedText');
        selectedText.textContent = e.detail.date.toISOString().split('T')[0];
    });

    // Handle person selection dropdown
    const personDropdown = document.querySelector('.filterbox[data-type="Persons"]');
    const personOptions = personDropdown.querySelectorAll('input[type="radio"]');
    
    personOptions.forEach(option => {
        option.addEventListener('change', function() {
            const selectedText = personDropdown.querySelector('.selectedText');
            selectedText.textContent = this.parentElement.querySelector('label').textContent;
        });
    });
});

// Room filtering functionality
document.getElementById("reserveHotel").addEventListener("click", function() {
    clearErrors();
    
    // Get and validate inputs
    const personInput = document.querySelector('input[name="person"]:checked');
    const checkInDate = document.querySelector('.filterbox[data-type="Check-in"] .selectedText').textContent;
    const checkOutDate = document.querySelector('.filterbox[data-type="Check-out"] .selectedText').textContent;

    // Validate all inputs
    if (!validateInputs(personInput, checkInDate, checkOutDate)) {
        return;
    }

    // Show loading state
    showLoading();

    // Extract the number from the person input id (e.g., "one", "two", etc.)
    const personCountMap = {
        'one': '1',
        'two': '2',
        'four': '4',
        'six': '6'
    };
    const personCount = personCountMap[personInput.id];

    fetchAvailableRooms(personCount, checkInDate, checkOutDate)
        .then(response => {
            if (!response.success) {
                throw new Error(response.message);
            }
            return response.data;
        })
        .then(handleRoomResults)
        .catch(handleError)
        .finally(hideLoading);
});

function validateInputs(personInput, checkInDate, checkOutDate) {
    if (!personInput) {
        showError("Please select number of persons");
        return false;
    }

    if (checkInDate === 'Date' || checkOutDate === 'Date') {
        showError("Please select both check-in and check-out dates");
        return false;
    }

    if (!isValidDateRange(checkInDate, checkOutDate)) {
        showError("Invalid date range selected");
        return false;
    }

    return true;
}

function isValidDateRange(checkIn, checkOut) {
    const checkInDate = new Date(checkIn);
    const checkOutDate = new Date(checkOut);
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    return checkInDate >= today && checkOutDate > checkInDate;
}

function fetchAvailableRooms(personCount, checkInDate, checkOutDate) {
    const params = new URLSearchParams({
        personCount: personCount,
        checkInDate: checkInDate,
        checkOutDate: checkOutDate
    });

    return fetch(`/rooms/availability?${params}`)
        .then(response => response.json())
        .catch(error => {
            console.error('Network error:', error);
            throw new Error('Failed to connect to the server');
        });
}

function handleRoomResults(rooms) {
    const roomDetailsBox = document.getElementById("roomDetailsBox");
    const filteredRoomList = document.createElement('div');
    filteredRoomList.id = 'filteredRoomList';
    filteredRoomList.className = 'filtered-rooms';

    if (!Array.isArray(rooms) || rooms.length === 0) {
        filteredRoomList.innerHTML = `
            <div class="no-rooms-message">
                <p>No rooms available for the selected criteria.</p>
            </div>
        `;
    } else {
        rooms.forEach(room => {
            filteredRoomList.innerHTML += `
                <div class="roomdetail">
                    <div class="room-detail">
                        <img class="roomImage" src="data:image/jpeg;base64,${room.primaryRoomImageBase64}" alt="${room.roomType}">
                    </div>
                    <div class="featureBox">
                        <div class="roomFeaturesBox">
                            <div class="roomFeature">
                                <p class="title">${room.roomType}</p>
                            </div>
                            <div class="roomFeature">
                                <p class="roomPrice">$${room.pricePerNight}</p>
                            </div>
                            <div class="roomFeature">
                                <p>Max Guests: ${room.maxGuests}</p>
                            </div>
                            <div class="roomFeature">
                                <p>${room.isAvailable ? 'Available' : 'Not Available'}</p>
                            </div>
                            ${room.hotelName ? `
                            <div class="roomFeature">
                                <p>${room.hotelName}</p>
                            </div>
                            ` : ''}
                        </div>
                        <button class="reserveBtn" onclick="bookRoom(${room.roomId})">Book Now</button>
                    </div>
                </div>
            `;
        });
    }

    // Replace original content with filtered results
    roomDetailsBox.style.display = 'none';
    roomDetailsBox.parentNode.insertBefore(filteredRoomList, roomDetailsBox.nextSibling);
}

function clearErrors() {
    const existingError = document.querySelector('.error-message');
    if (existingError) {
        existingError.remove();
    }
}

function showError(message) {
    clearErrors();
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
    errorDiv.textContent = message;
    document.querySelector('.filtercontainer').appendChild(errorDiv);
    setTimeout(() => errorDiv.remove(), 3000);
}

function handleError(error) {
    console.error('Error:', error);
    showError(error.message || 'An error occurred while fetching available rooms');
}

function showLoading() {
    const loader = document.createElement('div');
    loader.className = 'loader';
    loader.innerHTML = '<div class="spinner"></div>';
    document.querySelector('.rooms-details').appendChild(loader);
}

function hideLoading() {
    const loader = document.querySelector('.loader');
    if (loader) {
        loader.remove();
    }
}

function bookRoom(roomId) {
    // Implement booking functionality
    console.log(`Booking room ${roomId}`);
    // Add your booking logic here
}
