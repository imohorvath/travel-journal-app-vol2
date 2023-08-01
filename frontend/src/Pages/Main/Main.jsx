import "./Main.css";
import JournalAlbum from "../../Components/JournalAlbum";
import JournalAlbumIntro from "../../Components/JournalAlbumIntro/JournalAlbumIntro";
import JournalCreate from "../../Components//JournalCreate/JournalCreate";

import { Container } from "@mui/material";

const Main = () => {
  return (
    <>
      <JournalAlbumIntro />
      <Container sx={{ py: 8 }} maxWidth="md">
        <JournalCreate />
      </Container>
      <JournalAlbum />
    </>
  );
};

export default Main;
