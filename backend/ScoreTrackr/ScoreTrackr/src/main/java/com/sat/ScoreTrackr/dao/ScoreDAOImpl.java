package com.sat.ScoreTrackr.dao;

import com.sat.ScoreTrackr.entity.SatResults;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreDAOImpl implements  ScoreDAO {

    private EntityManager entityManager;

    @Autowired
    public ScoreDAOImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<SatResults> findAll() {
        return entityManager.createQuery("SELECT s FROM SatResults s", SatResults.class).getResultList();
    }

    @Override
    public SatResults findByName(String name) {
        try {
            return entityManager.createQuery("SELECT s FROM SatResults s WHERE TRIM(s.name) = :name", SatResults.class)
                    .setParameter("name", name.trim()) // Trim the input name
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public SatResults save(SatResults satResults) {
        // If the entity already has an ID (not null), merge it, otherwise persist it.
        if (satResults.getId() == 0) {
            entityManager.persist(satResults);
        } else {
            entityManager.merge(satResults);
        }
        return satResults;
    }


    @Override
    public SatResults updateSatScore(String name, int newSatScore) {
        try {
            SatResults result = entityManager.createQuery("SELECT s FROM SatResults s WHERE TRIM(s.name) = :name", SatResults.class)
                    .setParameter("name", name.trim()) // Trim the input name
                    .getSingleResult();

            // Update the SAT score
            result.setSatScore(newSatScore);
            result.setResult(newSatScore > 30 ? "Pass" : "Fail");

            // Merge the updated entity back into the persistence context
            entityManager.merge(result);

            return result;
        } catch (NoResultException e) {
            return null; // Candidate not found
        }
    }

    @Override
    public void deleteByName(String name) {
        entityManager.createQuery("DELETE FROM SatResults s WHERE s.name = :name")
                .setParameter("name", name)
                .executeUpdate();
    }
}
