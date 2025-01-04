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

document.getElementById('add-list-btn').addEventListener('click', function() {
    addToList("adimaithanam");
});

function selectedItem(item) {
    const awardShowsContainer = document.getElementById('awardShowsContainer');
    const filteredFilms = item === 'all' ? shortfilm.awardshows : shortfilm.awardshows.filter(show => show.language === item);

    awardShowsContainer.innerHTML = filteredFilms.map(film => `
        <a href="${film.link}" class="flex flex-col font-montserrat min-w-[250px] gap-3">
            <img src="../src/assets/images/short films/${film.image}" alt="${film.name}" class="min-w-[200px] h-[200px] object-contain">
            <div class="flex justify-between pr-4 items-start translate-y-[-.5rem]">
                <div class="flex flex-col">
                    <p class="text-[14px] font-bold">${film.name}</p>
                    <p class="font-semibold text-[10px] text-[#AFAFAF]">${film.year}</p>
                </div>
                <div class="flex h-full gap-1 items-start">
                    <img src="../src/assets/icons/eye.svg" alt="eye" width="15">
                    <img src="../src/assets/icons/heart2.svg" alt="heart" width="15">
                </div>
            </div>
        </a>
    `).join('');
}


function setLanguage(language) {
    const recommendedContainer = document.getElementById('recommendedFilms');
    const recommendedFilms = language === 'all' ? shortfilm.recommended : shortfilm.recommended.filter(film => film.language === language);

    recommendedContainer.innerHTML = recommendedFilms.map(film => `
        <a href="${film.link}" class="flex flex-col font-montserrat min-w-[250px] gap-3">
            <img src="../src/assets/images/short films/${film.image}" alt="${film.name}" class="min-w-[200px] h-[200px] object-contain">
            <div class="flex justify-between pr-4 items-start translate-y-[-.5rem]">
                <div class="flex flex-col">
                    <p class="text-[14px] font-bold">${film.name}</p>
                    <p class="font-semibold text-[10px] text-[#AFAFAF]">${film.year}</p>
                </div>
                <div class="flex h-full gap-1 items-start">
                    <img src="../src/assets/icons/eye.svg" alt="eye" width="15">
                    <img src="../src/assets/icons/heart2.svg" alt="heart" width="15">
                </div>
            </div>
        </a>
    `).join('');
}


function loadLanguages() {
    const languagesContainer = document.getElementById('languages');
    languagesContainer.innerHTML = shortfilm.languages.map(lang => `
        <p class="text-[16px] capitalize cursor-pointer font-medium w-fit h-fit px-[1.5rem] py-[.5rem] rounded-[30px] text-white bg-[#5C5C5C]" data-lang="${lang.toLowerCase()}">${lang}</p>
    `).join('');
}


function loadSocialMedia() {
    const socialContainer = document.getElementById('socialMediaLinks');
    socialContainer.innerHTML = socialmedia.map(social => `
        <img src="../src/assets/icons/${social}.svg" alt="${social}" class="cursor-pointer w-[30px]">
    `).join('');
}

// Event delegation to handle language selection and film category selection
document.addEventListener('DOMContentLoaded', function() {
    selectedItem('all'); 
    loadLanguages();     
    loadSocialMedia(); 
    setLanguage('all')  

    // Event listener for clicking on a language
    document.getElementById('languages').addEventListener('click', function(event) {
        const target = event.target;
        if (target && target.hasAttribute('data-lang')) {
            const selectedLang = target.getAttribute('data-lang');
            setLanguage(selectedLang);
        }
    });

    
    document.getElementById('languageDropdown').addEventListener('change', function(event) {
        const target = event.target;
       
        selectedItem(target.value)
    });
});
