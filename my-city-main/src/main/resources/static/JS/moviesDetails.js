
import { newMovieDetails } from "../../assets/data/js/movieDetails.js";
import { movies } from "../data/js/movie.js";

document.addEventListener("DOMContentLoaded", () => {
    const mainImage = document.getElementById("mainImage");
    const posterImage = document.getElementById("posterImage");
    const movieName = document.getElementById("movieName");
    const movieRating = document.getElementById("movieRating");
    const movieVotes = document.getElementById("movieVotes");
    const movieDuration = document.getElementById("movieDuration");
    const movieGenre = document.getElementById("language");
    const movieOneliner = document.getElementById("movieOneliner");
    const theaterList = document.getElementById("theaterList");
    const castList = document.getElementById("castList");
    const reviewMarquee = document.getElementById("reviewMarquee");
    const reviewsList = document.getElementById("reviewsList");
    const suggestedMovies = document.getElementById("suggestedMovies");
    const calendarContainer = document.getElementById("calendarContainer");
    const calendarToggle = document.getElementById("calendarToggle");



    function getQueryParams() {
        const params = new URLSearchParams(window.location.search);
        const ids = ['1', '2', '3', '4', '5', '6']

        return ids.includes(params.get('id')) ? params.get('id') : 0;

    }

    const id = getQueryParams();



    const currentMovie = newMovieDetails.details[id];
    const movieReview = newMovieDetails.reviews[id];


    mainImage.src = `../../assets/images/movies/${currentMovie.mainimage}`;
    mainImage.className = 'z-40 rounded-[30px] w-[1364px] h-[613px] object-cover'
    posterImage.src = `../../assets/images/movies/${currentMovie.poster}`;
    movieName.innerText = currentMovie.movieName;
    movieRating.innerText = currentMovie.rating;
    movieVotes.innerText = `(${currentMovie.votes}) Votes`;
    movieDuration.innerText = currentMovie.duration;
    movieGenre.innerText = currentMovie.genre;
    movieOneliner.innerText = currentMovie.oneliner;

    // Populate theaters
    currentMovie.theaters.forEach((theater) => {
        const theaterDiv = document.createElement("div");
        theaterDiv.className = 'w-full min-h-[425px] min-w-[373px] flex flex-col text-white';
        theaterDiv.innerHTML = `
            <div class="w-full relative">
            <img src="../../assets/images/${theater}.png" alt="${theater.name}" class="w-[373px] h-[425px]" />
            <div class="locations flex w-full bg-[#00000099] opacity-[1] text-white py-4 px-2 absolute left-0 bottom-0 justify-between items-center">
                <div>
                    <p class="font-semibold text-[32px] capitalize">${theater}</p>
                    <p class="font-normal text-[24px]">Vishakapatinam</p>
                </div>
                <button class="bg-[#F37022] h-fit py-[13px] px-[15px] rounded-[15px] font-bold">Buy Tickets</button>
            </div>
            </div>
        `;
        theaterList.appendChild(theaterDiv);
    });

    
    currentMovie.actors.forEach((actor) => {
        const actorDiv = document.createElement("div");
        actorDiv.className = 'actor-card flex flex-col text-white min-w-[300px] text-white items-center gap-[1rem]';
        actorDiv.innerHTML = `
            <img src="../../assets/images/actors/${actor}.jpeg" alt="${actor}" class="w-[250px] h-[250px] rounded-full object-cover" />
            <h1 class="font-semibold text-[36px] text-center text-nowrap capitalize w-[200px] overflow-x-scroll scrollbarhide">${actor}</h1>
            <p class="text-[#666666] font-normal text-[36px]">Actor</p>
        `;
        castList.appendChild(actorDiv);
    });

   
    movieReview.forEach((review) => {
        const reviewDiv = document.createElement("div");
        reviewDiv.className = 'event-cards flex flex-col gap-5 min-h-[348px] w-[100%] max-w-[508px] rounded-[10px] border-[1px] border-[#F37022] text-white px-5 py-3 ';
        reviewDiv.innerHTML = `
            <img src="../../assets/images/reviews/${review.image}" alt="${review.image}" class="w-[63px]  rounded-t-[20px]"/>
            <p class="text-wrap font-semibold text-[20px] font-roboto leading-[23px]">${review.content}</p>
        `;
        const starContainer = document.createElement('div');
            starContainer.className = 'flex gap-2 self-end mt-auto';

            for (let i = 0; i < review.stars; i++) {
                const starImg = document.createElement('img');
                starImg.src = '../../../src/assets/icons/star.svg';
                starImg.alt = 'star';
                starImg.width = 30;
                starImg.height = 38;
                starImg.className = 'fill-[#FFDA92]';
                
                starContainer.appendChild(starImg);
            }
        reviewDiv.appendChild(starContainer)
        reviewsList.appendChild(reviewDiv);
    });

    movies.UpcomingMovies.forEach((movie, index) => {
        const link = document.createElement('a');
        link.href = `../upcoming/upcomingMovie.html?id=${index}`;
        const div = document.createElement('div');
        div.className = 'w-full min-h-[321px] min-w-[256px] flex flex-col text-white';
        div.innerHTML = `
            <div class='w-full relative'>
                <img src="../../assets/images/movies/${movie.image}.png" alt="${movie.name}" class='w-[256px] h-[321px]' />
                <div class='ratings flex w-full bg-[#00000099] opacity-[1] text-white py-4 px-2 absolute bottom-0 gap-5'>
                    <p class='text-sm items-center flex w-[100%] text-nowrap'>${movie.releasedate}</p>
                </div>
            </div>
            <div class='flex flex-col gap-1 mt-[1rem]'>
                <p class='font-bold text-[24px]'>${movie.name}</p>
                <p class='text-[#666666] text-[20px] font-medium'>${movie.genre}</p>
                <p class='text-[#666666] text-[16px] font-medium flex items-center pt-1 gap-1'><img src="../../../src/assets/icons/like.svg" alt="like" width={25} class='-translate-y-[.2rem]' />${movie.likes} Likes</p>
            </div>
        `;
        link.appendChild(div);
        suggestedMovies.appendChild(link);
    });

   
    calendarToggle.addEventListener("click", () => {
        calendarContainer.classList.toggle("hidden");
    });
});
