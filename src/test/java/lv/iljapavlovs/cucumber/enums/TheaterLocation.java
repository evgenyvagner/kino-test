package lv.iljapavlovs.cucumber.enums;


public enum TheaterLocation {

    ALFA_RIGA("alfa.cinamonkino.com"),
    KOSMOS_TALLINN("kosmos.cinamonkino.com"),
    MEGA_KAUNAS("mega.cinamonkino.com");

    private String movieTheaterLocation;

    TheaterLocation(String movieTheaterLocation) {
        this.movieTheaterLocation = movieTheaterLocation;
    }

    @Override
    public String toString() {
        return movieTheaterLocation;
    }
}
