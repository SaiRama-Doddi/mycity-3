
import { newMovieDetails, upcomingMovies } from "../data/js/movieDetails.js";
import { movies } from "../data/js/movie.js";

document.addEventListener("DOMContentLoaded", () => {
    const mainImage = document.getElementById("mainImage");
    const posterImage = document.getElementById("posterImage");
    const movieName = document.getElementById("movieName");
    const movieLikes = document.getElementById("movieLikes");
    const releaseDate = document.getElementById('releaseDate');
    const movieLanguage = document.getElementById("language");
    const movieGenre = document.getElementById("movieGenre");
    const movieOneliner = document.getElementById("movieOneliner");
    const suggestedMovies = document.getElementById("suggestedMovies");
    const calendarContainer = document.getElementById("calendarContainer");
    const calendarToggle = document.getElementById("calendarToggle");



    function getQueryParams() {
        const params = new URLSearchParams(window.location.search);
        const ids = ['1', '2', '3', '4', '5', '6']
        return ids.includes(params.get('id')) ? params.get('id') : 0;
    }
    const id = getQueryParams();



    const currentMovie = upcomingMovies.details[id];



    mainImage.src = `../../assets/images/movies/${currentMovie.mainimage}`;
    mainImage.className = 'z-40 rounded-[30px] w-[1364px] h-[613px] object-cover'
    posterImage.src = `../../assets/images/movies/${currentMovie.image}`;
    movieName.innerText = currentMovie.name;
    movieLikes.innerText = currentMovie.likes;
    releaseDate.innerText = currentMovie.releasedate;
    movieLanguage.innerText = currentMovie.language;
    movieGenre.innerText = currentMovie.genre;
    movieOneliner.innerText = currentMovie.oneline;


    let interestedMovies = [];

    const toggleInterest = (movieId) => {
      if (interestedMovies.includes(movieId)) {
        interestedMovies = interestedMovies.filter((id) => id !== movieId);
      } else {
        interestedMovies.push(movieId);
      }
      updateInterestedButton(movieId);
    };

    const button = document.querySelector('.interest-button');
    button.addEventListener('click', () => toggleInterest(id));
    const updateInterestedButton = (movieId) => {
   
      if (interestedMovies.includes(movieId)) {
        button.textContent = 'Interested';
        button.classList.add('bg-blue-500');
        button.classList.remove('bg-[#666666]');
      } else {
        button.textContent = "I'm Interested";
        button.classList.remove('bg-blue-500');
        button.classList.add('bg-[#666666]');
      }
    };

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




    movies.UpcomingMovies.forEach((movie, index) => {
        const link = document.createElement('a');
        link.href = `upcomingMovie.html?id=${index}`;
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



});
