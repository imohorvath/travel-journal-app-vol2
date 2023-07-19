import "./LandingPage.css";
import React from "react";
import Card from "@mui/material/Card";
import CardActionArea from "@mui/material/CardActionArea";
import CardMedia from "@mui/material/CardMedia";

const LandingPage = () => {

    return (
        <>
            <Card raised={true}>
                <CardActionArea>
                    <CardMedia
                        component="video"
                        image={"../../video/banner_vid.mp4"}
                        muted
                        /*autoPlay*/
                        loop
                    />
                </CardActionArea>
            </Card>
        </>
    );

};

export default LandingPage;