public class Episode {
    private String name;
    private String recap;
    private EpisodeDate broadcastTime;



    public Episode(String name, String recap, EpisodeDate broadcastTime) {
        this.name = name;
        this.recap = recap;
        this.broadcastTime = broadcastTime;
    }

    public void printEpisodeAndRecap() {
        System.out.println(this.name);
        System.out.println(this.recap);
        System.out.println("⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸⊷ ⊸");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecap() {
        return recap;
    }

    public void setRecap(String recap) {
        this.recap = recap;
    }

    public EpisodeDate getBroadcastTime() {
        return broadcastTime;
    }

    public void setBroadcastTime(EpisodeDate broadcastTime) {
        this.broadcastTime = broadcastTime;
    }


    public void episodeDetails() {
        System.out.println("Chapter Summary:"+this.recap);
        System.out.println("Broadcast Time:\n" + this.broadcastTime);
    }



}
