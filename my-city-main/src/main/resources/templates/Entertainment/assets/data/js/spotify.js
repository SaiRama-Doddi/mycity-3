
const authEndpoint = 'https://accounts.spotify.com/authorize?';
const clientId = '<CLIENT-ID>';
const redirectUri = 'http://127.0.0.1:5501/src/musics.html';
const scopes = ['user-library-read', 'playlist-read-private']
let token = '';
const topPlaylist = [
    'playlists/37i9dQZEVXbMDoHDwVN2tF?si=74Xfbd4oQeW2O7lszZk76Q',
    'playlists/5ABHKGoOzxkaa28ttQV9sE?si=1U-0jWnHR4GO-HdVNKKEJA',
    'playlists/1wGjJFYGu12FbrPtiTkSi9?si=h_-dlDWQSE2zJ_Vwif8dlg',
    'playlists/3MsR9p5xhhfmQYNp2XWJ2Z?si=WpoR9jbKTnew8Up2VcfQYw',
    'playlists/4SaPvMAaUjkbNTky16KjfD?si=KNhmEWImTP-aLRtgHl5VMw',
    'playlists/2YRe7HRKNRvXdJBp9nXFza?si=MEV3f3cIRPm8V2WCHnza9w',
];

const topArtists = [
    'artists/1Xyo4u8uXC1ZmMpatF05PJ?si=a32FUm_vTyyce9ue1otGtQ',
    'artists/6qqNVTkY8uBg9cP3Jd7DAH?si=2ylOUyplQEWBGNaregRRCw',
    'artists/53XhwfbYqKCa1cC15pYq2q?si=0mAwmhz7S6qIvaKKlQI5PA',
    'artists/7vk5e3vY1uw9plTHJAMwjN?si=jND0DvD9T0Smql8jimnCbQ',
    'artists/64KEffDW9EtZ1y2vBYgq8T?si=rTb9YaQKQlW0nrb3wftaSA',
    'artists/6eUKZXaKkcviH0Ku9w2n3V?si=LuMSoKY7QBCun3jY_cLEZw',
];

const topTracks = [
    'tracks/7qiZfU4dY1lWllzX7mPBI3?si=d32da694d44a4986',
    'tracks/0VjIjW4GlUZAMYd2vXMi3b?si=3ae5cecea1094d5d',
    'tracks/7qEHsqek33rTcFNT9PFqLf?si=217439e2205545cd',
    'tracks/4Dvkj6JhhA12EX05fT7y2e?si=6de23bc41597482c',
    'tracks/5aAx2yezTd8zXrkmtKl66Z?si=4e9fde127a594f22',
    'tracks/5XeFesFbtLpXzIVDNQP22n?si=f67d3c873b3c49f8',
];

const topEpisodes = [
    'episodes/5rIjNxwPxdCcgr9bSt0Pby?si=2b5c397a70e248cb',
    'episodes/3t8iUSntRaSqsNzAQOX72I?si=e43edeff144b4fb4',
    'episodes/3TxjF2mZy9S9I9GL5eZ8sq?si=55a62dbb90c2482b',
    'episodes/1k7ofXS8KOle5Gj1Ov40Cj?si=ae9d10c9a92b4b91',
    'episodes/2aokNhH1yC1H3UTrAHHkSr?si=9de219b1e5dd4134',
    'episodes/63AJ48vqQCdobFNtuY7tyw?si=a35ceff9bcbd4954',
];


export const loginEndpoint = `${authEndpoint}client_id=${clientId}&redirect_uri=${redirectUri}&scope=${scopes.join("%20")}&response_type=token&show_dialog=true`;

const apiClient = axios.create({
    baseURL: "https://api.spotify.com/v1"
});



export const setClientToken = (token) => {
    apiClient.interceptors.request.use(async function (config) {
        config.headers.Authorization = "Bearer " + token
        return config
    })
}



if (window.location.href === "http://127.0.0.1:5501/src/spotifylogin.html") {
    window.localStorage.removeItem('token')
    const loginbtn = document.querySelector('.login-btn');
    loginbtn.href = loginEndpoint;
}



function setToken(_token) {
    token = _token;
}


