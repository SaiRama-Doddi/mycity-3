

import { songs  as mySongs} from './../data/js/songData.js';
import { playWithId } from './player.js';

const trending = {
    image: "assets/songs/Die-With-A-Smile.jpg",
    name: "Trending Song",
    artist: "Artist Name"
};
const artists = [
    {
        images: [{ url: "assets/images/music/wizkid.png" }],
        name: "Artist A",
        followers: { total: 5000 }
    },
    {
        images: [{ url: "assets/images/music/omah-lay.png" }],
        name: "Artist B",
        followers: { total: 3000 }
    },
   
];
let track = mySongs[0]

let time = {
    currentTime: { formatted: '0:00' },
    totalTime: { formatted: '0:00' }
};

const playlists = [
    {
        images: [{ url: "assets/images/music/wizkid.png" }],
        name: "Playlist 1",
        followers: { total: 1500 }
    },
    {
        images: [{ url: "assets/images/music/omah-lay.png" }],
        name: "Playlist 2",
        followers: { total: 2500 }
    },
  
];

const songs = [
    {
        album: {
            images: [{ url: "assets/images/music/olamide.png" }],
            artists: [{ name: "Song Artist 1" }]
        },
        name: "Song 1"
    },
    {
        album: {
            images: [{ url: "assets/images/music/burnaboy.png" }],
            artists: [{ name: "Song Artist 2" }]
        },
        name: "Song 2"
    },
    
];

const episodes = [
    {
        images: [{ url: "assets/images/music/olamide.png" }],
        name: "Episode 1"
    },
    {
        images: [{ url: "assets/images/music/burnaboy.png" }],
        name: "Episode 2"
    },
    
];

const addIcon = document.getElementById('add-icon');
const plusIcon = document.querySelector('.plus-icon');
addIcon.addEventListener('click', toggleAddToList)
let isAdded = false;
function toggleAddToList(){
    if (isAdded){
        plusIcon.src='assets/icons/plus2.svg'
        isAdded = false;
    } else{
        plusIcon.src = 'assets/icons/checkblack.svg'
        isAdded = true
    }
}


let toggleNewRelease = false;
let togglePlayList = false;

function formatDate(inputDate) {
    const date = new Date(inputDate);
    const options = { year: 'numeric', month: 'short', day: '2-digit' };
    return date.toLocaleDateString('en-US', options);
}

function formatNumber(num) {
    if (num >= 1e9) return (num / 1e9).toFixed(1).replace(/\.0$/, '') + 'B';
    if (num >= 1e6) return (num / 1e6).toFixed(1).replace(/\.0$/, '') + 'M';
    if (num >= 1e3) return (num / 1e3).toFixed(1).replace(/\.0$/, '') + 'K';
    return num.toString();
}


function handlePlaylist () {
    togglePlayList = !togglePlayList;
    const playlistElement = document.getElementById('playlist');
    if (togglePlayList) {
        playlistElement.classList.remove('hidden');
        populateSongList();
    } else {
        playlistElement.classList.add('hidden');
    }
}


document.getElementById('trending-image').src = trending.image;
document.getElementById('trending-title').textContent = trending.name;
document.getElementById('trending-artist').textContent = trending.artist;


const handleNewRelease = ()=>{
    toggleNewRelease = !toggleNewRelease;
    const newRelease = document.getElementById('newReleaseContainer');
    if(toggleNewRelease){
        newRelease.classList.remove('hidden');
    }else{
        newRelease.classList.add('hidden')
    }
}



const seeAll = document.querySelectorAll('.see-all')
seeAll[0].addEventListener("click", handleNewRelease)
const closenewList = document.getElementById('closenewRelease')
closenewList.addEventListener('click', handleNewRelease)



