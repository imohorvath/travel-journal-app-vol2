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
        <ThemeProvider theme={theme}>
            <RouterProvider router={router} />
        </ThemeProvider>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
