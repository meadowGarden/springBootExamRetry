package com.e.retry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_custom")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "user_song",
            joinColumns = @JoinColumn(name = "user_custom_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<SongEntity> songs;

    public void addSong(SongEntity song) {
        if (song == null) {
            songs = new ArrayList<>();
        }
        songs.add(song);
    }

    public void removeSong(SongEntity song) {
        if (song == null) {
            System.out.println("no such song exists");
        }
        songs.remove(song);
    }




}
