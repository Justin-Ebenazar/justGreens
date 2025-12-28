import React, { useEffect, useState } from "react";

const API_KEY = "05a48e07dd74eac37038a4f45055c720";
const BASE_URL = "https://api.themoviedb.org/3";

const MovieList = ({ searchQuery, onMovieClick }) => {
  const [movies, setMovies] = useState([]);

 
  const fetchTrendingMovies = async () => {
    try {
      const response = await fetch(`${BASE_URL}/trending/movie/day?api_key=${API_KEY}`);
      const data = await response.json();
      setMovies(data.results);
    } catch (error) {
      console.error("Error fetching trending movies:", error);
    }
  };

  const fetchSearchedMovies = async (query) => {
    try {
      const response = await fetch(
        `${BASE_URL}/search/movie?api_key=${API_KEY}&query=${query}`
      );
      const data = await response.json();
      setMovies(data.results);
    } catch (error) {
      console.error("Error fetching searched movies:", error);
    }
  };

  useEffect(() => {
    if (searchQuery) {
      fetchSearchedMovies(searchQuery);
    } else {
      fetchTrendingMovies();
    }
  }, [searchQuery]);

  return (
    <section className="movies-container">
      <h1>{searchQuery ? `Results for "${searchQuery}"` : "Trending Movies"}</h1>
      <div className="movies-grid">
        {movies && movies.length > 0 ? (
          movies.map((movie) => (
            <div
              className="card"
              key={movie.id}
              onClick={() => onMovieClick(movie)}
            >
              <div className="img">
                {movie.poster_path ? (
                  <img
                    src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`}
                    alt={movie.title}
                  />
                ) : (
                  <div className="no-image">No Image</div>
                )}
              </div>
              <div className="info">
                <h2>{movie.title}</h2>
                <div className="single-info">
                  <span>Rate: </span>
                  <span>{movie.vote_average.toFixed(1)} / 10</span>
                </div>
                <div className="single-info">
                  <span>Release Date: </span>
                  <span>{movie.release_date || "N/A"}</span>
                </div>
              </div>
            </div>
          ))
        ) : (
          <p>No movies found.</p>
        )}
      </div>
    </section>
  );
};

export default MovieList;
