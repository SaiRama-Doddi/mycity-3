package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Tracks;
import com.example.hotels.Repository.TrackRepository;


@Service
public class TrackService {
    
     @Autowired
    private TrackRepository trackRepository;

    public Tracks saveTrack(Tracks track) {
        return trackRepository.save(track);
    }

    public List<Tracks> getAllTracks() {
        return trackRepository.findAll();
    }

    public Tracks getTrackById(Long trackId) {
        return trackRepository.findById(trackId).orElse(null);
    }

    public void deleteTrack(Long trackId) {
        trackRepository.deleteById(trackId);
    }

    public Tracks updateTrack(Tracks track) {
        return trackRepository.save(track);
    }
}
