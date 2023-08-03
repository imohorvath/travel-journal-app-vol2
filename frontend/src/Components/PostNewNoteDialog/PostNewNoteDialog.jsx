import "./PostNewNoteDialog.css";
import { useEffect, useState } from "react";

import {
  Box,
  Button,
  CardActions,
  Container,
  Stack,
  Typography,
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
  DialogContentText,
  TextField,
} from "@mui/material";

const PostNewNoteDialog = ({ open, onClose, onSubmit }) => {
    const [text, setText] = useState("");

    const handleSave = (e) => {
        e.preventDefault();

        const newNote = {
            text: text
        };

        onSubmit(newNote);
        setText("");
    };

  return (
    <>
      <Dialog onClose={onClose} open={open} onSubmit={onSubmit} fullWidth>
        <DialogTitle>Let's preserve your precious memories</DialogTitle>
        <DialogContent></DialogContent>
        <DialogContent>
          <TextField
            autoFocus
            id="outlined-multiline-flexible"
            label="Your thoughts"
            type="text"
            multiline
            fullWidth
            rows={5}
            value={text}
            onChange={(e) => setText(e.target.value)}
          />
        </DialogContent>
        <DialogActions
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-evenly",
          }}
        >
          <Button size="medium" onClick={handleSave}>
            SUBMIT
          </Button>
          <Button size="medium" onClick={onClose}>
            CANCEL
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default PostNewNoteDialog;
