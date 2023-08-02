import "./Main.css";
import JournalAlbum from "../../Components/JournalAlbum";
import JournalAlbumIntro from "../../Components/JournalAlbumIntro/JournalAlbumIntro";
import JournalCreate from "../../Components//JournalCreate/JournalCreate";

import { Container } from "@mui/material";
import { useState } from "react";

const Main = () => {
  const [showJournalCreate, setShowJournalCreate] = useState(false);

  const handleShowJournalCreate = () => {
    setShowJournalCreate(true);
  };

  const handleCloseJournalCreate = () => {
    setShowJournalCreate(false);
  };

  const handleSubmit = () => {
    setShowJournalCreate(false);
  };

  //TODO userId

  return (
    <>
      <JournalAlbumIntro onShowCreate={handleShowJournalCreate} />
      <Container maxWidth="md">
        {showJournalCreate && (
          <JournalCreate onCancel={handleCloseJournalCreate} onSubmit={handleSubmit} userId={1}/>
        )}
      </Container>
      <JournalAlbum />
    </>
  );
};

export default Main;
