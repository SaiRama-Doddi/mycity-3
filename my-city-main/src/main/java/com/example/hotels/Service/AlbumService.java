package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Albums;
import com.example.hotels.Repository.AlbumsRepository;



@Service
public class AlbumService {
    
    @Autowired
    public AlbumsRepository albumsRepository;

    public List<Albums> getAlbums(){
        return albumsRepository.findAll();
    }

    public Albums getAlbumById(Long albumId) {
        return albumsRepository.findById(albumId).orElse(null);
    }

    public void deleteAlbum(Long albumId) {
        albumsRepository.deleteById(albumId);
    }

    public Albums saveAlbums(Albums albums){
        return albumsRepository.save(albums);
    }

    public Albums updateAlbums(Albums albums){
        return albumsRepository.save(albums);
    }
}
