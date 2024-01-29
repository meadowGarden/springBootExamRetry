package com.e.retry.repository;

import com.e.retry.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavouriteSongRepository extends JpaRepository<SongEntity, String> {
}
