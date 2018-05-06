package miskow.tomasz.allegro;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FilmsController.class)
public class AllegroApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFilmsReturnsEmptyArray() throws Exception {
        this.mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getFilmReturnsNotFound() throws Exception {
        this.mockMvc.perform(get("/films/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postFilmAddsNewFilm() throws Exception {
        final String TITLE = "Pulp Fiction";
        final String YEAR = "1994";
        final String DIRECTOR = "Quentin Tarantino";
        final String JSON_STRING = String.format(
                "{\"title\": \"%s\", \"year\": \"%s\", \"director\": \"%s\"}",
                TITLE, YEAR, DIRECTOR
        );

        this.mockMvc.perform(post("/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_STRING))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].title").value(TITLE));

        this.mockMvc.perform(get("/films/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.year").value(YEAR))
                .andExpect(jsonPath("$.director").value(DIRECTOR));
    }
}
