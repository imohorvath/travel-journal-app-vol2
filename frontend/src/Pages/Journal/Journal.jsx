import NoteList from "../../Components/NoteList";
import "./Journal.css";

import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { CardMedia, Container, Typography } from "@mui/material";

import { createClient } from "pexels";

const Journal = () => {
  const [journal, setJournal] = useState({});
  const { journalId } = useParams();
  const [imageURL, setImageURL] = useState(null);

  useEffect(() => {
    fetch(`/api/v1/journals/${journalId}`)
      .then((res) => res.json())
      .then((result) => {
        console.log(result);
        setJournal(result);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from /api/journal:${error}`)
      );
  }, [journalId]);

  useEffect(() => {
    getPexelImage(journal.title, journal.id);
  }, [journal]);

  const getPexelImage = (query, id) => {
    const client = createClient(
      "ObPMaZKwS1wvcaQJAHUkbW9dryeMn2WgxOyIJCGH7NwMo0tRFpgcZgvx"
    );

    client.photos
      .search({ query, per_page: 1 })
      .then((photo) => {
        setImageURL(photo.photos[0].src.landscape);
      })
      .catch((error) =>
        console.log(`An error occurred at fetching from Pexel`)
      );
  };

  return (
    <>
      <CardMedia
        sx={{
          backgroundImage: `url(${imageURL})`,
          backgroundSize: "cover",
          height: 100,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Typography
          component="h4"
          variant="h4"
          align="center"
          color="text.primary"
          gutterBottom
          sx={{
            fontFamily: "Satisfy",
          }}
        >
          {journal.title}
        </Typography>
      </CardMedia>
      <NoteList />
    </>
  );
};

export default Journal;
