package com.sat.ScoreTrackr.service;

import com.sat.ScoreTrackr.dao.ScoreDAO;
import com.sat.ScoreTrackr.entity.SatResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ScoreServiceImp implements ScoreService{

    private ScoreDAO scoreDAO;

    @Autowired
    public ScoreServiceImp(ScoreDAO theScoreDao){
        scoreDAO = theScoreDao;
    }

    @Transactional
    @Override
    public List<SatResults> findAll() {
        return scoreDAO.findAll();
    }

    @Transactional
    @Override
    public SatResults findByName(String name) {
        return scoreDAO.findByName(name);
    }

    @Transactional
    @Override
    public SatResults save(SatResults theSatResults) {
        return scoreDAO.save(theSatResults);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        scoreDAO.deleteByName(name);
    }

    @Transactional
    @Override
    public SatResults updateSatScore(String name, int newSatScore){return scoreDAO.updateSatScore(name,newSatScore);}

    @Transactional
    @Override
    public  int getRankByName(String name){return scoreDAO.getRankByName(name);}

}
