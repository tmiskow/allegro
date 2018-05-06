package miskow.tomasz.allegro;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class FilmsController {
    private final Map<Long, Film> filmsMap = new ConcurrentHashMap<>();

    @RequestMapping("/films")
    public List<Film.Summary> getFilms() {
        return filmsMap.values().stream().map(Film::summary).collect(Collectors.toList());
    }

    @RequestMapping("/films/{id}")
    public Film getFilm(@PathVariable long id) throws NotFoundException {
        Film film = filmsMap.get(id);

        if (film == null) {
            throw new NotFoundException("Invalid film ID.");
        }

        return film;
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public void postFilm(@RequestBody Film film) {
        long id = film.getId();
        filmsMap.put(id, film);
    }
}
