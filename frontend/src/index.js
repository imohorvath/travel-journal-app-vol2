import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import reportWebVitals from "./reportWebVitals";

import ErrorPage from "./Pages/ErrorPage";
import LandingPage from "./Pages/LandingPage";
import Login from "./Pages/Login";
import Header from "./Pages/Header";
import Profile from "./Pages/Profile";
import Main from "./Pages/Main";
import Journal from "./Pages/Journal";

import "./index.css";
import {createTheme, ThemeProvider} from "@mui/material/styles";

const theme = createTheme({
    palette: {
    primary: {
        // light: will be calculated from palette.primary.main,
        main: '#ff4400',
        // dark: will be calculated from palette.primary.main,
        // contrastText: will be calculated to contrast with palette.primary.main
    },
    secondary: {
        light: '#0066ff',
            main: '#0044ff',
            // dark: will be calculated from palette.secondary.main,
            contrastText: '#ffcc00',
    },
    // Provide every color token (light, main, dark, and contrastText) when using
    // custom colors for props in Material UI's components.
    // Then you will be able to use it like this: `<Button color="custom">`
    // (For TypeScript, you need to add module augmentation for the `custom` value)
    custom: {
        light: '#ffa726',
            main: '#f57c00',
            dark: '#ef6c00',
            contrastText: 'rgba(0, 0, 0, 0.87)',
    },
    // Used by `getContrastText()` to maximize the contrast between
    // the background and the text.
    contrastThreshold: 3,
        // Used by the functions below to shift a color's luminance by approximately
        // two indexes within its tonal palette.
        // E.g., shift from Red 500 to Red 300 or Red 700.
        tonalOffset: 0.2,
},
}
)

const theme2 = createTheme({
    palette: {
        primary: {
            main: '#ff8c00', // Stronger warm pastel orange color
        },
        secondary: {
            main: '#9c6042', // Warm pastel brown color
        },
        custom: {
            light: '#d6a27c', // Light warm pastel brown
            main: '#988c89', // Main warm pastel brown
            dark: '#865c3a', // Dark warm pastel brown
            contrastText: '#ffffff', // White or off-white for text contrast
        },
        contrastThreshold: 3,
        tonalOffset: 0.2,
    },
});

  
  

const router = createBrowserRouter([
    {
        path: "/",
        element: <Header />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "/",
                element: <LandingPage />,
            },
            {
                path: "/login",
                element: <Login />,
            },
            {
                path: "/main",
                element: <Main />,
            },
            {
                path: "/:journalId",
                element: <Journal />,
            },
            {
                path: "/profile",
                element: <Profile />,
            }
        ],
    }
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <React.StrictMode>
        <ThemeProvider theme={theme2}>
        <RouterProvider router={router} />
        </ThemeProvider>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
