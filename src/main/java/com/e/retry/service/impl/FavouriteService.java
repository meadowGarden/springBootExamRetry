package com.e.retry.service.impl;

import com.e.retry.entity.SongEntity;
import com.e.retry.entity.UserEntity;
import com.e.retry.repository.IFavouriteSongRepository;
import com.e.retry.repository.IUserRepository;
import com.e.retry.service.IFavouriteSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteService implements IFavouriteSongService {

    private final IFavouriteSongRepository favouriteSongRepository;
    private final IUserRepository userRepository;

    @Autowired
    public FavouriteService(IFavouriteSongRepository favouriteSongRepository, IUserRepository userRepository) {
        this.favouriteSongRepository = favouriteSongRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SongEntity> showAllSongsByUser(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        return user.getSongs();
    }

    @Override
    @Transactional
    public List<SongEntity> addSong(String id, SongEntity song) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        List<SongEntity> songsInRepository = favouriteSongRepository.findAll();
        for (SongEntity s : songsInRepository) {
            if ((s.getArtist().equalsIgnoreCase(song.getArtist())) && (s.getTitle().equalsIgnoreCase(song.getTitle()))) {
                song = s;
                break;
            }
        }

        user.addSong(song);
        return user.getSongs();
    }

    @Override
    @Transactional
    public List<SongEntity> deleteSong(String id, SongEntity song) {

        UserEntity user = userRepository.findById(id).orElseThrow();
        List<SongEntity> songsInRepository = favouriteSongRepository.findAll();
        for (SongEntity s : songsInRepository) {
            if ((s.getArtist().equalsIgnoreCase(song.getArtist())) && (s.getTitle().equalsIgnoreCase(song.getTitle()))) {
                song = s;
                break;
            }
        }

        user.removeSong(song);
        return user.getSongs();
    }

    @Override
    public void addNewUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<UserEntity> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findUser(String id) {
        return userRepository.findById(id);
    }


}
