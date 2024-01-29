package com.e.retry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "artist")
    private String artist;
    @Column(name = "title")
    private String title;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "user_song",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "user_custom_id")
    )
    private List<UserEntity> users;

    public void add(UserEntity user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
