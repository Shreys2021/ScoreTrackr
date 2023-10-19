package com.sat.ScoreTrackr.dao;

import com.sat.ScoreTrackr.entity.SatResults;

import java.util.List;

public interface ScoreDAO {

    List<SatResults> findAll();

    SatResults findByName(String name);

    SatResults save(SatResults theTask);

    void deleteByName(String name);

    SatResults updateSatScore(String name, int newSatScore);

    int getRankByName(String name);
}
