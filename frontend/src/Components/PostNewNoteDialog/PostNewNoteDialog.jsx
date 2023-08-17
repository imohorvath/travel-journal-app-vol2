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
  Input,
} from "@mui/material";

const PostNewNoteDialog = ({ open, onClose, onSubmit }) => {
  const MAX_COUNT = 5;

  const [text, setText] = useState("");
  const [uploadedFiles, setUploadedFiles] = useState([]);
  const [fileLimit, setFileLimit] = useState(false);

  const handleSave = (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append("text", text);

    uploadedFiles.forEach(file => formData.append("files", file));

    onSubmit(formData);
    setText("");
    setUploadedFiles([]);
    setFileLimit(false);
  };

  const handleFileUpload = (e) => {
    e.preventDefault();
    const chosenFiles = Array.prototype.slice.call(e.target.files);

    const uploaded = [...uploadedFiles];
    let limitExceeded = false;

    chosenFiles.some(file => {
      if (uploaded.findIndex((f) => f.name === file.name) === -1) {
        uploaded.push(file);
        if (uploaded.length === MAX_COUNT) {
          setFileLimit(true);
        }
        if (uploaded.length > MAX_COUNT) {
          alert(`You can only add a maximum of ${MAX_COUNT} files`);
          setFileLimit(false);
          limitExceeded = true;
          return true;
        }
      }
    });
    if (!limitExceeded) {
      setUploadedFiles(uploaded);
    }
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
        <DialogContent>
          <label htmlFor="upload-image">
            <Button variant="contained" component="span" disabled={fileLimit}>
              Upload Images
            </Button>
            <input
              id="upload-image"
              hidden
              multiple
              accept="image/jpeg, image/png, image/jpeg, image/bmp"
              type="file"
              disabled={fileLimit}
              onChange={handleFileUpload}
            />
          </label>
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
