package com.sat.ScoreTrackr.service;

import com.sat.ScoreTrackr.entity.SatResults;

import java.util.List;

public interface ScoreService {
    List<SatResults> findAll();

    SatResults findByName(String name);

    SatResults save(SatResults theSatResults);

    void deleteByName(String name);

    SatResults updateSatScore(String name, int newSatScore);
}
