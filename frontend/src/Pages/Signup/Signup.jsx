import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Copyright from "../../Components/Copyright/Copyright";
import { useNavigate } from "react-router-dom";

import "./Signup.css";
import { useState, useEffect } from "react";
import Loading from "../../Components/Loading";

const Signup = () => {
  const [errorMsg, setErrorMsg] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    if (localStorage.length !== 0) {
      navigate("/main");
    }
    setLoading(false);
  }, [navigate]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.currentTarget);
    const details = Object.fromEntries(formData.entries());
    console.log(details);
    signupUser(details);
  };

  const setLocalStorage = (userDetails) => {
    localStorage.setItem("id", userDetails.id);
    localStorage.setItem("username", userDetails.username);
    localStorage.setItem("email", userDetails.email);
    localStorage.setItem("jwt", userDetails.jwt);
  };

  const signupUser = (details) => {
    fetch("/api/v1/auth/signup", {
      headers: {
        "Content-type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(details),
    })
      .then((response) => {
        console.log(response);
        if (response.status === 200) {
          return response.json();
        } else if (response.status === 409 || response.status === 400) {
          setErrorMsg("Email or username already existing in database");
        } else {
          setErrorMsg("Something went wrong, try again later");
        }
      })
      .then((data) => {
        if (data) {
          console.log(data);
          setLocalStorage(data);
          navigate("/main");
        }
      });
  };

  const handleRedirection = () => {
    navigate("/login");
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
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="given-name"
                name="firstName"
                required
                fullWidth
                id="firstName"
                label="First Name"
                autoFocus
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="family-name"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="new-password"
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign Up
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="#" variant="body2" onClick={handleRedirection}>
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
      <Copyright sx={{ mt: 8, mb: 4 }} />
    </Container>
  );
};

export default Signup;
