import "./Header.css";
import {Outlet} from "react-router-dom";
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

    const [auth, setAuth] = useState(false);
    const [openMenu, setOpenMenu] = useState(false);

    const settings = ['Profile', 'Journals', 'Logout'];

    const handleOpenUserMenu = (event) => {
        console.log(event);
        setOpenMenu(true);
        //setAnchorElUser(event.currentTarget);
    };

    const handleCloseUserMenu = (event) => {
        console.log(event);
        setOpenMenu(false);
        //setAnchorElUser(null);
    };

    return (
        <>
            <AppBar
                position="static"
                color="default"
                elevation={0}
                sx={{borderBottom: (theme) => `1px solid ${theme.palette.divider}`}}
            >
                <Toolbar sx={{flexWrap: 'wrap'}}>
                    <Typography variant="h5" color="inherit" noWrap sx={{flexGrow: 1}}>
                        Travel Journal
                    </Typography>
                    {!auth && <Button href="#" variant="outlined" sx={{my: 1, mx: 1.5}}>
                        Login
                    </Button>}
                    {!auth && <Box sx={{flexGrow: 0}}>
                        <Tooltip title="Open settings">
                            <IconButton onClick={(event)=>handleOpenUserMenu(event)} sx={{p: 0}}>
                                <Avatar alt="Pinguin" src="../../img/animal(6).png"/>
                            </IconButton>
                        </Tooltip>
                        {openMenu && <Menu
                            sx={{mt: '45px'}}
                            id="menu-appbar"
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            onClose={(event)=>handleCloseUserMenu(event)}
                        >
                            {settings.map((setting) => (
                                <MenuItem key={setting} onClick={(event)=>handleCloseUserMenu(event)}>
                                    <Typography textAlign="center">{setting}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>}
                    </Box>}
                </Toolbar>
            </AppBar>
            <Outlet/>
        </>
    );

}

export default Header;