let playLists;
let episodes;
let artists;
let newRelease;
let songs;
function formatDate(inputDate) {
    const date = new Date(inputDate);
    const options = { year: 'numeric', month: 'short', day: '2-digit' };
    return date.toLocaleDateString('en-US', options);
}


export const getNewReleases = async function () {
    apiClient.get('browse/new-releases?limit=50').then(response => {
        newRelease = response.data.albums.items

        const newReleasesList = document.getElementById('newReleasesList');
        newReleasesList.innerHTML = '';

        newRelease.forEach((release, index) => {
            const releaseItem = document.createElement('div') 
            releaseItem.innerHTML = `
            <div class="flex items-center cursor-pointer hover:bg-zinc-600 bg-opacity-55 py-2 px-1 rounded-md gap-[1rem] justify-between">
                <div class="flex gap-6 items-center w-[400px]">
                    <p>${index + 1}</p>
                    <span class="w-[400px] text-nowrap text-start overflow-x-scroll scrollbarhide flex items-center gap-3">
                        <img src="${release.images[0].url}" alt="${release.name}" class="w-8" />
                        <div>
                            <p class="text-[15px] font-normal">${release.name}</p>
                            <p class="text-[12px] text-[#c1b4b4]">${release.artists[0].name}</p>
                        </div>
                    </span>
                </div>
                <p class="capitalize w-[100px] text-center">${release.album_type}</p>
                <p class="w-[100px] text-center">${release.total_tracks}</p>
                <p class="w-[100px] text-start text-[15px]">${formatDate(release.release_date)}</p>
            </div>
        `;
            newReleasesList.appendChild(releaseItem);
            const releaseContainer = document.getElementById('releaseContainer');

    
    const releasesToShow = newRelease.slice(0, 6);

   
    releasesToShow.forEach((release, index) => {
        
        const releaseDiv = document.createElement('div');
        releaseDiv.className = 'flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]';

        const img = document.createElement('img');
        img.src = release.images[0].url;
        img.alt = `${release.artists[0].name} image`;
        img.className = 'max-w-[145px] rounded-[30px]';

        // Artist name
        const artistName = document.createElement('p');
        artistName.className = 'mt-3 font-bold text-[16px]';
        artistName.textContent = release.artists[0].name;

        // Release name
        const releaseName = document.createElement('p');
        releaseName.className = 'font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center';
        releaseName.textContent = release.name;

    
        releaseDiv.appendChild(img);
        releaseDiv.appendChild(artistName);
        releaseDiv.appendChild(releaseName);

     
        releaseContainer.appendChild(releaseDiv);
    });
        });

    })
}

const getPlaylists = async () => {
    const playlistPromises = topPlaylist.map((list) => apiClient.get(list));
    const playlistResponses = await Promise.all(playlistPromises);
    const fetchedPlaylists = playlistResponses.map(response => response.data);
    playLists = fetchedPlaylists

};

const getArtists = async () => {
    const artistPromises = topArtists.map((artist) => apiClient.get(artist));
    const artistResponses = await Promise.all(artistPromises);
    const fetchedArtists = artistResponses.map(response => response.data);
    artists = fetchedArtists;
};

const getSongs = async () => {
    const songPromises = topTracks.map((song) => apiClient.get(song));
    const songResponses = await Promise.all(songPromises);
    const fetchedSongs = songResponses.map(response => response.data);
    songs = fetchedSongs
};

const getEpisodes = async () => {
    const episodePromises = topEpisodes.map((episode) => apiClient.get(episode));
    const episodeResponses = await Promise.all(episodePromises);
    const fetchedEpisodes = episodeResponses.map(response => response.data);
    episodes = fetchedEpisodes

};

function getTokenFromURL() {
    const hash = window.location.hash;
    if (hash) {
        const _token = hash.split('&')[0].split('=')[1];
        window.localStorage.setItem('token', _token);
        window.location.hash = '';
        setToken(_token);
        setClientToken(_token);
    } else {
        setToken(window.localStorage.getItem('token'));
        setClientToken(token);
    }
}



window.addEventListener('DOMContentLoaded', () => {
    getTokenFromURL();
    getArtists();
    getEpisodes();
    getNewReleases();
    getPlaylists();
    getSongs();


})



export default apiClient;