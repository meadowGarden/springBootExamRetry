package com.e.retry.entity;

public class EntityConverter {

    public static Song SongEntityToSong(SongEntity songEntity) {
        return Song.builder()
                .artist(songEntity.getArtist())
                .title(songEntity.getTitle())
                .build();
    }

    public static User UserEntityToUser(UserEntity user) {
        return User.builder()
                .name(user.getName())
                .build();
    }
}
