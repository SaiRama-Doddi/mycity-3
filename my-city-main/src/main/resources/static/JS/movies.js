
import { movies } from '../data/js/movie.js';

document.addEventListener("DOMContentLoaded", () => {

    let currentIndex = 0;
    let watchlist = [];


    const carouselContainer = document.getElementById('carousel-container');

    function renderCarousel() {
        carouselContainer.innerHTML = '';
        movies.images.forEach((image, index) => {
            const div = document.createElement('div');
            div.className = 'carousel-box relative';
            div.style.display = index === currentIndex ? 'block' : 'none';
            div.innerHTML = `
                <img src="${image}" alt="carousel-${index}" />
                <div class="watchlist-button flex gap-2 items-center absolute px-[40px] py-[9px] border-[3px] border-white rounded-xl bottom-[8%] left-[5%] cursor-pointer drop-shadow-[0_0_4px_4px_rgba(0,0,0,0.25)] ">
                    <img src="${watchlist.includes(image) ? '../src/assets/icons/check.svg' : '../src/assets/icons/play1.svg'}" alt="icon" class="w-[32px]" />
                    <h1 class="font-helvetica font-black text-[32px]">${watchlist.includes(image) ? 'Added to Watchlist' : 'Add to Watchlist'}</h1>
                </div>
            `;
            div.querySelector('.watchlist-button').addEventListener('click', () => toggleWatchlist(image));
            carouselContainer.appendChild(div);
        });
    }


    function startCarousel() {
        setInterval(() => {
            currentIndex = (currentIndex + 1) % movies.images.length;
            renderCarousel();
        }, 3000);
    }

    function toggleWatchlist(movie) {
        if (watchlist.includes(movie)) {
            watchlist = watchlist.filter((item) => item !== movie);
        } else {
            watchlist.push(movie);
        }
        renderCarousel();
    }

    const newReleases = document.getElementById('new-releases');
    const upcomingMovies = document.getElementById('upcoming-movies');

    function displayNewReleases() {
        movies.newMovies.forEach((movie, index) => {
            const link = document.createElement('a');
            link.href = `movies/intheatre/movieDetails.html?id=${index}`;
            const div = document.createElement('div');
            div.className = 'w-full min-h-[321px] min-w-[256px] flex flex-col text-white';
            div.innerHTML = `
                <div class='w-full relative'>
                    <img src="../src/assets/images/movies/${movie.image}.png" alt="${movie.name}" class='w-[256px] h-[321px]' />
                    <div class='ratings flex w-full bg-[#00000099] opacity-[1] text-white py-4 px-2 absolute bottom-0 gap-5'>
                        <p class='text-[16px] w-full flex items-center gap-1'><img src="../src/assets/icons/star.svg" alt="Star" width={15} />${movie.rating}/10</p>
                        <p class='text-[16px] items-center flex w-[100%] text-nowrap'>${movie.votes} Votes</p>
                    </div>
                </div>
                <div class='flex flex-col gap-1 mt-[1rem]'>
                    <p class='font-bold text-[24px]'>${movie.name}</p>
                    <p class='text-[#666666] text-[20px] font-medium'>${movie.censor}</p>
                    <p class='text-[#666666] text-[16px] font-medium'>${movie.language}</p>
                </div>
            `;
            link.appendChild(div);
            newReleases.appendChild(link);
        });
    }

    function displayUpcomingMovies() {
        movies.UpcomingMovies.forEach((movie, index) => {
            const link = document.createElement('a');
            link.href = `../src/movies/upcoming/upcomingMovie.html?id=${index}`;
            const div = document.createElement('div');
            div.className = 'w-full min-h-[321px] min-w-[256px] flex flex-col text-white';
            div.innerHTML = `
                <div class='w-full relative'>
                    <img src="../src/assets/images/movies/${movie.image}.png" alt="${movie.name}" class='w-[256px] h-[321px]' />
                    <div class='ratings flex w-full bg-[#00000099] opacity-[1] text-white py-4 px-2 absolute bottom-0 gap-5'>
                        <p class='text-sm items-center flex w-[100%] text-nowrap'>${movie.releasedate}</p>
                    </div>
                </div>
                <div class='flex flex-col gap-1 mt-[1rem]'>
                    <p class='font-bold text-[24px]'>${movie.name}</p>
                    <p class='text-[#666666] text-[20px] font-medium'>${movie.genre}</p>
                    <p class='text-[#666666] text-[16px] font-medium flex items-center pt-1 gap-1'><img src="../src/assets/icons/like.svg" alt="like" width={25} class='-translate-y-[.2rem]' />${movie.likes} Likes</p>
                </div>
            `;
            link.appendChild(div);
            upcomingMovies.appendChild(link);
        });
    }

    renderCarousel();
    startCarousel();
    displayNewReleases();
    displayUpcomingMovies();

});