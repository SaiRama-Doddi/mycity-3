document.getElementById("searchbar").addEventListener("keypress", (e) => {
  // Check if the key pressed is 'Enter'
  if (e.key === "Enter") {
    e.preventDefault();
    const searchInput = document.getElementById("searchbar").value;

    if (searchInput) {
      const url = `https://spotify23.p.rapidapi.com/search/?type=multi&offset=0&limit=10&numberOfTopResults=5&q=${encodeURIComponent(
        searchInput
      )}`;
      const options = {
        method: "GET",
        headers: {
          "x-rapidapi-key":
            "66758d5d00msha5d7d70855a56b0p1ef5eejsn69f5009871be",
          "x-rapidapi-host": "spotify23.p.rapidapi.com",
        },
      };

      fetch(url, options)
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Network response was not ok " + response.statusText
            );
          }
          return response.json();
        })
        .then((data) => {
          console.log(data);
          populateResults(data); // Call your function to populate results
        })
        .catch((error) => console.error("Error:", error));
    } else {
      console.error("Search input is empty.");
    }
  }
});

// Function to populate search results
function populateResults(data) {
  const tracksResults = document.getElementById("search-results-tracks"); // This is for the tracks results"
  tracksResults.innerHTML = ""; // Clear previous results

  const artistsResults = document.getElementById("search-results-artists"); // This is for the artists results"
  artistsResults.innerHTML = ""; // Clear previous results

  const albumsResults = document.getElementById("search-results-albums"); // This is for the albums results"
  albumsResults.innerHTML = ""; // Clear previous results

  const playlistsResults = document.getElementById("search-results-playlists"); // This is for the playlists results"
  playlistsResults.innerHTML = ""; // Clear previous results

  const podcastsResults = document.getElementById("search-results-podcasts"); // This is for the podcasts results"
  podcastsResults.innerHTML = ""; // Clear previous results

  const episodesResults = document.getElementById("search-results-episodes"); // This is for the episodes results"
  episodesResults.innerHTML = ""; // Clear previous results

  const trackResultsContainer = document.createElement("div");
  trackResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionTrack = document.createElement("div");
  titleSectionTrack.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const trackHeading = document.createElement("h1");
  trackHeading.classList.add("heading");
  trackHeading.textContent = "Top Tracks";
  titleSectionTrack.appendChild(trackHeading);
  tracksResults.appendChild(titleSectionTrack);

  const artistResultsContainer = document.createElement("div");
  artistResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionArtist = document.createElement("div");
  titleSectionArtist.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const artistHeading = document.createElement("h1");
  artistHeading.classList.add("heading");
  artistHeading.textContent = "Top Artists";
  titleSectionArtist.appendChild(artistHeading);
  artistsResults.appendChild(titleSectionArtist);

  const albumResultsContainer = document.createElement("div");
  albumResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionAlbum = document.createElement("div");
  titleSectionAlbum.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const albumHeading = document.createElement("h1");
  albumHeading.classList.add("heading");
  albumHeading.textContent = "Top Albums";
  titleSectionAlbum.appendChild(albumHeading);
  albumsResults.appendChild(titleSectionAlbum);

  const playlistResultsContainer = document.createElement("div");
  playlistResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionPlaylist = document.createElement("div");
  titleSectionPlaylist.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const playlistHeading = document.createElement("h1");
  playlistHeading.classList.add("heading");
  playlistHeading.textContent = "Top Playlists";
  titleSectionPlaylist.appendChild(playlistHeading);
  playlistsResults.appendChild(titleSectionPlaylist);

  const podcastResultsContainer = document.createElement("div");
  podcastResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionPodcast = document.createElement("div");
  titleSectionPodcast.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const podcastHeading = document.createElement("h1");
  podcastHeading.classList.add("heading");
  podcastHeading.textContent = "Top Podcasts";
  titleSectionPodcast.appendChild(podcastHeading);
  podcastsResults.appendChild(titleSectionPodcast);

  const episodeResultsContainer = document.createElement("div");
  episodeResultsContainer.classList.add(
    "search_results",
    "flex",
    "overflow-x-scroll",
    "w-full",
    "gap-[3rem]",
    "scrollbarhide",
    "px-[10px]"
  );

  const titleSectionEpisode = document.createElement("div");
  titleSectionEpisode.classList.add(
    "title",
    "flex",
    "justify-between",
    "mb-[1rem]"
  );

  // Add trackHeading element to the title section
  const episodeHeading = document.createElement("h1");
  episodeHeading.classList.add("heading");
  episodeHeading.textContent = "Top Episodes";
  titleSectionEpisode.appendChild(episodeHeading);
  episodesResults.appendChild(titleSectionEpisode);

  // Truncate text function
  const truncateText = (text, maxLength) => {
    if (text.length > maxLength) {
      return text.slice(0, maxLength) + "...";
    }
    return text;
  };

  //This is for populations of tracks
  if (data.tracks && data.tracks.items.length > 0) {
    data.tracks.items.forEach((track, index) => {
      // Include index here
      // Create a card for each track
      const trackCard = document.createElement("div");
      trackCard.classList.add("container");
      trackCard.style.maxWidth = "540px";

      // HTML structure for each track card
      trackCard.innerHTML = `
        <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                  <img src="${track.data.albumOfTrack.coverArt.sources[0].url}" alt="${track.data.name}" class="max-w-[145px] rounded-[30px]" />
                  <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${track.data.artists.items[0].profile.name}</p>
                  <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${track.data.albumOfTrack.name}</p>
                  <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                        <p class="text-[20px] font-semibold"><a href="${track.data.uri}">Play Now</a></p>
                        <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                    </div>
              </div>
          `;

      // Append each track card to the results container
      trackResultsContainer.appendChild(trackCard);
    });

    // Add the results container to the main container
    tracksResults.appendChild(trackResultsContainer);
  } else {
    // Display a message if no results are found
    tracksResults.innerHTML = "<p>No tracks found.</p>";
  }

  //This is for the population of the Albums
  if (data.albums && data.albums.items.length > 0) {
    data.albums.items.forEach((album, index) => {
      // Include index here
      // Create a card for each track
      const albumCard = document.createElement("div");
      albumCard.classList.add("container");
      albumCard.style.maxWidth = "540px";

      // HTML structure for each track card
      albumCard.innerHTML = `
        <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                  <img src="${album.data.coverArt.sources[0].url}" alt="${album.data.name}" class="max-w-[145px] rounded-[30px]" />
                  <a href="${album.data.artists.items[0].uri}"><p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${album.data.artists.items[0].profile.name}</p></a>
                  <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${album.data.name}</p>
                  <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                        <p class="text-[20px] font-semibold"><a href="${album.data.uri}">Play Now</a></p>
                        <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                    </div>
              </div>
          `;

      // Append each track card to the results container
      albumResultsContainer.appendChild(albumCard);
    });

    // Add the results container to the main container
    albumsResults.appendChild(albumResultsContainer);
  } else {
    // Display a message if no results are found
    albumsResults.innerHTML = "<p>No tracks found.</p>";
  }

  //This is for populating the artists
  if (data.artists && data.artists.items.length > 0) {
    data.artists.items.forEach((artist, index) => {
      // Include index here
      // Create a card for each track
      const artistCard = document.createElement("div");
      artistCard.classList.add("container");
      artistCard.style.maxWidth = "540px";

      // HTML structure for each track card
      artistCard.innerHTML = `
        <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                  <img src="${artist.data.visuals.avatarImage.sources[0].url}" alt="${artist.data.profile.name}" class="max-w-[145px] rounded-[30px]" />
                  <a href="${artist.data.uri}"><p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${artist.data.profile.name}</p></a>
                  <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                        <p class="text-[20px] font-semibold"><a href="${artist.data.uri}">Open</a></p>
                        <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                    </div>
              </div>
          `;

      // Append each track card to the results container
      artistResultsContainer.appendChild(artistCard);
    });

    // Add the results container to the main container
    artistsResults.appendChild(artistResultsContainer);
  } else {
    // Display a message if no results are found
    artistsResults.innerHTML = "<p>No tracks found.</p>";
  }

  //This is for populating the playlists
  if (data.playlists && data.playlists.items.length > 0) {
    data.playlists.items.forEach((playlist, index) => {
      // Include index here
      // Create a card for each track
      const playlistCard = document.createElement("div");
      playlistCard.classList.add("container");
      playlistCard.style.maxWidth = "540px";

      // HTML structure for each track card
      playlistCard.innerHTML = `
          <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                    <img src="${playlist.data.images.items[0].sources[0].url}" alt="${playlist.data.name}" class="max-w-[145px] rounded-[30px]" />
                    <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${playlist.data.owner.name}</p>
                                      <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${playlist.data.name}</p>
                    <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                          <p class="text-[20px] font-semibold"><a href="${playlist.data.uri}">Open</a></p>
                          <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                      </div>
                </div>
            `;

      // Append each track card to the results container
      playlistResultsContainer.appendChild(playlistCard);
    });

    // Add the results container to the main container
    playlistsResults.appendChild(playlistResultsContainer);
  } else {
    // Display a message if no results are found
    playlistsResults.innerHTML = "<p>No tracks found.</p>";
  }

  //This is for populating the podcasts
  if (data.podcasts && data.podcasts.items.length > 0) {
    data.podcasts.items.forEach((podcast, index) => {
      // Include index here
      // Create a card for each track
      const podcastCard = document.createElement("div");
      podcastCard.classList.add("container");
      podcastCard.style.maxWidth = "540px";

      // HTML structure for each track card
      podcastCard.innerHTML = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                      <img src="${podcast.data.coverArt.sources[0].url}" alt="${podcast.data.type}" class="max-w-[145px] rounded-[30px]" />
                      <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${podcast.data.name}</p>
                      <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${podcast.data.publisher.name}</p>
                      <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                            <p class="text-[20px] font-semibold"><a href="${podcast.data.uri}">Open</a></p>
                            <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                        </div>
                  </div>
              `;

      // Append each track card to the results container
      podcastResultsContainer.appendChild(podcastCard);
    });

    // Add the results container to the main container
    podcastsResults.appendChild(podcastResultsContainer);
  } else {
    // Display a message if no results are found
    podcastsResults.innerHTML = "<p>No tracks found.</p>";
  }

  //This is for populating the episodes
  if (data.episodes && data.episodes.items.length > 0) {
    data.episodes.items.forEach((episode, index) => {
      // Include index here
      // Create a card for each track
      const episodeCard = document.createElement("div");
      episodeCard.classList.add("container");
      episodeCard.style.maxWidth = "540px";

      const isoDate = `${episode.data.releaseDate.isoString}`;
      const date = new Date(isoDate);

      // Format the date
      const formattedDate = date.toLocaleString("en-IN", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: false,
      });

      console.log("Formatted Date:", formattedDate);

      const totalMilliseconds = `${episode.data.duration.totalMilliseconds}`;

      const hours = Math.floor(totalMilliseconds / (1000 * 60 * 60)) % 24;
      const minutes = Math.floor(totalMilliseconds / (1000 * 60)) % 60;
      const seconds = Math.floor(totalMilliseconds / 1000) % 60;

      const formattedTime = `${String(hours).padStart(2, "0")}:${String(
        minutes
      ).padStart(2, "0")}:${String(seconds).padStart(2, "0")}`;

      console.log("Formatted Time:", formattedTime);

      // HTML structure for each track card
      episodeCard.innerHTML = `
            <div class="flex flex-col items-center gap-1 px-2 py-1 rounded-lg hover:cursor-pointer hover:bg-[#66666629]" key="${index}">
                      <img src="${episode.data.coverArt.sources[0].url}" alt="${
        episode.data.name
      }" class="max-w-[145px] rounded-[30px]" />
                      <p class="mt-3 font-bold text-[16px] text-center text-nowrap overflow-x-scroll scrollbarhide w-[140px]">${truncateText(
                        `${episode.data.name}`,
                        15
                      )}</p>
                      <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${formattedDate}</p>
                      <p class="font-normal text-[13px] text-nowrap overflow-x-scroll scrollbarhide w-[140px] text-center">${formattedTime}</p>
                      <div class="play-now bg-[#F37022] flex gap-4 px-[36px] py-[15px] rounded-[18px] cursor-pointer">
                            <p class="text-[20px] font-semibold"><a href="${
                              episode.data.uri
                            }">Open</a></p>
                            <img src="../src/assets/icons/play1.svg" alt="play icon" width="20">
                        </div>
                  </div>
              `;

      // Append each track card to the results container
      episodeResultsContainer.appendChild(episodeCard);
    });

    // Add the results container to the main container
    episodesResults.appendChild(episodeResultsContainer);
  } else {
    // Display a message if no results are found
    episodesResults.innerHTML = "<p>No tracks found.</p>";
  }
}

// This is for the New release block
