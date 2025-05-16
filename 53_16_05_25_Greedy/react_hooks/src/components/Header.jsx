import React, { useContext } from 'react';
import {ThemeContext} from './ThemeContext';

const Header = () => {
  const { theme, toggleTheme, setDark } = useContext(ThemeContext);

  return (
    <header style={{ background: theme === 'dark' ? '#333' : '#fff', color: theme === 'dark' ? '#fff' : '#000' }}>
      <h1>Current Theme: {theme}</h1>
      <button onClick={toggleTheme}>
        Switch Theme
      </button>
      <button onClick={setDark}>
        Set Dark Theme
      </button>
    </header>
  );
};

export default Header;