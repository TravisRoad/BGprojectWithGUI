package model.boardgamefetched;

import java.io.Serializable;
import java.util.List;

/**
 * 自动生成模型
 * Auto-generated: 2020-11-28 18:54:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BoardGameFetched implements Serializable {
    private int gameId;
    private String name;
    private String description;
    private String image;
    private String thumbnail;
    private int minPlayers;
    private int maxPlayers;
    private int playingTime;
    private List<String> mechanics;
    private boolean isExpansion;
    private int yearPublished;
    private double bggRating;
    private double averageRating;
    private int rank;
    private List<String> designers;
    private List<String> publishers;
    private List<String> artists;
    private List<PlayerPollResults> playerPollResults;
    private List<Expansions> expansions;

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setMechanics(List<String> mechanics) {
        this.mechanics = mechanics;
    }

    public List<String> getMechanics() {
        return mechanics;
    }

    public void setIsExpansion(boolean isExpansion) {
        this.isExpansion = isExpansion;
    }

    public boolean getIsExpansion() {
        return isExpansion;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setBggRating(double bggRating) {
        this.bggRating = bggRating;
    }

    public double getBggRating() {
        return bggRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setDesigners(List<String> designers) {
        this.designers = designers;
    }

    public List<String> getDesigners() {
        return designers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setPlayerPollResults(List<PlayerPollResults> playerPollResults) {
        this.playerPollResults = playerPollResults;
    }

    public List<PlayerPollResults> getPlayerPollResults() {
        return playerPollResults;
    }

    public void setExpansions(List<Expansions> expansions) {
        this.expansions = expansions;
    }

    public List<Expansions> getExpansions() {
        return expansions;
    }

}