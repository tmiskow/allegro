# allegro
Simple spring application.

## Endpoints

### GET /films
Returns the list of films.

Example response:
```json
[
  {
    "id": 0,
    "title": "Pulp Fiction"
  },
  {
    "id": 1,
    "title": "Fight Club"
  }
]
```

### POST /films
Adds a new film.

Example request:
```json
{
  "title": "Pulp Fiction",
  "year": "1994", 
  "director": "Quentin Tarantino"
}
```

### GET /films/{id}
Returns details of a film with provided id.

Example response:
```json
{
  "id": 0,
  "title": "Pulp Fiction",
  "year": "1994",
  "director": "Quentin Tarantino"
}
```
