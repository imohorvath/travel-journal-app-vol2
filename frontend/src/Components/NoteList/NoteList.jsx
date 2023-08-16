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
import AddIcon from "@mui/icons-material/Add";

import NoteCard from "../NoteCard";
import PostNewNoteDialog from "../PostNewNoteDialog";
import ConfirmationDialog from "../ConfirmationDialog/ConfirmationDialog";

const NoteList = () => {
  const { journalId } = useParams();
  const [noteList, setNoteList] = useState([]);
  const [currentNote, setCurrentNote] = useState("");
  const [openPostDialog, setOpenPostDialog] = useState(false);
  const [openConfirmDialog, setOpenConfirmDialog] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`/api/v1/journals/${journalId}/notes/`, {
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("jwt")
      },
    })
      .then((res) => res.json())
      .then((result) => {
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

  const handleOpenPostDialog = () => {
    setOpenPostDialog(true);
  };

  const handleClosePostDialog = () => {
    setOpenPostDialog(false);
  };

  const handleDeleteClicked = (note) => {
    setCurrentNote(note);
    setOpenConfirmDialog(true);
  };

  const handleCloseConfirmDialog = () => {
    setCurrentNote("");
    setOpenConfirmDialog(false);
  };

  //TODO get userId

  const handlePostNewNote = (newNote) => {
    
    fetch(`/api/v1/journals/${journalId}/notes/1`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newNote),
    })
      .then((res) => res.json())
      .then((result) => {
        setNoteList([...noteList, result]);
      })
      .catch((error) =>
        console.log(
          `An error occurred at fetching from /api/v1/journals/${journalId}/notes/:${error}`
        )
      );

    setOpenPostDialog(false);
  };

  const handleDeleteNote = () => {

    fetch(`/api/v1/notes/${currentNote.id}`, {
      method: "DELETE",
    })
      .then((res) => {
        console.log(res);
        if (!res.ok) {
          throw new Error("Network response was not ok.");
        }
        setNoteList(notelist => {return notelist.filter(note => note.id !== currentNote.id)});
        setCurrentNote("");
      })
      .catch((error) => {
        console.log(error);
      });

    setOpenConfirmDialog(false);
  };


  const handleEditNote = () => {
    console.log("This is handleEditNote");
  };

  return (
    <>
      <Container sx={{ py: 8 }} maxWidth="md">
        <Grid container spacing={2}>
          {noteList.map((note) => (
            <NoteCard
              key={note.id}
              note={note}
              onDelete={handleDeleteClicked}
              onEdit={handleEditNote}
            />
          ))}
        </Grid>
        <Fab
          color="primary"
          aria-label="add"
          onClick={handleOpenPostDialog}
          sx={{ position: "fixed", bottom: 30, right: 30, zIndex: 2000 }}
        >
          <AddIcon />
        </Fab>
        <PostNewNoteDialog
          open={openPostDialog}
          onClose={handleClosePostDialog}
          onSubmit={handlePostNewNote}
        />
        <ConfirmationDialog
          open={openConfirmDialog}
          onClose={handleCloseConfirmDialog}
          itemTitle={currentNote && currentNote.text.substring(0, 20) + "..."}
          onSubmit={handleDeleteNote}
        />
      </Container>
    </>
  );
};

export default NoteList;
