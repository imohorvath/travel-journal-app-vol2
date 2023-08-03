import "./JournalAlbumItem.css";
import { useEffect, useState } from "react";
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardMedia,
  Grid,
  Typography,
  IconButton,
} from "@mui/material";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";

const JournalAlbumItem = ({ journal, imageURLs, onOpen, onDelete, isOwnJournal }) => {

  return (
    <>
      <Grid item key={journal.id} xs={12} sm={6} md={4}>
        <Card
          sx={{
            height: "100%",
            display: "flex",
            flexDirection: "column",
          }}
        >
          <CardMedia
            component="div"
            sx={{
              // 16:9
              pt: "56.25%",
            }}
            image={
              imageURLs.find((item) => item.journalId === journal.id)?.url || ""
            }
          />
          <CardContent sx={{ flexGrow: 1 }}>
            <Typography
              gutterBottom
              variant="h5"
              component="h2"
              sx={{
                fontFamily: "Satisfy",
              }}
            >
              {journal.title}
            </Typography>
            <Typography>{journal.createdAt.split("T")[0]}</Typography>
          </CardContent>
          <CardActions
            sx={{
              display: "flex",
              flexDirection: "row",
              justifyContent: "space-between",
            }}
          >
            <Button size="small" onClick={() => onOpen(journal.id)}>
              Open
            </Button>
            {isOwnJournal && <IconButton aria-label="Delete" onClick={() => onDelete(journal)}>
              <DeleteForeverIcon />
            </IconButton>}
          </CardActions>
        </Card>
      </Grid>
    </>
  );
};

export default JournalAlbumItem;
