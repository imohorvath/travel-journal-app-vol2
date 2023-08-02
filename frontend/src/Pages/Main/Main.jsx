import "./Main.css";
import JournalAlbum from "../../Components/JournalAlbum";
import JournalAlbumIntro from "../../Components/JournalAlbumIntro/JournalAlbumIntro";
import JournalCreate from "../../Components//JournalCreate/JournalCreate";

import { Container } from "@mui/material";
import { useState, useEffect } from "react";

const Main = () => {
  const [showJournalCreate, setShowJournalCreate] = useState(false);
  const [journalList, setJournalList] = useState([]);

  useEffect(() => {
    fetch("/api/v1/users/1/journals")
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setJournalList(result);
        //  setLoading(false);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/journal:${error}`)
      );
  }, []);


  const handleShowJournalCreate = () => {
    setShowJournalCreate(true);
  };

  const handleCloseJournalCreate = () => {
    setShowJournalCreate(false);
  };

  const handleSubmit = () => {
    setShowJournalCreate(false);
  };

  const refreshJournalListAfterDelete = (journalId) => {
    setJournalList((journalList) => {
      return journalList.filter((journal) => journal.id !== journalId);
    });
  }

  //TODO userId

  return (
    <>
      <JournalAlbumIntro onShowCreate={handleShowJournalCreate} />
      <Container maxWidth="md">
        {showJournalCreate && (
          <JournalCreate onCancel={handleCloseJournalCreate} onSubmit={handleSubmit} userId={1}/>
        )}
      </Container>
      <JournalAlbum journalList={journalList} refreshJournalList={refreshJournalListAfterDelete}/>
    </>
  );
};

export default Main;
