package com.sat.ScoreTrackr.rest;

import com.sat.ScoreTrackr.entity.SatResults;
import com.sat.ScoreTrackr.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/")
public class ScoreRestController {
    private ScoreService scoreService;

    @Autowired
    public ScoreRestController(ScoreService theScoreService){
        scoreService = theScoreService;
    }

    @GetMapping("/")
    public String index(){
        return "hiiiii";
    }

    @GetMapping("/results")
    @ResponseBody
    public List<SatResults> findall(){
        return scoreService.findAll();
    }


@PostMapping("/results")
public ResponseEntity<?> saveSatResults(@RequestBody SatResults satResults) {
    try {
        int satScore = satResults.getSatScore();
        String result = (satScore > 30) ? "Pass" : "Fail";
        satResults.setResult(result);
        int rank = calculateRankForNewResult(satScore);
        satResults.setCandidateRank(rank);
        SatResults savedResult = scoreService.save(satResults);

        // Return a 201 Created status with the saved data
        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    } catch (DataIntegrityViolationException e) {
        // Handle the duplicate entry exception
        return new ResponseEntity<>("Duplicate name is not allowed.", HttpStatus.CONFLICT);
    }
}

    private int calculateRankForNewResult(int newSatScore) {
        List<SatResults> allResults = scoreService.findAll();
        allResults.sort(Comparator.comparingInt(SatResults::getSatScore).reversed());

        int rank = 1;
        for (SatResults result : allResults) {
            if (newSatScore < result.getSatScore()) {
                rank++;
            } else {
                break;
            }
        }
        return rank;
    }

    @GetMapping("/results/rank/{name}")
    public ResponseEntity<Integer> getRankByName(@PathVariable String name) {
        int rank = scoreService.getRankByName(name);

        if (rank != -1) {
            return ResponseEntity.ok(rank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/results/update")
    @ResponseBody
    public ResponseEntity<?> updateSatScore(@RequestParam String name, @RequestParam int newSatScore) {
        // Add logic to update the SAT score by candidate name
        SatResults result = scoreService.updateSatScore(name, newSatScore);
        if (result == null) {
            return new ResponseEntity<>("Candidate Not Found.", HttpStatus.CONFLICT);
        }
      return  new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/results/{name}")
    public SatResults viewTask(@PathVariable String name) {
        SatResults result = scoreService.findByName(name);
        if(result == null){
            throw new RuntimeException("Task not found: " + name);
        }

        return result;
    }

    @DeleteMapping("/results/{name}")
    @ResponseBody
    public String deleteTasks(@PathVariable String name){
        SatResults result = scoreService.findByName(name);

        if (result == null) {
            throw new RuntimeException("Result not found for name: " + name);
        }

        scoreService.deleteByName(name);
        return "DELETED RESULT";
    }
}
