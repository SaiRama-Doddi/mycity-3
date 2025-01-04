import { songs } from "../data/js/songData.js";

let audioPlayer = document.getElementById('audioPlayer');
let playPauseBtn = document.getElementById('playPauseBtn');
let prevBtn = document.getElementById('prevBtn');
let nextBtn = document.getElementById('nextBtn');
let loopBtn = document.getElementById('loopBtn');
let shuffleBtn = document.getElementById('shuffleBtn');
let seekBg = document.getElementById('seekBg');
let seekBar = document.getElementById('seekBar');
let currentTimeEl = document.getElementById('currentTime');
let totalTimeEl = document.getElementById('totalTime');




let currentTrack = songs[0];
let isPlaying = false;
let isLooping = false;
let isShuffling = false;
let playedTracks = [];


function formatTime(timeInSeconds) {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = Math.floor(timeInSeconds % 60);
    return `${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
}

function loadTrack(track) {
    audioPlayer.src = track.path;
    
    audioPlayer.load();
}

function play() {
    audioPlayer.play();
    isPlaying = true;
    playPauseBtn.src = 'assets/icons/pause2.svg'
}

function pause() {
    audioPlayer.pause();
    isPlaying = false;
    playPauseBtn.src = "assets/icons/play1.svg";
}

function togglePlayPause() {
    if (isPlaying) {
        pause();
    } else {
        play();
    }
}

export const playWithId = async (id) => {
    currentTrack = songs[id];
    audioPlayer.play();
    isPlaying = true;
};

function playNextTrack() {
    if (isShuffling) {
        playShuffledTrack();
    } else {
        let nextTrackIndex = (currentTrack.id + 1) % songs.length;
        currentTrack = songs[nextTrackIndex];
        loadTrack(currentTrack);
        play();
    }
}

function playPrevTrack() {
    let prevTrackIndex = currentTrack.id > 0 ? currentTrack.id - 1 : songs.length - 1;
    currentTrack = songs[prevTrackIndex];
    loadTrack(currentTrack);
    play();
}

function toggleLoop() {
    isLooping = !isLooping;
    audioPlayer.loop = isLooping;
    loopBtn.textContent = isLooping ? "Looping" : "Loop";
}

function toggleShuffle() {
    isShuffling = !isShuffling;
    playedTracks = [];
    shuffleBtn.textContent = isShuffling ? "Shuffling" : "Shuffle";
}

document.getElementById('trackImage').src = currentTrack.image;
document.getElementById('trackTitle').textContent = currentTrack.title;
document.getElementById('trackArtist').textContent = currentTrack.artist;

function playShuffledTrack() {
    let availableTracks = songs.filter(song => !playedTracks.includes(song.id));
    
    if (availableTracks.length === 0) {
        playedTracks = [];
        availableTracks = songs;
    }
    
    const randomIndex = Math.floor(Math.random() * availableTracks.length);
    currentTrack = availableTracks[randomIndex];
    playedTracks.push(currentTrack.id);
    loadTrack(currentTrack);
    play();
}

function seekSong(event) {
    const seekBgWidth = seekBg.offsetWidth;
    const offsetX = event.offsetX;
    const duration = audioPlayer.duration;

    audioPlayer.currentTime = (offsetX / seekBgWidth) * duration;
}

function updateSeekBar() {
    const currentTime = audioPlayer.currentTime;
    const duration = audioPlayer.duration;
    const progress = (currentTime / duration) * 100;

    seekBar.style.width = `${progress}%`;
    currentTimeEl.textContent = formatTime(currentTime);
    totalTimeEl.textContent = formatTime(duration);
}

audioPlayer.ontimeupdate = updateSeekBar;

audioPlayer.onended = () => {
    if (isLooping) {
        play();
    } else {
        playNextTrack();
    }
};


playPauseBtn.addEventListener('click', togglePlayPause);
prevBtn.addEventListener('click', playPrevTrack);
nextBtn.addEventListener('click', playNextTrack);
loopBtn.addEventListener('click', toggleLoop);
shuffleBtn.addEventListener('click', toggleShuffle);
seekBg.addEventListener('click', seekSong);


loadTrack(currentTrack);
