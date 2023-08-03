import "./NoteList.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import {
  Card,
  CardActions,
  CardContent,
  Container,
  Grid,
  Button,
  Typography,
  Box,
  IconButton,
  Fab,
} from "@mui/material";
import AddIcon from '@mui/icons-material/Add';

import NoteCard from "../NoteCard";

const NoteList = () => {
  const { journalId } = useParams();
  const [noteList, setNoteList] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`/api/v1/journals/${journalId}/notes/`)
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setNoteList(result);
        setLoading(false);
      })
      .catch((error) =>
        console.log(
          `An error occurred at fetching from /api/v1/journals/${journalId}/notes/:${error}`
        )
      );
  }, [journalId]);

  // if (loading) {
  //     return <Loading />;
  // }

  const handlePostNewNote = () => {
    console.log("This is handlePostNewNote");
  };

  const handleDeleteNote = (id) => {
    console.log("This is handlePostNewNote");
  };

  const handleEditNote = () => {
    console.log("This is handlePostNewNote");
  };

  return (
    <>
      <Container sx={{ py: 8 }} maxWidth="md">
        <Grid container spacing={2}>
          {noteList.map((note) => (
            <NoteCard
              key={note.id}
              note={note}
              onDelete={handleDeleteNote}
              onEdit={handleEditNote}
            />
          ))}
        </Grid>
        <Fab color="primary" 
              aria-label="add"
              onClick={handlePostNewNote}
              sx={{ position: "fixed", bottom: 30, right: 30, zIndex: 2000 }}
        >
          <AddIcon />
        </Fab>
      </Container>
    </>
  );
};

export default NoteList;