function renderTopArtists() {
    const topArtistsList = document.getElementById('topArtistsList');
    topArtistsList.innerHTML = '';

    artists.slice(0, 6).forEach((artist, index) => {
        const artistItem = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                <img src="${artist.images[0].url}" alt="" class="max-w-[145px] rounded-[30px]" />
                <p class="mt-3 font-bold text-[16px]">${artist.name}</p>
                <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${formatNumber(artist.followers.total)} Followers</p>
            </div>
        `;
        topArtistsList.insertAdjacentHTML('beforeend', artistItem);
    });
}

// Render Top Playlists
function renderTopPlaylists() {
    const topPlaylistsList = document.getElementById('topPlaylistsList');
    topPlaylistsList.innerHTML = '';

    playlists.slice(0, 6).forEach((playlist, index) => {
        const playlistItem = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                <img src="${playlist.images[0].url}" alt="" class="max-w-[145px] rounded-[30px]" />
                <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${playlist.name}</p>
                <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${formatNumber(playlist.followers.total)} Followers</p>
            </div>
        `;
        topPlaylistsList.insertAdjacentHTML('beforeend', playlistItem);
    });
}

// Render Top Tracks
function renderTopTracks() {
    const topTracksList = document.getElementById('topTracksList');
    topTracksList.innerHTML = '';

    songs.slice(0, 6).forEach((song, index) => {
        const songItem = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                <img src="${song.album.images[0].url}" alt="" class="max-w-[145px] rounded-[30px]" />
                <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${song.name}</p>
                <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${song.album.artists[0].name}</p>
            </div>
        `;
        topTracksList.insertAdjacentHTML('beforeend', songItem);
    });
}

// Render Top Episodes
function renderTopEpisodes() {
    const topEpisodesList = document.getElementById('topEpisodesList');
    topEpisodesList.innerHTML = '';

    episodes.slice(0, 6).forEach((episode, index) => {
        const episodeItem = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                <img src="${episode.images[0].url}" alt="" class="max-w-[145px] rounded-[30px]" />
                <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${episode.name}</p>
            </div>
        `;
        topEpisodesList.insertAdjacentHTML('beforeend', episodeItem);
    });
}

function updateUI() {
    // document.getElementById('trackImage').src = track.image;
    // document.getElementById('trackTitle').textContent = track.title;
    // document.getElementById('trackArtist').textContent = track.artist;
    document.getElementById('currentTime').textContent = time.currentTime.formatted;
    document.getElementById('totalTime').textContent = time.totalTime.formatted;


    
}



function populateSongList() {
    const songListElement = document.getElementById('songList');
    songListElement.innerHTML = ''; // Clear existing songs
    mySongs.forEach((song, index) => {
        const songItem = document.createElement('div');
        songItem.className = 'flex gap-[1rem] items-center px-2 py-3 cursor-pointer';
        songItem.onclick = () => playWithId(song.id);
        songItem.innerHTML = `
            <p>${index + 1}</p>
            <div class="flex gap-3">
                <img src="${song.image}" alt="" class="w-16" />
                <div class="flex flex-col gap-2">
                    <p>${song.title}</p>
                    <p>${song.artist}</p>
                </div>
            </div>
        `;
        songListElement.appendChild(songItem);
    });
}

const closeplayList = document.getElementById('closebutton');
const openPlayList = document.getElementById('openPlaylist')

closeplayList.addEventListener("click", handlePlaylist);
openPlayList.addEventListener("click", handlePlaylist);




const lyricsButton = document.getElementById('lyricsButton');
const albumContent = document.querySelector('.album-content')
const lyricsContent = document.querySelector('.lyric-content');
// const noLyrics = document.getElementById('noLyrics');
const lyricName = document.querySelector('.lyrics-name')
const songName = document.querySelector('.song-name')
let showLyrics = false;
const uparrow = document.querySelector('.uparrow')

function toggleLyrics() {
    console.log("Toggle Lyrics Button Clicked");
    uparrow.classList.toggle('rotate-180');
    lyricName.classList.toggle('hidden');
    songName.classList.toggle('hideText');

    
    albumContent.classList.toggle('hidden')
    lyricsContent.classList.toggle('hidden');
}



lyricsButton.addEventListener('click', toggleLyrics);



// Initialize rendering
function initialize() {
    // renderNewReleases();
    renderTopArtists();
    renderTopPlaylists();
    renderTopTracks();
    renderTopEpisodes(); 
    updateUI();
    // renderNewReleases();
}

initialize();


