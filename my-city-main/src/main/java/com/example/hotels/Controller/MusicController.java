package com.example.hotels.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotels.Model.Albums;
import com.example.hotels.Model.Artist;
import com.example.hotels.Model.Tracks;
import com.example.hotels.Service.AlbumService;
import com.example.hotels.Service.ArtistService;
import com.example.hotels.Service.TrackService;


@Controller
public class MusicController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TrackService trackService;

    // Artist controller
    @GetMapping("/artists")
    public String artistsList(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        return "artists/artists";
    }

    @GetMapping("/createArtist")
    public String createOrEditArtist(@RequestParam(required = false) Long artistId, Model model) {
        Artist artist = artistId != null ? artistService.getArtistById(artistId) : new Artist();
        model.addAttribute("artist", artist);
        return "artists/create";
    }

    @PostMapping("/createArtist")
    public String saveArtist(
            @RequestParam(required = false) Long artistId,
            @RequestParam("name") String name,
            @RequestParam("bio") String bio,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        Artist artist = artistId != null ? artistService.getArtistById(artistId) : new Artist();
        artist.setName(name);
        artist.setBio(bio);

        if (imageFile != null && !imageFile.isEmpty()) {
            artist.setImage(imageFile.getBytes());
        }

        artistService.saveArtist(artist);
        return "redirect:/artists";
    }

    @GetMapping("/artists/edit/{id}")
    public String editArtist(@PathVariable Long id, Model model) {
        Artist artist = artistService.getArtistById(id);
        if (artist == null) {
            // Redirect to the artists list page if the artist does not exist
            return "redirect:/artists";
        }

        // Convert the image bytes to a Base64 string to display in the form
        if (artist.getImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(artist.getImage());
            model.addAttribute("base64Image", base64Image);
        }

        model.addAttribute("artist", artist);
        return "artists/edit"; // Thymeleaf template name
    }

    // Save the updated artist
    @PostMapping("/artists/save")
    public String saveArtist(@ModelAttribute Artist artist, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            // If a new image is uploaded, update the artist's image field
            if (!imageFile.isEmpty()) {
                artist.setImage(imageFile.getBytes());
            } else {
                // Retain the existing image if no new image is uploaded
                Artist existingArtist = artistService.getArtistById(artist.getArtistId());
                if (existingArtist != null) {
                    artist.setImage(existingArtist.getImage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }

        // Save the artist entity
        artistService.saveArtist(artist);

        return "redirect:/artists"; // Redirect back to the artists list page
    }

    @GetMapping("/artists/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getArtistImage(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        if (artist != null && artist.getImage() != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", "image/jpeg")
                    .body(artist.getImage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/deleteArtist")
    public String deleteArtist(@RequestParam Long artistId) {
        artistService.deleteArtist(artistId);
        return "redirect:/artists";
    }

    // albums controller

    @GetMapping("/albums")
    public String albumsList(Model model) {
        model.addAttribute("albums", albumService.getAlbums());
        return "albums/albums";
    }

    @GetMapping("/createAlbum")
    public String createAlbum(@RequestParam(required = false) Long albumsId, Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        Albums albums = albumsId != null ? albumService.getAlbumById(albumsId) : new Albums();
        model.addAttribute("albums", albums);
        return "albums/create";
    }

    @PostMapping("/createAlbum")
    public String addAlbum(
            @RequestParam("title") String title,
            @RequestParam("artistId") Long artistId,
            @RequestParam(value = "releaseDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) throws IOException {

        // Fetch the artist from the database
        Artist artist = new Artist();
        artist.setArtistId(artistId);

        // Create a new album object
        Albums album = new Albums();
        album.setTitle(title);
        album.setArtist(artist);
        album.setReleaseDate(releaseDate != null ? releaseDate : LocalDate.now());

        if (coverImage != null && !coverImage.isEmpty()) {
            album.setCoverImage(coverImage.getBytes());
        }

        albumService.saveAlbums(album);

        return "redirect:/albums";
    }

    @GetMapping("/albums/image/{id}")
    public ResponseEntity<byte[]> getAlbumImage(@PathVariable Long id) {
        Albums album = albumService.getAlbumById(id);
        if (album == null || album.getCoverImage() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust based on your image type
                .body(album.getCoverImage());
    }

    @GetMapping("/albums/edit/{id}")
    public String editAlbum(@PathVariable Long id, Model model) {
        Albums album = albumService.getAlbumById(id);
        if (album == null) {
            return "redirect:/albums"; // Redirect if album not found
        }
        if (album.getCoverImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(album.getCoverImage());
            model.addAttribute("base64Image", base64Image);
        }
        model.addAttribute("album", album);
        model.addAttribute("artists", artistService.getAllArtists()); // Populate artist dropdown
        return "albums/edit";
    }

    // Handle Save or Update
    @PostMapping("/albums/save")
    public String saveAlbum(
            Albums album,
            @RequestParam("coverImageFile") MultipartFile coverImageFile) {
        try {
            if (!coverImageFile.isEmpty()) {
                album.setCoverImage(coverImageFile.getBytes());
            } else if (album.getAlbumId() != null) {
                Albums existingAlbum = albumService.getAlbumById(album.getAlbumId());
                album.setCoverImage(existingAlbum.getCoverImage()); // Retain existing image if not updated
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/albums/edit/" + album.getAlbumId() + "?error";
        }

        albumService.saveAlbums(album);
        return "redirect:/albums";
    }

    @GetMapping("/deleteAlbum")
    public String deleteAlbum(@RequestParam Long albumId) {
        albumService.deleteAlbum(albumId);
        return "redirect:/albums";
    }

    // tracks controller
    @GetMapping("/tracks")
    public String tracksList(Model model) {
        model.addAttribute("tracks", trackService.getAllTracks());
        return "tracks/tracks";
    }

    @GetMapping("/createTracks")
public String createTrack(@RequestParam(required = false) Long trackId, Model model) {
    model.addAttribute("albums", albumService.getAlbums());
    Tracks tracks = trackId != null ? trackService.getTrackById(trackId) : new Tracks();
    model.addAttribute("tracks", tracks);
    return "tracks/create";
}

@PostMapping("/createTracks")
public String createTrack(@RequestParam("title") String title,
                          @RequestParam("albumId") Long albumId,
                          @RequestParam("duration") String duration,
                          @RequestParam("musicFile") MultipartFile musicFile,
                          Model model) throws IOException {

    Albums album = albumService.getAlbumById(albumId);
    // Create a new Tracks object and set the fields
    Tracks track = new Tracks();
    track.setTitle(title);
    track.setDuration(duration);
    track.setAlbum(album);

    // Handle the file upload
    if (!musicFile.isEmpty()) {
        track.setMusicFile(musicFile.getBytes());
    }

    // Save the track
    trackService.saveTrack(track);

    return "redirect:/tracks"; // Redirect after saving
}


    @GetMapping("/tracks/edit/{trackId}")
    public String editTrack(@PathVariable Long trackId, Model model) {
        Tracks track = trackService.getTrackById(trackId);
        if (track != null) {
            model.addAttribute("track", track);
            model.addAttribute("albums", albumService.getAlbums()); // List of albums for the dropdown
            return "tracks/edit"; // the edit form view
        }
        return "redirect:/tracks"; // If track is not found, redirect to list page
    }

    // Save the updated track information
    @PostMapping("/tracks/save")
    public String saveTrack(@ModelAttribute Tracks track, @RequestParam("musicFile") MultipartFile musicFile) throws IOException {
        // Handle the music file upload
        if (!musicFile.isEmpty()) {
            track.setMusicFile(musicFile.getBytes());
        }else{
            Tracks existingTrack = trackService.getTrackById(track.getTrackId());
        if (existingTrack != null) {
            track.setMusicFile(existingTrack.getMusicFile());
        }
        }
        trackService.saveTrack(track);
        return "redirect:/tracks"; // Redirect to list page after saving
    }

    @GetMapping("/deleteTrack")
    public String deleteTrack(@RequestParam Long trackId) {
        trackService.deleteTrack(trackId);
        return "redirect:/tracks";
    }

}
