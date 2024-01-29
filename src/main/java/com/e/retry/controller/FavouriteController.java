package com.e.retry.controller;

import com.e.retry.entity.*;
import com.e.retry.service.IFavouriteSongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class FavouriteController {

    private final IFavouriteSongService favouriteSongService;

    @Autowired
    public FavouriteController(IFavouriteSongService favouriteSongService) {
        this.favouriteSongService = favouriteSongService;
    }

    @GetMapping(path = "/favourites")
    public ResponseEntity<List<Song>> showAllSongs(@RequestParam String id) {
        final List<SongEntity> songs = favouriteSongService.showAllSongsByUser(id);
        final List<Song> songsToDisplay = songs.stream()
                .map(EntityConverter::SongEntityToSong)
                .toList();
        return new ResponseEntity<>(songsToDisplay, HttpStatus.OK);
    }

    @PostMapping(path = "/favourites")
    public ResponseEntity<List<Song>> addSong(@RequestParam String id, @RequestBody SongEntity songEntity) {
        final List<SongEntity> songs = favouriteSongService.addSong(id, songEntity);
        final List<Song> songsToDisplay = songs.stream()
                .map(EntityConverter::SongEntityToSong)
                .toList();
        return new ResponseEntity<>(songsToDisplay, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/favourites")
    public ResponseEntity<SongEntity> deleteSong(@RequestParam String id, @RequestBody SongEntity songEntity) {
        final List<SongEntity> songs = favouriteSongService.deleteSong(id, songEntity);
        final List<Song> songsToDisplay = songs.stream()
                .map(EntityConverter::SongEntityToSong)
                .toList();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(path = "/users")
    public ResponseEntity<User> addNewUser(@RequestBody UserEntity userEntity){
        favouriteSongService.addNewUser(userEntity);
        final User user = EntityConverter.UserEntityToUser(userEntity);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> showAllUsers() {
        final List<UserEntity> users = favouriteSongService.showAllUsers();
        final List<User> usersToDisplay = users.stream()
                .map(EntityConverter::UserEntityToUser)
                .toList();
        return new ResponseEntity<>(usersToDisplay, HttpStatus.OK);
    }

}
