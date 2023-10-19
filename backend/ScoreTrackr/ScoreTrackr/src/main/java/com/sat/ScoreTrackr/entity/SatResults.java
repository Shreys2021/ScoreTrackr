package com.sat.ScoreTrackr.entity;


import jakarta.persistence.*;

@Entity
@Table(name="satresults")
public class SatResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name",unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "satScore")
    private int satScore;

    @Column(name = "result")
    private String result;

    @Column(name = "candidateRank")
    private int candidateRank;


    public SatResults() {
    }

    public SatResults(String name, String address, String city, String country, String pincode, int satScore, String result,int candidateRank) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
        this.satScore = satScore;
        this.result = result;
        this.candidateRank = candidateRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getSatScore() {
        return satScore;
    }

    public void setSatScore(int satScore) {
        this.satScore = satScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCandidateRank() {
        return candidateRank;
    }

    public void setCandidateRank(int candidateRank) {
        this.candidateRank = candidateRank;
    }

    @Override
    public String toString() {
        return "SatResults{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", satScore=" + satScore +
                ", result='" + result + '\'' +
                ", candidateRank=" + candidateRank +
                '}';
    }
}
