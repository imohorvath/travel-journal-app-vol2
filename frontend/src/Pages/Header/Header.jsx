import "./Header.css";
import {Outlet} from "react-router-dom";

const Header = () => {

    return (
        <>
            <h1>Hello Header!</h1>
            <Outlet />
        </>
    );

}

export default Header ;