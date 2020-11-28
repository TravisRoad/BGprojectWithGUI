package model.boardgamefetched;

public class HotBoardGame {
    private int rank;
    private long gameId;
    private String name;
    private String thumbnail;
    private int yearPublished;

    public HotBoardGame(int rank, long gameId, String name, String thumbnail, int yearPublished) {
        super();
        this.rank = rank;
        this.gameId = gameId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.yearPublished = yearPublished;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}
