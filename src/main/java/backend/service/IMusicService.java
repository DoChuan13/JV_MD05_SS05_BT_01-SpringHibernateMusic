package backend.service;

import backend.model.Music;

import java.util.List;

public interface IMusicService {
    List<Music> findAll();

    Music findById(Long id);

    void save(Music music);

    void deleteById(Long id);
}
