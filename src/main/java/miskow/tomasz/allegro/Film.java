package miskow.tomasz.allegro;

import java.time.Year;
import java.util.concurrent.atomic.AtomicLong;

public class Film {
    private static final AtomicLong idCounter = new AtomicLong();

    public class Summary {
        private final long id;
        private final String title;

        public Summary(long id, String title) {
            this.id = id;
            this.title = title;
        }

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    private final long id;
    private final String title;
    private final Year year;
    private final String director;

    public Film() {
        this(null, null, null);
    }

    public Film(String title, Year year, String director) {
        this.id = idCounter.getAndIncrement();
        this.title = title;
        this.year = year;
        this.director = director;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public Summary summary() {
        return new Summary(id, title);
    }
}
