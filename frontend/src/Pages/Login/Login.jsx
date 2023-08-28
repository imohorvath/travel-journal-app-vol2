import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Copyright from "../../Components/Copyright/Copyright";
import Loading from '../../Components/Loading';
import {useNavigate} from "react-router-dom";
import { useState, useEffect } from "react";

import "./Login.css";


const Login = () => {
    const [errorMsg, setErrorMsg] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        if(localStorage.length !== 0) {
            navigate("/main");
        }
        setLoading(false);
    }, [navigate]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const details = Object.fromEntries(formData.entries());
        console.log(details);
        loginUser(details);
    };

    const setLocalStorage = (userDetails) => {
        localStorage.setItem("id", userDetails.id);
        localStorage.setItem("username", userDetails.username);
        localStorage.setItem("email", userDetails.email);
        localStorage.setItem("jwt", userDetails.jwt);
    }

    const loginUser = (details) => {
        fetch("/api/v1/auth/login", {
            headers: {
                "Content-type" : "application/json", 
            },
            method: "post",
            body: JSON.stringify(details),
        })
        .then((response) => {
            console.log(response);
            if (response.status === 200) {
                return response.json()
            }
            else if (response.status === 401 || response.status === 403) {
              setErrorMsg("Invalid username or password");
            } else {
              setErrorMsg(
                "Something went wrong, try again later"
              );
            }
          })
          .then((data) => {
            if (data) {
                console.log(data);
                setLocalStorage(data);
                navigate('/main');
            }
        });
    }

    const handleRedirection = () => {
        navigate("/signup");
    }

    if (loading) {
        return <Loading />;
    }

    return (
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email Address"
                            name="email"
                            autoComplete="email"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                        />
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary" />}
                            label="Remember me"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item xs>
                                <Link href="#" variant="body2">
                                    Forgot password?
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="#" variant="body2" onClick={handleRedirection}>
                                    {"Don't have an account? Sign Up"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
                <Copyright sx={{ mt: 8, mb: 4 }} />
            </Container>
    );

};

export default Login;