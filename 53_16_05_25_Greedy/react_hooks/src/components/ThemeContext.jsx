import React from "react";
import { createContext } from "react";

export const ThemeContext = createContext();

export const ThemeProvider = ({children}) =>{
    const [theme, setTheme] = React.useState("light");
    const toggleTheme = () => setTheme(prev => prev === "light" ? "dark" : "light");
    const setDark = () => setTheme("dark");

    return (
        <ThemeContext.Provider value={{theme, toggleTheme, setDark}}>
            {children}
        </ThemeContext.Provider>
    )
}