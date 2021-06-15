
public class Series {
    private String name;
    private Episode[] episodes;
    private Episode lastEpisodeWatched;

    public Series(String name, Episode[] episodes) {
        this.name = name;
        this.episodes = episodes;
    }
    public void printEpisodesNames() {
        for (int i = 0; i < episodes.length; i++) {
            System.out.println(i + 1 + "-" + episodes[i].getName());
            System.out.println();
        }
    }



    public Episode getLastEpisodeWatched() {
        return lastEpisodeWatched;
    }

    public void setLastEpisodeWatched(Episode lastEpisodeWatched) {
        this.lastEpisodeWatched = lastEpisodeWatched;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episode[] episodes) {
        this.episodes = episodes;
    }

}
