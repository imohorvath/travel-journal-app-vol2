import "./NoteCard.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import {
  Card,
  CardActions,
  CardContent,
  Grid,
  Typography,
  Box,
  IconButton,
} from "@mui/material";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import EditIcon from '@mui/icons-material/Edit';

const NoteCard = ({ note, onDelete, onEdit }) => {
  return (
    <Grid item xs={12} key={note.id}>
      <Card>
        <CardContent>
          <Typography
            gutterBottom
            sx={{
              fontSize: 18,
              fontFamily: "Handlee",
            }}
          >
            {note.text}
          </Typography>
        </CardContent>

        <CardContent
          sx={{
            display: "flex",
            flexDirection: { xs: "column", sm: "row" },
            justifyContent: "space-between",
          }}
        >
          <Box
            sx={{
              display: "flex",
              flexDirection: "row",
            }}
          >
            <Typography variant="body2" color="textSecondary">
              {note.createdAt.split("T")[0]}
            </Typography>
            <Typography variant="body2" color="textSecondary" sx={{ px: 2 }}>
              {note.createdBy.username}
            </Typography>
          </Box>

          <Box
            sx={{
              display: "flex",
              flexDirection: "row",
            }}
          >
            <Typography variant="body2" color="textSecondary">
              {note.updatedAt.split("T")[0]}
            </Typography>
            <Typography variant="body2" color="textSecondary" sx={{ px: 2 }}>
              {note.updatedBy.username}
            </Typography>
          </Box>
        </CardContent>

        <CardActions>
          <IconButton aria-label="Edit" onClick={() => onEdit(note)} color="primary" fontSize="large">
            <EditIcon />
          </IconButton>
          <IconButton aria-label="Delete" onClick={() => onDelete(note)} fontSize="small">
            <DeleteForeverIcon />
          </IconButton>
        </CardActions>
      </Card>
    </Grid>
  );
};

export default NoteCard;
