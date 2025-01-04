const url =
  "https://spotify23.p.rapidapi.com/search/?q=new%20telugu%20songs&type=albums&offset=0&limit=10&numberOfTopResults=5";
const options = {
  method: "GET",
  headers: {
    "x-rapidapi-key": "66758d5d00msha5d7d70855a56b0p1ef5eejsn69f5009871be",
    "x-rapidapi-host": "spotify23.p.rapidapi.com",
  },
};

async function fetchAndDisplayNewReleases() {
  try {
    const response = await fetch(url, options);
    const result = await response.json(); // Parse as JSON
    const albums = result.albums.items;

    const newReleasesList = document.getElementById("newReleasesList");
    newReleasesList.innerHTML = ""; // Clear existing content

    albums.forEach((albumData, index) => {
      const album = albumData.data; // Access the album data

      // Create a container div for each album entry
      const albumItem = document.createElement("div");
      albumItem.classList.add("flex", "justify-between", "w-full", "px-1");

      // Album index
      const albumIndex = document.createElement("p");
      albumIndex.textContent = index + 1;

      // Album title
      // Create the <p> element to act as the container
      const albumTitleContainer = document.createElement("p");
      albumTitleContainer.classList.add("w-[400px]");

      // Create the <a> element with the album link
      const albumLink = document.createElement("a");
      albumLink.href = album.uri;
      albumLink.target = "_blank"; // Opens link in a new tab
      albumLink.textContent = album.name; // Set album title text

      // Append the <a> to the <p> container
      albumTitleContainer.appendChild(albumLink);

      // Add the <p> container to albumItem
      albumItem.appendChild(albumTitleContainer);

      // Artist name
      // Create the <p> element to act as the container for the artist link
      const albumArtistContainer = document.createElement("p");
      albumArtistContainer.classList.add("w-[100px]", "text-center");

      // Create the <a> element with the artist's link
      const artistLink = document.createElement("a");
      artistLink.href = album.artists.items[0].uri;
      artistLink.target = "_blank"; // Opens link in a new tab
      artistLink.textContent = album.artists.items[0].profile.name; // Set artist name text

      // Append the <a> to the <p> container
      albumArtistContainer.appendChild(artistLink);

      // Add the <p> container to albumItem
      albumItem.appendChild(albumArtistContainer);

      // Release date
      const dateAdded = document.createElement("p");
      dateAdded.classList.add("w-[100px]", "text-center");
      dateAdded.textContent = album.date.year || "N/A"; // Adjust if necessary

      // Append all elements to album item
      albumItem.appendChild(albumIndex);
      albumItem.appendChild(albumTitleContainer);
      albumItem.appendChild(albumArtistContainer);
      albumItem.appendChild(dateAdded);

      // Append album item to new releases list
      newReleasesList.appendChild(albumItem);
    });
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

// Trigger the function to run when the window loads
window.onload = fetchAndDisplayNewReleases;
