import shortfilm from './../data/js/Shortfilm.js';

const socialmedia = ['instagram', 'facebook', 'googleplus', 'x'];
let watchlist = [];




function addToList(name) {
    if (watchlist.includes(name)) {
        watchlist = watchlist.filter(item => item !== name);
    } else {
        watchlist.push(name);
    }
    updateListStatus(name);
}


function updateListStatus(name) {
    const listText = document.querySelector('.add-list');
    const listIcon = document.getElementById('add-icon');
    if (watchlist.includes(name)) {
        listText.textContent = "ADDED";
        listIcon.src = "../../../src/assets/icons/check.svg";
    } else {
        listText.textContent = "MYLIST";
        listIcon.src = "../../../src/assets/icons/plus.svg";
    }
}

// Populate cast crew dynamically
function populateCastCrew() {
    const castContainer = document.getElementById('cast-crew');
    shortfilm.castcrew.forEach(cast => {
        const castDiv = document.createElement('div');
        castDiv.classList.add('cast', 'font-montserrat', 'font-semibold');
        castDiv.innerHTML = `
            <img src="../src/assets/images/short films/${cast.image}.png" alt="${cast.name}" class="w-[121px] h-[145px] object-contain mb-2" />
            <p class="text-[14px] font-bold capitalize">${cast.name}</p>
            <p class="text-[15px] capitalize font-bold text-[#A9A9A9]">${cast.role}</p>
        `;
        castContainer.appendChild(castDiv);
    });
}

// Populate recommended films dynamically
function populateRecommendedFilms() {
    const recommendBox = document.getElementById('recommend-box');
    shortfilm.awardshows.forEach(film => {
        const filmDiv = document.createElement('div');
        filmDiv.classList.add('bg-gray-200', 'min-w-[289px]', 'h-[172px]');
        filmDiv.innerHTML = `
            <img src="../../../src/assets/images/short films/${film.image}" alt="${film.name}" class="min-w-[289px] h-[172px] object-cover" />
        `;
        recommendBox.appendChild(filmDiv);
    });
}

// Populate social media icons dynamically
function populateSocialMedia() {
    const socialContainer = document.getElementById('social-media');
    socialmedia.forEach(social => {
        const socialLink = document.createElement('a');
        socialLink.href = "#";
        socialLink.classList.add('text-white', 'font-poppins', 'font-medium', 'text-[20px]');
        socialLink.innerHTML = `<img src="../src/assets/icons/${social}.svg" alt="${social}" class="w-[35px] text-white" />`;
        socialContainer.appendChild(socialLink);
    });
}

// Event listeners
document.getElementById('add-list-btn').addEventListener('click', function() {
    addToList("adimaithanam");
});

// Call functions to populate dynamic content
populateCastCrew();
populateRecommendedFilms();
populateSocialMedia();
