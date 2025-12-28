import React, { useState } from "react";
import Header from "./components/Header";
import MovieList from "./components/MovieList";
import MoviePopup from "./components/MoviePopup";
import "./style.css";

const App = () => {
  const [searchQuery, setSearchQuery] = useState("");
  const [selectedMovie, setSelectedMovie] = useState(null);

  const handleSearch = (query) => {
    setSearchQuery(query);
  };

  const handleMovieClick = (movie) => {
    setSelectedMovie(movie);
  };

  const handleClosePopup = () => {
    setSelectedMovie(null);
  };

  return (
    <div className="app-container">
      <Header onSearch={handleSearch} />
      <MovieList searchQuery={searchQuery} onMovieClick={handleMovieClick} />
      <MoviePopup movie={selectedMovie} onClose={handleClosePopup} />
    </div>
  );
};

export default App;
