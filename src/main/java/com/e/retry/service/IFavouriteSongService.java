package com.e.retry.service;

import com.e.retry.entity.SongEntity;
import com.e.retry.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IFavouriteSongService {

    List<SongEntity> showAllSongsByUser(String id);

    List<SongEntity> addSong(String id, SongEntity song);

    List<SongEntity> deleteSong(String id, SongEntity song);



    void addNewUser(UserEntity user);
    List<UserEntity> showAllUsers();
    Optional<UserEntity> findUser(String id);
}



