import React from "react";

const MoviePopup = ({ movie, onClose }) => {
  if (!movie) return null;

  return (
    <div className="popup-overlay" onClick={onClose}>
      <div className="popup-content" onClick={(e) => e.stopPropagation()}>
        <span className="close-btn" onClick={onClose}>
          &times;
        </span>
        <div className="popup-body">
          <div className="popup-left">
            {movie.poster_path ? (
              <img
                src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`}
                alt={movie.title}
              />
            ) : (
              <div className="no-image">No Image</div>
            )}
          </div>

          <div className="popup-right">
            <h1>{movie.title}</h1>
            <p>
              <strong>Release Date:</strong> {movie.release_date || "N/A"}
            </p>
            <p>
              <strong>Rating:</strong> {movie.vote_average.toFixed(1)} / 10
            </p>
            <p className="overview">{movie.overview}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MoviePopup;
