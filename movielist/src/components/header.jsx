import React, { useState } from "react";

const Header = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleInputChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchClick = () => {
    if (searchTerm.trim() !== "") {
      onSearch(searchTerm);
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      handleSearchClick();
    }
  };

  return (
    <header className="header">
      <div className="search">
        <input
          type="text"
          placeholder="Search Movie..."
          value={searchTerm}
          onChange={handleInputChange}
          onKeyDown={handleKeyPress}
        />
        <button onClick={handleSearchClick}>Search</button>
      </div>
    </header>
  );
};

export default Header;
