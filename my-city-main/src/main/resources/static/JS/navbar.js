
function searchExpand(event) {
    const input = document.querySelector(".searchInput");
    input.classList.toggle("search-expand");

    document.addEventListener("click", handleClickOutside);
    event.stopPropagation(); 
}


function handleClickOutside(e) {
    const input = document.querySelector(".searchInput");
    const navbar = document.querySelector(".navbar"); 

    if (input && !input.contains(e.target) && !navbar.contains(e.target)) {
        input.classList.remove("search-expand");
        document.removeEventListener("click", handleClickOutside);
    }
}


function highlightActiveNavLink() {
    const pathname = window.location.pathname;
    const navLinks = {
        home: document.getElementById("home-link"),
        movies: document.getElementById("movies-link"),
        musics: document.getElementById("musics-link"),
        shortfilms: document.getElementById("shortfilms-link"),
        filmnews: document.getElementById("filmnews-link"),
    };

    if (pathname === "../src/home.html") {
        navLinks.home.classList.add('bg-orange', 'hover:text-white');
    } else if (pathname.startsWith("../src/movies.html")) {
        navLinks.movies.classList.add('bg-orange', 'hover:text-white');
    }else if (pathname.startsWith("../src/movies/intheatre/")) {
        navLinks.movies.classList.add('bg-orange', 'hover:text-white');
    } else if (pathname.startsWith("../src/movies/upcoming/")){
        navLinks.movies.classList.add('bg-orange', 'hover:text-white');
    }else if (pathname === "../src/musics.html") {
        navLinks.musics.classList.add('bg-orange', 'hover:text-white');
    } else if (pathname.startsWith("../src/shortfilm.html")) {
        navLinks.shortfilms.classList.add('bg-orange', 'hover:text-white');
    } else if (pathname.startsWith("../src/shortfilmDetails.html")) {
        navLinks.shortfilms.classList.add('bg-orange', 'hover:text-white');
    } else if (pathname.startsWith("../src/fil.html")) {
        navLinks.filmnews.classList.add('bg-orange', 'hover:text-white');
    }else if (pathname.startsWith("../src/newsDetails.html")) {
        navLinks.filmnews.classList.add('bg-orange', 'hover:text-white');
    }
}


window.onload = highlightActiveNavLink;
