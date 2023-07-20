import "./LandingPage.css";
import React from "react";
import Card from "@mui/material/Card";
import CardActionArea from "@mui/material/CardActionArea";
import CardMedia from "@mui/material/CardMedia";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {useNavigate} from "react-router-dom";

const LandingPage = () => {

    const navigate = useNavigate();

    const handleRedirection = () => {
        navigate(`/login`);
    }

    return (
        <>
            <Card raised={true}>
                <CardActionArea>
                    <CardMedia
                        component="video"
                        image={"../../video/banner_vid.mp4"}
                        muted
                        autoPlay
                        loop
                    />
                </CardActionArea>
            </Card>
            <Container>
                <Paper
                    elevation="0"
                    sx={{
                        mx: {xs: 0.5, sm: 5},
                        my: {xs: 3, sm: 5},
                        /*typography: {
                            fontSize: {xs: 10, sm: 10}
                        }*/
                    }}
                >
                    <Typography
                        variant="h5"
                        textAlign="center"
                        sx={{
                            px: {xs: 1, sm: 3},
                            py: {xs: 2, sm: 3}
                        }}
                    >
                        Welcome to our Travel Journal app - your passport to preserving and sharing your adventures!
                    </Typography>
                    <Typography
                        textAlign="center"
                        sx={{
                            px: {xs: 1, sm: 3},
                            py: {xs: 2, sm: 3},
                            fontSize: 18
                        }}
                    >
                        Seamlessly record your travel experiences, snap photos, and jot down thoughts on-the-go with our
                        user-friendly interface.
                        Relive the magic of your journeys and inspire others to wander with your beautifully crafted
                        digital travel journals.
                    </Typography>
                </Paper>
                <Paper
                    elevation="2"
                    sx={{
                        mx: {xs: 0.5, sm: 5},
                        my: {xs: 3, sm: 5},
                        display: 'flex',
                        flexDirection: 'column'
                        /*typography: {
                            fontSize: {xs: 10, sm: 10}
                        }*/
                    }}
                >
                    <Typography
                        variant="h5"
                        textAlign="center"
                        sx={{
                            px: {xs: 1, sm: 3},
                            py: {xs: 2, sm: 3}
                        }}
                    >
                        Are you ready for your next adventure?
                    </Typography>
                    <Button
                        onClick={handleRedirection}
                    >
                        <Typography>
                            Sign in
                        </Typography>
                    </Button>
                </Paper>
            </Container>
        </>
    );

};

export default LandingPage;