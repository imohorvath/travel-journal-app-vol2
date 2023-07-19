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
        <RouterProvider router={router} />
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
