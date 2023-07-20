import "./Header.css";
import {Outlet, useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import {useState} from "react";
import React from "react";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import {IconButton, Menu, MenuItem, Tooltip} from "@mui/material";

const Header = () => {

    const [auth, setAuth] = useState(true);
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);

    const navigate = useNavigate();

    const settings = ['Profile', 'Journals', 'Logout'];

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = (event) => {
        switch (event.target.innerText) {
            case "Profile" : {
                navigate(`/profile`);
                break;
            }
            case "Journals" : {
                navigate(`/main`);
                break;
            }
            case "Logout" : {
                navigate(`/`);
                setAuth(false);
                break;
            }
        }
        setAnchorEl(null);
    };

    return (
        <>
            <AppBar
                position="static"
                elevation={0}
                sx={{
                    borderBottom: 1,
                    bgcolor: "#d6a27c"
                }}
            >
                <Toolbar sx={{flexWrap: 'wrap'}}>
                    <Typography variant="h5" color="inherit" noWrap sx={{flexGrow: 1}}>
                        Travel Journal
                    </Typography>
                    {!auth && <Button href="#" variant="outlined" sx={{my: 1, mx: 1.5}}>
                        Login
                    </Button>}
                    {auth && <Box sx={{flexGrow: 0}}>
                        <Tooltip title="Open settings">
                            <IconButton
                                onClick={handleClick}
                                sx={{p: 0}}
                            >
                                <Avatar alt="Pinguin" src="../../img/animal(6).png"/>
                            </IconButton>
                        </Tooltip>
                        <Menu
                            sx={{mt: '45px'}}
                            id="menu-appbar"
                            anchorEl={anchorEl}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={open}
                            onClose={handleClose}
                        >
                            {settings.map((setting) => (
                                <MenuItem key={setting} onClick={handleClose}>
                                    <Typography textAlign="center">{setting}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>}
                </Toolbar>
            </AppBar>
            <Outlet/>
        </>
    );

}

export default Header;